package Prova;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quartoluxo {
	private static List<Quartoluxo> listaQuartos = new ArrayList<>();
	static Scanner quartoluxo = new Scanner (System.in);
	String numero;
	String andar;
	int preco;
	public static void cadastrar() {
		Quartoluxo quartosluxo = new Quartoluxo();
		System.out.println("Digite o número do quarto");
		quartosluxo.numero = quartoluxo.nextLine();
		System.out.println("Digite o andar que ele fica.");
		quartosluxo.andar = quartoluxo.nextLine();
		System.out.println("Digite o valor da diária");
		quartosluxo.preco = quartoluxo.nextInt();
		quartoluxo.nextLine();
		listaQuartos.add(quartosluxo);
		System.out.println("Quarto cadastrado com sucesso!");
	}
	public static boolean listarQuartos() {
		 if (listaQuartos.isEmpty()) return true;
	        for (Quartoluxo q : listaQuartos) {
	        	System.out.println("Quartos de luxo: ");
	            System.out.println("Número: " + q.numero + "  Andar: " + q.andar + "  Preço: R$ " + q.preco);
	        }
	        return false;
	}
}

