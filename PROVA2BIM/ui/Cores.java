package tvtracker.ui; // pacote de interface gráfica

import java.awt.Color; // representa cores RGB
import java.awt.Font;  // representa fontes tipográficas

/**
 * Define todas as cores e fontes usadas na interface gráfica.
 * Centralizar aqui facilita manutenção: mudar uma cor reflete em toda a UI.
 * Segue o padrão de "design tokens" — valores reutilizáveis por todos os painéis.
 */
public final class Cores { // final: não pode ser herdada (classe utilitária)

    // --- Paleta de cores (tema escuro, estilo cinema) ---
    public static final Color FUNDO_ESCURO   = new Color(15,  15,  20);    // fundo da janela principal
    public static final Color FUNDO_PAINEL   = new Color(22,  22,  32);    // fundo dos painéis laterais
    public static final Color FUNDO_CARD     = new Color(30,  30,  44);    // fundo dos cartões de série
    public static final Color FUNDO_HOVER    = new Color(40,  40,  58);    // fundo ao passar o mouse
    public static final Color DESTAQUE       = new Color(229, 57,  53);    // vermelho principal (botões, logo)
    public static final Color DESTAQUE_SUAVE = new Color(229, 57,  53, 60); // vermelho semitransparente
    public static final Color DOURADO        = new Color(255, 193, 7);     // cor das estrelas de nota
    public static final Color TEXTO          = new Color(240, 240, 245);   // texto principal (claro)
    public static final Color TEXTO_APAGADO  = new Color(140, 140, 160);   // texto secundário (cinza)
    public static final Color BORDA          = new Color(50,  50,  68);    // cor das bordas e separadores
    public static final Color VERDE          = new Color(76,  175, 80);    // cor para "Em exibição"
    public static final Color AMARELO        = new Color(255, 152, 0);     // cor para "A definir"
    public static final Color AZUL           = new Color(33,  150, 243);   // cor para informações

    // --- Fontes tipográficas ---
    public static final Font FONTE_TITULO    = new Font("Segoe UI", Font.BOLD,  22); // títulos de tela
    public static final Font FONTE_CABECALHO = new Font("Segoe UI", Font.BOLD,  15); // nome das séries
    public static final Font FONTE_CORPO     = new Font("Segoe UI", Font.PLAIN, 13); // texto normal
    public static final Font FONTE_PEQUENA   = new Font("Segoe UI", Font.PLAIN, 11); // metadados e badges

    // Construtor privado: impede criação de instâncias (classe só tem constantes)
    private Cores() {}

    /**
     * Retorna a cor correspondente ao estado atual da série.
     * Usada para colorir badges e textos de estado.
     *
     * @param estado o estado da série vindo da API (ex: "Running", "Ended")
     * @return a cor adequada para o estado informado
     */
    public static Color corDoEstado(String estado) {
        if (estado == null)                    return TEXTO_APAGADO; // sem estado: cinza
        if (estado.equals("Running"))          return VERDE;          // em exibição: verde
        if (estado.equals("Ended"))            return TEXTO_APAGADO; // encerrada: cinza
        if (estado.equals("To Be Determined")) return AMARELO;        // a definir: amarelo
        return AZUL; // outros estados: azul informativo
    }
}
