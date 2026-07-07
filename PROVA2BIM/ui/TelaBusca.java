package tvtracker.ui; // pacote de interface gráfica

import tvtracker.model.PerfilUsuario; // modelo do perfil
import tvtracker.model.Serie;         // modelo de série
import tvtracker.service.ErroApi;     // exceção de erro da API
import tvtracker.service.ServicoTVMaze; // serviço de busca na API

import javax.swing.*;                 // componentes Swing
import javax.swing.border.EmptyBorder; // borda vazia para margens
import java.awt.*;                    // cores e layout
import java.awt.event.KeyAdapter;     // adaptador de eventos de teclado
import java.awt.event.KeyEvent;       // evento de tecla pressionada
import java.util.List;                // interface de lista

/**
 * Painel de busca de séries pela API TVMaze.
 * Permite ao usuário digitar um nome e ver os resultados como cartões.
 * A busca é feita em uma thread separada (SwingWorker) para não travar a UI.
 */
public class TelaBusca extends JPanel { // herda de JPanel (é um painel de conteúdo)

    private final ServicoTVMaze  servico;      // serviço que faz as chamadas à API
    private final PerfilUsuario  perfil;       // perfil do usuário (para gerenciar listas)
    private final Runnable       aoMudarDados; // callback para salvar após ações

    private JTextField camposBusca;     // campo onde o usuário digita o nome da série
    private JPanel     painelResultados; // painel que exibe os cartões dos resultados
    private JLabel     lblStatus;       // rótulo de feedback (buscando..., X resultados, erro)

    /**
     * Construtor da tela de busca.
     *
     * @param servico      serviço TVMaze para consultar a API
     * @param perfil       perfil do usuário
     * @param aoMudarDados ação executada quando o usuário modifica suas listas
     */
    public TelaBusca(ServicoTVMaze servico, PerfilUsuario perfil, Runnable aoMudarDados) {
        this.servico      = servico;
        this.perfil       = perfil;
        this.aoMudarDados = aoMudarDados;
        setLayout(new BorderLayout(0, 12)); // layout com espaço vertical de 12px
        setBackground(Cores.FUNDO_PAINEL); // fundo escuro
        setBorder(new EmptyBorder(20, 20, 20, 20)); // margens de 20px
        construirBarraDeBusca(); // monta a parte superior (título, campo e botão)
        construirAreaResultados(); // monta a área de rolagem dos resultados
    }

    /**
     * Monta a barra de busca: título, campo de texto, botão e rótulo de status.
     */
    private void construirBarraDeBusca() {
        JPanel topo = new JPanel(); // painel do topo
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS)); // empilha verticalmente
        topo.setOpaque(false); // transparente

        // título da tela de busca
        JLabel titulo = new JLabel("🔍 Buscar Séries");
        titulo.setFont(Cores.FONTE_TITULO); // fonte grande
        titulo.setForeground(Cores.TEXTO);  // cor clara

        // linha horizontal com o campo de busca e o botão
        JPanel linhaBusca = new JPanel(new BorderLayout(8, 0)); // espaço de 8px entre campo e botão
        linhaBusca.setOpaque(false);

        // campo de texto para digitar o nome da série
        camposBusca = new JTextField(); // campo de entrada livre
        camposBusca.setFont(Cores.FONTE_CORPO);          // fonte normal
        camposBusca.setBackground(Cores.FUNDO_CARD);     // fundo escuro
        camposBusca.setForeground(Cores.TEXTO);          // texto claro
        camposBusca.setCaretColor(Cores.TEXTO);          // cursor de texto claro
        camposBusca.setBorder(BorderFactory.createCompoundBorder( // borda composta
                BorderFactory.createLineBorder(Cores.BORDA, 1),   // linha fina externa
                new EmptyBorder(8, 12, 8, 12)));         // padding interno
        // permite buscar ao pressionar Enter no campo
        camposBusca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) realizarBusca(); // Enter: busca
            }
        });

        // botão de busca com destaque vermelho
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(Cores.FONTE_CORPO);
        btnBuscar.setBackground(Cores.DESTAQUE);     // fundo vermelho
        btnBuscar.setForeground(Cores.TEXTO);        // texto claro
        btnBuscar.setFocusPainted(false);            // sem contorno de foco
        btnBuscar.setBorderPainted(false);           // sem borda padrão
        btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // mão
        btnBuscar.setBorder(new EmptyBorder(8, 20, 8, 20)); // padding generoso
        btnBuscar.addActionListener(e -> realizarBusca()); // clique: realiza a busca

        linhaBusca.add(camposBusca, BorderLayout.CENTER); // campo ocupa todo o centro
        linhaBusca.add(btnBuscar,   BorderLayout.EAST);   // botão fixo à direita

        // rótulo de status: mensagens de feedback ao usuário
        lblStatus = new JLabel(" "); // espaço vazio inicial para não colapsar o layout
        lblStatus.setFont(Cores.FONTE_PEQUENA);
        lblStatus.setForeground(Cores.TEXTO_APAGADO);

        // empilha os componentes com espaçamentos
        topo.add(titulo);
        topo.add(Box.createVerticalStrut(12)); // espaço antes da barra de busca
        topo.add(linhaBusca);
        topo.add(Box.createVerticalStrut(6));  // espaço antes do status
        topo.add(lblStatus);

        add(topo, BorderLayout.NORTH); // adiciona o topo ao painel principal
    }

    /**
     * Monta a área de rolagem que exibirá os cartões dos resultados da busca.
     */
    private void construirAreaResultados() {
        painelResultados = new JPanel(); // painel que conterá os cartões
        painelResultados.setLayout(new BoxLayout(painelResultados, BoxLayout.Y_AXIS)); // vertical
        painelResultados.setBackground(Cores.FUNDO_PAINEL); // mesmo fundo do painel pai

        // envolve o painel em uma barra de rolagem para suportar muitos resultados
        JScrollPane scroll = new JScrollPane(painelResultados);
        scroll.setBackground(Cores.FUNDO_PAINEL);
        scroll.getViewport().setBackground(Cores.FUNDO_PAINEL); // fundo do viewport
        scroll.setBorder(null); // remove a borda padrão do scroll

        add(scroll, BorderLayout.CENTER); // ocupa todo o espaço central
    }

    /**
     * Executa a busca na API em uma thread separada usando SwingWorker.
     * Isso evita que a interface trave enquanto espera a resposta da API.
     */
    private void realizarBusca() {
        String busca = camposBusca.getText().trim(); // lê o texto digitado sem espaços extras
        if (busca.isEmpty()) { // valida que o campo não está vazio
            mostrarStatus("⚠ Digite um nome para buscar.", Cores.AMARELO);
            return; // interrompe sem buscar
        }

        camposBusca.setEnabled(false); // desativa o campo durante a busca (evita dupla busca)
        mostrarStatus("Buscando...", Cores.TEXTO_APAGADO); // feedback visual
        painelResultados.removeAll(); // limpa resultados anteriores
        painelResultados.revalidate(); // recalcula o layout
        painelResultados.repaint();    // redesenha o painel vazio

        // SwingWorker executa o trabalho pesado (rede) em segundo plano
        // e atualiza a interface na thread correta (Event Dispatch Thread) após concluir
        SwingWorker<List<Serie>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Serie> doInBackground() throws ErroApi {
                // este método roda em uma thread separada (não bloqueia a UI)
                return servico.buscarPorNome(busca); // faz a chamada HTTP à API TVMaze
            }

            @Override
            protected void done() {
                // este método roda na Event Dispatch Thread após o doInBackground terminar
                camposBusca.setEnabled(true); // reativa o campo de busca
                try {
                    List<Serie> resultados = get(); // obtém o resultado (pode lançar exceção)
                    exibirResultados(resultados);   // exibe os cartões na tela
                } catch (Exception ex) {
                    // pega a causa real do erro (wrapped em ExecutionException pelo SwingWorker)
                    Throwable causa = ex.getCause() != null ? ex.getCause() : ex;
                    mostrarStatus("❌ " + causa.getMessage(), Cores.DESTAQUE); // exibe o erro
                }
            }
        };
        worker.execute(); // inicia o worker em segundo plano
    }

    /**
     * Exibe os cartões de série no painel de resultados.
     * @param resultados lista de séries retornadas pela API
     */
    private void exibirResultados(List<Serie> resultados) {
        painelResultados.removeAll(); // limpa resultados anteriores antes de exibir os novos

        if (resultados.isEmpty()) { // nenhuma série encontrada
            mostrarStatus("Nenhuma série encontrada.", Cores.TEXTO_APAGADO);
        } else {
            mostrarStatus(resultados.size() + " resultado(s) encontrado(s).", Cores.VERDE);
            for (Serie s : resultados) { // percorre cada série encontrada
                // envolve o cartão em um painel para controlar o tamanho e espaçamento
                JPanel envoltorio = new JPanel(new BorderLayout());
                envoltorio.setOpaque(false); // transparente
                envoltorio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120)); // altura máxima

                // cria o cartão: ao mudar dados, salva E reexibe os resultados (atualiza botões)
                CardSerie card = new CardSerie(s, perfil, () -> {
                    aoMudarDados.run();          // salva os dados
                    exibirResultados(resultados); // reexibe para atualizar estado dos botões
                });

                envoltorio.add(card, BorderLayout.CENTER);
                envoltorio.add(Box.createVerticalStrut(8), BorderLayout.SOUTH); // espaço entre cartões
                painelResultados.add(envoltorio); // adiciona o cartão ao painel
            }
        }

        painelResultados.revalidate(); // recalcula o layout após adicionar os cartões
        painelResultados.repaint();    // redesenha o painel
    }

    /**
     * Atualiza o rótulo de status com uma mensagem e cor específicas.
     *
     * @param msg  texto da mensagem de feedback
     * @param cor  cor do texto da mensagem
     */
    private void mostrarStatus(String msg, Color cor) {
        lblStatus.setText(msg);       // define o texto do rótulo
        lblStatus.setForeground(cor); // define a cor do texto
    }
}
