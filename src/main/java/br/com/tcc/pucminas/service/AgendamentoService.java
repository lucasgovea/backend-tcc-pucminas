package br.com.tcc.pucminas.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.tcc.pucminas.model.Agendamento;
import br.com.tcc.pucminas.model.DadosEmailConfirmacao;
import br.com.tcc.pucminas.producer.ConfirmaAgendamentoProducer;
import br.com.tcc.pucminas.repository.AgendamentoRepository;
import br.com.tcc.pucminas.service.exception.ConfirmacaoEmailException;
import br.com.tcc.pucminas.service.exception.HorarioIndisponivelException;

@Service
public class AgendamentoService {
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
	private final AgendamentoRepository agendamentoRepo;
	private final PacienteService pacienteService;
	private final ProfissionalService profissionalService;
	private final ConfirmaAgendamentoProducer confirmaAgendamentoProducer;
	
	public AgendamentoService(AgendamentoRepository agendamentoRepo, PacienteService pacienteService,
			ProfissionalService profissionalService, ConfirmaAgendamentoProducer confirmaAgendamentoProducer) {
		this.agendamentoRepo = agendamentoRepo;
		this.pacienteService = pacienteService;
		this.profissionalService = profissionalService;
		this.confirmaAgendamentoProducer = confirmaAgendamentoProducer;
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
	
	@Transactional
	public void confirmarAgendamento(Long idAgendamento, boolean isConfirmado) {
		Optional<Agendamento> agendamentoOpt = agendamentoRepo.findById(idAgendamento);
		if(agendamentoOpt.isPresent()) {
			agendamentoOpt.get().setConfirmado(isConfirmado);
		}
	}
	
	@Scheduled(cron = "0 0 8 * * *", zone = TIME_ZONE) //todo dia as 8h
	protected void enfileirarEmailConfirmacao() {
		List<Agendamento> agendamentos = agendamentoRepo.buscarAgendamentosPeloDia(LocalDate.now().plusDays(1));
		for (Agendamento agendamento : agendamentos) {
			DadosEmailConfirmacao dados = montarDadosEmailConfirmacao(agendamento);
			confirmaAgendamentoProducer.adicionarFila(dados);
		}
	}

	public void enviarEmailConfirmacao(Long idAgendamento) {
		Agendamento agendamento = agendamentoRepo.findById(idAgendamento).orElseThrow(() -> new ConfirmacaoEmailException("Agendamento nao encontrado"));
		DadosEmailConfirmacao dados = montarDadosEmailConfirmacao(agendamento);
		confirmaAgendamentoProducer.adicionarFila(dados);
	}
	
	private DadosEmailConfirmacao montarDadosEmailConfirmacao(Agendamento agendamento) {
		Map<String, Object> props = new HashMap<>();
		props.put("nomePaciente", agendamento.getPaciente().getNome());
		props.put("nomeProfissional", agendamento.getProfissional().getNome());
		props.put("datahoraAgendamento", agendamento.getMarcacaoAsString());
		props.put("idAgendamento", agendamento.getId());
		props.put("nomeEstabelecimento", "DentalClin");
		DadosEmailConfirmacao dados = new DadosEmailConfirmacao("meusistema@meusistema.com", agendamento.getPaciente().getEmail(), "Confirmação Consulta", null, props);
		return dados;
	}
		
}
