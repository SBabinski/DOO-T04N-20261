import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;

public class GerenciadorJson {

    // salva os dados do usuario em json
    public static void salvar(Usuario usuario) {

        try {

            Gson gson =
                    new GsonBuilder()
                            .setPrettyPrinting()
                            .create();

            FileWriter writer =
                    new FileWriter("usuario.json");

            gson.toJson(
                    usuario,
                    writer
            );

            writer.close();

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }

    // carrega os dados do usuario do json
    public static Usuario carregar() {

        try {

            Gson gson =
                    new Gson();

            FileReader reader =
                    new FileReader("usuario.json");

            Usuario usuario =
                    gson.fromJson(
                            reader,
                            Usuario.class
                    );

            reader.close();

            return usuario;

        }
        catch (Exception e) {

            return null;

        }

    }

}
