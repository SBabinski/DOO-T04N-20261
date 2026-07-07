package tvtracker.ui; // pacote de interface gráfica

import tvtracker.model.OrdenadorSeries; // critérios de ordenação
import tvtracker.model.PerfilUsuario;   // modelo do perfil
import tvtracker.model.Serie;           // modelo de série

import javax.swing.*;                   // componentes Swing
import javax.swing.border.EmptyBorder;  // borda vazia para margens
import java.awt.*;                      // cores e layout
import java.util.ArrayList;             // lista dinâmica
import java.util.List;                  // interface de lista

/**
 * Painel que exibe uma das três listas de séries do usuário:
 * Favoritos, Séries Assistidas ou Séries que Quer Ver.
 * Suporta ordenação por quatro critérios e remoção individual de itens.
 */
public class TelaLista extends JPanel { // herda de JPanel

    /**
     * Enum interno que identifica qual das três listas esta tela representa.
     */
    public enum TipoLista {
        FAVORITOS,  // lista de séries favoritas
        ASSISTIDAS, // lista de séries já assistidas
        QUER_VER    // lista de séries que o usuário deseja assistir
    }

    private final String        titulo;       // título exibido no topo da tela
    private final TipoLista     tipo;         // qual das três listas esta tela representa
    private final PerfilUsuario perfil;       // perfil do usuário com as listas
    private final Runnable      aoMudarDados; // callback para salvar após modificações

    private JComboBox<OrdenadorSeries.TipoOrdenacao> comboOrdenacao; // seletor de ordenação
    private JPanel painelCards; // painel onde os cartões das séries são exibidos

    /**
     * Construtor da tela de lista.
     *
     * @param titulo       título da lista (ex: "♥ Favoritos")
     * @param tipo         qual lista esta tela representa
     * @param perfil       perfil do usuário
     * @param aoMudarDados ação executada ao modificar a lista
     */
    public TelaLista(String titulo, TipoLista tipo, PerfilUsuario perfil, Runnable aoMudarDados) {
        this.titulo       = titulo;
        this.tipo         = tipo;
        this.perfil       = perfil;
        this.aoMudarDados = aoMudarDados;
        setLayout(new BorderLayout(0, 12)); // layout com espaço vertical de 12px
        setBackground(Cores.FUNDO_PAINEL); // fundo escuro
        setBorder(new EmptyBorder(20, 20, 20, 20)); // margens de 20px
        construirCabecalho(); // monta o título e o seletor de ordenação
        construirAreaCards(); // monta a área de rolagem dos cartões
        atualizar();          // carrega e exibe os dados iniciais
    }

    /**
     * Monta o cabeçalho da tela: título à esquerda e combobox de ordenação à direita.
     */
    private void construirCabecalho() {
        JPanel cabecalho = new JPanel(new BorderLayout(12, 0)); // horizontal com espaço
        cabecalho.setOpaque(false); // transparente

        // título da lista
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(Cores.FONTE_TITULO); // fonte grande
        lblTitulo.setForeground(Cores.TEXTO);  // cor clara

        // --- Painel de ordenação ---
        JPanel painelOrdem = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        painelOrdem.setOpaque(false);

        JLabel lblOrdenar = new JLabel("Ordenar por:"); // rótulo
        lblOrdenar.setFont(Cores.FONTE_PEQUENA);
        lblOrdenar.setForeground(Cores.TEXTO_APAGADO);

        // combobox com todos os critérios de ordenação definidos no enum
        comboOrdenacao = new JComboBox<>(OrdenadorSeries.TipoOrdenacao.values());
        comboOrdenacao.setBackground(Cores.FUNDO_CARD); // fundo escuro
        comboOrdenacao.setForeground(Cores.TEXTO);      // texto claro
        comboOrdenacao.setFont(Cores.FONTE_CORPO);      // fonte normal
        comboOrdenacao.setFocusable(false);             // sem foco (melhora a estética)
        // ao mudar a opção de ordenação: recarrega a lista na nova ordem
        comboOrdenacao.addActionListener(e -> atualizar());

        painelOrdem.add(lblOrdenar);     // rótulo "Ordenar por:"
        painelOrdem.add(comboOrdenacao); // combobox com as opções

        cabecalho.add(lblTitulo,   BorderLayout.WEST); // título à esquerda
        cabecalho.add(painelOrdem, BorderLayout.EAST); // ordenação à direita

        add(cabecalho, BorderLayout.NORTH); // cabeçalho fica no topo da tela
    }

    /**
     * Monta a área de rolagem que conterá os cartões das séries.
     */
    private void construirAreaCards() {
        painelCards = new JPanel(); // painel que conterá os cartões
        painelCards.setLayout(new BoxLayout(painelCards, BoxLayout.Y_AXIS)); // empilha verticalmente
        painelCards.setBackground(Cores.FUNDO_PAINEL); // mesmo fundo

        // envolve em barra de rolagem para suportar listas longas
        JScrollPane scroll = new JScrollPane(painelCards);
        scroll.setBackground(Cores.FUNDO_PAINEL);
        scroll.getViewport().setBackground(Cores.FUNDO_PAINEL); // fundo do viewport
        scroll.setBorder(null); // sem borda

        add(scroll, BorderLayout.CENTER); // ocupa o centro da tela
    }

    /**
     * Recarrega e exibe todos os cartões da lista com a ordenação atual.
     * Chamado ao abrir a tela, ao mudar a ordenação ou após modificar uma série.
     */
    public void atualizar() {
        painelCards.removeAll(); // remove todos os cartões existentes

        List<Serie> fonte = obterListaDoTipo(); // obtém a lista correta do perfil

        if (fonte.isEmpty()) { // lista vazia: exibe mensagem de estado vazio
            JPanel estadoVazio = new JPanel(new GridBagLayout()); // centraliza o conteúdo
            estadoVazio.setBackground(Cores.FUNDO_PAINEL);
            JLabel lblVazio = new JLabel("Nenhuma série nesta lista ainda.");
            lblVazio.setFont(Cores.FONTE_CORPO);
            lblVazio.setForeground(Cores.TEXTO_APAGADO);
            estadoVazio.add(lblVazio); // centraliza a mensagem
            painelCards.add(estadoVazio);
        } else {
            // cria uma cópia para não modificar a lista original ao ordenar
            List<Serie> ordenada = new ArrayList<>(fonte);
            OrdenadorSeries.TipoOrdenacao criterio =
                    (OrdenadorSeries.TipoOrdenacao) comboOrdenacao.getSelectedItem();
            if (criterio != null) {
                ordenada.sort(OrdenadorSeries.por(criterio)); // aplica a ordenação escolhida
            }

            for (Serie s : ordenada) { // percorre cada série na ordem definida
                // painel de linha: cartão à esquerda, botão remover à direita
                JPanel linha = new JPanel(new BorderLayout(8, 0));
                linha.setOpaque(false); // transparente
                linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120)); // altura máxima

                // cartão da série: ao mudar dados, salva e recarrega a lista
                CardSerie card = new CardSerie(s, perfil, () -> {
                    aoMudarDados.run(); // salva os dados
                    atualizar();        // recarrega a lista para refletir mudanças
                });

                // botão X vermelho para remover a série da lista
                JButton btnRemover = new JButton("✕");
                btnRemover.setFont(new Font("Segoe UI", Font.BOLD, 14)); // negrito
                btnRemover.setForeground(Cores.DESTAQUE);    // X vermelho
                btnRemover.setBackground(Cores.FUNDO_CARD);  // fundo escuro
                btnRemover.setFocusPainted(false);           // sem contorno de foco
                btnRemover.setBorderPainted(false);          // sem borda padrão
                btnRemover.setToolTipText("Remover da lista"); // dica ao passar o mouse
                btnRemover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnRemover.setBorder(new EmptyBorder(4, 10, 4, 10)); // padding
                btnRemover.addActionListener(e -> {
                    removerDaLista(s); // remove a série da lista correspondente
                    aoMudarDados.run(); // salva os dados atualizados
                    atualizar();        // recarrega a lista sem o item removido
                });

                linha.add(card,       BorderLayout.CENTER); // cartão ocupa o centro
                linha.add(btnRemover, BorderLayout.EAST);   // botão X fixo à direita

                // envolve a linha em um painel para adicionar espaço inferior
                JPanel envoltorio = new JPanel(new BorderLayout());
                envoltorio.setOpaque(false);
                envoltorio.add(linha, BorderLayout.CENTER);
                envoltorio.add(Box.createVerticalStrut(8), BorderLayout.SOUTH); // espaço entre itens
                painelCards.add(envoltorio); // adiciona ao painel de cartões
            }
        }

        painelCards.revalidate(); // recalcula o layout após as mudanças
        painelCards.repaint();    // redesenha o painel
    }

    /**
     * Retorna a lista de séries correta do perfil com base no tipo desta tela.
     * @return a lista de Series correspondente ao tipo (FAVORITOS, ASSISTIDAS ou QUER_VER)
     */
    private List<Serie> obterListaDoTipo() {
        switch (tipo) {
            case FAVORITOS:  return perfil.getFavoritos();  // lista de favoritos
            case ASSISTIDAS: return perfil.getAssistidas(); // lista de assistidas
            case QUER_VER:   return perfil.getQuerVer();    // lista de quer ver
            default:         return new ArrayList<>();      // fallback: lista vazia
        }
    }

    /**
     * Remove uma série da lista correta do perfil com base no tipo desta tela.
     * @param serie a série a ser removida
     */
    private void removerDaLista(Serie serie) {
        switch (tipo) {
            case FAVORITOS:  perfil.removerFavorito(serie);  break; // remove dos favoritos
            case ASSISTIDAS: perfil.removerAssistida(serie); break; // remove das assistidas
            case QUER_VER:   perfil.removerQuerVer(serie);   break; // remove de quer ver
        }
    }
}
