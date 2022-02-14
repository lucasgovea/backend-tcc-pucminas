package br.com.tcc.pucminas.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String login;
	
	private String senha;
	
	@JsonIgnoreProperties("usuarios")
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USUARIO_PERFIL", joinColumns = {
			@JoinColumn(name = "id_usuario", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_perfil", referencedColumnName = "id")})
	private Set<Perfil> perfis;
	

}

