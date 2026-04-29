import java.util.ArrayList;

public class Vendedor {
    String nomeVendedor;
    int idadeVendedor;
    String nomeLoja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;
    ArrayList<Double> listaSalarios;

    public Vendedor(String nomeVendedor, int idadeVendedor, String nomeLoja,
                    String cidade, String bairro, String rua, double salarioBase) {

        this.nomeVendedor = nomeVendedor;
        this.idadeVendedor = idadeVendedor;
        this.nomeLoja = nomeLoja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;

        listaSalarios = new ArrayList<>();

        listaSalarios.add(2000.0);
        listaSalarios.add(2200.0);
        listaSalarios.add(2100.0);
    }

    public void mostrarDados() {
        System.out.println("Vendedor: " + nomeVendedor);
        System.out.println("Idade: " + idadeVendedor);
        System.out.println("Loja: " + nomeLoja);
    }

    public double mediaSalarios() {
        double total = 0;

        for (int i = 0; i < listaSalarios.size(); i++) {
            total += listaSalarios.get(i);
        }

        return total / listaSalarios.size();
    }

    public double bonus() {
        double valorBonus = salarioBase * 0.2;
        return valorBonus;
    }
}