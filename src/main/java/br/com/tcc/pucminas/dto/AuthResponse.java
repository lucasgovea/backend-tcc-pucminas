package br.com.tcc.pucminas.dto;

import java.util.List;

public class AuthResponse {

	
	private String usuario;
	private String token;
	private List<String> perfis;
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<String> getPerfis() {
		return perfis;
	}
	public void setPerfis(List<String> perfis) {
		this.perfis = perfis;
	}
	
	
	
	
}
