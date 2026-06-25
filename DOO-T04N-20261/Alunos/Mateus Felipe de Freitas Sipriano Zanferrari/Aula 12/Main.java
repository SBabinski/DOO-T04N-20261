import java.util.*;
import java.util.stream.*;

public class Main {

    // Classe Produto usada nas Atv4 e Atv5
    static class Produto {
        private String nome;
        private double preco;

        public Produto(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }

        public String getNome() { return nome; }
        public double getPreco() { return preco; }

        @Override
        public String toString() {
            return nome + " (R$ " + String.format("%.2f", preco) + ")";
        }
    }

    public static void main(String[] args) {

        //ATV1
        // Recebe uma lista de números inteiros e retorna apenas os números pares
        List<Integer> numeros = Arrays.asList(5, 44, 31, 8, 13, 6, 25, 22, 302, 71, 12);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("=== ATV1 - Números Pares ===");
        System.out.println("Lista original : " + numeros);
        System.out.println("Números pares  : " + pares);

        //ATV2
        // Converte todos os nomes da lista para letras maiúsculas
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("\n=== ATV2 - Nomes em Maiúsculo ===");
        System.out.println("Original  : " + nomes);
        System.out.println("Maiúsculo : " + nomesMaiusculos);

        //ATV3
        // Conta quantas vezes cada palavra única aparece na lista
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        System.out.println("\n=== ATV3 - Contagem de Palavras ===");
        contagemPalavras.forEach((palavra, contagem) ->
                System.out.println("\"" + palavra + "\" aparece " + contagem + " vez(es)"));

        //ATV4
        // Filtra os produtos cujo preço seja maior que R$ 100,00 
        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado",    149.90),
                new Produto("Mouse",       79.90),
                new Produto("Monitor",    899.00),
                new Produto("Headset",     89.99)
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("\n=== ATV4 - Produtos acima de R$ 100,00 ===");
        produtosFiltrados.forEach(p -> System.out.println("  " + p));

        //ATV5
        // Realiza a soma do valor total dos produtos da lista
        double total = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\n=== ATV5 - Soma Total dos Produtos ===");
        System.out.printf("Total: R$ %.2f%n", total);

        //ATV6
        // Ordena a lista de linguagens pelo tamanho do nome, da menor para a maior
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("\n=== ATV6 - Linguagens Ordenadas por Tamanho ===");
        System.out.println("Original  : " + linguagens);
        System.out.println("Ordenada  : " + linguagensOrdenadas);
    }
}