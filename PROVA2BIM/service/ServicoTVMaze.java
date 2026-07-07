package tvtracker.service; // pacote de serviços

import tvtracker.model.Serie; // modelo de série

import java.io.BufferedReader;       // leitura eficiente de texto linha a linha
import java.io.InputStreamReader;    // converte o fluxo de bytes em caracteres
import java.net.HttpURLConnection;   // conexão HTTP para chamadas de API REST
import java.net.URL;                 // representa e valida uma URL
import java.net.URLEncoder;          // codifica texto para uso seguro em URLs
import java.nio.charset.StandardCharsets; // conjunto de caracteres padrão UTF-8
import java.util.ArrayList;          // implementação de lista dinâmica
import java.util.List;               // interface de lista genérica
import java.util.Map;                // interface de mapa chave-valor

/**
 * Serviço responsável por se comunicar com a API pública TVMaze.
 * Faz requisições HTTP GET, recebe o JSON e converte em objetos Serie.
 * Documentação da API: https://www.tvmaze.com/api
 */
public class ServicoTVMaze { // classe de serviço de acesso à API

    private static final String URL_BASE   = "https://api.tvmaze.com"; // URL raiz da API TVMaze
    private static final int    TIMEOUT_MS = 8000; // tempo máximo de espera: 8 segundos

    /**
     * Busca séries de TV pelo nome usando o endpoint /search/shows da API.
     *
     * @param busca o nome (ou parte do nome) da série a ser buscada
     * @return lista de séries encontradas (pode ser vazia se nenhuma for encontrada)
     * @throws ErroApi se a busca estiver vazia, a conexão falhar ou a API retornar erro
     */
    public List<Serie> buscarPorNome(String busca) throws ErroApi {
        // valida que o campo de busca não está vazio ou apenas com espaços
        if (busca == null || busca.trim().isEmpty()) {
            throw new ErroApi("Digite um nome para buscar."); // erro amigável para o usuário
        }

        String buscaCodificada; // texto codificado para ser usado em URLs
        try {
            // codifica o texto: substitui espaços por %20, acentos por %XX, etc.
            buscaCodificada = URLEncoder.encode(busca.trim(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ErroApi("Erro ao preparar a busca: " + e.getMessage()); // falha na codificação
        }

        // monta a URL completa do endpoint de busca
        String urlBusca = URL_BASE + "/search/shows?q=" + buscaCodificada;

        // faz a requisição HTTP e obtém o corpo da resposta em JSON
        String json = fazerRequisicao(urlBusca);

        // converte o JSON recebido em uma lista de objetos Serie
        return converterResultados(json);
    }

    /**
     * Realiza uma requisição HTTP GET para a URL fornecida.
     * Abre a conexão, valida o status HTTP e lê o corpo da resposta.
     *
     * @param urlString a URL completa a ser requisitada
     * @return o corpo da resposta HTTP como texto (JSON)
     * @throws ErroApi se a conexão falhar ou o servidor retornar código de erro
     */
    private String fazerRequisicao(String urlString) throws ErroApi {
        HttpURLConnection conexao = null; // referência para a conexão (fechada no finally)
        try {
            URL url = new URL(urlString);                         // cria e valida a URL
            conexao = (HttpURLConnection) url.openConnection();   // abre a conexão HTTP
            conexao.setRequestMethod("GET");                      // define o método HTTP GET
            conexao.setConnectTimeout(TIMEOUT_MS);                // tempo limite para conectar
            conexao.setReadTimeout(TIMEOUT_MS);                   // tempo limite para ler a resposta
            conexao.setRequestProperty("Accept", "application/json"); // informa que aceita JSON

            int statusHttp = conexao.getResponseCode(); // lê o código de status HTTP da resposta
            if (statusHttp != 200) { // 200 = OK; qualquer outro código indica erro
                throw new ErroApi("A API retornou o erro HTTP: " + statusHttp);
            }

            // lê o corpo da resposta linha a linha de forma eficiente
            BufferedReader leitor = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(); // acumula todas as linhas
            String linha; // variável temporária para cada linha lida
            while ((linha = leitor.readLine()) != null) { // lê enquanto houver linhas
                sb.append(linha); // adiciona a linha ao resultado
            }
            leitor.close(); // fecha o leitor após a leitura completa

            return sb.toString(); // retorna o JSON completo como uma única string

        } catch (ErroApi e) {
            throw e; // relança erros já tratados por este método (sem modificar)
        } catch (Exception e) {
            // captura qualquer outro erro (sem internet, DNS, timeout, etc.)
            throw new ErroApi("Falha na conexão com a internet: " + e.getMessage());
        } finally {
            if (conexao != null) conexao.disconnect(); // SEMPRE fecha a conexão, mesmo com erro
        }
    }

    /**
     * Converte o JSON retornado pelo endpoint /search/shows em uma lista de Serie.
     * O JSON é um array onde cada item contém um objeto "score" e um objeto "show".
     *
     * @param json o texto JSON recebido da API
     * @return lista de objetos Serie populados com os dados da API
     * @throws ErroApi se o JSON estiver malformado ou não puder ser interpretado
     */
    @SuppressWarnings("unchecked") // suprime aviso do cast de Object para Map
    private List<Serie> converterResultados(String json) throws ErroApi {
        List<Serie> resultados = new ArrayList<>(); // lista que será preenchida e retornada
        try {
            List<Object> array = JsonUtil.analisarArray(json); // analisa o JSON como array
            if (array == null) return resultados;               // JSON vazio: retorna lista vazia

            for (Object elemento : array) { // percorre cada item do array
                Map<String, Object> item = (Map<String, Object>) elemento; // cada item é um objeto
                Map<String, Object> show = JsonUtil.getObjeto(item, "show"); // extrai o objeto "show"
                if (show != null) { // se o show foi encontrado no item
                    resultados.add(converterShow(show)); // converte e adiciona à lista
                }
            }
        } catch (Exception e) {
            // qualquer erro de parsing é convertido em ErroApi
            throw new ErroApi("Erro ao processar os dados da API: " + e.getMessage());
        }
        return resultados; // retorna a lista de séries montada
    }

    /**
     * Converte um único objeto JSON "show" em um objeto Serie.
     * Extrai cada campo do mapa e trata valores nulos com segurança.
     *
     * @param show mapa com os dados de uma série vindo da API
     * @return objeto Serie populado com os dados extraídos
     */
    private Serie converterShow(Map<String, Object> show) {
        // extrai o id numérico da série (usa 0 se não encontrar)
        Integer idObj = JsonUtil.getInt(show, "id");
        int id = idObj != null ? idObj : 0; // garante que o id nunca seja null

        // extrai campos simples de texto diretamente do mapa
        String nome    = JsonUtil.getString(show, "name");     // nome da série
        String idioma  = JsonUtil.getString(show, "language"); // idioma original
        String estado  = JsonUtil.getString(show, "status");   // estado atual
        String estreia = JsonUtil.getString(show, "premiered"); // data de estreia
        String termino = JsonUtil.getString(show, "ended");    // data de término

        // extrai a nota do objeto aninhado: show.rating.average
        Double nota = null; // começa como null (sem nota)
        Map<String, Object> rating = JsonUtil.getObjeto(show, "rating"); // objeto "rating"
        if (rating != null) { // se o objeto rating existir
            nota = JsonUtil.getDouble(rating, "average"); // extrai a média (pode ser null)
        }

        // extrai os gêneros do array "genres" e converte para lista de strings
        List<String> generos = new ArrayList<>(); // lista de gêneros
        List<Object> generosArr = JsonUtil.getArray(show, "genres"); // array JSON de gêneros
        if (generosArr != null) { // se o array existir
            for (Object g : generosArr) { // percorre cada gênero
                if (g instanceof String) { // garante que é uma string
                    generos.add((String) g); // adiciona o gênero à lista
                }
            }
        }

        // extrai o nome da emissora: tenta rede TV tradicional primeiro, depois canal web
        String emissora = "N/A"; // valor padrão se não encontrar emissora
        Map<String, Object> network = JsonUtil.getObjeto(show, "network"); // rede TV tradicional
        if (network != null) { // se for uma rede TV
            String nomeRede = JsonUtil.getString(network, "name"); // nome da rede
            if (nomeRede != null) emissora = nomeRede; // usa o nome da rede
        } else {
            Map<String, Object> webChannel = JsonUtil.getObjeto(show, "webChannel"); // canal web
            if (webChannel != null) { // se for um canal web (Netflix, Amazon, etc.)
                String nomeWeb = JsonUtil.getString(webChannel, "name"); // nome do canal
                if (nomeWeb != null) emissora = nomeWeb + " (Web)"; // indica que é web
            }
        }

        // extrai e limpa a sinopse: remove todas as tags HTML como <p>, <b>, <i>
        String sinopse = ""; // sinopse vazia por padrão
        String sinopseHtml = JsonUtil.getString(show, "summary"); // sinopse com HTML
        if (sinopseHtml != null) {
            sinopse = sinopseHtml.replaceAll("<[^>]*>", "").trim(); // remove tags e espaços extras
        }

        // extrai a URL da imagem de tamanho médio
        String urlImagem = null; // null se não houver imagem
        Map<String, Object> imagem = JsonUtil.getObjeto(show, "image"); // objeto "image"
        if (imagem != null) { // se houver imagem
            urlImagem = JsonUtil.getString(imagem, "medium"); // URL do tamanho médio
        }

        // cria e retorna o objeto Serie com todos os dados extraídos
        return new Serie(id, nome, idioma, generos, nota, estado,
                estreia, termino, emissora, sinopse, urlImagem);
    }
}
