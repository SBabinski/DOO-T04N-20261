import java.util.*;
import java.util.stream.Collectors;

public class ListaDeExercicios{

    public static void main(String[] args) {

        // ATV1
        List<Integer> numeros = Arrays.asList(10, 15, 22, 33, 44, 55, 66, 77);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("ATV1 - Números pares: " + pares);


        // ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("ATV2 - Nomes em maiúsculo: " + nomesMaiusculos);


        // ATV3
        List<String> palavras = Arrays.asList(
                "se", "talvez", "hoje", "sábado",
                "se", "quarta", "sábado"
        );

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()
                ));

        System.out.println("ATV3 - Contagem de palavras:");
        contagemPalavras.forEach((palavra, quantidade) ->
                System.out.println(palavra + " = " + quantidade));


        // ATV4
        List<Produto> produtos = Arrays.asList(
                new Produto("Mouse", 80.00),
                new Produto("Teclado", 120.00),
                new Produto("Monitor", 900.00),
                new Produto("Headset", 150.00)
        );

        List<Produto> produtosAcimaDe100 = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("\nATV4 - Produtos acima de R$100,00:");
        produtosAcimaDe100.forEach(System.out::println);


        // ATV5
        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\nATV5 - Soma total dos produtos: R$ " + somaTotal);


        // ATV6
        List<String> linguagens = Arrays.asList(
                "Java", "Python", "C", "JavaScript", "Ruby"
        );

        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("\nATV6 - Linguagens ordenadas por tamanho:");
        System.out.println(ordenadas);
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
        return nome + " - R$ " + preco;
    }
}