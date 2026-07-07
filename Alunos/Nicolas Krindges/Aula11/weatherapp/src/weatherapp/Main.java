package weatherapp;

import weatherapp.config.ApiKeyProvider;
import weatherapp.service.WeatherService;
import weatherapp.ui.WeatherFrame;

import javax.swing.*;

/**
 * Ponto de entrada da aplicacao de clima.
 *
 * Fluxo:
 * obtem a chave da api
 *abre a janela principal
 */

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::iniciarAplicacao);
    }

    private static void iniciarAplicacao() {
        String apiKey = ApiKeyProvider.obterChave();

        if (apiKey == null || apiKey.isBlank()) {
            apiKey = solicitarChaveAoUsuario();
        }

        if (apiKey == null || apiKey.isBlank()) {
            JOptionPane.showMessageDialog(null,
                    "Nenhuma chave de API foi informada. A aplicacao sera encerrada.",
                    "Chave obrigatória",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return;
        }

        WeatherService weatherService = new WeatherService(apiKey);
        WeatherFrame frame = new WeatherFrame(weatherService);
        frame.setVisible(true);
    }

    private static String solicitarChaveAoUsuario() {
        String mensagem = "Chave de API da Visual Crossing não encontrada.\n"
                + "Crie uma conta gratuita em visualcrossing.com/sign-up\n"
                + "e informe sua chave abaixo:";
        return JOptionPane.showInputDialog(null, mensagem, "Chave de API", JOptionPane.QUESTION_MESSAGE);
    }
}
