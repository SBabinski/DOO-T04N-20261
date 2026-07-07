package tvtracker.ui; // pacote de interface gráfica

import tvtracker.model.PerfilUsuario; // modelo do perfil
import tvtracker.model.Serie;         // modelo de série

import javax.swing.*;                 // componentes Swing
import javax.swing.border.EmptyBorder; // borda vazia para padding
import java.awt.*;                    // cores, layout e eventos
import java.awt.event.MouseAdapter;   // adaptador de eventos de mouse
import java.awt.event.MouseEvent;     // evento de mouse (clique, hover)

/**
 * Componente visual que representa uma série em forma de cartão.
 * Exibe nome, estado, metadados, nota e botões de ação.
 * Abre os detalhes completos ao ser clicado.
 * Usa paintComponent customizado para o efeito de hover com cantos arredondados.
 */
public class CardSerie extends JPanel { // herda de JPanel (é um painel customizado)

    private final Serie         serie;        // série representada por este cartão
    private final PerfilUsuario perfil;       // perfil do usuário (para verificar listas)
    private final Runnable      aoMudarDados; // callback chamado quando o usuário age
    private boolean             mouse;        // true se o mouse está sobre o cartão (hover)

    /**
     * Construtor do cartão de série.
     *
     * @param serie        a série a ser exibida
     * @param perfil       o perfil do usuário
     * @param aoMudarDados ação executada ao adicionar/remover das listas
     */
    public CardSerie(Serie serie, PerfilUsuario perfil, Runnable aoMudarDados) {
        this.serie        = serie;        // guarda a referência da série
        this.perfil       = perfil;       // guarda a referência do perfil
        this.aoMudarDados = aoMudarDados; // guarda o callback
        construir(); // monta os componentes visuais do cartão
    }

    /**
     * Monta todos os componentes visuais do cartão.
     */
    private void construir() {
        setLayout(new BorderLayout(12, 0)); // layout com espaço horizontal entre áreas
        setBackground(Cores.FUNDO_CARD);    // cor de fundo do cartão
        setBorder(new EmptyBorder(12, 12, 12, 12)); // padding interno de 12px
        setOpaque(true);                    // fundo sólido (necessário para paintComponent)
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // cursor de mão ao passar

        // --- Listener de mouse: hover e clique ---
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouse = true;  // mouse entrou no cartão: ativa o hover
                repaint();     // redesenha para aplicar a nova cor
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mouse = false; // mouse saiu do cartão: desativa o hover
                repaint();     // redesenha para restaurar a cor normal
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirDetalhes(); // clique: abre o diálogo com detalhes completos
            }
        });

        // =====================================================
        // ESQUERDA: informações da série
        // =====================================================
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS)); // empilha verticalmente
        painelInfo.setOpaque(false); // transparente: herda o fundo do cartão

        // nome da série em destaque
        JLabel lblNome = new JLabel(serie.getNome());
        lblNome.setFont(Cores.FONTE_CABECALHO); // fonte maior
        lblNome.setForeground(Cores.TEXTO);      // texto claro

        // badge colorido com o estado traduzido
        Color corEstado = Cores.corDoEstado(serie.getEstado()); // cor do estado atual
        JLabel lblEstado = new JLabel("  " + serie.getEstadoTraduzido() + "  ");
        lblEstado.setFont(Cores.FONTE_PEQUENA);  // fonte pequena
        lblEstado.setForeground(corEstado);      // cor do texto igual ao estado
        lblEstado.setOpaque(true);               // fundo sólido
        lblEstado.setBackground(new Color(corEstado.getRed(), corEstado.getGreen(),
                corEstado.getBlue(), 40));       // fundo semitransparente
        lblEstado.setBorder(new EmptyBorder(2, 6, 2, 6)); // padding interno do badge

        // linha superior com nome e badge lado a lado
        JPanel linhaTopo = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        linhaTopo.setOpaque(false); // transparente
        linhaTopo.add(lblNome);     // nome à esquerda
        linhaTopo.add(lblEstado);   // badge ao lado

        // metadados: estreia, emissora e idioma numa única linha
        String meta = serie.getEstreia() + "  •  " + serie.getEmissora()
                    + "  •  " + serie.getIdioma();
        JLabel lblMeta = new JLabel(meta);
        lblMeta.setFont(Cores.FONTE_PEQUENA);       // fonte pequena
        lblMeta.setForeground(Cores.TEXTO_APAGADO); // cinza suave

        // gêneros da série
        JLabel lblGeneros = new JLabel(serie.getGenerosTexto());
        lblGeneros.setFont(Cores.FONTE_PEQUENA);       // fonte pequena
        lblGeneros.setForeground(Cores.TEXTO_APAGADO); // cinza suave

        // empilha as linhas de informação com pequenos espaços
        painelInfo.add(linhaTopo);
        painelInfo.add(Box.createVerticalStrut(3)); // espaço de 3px
        painelInfo.add(lblMeta);
        painelInfo.add(Box.createVerticalStrut(2)); // espaço de 2px
        painelInfo.add(lblGeneros);

        // =====================================================
        // DIREITA: nota e botões de ação
        // =====================================================
        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS)); // vertical
        painelDireito.setOpaque(false); // transparente

        // nota com estrela: dourada se tiver nota, cinza se não tiver
        JLabel lblNota = new JLabel("★ " + serie.getNotaFormatada());
        lblNota.setFont(new Font("Segoe UI", Font.BOLD, 14)); // negrito
        lblNota.setForeground(serie.getNota() != null ? Cores.DOURADO : Cores.TEXTO_APAGADO);
        lblNota.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza horizontalmente

        // painel de botões de ação (favorito, assistida, quer ver)
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
        painelBotoes.setOpaque(false); // transparente
        adicionarBotoes(painelBotoes); // cria e adiciona os três botões

        painelDireito.add(lblNota);                    // nota no topo direito
        painelDireito.add(Box.createVerticalStrut(6)); // espaço entre nota e botões
        painelDireito.add(painelBotoes);               // botões abaixo da nota

        add(painelInfo,    BorderLayout.CENTER); // informações ocupam o centro
        add(painelDireito, BorderLayout.EAST);  // nota e botões ficam à direita
    }

    /**
     * Cria e adiciona os três botões de gerenciar listas ao painel.
     * O texto e a cor de cada botão refletem o estado atual do perfil.
     *
     * @param painel onde os botões serão adicionados
     */
    private void adicionarBotoes(JPanel painel) {
        // --- Botão Favoritar ---
        boolean ehFav = perfil.isFavorito(serie); // está na lista de favoritos?
        JButton btnFav = criarBotao(ehFav ? "♥ Favorito" : "♡ Favoritar",
                ehFav ? Cores.DESTAQUE : Cores.TEXTO_APAGADO); // vermelho se ativo
        btnFav.addActionListener(e -> {
            if (perfil.isFavorito(serie)) perfil.removerFavorito(serie);   // toggle: remove
            else                          perfil.adicionarFavorito(serie); // toggle: adiciona
            aoMudarDados.run(); // dispara o callback para salvar e atualizar
        });

        // --- Botão Assistida ---
        boolean ehAssist = perfil.isAssistida(serie); // já assistiu?
        JButton btnAssist = criarBotao(ehAssist ? "✓ Assistida" : "+ Assistida",
                ehAssist ? Cores.VERDE : Cores.TEXTO_APAGADO); // verde se ativo
        btnAssist.addActionListener(e -> {
            if (perfil.isAssistida(serie)) perfil.removerAssistida(serie);   // toggle: remove
            else                           perfil.adicionarAssistida(serie); // toggle: adiciona
            aoMudarDados.run();
        });

        // --- Botão Quer Ver ---
        boolean ehQuerVer = perfil.isQuerVer(serie); // está na lista de quer ver?
        JButton btnQuerVer = criarBotao(ehQuerVer ? "📋 Na lista" : "+ Quero ver",
                ehQuerVer ? Cores.AZUL : Cores.TEXTO_APAGADO); // azul se ativo
        btnQuerVer.addActionListener(e -> {
            if (perfil.isQuerVer(serie)) perfil.removerQuerVer(serie);   // toggle: remove
            else                         perfil.adicionarQuerVer(serie); // toggle: adiciona
            aoMudarDados.run();
        });

        painel.add(btnFav);     // adiciona ao painel
        painel.add(btnAssist);  // adiciona ao painel
        painel.add(btnQuerVer); // adiciona ao painel
    }

    /**
     * Cria um botão estilizado para as ações do cartão.
     *
     * @param texto    texto exibido no botão
     * @param corTexto cor do texto do botão
     * @return o botão estilizado e configurado
     */
    private JButton criarBotao(String texto, Color corTexto) {
        JButton btn = new JButton(texto);            // cria o botão
        btn.setFont(Cores.FONTE_PEQUENA);            // fonte pequena
        btn.setForeground(corTexto);                 // cor do texto conforme o estado
        btn.setBackground(Cores.FUNDO_CARD);         // fundo escuro
        btn.setFocusPainted(false);                  // sem contorno de foco
        btn.setBorderPainted(false);                 // sem borda padrão
        btn.setOpaque(true);                         // fundo sólido
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // cursor de mão
        btn.setBorder(new EmptyBorder(4, 8, 4, 8)); // padding interno pequeno
        return btn; // retorna o botão pronto
    }

    /**
     * Abre o diálogo de detalhes completos da série.
     * Navega pela hierarquia de componentes para encontrar a janela pai.
     */
    private void abrirDetalhes() {
        // percorre a hierarquia de componentes até encontrar a JFrame pai
        JFrame janelaPai = (JFrame) SwingUtilities.getWindowAncestor(this);
        // cria e exibe o diálogo de detalhes (modal: bloqueia a janela pai)
        new TelaDetalhes(janelaPai, serie, perfil, aoMudarDados).setVisible(true);
    }

    /**
     * Desenha o fundo do cartão com cantos arredondados e efeito de hover.
     * Sobrescreve o paintComponent padrão do JPanel para visual customizado.
     *
     * @param g contexto gráfico fornecido pelo Swing
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create(); // cria cópia para não afetar o contexto original
        // ativa suavização de bordas (antialiasing) para cantos arredondados nítidos
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // cor muda conforme o hover: mais claro ao passar o mouse
        g2.setColor(mouse ? Cores.FUNDO_HOVER : Cores.FUNDO_CARD);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // preenche com cantos arredondados
        if (mouse) { // se o mouse estiver sobre o cartão
            g2.setColor(Cores.BORDA);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10); // borda destacada
        }
        g2.dispose(); // libera o contexto gráfico copiado
    }
}
