# Desenvolvimento de Jogos em Java para Iniciantes

> Material didático para quem já conhece o básico de Java (variáveis, `if`, `for`, classes e métodos) e quer criar o primeiro jogo.
> Funciona em **Windows**, **Linux** e **macOS**.

---

## Sumário

1. [O que é um jogo, do ponto de vista do programador?](#1-o-que-é-um-jogo-do-ponto-de-vista-do-programador)
2. [Preparando o ambiente](#2-preparando-o-ambiente)
3. [Primeiro programa: abrindo uma janela](#3-primeiro-programa-abrindo-uma-janela)
4. [Desenhando na tela](#4-desenhando-na-tela)
5. [O Game Loop](#5-o-game-loop)
6. [Lendo o teclado](#6-lendo-o-teclado)
7. [Colisão](#7-colisão)
8. [Jogo completo: Caça-Estrelas](#8-jogo-completo-caça-estrelas)
9. [Como compilar e executar em cada sistema](#9-como-compilar-e-executar-em-cada-sistema)
10. [Problemas comuns por sistema operacional](#10-problemas-comuns-por-sistema-operacional)
11. [Boas práticas](#11-boas-práticas)
12. [Próximos passos: bibliotecas e engines](#12-próximos-passos-bibliotecas-e-engines)
13. [Exercícios](#13-exercícios)
14. [Referências](#14-referências)

---

## 1. O que é um jogo, do ponto de vista do programador?

Todo jogo, de um simples Pong a um jogo 3D gigante, repete o mesmo ciclo várias vezes por segundo:

```
┌─────────────────────────────────────────────┐
│                 GAME LOOP                   │
│                                             │
│   1. ENTRADA     → ler teclado/mouse        │
│   2. ATUALIZAR   → mover objetos, colisões, │
│                    pontuação, regras        │
│   3. DESENHAR    → mostrar tudo na tela     │
│                                             │
│   (repete ~60 vezes por segundo)            │
└─────────────────────────────────────────────┘
```

Conceitos que vamos usar:

| Conceito | O que significa |
|---|---|
| **Frame** | Uma "foto" do jogo desenhada na tela |
| **FPS** | Frames por segundo (60 FPS é o padrão para jogos 2D) |
| **Sprite** | Imagem ou forma que representa um objeto do jogo |
| **Colisão** | Detectar quando dois objetos se tocam |
| **Delta time (`dt`)** | Tempo entre um frame e outro; deixa o jogo com a mesma velocidade em qualquer computador |
| **Estado do jogo** | Em que "fase" o jogo está (jogando, pausado, fim de jogo) |

---

## 2. Preparando o ambiente

### 2.1 Instalar o JDK (Java Development Kit)

Recomendamos o **JDK 21 (LTS)**. Escolha o seu sistema:

**Windows** (PowerShell ou Prompt de Comando):

```powershell
winget install EclipseAdoptium.Temurin.21.JDK
```

Ou baixe o instalador em <https://adoptium.net>.

**macOS** (com [Homebrew](https://brew.sh)):

```bash
brew install --cask temurin@21
```

**Linux**:

```bash
# Ubuntu / Debian / Linux Mint
sudo apt update
sudo apt install openjdk-21-jdk

# Fedora
sudo dnf install java-21-openjdk-devel

# Arch Linux
sudo pacman -S jdk21-openjdk
```

> Em versões mais antigas do Ubuntu/Debian o pacote 21 pode não existir. Nesse caso use `openjdk-17-jdk`. Todo o código deste material funciona do Java 11 em diante.

### 2.2 Conferir a instalação

Abra um terminal (Windows: *Prompt de Comando* ou *PowerShell*; macOS e Linux: *Terminal*) e rode:

```bash
java -version
javac -version
```

Os dois comandos devem mostrar a versão instalada. Se `javac` não for reconhecido, o JDK não está no `PATH`: reinstale ou feche e abra o terminal novamente.

### 2.3 Escolher um editor

| Ferramenta | Windows | Linux | macOS | Observação |
|---|:-:|:-:|:-:|---|
| **IntelliJ IDEA Community** | ✅ | ✅ | ✅ | IDE completa, a mais usada com Java |
| **VS Code** + *Extension Pack for Java* | ✅ | ✅ | ✅ | Leve e simples |
| **Eclipse** | ✅ | ✅ | ✅ | Clássica |
| **NetBeans** | ✅ | ✅ | ✅ | Boa para iniciantes |

Qualquer uma serve. O código é o mesmo nos três sistemas.

### 2.4 Estrutura de pastas

Crie uma pasta para o projeto:

```
meu-jogo/
└── Jogo.java
```

---

## 3. Primeiro programa: abrindo uma janela

Usaremos **Swing**, biblioteca gráfica que já vem com o JDK. Não é necessário baixar nada.

Crie o arquivo `Janela.java`:

```java
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Janela {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame janela = new JFrame("Meu Primeiro Jogo");
            janela.setSize(800, 600);
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setLocationRelativeTo(null); // centraliza na tela
            janela.setVisible(true);
        });
    }
}
```

Execute:

```bash
javac Janela.java
java Janela
```

Uma janela vazia de 800x600 deve aparecer.

**O que cada linha faz:**

- `JFrame` é a janela.
- `SwingUtilities.invokeLater` garante que a interface seja criada na thread correta do Swing (boa prática obrigatória).
- `setDefaultCloseOperation(EXIT_ON_CLOSE)` encerra o programa ao fechar a janela.
- `setLocationRelativeTo(null)` centraliza a janela.

---

## 4. Desenhando na tela

Para desenhar, criamos um `JPanel` e sobrescrevemos o método `paintComponent`. Ele recebe um objeto `Graphics`, que funciona como o "pincel" do jogo.

```java
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Desenho extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // limpa a tela

        setBackground(Color.BLACK);

        g.setColor(Color.CYAN);
        g.fillRect(100, 100, 50, 50);       // retângulo: x, y, largura, altura

        g.setColor(Color.YELLOW);
        g.fillOval(300, 200, 60, 60);       // círculo/elipse

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Olá, jogo!", 20, 30); // texto: x, y (linha de base)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame janela = new JFrame("Desenhando");
            janela.add(new Desenho());
            janela.setSize(800, 600);
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setLocationRelativeTo(null);
            janela.setVisible(true);
        });
    }
}
```

### O sistema de coordenadas

Na tela, a origem **(0, 0)** fica no **canto superior esquerdo**. O `x` cresce para a **direita** e o `y` cresce para **baixo** (diferente do plano cartesiano da matemática).

```
(0,0) ──────────► x
  │
  │
  ▼
  y
```

---

## 5. O Game Loop

Para ter movimento, precisamos redesenhar a tela várias vezes por segundo. Com Swing, a forma mais simples é o `javax.swing.Timer`:

```java
Timer timer = new Timer(16, e -> {   // 16 ms ≈ 60 FPS
    atualizar();   // lógica do jogo
    repaint();     // pede para redesenhar
});
timer.start();
```

### Delta time: velocidade igual em qualquer PC

Se você mover o objeto "5 pixels por frame", o jogo ficará mais rápido em computadores mais velozes. O correto é mover **pixels por segundo** e multiplicar pelo tempo do frame:

```java
posicao += velocidade * dt;   // velocidade em pixels/segundo, dt em segundos
```

Como calcular `dt`:

```java
long agora = System.nanoTime();
double dt = (agora - ultimoTempo) / 1_000_000_000.0;
ultimoTempo = agora;
```

---

## 6. Lendo o teclado

Implementamos `KeyListener`. A estratégia mais simples e robusta é guardar as teclas pressionadas em um conjunto (`Set`):

```java
private final Set<Integer> teclas = new HashSet<>();

@Override
public void keyPressed(KeyEvent e)  { teclas.add(e.getKeyCode()); }

@Override
public void keyReleased(KeyEvent e) { teclas.remove(e.getKeyCode()); }

@Override
public void keyTyped(KeyEvent e) { }
```

E, no `atualizar`, apenas perguntamos se a tecla está pressionada:

```java
if (teclas.contains(KeyEvent.VK_LEFT) || teclas.contains(KeyEvent.VK_A)) {
    x -= velocidade * dt;
}
```

> **Importante:** o painel precisa de foco para receber o teclado. Use `setFocusable(true)` e `requestFocusInWindow()`.

---

## 7. Colisão

A forma mais simples é a colisão entre **retângulos** (AABB, *Axis-Aligned Bounding Box*). O Java já tem isso pronto na classe `Rectangle`:

```java
Rectangle jogador = new Rectangle(100, 100, 40, 40);
Rectangle estrela = new Rectangle(120, 110, 20, 20);

if (jogador.intersects(estrela)) {
    System.out.println("Colidiu!");
}
```

Dois retângulos colidem quando se sobrepõem tanto na horizontal quanto na vertical.

---

## 8. Jogo completo: Caça-Estrelas

Objetivo: mover o quadrado azul e coletar o máximo de estrelas amarelas em **30 segundos**.

**Controles:** setas ou `W`, `A`, `S`, `D` para mover; `ENTER` para reiniciar após o fim.

Crie o arquivo `Jogo.java`:

```java
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Jogo extends JPanel implements ActionListener, KeyListener {

    // ---------- Constantes ----------
    private static final int LARGURA = 800;
    private static final int ALTURA = 600;
    private static final int TAMANHO_JOGADOR = 40;
    private static final int TAMANHO_ESTRELA = 24;
    private static final double VELOCIDADE = 320;   // pixels por segundo
    private static final double TEMPO_TOTAL = 30;   // segundos
    private static final int QTD_ESTRELAS = 5;

    // ---------- Estado do jogo ----------
    private double jogadorX;
    private double jogadorY;
    private final List<Rectangle> estrelas = new ArrayList<>();
    private final Set<Integer> teclas = new HashSet<>();
    private final Random sorteio = new Random();
    private int pontos;
    private double tempoRestante;
    private boolean fimDeJogo;

    // ---------- Controle do loop ----------
    private final Timer timer;
    private long ultimoTempo;

    public Jogo() {
        setPreferredSize(new java.awt.Dimension(LARGURA, ALTURA));
        setBackground(new Color(20, 20, 40));
        setFocusable(true);
        addKeyListener(this);

        reiniciar();

        ultimoTempo = System.nanoTime();
        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    // ---------- Preparar / reiniciar ----------
    private void reiniciar() {
        jogadorX = LARGURA / 2.0 - TAMANHO_JOGADOR / 2.0;
        jogadorY = ALTURA / 2.0 - TAMANHO_JOGADOR / 2.0;
        pontos = 0;
        tempoRestante = TEMPO_TOTAL;
        fimDeJogo = false;
        estrelas.clear();
        for (int i = 0; i < QTD_ESTRELAS; i++) {
            estrelas.add(novaEstrela());
        }
    }

    private Rectangle novaEstrela() {
        int x = sorteio.nextInt(LARGURA - TAMANHO_ESTRELA);
        int y = sorteio.nextInt(ALTURA - TAMANHO_ESTRELA);
        return new Rectangle(x, y, TAMANHO_ESTRELA, TAMANHO_ESTRELA);
    }

    // ---------- GAME LOOP: chamado a cada ~16 ms ----------
    @Override
    public void actionPerformed(ActionEvent e) {
        long agora = System.nanoTime();
        double dt = (agora - ultimoTempo) / 1_000_000_000.0;
        ultimoTempo = agora;

        atualizar(dt);
        repaint();
    }

    // ---------- ATUALIZAR: regras do jogo ----------
    private void atualizar(double dt) {
        if (fimDeJogo) {
            return;
        }

        // Movimento
        double dx = 0;
        double dy = 0;
        if (teclas.contains(KeyEvent.VK_LEFT) || teclas.contains(KeyEvent.VK_A)) dx -= 1;
        if (teclas.contains(KeyEvent.VK_RIGHT) || teclas.contains(KeyEvent.VK_D)) dx += 1;
        if (teclas.contains(KeyEvent.VK_UP) || teclas.contains(KeyEvent.VK_W)) dy -= 1;
        if (teclas.contains(KeyEvent.VK_DOWN) || teclas.contains(KeyEvent.VK_S)) dy += 1;

        // Normaliza para a diagonal não ser mais rápida
        if (dx != 0 && dy != 0) {
            dx *= 0.7071;
            dy *= 0.7071;
        }

        jogadorX += dx * VELOCIDADE * dt;
        jogadorY += dy * VELOCIDADE * dt;

        // Limites da tela
        jogadorX = Math.max(0, Math.min(jogadorX, LARGURA - TAMANHO_JOGADOR));
        jogadorY = Math.max(0, Math.min(jogadorY, ALTURA - TAMANHO_JOGADOR));

        // Colisão com estrelas
        Rectangle caixaJogador = new Rectangle(
                (int) jogadorX, (int) jogadorY, TAMANHO_JOGADOR, TAMANHO_JOGADOR);

        int coletadas = 0;
        Iterator<Rectangle> it = estrelas.iterator();
        while (it.hasNext()) {
            Rectangle estrela = it.next();
            if (caixaJogador.intersects(estrela)) {
                it.remove();
                pontos++;
                coletadas++;
            }
        }
        for (int i = 0; i < coletadas; i++) {
            estrelas.add(novaEstrela()); // repõe as estrelas coletadas
        }

        // Tempo
        tempoRestante -= dt;
        if (tempoRestante <= 0) {
            tempoRestante = 0;
            fimDeJogo = true;
        }
    }

    // ---------- DESENHAR ----------
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Estrelas
        g2.setColor(Color.YELLOW);
        for (Rectangle estrela : estrelas) {
            g2.fillOval(estrela.x, estrela.y, estrela.width, estrela.height);
        }

        // Jogador
        g2.setColor(new Color(80, 180, 255));
        g2.fillRect((int) jogadorX, (int) jogadorY, TAMANHO_JOGADOR, TAMANHO_JOGADOR);

        // Placar
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("SansSerif", Font.BOLD, 22));
        g2.drawString("Pontos: " + pontos, 20, 35);
        g2.drawString(String.format("Tempo: %.1f s", tempoRestante), LARGURA - 190, 35);

        // Tela de fim de jogo
        if (fimDeJogo) {
            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRect(0, 0, LARGURA, ALTURA);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("SansSerif", Font.BOLD, 48));
            centralizar(g2, "FIM DE JOGO", ALTURA / 2 - 20);

            g2.setFont(new Font("SansSerif", Font.PLAIN, 26));
            centralizar(g2, "Você coletou " + pontos + " estrelas", ALTURA / 2 + 30);
            centralizar(g2, "Pressione ENTER para jogar de novo", ALTURA / 2 + 70);
        }

        // Evita engasgos de animação em alguns sistemas Linux
        Toolkit.getDefaultToolkit().sync();
    }

    private void centralizar(Graphics2D g2, String texto, int y) {
        FontMetrics fm = g2.getFontMetrics();
        int x = (LARGURA - fm.stringWidth(texto)) / 2;
        g2.drawString(texto, x, y);
    }

    // ---------- ENTRADA (teclado) ----------
    @Override
    public void keyPressed(KeyEvent e) {
        teclas.add(e.getKeyCode());
        if (fimDeJogo && e.getKeyCode() == KeyEvent.VK_ENTER) {
            reiniciar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        teclas.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // não utilizado
    }

    // ---------- main ----------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame janela = new JFrame("Caça-Estrelas");
            Jogo jogo = new Jogo();
            janela.add(jogo);
            janela.pack();                       // ajusta ao tamanho preferido do painel
            janela.setResizable(false);
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setLocationRelativeTo(null);
            janela.setVisible(true);
            jogo.requestFocusInWindow();         // garante o foco do teclado
        });
    }
}
```

### Entendendo a estrutura

| Parte do código | Papel no jogo |
|---|---|
| Constantes (`LARGURA`, `VELOCIDADE`...) | Facilitam ajustar o jogo sem caçar números no código |
| Atributos de estado | Guardam tudo o que muda: posição, pontos, tempo |
| `actionPerformed` | É o **game loop**, chamado pelo `Timer` |
| `atualizar(dt)` | Entrada → movimento → colisão → regras |
| `paintComponent` | Apenas **desenha**; não deve alterar o estado |
| `keyPressed` / `keyReleased` | Registram as teclas pressionadas |

> **Regra de ouro:** separe **atualizar** (lógica) de **desenhar** (visual). Isso deixa o código organizado e evita bugs.

---

## 9. Como compilar e executar em cada sistema

Entre na pasta do projeto pelo terminal e execute:

**Windows (PowerShell / Prompt de Comando)**

```powershell
cd C:\Users\SeuNome\meu-jogo
javac Jogo.java
java Jogo
```

**Linux e macOS (Terminal)**

```bash
cd ~/meu-jogo
javac Jogo.java
java Jogo
```

**Atalho (Java 11 ou superior), sem compilar manualmente:**

```bash
java Jogo.java
```

### Gerando um arquivo `.jar` executável

```bash
javac Jogo.java
jar cfe jogo.jar Jogo *.class
java -jar jogo.jar
```

O `.jar` pode ser enviado para qualquer pessoa que tenha Java instalado, em qualquer sistema operacional. É a grande vantagem do Java: *escreva uma vez, rode em qualquer lugar*.

---

## 10. Problemas comuns por sistema operacional

| Sintoma | Sistema | Solução |
|---|---|---|
| `'javac' não é reconhecido como comando` | Windows | O JDK não está no `PATH`. Reinstale marcando a opção de adicionar ao PATH e reabra o terminal |
| `javac: command not found` | Linux/macOS | Instale o **JDK** (não apenas o JRE) e reabra o terminal |
| Teclado não responde | Todos | Clique na janela do jogo; confirme `setFocusable(true)` e `requestFocusInWindow()` |
| Animação com "engasgos" | Linux | Mantenha a linha `Toolkit.getDefaultToolkit().sync();` no final do `paintComponent` |
| Movimento travando ao segurar duas teclas | Alguns teclados | Limitação de hardware (*ghosting*); teste com WASD e setas |
| Imagem borrada em tela de alta densidade | macOS (Retina) / Windows com escala | Normal no Swing; considere JavaFX ou LibGDX para jogos mais avançados |
| Pasta com espaço ou acento no caminho | Todos | Use aspas no terminal (`cd "Meus Jogos"`) ou evite espaços e acentos nos nomes de pastas |
| `Error: Could not find or load main class` | Todos | Rode o `java` na mesma pasta dos `.class` e **sem** a extensão (`java Jogo`, não `java Jogo.class`) |
| `Permission denied` ao executar scripts | Linux/macOS | Use `chmod +x arquivo.sh` |

---

## 11. Boas práticas

1. **Separe lógica e desenho.** `atualizar()` nunca desenha; `paintComponent()` nunca muda regras.
2. **Use delta time.** Movimento em pixels por segundo, nunca em pixels por frame.
3. **Use constantes** para tamanhos, velocidades e tempos.
4. **Crie classes** conforme o jogo cresce (`Jogador`, `Estrela`, `Inimigo`), aplicando orientação a objetos.
5. **Não crie objetos pesados dentro do loop** sempre que puder (por exemplo, carregar imagens a cada frame).
6. **Versione o código com Git** desde o primeiro dia.
7. **Evolua em passos pequenos.** Faça funcionar, teste, e só então adicione a próxima funcionalidade.
8. **Use caminhos relativos** para imagens e sons, e barras `/` (funcionam em todos os sistemas), nunca `C:\...`.

### Carregando uma imagem (sprite)

Coloque `jogador.png` em uma pasta `imagens/` ao lado do código:

```java
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

private BufferedImage carregarImagem(String caminho) {
    try {
        return ImageIO.read(new File(caminho));
    } catch (IOException e) {
        System.err.println("Não foi possível carregar: " + caminho);
        return null;
    }
}

// Uso (carregue UMA vez, no construtor):
// BufferedImage sprite = carregarImagem("imagens/jogador.png");
// No paintComponent:
// g.drawImage(sprite, (int) jogadorX, (int) jogadorY, null);
```

---

## 12. Próximos passos: bibliotecas e engines

Swing é ótimo para **aprender os fundamentos**. Quando quiser ir além:

| Opção | Tipo | Indicada para |
|---|---|---|
| **JavaFX** | Biblioteca gráfica moderna | Jogos 2D simples, interface mais bonita, animações |
| **LibGDX** | Framework de jogos | Jogos 2D e 3D, publicação para desktop, Android e web |
| **LWJGL** | Acesso a OpenGL/Vulkan | Quem quer entender gráficos em baixo nível (base do LibGDX e do Minecraft Java) |
| **jMonkeyEngine** | Engine 3D | Jogos 3D em Java |
| **Processing** | Ambiente criativo | Prototipagem rápida e arte generativa |

Sugestão de caminho de estudos: **Swing → JavaFX ou LibGDX → engine 3D**.

Sites oficiais:

- LibGDX: <https://libgdx.com>
- JavaFX (OpenJFX): <https://openjfx.io>
- LWJGL: <https://www.lwjgl.org>
- jMonkeyEngine: <https://jmonkeyengine.org>

---

## 13. Exercícios

### Nível 1: Aquecimento
1. Mude as cores do fundo, do jogador e das estrelas.
2. Altere a velocidade do jogador e o tempo total da partida.
3. Aumente o número de estrelas na tela.

### Nível 2: Modificando o jogo
4. Faça o jogador **mudar de cor** a cada estrela coletada.
5. Mostre no fim da partida a **maior pontuação** (recorde) da sessão.
6. Adicione a tecla `P` para **pausar** e continuar o jogo.

### Nível 3: Novas mecânicas
7. Crie **inimigos** (quadrados vermelhos) que se movem sozinhos; se tocarem no jogador, o jogo acaba.
8. Faça cada estrela **desaparecer depois de 5 segundos** se não for coletada.
9. Crie **níveis**: a cada 10 pontos, a velocidade dos inimigos aumenta.

### Nível 4: Desafios
10. Substitua os retângulos por **imagens (sprites)**.
11. Adicione **efeitos sonoros** com `javax.sound.sampled.Clip`.
12. Refatore o código criando as classes `Jogador`, `Estrela` e `Inimigo`.
13. Recrie o jogo em **JavaFX** ou **LibGDX** e compare as duas versões.

### Projetos clássicos para praticar
- **Pong** (dois jogadores)
- **Snake** (cobrinha)
- **Space Invaders** simplificado
- **Flappy Bird**
- **Jogo da memória**

---

## 14. Referências

- Documentação oficial do Java: <https://docs.oracle.com/en/java/>
- Tutorial oficial de Swing: <https://docs.oracle.com/javase/tutorial/uiswing/>
- Adoptium (distribuição gratuita do JDK): <https://adoptium.net>
- Game Programming Patterns, Robert Nystrom (gratuito online): <https://gameprogrammingpatterns.com>
- LibGDX Wiki: <https://libgdx.com/wiki/>

---

*Dica final: o segredo para aprender a fazer jogos é fazer jogos. Comece pequeno, termine o que começou e vá aumentando a dificuldade aos poucos.*
