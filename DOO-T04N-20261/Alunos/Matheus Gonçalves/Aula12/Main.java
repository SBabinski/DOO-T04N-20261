package fag;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Atv1();
        Atv2();
        Atv3();
        
        List<Produto> produtos;
        produtos = Atv4();
        Atv5(produtos);
        Atv6();
    }
    
    // ATV1
    public static void Atv1() {
		System.out.println("ATV1 - Números pares");
		
		List<Integer> numeros = Arrays.asList(10, 15, 8, 23, 42, 7, 18, 30);
		
		List<Integer> pares = numeros.stream()
		        .filter(numero -> numero % 2 == 0)
		        .collect(Collectors.toList());
		
		System.out.println("Lista original: " + numeros);
		System.out.println("Números pares: " + pares);
    }
    
    //ATV2
    public static void Atv2() {
    	System.out.println("\nATV2 - Nomes em maiúsculo");

        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Nomes originais: " + nomes);
        System.out.println("Nomes em maiúsculo: " + nomesMaiusculos);
    }
    
    //ATV3
    public static void Atv3() {
    	System.out.println("\nATV3 - Contagem de palavras");

        List<String> palavras = Arrays.asList(
                "se",
                "talvez",
                "hoje",
                "sábado",
                "se",
                "quarta",
                "sábado"
        );

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));

        contagemPalavras.forEach((palavra, quantidade) ->
                System.out.println(palavra + ": " + quantidade));
    }
    
    //ATV4
    public static List<Produto> Atv4() {
    	System.out.println("\nATV4 - Produtos com preço maior que R$100,00");

        List<Produto> produtos = Arrays.asList(
                new Produto("Mouse", 80.00),
                new Produto("Teclado Mecânico", 150.00),
                new Produto("Monitor", 900.00),
                new Produto("Headset", 120.00)
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.00)
                .collect(Collectors.toList());

        produtosFiltrados.forEach(System.out::println);
        return produtos;
    }
    
    //ATV5
    public static void Atv5(List<Produto> produtos) {
    	System.out.println("\nATV5 - Soma total dos produtos");

        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.printf("Valor total dos produtos: R$ %.2f%n", somaTotal);
    }
    
    //ATV6
    public static void Atv6() {
    	 System.out.println("\nATV6 - Linguagens ordenadas pelo tamanho");

         List<String> linguagens = Arrays.asList(
                 "Java",
                 "Python",
                 "C",
                 "JavaScript",
                 "Ruby"
         );

         List<String> linguagensOrdenadas = linguagens.stream()
                 .sorted((a, b) -> Integer.compare(a.length(), b.length()))
                 .collect(Collectors.toList());

         System.out.println("Lista original: " + linguagens);
         System.out.println("Lista ordenada: " + linguagensOrdenadas);
    }
}