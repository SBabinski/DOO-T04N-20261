import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import objects.Produto;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // ATV1
        List<Integer> numeros = new ArrayList<>();

        int contador = 1;
        int actualNumber = -1;

        System.out.println("Digite números inteiros:");
        System.out.println("A lista precisa ter pelo menos 8 números.");
        System.out.println("Depois de digitar pelo menos 8 números, digite 0 para parar.");

        while (actualNumber != 0 || numeros.size() < 8) {
            System.out.println("Digite o " + contador + "º número:");
            actualNumber = scan.nextInt();

            if (actualNumber == 0 && numeros.size() < 8) {
                System.out.println("Você ainda precisa digitar pelo menos 8 números.");
            } else if (actualNumber != 0) {
                numeros.add(actualNumber);
                contador++;
            }
        }

        List<Integer> numerosPares = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Lista de números digitados:");
        System.out.println(numeros);

        System.out.println("Lista de números pares:");
        System.out.println(numerosPares);


        // ATV2
        List<String> nomesMinusculos = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomesMinusculos.stream()
                .map(nome -> nome.toUpperCase())
                .collect(Collectors.toList());

        System.out.println("Lista de nomes em letras maiúsculas:");
        System.out.println(nomesMaiusculos);


        // ATV3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()
                ));

        System.out.println("Contagem de palavras:");
        System.out.println(contagemPalavras);


        // ATV4
        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado", 80.00),
                new Produto("Mouse", 120.00),
                new Produto("Monitor", 750.00),
                new Produto("Cabo USB", 35.00)
        );

        List<Produto> produtosAcimaDe100 = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("Produtos com preço maior que R$ 100,00:");
        produtosAcimaDe100.forEach(produto -> System.out.println(produto));


        // ATV5
        double somaTotalProdutos = produtos.stream()
                .mapToDouble(produto -> produto.getPreco())
                .sum();

        System.out.println("Soma total dos produtos:");
        System.out.println("R$ " + String.format("%.2f", somaTotalProdutos));


        // ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(palavra -> palavra.length()))
                .collect(Collectors.toList());

        System.out.println("Linguagens ordenadas da menor para a maior:");
        System.out.println(linguagensOrdenadas);

        scan.close();
    }
}