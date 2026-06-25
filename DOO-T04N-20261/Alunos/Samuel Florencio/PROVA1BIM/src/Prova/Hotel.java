package Prova;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Hotel  {
	static Scanner hotelmagic = new Scanner (System.in);
	static ArrayList<Hospede> hospedes = new ArrayList<>();
	static ArrayList<Reserva> reservas = new ArrayList<>();
	public static void main(String[] args) {
		int escolha = 0;
		do {
			System.out.println("Seja Bem Vindo ao Hotel Magic \nAbaixo está suas opções para gerenciamento do hotel, escolha uma delas.");
			System.out.println("Digite [1] para cadastrar um hospede.\n");
			System.out.println("Digite [2] para cadastrar quartos.\n");
			System.out.println("Digite [3] para cadastrar uma reserva.\n");
			System.out.println("Digite [4] para realizar o check-out.\n");
			System.out.println("Digite [5] para mostrar os quartos disponiveis.\n");
			System.out.println("Digite [6] para mostrar os hospedes atuais.\n");
			System.out.println("Digite [7] para mostrar as reservas ativas.\n");
			System.out.println("Digite [8] para remover hospede.\n");
			System.out.println("Digite [9] para remover reservas. \n");
			System.out.println("Digite [10] pra ver as informações do hotel.\n");
			System.out.println("Digite [0] para sair do sistema.\n");
			escolha = hotelmagic.nextInt();
			hotelmagic.nextLine();
			opcao(escolha);
		}while(escolha!=0);
	}
	public static void opcao(int escolha) {
		switch(escolha) {
		case 1:
			criarhospede();
			break;
		case 2:
		    criarquarto();
			break;
		case 3:
			criarreserva();
			break;
		case 4:
			fazercheck();
			break;
		case 5:
			mostrarquarto();
			break;
		case 6:
			mostrarhospedes();
			break;
		case 7:
			mostrarreserva();
			break;
		case 8:
			remhospede();
			break;
		case 9:
			remreserva();
			break;
		case 10:
			infohotel();
			break;
		case 0:
			System.out.println("Sistema encerrado! \nObrigado por utilizar o sistema, até a próxima!");
			break;
		default:
			System.out.println("Informe uma opção válida");
			break;
		}
	}
	public static void criarhospede() {
	Hospede newhospede = new Hospede();
	System.out.println("Digite o nome do Hospede.");
	newhospede.nome = hotelmagic.nextLine();
	System.out.println("Digite o cpf do Hospede.");
	newhospede.cpf = hotelmagic.nextLine();
	System.out.println("Digite o telefone do Hospede");
	newhospede.telefone = hotelmagic.nextLine();
	hospedes.add(newhospede);
	System.out.println("Hospede Cadastrado com sucesso!");
	}
	public static void criarquarto() {
		int qual;
		System.out.println("Escolha o quarto que quer cadastrar");
		System.out.println("Digite [1] para cadastrar quarto simples.\n");
		System.out.println("Digite [2] para cadastrar um quarto de luxo.\n");
		System.out.println("Digite [0] para sair.");
		qual = hotelmagic.nextInt();
		hotelmagic.nextLine();
		switch(qual) {
		case 1:
			Quarto.cadastrar();
			break;
		case 2:
			Quartoluxo.cadastrar();
			break;
		case 0:
			System.out.println("Sistema encerrado! \nObrigado por utilizar o sistema, até a próxima!");
			break;
		default:
			System.out.println("ERRO.\nEscolha uma opção verdadeira.");
			break;
		}
	}
	public static void criarreserva() {
		if (hospedes.isEmpty()) {
	        System.out.println("Erro: Cadastre um hóspede primeiro!");
	        return;
	    }    
		DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    Reserva r = new Reserva();
	    System.out.println("Digite o nome do hóspede para a reserva:");
	    r.nomeHospede = hotelmagic.nextLine();    
	    System.out.println("Digite o número do quarto:");
	    r.numeroQuarto = hotelmagic.nextInt();
	    hotelmagic.nextLine();    
	    System.out.println("Digite a data de entrada (DD-MM-AAAA1):");
	    r.dataEntrada = LocalDate.parse(hotelmagic.nextLine(), formatar); 
	    System.out.println("Digite a data de saída (DD-MM-AAAA):");
	    r.dataSaida = LocalDate.parse(hotelmagic.nextLine(),formatar);    
	    reservas.add(r);
	    System.out.println("Reserva realizada com sucesso!");
	}
	public static void fazercheck() {
    System.out.println("Digite o nome do hóspede para check-out:");
    String nome = hotelmagic.nextLine();
    boolean encontrou = reservas.removeIf(r -> r.nomeHospede.equalsIgnoreCase(nome));
    if (encontrou) {
        System.out.println("Check-out realizado. Quarto liberado e reserva encerrada.");
    } else {
        System.out.println("Reserva não encontrada para este nome.");
    }
	}
	public static void mostrarquarto() {
		int qual;
		System.out.println("Escolha o quarto que quer cadastrar");
		System.out.println("Digite [1] para mostrar os quartos de luxo.\n");
		System.out.println("Digite [2] para mostrar os quartos simples.\n");
		System.out.println("Digite [0] para sair.");
		qual = hotelmagic.nextInt();
		hotelmagic.nextLine();
		switch(qual) {
		case 1:
			Quartoluxo.listarQuartos();
			break;
		case 2:
			Quarto.listarQuartos();
			break;
		case 0:
			System.out.println("Sistema encerrado! \nObrigado por utilizar o sistema, até a próxima!");
			break;
		default:
			System.out.println("ERRO.\nEscolha uma opção verdadeira.");
			break;
		}
	}
	public static void mostrarhospedes() {
		  for (Hospede h : hospedes) {
		      System.out.println("Nome: " + h.nome);
		      System.out.println("CPF: " + h.cpf);
		      System.out.println("Telefone: " + h.telefone);
		      System.out.println("----------------------");
		  }
	}
	public static void mostrarreserva() {
	    if (reservas.isEmpty()) {
	        System.out.println("Não há reservas cadastradas.");
	        return;
	    }
	    for (Reserva r : reservas) {
	        System.out.println("Hospede: " + r.nomeHospede);
	        System.out.println("Quarto: " + r.numeroQuarto);
	        System.out.println("----------------------");
	    }
	}
	public static void remhospede() {
	    System.out.println("Digite o nome do hospede que deseja remover:");
	    String nome = hotelmagic.nextLine();
	    boolean removido = false;
	    for (int i = 0; i < hospedes.size(); i++) {
	        if (hospedes.get(i).nome.equalsIgnoreCase(nome)) {
	            hospedes.remove(i);
	            removido = true;
	            System.out.println("Hospede removido com sucesso.");
	            break;
	        }
	    }
	    if (!removido) {
	        System.out.println("Hospede não encontrado.");
	    }
	}
	public static void remreserva() {
	    System.out.println("Digite o nome do hospede da reserva que deseja remover:");
	    String nome = hotelmagic.nextLine();
	    boolean removido = false;
	    for (int i = 0; i < reservas.size(); i++) {
	        if (reservas.get(i).nomeHospede.equalsIgnoreCase(nome)) {
	            reservas.remove(i);
	            removido = true;
	            System.out.println("Reserva removida com sucesso.");
	            break;
	        }
	    }
	    if (!removido) {
	        System.out.println("Reserva não encontrada.");
	    }
	}
	public static void infohotel() {
		System.out.println("Nome: Hotel Magic LTDA.");
		System.out.println("Rua: Magia está no ar.");
		System.out.println("Bairro: Magico Bum.");
		System.out.println("Cidade: Reino Magico.");
		System.out.println("Cnpj: 11.222.333/0001-44\n");
	}

}
