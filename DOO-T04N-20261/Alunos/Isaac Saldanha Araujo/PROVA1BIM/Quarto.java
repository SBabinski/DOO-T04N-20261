public abstract class Quarto {
 protected int numero;
 protected double valorDiarias;
 
 public Quarto(int numero, double valorDiarias){
    this.numero = numero;
    this.valorDiarias = valorDiarias;
 }
 public double getValorDiarias() {
    return valorDiarias;
 }

 public abstract void exibirInfo();

}
