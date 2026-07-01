package ListTV;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class PainelLista extends JPanel {

    private final CategoriaLista categoria;      
    private final PerfilUsuario usuario;         
    private final Runnable aoMudar; 
    // Runnable é usado aqui como um "callback genérico" sem parâmetros — quando a lista muda,

    private final DefaultListModel<SerieTV> modeloLista; 
    private final JList<SerieTV> jList;                 
    private final JTextArea areaDetalhes;              
    private final JComboBox<String> comboOrdenar;      
    private final JLabel labelContagem;                

    private static final String[] CRITERIOS = {
            "Nome (alfabetica)", "Nota geral", "Estado", "Data de estreia"
    };

    public PainelLista(CategoriaLista categoria, PerfilUsuario usuario, Runnable aoMudar) {
        this.categoria = categoria;
        this.usuario = usuario;
        this.aoMudar = aoMudar;

        setLayout(new BorderLayout(8, 8)); 
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // margem interna do painel

        JPanel painelTopo = new JPanel(new BorderLayout(8, 8));

        // Painel da esquerda: label + combo de ordenação
        JPanel painelOrdenar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelOrdenar.add(new JLabel("Ordenar por:"));
        comboOrdenar = new JComboBox<>(CRITERIOS);
        comboOrdenar.addActionListener(e -> atualizar()); 
        painelOrdenar.add(comboOrdenar);
        painelTopo.add(painelOrdenar, BorderLayout.WEST);

        // Botão para remover a série selecionada da lista
        JButton botaoRemover = new JButton("Remover selecionada");
        botaoRemover.addActionListener(e -> remover());
        painelTopo.add(botaoRemover, BorderLayout.EAST);

        add(painelTopo, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>(); 
        jList = new JList<>(modeloLista);
        jList.addListSelectionListener(e -> mostrarDetalhes()); 

        JScrollPane scrollLista = new JScrollPane(jList); 
        scrollLista.setPreferredSize(new Dimension(280, 0)); 

        areaDetalhes = new JTextArea();
        areaDetalhes.setEditable(false);   
        areaDetalhes.setLineWrap(true);     
        areaDetalhes.setWrapStyleWord(true);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                scrollLista, new JScrollPane(areaDetalhes));
        split.setDividerLocation(280); // posição inicial da divisória
        add(split, BorderLayout.CENTER);

        labelContagem = new JLabel();
        add(labelContagem, BorderLayout.SOUTH);

        atualizar(); 
    }

    public void atualizar() {
        List<SerieTV> series = new ArrayList<>(usuario.getLista(categoria));
        int criterio = comboOrdenar.getSelectedIndex();
        switch (criterio) {
            case 0: OrdenadorSeries.ordenarPorNome(series); break;
            case 1: OrdenadorSeries.ordenarPorNota(series); break;
            case 2: OrdenadorSeries.ordenarPorStatus(series); break;
            case 3: OrdenadorSeries.ordenarPorEstreia(series); break;
            default: break;
        }

        modeloLista.clear();
        for (SerieTV s : series) {
            modeloLista.addElement(s);
        }
        labelContagem.setText(series.size() + " serie(s) em \"" + categoria.getRotulo() + "\".");
        areaDetalhes.setText(""); // limpa os detalhes, já que a seleção anterior pode não existir mais
    }

    private void mostrarDetalhes() {
        SerieTV s = jList.getSelectedValue();
        if (s == null) {
            areaDetalhes.setText(""); // nada selecionado: limpa a área
            return;
        }

        areaDetalhes.setText(
                "Nome: " + s.getNome() + "\n" +
                "Idioma: " + (s.getIdioma() == null ? "N/A" : s.getIdioma()) + "\n" +
                "Generos: " + s.getGenerosComoTexto() + "\n" +
                "Nota: " + s.getNotaComoTexto() + "\n" +
                "Status: " + (s.getStatus() == null ? "N/A" : s.getStatus()) + "\n" +
                "Estreia: " + (s.getEstreia() == null ? "N/A" : s.getEstreia()) + "\n" +
                "Termino: " + (s.getTermino() == null ? "N/A" : s.getTermino()) + "\n" +
                "Emissora: " + (s.getEmissora() == null ? "N/A" : s.getEmissora())
        );
    }

    private void remover() {
        SerieTV selecionada = jList.getSelectedValue();
        if (selecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma serie para remover.",
                    "Nenhuma selecao", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(this,
                "Remover \"" + selecionada.getNome() + "\" de \"" + categoria.getRotulo() + "\"?",
                "Confirmar remocao", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            usuario.removerDaLista(categoria, selecionada); 
            atualizar(); 
            if (aoMudar != null) aoMudar.run(); 
        }
    }
}

class PainelBusca extends JPanel {

    private final ApiTVMaze api;          
    private final PerfilUsuario usuario;  
    private final Runnable aoMudar;        

    private final JTextField campoBusca;          
    private final JButton botaoBuscar;             
    private final DefaultListModel<SerieTV> modeloLista; 
    private final JList<SerieTV> jList;           
    private final JTextArea areaDetalhes;        
    private final JLabel labelStatus;             

    public PainelBusca(ApiTVMaze api, PerfilUsuario usuario, Runnable aoMudar) {
        this.api = api;
        this.usuario = usuario;
        this.aoMudar = aoMudar;

        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelTopo = new JPanel(new BorderLayout(8, 8));
        campoBusca = new JTextField();
        botaoBuscar = new JButton("Buscar");
        painelTopo.add(new JLabel("Nome da serie:"), BorderLayout.WEST);
        painelTopo.add(campoBusca, BorderLayout.CENTER);
        painelTopo.add(botaoBuscar, BorderLayout.EAST);
        add(painelTopo, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        jList = new JList<>(modeloLista);
        jList.addListSelectionListener(e -> mostrarDetalhes());

        JScrollPane scrollLista = new JScrollPane(jList);
        scrollLista.setPreferredSize(new Dimension(280, 0));

        areaDetalhes = new JTextArea();
        areaDetalhes.setEditable(false);
        areaDetalhes.setLineWrap(true);
        areaDetalhes.setWrapStyleWord(true);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                scrollLista, new JScrollPane(areaDetalhes));
        split.setDividerLocation(280);
        add(split, BorderLayout.CENTER);

        JPanel painelBaixo = new JPanel(new BorderLayout());

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton botaoFavorito = new JButton("Adicionar a Favoritos");
        JButton botaoAssistida = new JButton("Adicionar a Ja Assistidas");
        JButton botaoDesejo = new JButton("Adicionar a Quero Assistir");
        painelBotoes.add(botaoFavorito);
        painelBotoes.add(botaoAssistida);
        painelBotoes.add(botaoDesejo);
        painelBaixo.add(painelBotoes, BorderLayout.NORTH);

        labelStatus = new JLabel("Digite o nome de uma serie e clique em Buscar.");
        painelBaixo.add(labelStatus, BorderLayout.SOUTH);

        add(painelBaixo, BorderLayout.SOUTH);

        botaoBuscar.addActionListener(e -> buscar());
        campoBusca.addActionListener(e -> buscar());

        botaoFavorito.addActionListener(e -> adicionar(CategoriaLista.FAVORITOS));
        botaoAssistida.addActionListener(e -> adicionar(CategoriaLista.ASSISTIDAS));
        botaoDesejo.addActionListener(e -> adicionar(CategoriaLista.DESEJO_ASSISTIR));
    }

    private void buscar() {
        String texto = campoBusca.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome de uma serie para buscar.",
                    "Campo vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }
        botaoBuscar.setEnabled(false); // desabilita o botão durante a busca, evitando cliques duplicados
        labelStatus.setText("Buscando...");

        SwingWorker<List<SerieTV>, Void> worker = new SwingWorker<>() {
            private TvMazeException erro; // guarda um possível erro ocorrido durante a busca

            @Override
            protected List<SerieTV> doInBackground() {
                try {
                    return api.buscarSerie(texto);
                } catch (TvMazeException e) {
                    erro = e;
                    return List.of(); // retorna lista vazia em caso de erro
                }
            }

            @Override
            protected void done() {
                botaoBuscar.setEnabled(true);
                if (erro != null) {
                    labelStatus.setText("Erro na busca.");
                    JOptionPane.showMessageDialog(PainelBusca.this, erro.getMessage(),
                            "Erro ao buscar series", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    List<SerieTV> resultados = get(); 
                    modeloLista.clear();
                    for (SerieTV s : resultados) {
                        modeloLista.addElement(s);
                    }
                    labelStatus.setText(resultados.isEmpty()
                            ? "Nenhuma serie encontrada para \"" + texto + "\"."
                            : resultados.size() + " serie(s) encontrada(s).");
                } catch (Exception ex) {
                    labelStatus.setText("Erro inesperado.");
                    JOptionPane.showMessageDialog(PainelBusca.this,
                            "Erro ao mostrar os resultados: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute(); 
    }

    private void mostrarDetalhes() {
        SerieTV s = jList.getSelectedValue();
        if (s == null) {
            areaDetalhes.setText("");
            return;
        }
        areaDetalhes.setText(
                "Nome: " + s.getNome() + "\n" +
                "Idioma: " + (s.getIdioma() == null ? "N/A" : s.getIdioma()) + "\n" +
                "Generos: " + s.getGenerosComoTexto() + "\n" +
                "Nota: " + s.getNotaComoTexto() + "\n" +
                "Status: " + (s.getStatus() == null ? "N/A" : s.getStatus()) + "\n" +
                "Estreia: " + (s.getEstreia() == null ? "N/A" : s.getEstreia()) + "\n" +
                "Termino: " + (s.getTermino() == null ? "N/A" : s.getTermino()) + "\n" +
                "Emissora: " + (s.getEmissora() == null ? "N/A" : s.getEmissora())
        );
    }

    private void adicionar(CategoriaLista categoria) {
        SerieTV selecionada = jList.getSelectedValue();
        if (selecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma serie na lista de resultados.",
                    "Nenhuma selecao", JOptionPane.WARNING_MESSAGE);
            return;
        }
        boolean adicionou = usuario.adicionarNaLista(categoria, selecionada);
        if (adicionou) {
            JOptionPane.showMessageDialog(this,
                    "\"" + selecionada.getNome() + "\" adicionada a " + categoria.getRotulo() + ".");
            if (aoMudar != null) aoMudar.run(); 
        } else {
            // série já estava na lista: apenas informa, sem duplicar
            JOptionPane.showMessageDialog(this,
                    "\"" + selecionada.getNome() + "\" ja esta em " + categoria.getRotulo() + ".",
                    "Ja adicionada", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}