import java.util.ArrayList;
import java.util.List;

public class SerieService {

    private final TvMazeClient apiClient;
    private final UsuarioRepository repository;
    private Usuario usuario;

    public SerieService() {
        this.apiClient = new TvMazeClient();
        this.repository = new UsuarioRepository();
        this.usuario = new Usuario();
    }

    //Inicialização / Usuário

    public boolean possuiDadosSalvos() {
        return repository.existeDados();
    }

    public void carregarUsuario() throws PersistenceException {
        Usuario carregado = repository.carregar();
        if (carregado != null) {
            this.usuario = carregado;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getNomeUsuario() {
        return usuario.getNome();
    }

    public void definirNomeUsuario(String nome) throws PersistenceException {
        usuario.setNome(nome);
        salvar();
    }

    public void salvar() throws PersistenceException {
        repository.salvar(usuario);
    }

    public String getCaminhoArquivo() {
        return repository.getCaminhoArquivo().toString();
    }

    //Busca

    public List<Serie> buscarSeries(String termo) throws ApiException {
        return apiClient.buscarPorNome(termo);
    }

    //Gerenciamento de listas

    public boolean adicionarNaLista(TipoLista tipo, Serie serie) throws PersistenceException {
        List<Serie> lista = usuario.getLista(tipo);
        if (lista.contains(serie)) {
            return false;
        }
        lista.add(serie);
        salvar();
        return true;
    }

    public boolean removerDaLista(TipoLista tipo, Serie serie) throws PersistenceException {
        List<Serie> lista = usuario.getLista(tipo);
        boolean removida = lista.remove(serie);
        if (removida) {
            salvar();
        }
        return removida;
    }

    public boolean estaNaLista(TipoLista tipo, Serie serie) {
        return usuario.getLista(tipo).contains(serie);
    }

    public List<Serie> obterListaOrdenada(TipoLista tipo, SerieComparators.Criterio criterio) {
        List<Serie> copia = new ArrayList<>(usuario.getLista(tipo));
        if (criterio != null) {
            copia.sort(SerieComparators.deCriterio(criterio));
        }
        return copia;
    }

    public List<Serie> obterLista(TipoLista tipo) {
        return new ArrayList<>(usuario.getLista(tipo));
    }
}
