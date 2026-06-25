

public class CalculadoraException extends Exception {

    private String codigoErro;

    public static final String DIVISAO_POR_ZERO  = "ERR_DIV_ZERO";
    public static final String ENTRADA_INVALIDA  = "ERR_ENTRADA_INVALIDA";
    public static final String CAMPO_VAZIO       = "ERR_CAMPO_VAZIO";

    public CalculadoraException(String mensagem, String codigoErro) {
       
        super(mensagem);
        this.codigoErro = codigoErro;
    }

    public String getCodigoErro() {
        return codigoErro;
    }
}
