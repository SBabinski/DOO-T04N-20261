import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        return nome + " (R$ " + preco + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        
        // ATV1
        List<Integer> numeros = Arrays.asList(15, 22, 8, 99, 4, 13, 10, 7, 42, 3);
        List<Integer> numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("ATV1 - Números pares: " + numerosPares);
        
        System.out.println("--------------------------------------------------");

        // ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("ATV2 - Nomes em maiúsculo: " + nomesMaiusculos);

        System.out.println("--------------------------------------------------");

        // ATV3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));
        System.out.println("ATV3 - Contagem de palavras: " + contagemPalavras);

        System.out.println("--------------------------------------------------");

        // ATV4
        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado Mecânico", 250.00),
                new Produto("Mouse Pad", 45.00),
                new Produto("Monitor 144hz", 1200.00),
                new Produto("Cabo HDMI", 30.00)
        );
        
        List<Produto> produtosAcima100 = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());
        System.out.println("ATV4 - Produtos acima de R$ 100,00: " + produtosAcima100);

        System.out.println("--------------------------------------------------");

        // ATV5
        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("ATV5 - Soma total dos produtos: R$ " + somaTotal);

        System.out.println("--------------------------------------------------");

        // ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("ATV6 - Linguagens ordenadas por tamanho: " + linguagensOrdenadas);
    }
}