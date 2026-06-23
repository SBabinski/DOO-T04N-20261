import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //ATV1

        List<Integer> numeros = Arrays.asList(0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> pares = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("ATV1");
        System.out.println("Números pares: " + pares);


        //ATV2

        List<String> nomes = Arrays.asList(
                "roberto",
                "josé",
                "caio",
                "vinicius"
        );

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("\nATV2");
        System.out.println("Nomes em maiúsculo: " + nomesMaiusculos);


        //ATV3

        List<String> palavras = Arrays.asList
                ( "se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()
                ));

        System.out.println("\nATV3");
        System.out.println("Contagem de palavras: " + contagem);


        //ATV4

        List<Produto> produtos = Arrays.asList(
                new Produto("Mouse", 90.00),
                new Produto("Teclado", 250.00),
                new Produto("Fone", 450.00),
                new Produto("Monitor", 1800.00)
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(produto -> produto.preco > 100)
                .collect(Collectors.toList());

        System.out.println("\nATV4");
        System.out.println("Produtos acima de R$ 100,00: " + produtosFiltrados);


        //ATV5

        double somaTotal = produtos.stream()
                .mapToDouble(produto -> produto.preco)
                .sum();

        System.out.println("\nATV5");
        System.out.println("Soma total dos produtos: R$ " + somaTotal);


        //ATV6

        List<String> linguagens = Arrays.asList(
                "Java",
                "Python",
                "C",
                "JavaScript",
                "Ruby"
        );

        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("\nATV6");
        System.out.println("Linguagens ordenadas por tamanho: " + linguagensOrdenadas);
    }
}

class Produto {

    String nome;
    double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + preco;
    }
}