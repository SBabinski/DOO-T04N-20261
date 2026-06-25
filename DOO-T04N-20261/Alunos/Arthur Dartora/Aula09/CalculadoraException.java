
public class CalculadoraException extends Exception {

    public enum TipoErro {
        DIVISAO_POR_ZERO,
        ENTRADA_INVALIDA,
        CAMPO_VAZIO
    }

    private TipoErro tipoErro;

    public CalculadoraException(String mensagem, TipoErro tipoErro) {
        super(mensagem); 
                    
        this.tipoErro = tipoErro;
    }
    public TipoErro getTipoErro() {
        return tipoErro;
    }
}
