package br.com.tcc.pucminas.dto;

import br.com.tcc.pucminas.model.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PacienteResponseDTO {

	private Long id;
	private String nome;
	private String cpf;
	private Agendamento proximoAgendamento;
}
