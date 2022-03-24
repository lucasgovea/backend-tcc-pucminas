package br.com.tcc.pucminas.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.pucminas.config.RabbitMqConfig;
import br.com.tcc.pucminas.model.DadosEmailConfirmacao;

@Component
public class ConfirmaAgendamentoProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void adicionarFila(DadosEmailConfirmacao dados) {
		rabbitTemplate.convertAndSend(RabbitMqConfig.FILA_EMAIL_CONFIRMAR_AGENDAMENTO, dados);
	}
}
