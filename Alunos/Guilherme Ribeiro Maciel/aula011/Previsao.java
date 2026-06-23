package weather;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import weather.Days;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Previsao {
	
	private static String resolvedAddress;
	private ArrayList<Days> days = new ArrayList<>();
	
	
	public Previsao() {
		
	}

	public static String getResolvedAddress() {
		return resolvedAddress;
	}

	public void setResolvedAddress(String resolvedAddress) {
		this.resolvedAddress = resolvedAddress;
	}

	public ArrayList<Days> getDays() {
		return days;
	}

	public void setDays(ArrayList<Days> days) {
		this.days = days;
	}
	
	public String resumo() {
		return "Cidade: " + resolvedAddress + ", Temperatura Atual: " + days.get(0).getTemp() + ", Maxima do dia: " + days.get(0).getTempmax() 
				+ ", Minima do dia: " + days.get(0).getTempmin() + ", Humidade: " + days.get(0).getHumidity() + ", Condição do tempo: " + days.get(0).getConditions() 
				+ ", Precipitação de Chuva: " + days.get(0).getPrecip() + ", Velocidade do Vento: " + days.get(0).getWindspeed() + ", Direção do Vento: " + days.get(0).DirecaoV()
				;
	}
}
