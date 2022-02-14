package br.com.tcc.pucminas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tcc.pucminas.model.Paciente;
import br.com.tcc.pucminas.repository.PacienteRepository;
import br.com.tcc.pucminas.service.exception.EntityNotFoundException;

@Service
public class PacienteService {
	
	@Autowired
	PacienteRepository pacienteRepo;
	
	public List<Paciente> buscarTodos() {
		return pacienteRepo.findAll();
	}
	
	public Paciente buscarPorId(Long id) {
		return pacienteRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada pelo id "+id));
	}

	public Paciente inserir(Paciente paciente) {
		return pacienteRepo.save(paciente);
	}
	
}
