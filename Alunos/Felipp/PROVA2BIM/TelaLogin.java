package Fag.gui;

import Fag.FonteDeSeries;
import Fag.Usuario;
import Fag.services.PersistenciaException;
import Fag.services.PersistenciaService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.regex.Pattern;

// primeira tela. pede o nome ou apelido do usuario (sistema local apenas).
public class TelaLogin extends TelaBase {

    // regex que valida o apelido: de 2 a 20 caracteres, aceitando letras,
    // numeros, espaco, ponto, traco e sublinhado.
    private static final Pattern APELIDO_VALIDO =
            Pattern.compile("^[\\p{L}\\p{N} ._-]{2,20}$");

    private final Usuario usuario;
    private final PersistenciaService persistencia;
    private final FonteDeSeries fonte;

    private final JTextField campoApelido = new JTextField(18);

    public TelaLogin(Usuario usuario, PersistenciaService persistencia, FonteDeSeries fonte) {
        super("acompanhador de séries", 380, 200);
        this.usuario = usuario;
        this.persistencia = persistencia;
        this.fonte = fonte;
        // se o usuario fechar esta tela, o programa encerra.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        montarTela();
    }

    private void montarTela() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);

        c.gridx = 0; c.gridy = 0;
        painel.add(new JLabel("informe seu nome ou apelido:"), c);

        // se ja existe um apelido salvo, ele aparece preenchido.
        campoApelido.setText(usuario.getApelido());
        c.gridy = 1;
        painel.add(campoApelido, c);

        JButton entrar = new JButton("entrar");
        entrar.addActionListener(e -> entrar());
        c.gridy = 2;
        painel.add(entrar, c);

        add(painel);
    }

    private void entrar() {
        String apelido = campoApelido.getText().trim();
        if (!APELIDO_VALIDO.matcher(apelido).matches()) {
            mostrarErro("o apelido deve ter de 2 a 20 caracteres válidos.");
            return;
        }
        usuario.setApelido(apelido);
        try {
            persistencia.salvar(usuario);
        } catch (PersistenciaException ex) {
            mostrarErro(ex.getMessage());
        }
        new TelaPrincipal(usuario, persistencia, fonte).setVisible(true);
        dispose();
    }
}
