public class Cliente {
	String nome;
	int idade;
	String cidade;
	String bairro;
	String rua;
	public void apresentar() {
		System.out.println("Cliente: "+ nome);
		System.out.println("Idade: "+ idade);
		System.out.println("Cidade: "+cidade+",  Bairro: "+bairro+",  Rua: "+rua+".\n");
	}
}
