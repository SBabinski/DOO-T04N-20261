package Objetos;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            TelaClima tela = new TelaClima();
            tela.setVisible(true);
        });

    }
}