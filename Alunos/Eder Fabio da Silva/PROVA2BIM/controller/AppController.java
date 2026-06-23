package com.tvtracker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.tvtracker.model.Show;
import com.tvtracker.model.UserData;
import com.tvtracker.service.DataSeeder;
import com.tvtracker.service.PersistenceService;
import com.tvtracker.service.TVMazeService;

/**
 * Controlador principal da aplicação. Intermedia a camada de visão com os
 * serviços de API e persistência, aplicando as regras de negócio.
 */
public class AppController {

    // Dados do usuário (nome, listas de séries)
    private UserData userData;
    // Serviço de busca na API TVMaze
    private final TVMazeService tvMazeService;
    // Serviço de persistência de dados
    private final PersistenceService persistenceService;

    // Construtor: inicializa serviços e carrega dados do usuário
    public AppController() {
        this.tvMazeService = new TVMazeService();
        this.persistenceService = new PersistenceService();
        loadData();
    }

    // Inicialização 
    private void loadData() {
        try {
            UserData loaded = persistenceService.load();
            if (loaded != null) {
                this.userData = loaded;
                this.userData.initNullLists();
            } else {
                // Primeira execução: cria usuário e pré-carrega séries populares
                this.userData = new UserData();
                DataSeeder.seed(this.userData);
            }
        } catch (IOException e) {
            // Se não conseguir ler, inicia com dados pré-carregados
            this.userData = new UserData();
            DataSeeder.seed(this.userData);
        }
    }

    // ── Usuário 
    public boolean isFirstRun() {
        return userData.getUsername() == null || userData.getUsername().isBlank();
    }

    //
    public String getUsername() {
        return userData.getUsername();
    }

    //
    public void setUsername(String username) {
        userData.setUsername(username);
        saveData();
    }

    // ── Busca 
    /**
     * Delega a busca ao TVMazeService. Deve ser chamado fora da EDT (via
     * SwingWorker).
     */
    public List<Show> searchShows(String query) throws IOException, InterruptedException {
        return tvMazeService.searchShows(query);
    }

    // ── Favoritos 
    // Adiciona à lista de favoritos e salva se houve adição
    public boolean addToFavorites(Show show) {
        if (!userData.getFavorites().contains(show)) {
            userData.getFavorites().add(show);
            saveData();
            return true;
        }
        return false;
    }

    // Remove da lista de favoritos e salva se houve remoção
    public boolean removeFromFavorites(Show show) {
        boolean removed = userData.getFavorites().remove(show);
        if (removed) {
            saveData();
        }
        return removed;
    }

    // Retorna uma nova lista de favoritos para evitar exposição direta
    public List<Show> getFavorites() {
        return new ArrayList<>(userData.getFavorites());
    }

    // ── Já assistidas 
    //
    public boolean addToWatched(Show show) {
        if (!userData.getWatched().contains(show)) {
            userData.getWatched().add(show);
            saveData();
            return true;
        }
        return false;
    }

    //
    public boolean removeFromWatched(Show show) {
        boolean removed = userData.getWatched().remove(show);
        if (removed) {
            saveData();
        }
        return removed;
    }

    //
    public List<Show> getWatched() {
        return new ArrayList<>(userData.getWatched());
    }

    // ── Quero assistir 
    // Adiciona à lista de quero assistir e salva se houve adição
    public boolean addToWatchlist(Show show) {
        if (!userData.getWatchlist().contains(show)) {
            userData.getWatchlist().add(show);
            saveData();
            return true;
        }
        return false;
    }

    // Remove da lista de quero assistir e salva se houve remoção
    public boolean removeFromWatchlist(Show show) {
        boolean removed = userData.getWatchlist().remove(show);
        if (removed) {
            saveData();
        }
        return removed;
    }

    // Retorna uma nova lista de quero assistir para evitar exposição direta
    public List<Show> getWatchlist() {
        return new ArrayList<>(userData.getWatchlist());
    }

    // ── Ordenação 
    /**
     * Retorna uma nova lista ordenada pelo critério informado, sem alterar a
     * original.
     *
     * @param shows Lista de séries a ordenar.
     * @param sortBy Critério: "Nome", "Nota", "Estado" ou "Estreia".
     * @return Nova lista ordenada.
     */
    // O método buildComparator constrói um comparador específico para o critério 
    // de ordenação escolhido.
    public List<Show> sortShows(List<Show> shows, String sortBy) {
        List<Show> sorted = new ArrayList<>(shows);
        Comparator<Show> comparator = buildComparator(sortBy);
        sorted.sort(comparator);
        return sorted;
    }

    // O método buildComparator retorna um comparador adequado para o critério de 
    // ordenação selecionado.
    private Comparator<Show> buildComparator(String sortBy) {
        if ("Nota".equals(sortBy)) {
            return Comparator.comparingDouble(Show::getRating).reversed();
        } else if ("Estado".equals(sortBy)) {
            return Comparator.comparing(Show::getStatus, String.CASE_INSENSITIVE_ORDER);
        } else if ("Estreia".equals(sortBy)) {
            return Comparator.comparing(
                    (Show s) -> s.getPremiered().equals("N/A") ? "9999" : s.getPremiered());
        } else {
            // padrão: Nome alfabético
            return Comparator.comparing(Show::getName, String.CASE_INSENSITIVE_ORDER);
        }
    }

    // ── Persistência 
    // Salva os dados do usuário usando o serviço de persistência. Falha silenciosa.
    private void saveData() {
        try {
            persistenceService.save(userData);
        } catch (IOException e) {
            // Falha silenciosa; a UI pode exibir aviso se necessário
        }
    }

    /**
     * Retorna o caminho do arquivo de dados para exibição na UI.
     */
    public String getDataFilePath() {
        return persistenceService.getFilePath();
    }
}
