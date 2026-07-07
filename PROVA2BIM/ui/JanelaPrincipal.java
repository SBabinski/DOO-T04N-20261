package tvtracker.ui; // pacote de interface gráfica

import tvtracker.model.PerfilUsuario;   // modelo do perfil
import tvtracker.persistence.ErroPersistencia; // exceção de persistência
import tvtracker.persistence.Repositorio;      // serviço de persistência
import tvtracker.service.ServicoTVMaze;        // serviço da API TVMaze

import javax.swing.*;                   // componentes Swing
import javax.swing.border.EmptyBorder;  // borda vazia para margens
import javax.swing.border.MatteBorder;  // borda com cor específica em lados escolhidos
import java.awt.*;                      // cores, layout e cursor
import java.awt.event.WindowAdapter;    // adaptador de eventos de janela
import java.awt.event.WindowEvent;      // evento de fechamento da janela

/**
 * Janela principal do aplicativo TV Tracker.
 * Contém uma sidebar de navegação à esquerda e uma área de conteúdo à direita.
 * Usa CardLayout para trocar entre as quatro telas sem recriar os componentes.
 */
public class JanelaPrincipal extends JFrame { // herda de JFrame (janela principal)

    private final PerfilUsuario perfil;      // perfil do usuário logado
    private final Repositorio   repositorio; // serviço para salvar/carregar dados

    private JPanel     areaConteudo;   // painel central que troca de tela (CardLayout)
    private TelaLista  telaFavoritos;  // tela da lista de favoritos
    private TelaLista  telaAssistidas; // tela das séries assistidas
    private TelaLista  telaQuerVer;    // tela das séries que quer ver

    // Rótulos dos botões de navegação da sidebar (com ícones emoji)
    private static final String[] ROTULOS_NAV = {
            "🔍  Buscar",       // tela de busca (índice 0)
            "♥  Favoritos",    // tela de favoritos (índice 1)
            "✓  Já assisti",   // tela de assistidas (índice 2)
            "📋  Quero ver"    // tela de quer ver (índice 3)
    };

    /**
     * Construtor da janela principal.
     *
     * @param perfil      perfil do usuário carregado ou recém-criado
     * @param repositorio serviço de persistência para salvar dados
     */
    public JanelaPrincipal(PerfilUsuario perfil, Repositorio repositorio) {
        super("TV Tracker"); // define o título da barra de título da janela
        this.perfil      = perfil;
        this.repositorio = repositorio;
        construirInterface(); // monta todos os componentes visuais
        configurarFechamento(); // configura o comportamento ao fechar
    }

    /**
     * Monta toda a interface gráfica da janela principal.
     */
    private void construirInterface() {
        setMinimumSize(new Dimension(960, 640)); // tamanho mínimo da janela
        setSize(1100, 700);                      // tamanho inicial
        setLocationRelativeTo(null);             // centraliza na tela
        setBackground(Cores.FUNDO_ESCURO);       // cor de fundo da janela

        // painel raiz com duas colunas: sidebar | conteúdo
        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(Cores.FUNDO_ESCURO);
        raiz.add(construirSidebar(), BorderLayout.WEST);   // sidebar à esquerda
        raiz.add(construirConteudo(), BorderLayout.CENTER); // conteúdo ao centro/direita

        setContentPane(raiz); // define o conteúdo da janela
    }

    /**
     * Constrói a sidebar de navegação com logo, botões e nome do usuário.
     * @return o painel da sidebar pronto para uso
     */
    private JPanel construirSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS)); // empilha verticalmente
        sidebar.setBackground(Cores.FUNDO_PAINEL); // fundo levemente mais claro que o principal
        sidebar.setPreferredSize(new Dimension(200, 0)); // largura fixa de 200px
        // borda direita fina para separar visualmente a sidebar do conteúdo
        sidebar.setBorder(new MatteBorder(0, 0, 0, 1, Cores.BORDA));

        // --- Logo do aplicativo no topo ---
        JPanel painelLogo = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 16));
        painelLogo.setOpaque(false); // transparente
        JLabel logo = new JLabel("📺 TV Tracker");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 16)); // negrito
        logo.setForeground(Cores.DESTAQUE);                // vermelho de destaque
        painelLogo.add(logo);

        sidebar.add(painelLogo);         // adiciona o logo
        sidebar.add(new JSeparator());   // linha separadora horizontal
        sidebar.add(Box.createVerticalStrut(8)); // espaço após o separador

        // --- Botões de navegação ---
        ButtonGroup grupo = new ButtonGroup(); // garante que apenas um botão fica selecionado
        JToggleButton[] botoes = new JToggleButton[ROTULOS_NAV.length]; // array de botões

        for (int i = 0; i < ROTULOS_NAV.length; i++) { // cria um botão para cada tela
            final int indice = i; // cópia final necessária para usar no lambda
            JToggleButton btn = construirBotaoNav(ROTULOS_NAV[i]); // cria o botão
            botoes[i] = btn;               // guarda referência
            grupo.add(btn);                // adiciona ao grupo (apenas um selecionado por vez)
            btn.addActionListener(e -> mostrarTela(indice)); // ao clicar: troca a tela
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(2)); // pequeno espaço entre botões
        }
        botoes[0].setSelected(true); // seleciona o primeiro botão (Buscar) por padrão

        sidebar.add(Box.createVerticalGlue()); // espaço flexível: empurra o rodapé para baixo
        sidebar.add(new JSeparator());         // separador antes do rodapé

        // --- Nome do usuário no rodapé da sidebar ---
        JPanel painelUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
        painelUsuario.setOpaque(false);
        JLabel lblUsuario = new JLabel("👤 " + perfil.getNome()); // ícone + nome do usuário
        lblUsuario.setFont(Cores.FONTE_PEQUENA);    // fonte pequena
        lblUsuario.setForeground(Cores.TEXTO_APAGADO); // cinza suave
        painelUsuario.add(lblUsuario);
        sidebar.add(painelUsuario); // adiciona o rodapé

        return sidebar; // retorna a sidebar montada
    }

    /**
     * Cria um botão de navegação estilizado para a sidebar.
     * Usa JToggleButton para que o botão permaneça "pressionado" ao selecionar.
     *
     * @param texto texto e ícone do botão
     * @return o botão estilizado e pronto para ser adicionado
     */
    private JToggleButton construirBotaoNav(String texto) {
        JToggleButton btn = new JToggleButton(texto); // botão que alterna entre pressionado/normal
        btn.setForeground(Cores.TEXTO);               // texto claro
        btn.setFont(Cores.FONTE_CORPO);               // fonte normal
        btn.setFocusPainted(false);                   // sem contorno de foco (estética)
        btn.setBorderPainted(false);                  // sem borda padrão do toggle
        btn.setContentAreaFilled(false);              // sem fundo padrão (pintamos via override)
        btn.setOpaque(false);                         // transparente para o paintComponent funcionar
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // cursor de mão
        btn.setHorizontalAlignment(SwingConstants.LEFT); // texto alinhado à esquerda
        btn.setBorder(new EmptyBorder(10, 20, 10, 16));  // padding interno
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42)); // altura fixa
        return btn; // retorna o botão configurado
    }

    /**
     * Constrói a área de conteúdo com as quatro telas usando CardLayout.
     * CardLayout permite trocar entre painéis sem destruí-los.
     *
     * @return o painel de conteúdo com todas as telas registradas
     */
    private JPanel construirConteudo() {
        areaConteudo = new JPanel(new CardLayout()); // CardLayout: uma tela visível por vez
        areaConteudo.setBackground(Cores.FUNDO_PAINEL);

        Runnable aoSalvar = this::salvarDados; // referência ao método de salvar (chamado pelas telas)

        // cria as quatro telas do aplicativo
        TelaBusca telaBusca = new TelaBusca(new ServicoTVMaze(), perfil, aoSalvar);
        telaFavoritos  = new TelaLista("♥ Favoritos",         TelaLista.TipoLista.FAVORITOS,  perfil, aoSalvar);
        telaAssistidas = new TelaLista("✓ Séries Assistidas",  TelaLista.TipoLista.ASSISTIDAS, perfil, aoSalvar);
        telaQuerVer    = new TelaLista("📋 Quero Assistir",    TelaLista.TipoLista.QUER_VER,   perfil, aoSalvar);

        // registra cada tela no CardLayout com um identificador numérico
        areaConteudo.add(telaBusca,    "0"); // tela de busca
        areaConteudo.add(telaFavoritos, "1"); // tela de favoritos
        areaConteudo.add(telaAssistidas,"2"); // tela de assistidas
        areaConteudo.add(telaQuerVer,  "3"); // tela de quer ver

        return areaConteudo; // retorna a área de conteúdo com todas as telas
    }

    /**
     * Troca a tela visível na área de conteúdo.
     * Também atualiza a lista ao navegar para ela (para refletir mudanças recentes).
     *
     * @param indice índice da tela a ser exibida (0 = busca, 1 = favoritos, etc.)
     */
    private void mostrarTela(int indice) {
        CardLayout layout = (CardLayout) areaConteudo.getLayout(); // obtém o CardLayout
        layout.show(areaConteudo, String.valueOf(indice));          // exibe a tela pelo id

        // ao navegar para uma lista: atualiza para refletir mudanças feitas na busca
        if (indice == 1) telaFavoritos.atualizar();  // atualiza a lista de favoritos
        if (indice == 2) telaAssistidas.atualizar(); // atualiza a lista de assistidas
        if (indice == 3) telaQuerVer.atualizar();    // atualiza a lista de quer ver
    }

    /**
     * Configura o comportamento ao tentar fechar a janela.
     * Salva os dados antes de encerrar o programa.
     */
    private void configurarFechamento() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // impede fechamento automático
        addWindowListener(new WindowAdapter() { // ouve o evento de fechar
            @Override
            public void windowClosing(WindowEvent e) {
                salvarDados();   // salva todos os dados antes de encerrar
                System.exit(0); // encerra o programa normalmente
            }
        });
    }

    /**
     * Salva o perfil do usuário no arquivo JSON.
     * Exibe um aviso ao usuário em caso de erro, mas não encerra o programa.
     */
    private void salvarDados() {
        try {
            repositorio.salvar(perfil); // persiste o perfil no arquivo JSON
        } catch (ErroPersistencia e) {
            // exibe uma caixa de aviso mas mantém o programa aberto
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar dados: " + e.getMessage(),
                    "Aviso de Persistência",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
