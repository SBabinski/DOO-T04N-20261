package calculadora;

public class Cliente extends Endereco {
	String nome;
	int idade;
	String cidade;
	String rua;
	String bairro;
	int loja;
	
	public Cliente() {
		
	}
	
	public Cliente(String nome, int idade, String cidade, String rua, String bairro, int loja) {
		setNome(nome);
		setIdade(idade);
		setCidade(cidade);
		setRua(rua);
		setBairro(bairro);
		setLoja(loja);
	}
	
	public int getLoja() {
		return loja;
	}

	public void setLoja(int loja) {
		this.loja = loja;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String apresentarse() {
		return "Nome: " + nome + "|| Idade: " + idade + "|| Loja: " + (loja+1);
	}
}