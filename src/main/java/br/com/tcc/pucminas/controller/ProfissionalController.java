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

import br.com.tcc.pucminas.dto.ProfissionalDTO;
import br.com.tcc.pucminas.dto.ProfissionalFilterDTO;
import br.com.tcc.pucminas.model.Profissional;
import br.com.tcc.pucminas.service.ProfissionalService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/profissionais")
@RestController
public class ProfissionalController {
	
	@Autowired
	ProfissionalService profissionalService;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<List<Profissional>> buscarProfissionais(ProfissionalFilterDTO dadosBusca) {
		List<Profissional> profissionais = profissionalService.buscarFiltranto(dadosBusca);
		return ResponseEntity.status(HttpStatus.OK).body(profissionais);
	}
	
	@GetMapping("/{idProfissional}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Profissional> buscarProfissional(@PathVariable Long idProfissional) {
		Profissional profissional = profissionalService.buscarPorId(idProfissional);
		return ResponseEntity.status(HttpStatus.OK).body(profissional);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Profissional> inserir(@RequestBody(required = true) @Valid ProfissionalDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(profissionalService.inserir(dto));
	}
	
	@DeleteMapping("/{idProfissional}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Void> excluir(@PathVariable Long idProfissional) {
		profissionalService.excluir(idProfissional);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("/{idProfissional}")
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Profissional> atualizar(@PathVariable Long idProfissional, @RequestBody @Valid ProfissionalDTO dto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(profissionalService.atualizar(idProfissional, dto));
	}
	
	
}

