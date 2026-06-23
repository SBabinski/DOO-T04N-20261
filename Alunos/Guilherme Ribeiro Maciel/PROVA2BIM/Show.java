package MySeries;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Show {
	//ArrayList<Show> show = new ArrayList<>();
	public int id;
	public String name;
	public String language;
	public ArrayList<String> genres = new ArrayList<>();
	public Rating rating;
	public String status;
	public LocalDate premiered;
	public LocalDate ended;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Network network;

	public Show() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ArrayList<String> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getPremiered() {
		return premiered;
	}

	public void setPremiered(LocalDate premiered) {
		this.premiered = premiered;
	}

	public LocalDate getEnded() {
		return ended;
	}

	public void setEnded(LocalDate ended) {
		this.ended = ended;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String Emissora() {
		if(getNetwork()!=null) {
			return getNetwork().getEmissora().toString();
		} else {
			return "não televisionado";
		}
	}
	
	public String sla() {
		return "Nome: " + getName() + ", Idioma: " + getLanguage() + ", Genero: " + getGenres() + ", Avaliação: " + getRating().getAverage() 
				+ ", Status: " + getStatus() + ", Data de Estreia: " + getPremiered() + ", Data de Encerramento:" + getEnded() + ", Emissora: " + Emissora();
	}
}
