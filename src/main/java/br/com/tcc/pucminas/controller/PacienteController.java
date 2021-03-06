package br.com.tcc.pucminas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.pucminas.dto.PacienteFilterDTO;
import br.com.tcc.pucminas.model.Paciente;
import br.com.tcc.pucminas.service.PacienteService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/pacientes")
@RestController
public class PacienteController {
	
	@Autowired
	PacienteService pacienteService;
	
	@GetMapping("/{idPaciente}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Paciente> listarPacientes(@PathVariable Long idPaciente) {
		Paciente paciente = pacienteService.buscarPorId(idPaciente);
		return ResponseEntity.status(HttpStatus.OK).body(paciente);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<List<Paciente>> buscarPaciente(PacienteFilterDTO dadosBusca) {
		List<Paciente> pacientes = pacienteService.buscarFiltrando(dadosBusca);
		return ResponseEntity.status(HttpStatus.OK).body(pacientes);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Paciente> inserir(@RequestBody @Valid Paciente paciente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.inserir(paciente));
	}
	
	@DeleteMapping("/{idPaciente}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Void> excluir(@PathVariable Long idPaciente) {
		pacienteService.excluir(idPaciente);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("/{idPaciente}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Paciente> atualizar(@PathVariable Long idPaciente, @RequestBody @Valid Paciente paciente) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(pacienteService.atualizar(idPaciente, paciente));
	}
	
	
}

