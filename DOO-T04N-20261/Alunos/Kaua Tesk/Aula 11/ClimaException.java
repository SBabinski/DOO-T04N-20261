// ================================================================
// CLASSE: ClimaException

public class ClimaException extends Exception {

    // Códigos de erro para identificar o tipo do problema
    public static final String CIDADE_NAO_ENCONTRADA = "ERR_CIDADE_NAO_ENCONTRADA";
    public static final String ERRO_CONEXAO          = "ERR_CONEXAO";
    public static final String CHAVE_INVALIDA        = "ERR_CHAVE_INVALIDA";
    public static final String RESPOSTA_INVALIDA     = "ERR_RESPOSTA_INVALIDA";

    private String codigoErro;

    
    public ClimaException(String mensagem, String codigoErro) {
        super(mensagem);
        this.codigoErro = codigoErro;
    }

    public String getCodigoErro() {
        return codigoErro;
    }
}
