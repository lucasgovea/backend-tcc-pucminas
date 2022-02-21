package br.com.tcc.pucminas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.tcc.pucminas.model.Agendamento;
import br.com.tcc.pucminas.repository.AgendamentoRepository;
import br.com.tcc.pucminas.service.exception.HorarioIndisponivelException;

@Service
public class AgendamentoService {
	
	
	private final AgendamentoRepository agendamentoRepo;
	private final PacienteService pacienteService;
	private final ProfissionalService profissionalService;
	
	public AgendamentoService(AgendamentoRepository agendamentoRepo, PacienteService pacienteService,
			ProfissionalService profissionalService) {
		this.agendamentoRepo = agendamentoRepo;
		this.pacienteService = pacienteService;
		this.profissionalService = profissionalService;
	}

	public List<Agendamento> buscarTodos() {
		return agendamentoRepo.findAll();
	}
	
	public Agendamento buscarPorId(Long id) {
		return agendamentoRepo.findById(id).orElse(null);
	}
	
	public Agendamento agendar(Long idPaciente, Long idProfissional, LocalDateTime horario) {
		
		verificarHorarioProfissionalEstaDisponivel(idProfissional, horario);
		
		Agendamento agendamento = Agendamento.builder()
			.marcacao(horario)
			.paciente(pacienteService.buscarPorId(idPaciente))
			.profissional(profissionalService.buscarPorId(idProfissional))
			.build();
		
		return agendamentoRepo.save(agendamento);
	}

	private void verificarHorarioProfissionalEstaDisponivel(Long idProfissional, LocalDateTime horario) {
		if(agendamentoRepo.findByProfissionalIdAndMarcacao(idProfissional, horario).size() > 0) {
			throw new HorarioIndisponivelException("O profissional nao atende neste horario ou o mesmo já esta ocupado");
		}
	}
	
}
