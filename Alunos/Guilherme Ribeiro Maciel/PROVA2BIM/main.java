package MySeries;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class main {

	static Arquivo jsonArquivo = new Arquivo();
	static File arquivo = new File("Series.json");
	static String caminho = System.getenv("arquivoJson");
	static Path caminhoArquivo = Paths.get(caminho + arquivo);
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws JsonProcessingException {
		ConsultarArquivo();
		PegarNome();
		Consulta();
		ConsultaUnica();
		ConsultaUnica();
	}

	private static void Consulta() {
		try {
			String serie = scan.nextLine();
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
			HttpClient client = HttpClient.newHttpClient();
		
			String params = URLEncoder.encode(serie);
			URI url = new URI("https://api.tvmaze.com/search/shows?q=" + params);
		
			HttpRequest request = HttpRequest.newBuilder(url)
					.GET()
					.build();
			
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			if(response.statusCode() == 200) {
				System.out.println(response.body());
				String json = response.body();
				Series[] series1 = mapper.readValue(json, Series[].class);
				System.out.println(series1.length);
				for (int i = 0; i < series1.length; i++) {
					System.out.println(series1[i].resumo());
				}
			}
		} catch (URISyntaxException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void ConsultaUnica() {
		try {
			String serie = scan.nextLine();
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			
			HttpClient client = HttpClient.newHttpClient();
			
			String params = URLEncoder.encode(serie);
			URI url = new URI("https://api.tvmaze.com/shows/" + params);
			
			HttpRequest request = HttpRequest.newBuilder(url)
					.GET()
					.build();
			
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			if(response.statusCode() == 200) {
				System.out.println(response.body());
				String json = response.body();
				Show series1 = mapper.readValue(json, Show.class);
				System.out.println(series1.sla());
				jsonArquivo.setSeries1(series1);
				String jsonArchive = mapper.writeValueAsString(jsonArquivo);
				Files.writeString(caminhoArquivo, jsonArchive);
			}
		} catch (URISyntaxException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void PegarNome() throws JsonProcessingException {
		jsonArquivo.setUsuario(scan.nextLine());
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = mapper.writeValueAsString(jsonArquivo);
		try {
			Files.writeString(caminhoArquivo, json);
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void ConsultarArquivo() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		try {
			FileReader reader = new FileReader(caminho + arquivo);
			jsonArquivo = mapper.readValue(reader, Arquivo.class);
			System.out.println(jsonArquivo.resumo());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
