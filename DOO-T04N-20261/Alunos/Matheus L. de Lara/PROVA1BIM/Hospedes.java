package prov1bim.java;

public class Hospedes {
	private String nome;
	private String cpf;
	private String tel;
	
	public Hospedes() {
		
	}
	public Hospedes(String nome, String cpf, String tel) {
		this.nome = nome;
		this.cpf = cpf;
		this.tel = tel;
	}
	
	public String getnome() {
		return nome;
	}
	
	public void setnome(String nome) {
		this.nome = nome;
	}
	
	public String getcpf() {
		return cpf;
	}
	
	public void setcpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String tel() {
		return tel;
	}
	
	public void settel(String tel) {
		this.tel = tel;
	}
}
	