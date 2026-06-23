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
        return "Produto: " + nome + " | Preço: R$ " + preco;
    }
}

public class Main {

    public static void main(String[] args) {

        //Atividade 1
        List<Integer> numeros = Arrays.asList(12, 17, 27, 34, 402, 520, 685, 700);

        List<Integer> numPar = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("ATV1");
        System.out.println("Números pares: " + numPar);


        //Atividade 2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("\nATV2");
        System.out.println("Nomes em maiúsculo: " + nomesMaiusculos);


        //Atividade 3
        List<String> palavras = Arrays.asList(
                "se",
                "talvez",
                "hoje",
                "sábado",
                "se",
                "quarta",
                "sábado"
        );

        Map<String, Long> contaPalavras = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()
                ));

        System.out.println("\nATV3");
        System.out.println("Contagem das palavras:");
        contaPalavras.forEach((palavra, quantidade) ->
                System.out.println(palavra + ": " + quantidade));


        //Atividade 4
        List<Produto> produtos = Arrays.asList(
                new Produto("Perfume", 120.00),
                new Produto("Macaneta", 160.00),
                new Produto("Bolinha", 1200.00),
                new Produto("Tenis", 160.00),
                new Produto("Retrato", 128.00)
        );

        List<Produto> produtosFil = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("\nATV4");
        System.out.println("Produtos com preço maior que R$100,00:");
        produtosFil.forEach(System.out::println);


        //Atividade 5
        double sTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\nATV5");
        System.out.println("Soma total dos produtos: R$ " + sTotal);


        //Atividade 6
        List<String> linguagens = Arrays.asList(
                "Java",
                "Python",
                "C",
                "JavaScript",
                "Ruby"
        );

        List<String> linguagensOrde = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("\nATV6");
        System.out.println("Linguagens ordenadas por tamanho:");
        System.out.println(linguagensOrde);
    }
}