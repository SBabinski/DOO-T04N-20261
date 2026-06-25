public class Cliente extends Pessoa {

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
