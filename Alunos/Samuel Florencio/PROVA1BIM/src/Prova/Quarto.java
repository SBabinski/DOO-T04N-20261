package Prova;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Quarto {
	static List<Quarto> listaQuartos = new ArrayList<>();
	static Scanner quarto = new Scanner (System.in);
	String numero;
	String andar;
	int preco;
	public static void cadastrar() {
		Quarto quartosimples = new Quarto();
		System.out.println("Digite o número do quarto");
		quartosimples.numero = quarto.nextLine();
		System.out.println("Digite o andar que ele fica.");
		quartosimples.andar = quarto.nextLine();
		System.out.println("Digite o valor da diária");
		quartosimples.preco = quarto.nextInt();
		quarto.nextLine();
		listaQuartos.add(quartosimples);
		System.out.println("Quarto cadastrado com sucesso!");
	}
	public static boolean listarQuartos() {
		 if (listaQuartos.isEmpty()) return true;
	        for (Quarto q : listaQuartos) {
	        	System.out.println("Quartos Simples: ");
	            System.out.println("Número: " + q.numero + "  Andar: " + q.andar + "  Preço: R$ " + q.preco);
	        }
	        return false;
	}
}
