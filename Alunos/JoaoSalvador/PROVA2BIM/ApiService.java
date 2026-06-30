package Fag.services;

import Fag.Serie;
import java.util.List;

/*
 * Interface simples para demonstrar o conteúdo estudado.
 * A classe TvMazeService implementa essa interface.
 */
public interface ApiService {
    List<Serie> buscarSeries(String nome) throws Exception;
}
