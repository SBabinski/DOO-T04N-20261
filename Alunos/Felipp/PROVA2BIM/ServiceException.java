package Fag.services;

// excecao usada quando ocorre algum problema ao falar com a api tvmaze
// (sem conexao, codigo de erro, json invalido, etc).
public class ServiceException extends Exception {

    public ServiceException(String mensagem) {
        super(mensagem);
    }

    public ServiceException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
