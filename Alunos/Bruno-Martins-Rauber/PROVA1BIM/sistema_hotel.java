package principal;

import java.util.Scanner;
import principal.classes.*;

public class sistema_hotel {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Check_in[] lista = new Check_in[10];
        int contador = 0;
        int OP = -1;

        while (OP != 0) {
            System.out.println("\n--- MENU HOTEL ---");
            System.out.println("1. Cadastrar Check-in");
            System.out.println("2. Listar Reservas Ativas");
            System.out.println("3. Realizar Check-out");
            System.out.println("4. EXECUTAR DEMONSTRACAO");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            
            OP = teclado.nextInt();
            teclado.nextLine();

            switch (OP) {
                case 1:
                    if (contador >= 10) {
                        System.out.println("Erro: Lotado!");
                        break;
                    }
                    System.out.print("Nome: ");
                    String nomeH = teclado.nextLine();
                    System.out.print("CPF: ");
                    String cpfH = teclado.nextLine();
                    System.out.print("Tel: ");
                    String telH = teclado.nextLine();
                    
                 
                    Hospede h = new Hospede(nomeH, cpfH, telH);

                    System.out.print("Num Quarto: ");
                    int numQ = teclado.nextInt();
                    System.out.print("Valor Diaria: ");
                    double vQ = teclado.nextDouble();
                    System.out.print("Tipo: (1) Luxo (2) Simples: ");
                    int tipo = teclado.nextInt();
                    
                    quarto q;
                    if (tipo == 1) {
                        System.out.print("Tem varanda? (1-S / 2-N): ");
                        q = new quarto_luxo(vQ, numQ, teclado.nextInt() == 1);
                    } else {
                        System.out.print("Tem ventilador? (1-S / 2-N): ");
                        q = new quarto_pobre(vQ, numQ, teclado.nextInt() == 1);
                    }

                    System.out.print("Dias: ");
                    int dias = teclado.nextInt();
                    lista[contador] = new Check_in(h, q, dias);
                    contador++;
                    System.out.println("Check-in realizado!");
                    break;

                case 2:
                    System.out.println("\n--- RESERVAS ATIVAS (SEM CHECK-OUT) ---");
                    for (int i = 0; i < contador; i++) {
                        if (!lista[i].devolvido) {
                            lista[i].exibirCu();
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- LISTA PARA CHECK-OUT ---");
                    for (int i = 0; i < contador; i++) {
                        if (!lista[i].devolvido) {
                           
                            System.out.println("[" + i + "] " + lista[i].hospede.Nome);
                        }
                    }
                    System.out.print("Informe o indice: ");
                    int idx = teclado.nextInt();
                    if (idx >= 0 && idx < contador) {
                        lista[idx].devolvido = true;
                        System.out.println("Check-out realizado com sucesso!");
                    } else {
                        System.out.println("Indice invalido!");
                    }
                    break;

                case 4:
                    System.out.println("\n*** INICIANDO DEMONSTRACAO ***");
                    
                    Hospede h1 = new Hospede("Joao Silva", "111.222.333-67", "9999-0000");
                    Hospede h2 = new Hospede("Maria Oliveira", "555.666.677-67", "6767-6767");

                    quarto qSimples = new quarto_pobre(100.0, 101, true);
                    quarto qLuxo = new quarto_luxo(350.0, 202, true);

                   
                    lista[contador++] = new Check_in(h1, qSimples, 3);
                    lista[contador++] = new Check_in(h2, qLuxo, 5);

                    
                    lista[contador - 2].devolvido = true;

                    System.out.println("Demonstracao concluida!");
                    
                    System.out.println("\n=== RESERVA INATIVA (CHECK-OUT REALIZADO) ===");
                    for (int i = 0; i < contador; i++) {
                        if (lista[i].devolvido) {
                            lista[i].exibirCu();
                        }
                    }

                    System.out.println("\n=== RESERVA ATIVA (OCUPADO) ===");
                    for (int i = 0; i < contador; i++) {
                        if (!lista[i].devolvido) {
                            lista[i].exibirCu();
                        }
                    }
                    break;
                    
                case 0:
                    System.out.println("Saindo...");
                    break;
            }
        }
        teclado.close();
    }
}