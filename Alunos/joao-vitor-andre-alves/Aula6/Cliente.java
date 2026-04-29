package objects;

public class Cliente {

	private String nome;
	private int idade;
	private String bairro;
	private String rua;
	
	// GETTERS E SETTERS
		// NOME
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
		// IDADE
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
		// BAIRRO
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
		// RUA
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}

	// METODOS
		// APRESENTACAO DO USUARIO
	public String apresentarSe() {
		return "Olá, meu nome é " + nome + " e eu tenho " + idade + " anos!";
	}
    
        // LISTAGEM DE CLIENTES
	public void listarCliente() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Bairro: " + bairro);
        System.out.println("Rua: " + rua);
        System.out.println("--------------------------");
    }   
}
