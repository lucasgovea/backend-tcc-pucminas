package br.com.tcc.pucminas.service;

import org.springframework.stereotype.Service;

import br.com.tcc.pucminas.model.Cep;
import br.com.tcc.pucminas.service.client.CepClient;

@Service
public class CepService {
	
	private CepClient cepCliente;
	
	
	public CepService(CepClient cepCliente) {
		this.cepCliente = cepCliente;
	}


	public Cep buscarCep(String cep) {
		return cepCliente.getCep(cep);
	}
}
