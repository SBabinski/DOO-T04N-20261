import javax.swing.SwingUtilities;
import javax.swing.UIManager;

// classe principal que roda o programa todo
public class Main {
    public static void main(String[] args) {
        
        // tenta colocar um tema mais bonito
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // se der algum erro avisa no console e segue com o visual padrão
            System.err.println("erro ao carregar o tema nimbus. usando padrao.");
        }

        // cria e abre a tela principal
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}