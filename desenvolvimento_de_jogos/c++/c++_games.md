# Desenvolvimento de Jogos em C++ para Iniciantes

> Material didático para quem já conhece o básico de C++ (variáveis, `if`, `for`, funções, `struct`/classes e `std::vector`) e quer criar o primeiro jogo.
> Funciona em **Windows**, **Linux** e **macOS**.
> Biblioteca utilizada: **raylib**, com o projeto montado pelo **CMake**.

---

## Sumário

1. [O que é um jogo, do ponto de vista do programador?](#1-o-que-é-um-jogo-do-ponto-de-vista-do-programador)
2. [Por que raylib e CMake?](#2-por-que-raylib-e-cmake)
3. [Preparando o ambiente](#3-preparando-o-ambiente)
4. [Estrutura do projeto e primeiro programa](#4-estrutura-do-projeto-e-primeiro-programa)
5. [Compilando e executando em cada sistema](#5-compilando-e-executando-em-cada-sistema)
6. [Desenhando na tela](#6-desenhando-na-tela)
7. [O Game Loop e o delta time](#7-o-game-loop-e-o-delta-time)
8. [Lendo o teclado](#8-lendo-o-teclado)
9. [Colisão](#9-colisão)
10. [Jogo completo: Caça-Estrelas](#10-jogo-completo-caça-estrelas)
11. [Problemas comuns por sistema operacional](#11-problemas-comuns-por-sistema-operacional)
12. [Boas práticas em C++](#12-boas-práticas-em-c)
13. [Imagens e sons](#13-imagens-e-sons)
14. [Distribuindo o jogo](#14-distribuindo-o-jogo)
15. [Próximos passos: bibliotecas e engines](#15-próximos-passos-bibliotecas-e-engines)
16. [Exercícios](#16-exercícios)
17. [Referências](#17-referências)

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

## 2. Por que raylib e CMake?

Em C++ não existe uma biblioteca gráfica padrão, e **instalar bibliotecas é a parte mais difícil para iniciantes**, principalmente quando a turma usa três sistemas diferentes. Por isso este material usa:

- **raylib**: biblioteca simples e feita para ensino. Abre janela, desenha, lê teclado, toca som e carrega imagem com poucas linhas. Funciona igual nos três sistemas.
- **CMake**: ferramenta que gera o projeto de compilação para cada sistema (Visual Studio no Windows, Makefile no Linux e macOS). **Ele baixa e compila a raylib sozinho**, então ninguém precisa instalar a biblioteca manualmente.

> A raylib é escrita em C, mas pode ser usada normalmente em programas C++.

---

## 3. Preparando o ambiente

Você precisa de três coisas: **compilador C++**, **CMake** e **Git** (o CMake usa o Git para baixar a raylib).

### 3.1 Windows

1. **Compilador:** instale o **Visual Studio Build Tools** (gratuito) em <https://visualstudio.microsoft.com/downloads/> (seção *Ferramentas para Visual Studio*) e marque a carga de trabalho **"Desenvolvimento para desktop com C++"**.
   Alternativa: Visual Studio Community, com a mesma carga de trabalho.
2. **CMake e Git** (PowerShell):

```powershell
winget install Kitware.CMake
winget install Git.Git
```

3. **Feche e abra o terminal** depois de instalar, para o `PATH` ser atualizado.

### 3.2 macOS

```bash
xcode-select --install       # compilador (Apple Clang) e Git
brew install cmake           # com o Homebrew (https://brew.sh)
```

### 3.3 Linux

```bash
# Ubuntu / Debian / Linux Mint
sudo apt update
sudo apt install build-essential git cmake \
  libasound2-dev libx11-dev libxrandr-dev libxi-dev libgl1-mesa-dev \
  libglu1-mesa-dev libxcursor-dev libxinerama-dev libwayland-dev libxkbcommon-dev

# Fedora
sudo dnf install gcc-c++ git cmake \
  alsa-lib-devel mesa-libGL-devel libX11-devel libXrandr-devel libXi-devel \
  libXcursor-devel libXinerama-devel libatomic

# Arch Linux
sudo pacman -S base-devel git cmake alsa-lib mesa libx11 libxrandr libxi libxcursor libxinerama
```

> No Linux, as bibliotecas `-dev` (cabeçalhos de X11, OpenGL e áudio) são necessárias para compilar a raylib. Se faltar alguma, o CMake mostra um erro dizendo qual.

### 3.4 Conferir a instalação

Em qualquer sistema:

```bash
cmake --version
git --version
```

E o compilador:

| Sistema | Comando |
|---|---|
| Windows | Abra o *Developer PowerShell for VS* e rode `cl` (ou confie no CMake, que encontra o Visual Studio sozinho) |
| Linux | `g++ --version` |
| macOS | `clang++ --version` |

### 3.5 Escolher um editor

| Ferramenta | Windows | Linux | macOS | Observação |
|---|:-:|:-:|:-:|---|
| **VS Code** + extensões *C/C++* e *CMake Tools* | ✅ | ✅ | ✅ | Leve e igual nos três sistemas |
| **CLion** | ✅ | ✅ | ✅ | IDE completa, abre projetos CMake direto (gratuito para uso não comercial) |
| **Visual Studio Community** | ✅ | ❌ | ❌ | Abre pastas com `CMakeLists.txt` |
| **Qt Creator** | ✅ | ✅ | ✅ | Boa integração com CMake |

---

## 4. Estrutura do projeto e primeiro programa

Crie esta estrutura:

```
meu-jogo/
├── CMakeLists.txt
└── main.cpp
```

### 4.1 `CMakeLists.txt`

```cmake
cmake_minimum_required(VERSION 3.14)
project(MeuJogo CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Baixa e compila a raylib automaticamente
include(FetchContent)
FetchContent_Declare(
    raylib
    GIT_REPOSITORY https://github.com/raysan5/raylib.git
    GIT_TAG 5.5
    GIT_SHALLOW TRUE
)
set(BUILD_EXAMPLES OFF CACHE BOOL "" FORCE)
FetchContent_MakeAvailable(raylib)

add_executable(jogo main.cpp)
target_link_libraries(jogo raylib)
```

> Na **primeira** compilação o CMake baixa e compila a raylib; isso leva alguns minutos e precisa de internet. As próximas são rápidas.

### 4.2 `main.cpp`: a primeira janela

```cpp
#include "raylib.h"

int main() {
    InitWindow(800, 600, "Meu Primeiro Jogo");
    SetTargetFPS(60);

    while (!WindowShouldClose()) {       // roda até fechar a janela (ou ESC)
        BeginDrawing();
        ClearBackground(DARKBLUE);
        DrawText("Ola, jogo!", 20, 20, 30, WHITE);
        EndDrawing();
    }

    CloseWindow();
    return 0;
}
```

**O que cada parte faz:**

- `InitWindow(largura, altura, titulo)` cria a janela.
- `SetTargetFPS(60)` limita o jogo a 60 quadros por segundo.
- `WindowShouldClose()` vira `true` quando o jogador fecha a janela ou aperta `ESC`.
- `BeginDrawing()` / `EndDrawing()` delimitam o desenho de **um frame**.
- `ClearBackground(cor)` limpa a tela.
- `CloseWindow()` libera os recursos.

---

## 5. Compilando e executando em cada sistema

Rode sempre **dentro da pasta do projeto** (onde está o `CMakeLists.txt`).

### Windows (PowerShell)

```powershell
cd C:\Users\SeuNome\meu-jogo
cmake -B build
cmake --build build --config Release
.\build\Release\jogo.exe
```

### Linux e macOS (Terminal)

```bash
cd ~/meu-jogo
cmake -B build -DCMAKE_BUILD_TYPE=Release
cmake --build build
./build/jogo
```

> Se você usa o **MinGW/MSYS2** no Windows, o executável fica em `build\jogo.exe` (sem a pasta `Release`).

### Ciclo de trabalho

Depois da primeira vez, para cada alteração no código basta repetir **só** os comandos `cmake --build build ...` e executar.

---

## 6. Desenhando na tela

```cpp
#include "raylib.h"

int main() {
    InitWindow(800, 600, "Desenhando");
    SetTargetFPS(60);

    while (!WindowShouldClose()) {
        BeginDrawing();
        ClearBackground(BLACK);

        DrawRectangle(100, 100, 50, 50, SKYBLUE);        // x, y, largura, altura, cor
        DrawCircle(330, 230, 30, YELLOW);                 // centro x, centro y, raio, cor
        DrawLine(50, 400, 750, 400, WHITE);               // x1, y1, x2, y2, cor
        DrawText("Ola, jogo!", 20, 20, 30, WHITE);        // texto, x, y, tamanho, cor

        EndDrawing();
    }

    CloseWindow();
    return 0;
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

### Cores

A raylib já traz cores prontas (`WHITE`, `BLACK`, `RED`, `GREEN`, `BLUE`, `YELLOW`, `SKYBLUE`, `DARKBLUE`...). Para criar a sua, use `Color{R, G, B, A}`, com valores de 0 a 255:

```cpp
Color MINHA_COR = {20, 20, 40, 255};
```

> **Atenção com acentos:** a fonte padrão da raylib não desenha bem caracteres como `ç`, `ã` e `é`. Por isso os textos do jogo neste material estão sem acento. Na seção 13 há uma dica para usar fonte própria.

---

## 7. O Game Loop e o delta time

O `while (!WindowShouldClose())` **é** o game loop. Cada volta é um frame.

### Delta time: velocidade igual em qualquer PC

Se você mover o objeto "5 pixels por frame", o jogo ficará mais rápido em computadores mais velozes. O correto é mover **pixels por segundo** e multiplicar pelo tempo do frame, que a raylib entrega em `GetFrameTime()`:

```cpp
float dt = GetFrameTime();        // segundos desde o último frame
x += velocidade * dt;             // velocidade em pixels/segundo
```

---

## 8. Lendo o teclado

| Função | Quando devolve `true` | Use para |
|---|---|---|
| `IsKeyDown(tecla)` | Enquanto a tecla está pressionada | Movimento contínuo |
| `IsKeyPressed(tecla)` | Só no frame em que foi apertada | Pausar, reiniciar, atirar |
| `IsKeyReleased(tecla)` | Só no frame em que foi solta | Ações ao soltar |

```cpp
if (IsKeyDown(KEY_LEFT) || IsKeyDown(KEY_A)) {
    x -= velocidade * dt;
}

if (IsKeyPressed(KEY_SPACE)) {
    // acontece uma vez por aperto
}
```

---

## 9. Colisão

A forma mais simples é a colisão entre **retângulos**. A raylib já traz o tipo `Rectangle` e a função pronta:

```cpp
Rectangle jogador = {100, 100, 40, 40};   // x, y, largura, altura
Rectangle estrela = {120, 110, 20, 20};

if (CheckCollisionRecs(jogador, estrela)) {
    // colidiu!
}
```

Dois retângulos colidem quando se sobrepõem tanto na horizontal quanto na vertical.

> Também existem `CheckCollisionCircles` (círculo com círculo) e `CheckCollisionCircleRec` (círculo com retângulo).

---

## 10. Jogo completo: Caça-Estrelas

Objetivo: mover o quadrado azul e coletar o máximo de estrelas amarelas em **30 segundos**.

**Controles:** setas ou `W`, `A`, `S`, `D` para mover; `ENTER` para reiniciar após o fim.

Substitua o conteúdo de `main.cpp` por:

```cpp
#include "raylib.h"

#include <algorithm>
#include <vector>

// ---------- Constantes ----------
constexpr int LARGURA = 800;
constexpr int ALTURA = 600;
constexpr int FPS = 60;
constexpr int TAMANHO_JOGADOR = 40;
constexpr int TAMANHO_ESTRELA = 24;
constexpr float VELOCIDADE = 320.0f;     // pixels por segundo
constexpr float TEMPO_TOTAL = 30.0f;     // segundos
constexpr int QTD_ESTRELAS = 5;

const Color COR_FUNDO = {20, 20, 40, 255};
const Color COR_JOGADOR = {80, 180, 255, 255};

// ---------- Estado e regras do jogo ----------
struct Jogo {
    Vector2 pos{};
    std::vector<Rectangle> estrelas;
    int pontos = 0;
    float tempoRestante = TEMPO_TOTAL;
    bool fimDeJogo = false;

    static Rectangle novaEstrela() {
        float x = static_cast<float>(GetRandomValue(0, LARGURA - TAMANHO_ESTRELA));
        float y = static_cast<float>(GetRandomValue(0, ALTURA - TAMANHO_ESTRELA));
        return Rectangle{x, y, static_cast<float>(TAMANHO_ESTRELA),
                         static_cast<float>(TAMANHO_ESTRELA)};
    }

    void reiniciar() {
        pos = {LARGURA / 2.0f - TAMANHO_JOGADOR / 2.0f,
               ALTURA / 2.0f - TAMANHO_JOGADOR / 2.0f};
        pontos = 0;
        tempoRestante = TEMPO_TOTAL;
        fimDeJogo = false;
        estrelas.clear();
        for (int i = 0; i < QTD_ESTRELAS; i++) {
            estrelas.push_back(novaEstrela());
        }
    }

    // ---------- ATUALIZAR ----------
    void atualizar(float dt) {
        if (fimDeJogo) {
            return;
        }

        // Movimento
        float dx = 0.0f;
        float dy = 0.0f;
        if (IsKeyDown(KEY_LEFT) || IsKeyDown(KEY_A)) dx -= 1.0f;
        if (IsKeyDown(KEY_RIGHT) || IsKeyDown(KEY_D)) dx += 1.0f;
        if (IsKeyDown(KEY_UP) || IsKeyDown(KEY_W)) dy -= 1.0f;
        if (IsKeyDown(KEY_DOWN) || IsKeyDown(KEY_S)) dy += 1.0f;

        // Evita que a diagonal seja mais rapida
        if (dx != 0.0f && dy != 0.0f) {
            dx *= 0.7071f;
            dy *= 0.7071f;
        }

        pos.x += dx * VELOCIDADE * dt;
        pos.y += dy * VELOCIDADE * dt;

        // Limites da tela
        pos.x = std::clamp(pos.x, 0.0f, static_cast<float>(LARGURA - TAMANHO_JOGADOR));
        pos.y = std::clamp(pos.y, 0.0f, static_cast<float>(ALTURA - TAMANHO_JOGADOR));

        // Colisao com estrelas
        Rectangle caixa = {pos.x, pos.y, static_cast<float>(TAMANHO_JOGADOR),
                           static_cast<float>(TAMANHO_JOGADOR)};

        int coletadas = 0;
        for (auto it = estrelas.begin(); it != estrelas.end();) {
            if (CheckCollisionRecs(caixa, *it)) {
                it = estrelas.erase(it);   // erase devolve o proximo iterador
                coletadas++;
            } else {
                ++it;
            }
        }
        pontos += coletadas;
        for (int i = 0; i < coletadas; i++) {
            estrelas.push_back(novaEstrela());   // repoe as coletadas
        }

        // Tempo
        tempoRestante -= dt;
        if (tempoRestante <= 0.0f) {
            tempoRestante = 0.0f;
            fimDeJogo = true;
        }
    }

    // ---------- DESENHAR ----------
    void desenhar() const {
        ClearBackground(COR_FUNDO);

        for (const Rectangle& e : estrelas) {
            DrawCircle(static_cast<int>(e.x + e.width / 2),
                       static_cast<int>(e.y + e.height / 2),
                       e.width / 2, YELLOW);
        }

        DrawRectangle(static_cast<int>(pos.x), static_cast<int>(pos.y),
                      TAMANHO_JOGADOR, TAMANHO_JOGADOR, COR_JOGADOR);

        // Placar
        DrawText(TextFormat("Pontos: %d", pontos), 20, 15, 24, WHITE);
        const char* textoTempo = TextFormat("Tempo: %.1f s", tempoRestante);
        DrawText(textoTempo, LARGURA - MeasureText(textoTempo, 24) - 20, 15, 24, WHITE);

        // Tela de fim de jogo
        if (fimDeJogo) {
            DrawRectangle(0, 0, LARGURA, ALTURA, Fade(BLACK, 0.7f));
            centralizar("FIM DE JOGO", ALTURA / 2 - 60, 52);
            centralizar(TextFormat("Voce coletou %d estrelas", pontos), ALTURA / 2 + 10, 26);
            centralizar("Pressione ENTER para jogar de novo", ALTURA / 2 + 50, 26);
        }
    }

private:
    static void centralizar(const char* texto, int y, int tamanho) {
        int largura = MeasureText(texto, tamanho);
        DrawText(texto, (LARGURA - largura) / 2, y, tamanho, WHITE);
    }
};

int main() {
    InitWindow(LARGURA, ALTURA, "Caca-Estrelas");
    SetTargetFPS(FPS);

    Jogo jogo;
    jogo.reiniciar();

    while (!WindowShouldClose()) {
        // Evita saltos grandes se a janela travar por um instante
        float dt = std::min(GetFrameTime(), 0.05f);

        // 1) ENTRADA (eventos pontuais)
        if (jogo.fimDeJogo && IsKeyPressed(KEY_ENTER)) {
            jogo.reiniciar();
        }

        // 2) ATUALIZAR
        jogo.atualizar(dt);

        // 3) DESENHAR
        BeginDrawing();
        jogo.desenhar();
        EndDrawing();
    }

    CloseWindow();
    return 0;
}
```

### Entendendo a estrutura

| Parte do código | Papel no jogo |
|---|---|
| Constantes (`constexpr`) | Facilitam ajustar o jogo sem caçar números no código |
| `struct Jogo` | Guarda o estado (posição, pontos, tempo) e as regras |
| `atualizar(dt)` | Entrada contínua → movimento → colisão → tempo |
| `desenhar() const` | Apenas **desenha**; o `const` garante que não altera o estado |
| `main()` | Cria a janela e roda o **game loop** |

> **Regra de ouro:** separe **atualizar** (lógica) de **desenhar** (visual). Isso deixa o código organizado e evita bugs.

### Conceitos de C++ usados neste jogo

| Conceito | Onde aparece |
|---|---|
| `constexpr` | Constantes calculadas na compilação |
| `struct` com métodos | `Jogo` agrupa dados e comportamento |
| `std::vector` | Lista dinâmica de estrelas |
| Iteradores e `erase` | Remover estrelas durante a iteração sem quebrar o laço |
| `static_cast<float>(...)` | Conversão explícita e segura entre `int` e `float` |
| `const` em métodos e referências | `desenhar() const` e `const Rectangle& e` |
| `std::clamp`, `std::min` | Limitar valores (C++17, `<algorithm>`) |

---

## 11. Problemas comuns por sistema operacional

| Sintoma | Sistema | Solução |
|---|---|---|
| `cmake não é reconhecido como comando` | Windows | Instale o CMake e **feche e reabra** o terminal |
| `No CMAKE_CXX_COMPILER could be found` | Windows | Instale a carga de trabalho **"Desenvolvimento para desktop com C++"** do Visual Studio Build Tools |
| `No CMAKE_CXX_COMPILER could be found` | Linux | `sudo apt install build-essential` |
| `No CMAKE_CXX_COMPILER could be found` ou `xcrun: error` | macOS | `xcode-select --install` |
| `Git not found` / falha ao baixar a raylib | Todos | Instale o Git e verifique a conexão com a internet (redes de faculdade podem bloquear o GitHub) |
| `Could NOT find X11` / `Xrandr` / `OpenGL` | Linux | Instale os pacotes `-dev` da seção 3.3 |
| Erro citando `wayland` ou `xkbcommon` | Linux | `sudo apt install libwayland-dev libxkbcommon-dev` |
| `jogo.exe` não está em `build\` | Windows (Visual Studio) | Ele fica em `build\Release\jogo.exe` |
| Janela abre e fecha na hora | Todos | Rode pelo terminal para ver mensagens de erro; confira se o loop usa `while (!WindowShouldClose())` |
| `Failed to load texture` / sem imagem | Todos | O caminho é relativo à **pasta onde você executa o programa**. Rode a partir da pasta do projeto |
| Letras com acento aparecem erradas | Todos | A fonte padrão não cobre acentos; veja a dica na seção 13 |
| Sem som | Linux | Instale `libasound2-dev` antes de compilar e verifique o servidor de áudio (PulseAudio ou PipeWire) |
| Alterei o `CMakeLists.txt` e nada mudou | Todos | Apague a pasta `build` e gere de novo com `cmake -B build` |
| Pasta com espaço ou acento no caminho | Todos | Use aspas no terminal (`cd "Meus Jogos"`) ou evite espaços e acentos nos nomes de pastas |

---

## 12. Boas práticas em C++

1. **Separe lógica e desenho.** `atualizar()` nunca desenha; `desenhar()` nunca muda regras.
2. **Use delta time.** Movimento em pixels por segundo, nunca em pixels por frame.
3. **Evite `new` e `delete` manuais.** Prefira objetos na pilha, `std::vector` e, quando precisar de alocação dinâmica, `std::unique_ptr`. Isso evita vazamento de memória.
4. **Use `const` sempre que possível** (métodos, parâmetros e variáveis que não mudam).
5. **Passe objetos grandes por referência** (`const Tipo&`), não por cópia.
6. **Use constantes (`constexpr`)** em vez de "números mágicos" espalhados.
7. **Carregue texturas e sons uma única vez**, antes do loop, e libere no final (`Unload...`).
8. **Evite variáveis globais.** Guarde o estado dentro de `struct`/`class`.
9. **Ative os avisos do compilador** para pegar erros cedo. No `CMakeLists.txt`:

   ```cmake
   if (MSVC)
       target_compile_options(jogo PRIVATE /W4)
   else()
       target_compile_options(jogo PRIVATE -Wall -Wextra)
   endif()
   ```
10. **Versione com Git** desde o primeiro dia, e coloque a pasta `build/` no `.gitignore`.
11. **Evolua em passos pequenos.** Faça funcionar, teste, e só então adicione a próxima funcionalidade.

---

## 13. Imagens e sons

Coloque os arquivos nas pastas `imagens/` e `sons/` na raiz do projeto e **execute o jogo a partir da raiz do projeto**:

```
meu-jogo/
├── CMakeLists.txt
├── main.cpp
├── imagens/
│   └── jogador.png
└── sons/
    └── coleta.wav
```

### Imagem (sprite)

```cpp
InitWindow(LARGURA, ALTURA, "Jogo");          // a janela precisa existir antes
Texture2D sprite = LoadTexture("imagens/jogador.png");

// dentro do loop, entre BeginDrawing() e EndDrawing():
DrawTexture(sprite, static_cast<int>(pos.x), static_cast<int>(pos.y), WHITE);

// antes do CloseWindow():
UnloadTexture(sprite);
```

> `LoadTexture` só funciona **depois** de `InitWindow`.

### Som

```cpp
InitAudioDevice();
Sound somColeta = LoadSound("sons/coleta.wav");

PlaySound(somColeta);            // quando coletar uma estrela

// no final do programa:
UnloadSound(somColeta);
CloseAudioDevice();
```

Prefira **`.wav`** (efeitos) e **`.ogg`** (música, com `LoadMusicStream`). Funcionam bem nos três sistemas.

### Texto com acentos

Carregue uma fonte `.ttf` (por exemplo, de <https://fonts.google.com>) e peça os caracteres latinos:

```cpp
int pontosDeCodigo[256];
for (int i = 0; i < 256; i++) pontosDeCodigo[i] = i;        // Latin-1 cobre ç, ã, é...
Font fonte = LoadFontEx("fontes/MinhaFonte.ttf", 32, pontosDeCodigo, 256);

DrawTextEx(fonte, "Você coletou estrelas", {20, 60}, 32, 2, WHITE);

UnloadFont(fonte);   // no final
```

> Salve o arquivo `.cpp` em **UTF-8** (padrão do VS Code). Em outros editores, confira a codificação.

### Copiando os arquivos automaticamente para a pasta do executável

Se preferir executar o jogo de dentro da pasta `build`, adicione ao `CMakeLists.txt`:

```cmake
file(COPY ${CMAKE_SOURCE_DIR}/imagens DESTINATION ${CMAKE_BINARY_DIR})
file(COPY ${CMAKE_SOURCE_DIR}/sons DESTINATION ${CMAKE_BINARY_DIR})
```

> Use apenas imagens e sons livres ou criados por você. Sites como <https://opengameart.org> e <https://kenney.nl> têm material gratuito para jogos.

---

## 14. Distribuindo o jogo

O executável **depende do sistema onde foi compilado**: o `.exe` do Windows não roda no Linux nem no macOS. Para entregar o jogo:

1. Compile em **modo Release** (seção 5).
2. Crie uma pasta com o executável e as pastas `imagens/` e `sons/`.
3. Compacte em `.zip`.

Para cada sistema, gere o executável **naquele sistema** (ou use um serviço de integração contínua, como o GitHub Actions, que compila nos três).

> No Windows, jogos compilados com Visual Studio podem exigir o *Microsoft Visual C++ Redistributable* em computadores que não têm o Visual Studio instalado.

---

## 15. Próximos passos: bibliotecas e engines

A raylib é ótima para **aprender os fundamentos**. Quando quiser ir além:

| Opção | Tipo | Indicada para |
|---|---|---|
| **SFML** | Biblioteca 2D orientada a objetos | Jogos 2D com API muito "C++"; ótima para praticar classes |
| **SDL3** | Biblioteca de baixo nível | Base de muitos jogos e engines; mais controle, mais trabalho |
| **Box2D** | Física 2D | Gravidade, colisões realistas, plataformas |
| **Dear ImGui** | Interface | Menus e ferramentas de depuração dentro do jogo |
| **Godot** (com GDExtension) | Engine completa | Projetos maiores; usa GDScript e permite módulos em C++ |
| **Unreal Engine** | Engine profissional | Jogos 3D de alto nível, programados em C++ |

Sugestão de caminho de estudos: **raylib → SFML ou SDL → engine (Godot ou Unreal)**.

Sites oficiais:

- raylib: <https://www.raylib.com> (com a *cheatsheet* de todas as funções)
- SFML: <https://www.sfml-dev.org>
- SDL: <https://www.libsdl.org>
- Box2D: <https://box2d.org>
- Godot: <https://godotengine.org>
- Unreal Engine: <https://www.unrealengine.com>

---

## 16. Exercícios

### Nível 1: Aquecimento
1. Mude as cores do fundo, do jogador e das estrelas.
2. Altere a velocidade do jogador e o tempo total da partida.
3. Aumente o número de estrelas na tela.

### Nível 2: Modificando o jogo
4. Faça o jogador **mudar de cor** a cada estrela coletada.
5. Mostre no fim da partida a **maior pontuação** (recorde) da sessão.
6. Adicione a tecla `P` para **pausar** e continuar o jogo (use `IsKeyPressed`).

### Nível 3: Novas mecânicas
7. Crie **inimigos** (quadrados vermelhos) que se movem sozinhos; se tocarem no jogador, o jogo acaba.
8. Faça cada estrela **desaparecer depois de 5 segundos** se não for coletada.
9. Crie **níveis**: a cada 10 pontos, a velocidade dos inimigos aumenta.

### Nível 4: Desafios
10. Substitua os retângulos por **imagens (sprites)**.
11. Adicione **efeitos sonoros** e música de fundo.
12. Salve o **recorde em um arquivo** (`std::ofstream` / `std::ifstream`) para que ele continue após fechar o jogo.
13. Refatore o código em arquivos separados (`Jogo.h`, `Jogo.cpp`, `Estrela.h`...) e atualize o `CMakeLists.txt`.
14. Recrie o jogo em **SFML** e compare as duas versões.

### Projetos clássicos para praticar
- **Pong** (dois jogadores)
- **Snake** (cobrinha)
- **Space Invaders** simplificado
- **Flappy Bird**
- **Jogo da memória**

---

## 17. Referências

- Documentação do C++ (cppreference): <https://en.cppreference.com>
- Site e exemplos da raylib: <https://www.raylib.com/examples.html>
- Cheatsheet da raylib: <https://www.raylib.com/cheatsheet/cheatsheet.html>
- Wiki da raylib (instalação por sistema): <https://github.com/raysan5/raylib/wiki>
- Documentação do CMake: <https://cmake.org/cmake/help/latest/>
- Game Programming Patterns, Robert Nystrom (gratuito online): <https://gameprogrammingpatterns.com>
- Material gratuito de arte e som: <https://opengameart.org>, <https://kenney.nl>

---

*Dica final: o segredo para aprender a fazer jogos é fazer jogos. Comece pequeno, termine o que começou e vá aumentando a dificuldade aos poucos.*
