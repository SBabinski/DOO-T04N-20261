import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("===== ATV1 - Numeros pares =====");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Lista original: " + numeros);
        System.out.println("Apenas pares:   " + pares);
        System.out.println();

        System.out.println("===== ATV2 - Nomes em maiusculo =====");
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Lista original: " + nomes);
        System.out.println("Em maiusculo:   " + nomesMaiusculos);
        System.out.println();

        System.out.println("===== ATV3 - Contagem de palavras =====");
        List<String> palavras = Arrays.asList(
                "se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        System.out.println("Lista de palavras: " + palavras);
        contagem.forEach((palavra, qtd) ->
                System.out.println(palavra + " = " + qtd));
        System.out.println();

        System.out.println("===== ATV4 - Produtos acima de R$100,00 =====");
        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado", 80.00),
                new Produto("Monitor", 750.00),
                new Produto("Mouse", 45.50),
                new Produto("Cadeira Gamer", 1200.00));

        List<Produto> produtosCaros = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("Produtos com preco maior que R$100,00:");
        produtosCaros.forEach(System.out::println);
        System.out.println();

        System.out.println("===== ATV5 - Soma total dos produtos =====");
        double total = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.printf("Valor total dos produtos: R$ %.2f%n", total);
        System.out.println();

        System.out.println("===== ATV6 - Ordenar por tamanho =====");
        List<String> linguagens = Arrays.asList(
                "Java", "Python", "C", "JavaScript", "Ruby");

        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("Lista original:  " + linguagens);
        System.out.println("Ordenada (tam.): " + ordenadas);
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

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f", nome, preco);
    }
}
