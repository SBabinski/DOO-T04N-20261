package weather;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.Scanner;
import weather.Previsao;

public class main {

	static Scanner scan = new Scanner(System.in);
	static boolean d = false;
	
	public static void main(String[] args) throws Exception{
		Tela();
	}
	
	public static void Tela() {
		JFrame tela = new JFrame("clima");
		tela.setSize(1000, 500);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel consulta = new JPanel();
		JTextField cidade = new JTextField();
		cidade.setPreferredSize(new Dimension(100, 25));
		JLabel info = new JLabel("Insira a cidade a ser consultada aqui:");
		JButton butao = new JButton("Consultar");

		JPanel dados = new JPanel();
		String[] colunas = {"Cidade Requisitada", "Temperatura", "Temperatura Minima do Dia", "Temperatura Maxima do Dia", "Humidade do ar", 
				"Condições do Tempo", "Precipitação", "Direção do Vento", "Velocidade do Vento"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		JTable tabela = new JTable(modelo);
		JScrollPane tabelaScrolavel = new JScrollPane(tabela);
		dados.add(tabelaScrolavel);
		
		butao.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Consulta(cidade.getText());
					if (d) {
						modelo.addRow(new Object [] {Previsao.getResolvedAddress(), String.valueOf(Days.getTemp()), String.valueOf(Days.getTempmin()), String.valueOf(Days.getTempmax()), 
								String.valueOf(Days.getHumidity()), Days.getConditions(), String.valueOf(Days.getPrecip()), Days.DirecaoV(), String.valueOf(Days.getWindspeed())});
					}
				} catch (URISyntaxException e1) {
					ErrorMessage("Caracteres invalidos no campo de pesquisa");
					d = false;
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					d = false;
				} catch (IOException e1) {
					e1.printStackTrace();
					d = false;
				}
			}
		});
		
		consulta.add(info);
		consulta.add(cidade);
		consulta.add(butao);
		tela.add(consulta, BorderLayout.NORTH);
		tela.add(tabelaScrolavel, BorderLayout.CENTER);
		
		tela.setVisible(true);
		
	}
	
	public static void Consulta(String cidade) throws URISyntaxException, IOException, InterruptedException {
		try {
			LocalDateTime data = LocalDateTime.now();
			//String cidade = scan.nextLine();
			ObjectMapper mapper = new ObjectMapper();
			Previsao previsao = new Previsao();
			String token = System.getenv("weatherapi");
		
			HttpClient client = HttpClient.newHttpClient();
			URI url = new URI("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
					+ cidade.replace(" ", "") + "/" + data.truncatedTo(ChronoUnit.SECONDS) 
					+ "?key=" + token + "&unitGroup=metric&include=current&lang=pt");
		
			HttpRequest request = HttpRequest.newBuilder(url)
					.GET()
					.build();
		
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
			if (response.statusCode() == 200) {
				System.out.println(response.body());
				String json = response.body();
				previsao = mapper.readValue(json, Previsao.class);
				System.out.println(previsao.resumo());
				d = true;
			} else if(response.statusCode() == 400 || response.statusCode() == 404) {
				ErrorMessage("Cidade não encontrada, por favor tente novamente!");
				d = false;
			} else {
				System.out.println(response.statusCode() + " " + data + " " + token + " " + url);
				d = false;
			}
		} catch(IOException e) {
			System.out.println(e);
			ErrorMessage("Não foi possivel fazer a consulta do tempo");
			d = false;
		}
	}
	
	public static void ErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Erro não esperado", JOptionPane.ERROR_MESSAGE);
	}

}
