package objetos;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Hotel hotel = new Hotel();
		ArrayList<Hospede> hospedes = new ArrayList<Hospede>();
		ArrayList<Quarto> quarto = new ArrayList<Quarto>();
		int menu = -1;
		
		while(menu != 0) {
			System.out.println("-------------- Menu Hotel --------------");
			System.out.println("Escolha uma opção:");
			System.out.println("[0] Encerrar o sistema");
			System.out.println("[1] Cadastrar Hospede");
			System.out.println("[2] Cadastrar Quarto");
			System.out.println("[3] Fazer Reserva");
			System.out.println("[4] Encerrar Reserva");
			System.out.println("[5] Mostrar Reservas Ativas");
			System.out.println("[6] Fazer Demonstração");
			menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			
			case 1: 
				System.out.println("Digite o nome do hospede: ");
				String nome = sc.nextLine();
				System.out.println("Agora, digite o CPF do hospede: ");
				String cpf = sc.nextLine();
				System.out.println("Por fim, digite o telefone do hospede: ");
				String telefone = sc.nextLine();
				System.out.println("Hospede cadastrado!");
				
				Hospede hospede = new Hospede(nome,cpf,telefone);
				hospedes.add(hospede);
				break;
				
			case 2: 
				System.out.println("Escolha o tipo do seu quarto: ");
				System.out.println("[1] Quarto simples");
				System.out.println("[2] Quarto de luxo");
				int escolha = sc.nextInt();
				sc.nextLine();					
				System.out.println("Digite qual é o número do quarto");
				int numeroQuarto = sc.nextInt();
				sc.nextLine();
				System.out.println("Digite qual é o valor da diária");
				double diaria = sc.nextDouble();
				sc.nextLine();
				
				if(escolha == 1) {
					System.out.println("O quarto possui ventilador?");
					System.out.println("[true]  para possui");
					System.out.println("[false] para não possui");
					boolean temVentilador = sc.nextBoolean();
					sc.nextLine();
					System.out.println("Quarto simples cadastrado!");
					Simples simples = new Simples(numeroQuarto, diaria, temVentilador);
					quarto.add(simples);
				}
				 else if(escolha == 2) {
					 System.out.println("O quarto possui varanda?");
						System.out.println("[true]  para possui");
						System.out.println("[false] para não possui");
						boolean temVaranda = sc.nextBoolean();
						sc.nextLine();
						System.out.println("Quarto de Luxo cadastrado!");
						Luxo luxo= new Luxo(numeroQuarto, diaria, temVaranda);
						quarto.add(luxo);
					
				}
				
				else  {
					System.out.println("Modelo de quarto inválido! :(");
				}
						
				break;
				
			case 3:
				if(hospedes.size() == 0 || quarto.size() == 0) {
					System.out.println("É preciso cadastrar pelo menos um hospede e um quarto");
					return;
				} else {
					System.out.println("Todos os Hospedes Cadastrados: ");
					for(int i = 0; i < hospedes.size(); i++) {
						System.out.println("ID " + i + " -> " + hospedes.get(i).nome);
					}
					System.out.println("Escolha o ID do hospede desejado: ");
					int escolhaHospede = sc.nextInt();
					sc.nextLine();
					
					System.out.println("Todos os Quartos Cadastrados: ");
					for(int i = 0; i < quarto.size(); i++) {
						System.out.println("Nº " + i + " -> " + quarto.get(i).numeroQuarto);
					}
					System.out.println("Escolha o Nº do quarto desejado: ");
					int escolhaQuarto = sc.nextInt();
					sc.nextLine();
					
					if(escolhaHospede < -1 || escolhaHospede >= hospedes.size() || escolhaQuarto < 0 || escolhaQuarto >= quarto.size()) {
						System.out.println("Escolha inválida");
						return;
					}
					
					System.out.print("Escolha a Data de inicio da reserva: ");
					String dataInicio = sc.nextLine();
					System.out.print("Escolha a Data de saida da reserva: ");
					String dataFim = sc.nextLine();

					Reserva reserva = new Reserva(hospedes.get(escolhaHospede), quarto.get(escolhaQuarto), dataInicio, dataFim, false);
					hotel.fazerReserva(reserva);
					break;	
				}
				
			case 4: 
				if(hotel.reservas.size() == 0) {
					System.out.println("Não foi possivel encontrar reservas ativas :(");
					return;
				} else {
					System.out.println("Lista de Reservas: ");
					for(int i=0; i < hotel.reservas.size(); i++) {
						Reserva reserva = hotel.reservas.get(i);
						System.out.println("ID " + i + " -> " + reserva.hospede.nome + " | " + reserva.quarto.numeroQuarto + " | já foi encerrado?" + (reserva.encerrado ? "Sim" : "Não"));
					}
					
					System.out.println("Escolha o ID da reserva pra encerrar a hospedagem");
					int escolhaReserva = sc.nextInt();
					sc.nextLine();
					
					if(escolhaReserva < -1 || escolhaReserva >= hotel.reservas.size()) {
						System.out.println("Escolha inválida");
						return;
					}
					System.out.println("Reserva encerrada!");
					hotel.reservas.get(escolhaReserva).encerrado = true;
					break;		
				}
				
			case 5: 
				hotel.listarReservasAtivas();
				break;

			case 6:
				Demonstracao();
				break;
			}
		}
	}

	public static void Demonstracao() {
		Hotel hotel = new Hotel();
		
		Hospede hospede1 = new Hospede("Zé doQ se entende", "010.101.010-11", "91100-0011");
		Hospede hospede2 = new Hospede("Paola Caolha", "101.010.101-00", "90011-1100");
		
		Simples simples1 = new Simples(102, 230.0, false);
		Simples simples2 = new Simples(201, 320.0, true);
		
		Luxo luxo1 = new Luxo(304, 340.0, false);
		Luxo luxo2 = new Luxo(403, 430.0, true);
		
		Reserva reserva1 = new Reserva(hospede1, simples1, "01/01/2015", "04/01/2015", false);
		Reserva reserva2 = new Reserva(hospede2, luxo1, "15/01/2015", "25/01/2015", true);
		
		hotel.fazerReserva(reserva1);
		hotel.fazerReserva(reserva2);
		System.out.println("Demonstração: ");
		hotel.listarReservasAtivas();		
	}
}