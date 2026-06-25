import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Aplicativo de console que mostra o clima de uma cidade usando a
 * Timeline Weather API da Visual Crossing.
 *
 * <h2>Chave de API</h2>
 * Por segurança, a chave NÃO fica no código. Ela é lida:
 * <ol>
 *   <li>do 2º argumento da linha de comando; ou</li>
 *   <li>da variável de ambiente {@code VISUALCROSSING_API_KEY}.</li>
 * </ol>
 *
 * <h2>Como executar</h2>
 * <pre>
 *   javac *.java
 *   export VISUALCROSSING_API_KEY="SUA_CHAVE"
 *   java Main "Cascavel, PR"
 *   # ou, de forma interativa:
 *   java Main
 * </pre>
 */
public class Main {

    private static final String VAR_CHAVE = "VISUALCROSSING_API_KEY";

    public static void main(String[] args) {
        // Acentos corretos em qualquer terminal.
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        String chave = lerChave(args);
        if (chave == null) {
            System.err.println("""
                    ERRO: chave de API não encontrada.
                    Defina a variável VISUALCROSSING_API_KEY ou passe a chave como 2º argumento:
                        java Main "Cascavel, PR" SUA_CHAVE
                    Crie uma conta gratuita em https://www.visualcrossing.com/sign-up""");
            System.exit(1);
        }

        String cidade = lerCidade(args);
        if (cidade == null || cidade.isBlank()) {
            System.err.println("ERRO: nenhuma cidade informada.");
            System.exit(1);
        }

        try {
            Tempo tempo = new Clima(chave).buscar(cidade);
            System.out.println();
            System.out.println(tempo.formatar());
        } catch (ErroClima e) {
            System.err.println("Não foi possível obter o clima: " + e.getMessage());
            System.exit(2);
        } catch (IllegalArgumentException e) {
            System.err.println("Entrada inválida: " + e.getMessage());
            System.exit(1);
        }
    }

    /** Pega a chave do 2º argumento ou da variável de ambiente. */
    private static String lerChave(String[] args) {
        if (args.length >= 2 && !args[1].isBlank()) {
            return args[1].trim();
        }
        String env = System.getenv(VAR_CHAVE);
        return (env != null && !env.isBlank()) ? env.trim() : null;
    }

    /** Pega a cidade do 1º argumento ou pergunta ao usuário. */
    private static String lerCidade(String[] args) {
        if (args.length >= 1 && !args[0].isBlank()) {
            return args[0].trim();
        }
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Digite a cidade (ex.: Cascavel, PR): ");
            return sc.hasNextLine() ? sc.nextLine().trim() : null;
        }
    }
}
