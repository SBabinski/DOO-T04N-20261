    import java.util.List;
    // Fala professor, vou fazer umas anotacao pra hora que eu for apresentar pra ti
    public class Serie {

        private String nome;
        private String idioma;
        private List<String> generos;
        private double nota;
        private String status;
        private String estreia;
        private String termino;
        private String emissora;

        public Serie(String nome,
                    String idioma,
                    List<String> generos,
                    double nota,
                    String status,
                    String estreia,
                    String termino,
                    String emissora) {

            this.nome = nome;
            this.idioma = idioma;
            this.generos = generos;
            this.nota = nota;
            this.status = status;
            this.estreia = estreia;
            this.termino = termino;
            this.emissora = emissora;
        }
        //construtor normal

        public String getNome() {
            return nome;
        }

        public String getIdioma() {
            return idioma;
        }

        public List<String> getGeneros() {
            return generos;
        }

        public double getNota() {
            return nota;
        }

        public String getStatus() {
            return status;
        }

        public String getEstreia() {
            return estreia;
        }

        public String getTermino() {
            return termino;
        }

        public String getEmissora() {
            return emissora;
        }

        @Override
        public String toString() {
            return nome;
        }
        
    }

    // basicamamente e a classe pra as informacoes guardar dentro de um objeto