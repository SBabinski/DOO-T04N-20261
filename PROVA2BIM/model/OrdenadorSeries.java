package tvtracker.model; 

import java.util.Comparator;


public class OrdenadorSeries { //classe utilitária de ordenação

    public enum TipoOrdenacao { //enumeração dos critérios

        ALFABETICA ("Alfabética (A-Z)"),
        NOTA       ("Nota (maior primeiro)"),
        ESTADO     ("Estado"),              
        ESTREIA    ("Data de estreia");    

        private final String rotulo; // texto exibido no JComboBox da interface

        // Construtor do enum: define o rótulo de cada valor
        TipoOrdenacao(String rotulo) {
            this.rotulo = rotulo; // guarda o texto de exibição
        }
        
        @Override
        public String toString() {
            return rotulo; // retorna o texto configurado no construtor
        }
    }

/**
Retorna um Comparator para o tipo de ordenação escolhido.
@param tipo critério de ordenação
@return Comparator correspondente
*/
     public static Comparator<Serie> por(TipoOrdenacao tipo) {
        switch (tipo) { // seleciona o comparator com base no tipo

            case ALFABETICA: // ordena pelo nome ignorando maiúsculas e minúsculas
                return Comparator.comparing(s -> s.getNome().toLowerCase());

            case NOTA: // ordena pela nota do maior para o menor (ordem decrescente)
                return (a, b) -> {
                    double na = a.getNota() != null ? a.getNota() : 0.0; // nota de 'a' (0 se nula)
                    double nb = b.getNota() != null ? b.getNota() : 0.0; // nota de 'b' (0 se nula)
                    return Double.compare(nb, na); // inverte para ordem decrescente
                };

            case ESTADO: // ordena pelo estado: em exibição primeiro, encerrada por último
                return Comparator.comparingInt(s -> prioridadeEstado(s.getEstado()));

            case ESTREIA: // ordena pela data de estreia mais recente primeiro
                return (a, b) -> {
                    String da = a.getEstreia() != null ? a.getEstreia() : ""; // data de 'a'
                    String db = b.getEstreia() != null ? b.getEstreia() : ""; // data de 'b'
                    return db.compareTo(da); // inverte para ordem decrescente (mais recente primeiro)
                };

            default: // caso não reconhecido: ordena alfabeticamente como padrão seguro
                return Comparator.comparing(s -> s.getNome().toLowerCase());
        }
    }

/**
Define a prioridade de ordenação para um estado.
@param estado estado da série
@return prioridade
*/
    private static int prioridadeEstado(String estado) {
        if (estado == null)                        return 99; // sem estado: vai para o fim
        if (estado.equals("Running"))              return 0;  // em exibição: primeiro
        if (estado.equals("To Be Determined"))     return 1;  // a definir: segundo
        if (estado.equals("In Development"))       return 2;  // em desenvolvimento: terceiro
        if (estado.equals("Ended"))                return 3;  // encerrada: último
        return 99; // outros valores desconhecidos: vai para o fim
    }
}
