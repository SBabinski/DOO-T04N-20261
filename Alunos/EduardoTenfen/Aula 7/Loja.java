package Aula2;

import java.util.ArrayList;

public class Loja {

	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private Endereco endereco;

	// --- LISTAS ---
	// Repare que aqui só deixamos o ArrayList.
	private ArrayList<Vendedor> listaVendedores = new ArrayList<>();
	private ArrayList<Cliente> listaCliente = new ArrayList<>();

	// --- CONSTRUTOR ---
	public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
		setNomeFantasia(nomeFantasia);
		setRazaoSocial(razaoSocial);
		setCnpj(cnpj);
		setEndereco(endereco);
	}

	public void contarClientes() {
		System.out.println("A loja " + getNomeFantasia() + " possui " + listaCliente.size() + " clientes cadastrados.");
	}

	public void contarVendedores() {

		System.out.println(
				"A loja " + getNomeFantasia() + " possui " + listaVendedores.size() + " vendedores cadastrados.");
	}

	public void mostrarVendedoresLoja() {
		System.out.println("--- Vendedores da loja " + getNomeFantasia() + "---");
		System.out.println();
		if (listaVendedores.isEmpty()) {
			System.out.println("A lista está vazia.");
		} else {
			for (Vendedor vendedores : listaVendedores) {
				vendedores.apresentarse();
			}
		}
	}

	public void mostrarClientes() {
		System.out.println("--- Mostar Clientes da loja " + getNomeFantasia() + "---");
		System.out.println();
		if (listaCliente.isEmpty()) {
			System.out.println("A lista está vazia.");
		} else {
			for (Cliente cliente : listaCliente) {
				cliente.apresentarse();
			}
		}
	}

	public void apresentarse() {
		System.out.println("---- Identificação da Loja ----");
		System.out.println("Nome Fantasia: " + getNomeFantasia());
		System.out.println("CNPJ: " + getCnpj());
		System.out.println("Endereço: " + getEndereco());
		System.out.println("-------------------------------");
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

	// Getters e Setters das novas listas (ArrayList)

	public ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}

	public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}

	public ArrayList<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(ArrayList<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
