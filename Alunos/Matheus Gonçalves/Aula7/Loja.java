package fag;

import java.util.ArrayList;

public class Loja{
    private String nomeFantasia;
    private String razaoSocial;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Cliente> clientes;
    private String cnpj;
    private Endereco endereco;

    public Loja(){
        vendedores = new ArrayList<>();
        clientes = new ArrayList<>();
    }
    
    public Loja(String nomeFantasia, String razaoSocial, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.vendedores = new ArrayList<>();
        this.clientes = new ArrayList<>();
        setEndereco(endereco);
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

    public ArrayList<Vendedor> getVendedores() { 
    	return vendedores; 
    }
    public ArrayList<Cliente> getClientes() { 
    	return clientes; 
    }
    
    public String getCnpj() { 
    	return cnpj; 
    }
    public void setCnpj(String cnpj) { 
    	if (!cnpj.isBlank())
    		this.cnpj = cnpj; 
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        if (endereco == null) {
            System.out.println("Endereço inválido!");
        } else {
            this.endereco = endereco;
        }
    }
    
    public void adicionarVendedor(Vendedor vendedor) { 
    	vendedores.add(vendedor); 
    }
    public void adicionarCliente(Cliente cliente) { 
    	clientes.add(cliente); 
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        endereco.apresentarLogradouro();
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