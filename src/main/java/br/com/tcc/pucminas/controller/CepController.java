package br.com.tcc.pucminas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.pucminas.model.Cep;
import br.com.tcc.pucminas.service.CepService;

@RequestMapping("/api/cep")
@RestController
public class CepController {
	
	@Autowired
	CepService cepService;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('admin')")
	public ResponseEntity<Cep> consultarCep(@RequestParam(required=true) String cep) {
		Cep cepConsultado = cepService.buscarCep(cep.replaceAll("\\D", ""));
		return ResponseEntity.status(HttpStatus.OK).body(cepConsultado);
	}
	
}

