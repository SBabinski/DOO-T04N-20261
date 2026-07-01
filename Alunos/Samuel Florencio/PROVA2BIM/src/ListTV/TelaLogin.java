package ListTV;

import javax.swing.*;
import java.awt.*;

// tela que abre no inicio do programa pra pedir o nome/apelido do usuario
public class TelaLogin extends JDialog {

    private String nome = null;
    private final JTextField campoNome;

    public TelaLogin(Frame dono, String nomeAtual) {
        // "true" no final deixa essa janela modal, ou seja, trava a janela principal
        // enquanto essa aqui estiver aberta
        super(dono, "ListTV - Identificacao", true);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel conteudo = new JPanel(new GridBagLayout());
        conteudo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        conteudo.add(new JLabel("Bem-vindo(a) ao ListTV!"), gbc);

        gbc.gridy = 1;
        conteudo.add(new JLabel("Informe seu nome ou apelido:"), gbc);

        campoNome = new JTextField(nomeAtual == null ? "" : nomeAtual, 20);
        gbc.gridy = 2;
        conteudo.add(campoNome, gbc);

        JButton botaoEntrar = new JButton("Entrar");
        botaoEntrar.addActionListener(e -> confirmar());
        // deixa confirmar tambem apertando Enter, sem precisar clicar no botao
        campoNome.addActionListener(e -> confirmar());

        gbc.gridy = 3; gbc.gridwidth = 1; gbc.gridx = 1;
        conteudo.add(botaoEntrar, gbc);

        add(conteudo, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(dono);
        setResizable(false);
    }

    private void confirmar() {
        String texto = campoNome.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe um nome valido.",
                    "Nome obrigatorio", JOptionPane.WARNING_MESSAGE);
            return;
        }
        this.nome = texto;
        dispose();
    }

    // devolve o nome digitado, ou null se a janela foi fechada sem confirmar
    public String getNome() {
        return nome;
    }
}