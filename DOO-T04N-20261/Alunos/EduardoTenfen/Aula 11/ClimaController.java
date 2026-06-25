package clima;

public class ClimaController {

    private final ClimaApiService apiService;
    private final ClimaParser parser;
    private final ClimaVizualizador visualizador;

  
    public ClimaController(ClimaApiService apiService,
                           ClimaParser parser,
                           ClimaVizualizador visualizador) {
        this.apiService   = apiService;
        this.parser       = parser;
        this.visualizador = visualizador;
    }

    
    public void buscarEExibir(String cidade) {
        visualizador.exibirCarregando(cidade);

        try {
            // 1. Requisição à API
            String json = apiService.buscarClimaJson(cidade);

            // 2. Parse do JSON para objeto de domínio
            ClimaData dados = parser.parse(json, cidade);

            // 3. Exibição formatada
            visualizador.exibir(dados);

        } catch (IllegalArgumentException e) {
            visualizador.exibirErro("Entrada inválida: " + e.getMessage());
        } catch (Exception e) {
            visualizador.exibirErro(e.getMessage());
        }
    }
}
