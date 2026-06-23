import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //ATV1
        // Stream API: filter() mantém apenas os elementos que satisfazem a condição
       
        List<Integer> numeros = List.of(1, 4, 7, 8, 13, 20, 33, 42, 55, 60);

        List<Integer> pares = numeros.stream()         // Cria um Stream a partir da lista
            .filter(n -> n % 2 == 0)                   // Mantém só os pares
            .collect(Collectors.toList());              // Coleta o resultado em uma nova lista

        System.out.println("ATV1 - Números pares: " + pares);


        //ATV2
        
        List<String> nomes = List.of("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
            .map(String::toUpperCase)                  // Converte cada nome para maiúsculas
            .collect(Collectors.toList());

        System.out.println("ATV2 - Nomes em maiúsculas: " + nomesMaiusculos);


        //ATV3
      
        List<String> palavras = List.of("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagem = palavras.stream()
            .collect(Collectors.groupingBy(
                p -> p,                                
                Collectors.counting()                  
            ));

        System.out.println("ATV3 - Contagem de palavras: " + contagem);


        //ATV4
        
        List<Produto> produtos = List.of(
            new Produto("Teclado",    80.00),
            new Produto("Monitor",   950.00),
            new Produto("Mouse",      45.00),
            new Produto("Headset",   180.00)
        );

        List<Produto> produtosCaros = produtos.stream()
            .filter(p -> p.getPreco() > 100.00)        // Mantém apenas preço > R$ 100,00
            .collect(Collectors.toList());

        System.out.print("ATV4 - Produtos acima de R$ 100,00: ");
        produtosCaros.forEach(p ->                     // forEach percorre cada elemento do Stream
            System.out.print(p.getNome() + " (R$ " + p.getPreco() + ")  ")
        );
        System.out.println();


        //ATV5
        
        double total = produtos.stream()
            .mapToDouble(Produto::getPreco)            
            .sum();                                    

        System.out.printf("ATV5 - Soma total dos produtos: R$ %.2f%n", total);


        //ATV6
       
        List<String> linguagens = List.of("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> ordenadas = linguagens.stream()
            .sorted(java.util.Comparator.comparingInt(String::length)) // Ordena pelo tamanho
            .collect(Collectors.toList());

        System.out.println("ATV6 - Linguagens por tamanho: " + ordenadas);
    }
}


// ================================================================
// CLASSE PRODUTO (usada na ATV4 e ATV5)
// Atributos: nome e preco
// Encapsulamento: atributos private, acessados via getters
// ================================================================
class Produto {

    private String nome;   // Nome do produto
    private double preco;  // Preço do produto em reais

    // Construtor: inicializa os atributos ao criar o objeto
    public Produto(String nome, double preco) {
        this.nome  = nome;
        this.preco = preco;
    }

    // Getters — necessários para o Stream conseguir acessar os valores
    public String getNome()  { return nome; }
    public double getPreco() { return preco; }
}
