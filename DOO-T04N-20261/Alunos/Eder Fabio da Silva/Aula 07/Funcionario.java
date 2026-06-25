public abstract class Funcionario extends Pessoa {
       
        String loja;
      
        double salarioBase;
        double salarioRecebido[] = {2500, 3000, 3500};

        //Metodo comum para todos   

        public void exibirDados() {  
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
            System.out.println("Loja: " + loja);
            if (endereco != null) {
                endereco.apresentarLogradouro();
            } else {
                System.out.println("Endereço não cadastrado.");
            }
            System.out.println("-----------------------------");
        }

        public double calcularMedia() {
            double soma = 0;
            for (double s : salarioRecebido) {
                soma += s;
            }
            return soma / salarioRecebido.length;
        }

        //metodo abstrato para calcular bonus, que será implementado nas subclasses
        public abstract void calcularBonus();
            
}
