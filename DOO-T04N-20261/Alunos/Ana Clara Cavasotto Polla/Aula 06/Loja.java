package fag.objetos;
import fag.objetos.Endereco;

public class Loja {

	private static final Vendedor[] VENDEDORES_VAZIOS = new Vendedor[0];
	private static final Cliente[] CLIENTES_VAZIOS = new Cliente[0];

	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private Endereco endereco;
	private Vendedor[] vendedores;
	private Cliente[] clientes;

	public Loja() {
		this.vendedores = VENDEDORES_VAZIOS;
		this.clientes = CLIENTES_VAZIOS;
	}

	public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco, Vendedor[] vendedores,
			Cliente[] clientes) {
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.vendedores = vendedores != null ? vendedores : VENDEDORES_VAZIOS;
		this.clientes = clientes != null ? clientes : CLIENTES_VAZIOS;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		if (nomeFantasia != null && !nomeFantasia.isBlank()) {
			this.nomeFantasia = nomeFantasia;
		}
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		if (razaoSocial != null && !razaoSocial.isBlank()) {
			this.razaoSocial = razaoSocial;
		}
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if (cnpj != null && !cnpj.isBlank()) {
			this.cnpj = cnpj;
		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		if (endereco != null) {
			this.endereco = endereco;
		}
	}

	public Vendedor[] getVendedores() {
		return vendedores;
	}

	public void setVendedores(Vendedor[] vendedores) {
		this.vendedores = vendedores != null ? vendedores : VENDEDORES_VAZIOS;
	}

	public Cliente[] getClientes() {
		return clientes;
	}

	public void setClientes(Cliente[] clientes) {
		this.clientes = clientes != null ? clientes : CLIENTES_VAZIOS;
	}

	public int contarClientes() {
		return clientes.length;
	}

	public int contarVendedores() {
		return vendedores.length;
	}

	public void apresentarSe() {
		System.out.printf("Loja: %s | CNPJ: %s\n | Endereço:", nomeFantasia, cnpj);
		endereco.imprimirEndereco();
	}
}
