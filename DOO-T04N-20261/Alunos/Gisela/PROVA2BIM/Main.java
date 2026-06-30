package PROVA2BIM;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Definição de cores padrão
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Label.foreground", new Color(0,139,139));
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", new Color(0,139,139));
        UIManager.put("TextField.background", Color.BLACK);
        UIManager.put("TextField.foreground", new Color(0,139,139));
        UIManager.put("TextArea.background", Color.BLACK);
        UIManager.put("TextArea.foreground", new Color(0,139,139));
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", new Color(0,139,139));

        // Carrega usuário salvo
        Usuario usuario = JsonService.carregar();
        if (usuario != null) {
            System.out.println("Bem-vindo(a/e) de volta, " + usuario.getNome());
            new TelaPrincipal(usuario); // abre tela principal direto user logade
        } else {
            new LoginFrame(); // abre tela de login
        }
    }
}
