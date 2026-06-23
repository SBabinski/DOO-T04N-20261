public class WeatherApiException extends Exception {

    private int codigoHttp;

    public WeatherApiException(String mensagem) {
        super(mensagem);
        this.codigoHttp = -1;
    }

    public WeatherApiException(String mensagem, int codigoHttp) {
        super(mensagem);
        this.codigoHttp = codigoHttp;
    }

    public WeatherApiException(String mensagem, Throwable causa) {
        super(mensagem, causa);
        this.codigoHttp = -1;
    }

    public int getCodigoHttp() {
        return codigoHttp;
    }
}
