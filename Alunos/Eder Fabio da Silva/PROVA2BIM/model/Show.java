package com.tvtracker.model;

import java.util.List;
import java.util.Objects;

/**
 * Representa uma série de televisão obtida da API TVMaze. Encapsula todos os
 * dados exibidos na interface e persistidos localmente.
 */
public class Show {

    private int id;
    private String name;
    private String language;
    private List<String> genres;
    private String status;
    private String premiered;
    private String ended;
    private double rating;
    private String networkName;

    // Construtores
    public Show() {
    }

    public Show(int id, String name, String language, List<String> genres,
            String status, String premiered, String ended,
            double rating, String networkName) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.genres = genres;
        this.status = status;
        this.premiered = premiered;
        this.ended = ended;
        this.rating = rating;
        this.networkName = networkName;
    }

    // ── Getters 
    public int getId() {
        return id;
    }

    public String getName() {
        return name != null ? name : "";
    }

    public String getLanguage() {
        return language != null ? language : "N/A";
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getStatus() {
        return status != null ? status : "N/A";
    }

    public String getPremiered() {
        return premiered != null ? premiered : "N/A";
    }

    public String getEnded() {
        return ended != null ? ended : "N/A";
    }

    public double getRating() {
        return rating;
    }

    public String getNetworkName() {
        return networkName != null ? networkName : "N/A";
    }

    // ── Setters 
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    // ── Métodos auxiliares 
    // Retorna os gêneros como uma string separada por vírgulas ou "N/A" se não houver.
    public String getGenresAsString() {
        if (genres == null || genres.isEmpty()) {
            return "N/A";
        }
        return String.join(", ", genres);
    }

    // Retorna a nota formatada ou "N/A" se não houver avaliação.
    public String getRatingAsString() {
        return rating > 0 ? String.format("%.1f", rating) : "N/A";
    }

    // Retorna o ano de estreia ou "?" se a data for desconhecida ou inválida.
    public String getYearPremiered() {
        if (premiered == null || premiered.equals("N/A")) {
            return "?";
        }
        return premiered.length() >= 4 ? premiered.substring(0, 4) : premiered;
    }

    // ── equals/hashCode por ID 
    // Dois shows são considerados iguais se tiverem o mesmo ID, garantindo que a 
    // comparação e remoção na watchlist funcionem corretamente.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Show)) {
            return false;
        }
        Show other = (Show) o;
        return this.id == other.id;
    }

    // O hashCode é baseado apenas no ID, garantindo consistência com equals.
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Representação para exibição em listas. Exibe nome e ano de estreia.
    @Override
    public String toString() {
        return name + " (" + getYearPremiered() + ")";
    }
}
