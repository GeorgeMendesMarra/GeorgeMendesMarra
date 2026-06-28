import javax.swing.*;
import java.awt.*;

public class BloombergBanner extends JPanel {

    private int x = 800;

    public BloombergBanner() {
        Timer timer = new Timer(20, e -> {
            x -= 3;
            if (x < -600) {
                x = getWidth();
            }
            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fundo estilo terminal
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Barra vermelha superior
        g.setColor(new Color(180, 0, 0));
        g.fillRect(0, 0, getWidth(), 40);

        // Texto superior
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 22));

        g.drawString("BLOOMBERG TERMINAL", 20, 28);


        // Texto rolando
        g.setFont(new Font("Monospaced", Font.BOLD, 30));

        g.setColor(Color.GREEN);

        g.drawString(
            "USD/BRL 5.48 ▲   IBOV 138.500 +1.25%   PETR4 38.20 ▼   VALE3 62.15 ▲",
            x,
            100
        );


        // Linha inferior
        g.setColor(Color.YELLOW);
        g.fillRect(0, 130, getWidth(), 5);


        g.setColor(Color.ORANGE);
        g.setFont(new Font("Monospaced", Font.BOLD, 18));

        g.drawString(
            "MARKET UPDATE | NEWS | FINANCE | DATA STREAM",
            20,
            170
        );
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Bloomberg Style Banner");

        frame.setSize(900,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new BloombergBanner());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
