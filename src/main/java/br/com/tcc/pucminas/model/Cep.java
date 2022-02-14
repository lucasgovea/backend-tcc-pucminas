package br.com.tcc.pucminas.model;

import lombok.Data;

@Data
public class Cep {
	
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String complemento;
	
}

