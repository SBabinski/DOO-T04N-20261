package tvtracker.ui; // pacote de interface gráfica

import tvtracker.model.PerfilUsuario; // modelo do perfil do usuário
import tvtracker.model.Serie;         // modelo de série

import javax.swing.*;                 // componentes visuais Swing
import javax.swing.border.EmptyBorder; // borda vazia para margens internas
import java.awt.*;                    // cores, fontes e layout

/**
 * Diálogo modal que exibe todas as informações detalhadas de uma série.
 * Abre ao clicar em um cartão de série na busca ou nas listas.
 * Permite adicionar/remover a série das três listas diretamente.
 */
public class TelaDetalhes extends JDialog { // herda de JDialog (janela modal)

    private final Serie         serie;        // série cujos detalhes estão sendo exibidos
    private final PerfilUsuario perfil;       // perfil do usuário (para verificar/modificar listas)
    private final Runnable      aoMudarDados; // callback chamado após modificar uma lista

    /**
     * Construtor do diálogo de detalhes.
     *
     * @param pai         janela pai (para centralização)
     * @param serie       série a ser exibida
     * @param perfil      perfil do usuário
     * @param aoMudarDados ação executada quando o usuário adiciona/remove das listas
     */
    public TelaDetalhes(JFrame pai, Serie serie, PerfilUsuario perfil, Runnable aoMudarDados) {
        super(pai, serie.getNome(), true); // título = nome da série; true = modal
        this.serie       = serie;
        this.perfil      = perfil;
        this.aoMudarDados = aoMudarDados;
        construir(); // monta todos os componentes visuais
    }

    /**
     * Monta todos os componentes do diálogo de detalhes.
     */
    private void construir() {
        setSize(560, 500);                  // tamanho fixo do diálogo
        setLocationRelativeTo(getOwner()); // centraliza em relação à janela pai
        setResizable(false);               // impede redimensionamento
        getContentPane().setBackground(Cores.FUNDO_PAINEL); // fundo escuro

        // painel raiz com margens externas de 20px
        JPanel raiz = new JPanel(new BorderLayout(0, 0));
        raiz.setBackground(Cores.FUNDO_PAINEL);
        raiz.setBorder(new EmptyBorder(20, 20, 20, 20)); // margens internas

        // =====================================================
        // TOPO: nome da série + badge de estado + nota
        // =====================================================
        JLabel lblNome = new JLabel(serie.getNome()); // rótulo com o nome da série
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 20)); // fonte grande e negrito
        lblNome.setForeground(Cores.TEXTO); // cor clara

        // badge colorido com o estado traduzido da série
        Color corEstado = Cores.corDoEstado(serie.getEstado()); // cor do estado
        JLabel lblEstado = new JLabel("  " + serie.getEstadoTraduzido() + "  "); // texto com padding
        lblEstado.setFont(Cores.FONTE_PEQUENA);   // fonte pequena
        lblEstado.setForeground(corEstado);       // cor do texto igual à cor do estado
        lblEstado.setOpaque(true);                // fundo sólido necessário para a cor
        lblEstado.setBackground(new Color(corEstado.getRed(), corEstado.getGreen(),
                corEstado.getBlue(), 40));        // fundo com mesma cor mas semitransparente
        lblEstado.setBorder(new EmptyBorder(3, 8, 3, 8)); // padding interno do badge

        // linha horizontal com nome e badge lado a lado
        JPanel linhaNome = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        linhaNome.setOpaque(false); // transparente
        linhaNome.add(lblNome);     // nome à esquerda
        linhaNome.add(lblEstado);   // badge ao lado do nome

        // nota com estrela dourada
        JLabel lblNota = new JLabel("★ " + serie.getNotaFormatada()); // texto com estrela
        lblNota.setFont(new Font("Segoe UI", Font.BOLD, 18)); // fonte grande
        lblNota.setForeground(serie.getNota() != null ? Cores.DOURADO : Cores.TEXTO_APAGADO); // dourado ou cinza

        // painel do topo com nome à esquerda e nota à direita
        JPanel topo = new JPanel(new BorderLayout());
        topo.setOpaque(false);
        topo.add(linhaNome, BorderLayout.WEST); // nome e badge à esquerda
        topo.add(lblNota,   BorderLayout.EAST); // nota à direita

        // =====================================================
        // CENTRO: grade de informações + sinopse
        // =====================================================
        JPanel painelInfo = new JPanel(new GridBagLayout()); // grade para alinhar rótulos e valores
        painelInfo.setBackground(Cores.FUNDO_CARD); // fundo levemente mais claro
        painelInfo.setBorder(new EmptyBorder(12, 12, 12, 12)); // padding interno
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST; // alinha conteúdo à esquerda
        c.insets = new Insets(4, 4, 4, 16); // espaçamento entre células da grade

        // adiciona cada campo como uma linha na grade
        adicionarLinhaInfo(painelInfo, c, 0, "Idioma:",   serie.getIdioma());       // idioma original
        adicionarLinhaInfo(painelInfo, c, 1, "Gêneros:",  serie.getGenerosTexto()); // gêneros separados por vírgula
        adicionarLinhaInfo(painelInfo, c, 2, "Emissora:", serie.getEmissora());     // emissora ou canal
        adicionarLinhaInfo(painelInfo, c, 3, "Estreia:",  serie.getEstreia());      // data de estreia
        adicionarLinhaInfo(painelInfo, c, 4, "Término:",  serie.getTermino());      // data de término

        // área de texto rolável para a sinopse
        JTextArea txtSinopse = new JTextArea(serie.getSinopse()); // conteúdo da sinopse
        txtSinopse.setFont(Cores.FONTE_CORPO);          // fonte normal
        txtSinopse.setForeground(Cores.TEXTO_APAGADO);  // texto cinza claro
        txtSinopse.setBackground(Cores.FUNDO_CARD);     // fundo escuro
        txtSinopse.setLineWrap(true);                   // quebra de linha automática
        txtSinopse.setWrapStyleWord(true);              // quebra por palavras inteiras
        txtSinopse.setEditable(false);                  // somente leitura
        txtSinopse.setBorder(new EmptyBorder(12, 12, 12, 12)); // padding interno

        JScrollPane scrollSinopse = new JScrollPane(txtSinopse); // adiciona barra de rolagem
        scrollSinopse.setBorder(null);                           // remove borda do scroll
        scrollSinopse.setPreferredSize(new Dimension(0, 110));   // altura fixa de 110px

        // painel central que empilha a grade e a sinopse verticalmente
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS)); // empilha verticalmente
        centro.setOpaque(false);
        centro.add(Box.createVerticalStrut(12)); // espaço antes da grade
        centro.add(painelInfo);                  // grade com informações
        centro.add(Box.createVerticalStrut(12)); // espaço entre grade e sinopse
        centro.add(scrollSinopse);               // área da sinopse

        // =====================================================
        // RODAPÉ: botões de ação e botão fechar
        // =====================================================
        JPanel rodape = new JPanel(new BorderLayout()); // layout do rodapé
        rodape.setOpaque(false);
        rodape.setBorder(new EmptyBorder(12, 0, 0, 0)); // espaço acima do rodapé

        // painel com os três botões de gerenciar listas
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        painelBotoes.setOpaque(false);
        adicionarBotoesAcao(painelBotoes); // adiciona os botões de favorito, assistida e quer ver

        // botão para fechar o diálogo sem fazer nada
        JButton btnFechar = new JButton("Fechar");
        btnFechar.setFont(Cores.FONTE_CORPO);
        btnFechar.setBackground(Cores.FUNDO_CARD);
        btnFechar.setForeground(Cores.TEXTO);
        btnFechar.setFocusPainted(false);
        btnFechar.setBorderPainted(false);
        btnFechar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnFechar.setBorder(new EmptyBorder(8, 16, 8, 16));
        btnFechar.addActionListener(e -> dispose()); // fecha ao clicar

        rodape.add(painelBotoes, BorderLayout.CENTER); // botões de lista ao centro
        rodape.add(btnFechar,    BorderLayout.EAST);   // fechar à direita

        // monta o layout completo do diálogo
        raiz.add(topo,   BorderLayout.NORTH);  // topo: nome e nota
        raiz.add(centro, BorderLayout.CENTER); // centro: informações e sinopse
        raiz.add(rodape, BorderLayout.SOUTH);  // rodapé: botões

        setContentPane(raiz); // define o conteúdo do diálogo
    }

    /**
     * Adiciona uma linha de informação (rótulo + valor) na grade.
     *
     * @param painel o painel com GridBagLayout
     * @param c      as restrições de layout
     * @param linha  o índice da linha na grade
     * @param rotulo o texto do rótulo (ex: "Idioma:")
     * @param valor  o valor a ser exibido (ex: "English")
     */
    private void adicionarLinhaInfo(JPanel painel, GridBagConstraints c,
                                     int linha, String rotulo, String valor) {
        c.gridx = 0; c.gridy = linha; // posiciona na coluna 0 da linha especificada
        JLabel lblRotulo = new JLabel(rotulo);
        lblRotulo.setFont(new Font("Segoe UI", Font.BOLD, 12)); // negrito para o rótulo
        lblRotulo.setForeground(Cores.TEXTO_APAGADO);           // cinza para o rótulo
        painel.add(lblRotulo, c); // adiciona o rótulo na grade

        c.gridx = 1; // move para a coluna 1 (mesma linha)
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(Cores.FONTE_CORPO); // fonte normal para o valor
        lblValor.setForeground(Cores.TEXTO); // texto claro para o valor
        painel.add(lblValor, c); // adiciona o valor na grade
    }

    /**
     * Adiciona os três botões de gerenciar listas (Favoritos, Assistidas, Quer Ver).
     * O estilo do botão muda conforme a série já esteja ou não na lista.
     *
     * @param painel o painel onde os botões serão adicionados
     */
    private void adicionarBotoesAcao(JPanel painel) {
        // --- Botão Favoritos ---
        boolean ehFavorito = perfil.isFavorito(serie); // verifica se já é favorita
        // texto e aparência mudam se já for favorita ou não
        JButton btnFav = criarBotaoAcao(ehFavorito ? "♥ Remover dos Favoritos" : "♥ Favoritar", ehFavorito);
        btnFav.addActionListener(e -> {
            if (perfil.isFavorito(serie)) perfil.removerFavorito(serie);   // remove se já está
            else                          perfil.adicionarFavorito(serie); // adiciona se não está
            aoMudarDados.run(); // notifica que os dados mudaram (salva e atualiza a tela)
            dispose();          // fecha o diálogo após a ação
        });

        // --- Botão Já Assisti ---
        boolean ehAssistida = perfil.isAssistida(serie); // verifica se já foi assistida
        JButton btnAssist = criarBotaoAcao(ehAssistida ? "✓ Remover de Assistidas" : "✓ Já Assisti", ehAssistida);
        btnAssist.addActionListener(e -> {
            if (perfil.isAssistida(serie)) perfil.removerAssistida(serie);   // remove
            else                           perfil.adicionarAssistida(serie); // adiciona
            aoMudarDados.run();
            dispose();
        });

        // --- Botão Quero Ver ---
        boolean ehQuerVer = perfil.isQuerVer(serie); // verifica se está na lista quer ver
        JButton btnQuerVer = criarBotaoAcao(ehQuerVer ? "📋 Remover da Lista" : "📋 Quero Ver", ehQuerVer);
        btnQuerVer.addActionListener(e -> {
            if (perfil.isQuerVer(serie)) perfil.removerQuerVer(serie);   // remove
            else                         perfil.adicionarQuerVer(serie); // adiciona
            aoMudarDados.run();
            dispose();
        });

        painel.add(btnFav);     // adiciona botão de favoritos
        painel.add(btnAssist);  // adiciona botão de assistidas
        painel.add(btnQuerVer); // adiciona botão de quer ver
    }

    /**
     * Cria um botão de ação estilizado para o rodapé do diálogo.
     * Botão ativo (já na lista) usa fundo vermelho; inativo usa fundo escuro.
     *
     * @param texto  texto exibido no botão
     * @param ativo  true se a série já está na lista correspondente
     * @return o botão estilizado e pronto para uso
     */
    private JButton criarBotaoAcao(String texto, boolean ativo) {
        JButton btn = new JButton(texto); // cria o botão com o texto
        btn.setFont(Cores.FONTE_CORPO);   // fonte normal
        btn.setBackground(ativo ? Cores.DESTAQUE : Cores.FUNDO_CARD); // vermelho se ativo, escuro se não
        btn.setForeground(Cores.TEXTO);   // texto sempre claro
        btn.setFocusPainted(false);       // sem contorno de foco
        btn.setBorderPainted(false);      // sem borda padrão
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // cursor de mão
        btn.setBorder(new EmptyBorder(8, 14, 8, 14)); // padding interno
        return btn; // retorna o botão pronto
    }
}
