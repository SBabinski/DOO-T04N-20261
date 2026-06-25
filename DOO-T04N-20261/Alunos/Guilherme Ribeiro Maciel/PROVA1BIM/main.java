package prova;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.*;
import prova.Hospede;
import prova.Qsimples;
import prova.Qluxo;
import prova.Reserva;

public class main {

	static Scanner scan = new Scanner(System.in);
	static List<Hospede> Hospede = new ArrayList<>();
	static List<Qsimples> Qsimples = new ArrayList<>();
	static List<Qluxo> Qluxo = new ArrayList<>();
	static List<Reserva> Reserva = new ArrayList<>();
	
	public static void main(String[] args) {
		
		Menu();
		
	}

	private static void Menu() {
		int opt=0;
		do {
			System.out.println("Escolha uma opção:");
			System.out.println("0-Sair");
			System.out.println("1-Cadastrar Hospede");
			System.out.println("2-Cadastrar Quarto Simples");
			System.out.println("3-Cadastrar Quarto de Luxo");
			System.out.println("4-Fazer Reserva");
			System.out.println("5-Listar Reservas Ativas");
			System.out.println("6-Fazer Demonstraçao");
			opt = scan.nextInt();
			scan.nextLine();
			switch(opt) {
			case 0:
				break;
			case 1:
				cadastrarHospede();
				break;
			case 2:
				cadastrarQsimples();
				break;
			case 3:
				cadastrarQluxo();
				break;
			case 4:
				fazerReserva();
				break;
			case 5:
				listarReservasAtivas();
				break;
			case 6:
				popularTabelas();
				break;
			}
		}while(opt!=0);
	}

	private static void popularTabelas() {
		Hospede hos = new Hospede("Laura", "1105338974", 99999999);
		Hospede hos2 = new Hospede("rogerio", "298442393", 32984723);
		Qsimples quar1 = new Qsimples(1, 17f, true);
		Qluxo quar2 = new Qluxo(1, 25f, true);
		Reserva rese = new Reserva(1, 2, LocalDate.of(2026, 01, 15), LocalDate.of(2026, 01, 25), true);
		Reserva rese2 = new Reserva(2, 1, LocalDate.of(2026, 04, 25), null, false);
		Hospede.add(hos);
		Hospede.add(hos2);
		Qsimples.add(quar1);
		Qluxo.add(quar2);
		Reserva.add(rese);
		Reserva.add(rese2);
		listarReservasAtivas();
	}

	private static void listarReservasAtivas() {
		for(int i = 0; i<Reserva.size(); i++) {
			if(Reserva.get(i).isSituacaoCout() == false) {
				System.out.println(i+1 + " - " + Reserva.get(i).apresentarReserva());
			}
		}
	}

	

	private static void fazerReserva() {
		Reserva reserva = new Reserva();
		reserva.setCheckin(LocalDate.now());
		System.out.println("Selecione o Hospede");
		listarHospedes();
		reserva.setHospede(scan.nextInt()-1);
		scan.nextLine();
		System.out.println("Selecione o Quarto");
		listarQuartos();
		reserva.setQuarto(scan.nextInt());
		scan.nextLine();
		System.out.println("Entre com a data de Check-Out");
		String data = scan.nextLine();
		reserva.setCheckout(DateEn(data));
		reserva.setSituacaoCout(false);
		Reserva.add(reserva);
	}

	private static void listarQuartos() {
		System.out.println("Quartos simples");
		for(int i = 0; i<Qsimples.size(); i++) {
			System.out.println(Qsimples.get(i).apresentarQuarto());
		}
		System.out.println("Quartos de luxos");
		for(int i = 0; i<Qluxo.size(); i++) {
			System.out.println(Qluxo.get(i).apresentarQuarto());
		}
	}

	private static void listarHospedes() {
		for(int i = 0; i<Hospede.size(); i++) {
			System.out.println(i+1 + " - " + Hospede.get(i).apresentarHospede());
		}
	}

	private static void cadastrarQluxo() {
		Qluxo quarto = new Qluxo();
		System.out.println("Insira o numero do quarto simples");
		quarto.setNum(scan.nextInt());
		System.out.println("Entre com o valor da diaria do quarto");
		quarto.setDiaria(scan.nextFloat());
		System.out.println("o quarto possui ventilador?");
		String resp = scan.nextLine();
		String s="sim";
		if (resp.equals(s)) {
			quarto.setVaranda(true);
		} else {
			quarto.setVaranda(false);
		}
		Qluxo.add(quarto);
		System.out.println("Quarto cadastrado com sucesso");
	}

	private static void cadastrarQsimples() {
		Qsimples quarto = new Qsimples();
		System.out.println("Insira o numero do quarto simples");
		quarto.setNum(scan.nextInt());
		scan.nextLine();
		System.out.println("Entre com o valor da diaria do quarto");
		quarto.setDiaria(scan.nextFloat());
		scan.nextLine();
		System.out.println("o quarto possui ventilador?");
		String resp = scan.nextLine();
		String s="sim";
		if (resp.equals(s)) {
			quarto.setVentilador(true);
		} else {
			quarto.setVentilador(false);
		}
		Qsimples.add(quarto);
		System.out.println("Quarto cadastrado com sucesso");
	}

	private static void cadastrarHospede() {
		Hospede hospede = new Hospede();
		System.out.println("Digite o nome do Hospede:");
		hospede.setNome(scan.nextLine());
		System.out.println("Digite o cpf do Hospede:");
		hospede.setCpf(scan.nextLine());
		System.out.println("Digite o telefone do Hospede:");
		hospede.setFone(scan.nextInt());
		scan.nextLine();
		Hospede.add(hospede);
		System.out.println("Hospede cadastrado com sucesso");
	}
	
	public static LocalDate DateEn(String dataS) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dataS, formato);
		return data;
	}

}
