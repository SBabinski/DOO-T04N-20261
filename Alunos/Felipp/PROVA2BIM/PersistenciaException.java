package Fag.services;

// excecao usada quando ocorre algum problema ao salvar ou carregar o json local.
public class PersistenciaException extends Exception {

    public PersistenciaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
