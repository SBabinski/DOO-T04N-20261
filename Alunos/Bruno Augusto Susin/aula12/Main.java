import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //  ATIV1 

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> numerosPares = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("ATV1 - Números pares: " + numerosPares);



        // ATIV2 

        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("\nATV2 - Nomes em maiúsculo: " + nomesMaiusculos);



        //  ATIV3 

        List<String> palavras = Arrays.asList(
                "se", "talvez", "hoje", "sábado", "se", "quarta", "sábado"
        );

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()
                ));

        System.out.println("\nATV3 - Quantidade de palavras: " + contagemPalavras);



        // ATIV4 

        List<Produto> produtos = Arrays.asList(
                new Produto("iPhone 15", 3500.00),
                new Produto("Fone Bluetooth", 80.00),
                new Produto("Carregador Apple", 150.00),
                new Produto("iPad", 2340.00)
        );

        List<Produto> produtosCaros = produtos.stream()
                .filter(produto -> produto.getPreco() > 100)
                .collect(Collectors.toList());

        System.out.println("\nATV4 - Produtos acima de R$100:");

        produtosCaros.forEach(produto ->
                System.out.println(produto.getNome() + " - R$" + produto.getPreco())
        );



        // ATIV5 
        
        double valorTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\nATV5 - Valor total dos produtos: R$" + valorTotal);



        // ATIV6 

        List<String> linguagens = Arrays.asList(
                "Java",
                 "Python", 
                 "C", 
                 "JavaScript", 
                 "Ruby"
        );

        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());

        System.out.println("\nATV6 - Ordenado por tamanho: " + ordenadas);

    }

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
    }
}