import javax.swing.JOptionPane;

public class TelaLogin {

    // tela de login simples, se nome ja existente ele busca na persistencia
    public static String pedirNome() {
        String nome = JOptionPane.showInputDialog(
                null,
                "Como voce quer ser chamado?",
                "Login",
                JOptionPane.QUESTION_MESSAGE);

        if (nome == null || nome.isBlank()) {
            return "Visitante";
        }
        return nome.trim();
    }
}