package MySeries;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Series {
	ArrayList<Show> show = new ArrayList<>();
	
	public ArrayList<Show> getShow() {
		return show;
	}

	public void setShow(ArrayList<Show> show) {
		this.show = show;
	}

	public Series() {
		
	}
	
	public String Emissora() {
		if(show.get(0).getNetwork()!=null) {
			return show.get(0).getNetwork().getEmissora().toString();
		} else {
			return "não televisionado";
		}
	}
	
	public String resumo() {
		return "Nome: " + show.get(0).getName() + ", Idioma: " + show.get(0).getLanguage() + ", Genero: " + show.get(0).getGenres() + 
				", Avaliação: " + show.get(0).getRating().getAverage() + ", Status: " + show.get(0).getStatus() + ", Data de Estreia: " + show.get(0).getPremiered() 
				+ ", Data de Encerramento:" + show.get(0).getEnded() + ", Emissora: " + Emissora();
		
	}
}
