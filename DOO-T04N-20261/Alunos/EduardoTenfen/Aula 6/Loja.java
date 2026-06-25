package Aula2;

import java.util.ArrayList;

public class Loja {
	// Atributos de Identificacao
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;

	// Endereco
	private String cidade;
	private String bairro;
	private String rua;

	// listas
	private ArrayList<Vendedor> listaVendedores = new ArrayList<Vendedor>();
	private ArrayList<Cliente> listaCliente = new ArrayList<>();

	public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
		setNomeFantasia(nomeFantasia);
		setRazaoSocial(razaoSocial);
		setCnpj(cnpj);
		setCidade(cidade);
		setBairro(bairro);
		setRua(rua);

	}

	public void mostrarVendedoresLoja() {
		System.out.println("--- Vendedroes da loja " + getNomeFantasia() + "---");
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
				cliente.apresentarseCliente();
			}
		}
	}

	public void apresentarse() {
		System.out.println("---- Identificação da Loja ----");
		System.out.println("Nome Fantasia: " + getNomeFantasia());
		System.out.println("CNPJ: " + getCnpj());
		System.out.println("Endereço: " + getRua() + ", " + getBairro() + " - " + getCidade());
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

	public ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}

	public ArrayList<Cliente> getListaCliente() {
		return this.listaCliente;
	}

	public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}

	public void contarClientes() {
		System.out.println("A loja " + getNomeFantasia() + " possui " + listaCliente.size() + " clientes cadastrados.");
	}

	public void contarVendedores() {
		System.out.println(
				"A loja " + getNomeFantasia() + " possui " + listaVendedores.size() + " vendedores cadastrados.");
	}

}
