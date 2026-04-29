package provaBimestral;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	public static void main(String [] args) {
	List<Pessoa> pessoas = new ArrayList<>();
	List<Luxo> luxoquartos = new ArrayList<>();
	List<Simples> simplesquartos = new ArrayList<>();
	List<Reserva> reservas = new ArrayList<>();
	Scanner scan = new Scanner(System.in);
	Simples simp1 = new Simples(1, 50, 2);
	simplesquartos.add(simp1);
	Luxo lux1 = new Luxo(142, 500, 1);
	luxoquartos.add(lux1);
	Pessoa pess1 = new Pessoa(1 ,"Cleituon", "213123123124123");
	Pessoa pess2 = new Pessoa(2 ,"juninho ", "8824823752834283");
	pessoas.add(pess1);
	pessoas.add(pess2);
	
	Reserva reservaTeste1 = new Reserva(
	    simp1.getNumero(),
	    pess1.getNome(),
	    LocalDate.of(2026, 4, 20),  
	    LocalDate.of(2026, 4, 25), 
	    simp1.getValor()
	);
	reservas.add(reservaTeste1);
	simp1.setDisponivel(false);

	Reserva reservaTeste2 = new Reserva(
	    lux1.getNumero(),
	    pess2.getNome(),
	    LocalDate.of(2026, 4, 27), 
	    LocalDate.of(2026, 5, 10), 
	    lux1.getValor()
	);
	reservas.add(reservaTeste2);
	lux1.setDisponivel(false);
DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
System.out.println("Bem Vindo ao Cadastro no Hotel Cia e Mania");
int resposta;
int pessoaid =3;

do {
	System.out.println("-----------------------");
	System.out.println("Digite 1: Cadastrar alguém");
	System.out.println("Digite 2: Cadastrar um quarto");
	System.out.println("Digite 3: Apresentar Quartos");
	System.out.println("Digite 4: Cadastrar uma Reserva");
	System.out.println("Digite 5: Visualizar reservas");
	System.out.println("Digite 10: Ir embora");
	 resposta = scan.nextInt();
	scan.nextLine();
	switch(resposta) {	
	case 1:
		System.out.println(" Digite o Nome da Pessoa");
		String nomeP = scan.nextLine();
	System.out.println(" Digite o CPF da Pessoa");
		String cpfP = scan.nextLine();
		
	Pessoa p = new Pessoa(pessoaid, nomeP, cpfP);
	pessoas.add(p);
	pessoaid+=1;
	break;
	
	case 2:
		System.out.println("Qual vai ser o tipo de Quarto cadastrado?\n || 1 - Simples || 2 - Luxo || 3 - Sair||");
		int tipoQuarto = scan.nextInt();
		scan.nextLine();
	switch(tipoQuarto) {
	case 1:
		System.out.println("Digite o número do Quarto: ");
		int numQuartoSimples = scan.nextInt();
		scan.nextLine();
		System.out.println("Digite o Valor :");
		double valorQuartoSimples = scan.nextDouble();
		System.out.println("Possuí ventilador : 1 -sim 2- Não");
		int possuiVentilador = scan.nextInt();
		scan.nextLine();
		while(possuiVentilador!=1 && possuiVentilador!=2) {
			System.out.println("Possuí ventilador : 1 -sim 2- Não || Por favor preencha corretamente");
			possuiVentilador = scan.nextInt();
			scan.nextLine();
		}
		Simples smp = new Simples(numQuartoSimples, valorQuartoSimples, possuiVentilador);
		simplesquartos.add(smp);
		break;
	case 2:
		System.out.println("Digite o número do Quarto: ");
		int numQuartoLuxo = scan.nextInt();
		scan.nextLine();
		System.out.println("Digite o Valor :");
		double valorQuartoLuxo = scan.nextDouble();
		System.out.println("Possuí Varanda : 1 -sim 2- Não");
		int possuiVaranda = scan.nextInt();
		scan.nextLine();
		while(possuiVaranda!= 1 && possuiVaranda!= 2) {
			System.out.println("Possuí Varanda : 1 -sim 2- Não || Por favor preencha corretamente");
			possuiVaranda = scan.nextInt();
			scan.nextLine();
		}
		Luxo lux = new Luxo(numQuartoLuxo, valorQuartoLuxo, possuiVaranda);
		luxoquartos.add(lux);
		
			break;
	default:
		System.out.println("resposta não condiz com sistema, Leia as instruções com atenção!!!");
			break;
	}
	break;
	
	case 3: 
		System.out.println("Apresentar os Quartos"
		+ " | Digite 1 para apresentar os quartos Simples e 2 Para os de Luxo |");
		int apresentarQuartosValor = scan.nextInt();
		scan.nextLine();
		switch(apresentarQuartosValor) {
		case 1:
			for (Simples q : simplesquartos) {
			    q.exibirInformacoes();
			}
			break;
		case 2:
			for (Luxo l : luxoquartos) {
			    l.exibirInformacoes();
			}
		break;
		
		default:
			System.out.println("resposta não condiz com sistema, Leia as instruções com atenção!!!");
				break;
		}
	
case 4: 
    if (reservas.size() >= 10) {
        System.out.println("Hotel lotado! Não é possível fazer mais reservas.");
        break;
    }
    System.out.println("Cadastrar uma Reserva Digite qual Tipo de quarto: \n | 1 - Simples | | 2 - Luxo |");
    int i;
    int escolhaReserva = scan.nextInt();
    LocalDate dataReservaSimp = LocalDate.now();
    scan.nextLine();
    switch (escolhaReserva) {
    case 1:
        System.out.println("Digite a data de saída (dd/MM/yyyy):");
        String dataSaidaStr = scan.nextLine();
        LocalDate dataSaidaSimp = LocalDate.parse(dataSaidaStr, formato);
        for (i = 0; i < simplesquartos.size(); i++) {
            System.out.println("[" + i + "] - " + simplesquartos.get(i));
        }
        System.out.print("Escolha o quarto: ");
        int escolhaquartoSimples = scan.nextInt();
        scan.nextLine();
        Simples quartoEscolhido = simplesquartos.get(escolhaquartoSimples);
        if (quartoEscolhido.isDisponivel()) {
            for (i = 0; i < pessoas.size(); i++) {
                System.out.println("[" + i + "] - " + pessoas.get(i));
            }
            System.out.print("Escolha a pessoa: ");
            int escolhaPessoa = scan.nextInt();
            scan.nextLine();
            Pessoa pessoaEscolhida = pessoas.get(escolhaPessoa);
            Reserva reserva = new Reserva(
                    quartoEscolhido.getNumero(),
                    pessoaEscolhida.getNome(),
                    dataReservaSimp,
                    dataSaidaSimp,
                    quartoEscolhido.getValor());
            reservas.add(reserva);
            quartoEscolhido.setDisponivel(false);
            System.out.println("Reserva cadastrada com sucesso!");
        } else {
            System.out.println("Quarto indisponível, escolha outro.");
        }
        break;
    case 2:
        System.out.println("Digite a data de saída (dd/MM/yyyy):");
        String dataSaidaLux = scan.nextLine();
        LocalDate dataSaidaLuxForm = LocalDate.parse(dataSaidaLux, formato);
        for (i = 0; i < luxoquartos.size(); i++) {
            System.out.println("[" + i + "] - " + luxoquartos.get(i));
        }
        System.out.print("Escolha o quarto: ");
        int escolhaquartoLuxo = scan.nextInt();
        scan.nextLine();
        Luxo quartoEscolhidoLux = luxoquartos.get(escolhaquartoLuxo);
        if (quartoEscolhidoLux.isDisponivel()) {
            for (i = 0; i < pessoas.size(); i++) {
                System.out.println("[" + i + "] - " + pessoas.get(i));
            }
            System.out.print("Escolha a pessoa: ");
            int escolhaPessoaLuxo = scan.nextInt();
            scan.nextLine();
            Pessoa pessoaEscolhidaLux = pessoas.get(escolhaPessoaLuxo);
            Reserva reservalux = new Reserva(
                    quartoEscolhidoLux.getNumero(),
                    pessoaEscolhidaLux.getNome(),
                    dataReservaSimp,
                    dataSaidaLuxForm,
                    quartoEscolhidoLux.getValor());
            reservas.add(reservalux);
            quartoEscolhidoLux.setDisponivel(false);
            System.out.println("Reserva cadastrada com sucesso!");
        } else {
            System.out.println("Quarto indisponível, escolha outro.");
        }
        break;
    }
break;

	case 5:
	    System.out.println("===== RESERVAS EM ABERTO =====");
	    boolean encontrou = false;
	    LocalDate hoje = LocalDate.now();
	    for (i = 0; i < reservas.size(); i++) {
	        if (reservas.get(i).dataSaida.isAfter(hoje)) {
	            reservas.get(i).exibirInformacoes();
	            encontrou = true;
	        }
	    }
	    if (!encontrou) {
	        System.out.println("Nenhuma reserva em aberto.");
	    }
	break;

	case 10:
		System.out.println("Até a próxima!!!");
		break;
		default:
		System.out.println("resposta não condiz com sistema, Leia as instruções com atenção!!!");
		break;
	}
		
}while(resposta != 10);
	
scan.close();}
}
