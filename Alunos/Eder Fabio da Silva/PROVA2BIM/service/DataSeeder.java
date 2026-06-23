package com.tvtracker.service;

import java.util.List;

import com.tvtracker.model.Show;
import com.tvtracker.model.UserData;

/**
 * Popula as listas do usuário com dados pré-carregados na primeira execução,
 * permitindo demonstrar todas as funcionalidades sem necessidade de buscar
 * manualmente.
 */
public class DataSeeder {

    /**
     * Preenche as três listas de {@code userData} com séries populares. Deve
     * ser chamado apenas quando não há arquivo de dados salvo.
     */
    public static void seed(UserData userData) {
        seedFavorites(userData);
        seedWatched(userData);
        seedWatchlist(userData);
    }

    // ── Favoritos 
    private static void seedFavorites(UserData userData) {
        userData.getFavorites().addAll(List.of(
                new Show(169, "Breaking Bad",
                        "English",
                        List.of("Drama", "Crime", "Thriller"),
                        "Ended", "2008-01-20", "2013-09-29",
                        9.3, "AMC"),
                new Show(82, "Game of Thrones",
                        "English",
                        List.of("Drama", "Adventure", "Fantasy"),
                        "Ended", "2011-04-17", "2019-05-19",
                        9.3, "HBO"),
                new Show(2993, "Stranger Things",
                        "English",
                        List.of("Drama", "Horror", "Fantasy"),
                        "Ended", "2016-07-15", "2025-11-26",
                        8.7, "Netflix (Web)"),
                new Show(1871, "The Wire",
                        "English",
                        List.of("Drama", "Crime", "Thriller"),
                        "Ended", "2002-06-02", "2008-03-09",
                        9.3, "HBO"),
                new Show(180, "Chernobyl",
                        "English",
                        List.of("Drama", "History", "Thriller"),
                        "Ended", "2019-05-06", "2019-06-03",
                        9.4, "HBO")
        ));
    }

    // ── Já assistidas 
    private static void seedWatched(UserData userData) {
        userData.getWatched().addAll(List.of(
                new Show(526, "The Office",
                        "English",
                        List.of("Comedy"),
                        "Ended", "2005-03-24", "2013-05-16",
                        8.7, "NBC"),
                new Show(431, "Friends",
                        "English",
                        List.of("Comedy", "Romance"),
                        "Ended", "1994-09-22", "2004-05-06",
                        8.5, "NBC"),
                new Show(66, "The Big Bang Theory",
                        "English",
                        List.of("Comedy", "Romance"),
                        "Ended", "2007-09-24", "2019-05-16",
                        7.7, "CBS"),
                new Show(4614, "Black Mirror",
                        "English",
                        List.of("Drama", "Science-Fiction", "Thriller"),
                        "Running", "2011-12-04", null,
                        8.4, "Channel 4"),
                new Show(143, "Prison Break",
                        "English",
                        List.of("Drama", "Crime", "Action"),
                        "Ended", "2005-08-29", "2017-05-30",
                        8.0, "FOX")
        ));
    }

    // ── Quero assistir 
    private static void seedWatchlist(UserData userData) {
        userData.getWatchlist().addAll(List.of(
                new Show(37854, "The Last of Us",
                        "English",
                        List.of("Drama", "Action", "Horror"),
                        "Running", "2023-01-15", null,
                        8.7, "HBO"),
                new Show(38587, "Severance",
                        "English",
                        List.of("Drama", "Science-Fiction", "Thriller"),
                        "Running", "2022-02-18", null,
                        8.7, "Apple TV+ (Web)"),
                new Show(63923, "The Bear",
                        "English",
                        List.of("Drama", "Comedy"),
                        "Running", "2022-06-23", null,
                        8.5, "FX"),
                new Show(41686, "Andor",
                        "English",
                        List.of("Action", "Adventure", "Science-Fiction"),
                        "Ended", "2022-09-21", "2024-11-19",
                        8.8, "Disney+ (Web)"),
                new Show(1065, "Peaky Blinders",
                        "English",
                        List.of("Drama", "Crime", "History"),
                        "Ended", "2013-09-12", "2022-04-03",
                        8.8, "BBC One")
        ));
    }
}
