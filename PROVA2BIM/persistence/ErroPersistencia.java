package tvtracker.persistence; // pacote de persistência de dados

/**
 * Exceção personalizada lançada quando ocorre qualquer erro
 * ao salvar ou carregar dados no disco (leitura/escrita de arquivo JSON).
 *
 * Estende Exception (checked exception) para forçar o tratamento obrigatório.
 */
public class ErroPersistencia extends Exception { // herda de Exception

    /**
     * Construtor que recebe a mensagem e a causa original do erro.
     * Permite encadear exceções sem perder o stack trace da causa raiz.
     *
     * @param mensagem texto descritivo do erro para exibir ao usuário
     * @param causa    exceção original que gerou este erro (ex: IOException)
     */
    public ErroPersistencia(String mensagem, Throwable causa) {
        super(mensagem, causa); // repassa ambos para a classe pai (Exception)
    }
}
