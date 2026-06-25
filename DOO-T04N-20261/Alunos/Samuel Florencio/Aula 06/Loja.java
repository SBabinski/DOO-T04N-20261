import java.util.ArrayList;

public class Loja {
	String nome;
	String razaoSocial;
	String cnpj;
	String cidade;
	String bairro;
	String rua;
	
	ArrayList<Vendedor> vendedores = new ArrayList<>();
	ArrayList<Cliente> clientes = new ArrayList<>();

	public void contarCliente() {
		System.out.println("Total de clientes é: " + clientes.size());
	}
	public void contarVendedor() {
		System.out.println("Total de vendedores é: " + vendedores.size()+"\n");
	}
	public void apresentar() {
		System.out.println("Loja: "+ nome);
		System.out.println("CNPJ: "+ cnpj);
		System.out.println("Cidade: "+cidade+",  Bairro: "+bairro+",  Rua: "+rua+".\n");
	}
	public void infoVendedor(String nome) {
		boolean encontrado = false;
		
		for ( Vendedor v : vendedores) {
			if(v.nome.equalsIgnoreCase(nome)) {
				v.apresentar();
				System.out.println("Salario recebido: "+v.atualizarSalario(true));
				System.out.println("Média: " + v.calcularMedia());
				System.out.println("Bonus: \n" + v.calcularBonus());
				encontrado = true;
				break;

			}
		}
		if (!encontrado) {
			System.out.println("Vendedor não encontrado");
		}
	}
	public void infoCliente(String nome) {
		boolean encontrado = false;
		
		for (Cliente c : clientes) {
			if(c.nome.equalsIgnoreCase(nome)) {
				c.apresentar();
				encontrado = true;
				break;
			}
		}
		if(!encontrado) {
			System.out.println("Cliente não encontrado");
			}	
	}
	public void listarVendedor(){
		System.out.println("Vendedores disponiveis: ");
		for ( Vendedor v : vendedores) {
			System.out.println("- " + v.nome);
		}
	}
	public void listarCliente () {
		System.out.println("Clientes disponiveis: ");
		for (Cliente c : clientes) {
			System.out.println("- " + c.nome);
		}
	}
	public void addCliente(Cliente c) {
		clientes.add(c);
	}
	public void addVendedor(Vendedor v) {
		vendedores.add(v);
	}
}
