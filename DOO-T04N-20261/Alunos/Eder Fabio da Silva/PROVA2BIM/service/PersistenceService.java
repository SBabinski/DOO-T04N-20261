package com.tvtracker.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tvtracker.model.UserData;

/**
 * Responsável por salvar e carregar os dados do usuário em um arquivo JSON
 * local. O arquivo é gravado em: ${user.home}/dados.json
 */
public class PersistenceService {

    private static final String FILE_NAME = "dados.json";

    private final Gson gson;
    private final Path filePath;

    public PersistenceService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.filePath = Paths.get(System.getProperty("user.home"), FILE_NAME);
    }

    /**
     * Serializa o {@link UserData} para JSON e grava no arquivo local.
     *
     * @param userData Dados a persistir.
     * @throws IOException Se ocorrer erro ao escrever no arquivo.
     */
    public void save(UserData userData) throws IOException {
        String json = gson.toJson(userData);
        Files.writeString(filePath, json, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Lê o arquivo local e desserializa para {@link UserData}.
     *
     * @return Dados carregados, ou {@code null} se o arquivo não existir.
     * @throws IOException Se ocorrer erro ao ler o arquivo.
     */
    public UserData load() throws IOException {
        if (!Files.exists(filePath)) {
            return null;
        }
        String json = Files.readString(filePath, StandardCharsets.UTF_8);
        return gson.fromJson(json, UserData.class);
    }

    /**
     * Retorna o caminho absoluto do arquivo de dados.
     */
    public String getFilePath() {
        return filePath.toAbsolutePath().toString();
    }
}
