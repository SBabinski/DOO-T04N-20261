import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //ATV1
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> pares = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("=== ATV1 - Numeros pares ===");
        System.out.println(pares);

        //ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("\n=== ATV2 - Nomes em maiusculo ===");
        System.out.println(nomesMaiusculos);

        //ATV3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));

        System.out.println("\n=== ATV3 - Contagem de palavras ===");
        System.out.println(contagemPalavras);

        //ATV4
        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado", 89.90),
                new Produto("Monitor", 750.00),
                new Produto("Mouse", 49.90),
                new Produto("Cadeira", 540.00)
        );

        List<Produto> produtosCaros = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("\n=== ATV4 - Produtos com preco maior que R$ 100,00 ===");
        produtosCaros.forEach(produto -> System.out.println(produto.getNome() + " - R$ " + produto.getPreco()));

        //ATV5
        double valorTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\n=== ATV5 - Soma do valor total dos produtos ===");
        System.out.println("R$ " + valorTotal);

        //ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("\n=== ATV6 - Lista ordenada por tamanho da palavra ===");
        System.out.println(linguagensOrdenadas);
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
}
