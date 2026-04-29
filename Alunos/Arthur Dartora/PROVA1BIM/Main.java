import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Hotel hotel = new Hotel();
		
		Hospede[] hospedes = new Hospede [50];
		int totalH = 0;
		
		Quarto[] quartos = new Quarto[50];
		int totalQ = 0;
		
		int opcao;
		
		do {
			System.out.println("1 - Cadastro de hospede");
			System.out.println("2 - Cadastro de quarto");
			System.out.println("3 - Cadastro de reserva");
			System.out.println("4 - Fazer checkout");
			System.out.println("5 - Listar reservas ativas");
			System.out.println("6 - Demonstracao");
			System.out.println("0 - Sair");
			opcao = scan.nextInt();
			scan.nextLine();
			
			if(opcao == 1) {
				System.out.println("Nome: ");
				String nome = scan.nextLine();
				System.out.println("Cpf: ");
				String cpf = scan.nextLine();
				System.out.println("Telefone: ");
				String telefone = scan.nextLine();
				hospedes[totalH] = new Hospede(nome, cpf, telefone);
				totalH ++;
				System.out.println("Cadastro realizado");
			} else if(opcao ==2 ) {
				System.out.println("Tipo 1-Simples 2-Luxo ");
				int tipo = scan.nextInt();
				scan.nextLine();
				System.out.println("Numero do quarto: ");
				int num = scan.nextInt();
				System.out.println("Valor da diaria: ");
				double valor = scan.nextDouble();
				scan.nextLine();
				if (tipo == 1) {
					System.out.println("Tem ventilador (S/N): ");
					boolean ventilador = scan.nextLine().equalsIgnoreCase("s");
					quartos[totalQ] = new QuartoSimples(num, valor, ventilador);
					
				}else {
					System.out.println("Tem varanda (S/N): ");
					boolean varanda = scan.nextLine().equalsIgnoreCase("s");
					quartos[totalQ] = new QuartoLuxo(num, valor, varanda);
				}
				totalQ ++;
				System.out.println("Quarto cadastrado");
			} else if (opcao == 3) {
				if (totalH == 0 || totalQ == 0) {
					System.out.println("Cadastre hospedes e quartos");
				}else {
					for (int i = 0; i < totalH; i++) {
						System.out.println((i+1) + " " + hospedes[i].nome);
					}System.out.println("Selecione o hospede");
						int idx = scan.nextInt() - 1;
						scan.nextLine();
						
					 for (int i = 0; i < totalQ; i++) {
						 System.out.println((i+1) + "Quarto"+ quartos[i].num);
					 } System.out.println("Selecione o quarto");
					 	 int idxQ = scan. nextInt() - 1;
					 	 scan.nextLine();
					 	 
					 
						System.out.println("Checkin (dd/mm/yyyy): ");
						String checkIn = scan.nextLine();
						System.out.println("Checkout (dd/mm/yyyy): ");
						String checkOut = scan.nextLine();
						
						hotel.addReserva(new Reserva(hospedes[idx], quartos[idxQ] , checkIn, checkOut));
					}
			}
				else if (opcao == 4) {
					hotel.listaReservas();
					System.out.println("Numero da reserva: ");
					int num = scan.nextInt();
					scan.nextLine();
					Reserva r = hotel.buscaR(num);
					if(r == null) {
						System.out.println("Reserva nao encontrada");
					} else if (r.checkout) {
						System.out.println("Checkout ja realizado");
					} else {
						r.checkout = true;
						System.out.println("Checkout realizado, valor: R$ " + r.calcValor());
					}
				} else if(opcao == 5) {
					hotel.listaRAtiva();
				} else if (opcao == 6) {
					demonstracao(hotel);
				} else if (opcao != 0) {
					System.out.println("Opcao invalida");
				}
			
		} while (opcao != 0);
		System.out.println(" Saindo ");
		scan.close();
	}
	public static void demonstracao(Hotel hotel) {
		System.out.println("----------------------");
		
		Hospede hospede1 = new Hospede("Carlinhos Maia", "123.123.321-12", "(45) 91222-9999");
		Hospede hospede2 = new Hospede("Marco Tulio", "321.321.123.21", "(44) 99999-8888");
		
		QuartoSimples quartoS = new QuartoSimples(10, 350.00, true);
		QuartoLuxo quartoL = new QuartoLuxo(20, 210.00, true);
		
		Reserva reserva1 = new Reserva(hospede1, quartoS, "28/04/2026", "05/05/2026");
		Reserva reserva2 = new Reserva(hospede2, quartoL, "10/05/2026", "15/05/2026");
		
		reserva1.checkout = true;
		
		hotel.addReserva(reserva1);
		hotel.addReserva(reserva2);
		
		System.out.println("Quarto simples: ");
		quartoS.exibirQ();
		
		System.out.println("Quarto luxo: ");
		quartoL.exibirQ();
		
		System.out.println("Reservas ativas");
		hotel.listaRAtiva();
	}
}
