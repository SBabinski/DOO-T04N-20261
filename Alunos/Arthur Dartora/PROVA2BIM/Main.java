import java.awt.*;
import java.util.*;
import javax.swing.*;

// main monta as telas com swing e inicia tudo
public class Main extends JFrame {

    private static final String ARQUIVO = "dados_series.json";

    private Usuario usuario;
    private PersistenciaJson persistencia;
    private ServicoDeSeries servico; // tipo da interface

    private JTextField campoBusca;
    private DefaultListModel<Serie> modeloBusca;
    private JList<Serie> listaBusca;
    private JLabel mensagemBusca;

    private java.util.List<PainelLista> paineis;

    public Main(Usuario usuario, PersistenciaJson persistencia, ServicoDeSeries servico) {
        this.usuario = usuario;
        this.persistencia = persistencia;
        this.servico = servico;
        this.paineis = new ArrayList<PainelLista>();

        setTitle("Acompanhamento de Series");
        setSize(760, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // fechar a janela, mas salva antes de sair
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evento) {
                salvar();
                System.exit(0);
            }
        });

        montarTela();
    }

    private void montarTela() {
        JLabel topo = new JLabel("Usuario: " + usuario.getNome());
        topo.setFont(topo.getFont().deriveFont(Font.BOLD, 14f));
        topo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(topo, BorderLayout.NORTH);

        JTabbedPane abas = new JTabbedPane();

        //listas filhas diferentes, mas o painel usa ListaDeSeries
        PainelLista painelFavoritos = new PainelLista(usuario.getFavoritos());
        PainelLista painelAssistidas = new PainelLista(usuario.getAssistidas());
        PainelLista painelDesejo = new PainelLista(usuario.getDesejaAssistir());

        paineis.add(painelFavoritos);
        paineis.add(painelAssistidas);
        paineis.add(painelDesejo);

        abas.addTab("Buscar series", criarPainelBusca());
        abas.addTab("Favoritos", painelFavoritos);
        abas.addTab("Ja assistidas", painelAssistidas);
        abas.addTab("Desejo assistir", painelDesejo);

        add(abas, BorderLayout.CENTER);
    }

    private JPanel criarPainelBusca() {
        JPanel painel = new JPanel(new BorderLayout(8, 8));

        campoBusca = new JTextField(24);
        modeloBusca = new DefaultListModel<Serie>();
        listaBusca = new JList<Serie>(modeloBusca);
        mensagemBusca = new JLabel("Digite o nome de uma serie e clique em Buscar.");

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botaoBuscar = new JButton("Buscar");
        topo.add(new JLabel("Nome da serie:"));
        topo.add(campoBusca);
        topo.add(botaoBuscar);
        painel.add(topo, BorderLayout.NORTH);

        listaBusca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        painel.add(new JScrollPane(listaBusca), BorderLayout.CENTER);

        JPanel baixo = new JPanel(new BorderLayout());
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botaoDetalhes = new JButton("Ver detalhes");
        JButton botaoFavorito = new JButton("Adicionar aos favoritos");
        JButton botaoAssistida = new JButton("Adicionar a ja assistidas");
        JButton botaoDesejo = new JButton("Adicionar a desejo assistir");

        botoes.add(botaoDetalhes);
        botoes.add(botaoFavorito);
        botoes.add(botaoAssistida);
        botoes.add(botaoDesejo);

        baixo.add(mensagemBusca, BorderLayout.NORTH);
        baixo.add(botoes, BorderLayout.CENTER);
        painel.add(baixo, BorderLayout.SOUTH);

        botaoBuscar.addActionListener(e -> buscarSeries());
        campoBusca.addActionListener(e -> buscarSeries());

        botaoDetalhes.addActionListener(e -> mostrarDetalhes(listaBusca.getSelectedValue()));
        botaoFavorito.addActionListener(e -> adicionarNaLista(usuario.getFavoritos()));
        botaoAssistida.addActionListener(e -> adicionarNaLista(usuario.getAssistidas()));
        botaoDesejo.addActionListener(e -> adicionarNaLista(usuario.getDesejaAssistir()));

        return painel;
    }

    private void buscarSeries() {
        String nome = campoBusca.getText();

        if (nome == null || nome.trim().isEmpty()) {
            mostrarAviso("Digite o nome da serie.");
            return;
        }

        // tratamento de excecoes p erro na api
        try {
            mensagemBusca.setText("Buscando...");
            modeloBusca.clear();

            java.util.List<Serie> encontradas = servico.buscar(nome);

            for (Serie serie : encontradas) {
                modeloBusca.addElement(serie);
            }

            if (encontradas.isEmpty()) {
                mensagemBusca.setText("Nenhuma serie encontrada.");
            } else {
                mensagemBusca.setText(encontradas.size() + " serie(s) encontrada(s).");
            }
        } catch (Exception erro) {
            mensagemBusca.setText("Erro na busca.");
            mostrarErro("Nao foi possivel buscar as series.\n" + erro.getMessage());
        }
    }

    // recebe ListaDeSeries e adiciona a serie selecionada
    private void adicionarNaLista(ListaDeSeries lista) {
        Serie serie = listaBusca.getSelectedValue();

        if (serie == null) {
            mostrarAviso("Selecione uma serie primeiro.");
            return;
        }

        if (lista.adicionar(serie)) {
            salvar();
            atualizarListas();
            JOptionPane.showMessageDialog(this, "Serie adicionada em " + lista.getTitulo() + ".");
        } else {
            mostrarAviso("Essa serie ja esta nessa lista.");
        }
    }

    private void atualizarListas() {
        for (PainelLista painel : paineis) {
            painel.atualizar();
        }
    }

    private void salvar() {
        try {
            persistencia.salvar(usuario);
        } catch (Exception erro) {
            mostrarErro("Nao foi possivel salvar os dados.\n" + erro.getMessage());
        }
    }

    private void mostrarAviso(String texto) {
        JOptionPane.showMessageDialog(this, texto, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private void mostrarErro(String texto) {
        JOptionPane.showMessageDialog(this, texto, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarDetalhes(Serie serie) {
        if (serie == null) {
            mostrarAviso("Selecione uma serie.");
            return;
        }

        String texto = "";
        texto += "Nome: " + mostrar(serie.getNome()) + "\n";
        texto += "Idioma: " + mostrar(serie.getIdioma()) + "\n";
        texto += "Generos: " + mostrarGeneros(serie.getGeneros()) + "\n";
        texto += "Nota geral: " + mostrarNota(serie.getNotaGeral()) + "\n";
        texto += "Estado: " + mostrar(serie.getEstado()) + "\n";
        texto += "Data de estreia: " + mostrar(serie.getDataEstreia()) + "\n";
        texto += "Data de termino: " + mostrar(serie.getDataTermino()) + "\n";
        texto += "Emissora: " + mostrar(serie.getEmissora()) + "\n";
        texto += "Resumo: " + mostrar(serie.getResumo()) + "\n";

        JTextArea area = new JTextArea(texto);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane rolagem = new JScrollPane(area);
        rolagem.setPreferredSize(new Dimension(420, 260));

        JOptionPane.showMessageDialog(this, rolagem, "Detalhes da serie", JOptionPane.INFORMATION_MESSAGE);
    }

    private String mostrar(String valor) {
        if (valor == null || valor.isEmpty()) {
            return "nao informado";
        }
        return valor;
    }

    private String mostrarNota(Double nota) {
        if (nota == null) {
            return "sem nota";
        }
        return nota.toString();
    }

    private String mostrarGeneros(java.util.List<String> generos) {
        if (generos == null || generos.isEmpty()) {
            return "nao informado";
        }
        return String.join(", ", generos);
    }

    //Painel de lista favoritos/assistidas/deseja assistir
    //extende JPanel
    private class PainelLista extends JPanel {

        private ListaDeSeries lista;
        private DefaultListModel<Serie> modelo;
        private JList<Serie> visual;
        private JComboBox<String> comboOrdem;

        public PainelLista(ListaDeSeries lista) {
            this.lista = lista;
            this.modelo = new DefaultListModel<Serie>();
            this.visual = new JList<Serie>(modelo);
            this.comboOrdem = new JComboBox<String>(new String[]{
                    "Nome", "Nota geral", "Estado", "Data de estreia"
            });

            setLayout(new BorderLayout(8, 8));

            JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
            //getTitulo() responde diferente para cada lista
            topo.add(new JLabel(lista.getTitulo() + " - ordenar por:"));
            topo.add(comboOrdem);
            add(topo, BorderLayout.NORTH);

            visual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            add(new JScrollPane(visual), BorderLayout.CENTER);

            JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton botaoDetalhes = new JButton("Ver detalhes");
            JButton botaoRemover = new JButton("Remover");
            botoes.add(botaoDetalhes);
            botoes.add(botaoRemover);
            add(botoes, BorderLayout.SOUTH);

            comboOrdem.addActionListener(e -> atualizar());
            botaoDetalhes.addActionListener(e -> mostrarDetalhes(visual.getSelectedValue()));

            botaoRemover.addActionListener(e -> {
                Serie serie = visual.getSelectedValue();
                if (serie == null) {
                    mostrarAviso("Selecione uma serie para remover.");
                    return;
                }
                lista.remover(serie);
                salvar();
                atualizar();
            });

            atualizar();
        }

        public void atualizar() {
            String criterio = (String) comboOrdem.getSelectedItem();
            // a ordenacao dentro de ListaDeSeries
            java.util.List<Serie> ordenadas = lista.ordenar(criterio);

            modelo.clear();
            for (Serie serie : ordenadas) {
                modelo.addElement(serie);
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception erro) {
            // trata erro visual
        }

        PersistenciaJson persistencia = new PersistenciaJson(ARQUIVO);
        // variavel e do tipo da interface
        ServicoDeSeries servico = new TVMazeServico();

        Usuario usuario = null;

        try {
            usuario = persistencia.carregar();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,
                    "Nao foi possivel carregar os dados salvos.\nO programa vai iniciar com novos dados.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        // primeira vez pede o nome e deixa series pre-carregadas
        if (usuario == null) {
            usuario = criarUsuario();
            colocarDadosIniciais(usuario);

            try {
                persistencia.salvar(usuario);
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null,
                        "Nao foi possivel salvar agora, mas o programa vai continuar.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }

        Main janela = new Main(usuario, persistencia, servico);
        janela.setVisible(true);
    }

    private static Usuario criarUsuario() {
        String nome = JOptionPane.showInputDialog(
                null, "Digite seu nome ou apelido:", "Usuario", JOptionPane.QUESTION_MESSAGE);

        // regex: o nome precisa ter uma letra ou numero, senao usa "Visitante"
        if (nome == null || !nome.trim().matches(".*[\\p{L}\\p{N}].*")) {
            return new Usuario("Visitante");
        }
        return new Usuario(nome.trim());
    }

    // dados pre-carregados 
    private static void colocarDadosIniciais(Usuario usuario) {
        usuario.getFavoritos().adicionar(new Serie(
                169, "Breaking Bad", "English",
                Arrays.asList("Drama", "Crime", "Thriller"),
                9.2, "Ended", "2008-01-20", "2013-09-29", "AMC",
                "Um professor de quimica do ensino medio passa a fabricar drogas."));

        usuario.getAssistidas().adicionar(new Serie(
                431, "Friends", "English",
                Arrays.asList("Comedy", "Romance"),
                8.5, "Ended", "1994-09-22", "2004-05-06", "NBC",
                "Um grupo de amigos vivendo e se divertindo em Nova York."));

        usuario.getDesejaAssistir().adicionar(new Serie(
                2993, "Stranger Things", "English",
                Arrays.asList("Drama", "Fantasy", "Science-Fiction"),
                8.4, "Running", "2016-07-15", null, "Netflix",
                "Criancas enfrentam mistérios sobrenaturais em uma cidade pequena."));
    }
}
