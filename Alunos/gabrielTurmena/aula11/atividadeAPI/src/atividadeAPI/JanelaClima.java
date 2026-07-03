package atividadeAPI;

import javax.swing.*;
import java.awt.*;

public class JanelaClima extends JFrame {

    public JanelaClima(
            String cidade,
            double temperatura,
            double tempMax,
            double tempMin,
            double humidade,
            String condicao,
            double precipitacao,
            double velocidadeVento,
            String icone) {

        setTitle("Clima");

        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        JLabel desenho = new JLabel("Ícone: " + icone);

        JTextArea info = new JTextArea();

        info.setEditable(false);

        info.setFont(new Font("Arial", Font.BOLD, 16));

        info.setText(
                "Cidade: " + cidade + "\n\n" +
                "Temperatura: " + temperatura + " °C\n" +
                "Máxima: " + tempMax + " °C\n" +
                "Mínima: " + tempMin + " °C\n" +
                "Umidade: " + humidade + "%\n" +
                "Condição: " + condicao + "\n" +
                "Precipitação: " + precipitacao + " mm\n" +
                "Vento: " + velocidadeVento + " km/h"
        );

        painel.add(desenho, BorderLayout.CENTER);
        painel.add(info, BorderLayout.SOUTH);

        add(painel);

        setVisible(true);
    }
}