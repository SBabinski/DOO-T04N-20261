import java.util.Scanner;
import java.util.ArrayList;

public class Calculadora {
    public static Scanner abobora = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha = 0;

        Venda venda = new Venda();
        RegistroVenda meuRegis = new RegistroVenda();
        Loja lojinha = Teste.gerarDados(); 

        while (escolha != 11) {
            System.out.println("\n=== MENU MY PLANT ===");
            System.out.println("  [1] - Calcular Preço Total");
            System.out.println("  [2] - Calcular Troco");
            System.out.println("  [3] - Registro de Vendas");
            System.out.println("  [4] - Registro de datas");
            System.out.println("  [5] - Dados da Loja ");
            System.out.println("  [6] - Contar Vendedores e Clientes ");
            System.out.println("  [7] - Listar Clientes ");
            System.out.println("  [8] - Relatorio Vendedores ");
            System.out.println("  [9] - Relatorio Gerentes ");
            System.out.println("  [10] - Criar e Processar Pedido"); 
            System.out.println("  [11] - Sair");

            System.out.print("\nQual sua escolha: ");
            escolha = abobora.nextInt();
            abobora.nextLine();

            if (escolha == 1) {
                System.out.print("Quantidade do produto: ");
                float qtd = abobora.nextFloat();
                System.out.print("Digite o preço unitario: ");
                float prcUni = abobora.nextFloat();
                abobora.nextLine();

                Produto produto = new Produto(qtd, prcUni);
                float resultado = venda.calcularPrecoTotal(produto);
                System.out.println("O valor total da compra e " + resultado);
                meuRegis.registrarVenda(resultado);
            }

            if (escolha == 2) {
                System.out.print("Qual o valor de Entrada: ");
                float entrada = abobora.nextFloat();
                System.out.print("Valor da compra: ");
                float resultado = abobora.nextFloat();
                abobora.nextLine();

                float troco = venda.calcularTroco(resultado, entrada);
                System.out.println("Seu troco e de " + troco);
            }

            if (escolha == 3) {
                System.out.println("--- Histórico de Vendas ---");
               
                meuRegis.exibirRegistros(venda); 
            }

            if (escolha == 4) {
                System.out.print("Digite o dia: ");
                int diaBusca = abobora.nextInt();
                System.out.print("Digite o mês: ");
                int mesBusca = abobora.nextInt();

                float totalDoDia = meuRegis.buscarTotalPorData(diaBusca, mesBusca);

                if (totalDoDia > 0) {
                    System.out.println("O total vendido em " + diaBusca + "/" + mesBusca + " foi: R$ " + totalDoDia);
                } else {
                    System.out.println("Nenhuma venda encontrada para esta data.");
                }
            }

            if (escolha == 5) {              
                lojinha.apresentarSe();
            }

            if (escolha == 6) {              
                lojinha.contarVendedores();
                lojinha.contarClientes();
            }

            if (escolha == 7) { 
                for(Cliente c : lojinha.cliE){
                  
                    c.apresentarse(); 
                    if(c.endereco != null) c.endereco.apresentarLogradouro();
                }
            }

            if (escolha == 8) { 
                for(Vendedor v : lojinha.venD){
                    v.apresentarse();
                    System.out.println("Salarios Recebidos: " + v.salRec);
                    System.out.println("Media Salarial: " + v.calcularMedia());
                    System.out.println("Bonus do Salario: " + v.calcularBonus());
                }
            }

            if (escolha == 9) { 
               
                for(Gerente g : lojinha.gerT){
                    g.apresentarse();
                    System.out.println("Salarios Recebidos: " + g.salRec);
                    System.out.println("Media Salarial: " + g.calcularMedia());
                    System.out.println("Bonus do Salario: " + g.calcularBonus());
                }
            }

            if (escolha == 10) {
                System.out.println("--- Processando Pedido Fake ---");
                ProcessaPedido processador = new ProcessaPedido();
                
                ArrayList<Item> itensPedido = new ArrayList<>();
                itensPedido.add(new Item(1, "Rosa do Deserto", "Planta", 89.90f));
                
            
                Cliente c = lojinha.cliE.get(0);
                Vendedor v = lojinha.venD.get(0);

                Pedido p = processador.processar(1, c, v, lojinha, itensPedido);
                p.gerarDescricaoVenda();
                processador.confirmarPagamento(p);
            }
        }
    }
}