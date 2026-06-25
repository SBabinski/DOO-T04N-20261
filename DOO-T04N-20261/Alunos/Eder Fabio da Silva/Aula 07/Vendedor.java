
public class Vendedor extends Funcionario {
  
    @Override
    public void calcularBonus() {
        double bonus = salarioBase * 0.2;
        System.out.printf("O bonus do vendedor %s é R$ %.2f\n", nome, bonus);
    }
    
}

