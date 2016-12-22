package br.com.tmsfasdom.model;

public class RetornoAD {

	private String primeiroNome;
	private String ultimoNome;
	private String login;
	private String email;
	private String mensagem;
	
	public RetornoAD() {
	}
	public RetornoAD(String primeiroNome, String ultimoNome, String login, String email, String mensagem) {
		super();
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.login = login;
		this.email = email;
		this.setMensagem(mensagem);
	}
	public String getPrimeiroNome() {
		return primeiroNome;
	}
	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	public String getUltimoNome() {
		return ultimoNome;
	}
	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString(){
		return mensagem + "\nprimeiroNome: " + primeiroNome + " ultimoNome: " + ultimoNome + " email: " + email + " login: " + login;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
