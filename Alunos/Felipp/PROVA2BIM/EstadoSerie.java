package Fag;

// estado da serie, usado para exibir e ordenar de forma amigável.
public enum EstadoSerie {

    EM_EXIBICAO("ainda em exibição"),
    CONCLUIDA("concluída"),
    CANCELADA("cancelada"),
    DESCONHECIDO("desconhecido");

    private final String descricao;

    EstadoSerie(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // converte o status textual da api tvmaze para o nosso enum.
    public static EstadoSerie apartirDaApi(String status) {
        if (status == null) {
            return DESCONHECIDO;
        }
        switch (status.toLowerCase()) {
            case "running":
                return EM_EXIBICAO;
            case "ended":
                return CONCLUIDA;
            default:
                return DESCONHECIDO;
        }
    }
}
