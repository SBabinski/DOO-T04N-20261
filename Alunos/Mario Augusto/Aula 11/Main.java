import java.util.Scanner;

/**
 * Ponto de entrada do aplicativo de consulta de clima.
 *
 * <p><b>Pré-requisito:</b> defina a variável de ambiente
 * {@code VISUALCROSSING_API_KEY} com sua chave gratuita obtida em
 * <a href="https://www.visualcrossing.com/sign-up">visualcrossing.com/sign-up</a>
 * antes de executar o programa.</p>
 *
 * <p><b>Como compilar e executar:</b></p>
 * <pre>
 *   # Linux / macOS
 *   export VISUALCROSSING_API_KEY=SUA_CHAVE_AQUI
 *   javac *.java
 *   java Main
 *
 *   # Windows (CMD)
 *   set VISUALCROSSING_API_KEY=SUA_CHAVE_AQUI
 *   javac *.java
 *   java Main
 * </pre>
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║      Consulta de Clima — Visual Crossing ║");
        System.out.println("╚══════════════════════════════════════════╝");

        WeatherApiClient cliente;
        try {
            cliente = new WeatherApiClient();
        } catch (IllegalStateException e) {
            System.err.println("[ERRO] " + e.getMessage());
            System.exit(1);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String cidade = lerCidade(scanner, args);

        System.out.println("\nBuscando dados para: " + cidade + " ...\n");

        try {
            WeatherData dados = cliente.buscarClima(cidade);
            System.out.println(dados);
        } catch (Exception e) {
            System.err.println("[ERRO] Não foi possível obter os dados do clima.");
            System.err.println("Detalhe: " + e.getMessage());
            System.exit(2);
        }

        scanner.close();
    }

    /**
     * Obtém o nome da cidade: primeiro tenta o argumento de linha de comando;
     * se não houver, solicita ao usuário via terminal.
     */
    private static String lerCidade(Scanner scanner, String[] args) {
        if (args.length > 0 && !args[0].isBlank()) {
            return args[0].trim();
        }
        System.out.print("Digite o nome da cidade (ex.: Cascavel,PR,Brasil): ");
        String entrada = scanner.nextLine().trim();
        if (entrada.isEmpty()) {
            System.out.println("Nenhuma cidade informada. Usando 'Cascavel,PR,Brasil' como padrão.");
            return "Cascavel,PR,Brasil";
        }
        return entrada;
    }
}
