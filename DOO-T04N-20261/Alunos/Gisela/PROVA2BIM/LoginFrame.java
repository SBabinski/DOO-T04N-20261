package PROVA2BIM;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField campoNome;

    public LoginFrame() {
        super("Login - PROVA2BIM");

        setLayout(new BorderLayout());

        campoNome = new JTextField(20);
        JButton botaoLogin = new JButton("Entrar");

        JPanel painel = new JPanel();
        painel.add(new JLabel("Digite seu nome:"));
        painel.add(campoNome);
        painel.add(botaoLogin);

        add(painel, BorderLayout.CENTER);

        botaoLogin.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            if (!nome.isEmpty()) {
                Usuario usuario = new Usuario(nome);
                JsonService.salvar(usuario);
                dispose(); // fecha tela de login
                new TelaPrincipal(); // abre tela principal
            } else {
                JOptionPane.showMessageDialog(this, "Digite um nome válido!");
            }
        });

        setSize(300, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
