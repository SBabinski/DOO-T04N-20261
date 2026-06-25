/** Erro ocorrido ao consultar a API de clima da Visual Crossing. */
public class ErroClima extends Exception {

    public ErroClima(String mensagem) {
        super(mensagem);
    }

    public ErroClima(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
