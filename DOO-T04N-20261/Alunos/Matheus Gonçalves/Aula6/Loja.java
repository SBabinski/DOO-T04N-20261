package fag;

import java.util.ArrayList;

public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Cliente> clientes;

    public Loja(){
        vendedores = new ArrayList<>();
        clientes = new ArrayList<>();
    }
    
    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedores = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public String getNomeFantasia() { 
    	return nomeFantasia; 
    }
    public void setNomeFantasia(String nomeFantasia) { 
    	if (!nomeFantasia.isBlank())
    		this.nomeFantasia = nomeFantasia; 
    }

    public String getRazaoSocial() { 
    	return razaoSocial; 
    }
    public void setRazaoSocial(String razaoSocial) { 
    	if (!nomeFantasia.isBlank())
    		this.razaoSocial = razaoSocial; 
    }

    public String getCnpj() { 
    	return cnpj; 
    }
    public void setCnpj(String cnpj) { 
    	if (!nomeFantasia.isBlank())
    		this.cnpj = cnpj;
    }
    
    public String getCidade() { 
    	return cidade; 
    }
    public void setCidade(String cidade) { 
    	if (!cidade.isBlank())
    		this.cidade = cidade;
    }
    
    public String getBairro() { 
    	return bairro; 
    }
    public void setBairro(String bairro) { 
    	if (!bairro.isBlank())
    		this.bairro = bairro;
    }
    
    public String getRua() { 
    	return rua; 
    }
    public void setRua(String rua) { 
    	if (!rua.isBlank())
    		this.rua = rua;
    }

    public ArrayList<Vendedor> getVendedores() { 
    	return vendedores; 
    }
    public ArrayList<Cliente> getClientes() { 
    	return clientes; 
    }
    
    public void adicionarVendedor(Vendedor vendedor) { 
    	vendedores.add(vendedor); 
    }
    public void adicionarCliente(Cliente cliente) { 
    	clientes.add(cliente); 
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
    }

    public int contarClientes() {
        int quantidade = clientes.size();
        System.out.println("Quantidade de clientes: " + quantidade);
        return quantidade;
    }

    public int contarVendedores() {
        int quantidade = vendedores.size();
        System.out.println("Quantidade de vendedores: " + quantidade);
        return quantidade;
    }

}