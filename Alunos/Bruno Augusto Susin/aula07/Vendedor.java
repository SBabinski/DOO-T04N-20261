public class Vendedor extends Funcionario {

	public float calcularBonus() {
		float bonus = salarioBase * 0.2f;

		return bonus;
	}

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