
public class Hospede {
	String nome;
	String cpf;
	String telefone;
	
	public Hospede(String nome, String cpf, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	public void exibeH() {
		System.out.println("Nome: " + nome);
		System.out.println("Cpf: "+ cpf);
		System.out.println("Telefone: " + telefone);
	}
}
