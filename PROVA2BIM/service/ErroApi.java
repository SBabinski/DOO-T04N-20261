package tvtracker.service; // pacote de serviços

/**
 * Exceção personalizada lançada quando ocorre qualquer erro
 * na comunicação com a API TVMaze (rede, timeout, resposta inválida).
 *
 * Estende Exception (checked exception) para forçar o tratamento obrigatório.
 */
public class ErroApi extends Exception { // herda de Exception

    /**
     * Construtor que recebe apenas a mensagem descrevendo o erro.
     * @param mensagem descrição amigável do erro ocorrido
     */
    public ErroApi(String mensagem) {
        super(mensagem); // repassa a mensagem para a classe pai (Exception)
    }

    /**
     * Construtor que recebe a mensagem e a causa original do erro.
     * Útil para encadear exceções sem perder o stack trace original.
     * @param mensagem descrição amigável do erro
     * @param causa    exceção original que gerou este erro
     */
    public ErroApi(String mensagem, Throwable causa) {
        super(mensagem, causa); // repassa ambos para a classe pai
    }
}
