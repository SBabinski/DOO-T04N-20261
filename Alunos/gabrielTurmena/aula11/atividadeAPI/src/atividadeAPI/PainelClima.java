package atividadeAPI;

import java.awt.*;
import javax.swing.*;

public class PainelClima extends JPanel {

    private String condicao;

    public PainelClima(String condicao) {
        this.condicao = condicao.toLowerCase();
        setPreferredSize(new Dimension(300, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (condicao.contains("clear")
                || condicao.contains("sol")
                || condicao.contains("clara")
                || condicao.contains("claro")
                || condicao.contains("condições claras")) {
            desenharSol(g2);

       

        } else if (condicao.contains("rain")
                || condicao.contains("chuva")
                || condicao.contains("Chuvoso")) {

            desenharChuva(g2);
            
        } else if (condicao.contains("cloud")
                || condicao.contains("nublado"))
        	
        {

            desenharNuvem(g2);

        } else if (condicao.contains("storm")
                || condicao.contains("tempestade")) {

            desenharTempestade(g2);

        } else if (condicao.contains("snow")
                || condicao.contains("neve")) {

            desenharNeve(g2);

        } else {
            desenharNuvem(g2);
        }
    }
 // pesquisei desenhos achei que não podia usar SVG, JPG, PNG pronto....
    private void desenharSol(Graphics2D g2) {

        g2.setColor(Color.YELLOW);

        g2.fillOval(100, 40, 80, 80);

        for (int i = 0; i < 360; i += 30) {

            double angulo = Math.toRadians(i);

            int x1 = 140 + (int) (50 * Math.cos(angulo));
            int y1 = 80 + (int) (50 * Math.sin(angulo));

            int x2 = 140 + (int) (70 * Math.cos(angulo));
            int y2 = 80 + (int) (70 * Math.sin(angulo));

            g2.drawLine(x1, y1, x2, y2);
        }
    }

    private void desenharNuvem(Graphics2D g2) {

        g2.setColor(Color.LIGHT_GRAY);

        g2.fillOval(80, 70, 60, 60);
        g2.fillOval(120, 50, 70, 70);
        g2.fillOval(170, 70, 60, 60);
        g2.fillRect(100, 90, 100, 40);
    }

    private void desenharChuva(Graphics2D g2) {

        desenharNuvem(g2);

        g2.setColor(Color.BLUE);

        for (int i = 90; i <= 190; i += 20) {
            g2.drawLine(i, 130, i - 10, 160);
        }
    }

    private void desenharTempestade(Graphics2D g2) {

        desenharNuvem(g2);

        g2.setColor(Color.YELLOW);

        int[] x = {140, 120, 150, 130, 170};
        int[] y = {120, 170, 170, 210, 150};

        g2.fillPolygon(x, y, 5);
    }

    private void desenharNeve(Graphics2D g2) {

        desenharNuvem(g2);

        g2.setColor(Color.CYAN);

        for (int i = 90; i <= 190; i += 25) {

            g2.drawLine(i, 130, i, 160);
            g2.drawLine(i - 10, 145, i + 10, 145);

            g2.drawLine(i - 8, 137, i + 8, 153);
            g2.drawLine(i + 8, 137, i - 8, 153);
        }
    }
}