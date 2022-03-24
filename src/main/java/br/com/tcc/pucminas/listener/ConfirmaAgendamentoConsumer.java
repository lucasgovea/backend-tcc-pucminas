package br.com.tcc.pucminas.listener;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tcc.pucminas.config.RabbitMqConfig;
import br.com.tcc.pucminas.model.DadosEmailConfirmacao;
import br.com.tcc.pucminas.service.EmailSenderService;

@Component
@RabbitListener(queues = RabbitMqConfig.FILA_EMAIL_CONFIRMAR_AGENDAMENTO)
public class ConfirmaAgendamentoConsumer {

	@Autowired
	EmailSenderService emailService;
	
	@RabbitHandler
	public void processar(DadosEmailConfirmacao dados) throws MessagingException, IOException {
		emailService.sendEmail(dados);
	}
}
