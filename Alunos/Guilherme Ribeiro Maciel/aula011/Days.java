package weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Days {
	private static float temp;
	private static float tempmin;
	private static float tempmax;
	private static float humidity;
	private static String conditions;
	private static float precip;
	private static float winddir;
	private static float windspeed;
	
	public Days() {
		
	}

	public static float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public static float getTempmin() {
		return tempmin;
	}

	public void setTempmin(float tempmin) {
		this.tempmin = tempmin;
	}

	public static float getTempmax() {
		return tempmax;
	}

	public void setTempmax(float tempmax) {
		this.tempmax = tempmax;
	}

	public static float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public static String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public static float getPrecip() {
		return precip;
	}

	public void setPrecip(float precip) {
		this.precip = precip;
	}

	public float getWinddir() {
		return winddir;
	}

	public void setWinddir(float winddir) {
		this.winddir = winddir;
	}

	public static float getWindspeed() {
		return windspeed;
	}

	public void setWindspeed(float windspeed) {
		this.windspeed = windspeed;
	}
	
	public static String DirecaoV() {
		if(winddir>337.5 || winddir<=22.5) {
			return "Norte";
		}else if(winddir>22.5 || winddir<=67.5) {
			return "Nordeste";
		}else if(winddir>67.5 || winddir<=112.5) {
			return "Leste";
		}else if(winddir>112.5 || winddir<=157.5) {
			return "Sudeste";
		}else if(winddir>157.5 || winddir<=202.5) {
			return "Sul";
		}else if(winddir>202.5 || winddir<=247.5) {
			return "Sudoeste";
		}else if(winddir>247.5 || winddir<=292.5) {
			return "Oeste";
		}else if(winddir>292.5 || winddir<=337.5) {
			return "Noroeste";
		}else {
			return "não foi possivel calcular direção do vento";
		}
	}
}
