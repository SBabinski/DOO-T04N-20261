package streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //ATV1
        Scanner scanner = new Scanner(System.in);
        List<Integer> numeros = new ArrayList<>();

        System.out.println("Digite os números (mínimo 8):");
        while (true) {
            System.out.print("Número " + (numeros.size() + 1) + ": ");
            while (!scanner.hasNextInt()) {
                System.out.print("Valor inválido. Digite um número inteiro: ");
                scanner.next();
            }
            numeros.add(scanner.nextInt());

            System.out.print("Deseja encerrar? (sim/não): ");
            String resposta = scanner.next();
            if (resposta.equalsIgnoreCase("sim")) {
                if (numeros.size() >= 8) {
                    break;
                }
                System.out.println("É necessário informar ao menos 8 números. Faltam " + (8 - numeros.size()) + ".");
            }
        }

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("ATV1 - Pares: " + pares);

        //ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("ATV2 - Maiúsculas: " + nomesMaiusculos);

        //ATV3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        System.out.println("ATV3 - Contagem: " + contagem);

        //ATV4
        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado", 150.00),
                new Produto("Mouse", 80.00),
                new Produto("Monitor", 900.00),
                new Produto("Mousepad", 45.00)
        );
        List<Produto> produtosCaros = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());
        System.out.println("ATV4 - Produtos acima de R$ 100,00:");
        produtosCaros.forEach(p -> System.out.println("  " + p.getNome() + " - R$ " + p.getPreco()));

        //ATV5
        double total = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("ATV5 - Valor total dos produtos: R$ " + total);

        //ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("ATV6 - Ordenadas por tamanho: " + ordenadas);
    }
}
