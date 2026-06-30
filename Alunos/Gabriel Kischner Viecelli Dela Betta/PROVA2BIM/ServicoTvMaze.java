import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

// ta basicamente aqui ele vai o trabalho da api e tals, de buscar as coisa
//se for dar uma atribuicao é o funcionario do sistema

public class ServicoTvMaze {

    public List<Serie> buscarSeries(String nomeSerie) {

        List<Serie> series = new ArrayList<>();

        try {

            String endereco =
                    "https://api.tvmaze.com/search/shows?q="
                            + nomeSerie.replace(" ", "%20");

            URL url = new URL(endereco);

            HttpURLConnection conexao =
                    (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("GET");

            BufferedReader leitor =
                    new BufferedReader(
                            new InputStreamReader(
                                    conexao.getInputStream()
                            )
                    );

            StringBuilder resposta =
                    new StringBuilder();

            String linha;

            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }

            leitor.close();

            JsonArray resultados =
                    JsonParser.parseString(
                            resposta.toString()
                    ).getAsJsonArray();

            for (JsonElement item : resultados) {

                JsonObject show =
                        item.getAsJsonObject()
                                .getAsJsonObject("show");

                String nome =
                        obterTexto(show, "name");

                String idioma =
                        obterTexto(show, "language");

                String status =
                        obterTexto(show, "status");

                String estreia =
                        obterTexto(show, "premiered");

                String termino =
                        obterTexto(show, "ended");

                double nota = 0;

                if (show.has("rating")
                        && !show.get("rating").isJsonNull()) {

                    JsonObject rating =
                            show.getAsJsonObject("rating");

                    if (rating.has("average")
                            && !rating.get("average").isJsonNull()) {

                        nota =
                                rating.get("average")
                                        .getAsDouble();
                    }
                }

                List<String> generos =
                        new ArrayList<>();

                JsonArray listaGeneros =
                        show.getAsJsonArray("genres");

                for (JsonElement genero : listaGeneros) {

                    generos.add(
                            genero.getAsString()
                    );
                }

                String emissora =
                        "Não informada";

                if (show.has("network")
                        && !show.get("network").isJsonNull()) {

                    emissora =
                            show.getAsJsonObject("network")
                                    .get("name")
                                    .getAsString();
                }

                Serie serie =
                        new Serie(
                                nome,
                                idioma,
                                generos,
                                nota,
                                status,
                                estreia,
                                termino,
                                emissora
                        );

                series.add(serie);
            }

        }
        catch (Exception e) {

            e.printStackTrace();
        }

        return series;
    }

    private String obterTexto(
            JsonObject objeto,
            String campo) {

        if (objeto.has(campo)
                && !objeto.get(campo).isJsonNull()) {

            return objeto.get(campo)
                    .getAsString();
        }

        return "Não informado";
    }
}