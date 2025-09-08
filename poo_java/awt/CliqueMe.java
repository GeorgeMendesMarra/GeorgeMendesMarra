import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CliqueMe {

    public static void main(String[] args) {
        // 1. Cria a instância da janela (Frame)
        // O Frame é o componente de nível superior no AWT, representando a janela principal da aplicação.
        Frame frame = new Frame("Exemplo Simples de AWT (comentários detalhados)");

        // 2. Cria um componente: o botão
        // O Button é um dos componentes gráficos básicos que pode ser adicionado à janela.
        Button botao = new Button("Clique-me!");

        // 3. Adiciona um "ouvinte" de eventos ao botão (ActionListener)
        // Um ActionListener é uma interface que "ouve" por ações (como um clique do mouse).
        // Quando a ação ocorre, o método actionPerformed() é chamado.
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Esta linha é executada sempre que o botão é clicado.
                System.out.println("O botão foi clicado!");
            }
        });

        // 4. Adiciona o botão à janela
        // O método add() do Frame é usado para colocar o componente dentro da janela.
        frame.add(botao);

        // 5. Define o layout da janela
        // Um "layout manager" (gerenciador de layout) é responsável por organizar a posição e o tamanho dos componentes.
        // O FlowLayout organiza os componentes em uma linha, da esquerda para a direita.
        frame.setLayout(new FlowLayout());

        // 6. Define o tamanho da janela em pixels
        // O setSize() define a largura e a altura da janela na tela.
        frame.setSize(300, 150);

        // 7. Adiciona um "ouvinte" de eventos à janela (WindowAdapter)
        // Este é um passo crucial. Sem isso, a aplicação continuaria rodando em segundo plano
        // mesmo depois de você fechar a janela.
        // O WindowAdapter é uma classe de conveniência para tratar eventos de janela.
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // O método dispose() libera os recursos da tela e fecha a janela.
                // É a forma correta de terminar a aplicação AWT.
                frame.dispose();
            }
        });

        // 8. Torna a janela visível
        // Por padrão, a janela é criada invisível. O setVisible(true) a exibe na tela.
        frame.setVisible(true);
    }
}
