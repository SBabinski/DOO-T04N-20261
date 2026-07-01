package ListTV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaPrincipal extends JFrame {

    // Representa o usuário atualmente logado/usando o app (nome, listas de séries, etc.)
    private PerfilUsuario usuario;

    // Cliente de acesso à API externa (TVMaze), usado para buscar séries
    private final ApiTVMaze api;

    // Responsável por salvar/carregar os dados do usuário em disco (persistência)
    private final DadosUsuario persistencia;

    // Label que exibe o nome do usuário no topo da tela
    private final JLabel labelUsuario;

    // Painéis que exibem as listas de séries por categoria (favoritos, assistidas, desejo de assistir)
    private PainelLista painelFavoritos;
    private PainelLista painelAssistidas;
    private PainelLista painelDesejo;
    
    // Construtor: monta toda a tela e inicializa os componentes
    public TelaPrincipal() {
        super("ListTV"); // define o título da janela

        this.api = new ApiTVMaze();
        this.persistencia = new DadosUsuario();
        try {
            this.usuario = persistencia.carregar();
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this,
                    "Nao foi possivel carregar os dados salvos:\n" + e.getMessage()
                            + "\n\nO sistema sera iniciado com uma lista vazia.",
                    "Erro ao carregar dados", JOptionPane.WARNING_MESSAGE);
            this.usuario = new PerfilUsuario("Convidado");
        }

        TelaLogin login = new TelaLogin(null, usuario.getNome());
        login.setVisible(true);
        if (login.getNome() != null) {
            // se o usuário digitou um nome na tela de login, atualiza o perfil
            usuario.setNome(login.getNome());
        } else if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            // se não digitou nada e também não havia nome salvo, usa Convidado como nome padrão
            usuario.setNome("Convidado");
        }
        // Configurações básicas da janela principal
        setSize(1050, 650);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //
        setLocationRelativeTo(null); // centraliza a janela na tela

        // Painel superior, dividido em duas partes: nome do usuário (esquerda) e botão (direita)
        JPanel painelTopo = new JPanel(new BorderLayout());
        labelUsuario = new JLabel("Usuario: " + usuario.getNome());
        labelUsuario.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12)); // espaçamento interno (padding)
        painelTopo.add(labelUsuario, BorderLayout.WEST);

        // Botão para trocar de usuário sem precisar reiniciar o programa
        JButton botaoTrocarUsuario = new JButton("Trocar usuario");
        botaoTrocarUsuario.addActionListener(e -> trocarUsuario()); 
        JPanel painelTopoDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelTopoDireita.add(botaoTrocarUsuario);
        painelTopo.add(painelTopoDireita, BorderLayout.EAST);

        add(painelTopo, BorderLayout.NORTH); // adiciona o painel do topo na parte de cima da janela

        // JTabbedPane cria a interface com abas (tipo navegador), cada aba com um conteúdo diferente
        JTabbedPane abas = new JTabbedPane();

        // Painel de busca de séries, recebe a API, o usuário, e um método (this::atualizarTudo)
        PainelBusca painelBusca = new PainelBusca(api, usuario, this::atualizarTudo);

        // Cada painel mostra uma categoria de séries e salva os dados automaticamente quando a lista é modificada.
        painelFavoritos = new PainelLista(CategoriaLista.FAVORITOS, usuario, this::salvarAutomaticamente);
        painelAssistidas = new PainelLista(CategoriaLista.ASSISTIDAS, usuario, this::salvarAutomaticamente);
        painelDesejo = new PainelLista(CategoriaLista.DESEJO_ASSISTIR, usuario, this::salvarAutomaticamente);

        // Adiciona cada painel como uma aba, usando o rótulo definido no enum CategoriaLista
        abas.addTab("Buscar Series", painelBusca);
        abas.addTab(CategoriaLista.FAVORITOS.getRotulo(), painelFavoritos);
        abas.addTab(CategoriaLista.ASSISTIDAS.getRotulo(), painelAssistidas);
        abas.addTab(CategoriaLista.DESEJO_ASSISTIR.getRotulo(), painelDesejo);

        // atualiza as listas sempre que o usuario troca de aba (ex: apos adicionar pela busca)
        abas.addChangeListener(e -> atualizarTudo());

        add(abas, BorderLayout.CENTER); // adiciona o conjunto de abas no centro da janela

        // salva os dados ao fechar a janela, em vez de depender de um botao "Salvar"
        // WindowAdapter é usado para "escutar" o evento de fechamento da janela (clique no X)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salvarAoFechar();
            }
        });

        salvarAutomaticamente();
    }

    // Chamado, por exemplo, quando uma série é adicionada via busca ou ao trocar de aba.
    private void atualizarTudo() {
        painelFavoritos.atualizar();
        painelAssistidas.atualizar();
        painelDesejo.atualizar();
        salvarAutomaticamente();
    }

    // Abre a tela de login novamente para trocar o usuário atual, sem fechar o programa
    private void trocarUsuario() {
        TelaLogin login = new TelaLogin(this, usuario.getNome());
        login.setVisible(true);
        if (login.getNome() != null) {
            usuario.setNome(login.getNome());
            labelUsuario.setText("Usuario: " + usuario.getNome()); // atualiza o texto exibido na tela
            salvarAutomaticamente();
        }
    }

    // Usado como callback "silencioso": se der erro, apenas avisa o usuário, mas não trava o programa
    private void salvarAutomaticamente() {
        try {
            persistencia.salvar(usuario);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this,
                    "Nao foi possivel salvar os dados automaticamente:\n" + e.getMessage(),
                    "Aviso de persistencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Método chamado especificamente quando a janela está sendo fechada (botão X)
    // Diferente de salvarAutomaticamente, este encerra o programa após tentar salvar
    private void salvarAoFechar() {
        try {
            persistencia.salvar(usuario);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this,
                    "Nao foi possivel salvar os dados ao fechar o programa:\n" + e.getMessage(),
                    "Erro ao salvar", JOptionPane.WARNING_MESSAGE);
        }
        dispose(); // libera os recursos da janela (fecha a interface gráfica)
        System.exit(0); // encerra o programa por completo
    }
}