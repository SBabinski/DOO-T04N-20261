package fag;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		//ATV1
		List<Integer> numeros = Arrays.asList(2, 5, 8, 11, 14, 17, 20, 23);

		List<Integer> numerosPares = numeros.stream()
				.filter(numero -> numero % 2 == 0)
				.collect(Collectors.toList());

		System.out.println("ATV1 - Números pares:");
		System.out.println(numerosPares);

		//ATV2
		List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

		List<String> nomesMaiusculos = nomes.stream()
				.map(nome -> nome.toUpperCase())
				.collect(Collectors.toList());

		System.out.println("\nATV2 - Nomes em maiúsculo:");
		System.out.println(nomesMaiusculos);

		//ATV3
		List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

		Map<String, Long> quantidadePalavras = palavras.stream()
				.collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));

		System.out.println("\nATV3 - Quantidade de cada palavra:");
		System.out.println(quantidadePalavras);

		//ATV4 - PRODUTO
		List<Produto> produtos = Arrays.asList(
				new Produto("Caderno", 25.00),
				new Produto("Mochila", 150.00),
				new Produto("Estojo", 135.00),
				new Produto("Calculadora", 70.00)
		);

		List<Produto> produtosAcimaDeCem = produtos.stream()
				.filter(produto -> produto.preco > 100.00)
				.collect(Collectors.toList());

		System.out.println("\nATV4 - Produtos acima de R$ 100,00:");
		produtosAcimaDeCem.forEach(produto -> System.out.println(produto));

		//ATV5
		double somaTotalProdutos = produtos.stream()
				.mapToDouble(produto -> produto.preco)
				.sum();

		System.out.println("\nATV5 - Soma total dos produtos:");
		System.out.println("R$ " + somaTotalProdutos);

		//ATV6
		List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

		List<String> linguagensOrdenadas = linguagens.stream()
				.sorted((linguagem1, linguagem2) -> Integer.compare(linguagem1.length(), linguagem2.length()))
				.collect(Collectors.toList());

		System.out.println("\nATV6 - Linguagens ordenadas pelo tamanho da palavra:");
		System.out.println(linguagensOrdenadas);
	}
}
