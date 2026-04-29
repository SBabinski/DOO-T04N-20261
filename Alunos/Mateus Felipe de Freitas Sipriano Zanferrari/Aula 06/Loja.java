import java.util.ArrayList;
import java.util.List;

public class Loja {
    
    public String nomeLoja;
    public String razaosocial;
    public String cnpj;
    public String cidade;
    public String bairro;
    //cada loja tem uma lista de clientes e vendedores, para facilitar a contagem. 
    public List<Cliente> clientes = new ArrayList<>();
    public List<Vendedor> vendedores = new ArrayList<>();

    public Loja(String nomeLoja, String razaosocial, String cnpj, String cidade, String bairro) {
        this.nomeLoja = nomeLoja;
        this.razaosocial = razaosocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
    }

    public void apresentarLoja(){
        System.out.println("\n------ Dados da Loja ------");
        System.out.println("Nome Fantasia: " + nomeLoja);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + cidade + ", " + bairro);
        System.out.println("Número de Clientes: " + clientes.size());
        System.out.println("Número de Vendedores: " + vendedores.size());
    }
}
