import java.util.ArrayList;

public class Loja {
    String nomeFantasia;
    String razaoSoc;
    String cnpj;
    String cidade;
    String bairro;
    String rua;
    String loja;
    float salBase;
    public ArrayList<Vendedor> venD = new ArrayList<>();
    public ArrayList<Cliente> cliE = new ArrayList<>();
    public ArrayList<Gerente> gerT = new ArrayList<>();

    Loja(String nomeFantasia, String razaoSoc , String cnpj, String cidade, String bairro, String rua, String loja, float salBase) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSoc = razaoSoc;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.loja = loja;
        this.salBase = salBase;

    }


     public void apresentarSe(){
        System.out.println("O nome da Loja e " + nomeFantasia + " \ncnpj:   " + cnpj + " \nendereco  "+ cidade);
    }

    public void contarClientes(){
        System.out.println("A quantidade de clientes é " + cliE.size());
    }

     public void contarVendedores(){
        System.out.println("A quantidade de vendedores é " + venD.size());
    }




}
