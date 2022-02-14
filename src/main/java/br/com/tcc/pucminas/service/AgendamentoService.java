package br.com.tcc.pucminas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tcc.pucminas.model.Agendamento;
import br.com.tcc.pucminas.repository.AgendamentoRepository;

@Service
public class AgendamentoService {
	
	@Autowired
	AgendamentoRepository agendamentoRepo;
	
	public List<Agendamento> buscarTodos() {
		return agendamentoRepo.findAll();
	}
	
	public Agendamento buscarPorId(Long id) {
		return agendamentoRepo.findById(id).orElse(null);
	}
	
}
