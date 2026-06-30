import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// salva tudo no PC pra nao perder nada 
public class GerenciadorDados {

    private static final String ARQUIVO_DADOS = "dados_usuario.json";

    // grava o perfil e as listas 
    public void salvarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DADOS))) {
            writer.write("{\n");
            writer.write("  \"nome\": \"" + usuario.getNome() + "\",\n");
            writer.write("  \"apelido\": \"" + usuario.getApelido() + "\",\n");
            
            escreverLista(writer, "favoritos", usuario.getFavoritos());
            writer.write(",\n");
            escreverLista(writer, "jaAssistidas", usuario.getJaAssistidas());
            writer.write(",\n");
            escreverLista(writer, "desejaAssistir", usuario.getDesejaAssistir());
            writer.write("\n}");
        } catch (IOException e) {
            System.err.println("vish, erro ao salvar: " + e.getMessage());
        }
    }

    // monta as listas bonitinhas no formato json 
    private void escreverLista(BufferedWriter writer, String nomeLista, List<Serie> lista) throws IOException {
        writer.write("  \"" + nomeLista + "\": [\n");
        for (int i = 0; i < lista.size(); i++) {
            Serie s = lista.get(i);
            writer.write("    {\n");
            writer.write("      \"name\": \"" + s.getName() + "\",\n");
            writer.write("      \"score\": " + s.getScore() + ",\n");
            writer.write("      \"status\": \"" + s.getStatus() + "\",\n");
            writer.write("      \"premiered\": \"" + s.getPremiered() + "\",\n");
            writer.write("      \"imageUrl\": \"" + s.getImageUrl() + "\"\n");
            writer.write("    }");
            
            if (i < lista.size() - 1) writer.write(",");
            writer.write("\n");
        }
        writer.write("  ]");
    }

    // le do PC quando abre o app 
    public Usuario carregarUsuario() {
        Usuario usuario = new Usuario(); 
        File arquivo = new File(ARQUIVO_DADOS);
        
        if (!arquivo.exists()) return usuario; // se for a primeira vez, devolve limpo

        try {
            String conteudoJson = new String(Files.readAllBytes(Paths.get(ARQUIVO_DADOS)));
            usuario.setFavoritos(lerLista(conteudoJson, "\"favoritos\": ["));
            usuario.setJaAssistidas(lerLista(conteudoJson, "\"jaAssistidas\": ["));
            usuario.setDesejaAssistir(lerLista(conteudoJson, "\"desejaAssistir\": ["));
        } catch (Exception e) {
            System.err.println("erro ao carregar: " + e.getMessage());
        }
        return usuario;
    }

    // remonta os objetos lendo o texto
    private List<Serie> lerLista(String jsonGeral, String marcadorInicio) {
        List<Serie> lista = new ArrayList<>();
        int inicioBloco = jsonGeral.indexOf(marcadorInicio);
        
        if (inicioBloco == -1) return lista;
        
        int fimBloco = jsonGeral.indexOf("]", inicioBloco);
        String blocoArray = jsonGeral.substring(inicioBloco, fimBloco);
        String[] objetosSerie = blocoArray.split("\\{");
        
        for (int i = 1; i < objetosSerie.length; i++) {
            Serie s = new Serie();
            String item = objetosSerie[i];
            
            s.setName(extrair(item, "\"name\": \""));
            s.setStatus(extrair(item, "\"status\": \""));
            s.setPremiered(extrair(item, "\"premiered\": \""));
            s.setImageUrl(extrair(item, "\"imageUrl\": \""));
            
            String scoreStr = extrairNum(item, "\"score\": ");
            try { s.setScore(Double.parseDouble(scoreStr)); } catch (Exception e) { s.setScore(0.0); }
            
            lista.add(s);
        }
        return lista;
    }

    private String extrair(String texto, String chave) {
        int inicio = texto.indexOf(chave);
        if (inicio != -1) {
            inicio += chave.length();
            int fim = texto.indexOf("\"", inicio);
            return texto.substring(inicio, fim);
        }
        return "N/A";
    }

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