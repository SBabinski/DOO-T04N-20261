
import java.util.Scanner;

public class ClimaApp {
    public static void main(String[] args) {
        
    
        try (Scanner roma = new Scanner(System.in)) {
            
            ClimaService climaService = new ClimaService();

            System.out.println("========================================");
            System.out.println("       BEM-VINDO AO APP DE CLIMA        ");
            System.out.println("========================================");
            System.out.print("Digite o nome da cidade que deseja consultar: ");
            String cidade = roma.nextLine();

            System.out.println("\nBuscando dados na API do Visual Crossing...");
            
            ClimaInfo info = climaService.buscarClima(cidade);

            if (info != null) {
                System.out.println("\nPrevisão do tempo para: " + cidade.toUpperCase());
                info.exibirInformacoes();
            } else {
                System.out.println("Não foi possível processar a previsão do tempo.");
            }
            
        } 
    }
}