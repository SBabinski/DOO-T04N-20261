public class Gerente extends Pessoa {

    String loja;
    double salarioBase;
    double[] salarios = {5000, 5200, 5100};

    public Gerente(String n, int i, String l, Endereco e, double s) {
        super(n, i, e);
        loja = l;
        salarioBase = s;
    }

    public void apresentarSe() {
        System.out.println(nome + " " + idade + " " + loja);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double s : salarios) soma += s;
        return soma / salarios.length;
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}