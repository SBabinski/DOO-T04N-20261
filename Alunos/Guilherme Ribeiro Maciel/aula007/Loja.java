package calculadora;

import java.util.ArrayList;

public class Loja {
	String fantasia;
	String razaoSocial;
	String cnpj;
	String cidade;
	String bairro;
	String rua;
	ArrayList<String> vendedores = new ArrayList<>();
	ArrayList<String> clientes = new ArrayList<>();
	
	public Loja() {
		
	}
	
	public Loja(String fantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
		setFantasia(fantasia);
		setRazaoSocial(razaoSocial);
		setCnpj(cnpj);
		setCidade(cidade);
		setBairro(bairro);
		setRua(rua);
	}

	public ArrayList<String> getVendedores() {
		return vendedores;
	}

	public void setVendedores(String vendedores) {
		this.vendedores.add(vendedores);
	}

	public ArrayList<String> getClientes() {
		return clientes;
	}

	public void setClientes(String clientes) {
		this.clientes.add(clientes);
	}

	public String getFantasia() {
		return fantasia;
	}
	
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
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
	
	public int contarClientes() {
		return clientes.size();
	}
	
	public int contarVendedores() {
		return vendedores.size();
	}
	
	public String apresentarse() {
		return "Nome Fantasia: " + fantasia + "|| CNPJ: " + cnpj + "|| Endereço: " + cidade + ", " + bairro + ", " + rua;
	}
	
}