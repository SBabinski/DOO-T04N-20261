package objetos;

public class Hospede {
	String nome;
	String cpf;
	String telefone;
	
	
	public Hospede(String nome,	String cpf,String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	public void exibirInformacao() {
		System.out.println("Nome do hospede: "+ nome);
		System.out.println("CPF : "+ cpf);
		System.out.println("Telefone : "+ telefone);
	}
	
	
	
	
}
