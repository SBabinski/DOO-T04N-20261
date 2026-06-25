class Vendedor {
    
    String nome;
    int idade;
    String loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;
    double [] salarioRecebido = {1500, 1600, 1700};

    void apresentarSe(){
        System.out.println("nome:" +nome);
        System.out.println("idade" +idade);
        System.out.println("loja:" +loja);
    }

    double calcularMedia() {
        double soma = 0;
        for (double salario: salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.length;
    }
    double calcularBonus(){
        return salarioBase * 0.2;
    }

}
