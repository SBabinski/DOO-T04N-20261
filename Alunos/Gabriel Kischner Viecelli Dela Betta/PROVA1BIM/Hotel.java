import java.util.Scanner;
import java.util.Date;

public class Hotel {
    public static void main(String[] args) {
        Scanner abobora = new Scanner(System.in);
        
        
        Hospedes[] listaHospedes = new Hospedes[10];
        Quartos[] listaQuartos = new Quartos[10];
        Reserva[] listaReservas = new Reserva[10];

        int contH = 0, contQ = 0, contR = 0;
        int op = 0;

        while (op != 7) {
            System.out.println("\n--- SISTEMA DE GESTAO HOTELEIRA ---");
            System.out.println("1-Hospede, 2-Quarto, 3-Reserva, 4-Checkout, 5-Listar, 6-Demo, 7-Sair");
            System.out.print("Opcao: ");
            op = abobora.nextInt();
            abobora.nextLine(); 

         
            if (op == 1) {
                if (contH < 10) {
                    System.out.print("Nome: "); String n = abobora.nextLine();
                    System.out.print("CPF: "); String c = abobora.nextLine();
                    System.out.print("Fone: "); String t = abobora.nextLine();
                    listaHospedes[contH++] = new Hospedes(n, c, t);
                    System.out.println("Hospede guardado.");
                }
                System.out.println("\n--- Hospedes no Sistema ---");
                for (int i = 0; i < contH; i++) {
                    if (listaHospedes[i] != null) listaHospedes[i].apresentarse();
                }
            }

            if (op == 2) {
                if (contQ < 10) {
                    System.out.print("Tipo (1-Simples, 2-Luxo): "); int tipo = abobora.nextInt();
                    System.out.print("Numero: "); int num = abobora.nextInt();
                    System.out.print("Diaria: "); double vlr = abobora.nextDouble();

                    if (tipo == 1) {
                        listaQuartos[contQ++] = new QuartoSimples(num, vlr, true);
                    } else {
                        listaQuartos[contQ++] = new QuartoLuxo(num, vlr, true);
                    }
                    System.out.println("Quarto guardado.");
                }
                System.out.println("\n--- Quartos no Sistema ---");
                for (int i = 0; i < contQ; i++) {
                    if (listaQuartos[i] != null) listaQuartos[i].exibirInfo();
                }
            }

            
            if (op == 3) {
                if (contH > 0 && contQ > 0 && contR < 10) {
                    for (int i = 0; i < contH; i++) System.out.println(i + " - " + listaHospedes[i].nome);
                    System.out.print("Indice do Hospede: "); int idH = abobora.nextInt();

                    for (int i = 0; i < contQ; i++) System.out.println(i + " - Quarto " + listaQuartos[i].numQuarto);
                    System.out.print("Indice do Quarto: "); int idQ = abobora.nextInt();

                    System.out.print("Numero de Diarias: "); int d = abobora.nextInt();

                    Date in = new Date();
                    Date out = new Date(in.getTime() + (d * 86400000L));

                    listaReservas[contR++] = new Reserva(listaHospedes[idH], listaQuartos[idQ], in, out);
                    System.out.println("Reserva realizada com sucesso!");
                } else {
                    System.out.println("Erro: ");
                }
            }

          
            if (op == 4) {
                System.out.print("Numero do quarto para Checkout: ");
                int busca = abobora.nextInt();
                boolean achou = false;
                for (int i = 0; i < contR; i++) {
                    if (listaReservas[i] != null && listaReservas[i].quartos.numQuarto == busca && !listaReservas[i].checkOutRealizado) {
                        listaReservas[i].checkOutRealizado = true;
                        System.out.println("Hospede: " + listaReservas[i].hospedes.nome);
                        System.out.println("Total Pago: R$ " + listaReservas[i].calcularValorTotal());
                        achou = true;
                        break;
                    }
                }
                if (!achou) System.out.println("Nenhuma reserva ativa encontrada para este quarto.");
            }

           
            if (op == 5) {
                System.out.println("\n--- RELATORIO DE RESERVAS ---");
                for (int i = 0; i < contR; i++) {
                    if (listaReservas[i] != null) {
                        listaReservas[i].exibirReserva();
                        String status = listaReservas[i].checkOutRealizado ? "FINALIZADA" : "ATIVA";
                        System.out.println("Status: " + status);
                      
                    }
                }
            }

            
            if (op == 6) {
              
                contR = GerarDados.carregar(listaHospedes, listaQuartos, listaReservas, contR);
               
                contH = contR;
                contQ = contR;
                System.out.println("Dados de demonstracao adicionados. Total de registros: " + contR);
            }
        }
        abobora.close();
    }
}