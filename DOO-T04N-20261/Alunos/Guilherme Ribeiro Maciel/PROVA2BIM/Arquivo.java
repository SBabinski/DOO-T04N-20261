package MySeries;

import java.util.ArrayList;

public class Arquivo extends Show{
	private String usuario;
	private ArrayList<Show> series = new ArrayList<>();
	
	public Arquivo() {
		
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public ArrayList<Show> getSeries() {
		return series;
	}
	
	public void setSeries1(Show series) {
		this.series.add(series);
	}
	
	public void setSeries(ArrayList<Show> series) {
		this.series = series;
	}
	
	public void AllSeries() {
		for(int i = 0; i < series.size(); i++) {
			System.out.println(getSeries().get(i).getName());
		}
	}
	
	public String resumo() {
		return "Usuario: " + getUsuario() + ", Serie: " + getSeries().get(0).getName();
	}
}
