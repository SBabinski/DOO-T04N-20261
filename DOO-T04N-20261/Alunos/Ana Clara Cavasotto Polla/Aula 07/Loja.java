package fag.objetos;

import java.util.ArrayList;
import java.util.List;

public class Loja {

	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private Endereco endereco;
	private List<Vendedor> vendedores;
	private List<Cliente> clientes;

	public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
		this.nomeFantasia = validarTexto(nomeFantasia, "nomeFantasia");
		this.razaoSocial = validarTexto(razaoSocial, "razaoSocial");
		this.cnpj = validarTexto(cnpj, "cnpj");
		this.endereco = validarEndereco(endereco);
		this.vendedores = new ArrayList<>();
		this.clientes = new ArrayList<>();
	}

	// setters

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = validarTexto(nomeFantasia, "nomeFantasia");
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = validarTexto(razaoSocial, "razaoSocial");
	}

	public void setCnpj(String cnpj) {
		this.cnpj = validarTexto(cnpj, "cnpj");
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = validarEndereco(endereco);
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = new ArrayList<>();
		if (vendedores == null) {
			System.out.println("A lista de vendedores deve ser informada.");
			return;
		}
		for (Vendedor vendedor : vendedores) {
			adicionarVendedor(vendedor);
		}
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = new ArrayList<>();
		if (clientes == null) {
			System.out.println("A lista de clientes deve ser informada.");
			return;
		}
		for (Cliente cliente : clientes) {
			adicionarCliente(cliente);
		}
	}

	// getters

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public List<Vendedor> getVendedores() {
		return new ArrayList<>(vendedores);
	}

	public List<Cliente> getClientes() {
		return new ArrayList<>(clientes);
	}

	// metodos

	public void adicionarVendedor(Vendedor vendedor) {
		if (vendedor == null) {
			System.out.println("O vendedor deve ser informado.");
			return;
		}
		if (!vendedores.contains(vendedor)) {
			vendedores.add(vendedor);
		}
	}

	public void adicionarCliente(Cliente cliente) {
		if (cliente == null) {
			System.out.println("O cliente deve ser informado.");
			return;
		}
		if (!clientes.contains(cliente)) {
			clientes.add(cliente);
		}
	}

	public int contarClientes() {
		return clientes.size();
	}

	public int contarVendedores() {
		return vendedores.size();
	}

	public void apresentar() {
		System.out.printf("Loja: %s | CNPJ: %s | ", nomeFantasia, cnpj);
		endereco.apresentarLogradouro();
	}

	private String validarTexto(String valor, String campo) {
		if (valor == null || valor.isBlank()) {
			System.out.println("O campo " + campo + " deve ser preenchido.");
			return "";
		}
		return valor;
	}

	private Endereco validarEndereco(Endereco endereco) {
		if (endereco == null) {
			System.out.println("O endereco da loja deve ser informado.");
			return this.endereco;
		}
		return endereco;
	}
}
