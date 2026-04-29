public class Funcionario extends Pessoa {

    protected float salarioBase;
    protected float[] salarioRecebido;

    // SALARIO BASE
    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }

    // SALARIO RECEBIDO
    public float[] getSalarioRecebido() {
        return salarioRecebido;
    }

    public void setSalarioRecebido(float[] salarioRecebido) {
        this.salarioRecebido = salarioRecebido;
    }

    // METODOS
    // APRESENTAÇÃO DO FUNCIONARIO
    public String apresentarSe() {
        return "Olá, meu nome é " + nome + " e eu tenho " + idade + " anos! Eu trabalho na loja" + loja;
    }

    // CALCULA MEDIA SALARIAL BASEADO NOS REGISTROS DE SALARIO RECEBIDO
    public float calcularMedia() {
        float somaSalariosRecebidos = 0;

        for (int i = 0; i < salarioRecebido.length; i++) {
            somaSalariosRecebidos += salarioRecebido[i];
        }

        float mediaSalarial = somaSalariosRecebidos / salarioRecebido.length;

        return mediaSalarial;
    }

    // CALCULA BONUS BASE (PODE SER SOBRESCRITO PELAS SUBCLASSES)
    public float calcularBonus() {
        return 0.0f; // Implementação base: sem bônus
    }
}
