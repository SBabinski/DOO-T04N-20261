import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// classe pra salvar no pc
public class GerenciadorDados {

    // nome do arquivo de banco de dados fajuto
    private static final String ARQUIVO_DADOS = "dados_usuario.json";

    // monta o texto no formato json e salva no pc
    public void salvarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DADOS))) {
            writer.write("{\n");
            writer.write("  \"nome\": \"" + usuario.getNome() + "\",\n");
            writer.write("  \"apelido\": \"" + usuario.getApelido() + "\",\n");
            
            // salva cada lista separada chamando a funcao auxiliar ali em baixo
            escreverLista(writer, "favoritos", usuario.getFavoritos());
            writer.write(",\n");
            escreverLista(writer, "jaAssistidas", usuario.getJaAssistidas());
            writer.write(",\n");
            escreverLista(writer, "desejaAssistir", usuario.getDesejaAssistir());
            writer.write("\n}");
        } catch (IOException e) {
            System.err.println("erro ao salvar: " + e.getMessage());
        }
    }

    // ajuda a salvar os itens da lista no padrao json
    private void escreverLista(BufferedWriter writer, String nomeLista, List<Serie> lista) throws IOException {
        writer.write("  \"" + nomeLista + "\": [\n");
        for (int i = 0; i < lista.size(); i++) {
            Serie s = lista.get(i);
            
            // converte a lista de generos num texto pra salvar
            String generosStr = s.getGenres() != null ? String.join(",", s.getGenres()) : "";

            writer.write("    {\n");
            writer.write("      \"name\": \"" + s.getName() + "\",\n");
            writer.write("      \"language\": \"" + s.getLanguage() + "\",\n");
            writer.write("      \"genres\": \"" + generosStr + "\",\n");
            writer.write("      \"score\": " + s.getScore() + ",\n");
            writer.write("      \"status\": \"" + s.getStatus() + "\",\n");
            writer.write("      \"premiered\": \"" + s.getPremiered() + "\",\n");
            writer.write("      \"ended\": \"" + s.getEnded() + "\",\n");
            writer.write("      \"networkName\": \"" + s.getNetworkName() + "\",\n");
            writer.write("      \"imageUrl\": \"" + s.getImageUrl() + "\"\n");
            writer.write("    }");
            
            // nao bota virgula no ultimo item pra nao ter erro de sintaxe
            if (i < lista.size() - 1) writer.write(",");
            writer.write("\n");
        }
        writer.write("  ]");
    }

    // le o arquivo txt do pc pra preencher as listas quando o programa abre
    public Usuario carregarUsuario() {
        Usuario usuario = new Usuario();
        File arquivo = new File(ARQUIVO_DADOS);
        
        // se for a primeira vez e n tiver arquivo, so retorna o cara zerado
        if (!arquivo.exists()) return usuario;

        try {
            // puxa todo o texto do arquivo pra memoria
            String conteudoJson = new String(Files.readAllBytes(Paths.get(ARQUIVO_DADOS)));
            
            // alimenta as listas da classe com a funcao que varre o texto
            usuario.setFavoritos(lerLista(conteudoJson, "\"favoritos\": ["));
            usuario.setJaAssistidas(lerLista(conteudoJson, "\"jaAssistidas\": ["));
            usuario.setDesejaAssistir(lerLista(conteudoJson, "\"desejaAssistir\": ["));
        } catch (Exception e) {
            System.err.println("erro ao carregar dados: " + e.getMessage());
        }
        return usuario;
    }

    // recorta as chaves da nossa string salva pra criar os objetos serie dnv
    private List<Serie> lerLista(String jsonGeral, String marcadorInicio) {
        List<Serie> lista = new ArrayList<>();
        int inicioBloco = jsonGeral.indexOf(marcadorInicio);
        
        // se n achar a lista aborta
        if (inicioBloco == -1) return lista;
        
        int fimBloco = jsonGeral.indexOf("]", inicioBloco);
        String blocoArray = jsonGeral.substring(inicioBloco, fimBloco);
        String[] objetosSerie = blocoArray.split("\\{");
        
        for (int i = 1; i < objetosSerie.length; i++) {
            Serie s = new Serie();
            String item = objetosSerie[i];
            
            s.setName(extrair(item, "\"name\": \""));
            s.setLanguage(extrair(item, "\"language\": \""));
            s.setStatus(extrair(item, "\"status\": \""));
            s.setPremiered(extrair(item, "\"premiered\": \""));
            s.setEnded(extrair(item, "\"ended\": \""));
            s.setNetworkName(extrair(item, "\"networkName\": \""));
            s.setImageUrl(extrair(item, "\"imageUrl\": \""));
            
            // puxa os generos de volta pra uma lista do java
            String generosStr = extrair(item, "\"genres\": \"");
            List<String> listaGeneros = new ArrayList<>();
            if (!generosStr.equals("N/A") && !generosStr.trim().isEmpty()) {
                for (String g : generosStr.split(",")) {
                    listaGeneros.add(g.trim());
                }
            }
            s.setGenres(listaGeneros);
            
            // tenta converter a nota de volta pra double
            String scoreStr = extrairNum(item, "\"score\": ");
            try { s.setScore(Double.parseDouble(scoreStr)); } catch (Exception e) { s.setScore(0.0); }
            
            lista.add(s);
        }
        return lista;
    }

    // atalho q procura texto
    private String extrair(String texto, String chave) {
        int inicio = texto.indexOf(chave);
        if (inicio != -1) {
            inicio += chave.length();
            int fim = texto.indexOf("\"", inicio);
            return texto.substring(inicio, fim);
        }
        return "N/A";
    }

    // atalho q procura numero
    private String extrairNum(String texto, String chave) {
        int inicio = texto.indexOf(chave);
        if (inicio != -1) {
            inicio += chave.length();
            int fim = texto.indexOf(",", inicio);
            if(fim == -1) fim = texto.indexOf("\n", inicio);
            return texto.substring(inicio, fim).trim();
        }
        return "0.0";
    }
}