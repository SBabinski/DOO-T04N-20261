public class ApiException extends Exception{
    public ApiException(String mensagem){
        super(mensagem);
    }

    public ApiException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

    
}