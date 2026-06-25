package com.tvtracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contém todos os dados persistidos localmente para o usuário: nome/apelido e
 * as três listas de séries.
 */
public class UserData {

    private String username;
    private List<Show> favorites;
    private List<Show> watched;
    private List<Show> watchlist;

    // Construtores
    public UserData() {
        this.favorites = new ArrayList<>();
        this.watched = new ArrayList<>();
        this.watchlist = new ArrayList<>();
    }

    public UserData(String username) {
        this();
        this.username = username;
    }

    // ── Getters / Setters 
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Show> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Show> favorites) {
        this.favorites = favorites;
    }

    public List<Show> getWatched() {
        return watched;
    }

    public void setWatched(List<Show> watched) {
        this.watched = watched;
    }

    public List<Show> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<Show> watchlist) {
        this.watchlist = watchlist;
    }

    /**
     * Garante que nenhuma lista seja nula após desserialização.
     */
    public void initNullLists() {
        if (favorites == null) {
            favorites = new ArrayList<>();
        }
        if (watched == null) {
            watched = new ArrayList<>();
        }
        if (watchlist == null) {
            watchlist = new ArrayList<>();
        }
    }
}
