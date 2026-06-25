import javax.swing.SwingUtilities;

/**
 * Ponto de entrada da aplicação Calculadora.
 * Inicializa a interface gráfica na Event Dispatch Thread (EDT) do Swing.
 */
public class Main {

    public static void main(String[] args) {
        // Garante que a GUI seja criada na thread correta do Swing
        SwingUtilities.invokeLater(() -> {
            try {
                // Tenta usar o visual nativo do sistema operacional
                javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception e) {
                // Se não conseguir, usa o padrão do Swing (sem problema)
                System.out.println("Look and feel padrão será utilizado.");
            }

            new CalculadoraGUI();
        });
    }
}
