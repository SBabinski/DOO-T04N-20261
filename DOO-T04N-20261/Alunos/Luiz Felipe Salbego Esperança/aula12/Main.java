import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public String toString() {
        return nome + " - R$ " + preco;
    }
}

public class Main {

    public static void main(String[] args) {

        // ATV1
        System.out.println("ATV1");

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Numeros pares: " + pares);


        // ATV2
        System.out.println("\nATV2");

        List<String> nomes = Arrays.asList(
                "roberto",
                "jose",
                "caio",
                "vinicius"
        );

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Nomes em maiusculo: " + nomesMaiusculos);


        // ATV3
        System.out.println("\nATV3");

        List<String> palavras = Arrays.asList(
                "se",
                "talvez",
                "hoje",
                "sabado",
                "se",
                "quarta",
                "sabado"
        );

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()
                ));

        System.out.println("Contagem de palavras:");
        System.out.println(contagem);


        // ATV4
        System.out.println("\nATV4");

        List<Produto> produtos = Arrays.asList(
                new Produto("Mouse", 80.0),
                new Produto("Teclado", 120.0),
                new Produto("Monitor", 900.0),
                new Produto("Headset", 150.0)
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(produto -> produto.getPreco() > 100)
                .collect(Collectors.toList());

        System.out.println("Produtos acima de R$100:");
        produtosFiltrados.forEach(System.out::println);


        // ATV5
        System.out.println("\nATV5");

        double soma = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("Soma total dos produtos: R$ " + soma);


        // ATV6
        System.out.println("\nATV6");

        List<String> linguagens = Arrays.asList(
                "Java",
                "Python",
                "C",
                "JavaScript",
                "Ruby"
        );

        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("Ordenadas por tamanho:");
        System.out.println(ordenadas);
    }
}