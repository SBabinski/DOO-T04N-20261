public class Funcionario extends Pessoa {

    protected float salarioBase;
    protected float[] salarioRecebido;

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }

    public float[] getSalarioRecebido() {
        return salarioRecebido;
    }

    public void setSalarioRecebido(float[] salarioRecebido) {
        this.salarioRecebido = salarioRecebido;
    }

    public String apresentarSe() {
        return "Olá, meu nome é " + nome + " e eu tenho " + idade + " anos! Eu trabalho na loja" + loja;
    }

    public float calcularMedia() {
        float somaSalariosRecebidos = 0;

        for (int i = 0; i < salarioRecebido.length; i++) {
            somaSalariosRecebidos += salarioRecebido[i];
        }

        float mediaSalarial = somaSalariosRecebidos / salarioRecebido.length;

        return mediaSalarial;
    }

    public float calcularBonus() {
        return 0.0f;
    }
}