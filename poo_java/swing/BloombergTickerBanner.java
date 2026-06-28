import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingUtilities;

public class BloombergTickerBanner extends JPanel implements ActionListener {

    // Configurações da Janela e do Texto
    private final int LARGURA_TELA = 800;
    private final int ALTURA_TELA = 50;
    private int xPosicao = LARGURA_TELA; // Começa na borda direita
    private final int VELOCIDADE = 2;   // Quantidade de pixels movidos por frame
    private Timer timer;

    // Dados das cotações formatados no estilo Bloomberg
    private final String textoTicker = "PETR4 36.42 ▲ +1.25%      VALE3 62.80 ▼ -0.85%      ITUB4 32.15 ▲ +0.45%      BBAS3 28.90 ▲ +2.10%      MGLU3 1.95 ▼ -4.20%      AAPL 175.40 ▲ +0.15%";

    public BloombergTickerBanner() {
        // Define o tamanho e o fundo do painel (Preto Bloomberg)
        this.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        this.setBackground(Color.BLACK);

        // Inicializa o Timer: Executa a cada 16ms (~60 frames por segundo)
        this.timer = new Timer(16, this);
        this.timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Ativa o Anti-aliasing para o texto não ficar serrilhado (suaviza o movimento)
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Configura a fonte (Monospaced para evitar trepidação)
        g2d.setFont(new Font("Monospaced", Font.BOLD, 16));

        // Como o Swing desenha tudo em uma string contínua, precisamos separar para colorir
        String[] partes = textoTicker.split("      ");
        int xAtual = xPosicao;

        for (String parte : partes) {
            // Desenha o Nome da Ação e o Valor em Branco
            g2d.setColor(Color.WHITE);
            // Pega apenas a fatia do texto antes do símbolo de subida/descida
            int indiceSimbolo = parte.indexOf("▲") != -1 ? parte.indexOf("▲") : parte.indexOf("▼");
            
            if (indiceSimbolo != -1) {
                String ativoEValor = parte.substring(0, indiceSimbolo);
                String variacao = parte.substring(indiceSimbolo);

                g2d.drawString(ativoEValor, xAtual, 30);
                xAtual += g2d.getFontMetrics().stringWidth(ativoEValor);

                // Define a cor com base na variação (Verde ou Vermelho)
                if (variacao.contains("▲")) {
                    g2d.setColor(new Color(0, 255, 0)); // Verde brilhante
                } else {
                    g2d.setColor(new Color(255, 50, 50)); // Vermelho
                }
                g2d.drawString(variacao, xAtual, 30);
                xAtual += g2d.getFontMetrics().stringWidth(variacao);
            }
            
            // Espaçamento entre os blocos de ações
            xAtual += g2d.getFontMetrics().stringWidth("      ");
        }

        // Se todo o texto sumir pela esquerda, reinicia na direita
        int larguraTotalTexto = g2d.getFontMetrics().stringWidth(textoTicker);
        if (xPosicao < -larguraTotalTexto) {
            xPosicao = LARGURA_TELA;
        }
    }

    // Método chamado pelo Timer a cada 16ms
    @Override
    public void actionPerformed(ActionEvent e) {
        xPosicao -= VELOCIDADE; // Move o texto para a esquerda
        repaint();              // Força o Swing a redesenhar a tela
    }

    // Método principal para abrir a janela
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bloomberg Style Ticker (Swing)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new BloombergTickerBanner());
            frame.pack();
            frame.setLocationRelativeTo(null); // Centraliza na tela
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
