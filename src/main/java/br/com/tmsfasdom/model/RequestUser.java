package br.com.tmsfasdom.model;

public class RequestUser {
	
	private String cpf;
	private String senha;
	private long expires;
	
	
	
	public RequestUser() {
		super();
	}

	public RequestUser(String cpf, String senha) {
		super();
		this.cpf = cpf;
		this.senha = senha;
	}
		
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public long getExpires() {
		return expires;
	}
	
	public void setExpires(long expires) {
		this.expires = expires;
	}
	
	

}
