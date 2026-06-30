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
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// tela principal com o menu do sistema.
public class TelaPrincipal extends TelaBase {

    private final Usuario usuario;
    private final PersistenciaService persistencia;
    private final FonteDeSeries fonte;

    public TelaPrincipal(Usuario usuario, PersistenciaService persistencia, FonteDeSeries fonte) {
        super("acompanhador de séries", 420, 360);
        this.usuario = usuario;
        this.persistencia = persistencia;
        this.fonte = fonte;
        montarTela();
        salvarAoFechar();
    }

    private void montarTela() {
        JPanel painel = new JPanel(new GridLayout(0, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        painel.add(new JLabel("olá, " + usuario.getApelido() + "!", SwingConstants.CENTER));

        JButton buscar = new JButton("buscar séries");
        buscar.addActionListener(e ->
                new TelaBusca(usuario, persistencia, fonte).setVisible(true));
        painel.add(buscar);

        JButton favoritos = new JButton("ver favoritos");
        favoritos.addActionListener(e ->
                new TelaLista("favoritos", usuario.getFavoritos(), usuario, persistencia).setVisible(true));
        painel.add(favoritos);

        JButton assistidas = new JButton("ver séries já assistidas");
        assistidas.addActionListener(e ->
                new TelaLista("séries já assistidas", usuario.getAssistidas(), usuario, persistencia).setVisible(true));
        painel.add(assistidas);

        JButton desejadas = new JButton("ver séries que deseja assistir");
        desejadas.addActionListener(e ->
                new TelaLista("séries que deseja assistir", usuario.getDesejaAssistir(), usuario, persistencia).setVisible(true));
        painel.add(desejadas);

        JButton sair = new JButton("salvar e sair");
        sair.addActionListener(e -> salvarESair());
        painel.add(sair);

        add(painel);
    }

    private void salvarESair() {
        salvar();
        System.exit(0);
    }

    private void salvar() {
        try {
            persistencia.salvar(usuario);
        } catch (PersistenciaException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    // garante que os dados sejam salvos mesmo se o usuario fechar pelo "x".
    private void salvarAoFechar() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salvar();
                System.exit(0);
            }
        });
    }
}
