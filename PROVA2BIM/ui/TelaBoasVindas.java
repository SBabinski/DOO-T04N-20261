package tvtracker.ui; // pacote de interface gráfica

import javax.swing.*;              // componentes visuais Swing
import javax.swing.border.EmptyBorder; // borda vazia para criar margens
import java.awt.*;                 // cores, fontes e layout

/**
 * Tela exibida na primeira execução do programa.
 * Solicita ao usuário que informe seu nome ou apelido antes de entrar no sistema.
 * É um JDialog modal: bloqueia a janela pai até ser fechada.
 */
public class TelaBoasVindas extends JDialog { // herda de JDialog (janela filha modal)

    private String nomeUsuario; // armazena o nome digitado pelo usuário

    /**
     * Construtor da tela de boas-vindas.
     * @param pai a janela pai (pode ser null na primeira execução)
     */
    public TelaBoasVindas(JFrame pai) {
        super(pai, "Bem-vindo ao TV Tracker", true); // true = modal (bloqueia o pai)
        construir(); // monta todos os componentes da tela
    }

    /**
     * Monta todos os componentes visuais da tela de boas-vindas.
     */
    private void construir() {
        setSize(400, 280);              // define o tamanho fixo da janela
        setLocationRelativeTo(null);    // centraliza na tela do monitor
        setResizable(false);            // impede redimensionamento pelo usuário
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // impede fechar sem digitar nome
        getContentPane().setBackground(Cores.FUNDO_PAINEL); // cor de fundo da janela

        // painel principal com layout em coluna vertical
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // empilha verticalmente
        painel.setBackground(Cores.FUNDO_PAINEL); // fundo escuro
        painel.setBorder(new EmptyBorder(32, 32, 32, 32)); // margem interna de 32px

        // --- Logo do aplicativo ---
        JLabel logo = new JLabel("📺 TV Tracker");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 26)); // fonte grande e negrito
        logo.setForeground(Cores.DESTAQUE);                // cor vermelha de destaque
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);    // centraliza horizontalmente

        // --- Subtítulo ---
        JLabel subtitulo = new JLabel("Seu gerenciador de séries pessoal");
        subtitulo.setFont(Cores.FONTE_CORPO);              // fonte normal
        subtitulo.setForeground(Cores.TEXTO_APAGADO);      // cor cinza suave
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza

        // --- Pergunta ao usuário ---
        JLabel pergunta = new JLabel("Como você quer ser chamado?");
        pergunta.setFont(Cores.FONTE_CABECALHO);           // fonte de subtítulo
        pergunta.setForeground(Cores.TEXTO);               // texto claro
        pergunta.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza

        // --- Campo de texto para o nome ---
        JTextField campoNome = new JTextField(); // campo de entrada de texto
        campoNome.setFont(Cores.FONTE_CORPO);              // fonte normal
        campoNome.setBackground(Cores.FUNDO_CARD);         // fundo do campo
        campoNome.setForeground(Cores.TEXTO);              // cor do texto digitado
        campoNome.setCaretColor(Cores.TEXTO);              // cor do cursor de texto
        campoNome.setBorder(BorderFactory.createCompoundBorder( // borda composta
                BorderFactory.createLineBorder(Cores.BORDA, 1), // borda externa fina
                new EmptyBorder(8, 12, 8, 12)));           // padding interno
        campoNome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // altura máxima fixa

        // --- Botão de confirmação ---
        JButton btnEntrar = new JButton("Começar →");
        btnEntrar.setFont(Cores.FONTE_CORPO);              // fonte normal
        btnEntrar.setBackground(Cores.DESTAQUE);           // fundo vermelho
        btnEntrar.setForeground(Cores.TEXTO);              // texto claro
        btnEntrar.setFocusPainted(false);                  // remove contorno de foco
        btnEntrar.setBorderPainted(false);                 // remove borda padrão do botão
        btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // cursor de mão
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza
        btnEntrar.setMaximumSize(new Dimension(200, 40)); // largura máxima

        // --- Ação de confirmação (reutilizada pelo botão e pelo Enter) ---
        Runnable confirmar = () -> {
            String nome = campoNome.getText().trim(); // lê e remove espaços do nome
            if (nome.isEmpty()) { // valida que o campo não está vazio
                JOptionPane.showMessageDialog(this,
                        "Por favor, insira um nome ou apelido.", // mensagem de aviso
                        "Nome necessário",
                        JOptionPane.WARNING_MESSAGE); // ícone de aviso
                return; // interrompe: não fecha a janela
            }
            nomeUsuario = nome; // salva o nome digitado
            dispose();          // fecha este diálogo
        };

        btnEntrar.addActionListener(e -> confirmar.run()); // clique no botão: confirma
        campoNome.addActionListener(e -> confirmar.run()); // pressionar Enter: confirma

        // --- Montagem do painel com espaçamentos ---
        painel.add(logo);
        painel.add(Box.createVerticalStrut(4));  // espaço pequeno entre logo e subtítulo
        painel.add(subtitulo);
        painel.add(Box.createVerticalStrut(24)); // espaço maior antes da pergunta
        painel.add(pergunta);
        painel.add(Box.createVerticalStrut(10)); // espaço antes do campo
        painel.add(campoNome);
        painel.add(Box.createVerticalStrut(16)); // espaço antes do botão
        painel.add(btnEntrar);

        setContentPane(painel); // define o painel como conteúdo da janela

        // foca o campo de nome assim que a janela aparecer
        SwingUtilities.invokeLater(campoNome::requestFocusInWindow);
    }

    /**
     * Retorna o nome digitado pelo usuário.
     * @return o nome informado, ou null se o usuário fechou sem digitar
     */
    public String getNomeUsuario() {
        return nomeUsuario; // retorna o nome salvo ao confirmar
    }
}
