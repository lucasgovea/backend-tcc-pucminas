package br.com.tcc.pucminas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.tcc.pucminas.dto.AgendamentoDTO;
import br.com.tcc.pucminas.model.Agendamento;
import br.com.tcc.pucminas.service.AgendamentoService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/agendamentos")
@Controller
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
	
	@GetMapping("/confirmar")
	public ResponseEntity<String> confirmarConsulta(@RequestParam("id") Long idAgendamento, @RequestParam("confirm") boolean isConfirmado) {
		agendamentoService.confirmarAgendamento(idAgendamento, isConfirmado);
		if(isConfirmado) {
			return ResponseEntity.status(HttpStatus.OK).body("Consulta confirmada com sucesso");
		}
		return ResponseEntity.status(HttpStatus.OK).body("A consulta foi cancelada");
	}
	
	@PostMapping("enviar-email-confirmacao/{idAgendamento}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Agendamento> envirarEmailConfirmacao(@PathVariable Long idAgendamento) {
		agendamentoService.enviarEmailConfirmacao(idAgendamento);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
}

