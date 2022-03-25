package br.com.tcc.pucminas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AgendamentoFilterDTO {

	private String nome;
	private String cpf;
	private Boolean proximas;
	
}
