package br.com.tcc.pucminas.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableRabbit
public class RabbitMqConfig {

	public final static String FILA_EMAIL_CONFIRMAR_AGENDAMENTO = "email-comfirmar-agendamento";
	
	@Bean
	public RabbitTemplate rabbitTemplate(ObjectMapper objectMapper, ConnectionFactory factory) {
		RabbitTemplate template = new RabbitTemplate();
		template.setConnectionFactory(factory);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		template.setMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
		return template;
	}
	
	@Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
	
	@Bean
    public Queue queue() {
        return new Queue(FILA_EMAIL_CONFIRMAR_AGENDAMENTO);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("email-direct-exchange");
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("routing-key");
    }
	
}
