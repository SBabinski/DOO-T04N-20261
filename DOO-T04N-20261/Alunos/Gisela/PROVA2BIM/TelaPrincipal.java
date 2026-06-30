package PROVA2BIM;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaPrincipal extends JFrame {
    private TvMazeServico servico;
    private Usuario usuario;
    private List<Serie> series; // guarda os resultados da busca

    public TelaPrincipal(Usuario usuario) {
        super("Sistema de Séries - PROVA2BIM");
        this.servico = new TvMazeServico();
        this.usuario = usuario;

        setLayout(new BorderLayout());

        JLabel mensagem = new JLabel("Bem-vindo(a/e) de volta, " + usuario.getNome());
        mensagem.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField campoBusca = new JTextField(20);
        JButton botaoBuscar = new JButton("Buscar");
        JTextArea areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        // Botões para adicionar
        JButton btnFavorito = new JButton("Adicionar aos Favoritos");
        JButton btnAssistida = new JButton("Marcar como Assistida");
        JButton btnDesejo = new JButton("Adicionar ao Desejo Assistir");
        JButton btnRemoverFavorito = new JButton("Remover Favorito");
        JButton btnOrdenarFavoritos = new JButton("Ordenar Favoritos");

        // Botões para ver listas
        JButton btnVerFavoritos = new JButton("Ver Favoritos");
        JButton btnVerAssistidas = new JButton("Ver Assistidas");
        JButton btnVerDesejo = new JButton("Ver Desejo Assistir");

        // Botão para ver detalhes
        JButton btnDetalhes = new JButton("Ver Detalhes da Série");

        JPanel painelTopo = new JPanel();
        painelTopo.add(new JLabel("Nome da série:"));
        painelTopo.add(campoBusca);
        painelTopo.add(botaoBuscar);

        JPanel painelBotoes = new JPanel(new GridLayout(2, 4));
        painelBotoes.add(btnFavorito);
        painelBotoes.add(btnAssistida);
        painelBotoes.add(btnDesejo);
        painelBotoes.add(btnRemoverFavorito);
        painelBotoes.add(btnVerFavoritos);
        painelBotoes.add(btnVerAssistidas);
        painelBotoes.add(btnVerDesejo);
        painelBotoes.add(btnOrdenarFavoritos);

        add(mensagem, BorderLayout.SOUTH);
        add(painelTopo, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.PAGE_END);
        add(btnDetalhes, BorderLayout.WEST);

        // Ação do botão Buscar
        botaoBuscar.addActionListener(e -> {
            String nome = campoBusca.getText();
            series = servico.buscarSeries(nome);
            areaResultado.setText("");
            for (int i = 0; i < series.size(); i++) {
                areaResultado.append(i + " - " + series.get(i).toString() + "\n");
            }
        });

        // Ações dos botões de adicionar
        btnFavorito.addActionListener(e -> adicionarSerie(usuario.getFavoritos(), "Favoritos"));
        btnAssistida.addActionListener(e -> adicionarSerie(usuario.getAssistidas(), "Assistidas"));
        btnDesejo.addActionListener(e -> adicionarSerie(usuario.getDesejoAssistir(), "Desejo Assistir"));


        // Ações dos botões de ver listas
        btnVerFavoritos.addActionListener(e -> mostrarLista(usuario.getFavoritos(), "Favoritos"));
        btnRemoverFavorito.addActionListener(e -> removerSerie(usuario.getFavoritos(), "Favoritos"));
        btnVerAssistidas.addActionListener(e -> mostrarLista(usuario.getAssistidas(), "Assistidas"));
        btnVerDesejo.addActionListener(e -> mostrarLista(usuario.getDesejoAssistir(), "Desejo Assistir"));

        // Ação para ordenar alfabeticamente os favoritos
        btnOrdenarFavoritos.addActionListener(e -> ordenarAlfabeticamente(usuario.getFavoritos(), "Favoritos"));

        // Ação do botão de detalhes
        btnDetalhes.addActionListener(e -> verDetalhes());

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void adicionarSerie(List<Serie> lista, String nomeLista) {
        try {
            String input = JOptionPane.showInputDialog(this, "Digite o índice da série:");
            int indice = Integer.parseInt(input);
            if (indice >= 0 && indice < series.size()) {
                Serie selecionada = series.get(indice);
                lista.add(selecionada);
                JsonService.salvar(usuario);
                JOptionPane.showMessageDialog(this, "Adicionado em " + nomeLista + "!");
            } else {
                JOptionPane.showMessageDialog(this, "Índice inválido.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
     private void removerSerie(List<Serie> lista, String nomeLista) {
        try {
            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, nomeLista + " está vazio.");
                return;
            }
            String input = JOptionPane.showInputDialog(this, "Digite o índice da série na lista " + nomeLista + ":");
            int indice = Integer.parseInt(input);
            if (indice >= 0 && indice < lista.size()) {
                Serie selecionada = lista.get(indice);
                lista.remove(selecionada);
                JsonService.salvar(usuario);
                JOptionPane.showMessageDialog(this, "Removido de " + nomeLista + "!");
            } else {
                JOptionPane.showMessageDialog(this, "Índice inválido.");
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Entrada inválida: digite um número.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void ordenarAlfabeticamente(List<Serie> lista, String nomeLista) {
        try {
            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, nomeLista + " está vazio.");
                return;
            }
            lista.sort((a, b) -> a.getNome().compareToIgnoreCase(b.getNome()));
            JsonService.salvar(usuario);
            JOptionPane.showMessageDialog(this, nomeLista + " ordenado alfabeticamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao ordenar: " + ex.getMessage());
        }
    }

    private void mostrarLista(List<Serie> lista, String nomeLista) {
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, nomeLista + " está vazio.");
        } else {
            StringBuilder sb = new StringBuilder(nomeLista + ":\n");
            for (Serie s : lista) {
                sb.append(s.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }

    private void verDetalhes() {
        try {
            String input = JOptionPane.showInputDialog(this, "Digite o índice da série para ver detalhes:");
            int indice = Integer.parseInt(input);
            if (indice >= 0 && indice < series.size()) {
                Serie s = series.get(indice);
                JOptionPane.showMessageDialog(this,
                    "Título: " + s.getNome() + "\n" +
                    "Idioma: " + s.getIdioma() + "\n" +
                    "Nota: " + s.getNota() + "\n" +
                    "Resumo: " + s.getResumo()
                );
            } else {
                JOptionPane.showMessageDialog(this, "Índice inválido.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
