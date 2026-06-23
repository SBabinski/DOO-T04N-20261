import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static class Produto {
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

    public static void main(String[] args) {

        // ATV1
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> numerosPares = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .toList();

        System.out.println("ATV1 - Números pares: " + numerosPares);
        // ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println("ATV2 - Nomes em maiúsculo: " + nomesMaiusculos);
        // ATV3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));

        System.out.println("ATV3 - Contagem de palavras: " + contagemPalavras);
        // ATV4
        List<Produto> produtos = Arrays.asList(
                new Produto("Mouse", 80.00),
                new Produto("Teclado", 150.00),
                new Produto("Monitor", 900.00),
                new Produto("Cabo USB", 35.00)
        );

        List<Produto> produtosAcimaDe100 = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.00)
                .toList();

        System.out.println("ATV4 - Produtos acima de R$ 100,00: " + produtosAcimaDe100);
        // ATV5
        double somaTotalProdutos = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("ATV5 - Soma total dos produtos: R$ " + somaTotalProdutos);
        // ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();

        System.out.println("ATV6 - Linguagens ordenadas por tamanho: " + linguagensOrdenadas);
    }
}