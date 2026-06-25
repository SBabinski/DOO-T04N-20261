package objetos;

public class Vendedor {

    private String nomeVendedor;
    private int idadeVendedor;
    private String lojaVendedor;
    private String cidadeVendedor;
    private Double salarioRecebidoVendedor;

    private float[] salarioVendedor = new float[3];

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public int getIdadeVendedor() {
        return idadeVendedor;
    }

    public void setIdadeVendedor(int idadeVendedor) {
        this.idadeVendedor = idadeVendedor;
    }

    public String getLojaVendedor() {
        return lojaVendedor;
    }

    public void setLojaVendedor(String lojaVendedor) {
        this.lojaVendedor = lojaVendedor;
    }

    public String getCidadeVendedor() {
        return cidadeVendedor;
    }

    public void setCidadeVendedor(String cidadeVendedor) {
        this.cidadeVendedor = cidadeVendedor;
    }

    public Double getSalarioRecebidoVendedor() {
        return salarioRecebidoVendedor;
    }

    public void setSalarioRecebidoVendedor(Double salarioRecebidoVendedor) {
        this.salarioRecebidoVendedor = salarioRecebidoVendedor;
    }

    public float calcularMediaVendedor() {
        float soma = 0;

        for (int i = 0; i < salarioVendedor.length; i++) {
            soma += salarioVendedor[i];
        }

        return soma / salarioVendedor.length;
    }

    public float calcularBonusVendedor() {
        return (float) (salarioRecebidoVendedor * 0.2);
    }

    public String apresentarSe() {
        return "Olá, meu nome é " + nomeVendedor + " e tenho " + idadeVendedor + " anos.";
    }

    public void cadastroDoVendedor() {
        System.out.println("--------------------------------------------");
        System.out.println("NOME: " + nomeVendedor);
        System.out.println("IDADE: " + idadeVendedor);
        System.out.println("LOJA: " + lojaVendedor);
        System.out.println("CIDADE: " + cidadeVendedor);
        System.out.println("SALARIO: " + salarioRecebidoVendedor);
        System.out.println("MEDIA: " + calcularMediaVendedor());
        System.out.println("BONUS: " + calcularBonusVendedor());
        System.out.println("--------------------------------------------");
    }
}