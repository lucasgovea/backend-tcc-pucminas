package br.com.tcc.pucminas.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosEmailConfirmacao {
	
	private String from;
	private String mailTo;
	private String subject;
	private List<Object> attachments;
	private Map<String, Object> props;
	
}
