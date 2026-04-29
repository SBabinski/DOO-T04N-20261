
public class Loja {

	private String nome;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String cidade;
	private String bairro;
	private String rua;
	private Vendedor[] vendedores;
	private Cliente[] clientes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Vendedor[] getVendedores() {
		return vendedores;
	}

	public void setVendedores(Vendedor[] vendedores) {
		this.vendedores = vendedores;
	}

	public Cliente[] getClientes() {
		return clientes;
	}

	public void setClientes(Cliente[] clientes) {
		this.clientes = clientes;
	}

	public int contarClientes() {
		return clientes.length;
	}

	public int contarVendedores() {
		return vendedores.length;
	}

	public String apresentarSe() {
		return "Olá, somos a " + nomeFantasia + ", nosso CNPJ é " + cnpj + ", ficamos na " + rua + ", " + bairro + ", "
				+ cidade;
	}

	public void listarLoja() {
		System.out.println("Nome Fantasia: " + nomeFantasia);
		System.out.println("Razão Social: " + razaoSocial);
		System.out.println("CNPJ: " + cnpj);
		System.out.println("Cidade: " + cidade);
		System.out.println("Bairro: " + bairro);
		System.out.println("Rua: " + rua);
		System.out.println("Quantidade de Clientes: " + contarClientes());
		System.out.println("Quantidade de Vendedores: " + contarVendedores());

		System.out.println("VENDEDORES");
		if (vendedores != null) {
			for (int i = 0; i < vendedores.length; i++) {
				if (vendedores[i] != null) {
					vendedores[i].listarVendedor();
				}
			}
		}

		System.out.println("CLIENTES");
		if (clientes != null) {
			for (int i = 0; i < clientes.length; i++) {
				if (clientes[i] != null) {
					clientes[i].listarCliente();
				}
			}
		}
	}
}