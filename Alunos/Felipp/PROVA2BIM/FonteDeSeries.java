package Fag;

import Fag.services.ServiceException;
import java.util.List;

// interface que define "de onde" buscamos series por nome.
public interface FonteDeSeries {

    List<Serie> buscarPorNome(String termo) throws ServiceException;
}
