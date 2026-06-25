package StreamApi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class main {
	
	static List<Produtos> produto = Arrays.asList(new Produtos("abacaxi", 12.00), new Produtos("memoria ram", 550.00), 
			new Produtos("nerf", 55.00), new Produtos("item diverso", 100.00));
	
	public static void main(String[] args) {
		Ativ1();
		Ativ2();
		Ativ3();
		Ativ4();
		Ativ5();
		Ativ6();
	}

	//ATV1
	private static void Ativ1() {
		List<Integer> numeros = Arrays.asList(1, 2, 3, 4,5 ,6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
		List<Integer> pares = numeros.stream()
				.filter(num -> num % 2 == 0)
				.collect(Collectors.toList());
		System.out.println(pares);
	}
	
	//ATV2
	private static void Ativ2() {
		List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
		List<String> maiusculas = nomes.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());
		System.out.println(maiusculas);
	}

	//ATV3
	private static void Ativ3() {
		List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
		Map<String, Long> contagem = palavras.stream()
				.filter(Objects::nonNull)
				.collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));
		System.out.println(contagem);
	}
	
	//ATV4
	private static void Ativ4() {
		Map<String, Double> caros = produto.stream()
				.filter(p -> p.getVlr()>=100.0)
				.collect(Collectors.toMap(p -> p.getNome(), p -> p.getVlr()));
		System.out.println(caros);
	}
	
	//ATV5
	private static void Ativ5() {
		double vlTotal = produto.stream()
				.mapToDouble(p -> p.getVlr())
				.sum();
		System.out.println(vlTotal);
	}
	
	//ATV6
	private static void Ativ6() {
		List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
		List<String> menor = linguagens.stream()
				.sorted(Comparator.comparingInt(String::length))
				.collect(Collectors.toList());
		System.out.println(menor);
	}
}

class Produtos {
	String nome;
	double vlr;
	
	public String getNome() {
		return nome;
	}

	public double getVlr() {
		return vlr;
	}

	Produtos(String nome, double vlr){
		this.nome=nome;
		this.vlr=vlr;
	}
}