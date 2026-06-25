package aula12;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class Principal {
public static void main(String[] args) {
	ajustaNomesProgramação();
	
	}
	
public static void printaPares() {
	List<Integer> numeros = Arrays.asList(5, 2, 14, 11, 22, 176, 94, 99, 1);
	List<Integer> pares = numeros.stream().filter(numero -> numero  % 2 == 0).collect(Collectors.toList());
	System.out.println("Lista de valores Pares : " + pares);
	
}

public static void capsLock() {
	List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
	List<String> maiusculoNomes = nomes.stream().map(String::toUpperCase).collect(Collectors.toList());
	System.out.println("Nomes : "+ maiusculoNomes);
}

public static void filtraPalavras() {
	String texto = "Hoje é sábado, e eu estava pensando que talvez tudo aconteça por algum motivo. Se eu começar algo novo agora, talvez os resultados apareçam mais para frente. Na quarta, lembrei de uma conversa que tivemos no sábado passado, e percebi como o tempo passa rápido. Se continuarmos nos esforçando, talvez consigamos alcançar objetivos que hoje parecem distantes. Afinal, entre uma quarta e um sábado, muita coisa pode mudar se houver dedicação.";
	List<String> palavrasFiltradas = Arrays.stream(texto.split(" "))
            .map(p -> p.replaceAll("[.,]", "").toLowerCase())
            .filter(p -> p.equals("se") || p.equals("talvez") || p.equals("hoje") || p.equals("sábado")|| p.equals("se")|| p.equals("quarta"))
            .toList();
	
System.out.println("Palavras : " + palavrasFiltradas);
}

public static void filtraProdutos() {
	 List<String> produtos = List.of(
             "Mouse-50",
             "Teclado-120",
             "Monitor-600",
             "Fone-90"
     );

     produtos.stream()
             .filter(p -> Double.parseDouble(p.split("-")[1]) > 100)
             .forEach(System.out::println);

     double soma = produtos.stream()
    	        .mapToDouble(p -> Double.parseDouble(p.split("-")[1]))
    	        .sum();
     
System.out.println("Agora a soma dos valores dos produtos :" + soma);


 }


public static void ajustaNomesProgramação() {
	List<String> nomes = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

	List<String> grandes = nomes.stream().sorted(Comparator.comparingInt(String::length)).toList();

	System.out.println(grandes);
}

}