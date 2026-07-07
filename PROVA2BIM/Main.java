package tvtracker; // pacote raiz do projeto

import tvtracker.model.PerfilUsuario;          // modelo do perfil do usuário
import tvtracker.persistence.ErroPersistencia; // exceção de erros ao ler/salvar dados
import tvtracker.persistence.Repositorio;      // serviço de leitura e escrita em JSON
import tvtracker.ui.Cores;                     // constantes de cores e fontes da UI
import tvtracker.ui.JanelaPrincipal;           // janela principal do aplicativo
import tvtracker.ui.TelaBoasVindas;            // tela de primeiro uso (solicita o nome)

import javax.swing.*; // componentes visuais Swing (JOptionPane, UIManager, etc.)

/**
 * Classe principal do aplicativo TV Tracker.
 * Responsável por iniciar o programa, configurar o tema visual,
 * carregar o perfil do usuário e abrir a janela principal.
 */
public class Main { // classe de entrada do programa

    /**
     * Método principal — ponto de entrada da JVM ao executar o programa.
     * Delega toda a execução para a Event Dispatch Thread (EDT) do Swing,
     * garantindo que a interface gráfica seja criada na thread correta.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // invokeLater agenda a execução na EDT (thread de eventos do Swing)
        // NUNCA crie componentes Swing fora da EDT para evitar problemas de concorrência
        SwingUtilities.invokeLater(Main::iniciar);
    }

    /**
     * Inicializa o aplicativo: configura o visual, carrega o perfil e abre a janela.
     * Este método é executado na Event Dispatch Thread.
     */
    private static void iniciar() {
        configurarVisual(); // aplica o tema escuro em todos os componentes Swing

        Repositorio repositorio = new Repositorio(); // cria o serviço de persistência
        PerfilUsuario perfil    = carregarOuCriarPerfil(repositorio); // obtém o perfil

        if (perfil == null) { // usuário fechou a tela de boas-vindas sem digitar nome
            System.exit(0); // encerra o programa sem abrir a janela principal
        }

        // cria e exibe a janela principal do aplicativo
        JanelaPrincipal janela = new JanelaPrincipal(perfil, repositorio);
        janela.setVisible(true); // torna a janela visível na tela
    }

    /**
     * Tenta carregar o perfil salvo em disco.
     * Se não existir (primeiro uso), exibe a tela de boas-vindas para criar um novo.
     *
     * @param repositorio serviço de acesso aos dados persistidos
     * @return o perfil carregado ou recém-criado, ou null se o usuário cancelar
     */
    private static PerfilUsuario carregarOuCriarPerfil(Repositorio repositorio) {
        PerfilUsuario perfil = null; // começa sem perfil (será preenchido abaixo)

        // tenta ler o arquivo JSON salvo de uma execução anterior
        try {
            perfil = repositorio.carregar(); // lê e deserializa o arquivo JSON
        } catch (ErroPersistencia e) {
            // arquivo existe mas está corrompido: avisa e cria perfil novo
            JOptionPane.showMessageDialog(null,
                    "Aviso: " + e.getMessage() + "\nUm novo perfil será criado.",
                    "TV Tracker",
                    JOptionPane.WARNING_MESSAGE); // ícone de alerta
        }

        // perfil null significa que é a primeira execução (arquivo não existe)
        if (perfil == null) {
            TelaBoasVindas boasVindas = new TelaBoasVindas(null); // cria o diálogo
            boasVindas.setVisible(true); // exibe e aguarda (é modal: bloqueia aqui)

            String nome = boasVindas.getNomeUsuario(); // pega o nome digitado
            if (nome == null) return null;             // usuário fechou sem digitar: retorna null

            perfil = new PerfilUsuario(nome); // cria o perfil com o nome informado

            // salva o perfil novo imediatamente para garantir que o arquivo existe
            try {
                repositorio.salvar(perfil);
            } catch (ErroPersistencia e) {
                // avisa mas não impede o uso do programa (dados só não serão persistidos)
                JOptionPane.showMessageDialog(null,
                        "Erro ao salvar perfil: " + e.getMessage(),
                        "TV Tracker",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        return perfil; // retorna o perfil pronto para uso
    }

    /**
     * Aplica o tema visual escuro em todos os componentes Swing do aplicativo.
     * Usa UIManager.put() para sobrescrever as cores padrão de cada tipo de componente.
     */
    private static void configurarVisual() {
        try {
            // usa o look and feel multiplataforma (aparência consistente em Windows, Mac e Linux)
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Não foi possível configurar o visual: " + e.getMessage());
        }

        // sobrescreve as cores padrão de cada tipo de componente Swing
        UIManager.put("Panel.background",              Cores.FUNDO_PAINEL);   // painéis
        UIManager.put("OptionPane.background",         Cores.FUNDO_PAINEL);   // diálogos de aviso
        UIManager.put("OptionPane.messageForeground",  Cores.TEXTO);          // texto dos diálogos
        UIManager.put("Button.background",             Cores.FUNDO_CARD);     // botões
        UIManager.put("Button.foreground",             Cores.TEXTO);          // texto dos botões
        UIManager.put("Label.foreground",              Cores.TEXTO);          // rótulos
        UIManager.put("TextField.background",          Cores.FUNDO_CARD);     // campos de texto
        UIManager.put("TextField.foreground",          Cores.TEXTO);          // texto dos campos
        UIManager.put("TextField.caretForeground",     Cores.TEXTO);          // cursor dos campos
        UIManager.put("TextArea.background",           Cores.FUNDO_CARD);     // áreas de texto
        UIManager.put("TextArea.foreground",           Cores.TEXTO);          // texto das áreas
        UIManager.put("ComboBox.background",           Cores.FUNDO_CARD);     // comboboxes
        UIManager.put("ComboBox.foreground",           Cores.TEXTO);          // texto dos combos
        UIManager.put("ComboBox.selectionBackground",  Cores.DESTAQUE);       // seleção no combo
        UIManager.put("ComboBox.selectionForeground",  Cores.TEXTO);          // texto da seleção
        UIManager.put("ScrollPane.background",         Cores.FUNDO_ESCURO);   // scroll pane
        UIManager.put("Viewport.background",           Cores.FUNDO_ESCURO);   // área de rolagem
        UIManager.put("ToolTip.background",            Cores.FUNDO_CARD);     // dica de ferramenta
        UIManager.put("ToolTip.foreground",            Cores.TEXTO);          // texto da dica
        UIManager.put("Dialog.background",             Cores.FUNDO_PAINEL);   // janelas de diálogo
        UIManager.put("ToggleButton.background",       Cores.FUNDO_PAINEL);   // botões alternáveis
        UIManager.put("ToggleButton.foreground",       Cores.TEXTO);          // texto alternável

        // ativa suavização de texto (antialiasing) para fontes mais nítidas na tela
        System.setProperty("awt.useSystemAAFontSettings", "on");  // antialiasing do sistema
        System.setProperty("swing.aatext", "true");               // antialiasing no Swing
    }
}
