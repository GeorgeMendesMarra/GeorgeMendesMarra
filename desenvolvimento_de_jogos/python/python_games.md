# Desenvolvimento de Jogos em Python para Iniciantes

> Material didático para quem já conhece o básico de Python (variáveis, `if`, `for`, funções e classes) e quer criar o primeiro jogo.
> Funciona em **Windows**, **Linux** e **macOS**.
> Biblioteca utilizada: **Pygame**.

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
9. [Como executar em cada sistema](#9-como-executar-em-cada-sistema)
10. [Problemas comuns por sistema operacional](#10-problemas-comuns-por-sistema-operacional)
11. [Boas práticas](#11-boas-práticas)
12. [Imagens e sons](#12-imagens-e-sons)
13. [Gerando um executável](#13-gerando-um-executável)
14. [Próximos passos: bibliotecas e engines](#14-próximos-passos-bibliotecas-e-engines)
15. [Exercícios](#15-exercícios)
16. [Referências](#16-referências)

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

### 2.1 Instalar o Python

Recomendamos o **Python 3.11 ou superior**. Escolha o seu sistema:

**Windows** (PowerShell ou Prompt de Comando):

```powershell
winget install Python.Python.3.12
```

Ou baixe o instalador em <https://www.python.org/downloads/> e **marque a opção "Add python.exe to PATH"** na primeira tela.

**macOS** (com [Homebrew](https://brew.sh)):

```bash
brew install python
```

Ou baixe o instalador em <https://www.python.org/downloads/>.

**Linux**:

```bash
# Ubuntu / Debian / Linux Mint
sudo apt update
sudo apt install python3 python3-pip python3-venv

# Fedora
sudo dnf install python3 python3-pip

# Arch Linux
sudo pacman -S python python-pip
```

### 2.2 Conferir a instalação

Abra um terminal (Windows: *Prompt de Comando* ou *PowerShell*; macOS e Linux: *Terminal*):

| Sistema | Comando |
|---|---|
| Windows | `py --version` |
| Linux / macOS | `python3 --version` |

> **Atenção:** no Windows o comando recomendado é `py`; no Linux e no macOS é `python3`. Em alguns computadores `python` também funciona, mas não conte com isso.

### 2.3 Criar uma pasta e um ambiente virtual

O **ambiente virtual** (`venv`) isola as bibliotecas do projeto e evita conflitos. No Linux ele também evita o erro `externally-managed-environment`, comum nas versões recentes das distribuições.

```
meu-jogo/
└── jogo.py
```

**Windows (PowerShell):**

```powershell
cd C:\Users\SeuNome\meu-jogo
py -m venv venv
venv\Scripts\Activate.ps1
```

> Se o PowerShell bloquear a ativação com erro de política de execução, rode uma vez:
> `Set-ExecutionPolicy -Scope CurrentUser RemoteSigned`
> Ou use o Prompt de Comando (`cmd`) e execute `venv\Scripts\activate.bat`.

**Linux e macOS:**

```bash
cd ~/meu-jogo
python3 -m venv venv
source venv/bin/activate
```

Quando o ambiente estiver ativo, o terminal mostra `(venv)` no início da linha.

### 2.4 Instalar o Pygame

Com o ambiente virtual ativo, o comando é o mesmo nos três sistemas:

```bash
pip install pygame
```

### 2.5 Testar a instalação

```bash
python -c "import pygame; print(pygame.version.ver)"
```

Deve imprimir a versão (por exemplo `2.6.1`). Dentro do ambiente virtual, `python` funciona em qualquer sistema.

### 2.6 Escolher um editor

| Ferramenta | Windows | Linux | macOS | Observação |
|---|:-:|:-:|:-:|---|
| **VS Code** + extensão *Python* | ✅ | ✅ | ✅ | Leve e muito popular |
| **PyCharm Community** | ✅ | ✅ | ✅ | IDE completa para Python |
| **Thonny** | ✅ | ✅ | ✅ | Muito simples, ótimo para iniciantes |
| **IDLE** | ✅ | ✅ | ✅ | Já vem com o Python (no Linux: `sudo apt install idle3`) |

Se usar VS Code ou PyCharm, selecione o interpretador que está dentro da pasta `venv` do projeto.

---

## 3. Primeiro programa: abrindo uma janela

Crie o arquivo `janela.py`:

```python
import pygame

pygame.init()

tela = pygame.display.set_mode((800, 600))
pygame.display.set_caption("Meu Primeiro Jogo")

rodando = True
while rodando:
    for evento in pygame.event.get():
        if evento.type == pygame.QUIT:   # clicou no X da janela
            rodando = False

    tela.fill((20, 20, 40))              # pinta o fundo (R, G, B)
    pygame.display.flip()                # mostra o que foi desenhado

pygame.quit()
```

Execute:

```bash
python janela.py
```

Uma janela de 800x600 deve aparecer.

**O que cada parte faz:**

- `pygame.init()` inicializa os módulos do Pygame.
- `set_mode((800, 600))` cria a janela e devolve a **superfície** (`tela`) onde desenhamos.
- O `while rodando` é o **game loop**.
- `pygame.event.get()` lê os eventos (fechar a janela, teclas, mouse). **Sem ele a janela congela.**
- `fill(...)` limpa a tela com uma cor.
- `display.flip()` troca a imagem desenhada pela imagem que o jogador vê.

---

## 4. Desenhando na tela

```python
import pygame

pygame.init()
tela = pygame.display.set_mode((800, 600))
pygame.display.set_caption("Desenhando")
fonte = pygame.font.SysFont("arial", 28, bold=True)

rodando = True
while rodando:
    for evento in pygame.event.get():
        if evento.type == pygame.QUIT:
            rodando = False

    tela.fill((0, 0, 0))

    pygame.draw.rect(tela, (0, 255, 255), (100, 100, 50, 50))   # retângulo: x, y, largura, altura
    pygame.draw.circle(tela, (255, 255, 0), (330, 230), 30)     # círculo: centro e raio
    pygame.draw.line(tela, (255, 255, 255), (50, 400), (750, 400), 3)

    texto = fonte.render("Olá, jogo!", True, (255, 255, 255))
    tela.blit(texto, (20, 20))                                  # blit = "colar" na tela

    pygame.display.flip()

pygame.quit()
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

### Cores

Cores são tuplas **(R, G, B)**, cada valor de 0 a 255:

```python
PRETO    = (0, 0, 0)
BRANCO   = (255, 255, 255)
VERMELHO = (255, 0, 0)
AZUL     = (80, 180, 255)
```

---

## 5. O Game Loop

Para ter movimento, repetimos o ciclo **entrada → atualizar → desenhar**. O `pygame.time.Clock` controla a velocidade do loop e nos dá o **delta time**:

```python
relogio = pygame.time.Clock()

while rodando:
    dt = relogio.tick(60) / 1000   # limita a 60 FPS e devolve segundos desde o último frame
    ...
```

### Delta time: velocidade igual em qualquer PC

Se você mover o objeto "5 pixels por frame", o jogo ficará mais rápido em computadores mais velozes. O correto é mover **pixels por segundo** e multiplicar pelo tempo do frame:

```python
x += velocidade * dt    # velocidade em pixels/segundo, dt em segundos
```

---

## 6. Lendo o teclado

Existem duas formas:

**1) Eventos (uma vez por pressionamento):** bom para pausar, reiniciar, atirar.

```python
for evento in pygame.event.get():
    if evento.type == pygame.KEYDOWN and evento.key == pygame.K_SPACE:
        print("Espaço pressionado!")
```

**2) Estado das teclas (enquanto estiver pressionada):** bom para movimento contínuo.

```python
teclas = pygame.key.get_pressed()
if teclas[pygame.K_LEFT] or teclas[pygame.K_a]:
    x -= velocidade * dt
```

Use a forma 2 para andar e a forma 1 para ações pontuais.

---

## 7. Colisão

A forma mais simples é a colisão entre **retângulos**. O Pygame já traz a classe `pygame.Rect` com tudo pronto:

```python
jogador = pygame.Rect(100, 100, 40, 40)   # x, y, largura, altura
estrela = pygame.Rect(120, 110, 20, 20)

if jogador.colliderect(estrela):
    print("Colidiu!")
```

Dois retângulos colidem quando se sobrepõem tanto na horizontal quanto na vertical.

> Para colisão entre círculos, compare a distância entre os centros com a soma dos raios.

---

## 8. Jogo completo: Caça-Estrelas

Objetivo: mover o quadrado azul e coletar o máximo de estrelas amarelas em **30 segundos**.

**Controles:** setas ou `W`, `A`, `S`, `D` para mover; `ENTER` para reiniciar após o fim.

Crie o arquivo `jogo.py`:

```python
import random

import pygame

# ---------- Constantes ----------
LARGURA, ALTURA = 800, 600
FPS = 60
TAMANHO_JOGADOR = 40
TAMANHO_ESTRELA = 24
VELOCIDADE = 320        # pixels por segundo
TEMPO_TOTAL = 30        # segundos
QTD_ESTRELAS = 5

COR_FUNDO = (20, 20, 40)
COR_JOGADOR = (80, 180, 255)
COR_ESTRELA = (255, 255, 0)
BRANCO = (255, 255, 255)


class Jogo:
    """Guarda o estado do jogo e as regras."""

    def __init__(self):
        self.reiniciar()

    def reiniciar(self):
        self.pos = pygame.Vector2(
            LARGURA / 2 - TAMANHO_JOGADOR / 2,
            ALTURA / 2 - TAMANHO_JOGADOR / 2,
        )
        self.pontos = 0
        self.tempo_restante = TEMPO_TOTAL
        self.fim_de_jogo = False
        self.estrelas = [self.nova_estrela() for _ in range(QTD_ESTRELAS)]

    @staticmethod
    def nova_estrela():
        x = random.randint(0, LARGURA - TAMANHO_ESTRELA)
        y = random.randint(0, ALTURA - TAMANHO_ESTRELA)
        return pygame.Rect(x, y, TAMANHO_ESTRELA, TAMANHO_ESTRELA)

    # ---------- ATUALIZAR: regras do jogo ----------
    def atualizar(self, dt, teclas):
        if self.fim_de_jogo:
            return

        # Movimento
        direcao = pygame.Vector2(0, 0)
        if teclas[pygame.K_LEFT] or teclas[pygame.K_a]:
            direcao.x -= 1
        if teclas[pygame.K_RIGHT] or teclas[pygame.K_d]:
            direcao.x += 1
        if teclas[pygame.K_UP] or teclas[pygame.K_w]:
            direcao.y -= 1
        if teclas[pygame.K_DOWN] or teclas[pygame.K_s]:
            direcao.y += 1

        # Normaliza para a diagonal não ser mais rápida
        if direcao.length() > 0:
            direcao = direcao.normalize()

        self.pos += direcao * VELOCIDADE * dt

        # Limites da tela
        self.pos.x = max(0, min(self.pos.x, LARGURA - TAMANHO_JOGADOR))
        self.pos.y = max(0, min(self.pos.y, ALTURA - TAMANHO_JOGADOR))

        # Colisão com estrelas
        caixa_jogador = pygame.Rect(
            int(self.pos.x), int(self.pos.y), TAMANHO_JOGADOR, TAMANHO_JOGADOR
        )
        restantes = [e for e in self.estrelas if not caixa_jogador.colliderect(e)]
        coletadas = len(self.estrelas) - len(restantes)
        self.pontos += coletadas
        self.estrelas = restantes
        for _ in range(coletadas):
            self.estrelas.append(self.nova_estrela())   # repõe as coletadas

        # Tempo
        self.tempo_restante -= dt
        if self.tempo_restante <= 0:
            self.tempo_restante = 0
            self.fim_de_jogo = True

    # ---------- DESENHAR ----------
    def desenhar(self, tela, fonte, fonte_grande):
        tela.fill(COR_FUNDO)

        for estrela in self.estrelas:
            pygame.draw.ellipse(tela, COR_ESTRELA, estrela)

        pygame.draw.rect(
            tela,
            COR_JOGADOR,
            (int(self.pos.x), int(self.pos.y), TAMANHO_JOGADOR, TAMANHO_JOGADOR),
        )

        # Placar
        texto_pontos = fonte.render(f"Pontos: {self.pontos}", True, BRANCO)
        texto_tempo = fonte.render(f"Tempo: {self.tempo_restante:.1f} s", True, BRANCO)
        tela.blit(texto_pontos, (20, 15))
        tela.blit(texto_tempo, (LARGURA - texto_tempo.get_width() - 20, 15))

        # Tela de fim de jogo
        if self.fim_de_jogo:
            camada = pygame.Surface((LARGURA, ALTURA), pygame.SRCALPHA)
            camada.fill((0, 0, 0, 180))
            tela.blit(camada, (0, 0))

            centralizar(tela, fonte_grande, "FIM DE JOGO", ALTURA // 2 - 50)
            centralizar(tela, fonte, f"Você coletou {self.pontos} estrelas", ALTURA // 2 + 10)
            centralizar(tela, fonte, "Pressione ENTER para jogar de novo", ALTURA // 2 + 50)


def centralizar(tela, fonte, texto, y):
    imagem = fonte.render(texto, True, BRANCO)
    x = (LARGURA - imagem.get_width()) // 2
    tela.blit(imagem, (x, y))


def main():
    pygame.init()
    tela = pygame.display.set_mode((LARGURA, ALTURA))
    pygame.display.set_caption("Caça-Estrelas")
    relogio = pygame.time.Clock()
    fonte = pygame.font.SysFont("arial", 24, bold=True)
    fonte_grande = pygame.font.SysFont("arial", 52, bold=True)

    jogo = Jogo()
    rodando = True

    while rodando:
        # Evita saltos grandes se a janela travar por um instante
        dt = min(relogio.tick(FPS) / 1000, 0.05)

        # 1) ENTRADA (eventos)
        for evento in pygame.event.get():
            if evento.type == pygame.QUIT:
                rodando = False
            elif evento.type == pygame.KEYDOWN:
                if evento.key == pygame.K_RETURN and jogo.fim_de_jogo:
                    jogo.reiniciar()

        # 2) ATUALIZAR
        jogo.atualizar(dt, pygame.key.get_pressed())

        # 3) DESENHAR
        jogo.desenhar(tela, fonte, fonte_grande)
        pygame.display.flip()

    pygame.quit()


if __name__ == "__main__":
    main()
```

### Entendendo a estrutura

| Parte do código | Papel no jogo |
|---|---|
| Constantes (`LARGURA`, `VELOCIDADE`...) | Facilitam ajustar o jogo sem caçar números no código |
| `class Jogo` | Guarda o estado (posição, pontos, tempo) e as regras |
| `atualizar(dt, teclas)` | Entrada contínua → movimento → colisão → tempo |
| `desenhar(...)` | Apenas **desenha**; não deve alterar o estado |
| `main()` | Cria a janela e roda o **game loop** |
| `if __name__ == "__main__"` | Executa o jogo só quando o arquivo é rodado diretamente |

> **Regra de ouro:** separe **atualizar** (lógica) de **desenhar** (visual). Isso deixa o código organizado e evita bugs.

---

## 9. Como executar em cada sistema

Com o ambiente virtual **ativo** (veja a seção 2.3), dentro da pasta do projeto:

**Windows (PowerShell / Prompt de Comando)**

```powershell
cd C:\Users\SeuNome\meu-jogo
venv\Scripts\Activate.ps1
python jogo.py
```

**Linux e macOS (Terminal)**

```bash
cd ~/meu-jogo
source venv/bin/activate
python jogo.py
```

Para sair do ambiente virtual, digite `deactivate`.

### Registrando as dependências

Para que os alunos (ou você, em outro computador) instalem tudo com um comando:

```bash
pip freeze > requirements.txt     # gera a lista
pip install -r requirements.txt   # instala a partir da lista
```

---

## 10. Problemas comuns por sistema operacional

| Sintoma | Sistema | Solução |
|---|---|---|
| `'python' não é reconhecido como comando` | Windows | Use `py` ou reinstale o Python marcando "Add python.exe to PATH" |
| `python: command not found` | Linux/macOS | Use `python3` (ou ative o ambiente virtual, onde `python` funciona) |
| `error: externally-managed-environment` ao rodar `pip install` | Linux (Ubuntu/Debian/Fedora recentes) | Use um ambiente virtual (seção 2.3) em vez de instalar no sistema |
| `ensurepip is not available` ou `No module named venv` | Linux (Debian/Ubuntu) | `sudo apt install python3-venv` |
| `Activate.ps1 não pode ser carregado` | Windows (PowerShell) | `Set-ExecutionPolicy -Scope CurrentUser RemoteSigned` ou use o `cmd` |
| `ModuleNotFoundError: No module named 'pygame'` | Todos | O ambiente virtual não está ativo, ou o Pygame foi instalado em outro Python. Ative o `venv` e rode `pip install pygame` |
| Falha ao instalar o Pygame | Todos | Atualize o pip (`python -m pip install --upgrade pip`). Em versões muito novas do Python pode não haver pacote pronto; use uma versão estável do Python (3.11 a 3.13) |
| Janela abre e fecha na hora | Todos | Falta o game loop ou há um erro no código; rode pelo terminal para ver a mensagem de erro |
| Janela "não responde" | Todos | O `pygame.event.get()` não está sendo chamado a cada frame |
| Fonte `arial` não encontrada | Linux/macOS | O Pygame usa uma fonte padrão no lugar. Para fonte garantida, use `pygame.font.Font(None, 28)` ou inclua um arquivo `.ttf` no projeto |
| Sem som | Linux | Instale/verifique o servidor de áudio (PulseAudio ou PipeWire) |
| Pasta com espaço ou acento no caminho | Todos | Use aspas no terminal (`cd "Meus Jogos"`) ou evite espaços e acentos nos nomes de pastas |

---

## 11. Boas práticas

1. **Separe lógica e desenho.** `atualizar()` nunca desenha; `desenhar()` nunca muda regras.
2. **Use delta time.** Movimento em pixels por segundo, nunca em pixels por frame.
3. **Use constantes** (em MAIÚSCULAS) para tamanhos, velocidades e tempos.
4. **Crie classes** conforme o jogo cresce (`Jogador`, `Estrela`, `Inimigo`).
5. **Carregue imagens e sons uma única vez**, antes do loop, nunca dentro dele.
6. **Use `pathlib` para caminhos de arquivos**, assim funciona em qualquer sistema:

   ```python
   from pathlib import Path

   PASTA = Path(__file__).parent
   caminho_imagem = PASTA / "imagens" / "jogador.png"
   ```

   Evite escrever caminhos como `C:\...` ou `/home/...` no código.
7. **Versione o código com Git** desde o primeiro dia (e coloque `venv/` no `.gitignore`).
8. **Evolua em passos pequenos.** Faça funcionar, teste, e só então adicione a próxima funcionalidade.

---

## 12. Imagens e sons

### Imagem (sprite)

Coloque `jogador.png` em uma pasta `imagens/` ao lado do código:

```python
from pathlib import Path
import pygame

PASTA = Path(__file__).parent

# Carregue UMA vez, depois de criar a janela:
sprite = pygame.image.load(PASTA / "imagens" / "jogador.png").convert_alpha()
sprite = pygame.transform.scale(sprite, (40, 40))

# No desenhar():
tela.blit(sprite, (int(pos.x), int(pos.y)))
```

> `convert_alpha()` só funciona **depois** de `pygame.display.set_mode(...)`. Ele deixa o desenho mais rápido e preserva a transparência.

### Som

```python
pygame.mixer.init()
som_coleta = pygame.mixer.Sound(PASTA / "sons" / "coleta.wav")
som_coleta.play()          # toque quando coletar uma estrela

pygame.mixer.music.load(PASTA / "sons" / "musica.ogg")
pygame.mixer.music.play(-1)   # -1 = repete para sempre
```

Prefira formatos **`.wav`** (efeitos) e **`.ogg`** (música): funcionam bem nos três sistemas. O `.mp3` pode dar problema dependendo da versão.

> Use apenas imagens e sons livres ou criados por você. Sites como <https://opengameart.org> e <https://kenney.nl> têm material gratuito para jogos.

---

## 13. Gerando um executável

Para entregar o jogo a quem **não** tem Python instalado, use o **PyInstaller**:

```bash
pip install pyinstaller
pyinstaller --onefile --windowed jogo.py
```

O resultado fica na pasta `dist/`.

> **Importante:** o PyInstaller **não** faz compilação cruzada. O executável gerado no Windows só roda no Windows; para Linux e macOS é preciso gerar em cada sistema. Se o jogo usa imagens e sons, eles precisam ser incluídos (opção `--add-data`) ou copiados junto do executável.

Alternativa: compartilhe a pasta do projeto com o `requirements.txt` e peça para a pessoa seguir a seção 2 e a seção 9.

---

## 14. Próximos passos: bibliotecas e engines

Pygame é ótimo para **aprender os fundamentos**. Quando quiser ir além:

| Opção | Tipo | Indicada para |
|---|---|---|
| **Pygame Zero** | Camada simplificada sobre o Pygame | Primeiros jogos com muito menos código; ótima para turmas iniciantes |
| **Pygame-CE** | Versão mantida pela comunidade | Mesma API do Pygame, com desenvolvimento mais ativo |
| **Arcade** | Biblioteca 2D moderna | Jogos 2D com visual melhor e API mais "Pythônica" |
| **Ursina** | Engine 3D simples | Primeiros passos em 3D com Python |
| **Panda3D** | Engine 3D completa | Jogos 3D mais sérios |
| **Godot** (GDScript) | Engine profissional | Linguagem muito parecida com Python; caminho natural para projetos maiores |

Sugestão de caminho de estudos: **Pygame → Arcade ou Pygame Zero → Godot**.

Sites oficiais:

- Pygame: <https://www.pygame.org>
- Pygame Zero: <https://pygame-zero.readthedocs.io>
- Arcade: <https://api.arcade.academy>
- Ursina: <https://www.ursinaengine.org>
- Godot: <https://godotengine.org>

---

## 15. Exercícios

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
11. Adicione **efeitos sonoros** e música de fundo.
12. Salve o **recorde em um arquivo** (`recorde.txt` ou JSON) para que ele continue após fechar o jogo.
13. Refatore o código criando as classes `Jogador`, `Estrela` e `Inimigo`.
14. Recrie o jogo com **Pygame Zero** ou **Arcade** e compare as versões.

### Projetos clássicos para praticar
- **Pong** (dois jogadores)
- **Snake** (cobrinha)
- **Space Invaders** simplificado
- **Flappy Bird**
- **Jogo da memória**

---

## 16. Referências

- Documentação oficial do Python: <https://docs.python.org/pt-br/3/>
- Documentação do Pygame: <https://www.pygame.org/docs/>
- Download do Python: <https://www.python.org/downloads/>
- Tutorial oficial de ambientes virtuais: <https://docs.python.org/pt-br/3/tutorial/venv.html>
- Game Programming Patterns, Robert Nystrom (gratuito online): <https://gameprogrammingpatterns.com>
- Material gratuito de arte e som: <https://opengameart.org>, <https://kenney.nl>

---

*Dica final: o segredo para aprender a fazer jogos é fazer jogos. Comece pequeno, termine o que começou e vá aumentando a dificuldade aos poucos.*
