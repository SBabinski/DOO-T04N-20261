package PROVA2BIM;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                iniciar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro crítico ao iniciar o aplicativo:\n" + e.getMessage(),
                        "Erro Fatal", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }

    private static void iniciar() {
        PersistenciaService persistencia = new PersistenciaService();
        TVMazeService tvMazeService = new TVMazeService();

        Usuario usuario = null;

        if (persistencia.existeDadosSalvos()) {
            try {
                usuario = persistencia.carregar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Não foi possível carregar os dados salvos:\n" + e.getMessage() +
                        "\n\nO sistema iniciará com dados padrão.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (usuario == null || usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            usuario = configurarNovoUsuario(persistencia);
        }

        if (usuario == null) {
            System.exit(0);
        }

        SeriesTrackerApp app = new SeriesTrackerApp(usuario, persistencia, tvMazeService);
        app.setVisible(true);
    }

    private static Usuario configurarNovoUsuario(PersistenciaService persistencia) {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        painel.setBackground(new Color(245, 246, 252));

        JLabel lblTitulo = new JLabel("📺 Bem-vindo ao Series Tracker!");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(40, 60, 120));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblSubtitulo = new JLabel("Seu sistema pessoal de acompanhamento de séries");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(100, 110, 140));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNome = new JLabel("Como você quer ser chamado(a)?");
        lblNome.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JTextField campoNome = new JTextField("Usuário", 20);
        campoNome.setFont(new Font("SansSerif", Font.PLAIN, 13));
        campoNome.selectAll();

        JPanel tituloPanel = new JPanel(new GridLayout(2, 1, 0, 4));
        tituloPanel.setBackground(new Color(245, 246, 252));
        tituloPanel.add(lblTitulo);
        tituloPanel.add(lblSubtitulo);

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 0, 6));
        formPanel.setBackground(new Color(245, 246, 252));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        formPanel.add(lblNome);
        formPanel.add(campoNome);

        painel.add(tituloPanel, BorderLayout.NORTH);
        painel.add(formPanel, BorderLayout.CENTER);

        int resultado = JOptionPane.showConfirmDialog(null, painel,
                "Configuração Inicial", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (resultado != JOptionPane.OK_OPTION) return null;

        String nome = campoNome.getText().trim();
        if (nome.isEmpty()) nome = "Usuário";

        Usuario usuario = new Usuario(nome);
        carregarDadosIniciais(usuario);

        try {
            persistencia.salvar(usuario);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Aviso: não foi possível salvar os dados iniciais:\n" + e.getMessage(),
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        return usuario;
    }

    private static void carregarDadosIniciais(Usuario usuario) {
        Serie breakingBad = new Serie(
            169,
            "Breaking Bad",
            "English",
            Arrays.asList("Drama", "Crime", "Thriller"),
            9.2,
            "Ended",
            "2008-01-20",
            "2013-09-29",
            "AMC",
            "Um professor de química do ensino médio diagnosticado com câncer se une " +
            "a um ex-aluno para produzir e vender metanfetamina.",
            null
        );

        Serie gameOfThrones = new Serie(
            82,
            "Game of Thrones",
            "English",
            Arrays.asList("Drama", "Adventure", "Fantasy"),
            8.9,
            "Ended",
            "2011-04-17",
            "2019-05-19",
            "HBO",
            "Nove famílias nobres travam uma guerra pelo controle das terras míticas de Westeros.",
            null
        );

        Serie strangerThings = new Serie(
            305,
            "Stranger Things",
            "English",
            Arrays.asList("Drama", "Fantasy", "Horror"),
            8.5,
            "Running",
            "2016-07-15",
            null,
            "Netflix",
            "Quando um garoto desaparece em uma pequena cidade, seus amigos e família " +
            "enfrentam forças sobrenaturais para trazê-lo de volta.",
            null
        );

        Serie theWire = new Serie(
            1190,
            "The Wire",
            "English",
            Arrays.asList("Drama", "Crime", "Thriller"),
            9.0,
            "Ended",
            "2002-06-02",
            "2008-03-09",
            "HBO",
            "Série de televisão americana ambientada e produzida em Baltimore, Maryland.",
            null
        );

        Serie theLastOfUs = new Serie(
            14702,
            "The Last of Us",
            "English",
            Arrays.asList("Drama", "Action", "Horror"),
            8.8,
            "Running",
            "2023-01-15",
            null,
            "HBO",
            "Joel é encarregado de contrabandear Ellie para fora de uma zona de quarentena " +
            "opressiva. O que começa como um pequeno trabalho logo se torna uma jornada brutal.",
            null
        );

        Serie successionSerie = new Serie(
            37780,
            "Succession",
            "English",
            Arrays.asList("Drama", "Comedy"),
            8.7,
            "Ended",
            "2018-06-03",
            "2023-05-28",
            "HBO",
            "A família Roy, dona de um dos maiores conglomerados de mídia e entretenimento " +
            "do mundo, batalha pelo controle da empresa.",
            null
        );

        Serie betterCallSaul = new Serie(
            618,
            "Better Call Saul",
            "English",
            Arrays.asList("Drama", "Crime", "Thriller"),
            8.8,
            "Ended",
            "2015-02-08",
            "2022-08-15",
            "AMC",
            "Série derivada de Breaking Bad que acompanha a transformação de Jimmy McGill no " +
            "advogado criminoso Saul Goodman.",
            null
        );

        usuario.adicionarFavorito(breakingBad);
        usuario.adicionarFavorito(gameOfThrones);

        usuario.adicionarJaAssistida(strangerThings);
        usuario.adicionarJaAssistida(theWire);
        usuario.adicionarJaAssistida(breakingBad);  
        usuario.adicionarDesejaAssistir(theLastOfUs);
        usuario.adicionarDesejaAssistir(successionSerie);
        usuario.adicionarDesejaAssistir(betterCallSaul);
    }
}
