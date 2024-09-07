package Agenda;

public class Contato {
	private String nome;
	private String apelido;
	private String numero;
	private String email;
	
	public Contato() {
		// TODO Auto-generated constructor stub
	}
	public Contato(String nome, String apelido, String numero, String email) {
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
		this.numero = numero;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNome() {
		return nome;
	}
	public String getApelido() {
		return apelido;
	}
	public String getEmail() {
		return email;
	}
	public String getNumero() {
		return numero;
	}
	

}
