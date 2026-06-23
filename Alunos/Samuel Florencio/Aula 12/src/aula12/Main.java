package aula12;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //Atividade 1 Abaixo.
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .toList();

        System.out.println("ATV1 - Números pares:");
        System.out.println(pares);

        // Atividade 2 Abaixo
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println("\nATV2 - Nomes em maiúsculo:");
        System.out.println(nomesMaiusculos);

        // Atividade 3 Abaixo.
        List<String> palavras = Arrays.asList(
                "se", "talvez", "hoje", "sábado",
                "se", "quarta", "sábado"
        );

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println("\nATV3 - Contagem de palavras:");
        contagemPalavras.forEach((palavra, quantidade) ->
                System.out.println(palavra + ": " + quantidade));

        // Atividade 4 abaixo.
        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado",240.00),
                new Produto("Mouse", 520.00),
                new Produto("Microfone", 80.00),
                new Produto("MousePad", 90.00)
        );

        List<Produto> produtosAcima100 = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.00)
                .toList();

        System.out.println("\nATV4 - Produtos com preço maior que R$100,00:");
        produtosAcima100.forEach(System.out::println);

        // Atividade 5 abaixo.
        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\nATV5 - Soma total dos produtos:");
        System.out.printf("R$ %.2f%n", somaTotal);

        // Atividade 6 abaixo.
        List<String> linguagens = Arrays.asList(
                "Java", "Python", "C", "JavaScript", "Ruby"
        );

        List<String> ordenadasPorTamanho = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();

        System.out.println("\nATV6 - Linguagens ordenadas por tamanho:");
        System.out.println(ordenadasPorTamanho);
    }
}


class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String toString() {
        return nome + " - R$ " + preco;
    }
}