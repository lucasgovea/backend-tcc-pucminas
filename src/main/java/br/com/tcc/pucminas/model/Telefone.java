package br.com.tcc.pucminas.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Embeddable
public class Telefone {

	@NotBlank(message = "DDD é obrigatorio")
	private String ddd;
	
	@NotBlank(message = "Telefone é obrigatorio")
	private String telefone;
}
