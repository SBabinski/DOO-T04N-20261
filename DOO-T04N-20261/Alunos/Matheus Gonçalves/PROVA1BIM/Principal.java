package fag;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Principal {
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Reserva> Reservas = new ArrayList<>(10);
	static ArrayList<Hospede> Hospedes = new ArrayList<>();
	static ArrayList<Estadia> Quartos = new ArrayList<>();
	
	public static void main (String args[]) {
		Menu();
	}
	
	public static void Menu() {
		System.out.println("-----------------------------------");
		System.out.println("[1]- Cadastrar um hóspede ");
		System.out.println("[2]- Cadastrar quartos ");
		System.out.println("[3]- Cadastrar uma reserva ");
		System.out.println("[4]- Realizar o check-out ");
		System.out.println("[5]- Listar todas as reservas sem check-out ");
		System.out.println("[6]- fazer uma demonstração ");
		System.out.println("[7]- Sair ");
		
		int escolha = scan.nextInt();
		
		switch (escolha) {
			case 1: 
				CadastrarHospede();
			break;
			
			case 2: 
				CadastrarQuartos();
			break;
			
			case 3: 
				CadastrarReserva();
			break;
			
			case 4: 
				RealizarCheckOut();
			break;
			
			case 5: 
				ListarReservas();
			break;
			
			case 6:
				Demostracao();
			break;
			
			case 7: 
				System.out.println("Você saiu do sistema");
				return;
				
			default:
				System.out.println("Escolha invalida");
			break;
		}
		Menu();
	}
	
	public static void CadastrarHospede() {
        scan.nextLine();
        System.out.println("Nome:");
        String nome = scan.nextLine();

        System.out.println("cpf:");
        String cpf = scan.nextLine();

        System.out.println("Telefone:");
        String telefone = scan.nextLine();


        Hospede hosp = new Hospede(nome, cpf, telefone);
        Hospedes.add(hosp);
        System.out.println("Hopede criado com sucesso!");
	}
	
	public static void CadastrarQuartos() {		
		scan.nextLine();
        System.out.println("Numero:");
        int num = scan.nextInt();

        scan.nextLine();
        System.out.println("Valor:");
        double val = scan.nextDouble();

        scan.nextLine();
        System.out.println("Tem Varanda ou Ventilador:");
        boolean stat = scan.nextBoolean();

        System.out.println("[1] Simples");
        System.out.println("[2] Luxo");
        int escolha = scan.nextInt();
        
        if (escolha == 1) {
        	QuartoSimples qs = new QuartoSimples(num, val, stat);
        	System.out.println("Quarto criado com sucesso!");
        	Quartos.add(qs);
        }
        else if(escolha == 2) {
        	QuartoLuxo ql = new QuartoLuxo(num, val, stat);
        	System.out.println("Quarto criado com sucesso!");
        	Quartos.add(ql);
        }
        else {
        	System.out.println("escolha invalida!");
        }
	}
	
	public static void CadastrarReserva() {
	    if (Reservas.size() >= 10) {
	        System.out.println("Limite máximo de reservas atingido!");
	        return;
	    }
	    if (Hospedes.isEmpty()) {
	        System.out.println("Nenhum hóspede cadastrado!");
	        return;
	    }
	    if (Quartos.isEmpty()) {
	        System.out.println("Nenhum quarto cadastrado!");
	        return;
	    }

	    System.out.println("Escolha um hóspede:");
	    for (int i = 0; i < Hospedes.size(); i++) {
	        System.out.println("[" + i + "] " + Hospedes.get(i).getNome());
	    }
	    int indiceHospede = scan.nextInt();
	    if (indiceHospede < 0 || indiceHospede >= Hospedes.size()) {
	        System.out.println("Índice inválido!");
	        return;
	    }

	    System.out.println("Escolha um quarto:");
	    for (int i = 0; i < Quartos.size(); i++) {
	        System.out.print("[" + i + "] ");
	        System.out.println("Número: " + Quartos.get(i).getNumeroQuarto() + " | Valor: " + Quartos.get(i).getValorQuarto());
	    }
	    int indiceQuarto = scan.nextInt();
	    if (indiceQuarto < 0 || indiceQuarto >= Quartos.size()) {
	        System.out.println("Índice inválido!");
	        return;
	    }

	    scan.nextLine();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    System.out.println("Data de check-out (dd/MM/yyyy):");
	    LocalDate checkOut = LocalDate.parse(scan.nextLine(), formatter);

	    Reserva nova = new Reserva(
	        Hospedes.get(indiceHospede),
	        Quartos.get(indiceQuarto),
	        checkOut
	    );

	    Reservas.add(nova);

	    System.out.println("Reserva criada com sucesso!");
	}
	
	public static void ListarReservas() {
		for (int i = 0; i < Reservas.size(); i++) {	
			if (Reservas.get(i).CheckOutStatus == false) {
				System.out.println(" [" + i + "] "+ Reservas.get(i).DadosReserva());
			}
		}
	}
	
	public static void RealizarCheckOut() {
		System.out.println("Qual cadastro deseja realizar o checkout");
		ListarReservas();
		int escolha = scan.nextInt();	
		if (escolha < 0 || escolha >= Reservas.size()) {
			System.out.println("Índice inválido!");
	        return;
		}
		else {
			Reservas.get(escolha).setDataCheckOut(LocalDate.now());
			Reservas.get(escolha).CheckOutStatus = true;
			Reservas.remove(escolha);
		}
	}
	
	public static void Demostracao() {
	    Hospede h1 = new Hospede("João", "11111111111", "9999-9999");
	    Hospede h2 = new Hospede("Maria", "22222222222", "8888-8888");

	    Hospedes.add(h1);
	    Hospedes.add(h2);

	    QuartoSimples qs = new QuartoSimples(101, 100.0, true);
	    QuartoLuxo ql = new QuartoLuxo(202, 250.0, true);

	    Quartos.add(qs);
	    Quartos.add(ql);

	    Reserva r1 = new Reserva(h1, qs, LocalDate.now().plusDays(2)); 
	    Reserva r2 = new Reserva(h2, ql, LocalDate.now().plusDays(1)); 
	    r2.CheckOutStatus = true;

	    Reservas.add(r1);
	    Reservas.add(r2);

	    r2.setDataCheckOut(LocalDate.now());

	    System.out.println("Reservas ativas:");
	    ListarReservas();
	}
}
