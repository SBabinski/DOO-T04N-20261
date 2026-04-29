import java.util.Scanner;

public class Calculadora {
    public static Scanner abobora = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha = 0;

        Venda venda = new Venda();
        RegistroVenda meuRegis = new RegistroVenda();

        while (escolha != 5) {
            System.out.println("Escolha uma operaçao ");
            System.out.println("  [1] - Calcular Preço Total");
            System.out.println("  [2] - Calcular Troco");
            System.out.println("  [3] - Registro de Vendas");
            System.out.println("  [4] - Registro de datas");
            System.out.println("  [5] - Sair");

            System.out.print("Qual sua escolha ");
            escolha = abobora.nextInt();

            abobora.nextLine();

            if (escolha == 1) {
                System.out.print("Quantidade do produto  ");
                
            
                float qtd = abobora.nextFloat();
                System.out.print("Digite o preço unitario  ");
                float prcUni = abobora.nextFloat();

                abobora.nextLine();

                Produto produto = new Produto(qtd, prcUni);

                float resultado = venda.calcularPrecoTotal(produto);

                System.out.println("O valor total da compra e " + resultado);

                meuRegis.registrarVenda(resultado);


            }

            if (escolha == 2) {
                System.out.print("Qual o valor de Entrada  ");
                float entrada = abobora.nextFloat();

                System.out.print("Valor da compra: ");
                float resultado = abobora.nextFloat();

                float troco = venda.calcularTroco(resultado, entrada);

                System.out.println("Seu troco e de " + troco);
            }

            if (escolha == 3) {
                System.out.println("--- Histórico de Vendas ---");

                meuRegis.exibirRegistros(venda);

            }

            if (escolha == 4) {
               

                System.out.print("Digite o dia");
                int diaBusca = abobora.nextInt();

                System.out.print("Digite o mês");
                int mesBusca = abobora.nextInt();

               
                float totalDoDia = meuRegis.buscarTotalPorData(diaBusca, mesBusca);

                if (totalDoDia > 0) {
                    System.out.println("O total vendido em " + diaBusca + "/" + mesBusca + " foi: R$ " + totalDoDia);
                } else {
                    System.out.println("Nenhuma venda encontrada para esta data.");
                }
            }

        }

    }

}
