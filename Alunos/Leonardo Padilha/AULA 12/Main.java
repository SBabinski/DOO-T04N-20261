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
            return String.format("%s - R$ %.2f", nome, preco);
        }
    }

    public static void main(String[] args) {

        // ATV1
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> numerosPares = numeros.stream()
                .filter(numero -> (numero & 1) == 0)
                .collect(Collectors.toList());

        System.out.println("Números pares (Atividade 1): " + numerosPares);

        // ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(nome -> nome.toUpperCase())
                .collect(Collectors.toList());

        System.out.println("Nomes em maiúsculo (Atividade 2): " + nomesMaiusculos);

        // ATV3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        HashMap::new,
                        Collectors.counting()
                ));

        System.out.println("ATV3 - Contagem de palavras  (Atividade 3):" + contagemPalavras);

        // ATV4
        List<Produto> produtos = Arrays.asList(
                new Produto("Flor da Gabrielinha", 380.00),
                new Produto("Casa financiada", 150.00),
                new Produto("Mosquito", 900.00),
                new Produto("Carregador", 35.00)
        );

        List<Produto> produtosAcimaDe100 = produtos.stream()
                .filter(produto -> Double.compare(produto.getPreco(), 100.00) > 0)
                .collect(Collectors.toList());

        System.out.println("ATV4 - Produtos acima de R$ 100,00  (Atividade 4): " + produtosAcimaDe100);

        // ATV5
        double somaTotalProdutos = produtos.stream()
                .map(Produto::getPreco)
                .reduce(0.0, Double::sum);

        System.out.println("ATV5 - Soma total dos produtos  (Atividade 5): R$ " + somaTotalProdutos);

        // ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted((a, b) -> Integer.compare(a.length(), b.length()))
                .collect(Collectors.toList());

        System.out.println("ATV6 - Linguagens ordenadas por tamanho  (Atividade 6): " + linguagensOrdenadas);
    }
}