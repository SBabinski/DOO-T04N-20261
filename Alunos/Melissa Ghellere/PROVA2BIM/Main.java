import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.plaf.ColorUIResource;

// onde a mágica começa ✨
public class Main {
    public static void main(String[] args) {
        
        // deixa tudo bonitinho e fofinho 
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            
            // forca algumas cores padroes de sistema pra rosa pastel
            UIManager.put("control", new ColorUIResource(255, 228, 242));
            UIManager.put("info", new ColorUIResource(255, 182, 193));
            UIManager.put("nimbusBase", new ColorUIResource(255, 105, 180));
            
        } catch (Exception e) {
            System.err.println("vish, nao carregou o tema fofo. vai o padrao mesmo (╥﹏╥)");
        }

        // abre a janela 
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}