package br.com.tcc.pucminas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.pucminas.dto.AgendamentoDTO;
import br.com.tcc.pucminas.model.Agendamento;
import br.com.tcc.pucminas.service.AgendamentoService;

@RequestMapping("/api/agendamentos")
@RestController
public class AgendamentoController {
	
	@Autowired
	AgendamentoService agendamentoService;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<List<Agendamento>> listarAgendamentos() {
		List<Agendamento> agendamentos = agendamentoService.buscarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(agendamentos);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Agendamento> agendar(@RequestBody AgendamentoDTO dto) {
		Agendamento agendamento = agendamentoService.agendar(dto.getIdPaciente(), dto.getIdProfissional(), dto.getMarcacao());
		return ResponseEntity.status(HttpStatus.OK).body(agendamento);
	}
	
	
}

