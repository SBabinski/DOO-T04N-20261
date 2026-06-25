package fag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fag.objetos.Hospede;
import fag.objetos.PopularLista;
import fag.objetos.Quarto;
import fag.objetos.QuartoLuxo;
import fag.objetos.QuartoSimples;
import fag.objetos.Reserva;

public class Principal {
	private static final Scanner SCANNER = new Scanner(System.in);
	private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final List<Hospede> HOSPEDES = new ArrayList<Hospede>();
	private static final List<Quarto> QUARTOS = new ArrayList<Quarto>();
	private static final Reserva[] RESERVAS = new Reserva[10];
	private static int proximoIdReserva = 3;

	public static void main(String[] args) {
		PopularLista.popular(HOSPEDES, QUARTOS, RESERVAS);
		exibirMenu();
	}

	private static void exibirMenu() {
		int opcao;

		do {
			System.out.println("\n--- SISTEMA DE GERENCIAMENTO DE RESERVAS ---\n");
			System.out.println("1 - Cadastrar hospede");
			System.out.println("2 - Cadastrar quarto");
			System.out.println("3 - Cadastrar reserva");
			System.out.println("4 - Realizar check-out");
			System.out.println("5 - Listar reservas ativas");
			System.out.println("0 - Sair");
			System.out.println("---------------------------------------------\n");
			opcao = lerInteiro("Informe a opcao desejada: ");

			switch (opcao) {
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
				realizarCheckOut();
				break;
			case 5:
				listarReservasAtivas();
				break;
			case 0:
				System.out.println("Encerrando o sistema.");
				break;
			default:
				System.out.println("Opcao invalida.");
			}
		} while (opcao != 0);
	}

	// Menu: 
	private static void cadastrarHospede() {
		System.out.println("\n------ Cadastrar Hospede ------\n");

		String nome = lerTexto("Nome do hospede: ");
		String cpf = lerTexto("CPF do hospede: ");
		String telefone = lerTexto("Telefone do hospede: ");

		Hospede hospede = new Hospede(nome, cpf, telefone);
		if (hospede.dadosValidos()) {
			HOSPEDES.add(hospede);
			System.out.println("Hospede cadastrado com sucesso.");
		} else {
			System.out.println("Hospede nao cadastrado.");
		}
		System.out.println("---------------------------------------------");
	}

	private static void cadastrarQuarto() {
		System.out.println("\n------ Cadastrar Quarto ------\n");

		System.out.println("1 - Quarto simples");
		System.out.println("2 - Quarto luxo");
		System.out.println("---------------------------------------------\n");
		int tipo = lerInteiro("Escolha o tipo do quarto: ");
		int numeroQuarto = lerInteiro("Numero do quarto: ");
		if (buscarQuartoPorNumero(numeroQuarto) != null) {
			System.out.println("Ja existe um quarto com esse numero.");
			return;
		}
		double valorDiaria = lerDouble("Valor da diaria: ");
		Quarto quarto;

		if (tipo == 1) {
			boolean ventilador = lerBoolean("Possui ventilador? (s/n): ");
			quarto = new QuartoSimples(numeroQuarto, valorDiaria, ventilador);
		} else if (tipo == 2) {
			boolean varanda = lerBoolean("Possui varanda? (s/n): ");
			quarto = new QuartoLuxo(numeroQuarto, valorDiaria, varanda);
		} else {
			System.out.println("Tipo de quarto invalido.");
			return;
		}

		if (quarto.dadosValidos()) {
			QUARTOS.add(quarto);
			System.out.println("Quarto cadastrado com sucesso.");
		} else {
			System.out.println("Quarto nao cadastrado.");
		}
		System.out.println("---------------------------------------------");
	}

	private static void cadastrarReserva() {
		System.out.println("\n------ Cadastrar Reserva ------\n");

		int posicaoDisponivel = buscarPosicaoDisponivelReserva();
		if (posicaoDisponivel == -1) {
			System.out.println("Nao ha espaco disponivel para novas reservas.");
			return;
		}
		if (HOSPEDES.isEmpty()) {
			System.out.println("Cadastre pelo menos um hospede antes de registrar uma reserva.");
			System.out.println("---------------------------------------------");
			return;
		}
		if (QUARTOS.isEmpty()) {
			System.out.println("Cadastre pelo menos um quarto antes de registrar uma reserva.");
			System.out.println("---------------------------------------------");
			return;
		}

		System.out.println("Escolha o hospede:\n");
		listarHospedes();
		System.out.println();
		int indiceHospede = lerInteiro("Digite o numero do hospede: ");
		if (indiceHospede <= 0 || indiceHospede > HOSPEDES.size()) {
			System.out.println("Hospede invalido.");
			System.out.println("---------------------------------------------");
			return;
		}

		LocalDate checkIn = lerData("\nData de check-in (DD/MM/AAAA): ");
		if (!dataCheckInValida(checkIn)) {
			System.out.println("---------------------------------------------");
			return;
		}

		List<Quarto> quartosDisponiveis = buscarQuartosDisponiveisPorData(checkIn);
		if (quartosDisponiveis.isEmpty()) {
			System.out.println("\nNao ha quartos disponiveis nessa data.");
			System.out.println("---------------------------------------------");
			return;
		}

		System.out.println("\nEscolha o quarto:\n");
		listarQuartos(quartosDisponiveis);
		System.out.println();
		int indiceQuarto = lerInteiro("Digite o numero do quarto: ");
		if (indiceQuarto <= 0 || indiceQuarto > quartosDisponiveis.size()) {
			System.out.println("Quarto invalido.");
			System.out.println("---------------------------------------------");
			return;
		}
		Quarto quarto = quartosDisponiveis.get(indiceQuarto - 1);

		LocalDate checkOut = lerData("Data de check-out (DD/MM/AAAA): ");
		Reserva reserva = new Reserva(proximoIdReserva, HOSPEDES.get(indiceHospede - 1), quarto, checkIn, checkOut,
				false);
		if (reserva.dadosValidos()) {
			RESERVAS[posicaoDisponivel] = reserva;
			proximoIdReserva++;
			System.out.println("Reserva cadastrada com sucesso.");
			reserva.exibirInformacoes();
		} else {
			System.out.println("Reserva nao cadastrada.");
		}
		System.out.println("---------------------------------------------");
	}

	private static void realizarCheckOut() {
		System.out.println("\n------ Realizar Check-out ------\n");

		System.out.println("Reservas ativas disponiveis para check-out:\n");
		if (!listarReservasAtivasSemTitulo()) {
			return;
		}

		int idReserva = lerInteiro("\nInforme o ID da reserva para realizar o check-out: ");
		Reserva reserva = buscarReservaPorId(idReserva);
		if (reserva == null) {
			System.out.println("\nReserva nao encontrada.");
			return;
		}

		if (!reserva.isCheckOutRealizado()) {
			reserva.realizarCheckOut();
			System.out.println("\nCheck-out realizado com sucesso.");
		}
	}

	private static void listarReservasAtivas() {
		System.out.println("\n------ Reservas Ativas ------\n");

		listarReservasAtivasSemTitulo();
	}
	
	
// + metodos
	private static boolean listarReservasAtivasSemTitulo() {
		boolean encontrou = false;
		for (Reserva reserva : RESERVAS) {
			if (reserva != null && !reserva.isCheckOutRealizado()) {
				reserva.exibirInformacoes();
				System.out.println("---------------------------------------------");
				encontrou = true;
			}
		}

		if (!encontrou) {
			System.out.println("Nao existem reservas ativas.");
			System.out.println("---------------------------------------------");
		}

		return encontrou;
	}

	private static void listarHospedes() {
		if (HOSPEDES.isEmpty()) {
			System.out.println("Nao ha hospedes cadastrados.");
			return;
		}

		for (int i = 0; i < HOSPEDES.size(); i++) {
			System.out.printf("%d- ", i + 1);
			HOSPEDES.get(i).exibirInformacoes();
			System.out.println("---------------------------------------------");
		}
	}

	private static void listarQuartos() {
		if (QUARTOS.isEmpty()) {
			System.out.println("Nao ha quartos cadastrados.");
			return;
		}

		listarQuartos(QUARTOS);
	}

	private static void listarQuartos(List<Quarto> quartos) {
		for (int i = 0; i < quartos.size(); i++) {
			System.out.printf("%d- ", i + 1);
			quartos.get(i).exibirInformacoes();
			System.out.println("---------------------------------------------");
		}
	}

	private static int buscarPosicaoDisponivelReserva() {
		for (int i = 0; i < RESERVAS.length; i++) {
			if (RESERVAS[i] == null) {
				return i;
			}
		}
		return -1;
	}

	private static Quarto buscarQuartoPorNumero(int numeroQuarto) {
		for (Quarto quarto : QUARTOS) {
			if (quarto.getNumeroQuarto() == numeroQuarto) {
				return quarto;
			}
		}
		return null;
	}

	private static Reserva buscarReservaPorId(int idReserva) {
		for (Reserva reserva : RESERVAS) {
			if (reserva != null && reserva.getIdReserva() == idReserva) {
				return reserva;
			}
		}
		return null;
	}

	private static boolean existeReservaAtivaParaQuarto(int numeroQuarto) {
		for (Reserva reserva : RESERVAS) {
			if (reserva != null && !reserva.isCheckOutRealizado()
					&& reserva.getQuarto().getNumeroQuarto() == numeroQuarto) {
				return true;
			}
		}
		return false;
	}

	private static List<Quarto> buscarQuartosDisponiveisPorData(LocalDate dataCheckIn) {
		List<Quarto> quartosDisponiveis = new ArrayList<Quarto>();

		for (Quarto quarto : QUARTOS) {
			if (quartoDisponivelNaData(quarto, dataCheckIn)) {
				quartosDisponiveis.add(quarto);
			}
		}

		return quartosDisponiveis;
	}

	private static boolean quartoDisponivelNaData(Quarto quarto, LocalDate dataCheckIn) {
		for (Reserva reserva : RESERVAS) {
			if (reserva != null && !reserva.isCheckOutRealizado()
					&& reserva.getQuarto().getNumeroQuarto() == quarto.getNumeroQuarto()) {
				if (!dataCheckIn.isBefore(reserva.getDataCheckIn()) && !dataCheckIn.isAfter(reserva.getDataCheckOut())) {
					return false;
				}
			}
		}

		return true;
	}

	private static boolean dataCheckInValida(LocalDate checkIn) {
		if (checkIn == null) {
			System.out.println("A data de check-in deve ser informada.");
			return false;
		}
		if (checkIn.isBefore(LocalDate.now())) {
			System.out.println("A data de check-in nao pode ser anterior a data atual.");
			return false;
		}
		return true;
	}

	
	// ler informacoes
	private static String lerTexto(String mensagem) {
		System.out.print(mensagem);
		return SCANNER.nextLine();
	}

	private static int lerInteiro(String mensagem) {
		while (true) {
			try {
				System.out.print(mensagem);
				return Integer.parseInt(SCANNER.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Digite um numero inteiro valido.");
			}
		}
	}

	private static double lerDouble(String mensagem) {
		while (true) {
			try {
				System.out.print(mensagem);
				return Double.parseDouble(SCANNER.nextLine().replace(",", "."));
			} catch (NumberFormatException e) {
				System.out.println("Digite um valor numerico valido.");
			}
		}
	}

	private static boolean lerBoolean(String mensagem) {
		while (true) {
			String resposta = lerTexto(mensagem).trim().toLowerCase();
			if (resposta.equals("s")) {
				return true;
			}
			if (resposta.equals("n")) {
				return false;
			}
			System.out.println("Digite apenas s para sim ou n para nao.");
		}
	}

	private static LocalDate lerData(String mensagem) {
		while (true) {
			try {
				System.out.print(mensagem);
				return LocalDate.parse(SCANNER.nextLine(), FORMATADOR_DATA);
			} catch (DateTimeParseException e) {
				System.out.println("Digite a data no formato DD/MM/AAAA.");
			}
		}
	}
}
