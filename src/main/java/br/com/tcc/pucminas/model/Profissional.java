package br.com.tcc.pucminas.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tcc.pucminas.converter.TipoProfissionalConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause="deletado = false")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profissional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome é obrigatorio")
	private String nome;
	
	@Column(unique=true)
	@CPF(message = "CPF invalido")
	private String cpf;
	
	@NotNull(message = "A area de atuacao e obrigatoria")
	@Convert(converter=TipoProfissionalConverter.class)
	private TipoProfissional atuacao;

	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	
	@JsonIgnore
	private boolean deletado;
	
	
	
}

