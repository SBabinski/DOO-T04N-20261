import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Integer> numeros = Arrays.asList(10, 15, 22, 33, 40, 55, 68, 71, 84);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("== Atv1 ==\nNúmeros pares:");
        System.out.println(pares);

        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("\n== Atv2 ==\nNomes em maiúsculas:");
        System.out.println(nomesMaiusculos);

        List<String> palavras = Arrays.asList(
                "se", "talvez", "hoje", "sábado",
                "se", "quarta", "sábado"
        );

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()
                ));

        System.out.println("\n== Atv3 ==\nContagem de palavras:");
        contagem.forEach((palavra, quantidade) ->
                System.out.println(palavra + ": " + quantidade));

        List<Produto> produtos = Arrays.asList(
                new Produto("Mouse", 80.0),
                new Produto("Teclado", 120.0),
                new Produto("Monitor", 900.0),
                new Produto("Webcam", 150.0)
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(produto -> produto.getPreco() > 100)
                .collect(Collectors.toList());

        System.out.println("\n== Atv4 ==\nProdutos com preço maior que R$100:");
        produtosFiltrados.forEach(System.out::println);

        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\n== Atv5 ==\nSoma total dos produtos:");
        System.out.println("R$ " + somaTotal);

        List<String> linguagens = Arrays.asList(
                "Java", "Python", "C", "JavaScript", "Ruby"
        );

        List<String> ordenadas = linguagens.stream()
                .sorted((a, b) -> Integer.compare(a.length(), b.length()))
                .collect(Collectors.toList());

        System.out.println("\n== Atv6 ==\nLinguagens ordenadas por tamanho:");
        System.out.println(ordenadas);
    }
}