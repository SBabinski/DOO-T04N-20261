package tvtracker.service; // pacote de servicos

import java.util.ArrayList;      // implementacao de lista dinamica
import java.util.LinkedHashMap;  // mapa que preserva a ordem de insercao
import java.util.List;           // interface de lista generica
import java.util.Map;            // interface de mapa chave-valor

/**
 * Parser e serializador JSON implementado do zero, sem bibliotecas externas.
 * Suporta objetos, arrays, strings, numeros, booleanos e null.
 * Usado para interpretar as respostas da API e salvar dados no disco.
 */
public class JsonUtil { // classe utilitaria (sem instancias necessarias)

    private String texto; // texto JSON sendo analisado caractere por caractere
    private int    pos;   // posicao atual do cursor de leitura no texto

    // Construtor privado: a classe e usada apenas pelos metodos estaticos
    private JsonUtil(String texto) {
        this.texto = texto; // guarda o texto JSON a ser analisado
        this.pos   = 0;     // comeca lendo do primeiro caractere
    }

    // =========================================================
    // Metodos publicos estaticos de entrada
    // =========================================================

    /**
     * Analisa um texto JSON e retorna o valor Java correspondente.
     */
    public static Object analisar(String json) {
        return new JsonUtil(json.trim()).lerValor(); // cria instancia e le o valor raiz
    }

    /**
     * Analisa um JSON e retorna como Map (objeto JSON -> mapa Java).
     */
    @SuppressWarnings("unchecked") // suprime aviso de cast generico
    public static Map<String, Object> analisarObjeto(String json) {
        return (Map<String, Object>) analisar(json); // analisa e converte para mapa
    }

    /**
     * Analisa um JSON e retorna como List (array JSON -> lista Java).
     */
    @SuppressWarnings("unchecked") // suprime aviso de cast generico
    public static List<Object> analisarArray(String json) {
        return (List<Object>) analisar(json); // analisa e converte para lista
    }

    // =========================================================
    // Metodos privados de leitura (parsing)
    // =========================================================

    /**
     * Le o proximo valor JSON a partir da posicao atual do cursor.
     * Detecta automaticamente o tipo pelo primeiro caractere.
     */
    private Object lerValor() {
        pularEspacos();                                 // ignora espacos em branco
        if (pos >= texto.length()) return null;         // fim do texto: retorna null
        char c = texto.charAt(pos);                     // le o caractere atual
        if (c == '{') return lerObjeto();               // '{' indica objeto JSON
        if (c == '[') return lerArray();                // '[' indica array JSON
        if (c == '"') return lerString();               // '"' indica string JSON
        if (c == 't') { pos += 4; return Boolean.TRUE;  } // "true"  (4 chars)
        if (c == 'f') { pos += 5; return Boolean.FALSE; } // "false" (5 chars)
        if (c == 'n') { pos += 4; return null;          } // "null"  (4 chars)
        return lerNumero();                             // qualquer outro: numero
    }

    /**
     * Le um objeto JSON { "chave": valor, ... } e retorna como LinkedHashMap.
     */
    private Map<String, Object> lerObjeto() {
        Map<String, Object> mapa = new LinkedHashMap<>(); // mapa ordenado por insercao
        pos++; // avanca o cursor alem do '{' de abertura
        pularEspacos(); // ignora espacos apos '{'
        if (pos < texto.length() && texto.charAt(pos) == '}') { // objeto vazio {}
            pos++; // avanca alem do '}'
            return mapa; // retorna mapa vazio
        }
        while (pos < texto.length()) { // le pares chave:valor ate o fim
            pularEspacos();                 // ignora espacos antes da chave
            String chave = lerString();     // le a chave (sempre uma string JSON)
            pularEspacos();                 // ignora espacos antes de ':'
            pos++;                          // avanca o cursor alem do ':'
            Object valor = lerValor();      // le o valor (qualquer tipo JSON)
            mapa.put(chave, valor);         // adiciona o par ao mapa
            pularEspacos();                 // ignora espacos apos o valor
            if (pos >= texto.length()) break; // fim inesperado do texto
            char proximo = texto.charAt(pos); // proximo caractere delimitador
            if (proximo == '}') { pos++; break; } // '}' = fim do objeto
            if (proximo == ',') pos++;            // ',' = ha mais pares: avanca
        }
        return mapa; // retorna o mapa com todos os pares lidos
    }

    /**
     * Le um array JSON [ valor, valor, ... ] e retorna como ArrayList.
     */
    private List<Object> lerArray() {
        List<Object> lista = new ArrayList<>(); // lista para armazenar os valores
        pos++; // avanca o cursor alem do '[' de abertura
        pularEspacos(); // ignora espacos apos '['
        if (pos < texto.length() && texto.charAt(pos) == ']') { // array vazio []
            pos++; // avanca alem do ']'
            return lista; // retorna lista vazia
        }
        while (pos < texto.length()) { // le valores ate o fim do array
            lista.add(lerValor()); // le o proximo valor e adiciona a lista
            pularEspacos();        // ignora espacos apos o valor
            if (pos >= texto.length()) break; // fim inesperado do texto
            char proximo = texto.charAt(pos); // proximo caractere delimitador
            if (proximo == ']') { pos++; break; } // ']' = fim do array
            if (proximo == ',') pos++;            // ',' = ha mais valores: avanca
        }
        return lista; // retorna a lista com todos os valores
    }

    /**
     * Le uma string JSON entre aspas duplas.
     * Trata sequencias de escape como \n, \t, \" e \\.
     */
    private String lerString() {
        pos++; // avanca o cursor alem da aspas de abertura '"'
        StringBuilder sb = new StringBuilder(); // acumula os caracteres da string
        while (pos < texto.length()) { // le ate a aspas de fechamento
            char c = texto.charAt(pos++); // le o proximo caractere e avanca
            if (c == '"') break; // aspas de fechamento: fim da string
            if (c == '\\' && pos < texto.length()) { // barra invertida: escape
                char esc = texto.charAt(pos++); // le o caractere apos a barra
                if      (esc == '"')  sb.append('"');  // \" -> aspas duplas
                else if (esc == '\\') sb.append('\\'); // \\ -> barra invertida
                else if (esc == '/')  sb.append('/');  // \/ -> barra
                else if (esc == 'n')  sb.append('\n'); // \n -> nova linha
                else if (esc == 'r')  sb.append('\r'); // \r -> retorno de carro
                else if (esc == 't')  sb.append('\t'); // \t -> tabulacao
                else if (esc == 'u') { // sequencia unicode: u mais 4 digitos hex
                    String hex = texto.substring(pos, pos + 4); // le 4 digitos hex
                    sb.append((char) Integer.parseInt(hex, 16)); // converte para char
                    pos += 4; // avanca 4 posicoes alem dos digitos lidos
                } else sb.append(esc); // escape desconhecido: mantém o caractere
            } else {
                sb.append(c); // caractere normal: adiciona diretamente
            }
        }
        return sb.toString(); // retorna a string completa montada
    }

    /**
     * Le um numero JSON (inteiro ou decimal) a partir da posicao atual.
     */
    private Number lerNumero() {
        int inicio = pos; // marca o inicio do numero
        while (pos < texto.length()) { // avanca enquanto for parte do numero
            char c = texto.charAt(pos);
            // para ao encontrar delimitadores de JSON
            if (c == ',' || c == '}' || c == ']' || Character.isWhitespace(c)) break;
            pos++; // avanca o cursor
        }
        String numStr = texto.substring(inicio, pos); // extrai o texto do numero
        if (numStr.contains(".") || numStr.contains("e") || numStr.contains("E")) {
            return Double.parseDouble(numStr); // contem ponto ou expoente: e decimal
        }
        try {
            return Long.parseLong(numStr); // tenta como numero inteiro longo
        } catch (NumberFormatException e) {
            return Double.parseDouble(numStr); // fallback: tenta como decimal
        }
    }

    /**
     * Avanca o cursor ignorando todos os espacos em branco.
     */
    private void pularEspacos() {
        while (pos < texto.length() && Character.isWhitespace(texto.charAt(pos))) {
            pos++; // avanca enquanto o caractere atual for espaco em branco
        }
    }

    // =========================================================
    // Metodos auxiliares estaticos para acessar valores do mapa
    // =========================================================

    /** Retorna o valor de uma chave como String, ou null se nao existir. */
    public static String getString(Map<String, Object> obj, String chave) {
        Object v = obj.get(chave);                      // busca o valor pela chave
        return v instanceof String ? (String) v : null; // retorna so se for String
    }

    /** Retorna o valor de uma chave como Double, ou null se nao existir. */
    public static Double getDouble(Map<String, Object> obj, String chave) {
        Object v = obj.get(chave);                              // busca o valor
        return v instanceof Number ? ((Number) v).doubleValue() : null; // converte para Double
    }

    /** Retorna o valor de uma chave como Integer, ou null se nao existir. */
    public static Integer getInt(Map<String, Object> obj, String chave) {
        Object v = obj.get(chave);                            // busca o valor
        return v instanceof Number ? ((Number) v).intValue() : null; // converte para Integer
    }

    /** Retorna o valor de uma chave como Map aninhado, ou null se nao for mapa. */
    @SuppressWarnings("unchecked") // suprime aviso de cast generico
    public static Map<String, Object> getObjeto(Map<String, Object> obj, String chave) {
        Object v = obj.get(chave);                              // busca o valor
        return v instanceof Map ? (Map<String, Object>) v : null; // retorna so se for mapa
    }

    /** Retorna o valor de uma chave como List, ou null se nao for lista. */
    @SuppressWarnings("unchecked") // suprime aviso de cast generico
    public static List<Object> getArray(Map<String, Object> obj, String chave) {
        Object v = obj.get(chave);                           // busca o valor
        return v instanceof List ? (List<Object>) v : null; // retorna so se for lista
    }

    // =========================================================
    // Metodo de serializacao: escapa caracteres especiais
    // =========================================================

    /**
     * Escapa caracteres especiais para uso seguro dentro de strings JSON.
     * @param s a string a ser escapada
     * @return a string com caracteres especiais substituidos pelas sequencias JSON
     */
    public static String escapar(String s) {
        if (s == null) return "";                      // null vira string vazia
        return s.replace("\\", "\\\\")                // \ vira \\
                .replace("\"", "\\\"")                // " vira \"
                .replace("\n", "\\n")                 // nova linha vira \n
                .replace("\r", "\\r")                 // retorno de carro vira \r
                .replace("\t", "\\t");                // tabulacao vira \t
    }
}
