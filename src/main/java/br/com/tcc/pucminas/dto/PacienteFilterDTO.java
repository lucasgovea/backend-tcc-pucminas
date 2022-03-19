package br.com.tcc.pucminas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PacienteFilterDTO {

	private String nome;
	private String cpf;
	
}
