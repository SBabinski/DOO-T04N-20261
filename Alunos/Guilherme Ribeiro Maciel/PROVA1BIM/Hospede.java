package prova;

public class Hospede {
	
	String nome;
	String cpf;
	int fone;
	
	public Hospede() {
		
	}
	
	public Hospede(String nome, String cpf, int fone) {
		setNome(nome);
		setCpf(cpf);
		setFone(fone);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public int getFone() {
		return fone;
	}
	
	public void setFone(int fone) {
		this.fone = fone;
	}
	
	public String apresentarHospede() {
		return nome + "|| " + cpf + "|| " + fone ;
	}
}
