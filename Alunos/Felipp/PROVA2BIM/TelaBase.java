package Fag.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// classe base de todas as janelas. centraliza as configuracoes comuns e
// os metodos de mensagem. as telas concretas herdam dela (heranca).
public abstract class TelaBase extends JFrame {

    protected TelaBase(String titulo, int largura, int altura) {
        super(titulo);
        setSize(largura, altura);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // mensagens reaproveitadas por todas as telas, evitando codigo repetido.
    protected void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "erro", JOptionPane.ERROR_MESSAGE);
    }

    protected void mostrarInfo(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "aviso", JOptionPane.INFORMATION_MESSAGE);
    }
}
