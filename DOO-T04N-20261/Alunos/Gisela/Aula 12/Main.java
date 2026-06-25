import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        //mostrar pares
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> pares = numeros.stream()
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList());

       System.out.println("ATV1 - Pares: " + pares);

        //captalizar nomes
       List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
       List<String> nomesCaps = nomes.stream()
       .map(String::toUpperCase)
       .collect(Collectors.toList());

       System.out.println("ATV2 - Nomes captalizados: " + nomesCaps);

       //palavras únicas
       List<String> palavras = Arrays.asList("se","talvez","hoje","sábado","se","quarta","sábado");
       Map<String, Long> contagem = palavras.stream()
       .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        System.out.println("ATV3 - Contagem de palavras: " + contagem);

       //filtro e produto
       List<Produto> produtos = Arrays.asList(
        new Produto("Notebook", 3500.0),
        new Produto("Mouse", 80.0),
        new Produto("Teclado", 150.0),
        new Produto("Monitor", 900.0)
       );
       List<Produto> caros = produtos.stream()
       .filter(p -> p.getPreco() > 100.0)
       .collect(Collectors.toList());
       System.out.println("ATV4 - Produtos acima de R$100: " + caros);

        //soma dos preços
        double somaPrecos = produtos.stream()
        .mapToDouble(Produto::getPreco)
        .sum();
        System.out.println("ATV5 - Soma dos preços: R$" + somaPrecos);

        // ATV6 - Ordenar lista por tamanho da palavra
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadas = linguagens.stream()
        .sorted(Comparator.comparingInt(String::length))
        .collect(Collectors.toList());
        System.out.println("ATV6 - Ordenadas por tamanho: " + ordenadas);
    }
}

// Classe
class Produto {
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
        return nome + " - R$" + preco;
    }
}
