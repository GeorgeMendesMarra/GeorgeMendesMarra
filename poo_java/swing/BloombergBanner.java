import javax.swing.*;
import java.awt.*;

/*
 * Classe principal do painel estilo Bloomberg.
 * Ela herda JPanel para desenhar a interface personalizada.
 */
public class BloombergBanner extends JPanel {

    // Posição horizontal do texto que vai se mover (ticker)
    private int x = 800;


    /*
     * Construtor do painel.
     * Aqui criamos a animação do texto passando na tela.
     */
    public BloombergBanner() {


        /*
         * Timer executa uma ação a cada 20 milissegundos.
         * Ele será responsável pelo movimento do texto.
         */
        Timer timer = new Timer(20, e -> {


            // Move o texto alguns pixels para a esquerda
            x -= 3;


            // Quando o texto sair completamente da tela,
            // volta para o lado direito e reinicia
            if (x < -600) {
                x = getWidth();
            }


            // Solicita que o painel seja redesenhado
            repaint();

        });


        // Inicia a animação
        timer.start();
    }



    /*
     * Método responsável por desenhar todos os elementos
     * gráficos do banner.
     */
    @Override
    protected void paintComponent(Graphics g) {


        // Limpa a tela antes de desenhar novamente
        super.paintComponent(g);



        /*
         * -----------------------------
         * FUNDO DO TERMINAL
         * -----------------------------
         */

        // Fundo preto estilo terminal financeiro
        g.setColor(Color.BLACK);

        // Preenche toda a área do painel
        g.fillRect(
            0,
            0,
            getWidth(),
            getHeight()
        );



        /*
         * -----------------------------
         * BARRA SUPERIOR
         * -----------------------------
         */

        // Define cor vermelha da barra
        g.setColor(
            new Color(180,0,0)
        );


        // Desenha a faixa vermelha no topo
        g.fillRect(
            0,
            0,
            getWidth(),
            40
        );



        /*
         * -----------------------------
         * TÍTULO
         * -----------------------------
         */


        // Cor do texto
        g.setColor(Color.WHITE);


        // Fonte estilo terminal
        g.setFont(
            new Font(
                "Monospaced",
                Font.BOLD,
                22
            )
        );


        // Escreve o título
        g.drawString(
            "BLOOMBERG TERMINAL",
            20,
            28
        );



        /*
         * -----------------------------
         * TICKER FINANCEIRO
         * -----------------------------
         */


        // Define fonte maior para os dados
        g.setFont(
            new Font(
                "Monospaced",
                Font.BOLD,
                30
            )
        );


        // Verde representa valores positivos
        g.setColor(Color.GREEN);



        /*
         * Texto que fica passando na tela.
         * A variável x controla a posição.
         */
        g.drawString(

            "USD/BRL 5.48 ▲   "
            + "IBOV 138.500 +1.25%   "
            + "PETR4 38.20 ▼   "
            + "VALE3 62.15 ▲",

            x,
            100
        );



        /*
         * -----------------------------
         * LINHA DIVISÓRIA
         * -----------------------------
         */

        // Linha amarela separando as áreas
        g.setColor(Color.YELLOW);


        g.fillRect(
            0,
            130,
            getWidth(),
            5
        );



        /*
         * -----------------------------
         * RODAPÉ
         * -----------------------------
         */


        // Texto inferior
        g.setColor(Color.ORANGE);


        g.setFont(
            new Font(
                "Monospaced",
                Font.BOLD,
                18
            )
        );


        g.drawString(

            "MARKET UPDATE | NEWS | FINANCE | DATA STREAM",

            20,

            170
        );

    }





    /*
     * Método principal.
     * Cria a janela e adiciona o banner.
     */
    public static void main(String[] args) {


        // Cria a janela principal
        JFrame frame =
            new JFrame(
                "Bloomberg Style Banner"
            );



        // Define tamanho da janela
        frame.setSize(
            900,
            250
        );



        // Fecha o programa ao fechar a janela
        frame.setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE
        );



        // Adiciona o painel Bloomberg
        frame.add(
            new BloombergBanner()
        );



        // Centraliza a janela
        frame.setLocationRelativeTo(null);



        // Torna a janela visível
        frame.setVisible(true);
    }
}
