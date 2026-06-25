//calculadora plantas da gabriela v2
import java.util.Scanner;
import java.util.ArrayList;

public class CalculadoraPlantas {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> lstqntd = new ArrayList();
        ArrayList<Double> lsttotal = new ArrayList();
        ArrayList<Double> lstdscnt = new ArrayList();

        int opcao;

        do {
            System.out.println("Escolha uma opção");
            System.out.println("CALCULADORA DA DONA GABRIELA");
            System.out.println("[1] Calcular total");
            System.out.println("[2] Calcular troco");
            System.out.println("[3] Ver registro de vendas");
            System.out.println("[4] Sair");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Insira a quantidade de plantas: ");
                    int qntd = sc.nextInt();
                    System.out.println("Insira o valor da unidade: ");
                    double prcouni = sc.nextDouble();
                    double ttl = qntd*prcouni;
                    double desc = 0;
                    if(qntd > 10){
                        desc = ttl*0.05;
                        ttl = ttl -desc;
                        System.out.println("Desconto especial aplicado: R$ " + desc);
                    }
                    System.out.println("Preço total da compra: R$ "+ ttl);
                    lstdscnt.add(desc);
                    lsttotal.add(ttl);
                    lstqntd.add(qntd);
                    break;

                case 2:
                    System.out.println("Valor pago: ");
                    double recebido = sc.nextDouble();
                    System.out.println("Insira valor da compra: ");
                    double vlrcmpr = sc.nextDouble();
                    double trc = recebido - vlrcmpr;
                    if (trc< 0){
                        System.out.println("Valor insuficiente! Faltam R$ " + (-trc));
                    } else {
                        System.out.println("Troco a ser devolvido: R$ " + trc);
                    }
                    break;
                case 3:
                    System.out.println("Registro:");
                    if(lstqntd.isEmpty()){
                        System.out.println ("nenhuma venda até agora... ");
                    } else {
                        int cont = 1;
                        for (Integer q : lstqntd){
                        double total = lsttotal.get(cont-1);
                        double desconto = lstdscnt.get(cont-1);
                        System.out.println("Venda " + cont +
                            " | Quantidade: " + q +
                            " | Total: R$ " + total +
                            " | Desconto: R$ " + desconto);
                        cont++;


                        }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Obrigado por utilizar! Até logo! ");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente!");
            }
        }while (opcao != 4);
        sc.close();
    }