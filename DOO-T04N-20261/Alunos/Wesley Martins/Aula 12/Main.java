
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        atividade1();
        atividade2();
        atividade3();
        atividade4e5();
        atividade6();
    }

    private static void atividade1() {
        List<Integer> numeros = Arrays.asList(12, 7, 24, 3, 18, 41, 60, 99, 100, 27);
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Atv1 - Números pares:");
        System.out.println("Entrada: " + numeros);
        System.out.println("Pares: " + pares);
        System.out.println();
    }

    private static void atividade2() {
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Atv2 - Nomes em maiúsculas:");
        System.out.println("Entrada: " + nomes);
        System.out.println("Saída: " + nomesMaiusculos);
        System.out.println();
    }

    private static void atividade3() {
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> ocorrencias = palavras.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        System.out.println("Atv3 - Contagem de palavras:");
        System.out.println("Entrada: " + palavras);
        ocorrencias.forEach((palavra, count) -> System.out.println(palavra + ": " + count));
        System.out.println();
    }

    private static void atividade4e5() {
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 1299.90),
                new Produto("Camiseta", 79.90),
                new Produto("Fone de ouvido", 249.50),
                new Produto("Livro", 54.90)
        );

        List<Produto> produtosCaros = produtos.stream()
                .filter(p -> p.getPreco() > 100.0)
                .collect(Collectors.toList());

        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("Atv4 - Produtos com preço maior que R$ 100,00:");
        produtosCaros.forEach(p -> System.out.println("- " + p));
        System.out.println();

        System.out.println("Atv5 - Soma total dos produtos da lista:");
        System.out.println("Soma: R$ " + String.format("%.2f", somaTotal));
        System.out.println();
    }

    private static void atividade6() {
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadasPorTamanho = linguagens.stream()
                .sorted((a, b) -> Integer.compare(a.length(), b.length()))
                .collect(Collectors.toList());

        System.out.println("Atv6 - Lista ordenada por tamanho da palavra:");
        System.out.println("Entrada: " + linguagens);
        System.out.println("Ordenada: " + ordenadasPorTamanho);
        System.out.println();
    }
}
