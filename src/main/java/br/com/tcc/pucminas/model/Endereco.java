package br.com.tcc.pucminas.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Endereco {
	
	private String logradouro;
	private String numero;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;

	
	
	
}

