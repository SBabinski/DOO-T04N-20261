package Fag;

import javax.swing.SwingUtilities;

public class Principal {
    public static void main(String[] args) {
        Main.main(args);
    }
}

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaPrincipal tela = new TelaPrincipal();
                tela.setVisible(true);
            }
        });
    }
}
