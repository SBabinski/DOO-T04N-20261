import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    private final SerieService service;
    private final ListaPanel painelFavoritos;
    private final ListaPanel painelAssistidas;
    private final ListaPanel painelDeseja;

    public MainFrame(SerieService service) {
        super("SérieTracker");
        this.service = service;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1000, 620);
        setLocationRelativeTo(null);

        //Cabeçalho com nome do usuário
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        JLabel saudacao = new JLabel("Olá, " + nomeOuPadrao() + "! 👋");
        saudacao.setFont(saudacao.getFont().deriveFont(Font.BOLD, 16f));
        cabecalho.add(saudacao, BorderLayout.WEST);

        JButton btnTrocarNome = new JButton("Alterar nome");
        btnTrocarNome.addActionListener(e -> alterarNome(saudacao));
        cabecalho.add(btnTrocarNome, BorderLayout.EAST);

        add(cabecalho, BorderLayout.NORTH);

        //Abas
        JTabbedPane abas = new JTabbedPane();

        BuscaPanel painelBusca = new BuscaPanel(service);
        painelFavoritos = new ListaPanel(service, TipoLista.FAVORITOS);
        painelAssistidas = new ListaPanel(service, TipoLista.ASSISTIDAS);
        painelDeseja = new ListaPanel(service, TipoLista.DESEJA_ASSISTIR);

        abas.addTab("🔍 Buscar", painelBusca);
        abas.addTab("⭐ Favoritos", painelFavoritos);
        abas.addTab("✅ Já assistidas", painelAssistidas);
        abas.addTab("📌 Desejo assistir", painelDeseja);

        //Sempre que mudar para uma aba de lista, atualiza seu conteúdo.
        abas.addChangeListener(e -> {
            Component sel = abas.getSelectedComponent();
            if (sel instanceof ListaPanel) {
                ((ListaPanel) sel).atualizar();
            }
        });

        add(abas, BorderLayout.CENTER);

        //Rodapé com caminho do arquivo
        JLabel rodape = new JLabel("Dados salvos em: " + service.getCaminhoArquivo());
        rodape.setBorder(BorderFactory.createEmptyBorder(4, 12, 6, 12));
        rodape.setFont(rodape.getFont().deriveFont(11f));
        rodape.setForeground(Color.GRAY);
        add(rodape, BorderLayout.SOUTH);

        //Salva ao fechar
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fecharComSalvamento();
            }
        });
    }

    private String nomeOuPadrao() {
        String nome = service.getNomeUsuario();
        return (nome != null && !nome.trim().isEmpty()) ? nome : "visitante";
    }

    private void alterarNome(JLabel saudacao) {
        String novo = JOptionPane.showInputDialog(this,
                "Como você gostaria de ser chamado?",
                service.getNomeUsuario());
        if (novo != null && !novo.trim().isEmpty()) {
            try {
                service.definirNomeUsuario(novo.trim());
                saudacao.setText("Olá, " + novo.trim() + "! 👋");
            } catch (PersistenceException ex) {
                JOptionPane.showMessageDialog(this,
                        "Não foi possível salvar o nome: " + ex.getMessage(),
                        "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void fecharComSalvamento() {
        try {
            service.salvar();
        } catch (PersistenceException ex) {
            int opcao = JOptionPane.showConfirmDialog(this,
                    "Houve um erro ao salvar os dados: " + ex.getMessage()
                            + "\nDeseja sair mesmo assim?",
                    "Erro ao salvar", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (opcao != JOptionPane.YES_OPTION) {
                return;
            }
        }
        dispose();
        System.exit(0);
    }
}
