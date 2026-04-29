package fag;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import objetos.Hospede;
import objetos.Quarto;
import objetos.QuartoSimples;
import objetos.QuartoLuxo;
import objetos.Reserva;


public class Principal {
	
	static Scanner scan = new Scanner(System.in);
	
	static ArrayList<Hospede> hospedes = new ArrayList<>();
	static ArrayList<Quarto> quartos = new ArrayList<>();

	static Reserva[] reservas = new Reserva[10];
	static int qtdReservas = 0;

	public static void main(String[] args) {
		while (true) {
			mostrarMenu();
		}
	}
	
	public static void mostrarMenu() {
		
		int escolhaUser = 0;
		
		System.out.println("-- Bem vindo ao nosso hotel -- ");
		System.out.println("1 - Cadastrar um hóspede");
		System.out.println("2 - Cadastrar um quarto");
		System.out.println("3 - Cadastrar uma reserva");
		System.out.println("4 - Realizar o checkout");
		System.out.println("5 - Listar reservas sem checkout (Em uso)");
		System.out.println("6 - Fazer uma demonstração");
		escolhaUser = scan.nextInt();
		scan.nextLine();
		validarEscolha(escolhaUser);
	}
	
	public static void validarEscolha(int escolhaUser) {
		switch(escolhaUser) {
		case 1:
			cadastrarHospede();
			break;
		case 2:
			cadastrarQuarto();
			break;
		case 3:
			cadastrarReserva();
			break;
		case 4:
			realizarCheckout();
			break;
		case 5:
			listarReservas();
			break;
		case 6:
			demonstracao();
			break;
		default:
			System.out.println("Insira uma opção valida!");
			break;
		}
	}
	
	public static void cadastrarHospede() {
		System.out.println("Qual o nome do hospede?");
		String nome = scan.nextLine();
		
		System.out.println("Qual o CPF do hospede?");
		String cpf = scan.nextLine();
		
		System.out.println("Qual o telefone do hospede?");
		String telefone = scan.nextLine();
		
		Hospede hospede = new Hospede(nome, cpf, telefone);
		
		hospedes.add(hospede);
		
		System.out.println("Hospede cadastrado com sucesso!");
	}
	
	public static void cadastrarQuarto() {
		System.out.println("Qual o tipo de quarto a ser cadastrado?");
		System.out.println("[1] - Simples");
		System.out.println("[2] - Luxo");
		System.out.println("Escolha o tipo de quarto:");
		int escolhaTipoQuarto = scan.nextInt();		
		scan.nextLine();
		
		System.out.println("Qual o numero do quarto");
		int numero = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Qual o valor da diária?");
		double valorDaDiaria = scan.nextDouble();
		scan.nextLine();
		
		
		if (escolhaTipoQuarto == 1) {
			System.out.println("O quarto tem ventilador? (S/N)");
			String temVentiladorTxt = scan.nextLine();
			
			boolean temVentilador = false;
			
			if (temVentiladorTxt.equalsIgnoreCase("S")) {
				temVentilador = true;
			}
			
			QuartoSimples quartoSimples = new QuartoSimples(numero, valorDaDiaria, temVentilador);
			quartos.add(quartoSimples);
				
		} else if (escolhaTipoQuarto == 2) {
			System.out.println("O quarto tem varanda? (S/N)");
			String temVarandaTxt = scan.nextLine();
			
			boolean temVaranda = false;
			
			if (temVarandaTxt.equalsIgnoreCase("S")) {
				temVaranda = true;
			}
			
			QuartoLuxo quartoLuxo = new QuartoLuxo(numero, valorDaDiaria, temVaranda);
			quartos.add(quartoLuxo);
		} else {
			System.out.println("Por favor, insira um valor válido.");
		}
			
	}
	
	public static void listarHospedes() {
	    if (hospedes.isEmpty()) {
	        System.out.println("Nenhum hospedes cadastrado!");
	        return;
	    }

	    System.out.println("-- Hospedes cadastrados --");

	    for (int i = 0; i < hospedes.size(); i++) {
	        System.out.println("Índice: " + i);
	        hospedes.get(i).exibirInformacoes();
	        System.out.println("----------------------");
	    }
	}
	
	public static void listarQuartos() {
		if (quartos.isEmpty()) {
			System.out.println("Nenhum quarto cadastrado!");
			return;
		}
		
		System.out.println("-- Quartos cadastrados --");
		
		for (int i = 0; i < quartos.size(); i++) {
			System.out.println("Índice: " + i);
			quartos.get(i).exibirInformacoes();
			System.out.println("----------------------");
		}
	}
	
	public static void cadastrarReserva() {
		if (qtdReservas >= reservas.length) {
			System.out.println("Limite de locações atingido!");
			return;
		}
		
		if (hospedes.isEmpty()) {
			System.out.println("Por favor, cadastre um hospede antes de cadastrar uma reserva!");
			return;
		}
		
		if (quartos.isEmpty()) {
			System.out.println("Por favor, cadastre um hospede antes de cadastrar uma reserva!");
			return;
		}

	    listarHospedes();

	    System.out.print("Digite o índice do hospede: ");
	    int indiceHospede = scan.nextInt();
	    scan.nextLine();

	    if (indiceHospede < 0 || indiceHospede >= hospedes.size()) {
	        System.out.println("Hospede inválido!");
	        return;
	    }

	    listarQuartos();

	    System.out.print("Digite o índice do quarto: ");
	    int indiceQuarto = scan.nextInt();
	    scan.nextLine();

	    if (indiceQuarto < 0 || indiceQuarto >= quartos.size()) {
	        System.out.println("Quarto inválido!");
	        return;
	    }

	    Hospede hospedeEscolhido = hospedes.get(indiceHospede);
	    Quarto quartoEscolhido = quartos.get(indiceQuarto);

	    DateTimeFormatter formatoDaData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    System.out.print("Data de check-in (dd/MM/yyyy): ");
	    String checkInTexto = scan.nextLine();

	    LocalDate dataCheckIn = LocalDate.parse(checkInTexto, formatoDaData);
	    LocalDate dataCheckOut = null;

	    System.out.print("Quantidade de diárias: ");
	    int quantidadeDiarias = scan.nextInt();
	    scan.nextLine();

	    Reserva reserva = new Reserva(
	        hospedeEscolhido,
	        quartoEscolhido,
	        dataCheckIn,
	        dataCheckOut,
	        quantidadeDiarias,
	        false
	    );

	    reservas[qtdReservas] = reserva;
	    qtdReservas++;

	    System.out.println("Reserva cadastrada com sucesso!");	    
	}
	
	public static void listarReservas() {
		if (qtdReservas == 0) {
			System.out.println("Nenhuma reserva registrada!");
			return;
		}
		
		boolean encontrouIndice = false;
		
		for (int i = 0; i < qtdReservas; i++) {
			if (reservas[i].isCheckoutRealizado() == false) {
			    System.out.println("Índice: " + i);
			    reservas[i].exibirInformacoes();
			    System.out.println("--------------");
			    encontrouIndice = true;
			}
		}
		
		// SÓ ENTRA NO LAÇO DE REPETIÇÃO, RESERVAS QUE ESTÃO SEM CHECKOUT
		// ESSE IF NO FINAL DA FUNÇÃO SERVE PRA CASO NÃO TENHA NENHUMA RESERVA SEM CHECKOUT
		if (encontrouIndice == false) {
		    System.out.println("Nenhuma reserva sem checkout encontrada!");
		}
	}
	
	public static void realizarCheckout() {
		listarReservas();
		
		if (qtdReservas == 0) {
			return;
		}
		
		System.out.println("Digite o índice da reserva:");
		int indiceReserva = scan.nextInt();
		scan.nextLine();
		
		if (indiceReserva < 0 || indiceReserva >= qtdReservas) {
			System.out.println("Reserva Inválide");
			return;
		}


	    Reserva reservaEscolhida = reservas[indiceReserva];
	    
	    // CASO JÁ TENHA SIDO FEITO O CHECKOUT
	    if (reservaEscolhida.isCheckoutRealizado() == true) {
	    	System.out.println("O checkout ja foi feito!");
	    	return;
	    }
	    
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Preencha por favor a data de checkout (dd/MM/yyyy):");
		String checkOutTexto = scan.nextLine();
		
		LocalDate dataCheckOut = LocalDate.parse(checkOutTexto, formato);
		
		reservaEscolhida.setDataCheckOut(dataCheckOut);
		reservaEscolhida.setCheckoutRealizado(true);
		
		System.out.println("Checkout feito com sucesso!");
	}
	
	public static void demonstracao() {
		Hospede hospedeDesmonstracao1 = new Hospede("João Vitor André Alves", "10608918911", "4599999999");
		hospedes.add(hospedeDesmonstracao1);
		
		Hospede hospedeDemosntracao2 = new Hospede("Samuel Babisnki", "10345842291", "4599999999");
		hospedes.add(hospedeDemosntracao2);
		
		QuartoSimples quartoSimplesDemonstracao = new QuartoSimples(912, 199.1, false);
		quartos.add(quartoSimplesDemonstracao);
		
		QuartoLuxo quartoLuxoDemonstracao = new QuartoLuxo(911, 140.1, true);
		quartos.add(quartoLuxoDemonstracao);
		
		LocalDate checkin = LocalDate.of(2026, 4, 28);
		LocalDate checkout = LocalDate.of(2026, 4, 30);

		Reserva reserva = new Reserva(
			hospedeDesmonstracao1,
			quartoSimplesDemonstracao,
		    checkin,
		    checkout,
		    2,
		    true
		);

		reservas[qtdReservas] = reserva;
		qtdReservas++;
		
		// SEGUNDA LOCAÇÃO - DEVOLUÇÃO PENDENTE
		LocalDate checkin2 = LocalDate.of(2026, 5, 1);
		LocalDate checkout2 = null;

		Reserva reserva2 = new Reserva(
		   hospedeDemosntracao2,
		   quartoLuxoDemonstracao,
		   checkin2,
		   checkout2,
		   3,
		   false
		);

		reservas[qtdReservas] = reserva2;
		qtdReservas++;
		
		// FINALIZA A DEMONSTRAÇÃO CHAMANDO A LISTAGEM
		listarReservas();
	}
	
// A aplicação deverá ter um menu para o usuário cadastrar um hóspede, cadastrar quartos,
// cadastrar uma reserva, realizar o check-out, listar todas as reservas sem check-out e fazer uma demonstração.
}