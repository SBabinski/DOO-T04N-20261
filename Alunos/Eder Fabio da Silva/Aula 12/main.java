
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args) {

        //ATV1---------------------------------------------------------------------------
        //lista de números
        List<Integer> numeros = Arrays.asList(3, 8, 15, 22, 7, 14, 9, 30, 11, 42);

        //filtrar os números pares usando Streams e coletar em uma nova lista
        List<Integer> numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        //imprimir os números pares
        System.out.println("Números pares: " + numerosPares);

        //ATV2---------------------------------------------------------------------------
        //lista de nomes
        List<String> nomes = Arrays.asList("roberto", "jose", "caio", "vinicius");

        //usando stream api para convertor para maiuculas
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        //imprimir os nomes em maiúsculas
        System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);

        //ATV3---------------------------------------------------------------------------
        //lista de palavras
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sabado", "se");

        //contar quantas vezes cada palavra unica aparece em uma lista usando Stream API
        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        //imprimir a contagem de cada palavra
        System.out.println("Contagem de palavras: " + contagemPalavras);

        //ATV4---------------------------------------------------------------------------
        class Produto {

            private String nome;
            private double preco;

            //construtor
            public Produto(String nome, double preco) {
                this.nome = nome;
                this.preco = preco;
            }

            //getters
            public String getNome() {
                return nome;
            }

            public double getPreco() {
                return preco;
            }

            //sobrescrever o método toString para facilitar a impressão dos produtos
            @Override
            public String toString() {
                return nome + " - R$" + preco;
            }
        }
        //lista de produtos
        List<Produto> produtos = Arrays.asList(
                new Produto("Mouse", 80.00),
                new Produto("Teclado", 150.00),
                new Produto("Monitor", 600.00),
                new Produto("cabo HDMI", 50.00)
        );

        //filtrar os produtos com preço maior que 100 usando Stream API
        List<Produto> produtosCaros = produtos.stream()
                .filter(p -> p.getPreco() > 100)
                .collect(Collectors.toList());

        //imprimir os produtos caros
        System.out.println("Produtos com preço maior que R$ 100.00: " + produtosCaros);

        //ATV5---------------------------------------------------------------------------   
        //realize a soma ds preços de todos os produtos usando Stream API
        double somaPrecos = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        //imprimir a soma dos preços
        System.out.println("Soma dos preços dos produtos: R$ " + somaPrecos);

        //ATV6---------------------------------------------------------------------------
        //ordenar as palavras da menor para a maior usando Stream API
        List<String> palavrasOrdenadas = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        //ordenar as palavras usando Stream API e coletar em uma nova lista
        List<String> palavrasOrdenadasList = palavrasOrdenadas.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        //imprimir as palavras ordenadas
        System.out.println("Palavras ordenadas: " + palavrasOrdenadasList);
    }
}
