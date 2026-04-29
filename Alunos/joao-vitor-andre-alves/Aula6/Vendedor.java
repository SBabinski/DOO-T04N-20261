package objects;

public class Vendedor {

	private String nome;
	private int idade;
	private String loja;
	private String cidade;
	private String bairro;
	private String rua;
	private float salarioBase;
	private float[] salarioRecebido;
	
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
	
		// LOJA
	public String getLoja() {
		return loja;
	}
	public void setLoja(String loja) {
		this.loja = loja;
	}
	
		// CIDADE
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
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
	
		// SALARIO BASE
	public float getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(float salarioBase) {
		this.salarioBase = salarioBase;
	}
	
		// SALARIO RECEBIDO
	public float[] getSalarioRecebido() {
		return salarioRecebido;
	}
	public void setSalarioRecebido(float[] salarioRecebido) {
		this.salarioRecebido = salarioRecebido;
	}
	
	// METODOS
		// APRESENTAÇÃO DO USUÁRIO
	public String apresentarSe() {
		return "Olá, meu nome é " + nome + " e eu tenho " + idade + " anos! Eu trabalho na loja" + loja;
	}
		
		// CALCULA MEDIA SALARIAL BASEADO NOS REGISTROS DE SALARIO RECEBIDO
	public float calcularMedia() {
		float somaSalariosRecebidos = 0;
		
		for (int i = 0; i < salarioRecebido.length; i++) {
			somaSalariosRecebidos += salarioRecebido[i];
		}
		
		float mediaSalarial = somaSalariosRecebidos / salarioRecebido.length;
		
		return mediaSalarial;
	}
	
		// CALCULA O BONUS PARA O REGISTRO DO SALARIO DO USUARIO
	public float calcularBonus() {
		float bonus = salarioBase * 0.2f;
		
		return bonus;
	}

        // LISTAGEM DE VENDEDORES
    public void listarVendedor() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
        System.out.println("Cidade: " + cidade);
        System.out.println("Bairro: " + bairro);
        System.out.println("Rua: " + rua);
        System.out.println("Salário Base: " + salarioBase);

        System.out.print("Histórico de Salários: ");
        if (salarioRecebido != null) {
            for (int i = 0; i < salarioRecebido.length; i++) {
                System.out.print(salarioRecebido[i] + " ");
            }
        }

        System.out.println();
        System.out.println("Média Salarial: " + calcularMedia());
        System.out.println("Bônus: " + calcularBonus());
        System.out.println("--------------------------");
    }
}
