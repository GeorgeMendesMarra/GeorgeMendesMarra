# Python Mobile para Iniciantes

Guia introdutório sobre desenvolvimento de aplicativos móveis com Python, com instruções para **Windows**, **Linux** e **macOS**.

---

## Sumário

1. [O que é Python Mobile?](#1-o-que-é-python-mobile)
2. [Python em celulares: o estado da arte](#2-python-em-celulares-o-estado-da-arte)
3. [Panorama: quais ferramentas existem?](#3-panorama-quais-ferramentas-existem)
4. [Qual ferramenta escolher?](#4-qual-ferramenta-escolher)
5. [Preparando o ambiente base (Python + venv)](#5-preparando-o-ambiente-base-python--venv)
6. [Flet: o caminho mais simples para começar](#6-flet-o-caminho-mais-simples-para-começar)
7. [Kivy + Buildozer: o clássico](#7-kivy--buildozer-o-clássico)
8. [BeeWare (Toga + Briefcase): interface nativa](#8-beeware-toga--briefcase-interface-nativa)
9. [Chaquopy: Python dentro de um app Android (Java/Kotlin)](#9-chaquopy-python-dentro-de-um-app-android-javakotlin)
10. [Qt for Python (PySide6)](#10-qt-for-python-pyside6)
11. [Conceitos comuns de apps móveis](#11-conceitos-comuns-de-apps-móveis)
12. [Armazenamento de dados](#12-armazenamento-de-dados)
13. [Internet e tarefas em segundo plano](#13-internet-e-tarefas-em-segundo-plano)
14. [Permissões e recursos do aparelho](#14-permissões-e-recursos-do-aparelho)
15. [Testando: emulador e celular físico](#15-testando-emulador-e-celular-físico)
16. [Gerando e publicando o app](#16-gerando-e-publicando-o-app)
17. [E o iOS?](#17-e-o-ios)
18. [Rodar Python no celular (sem criar app)](#18-rodar-python-no-celular-sem-criar-app)
19. [Python como "cérebro" (backend) do app](#19-python-como-cérebro-backend-do-app)
20. [Limitações e cuidados](#20-limitações-e-cuidados)
21. [Boas práticas](#21-boas-práticas)
22. [Problemas comuns e soluções](#22-problemas-comuns-e-soluções)
23. [Exercícios propostos](#23-exercícios-propostos)
24. [Glossário](#24-glossário)
25. [Referências e links úteis](#25-referências-e-links-úteis)

---

## 1. O que é Python Mobile?

**Python Mobile** é o conjunto de técnicas e ferramentas para criar aplicativos para celulares e tablets (principalmente **Android** e **iOS**) usando a linguagem **Python**.

Diferente do Java, que tem o **Android Studio** como ferramenta oficial e única "porta de entrada", o Python **não possui** um SDK oficial para criar apps móveis. Por isso, existem **vários frameworks da comunidade**, cada um com sua filosofia. Entender essa diferença é o primeiro passo.

| | Java (Android) | Python |
|---|---|---|
| Ferramenta oficial do Google | ✅ Android Studio | ❌ Não existe |
| Frameworks | Android SDK (nativo) | Kivy, BeeWare, Flet, Chaquopy, Qt for Python... |
| Maturidade para apps móveis | Muito alta | Boa para apps simples/médios; menor que o nativo |
| Facilidade para começar | Média | **Alta** (sintaxe simples) |

> **Resumo para o iniciante:** com Python você consegue criar apps reais para Android (e, com um Mac, para iOS), mas precisa **escolher um framework** e aceitar algumas limitações (veja a seção 20).

---

## 2. Python em celulares: o estado da arte

- Rodar Python em celulares começou de forma **não oficial**, com projetos como SL4A, QPython e depois **Kivy** (2010 em diante).
- Surgiram novas abordagens: **BeeWare** (interface nativa), **Chaquopy** (Python dentro de apps Android) e **Flet** (interface baseada em Flutter, programada só em Python).
- Nos últimos anos, o próprio **CPython** (a implementação oficial do Python) passou a ter **suporte oficial em nível experimental** para **Android** e **iOS** (PEPs 730 e 738), o que melhora a base para todas essas ferramentas.

> O ecossistema muda rápido. Antes de iniciar um projeto real, confira a documentação atual do framework escolhido.

---

## 3. Panorama: quais ferramentas existem?

```
                          Python Mobile
                                │
   ┌──────────┬─────────────┬───┴────────┬──────────────┐
 Flet       Kivy         BeeWare      Chaquopy     Qt for Python
(Flutter)  (+Buildozer)  (Toga +       (Python      (PySide6)
                          Briefcase)    + Android Studio)
```

| Ferramenta | Como funciona | Interface | Plataformas | Ponto forte | Ponto de atenção |
|---|---|---|---|---|---|
| **Flet** | Python controla uma interface feita em Flutter | Moderna (Material) | Android, iOS, web, desktop | Mais fácil de aprender; poucas linhas | Projeto mais novo; API evolui rápido |
| **Kivy** | Desenha toda a interface com OpenGL (visual próprio) | Própria (não nativa) | Android, iOS, Windows, Linux, macOS | Muito maduro; ótimo para jogos e apps customizados | Aparência não é "nativa"; Buildozer não roda direto no Windows |
| **BeeWare (Toga)** | Usa componentes **nativos** de cada sistema | Nativa | Android, iOS, desktop | Visual e comportamento nativos | Menos componentes; evolução constante |
| **Chaquopy** | Plugin Gradle que embute Python em app Android | Java/Kotlin (XML) | Android | Combina Java/Kotlin + Python (ótimo para quem já viu Java) | Só Android; exige Android Studio |
| **Qt for Python** | Python com Qt/QML | Qt/QML | Android, iOS, desktop | Poderoso; usado na indústria | Mais complexo; empacotamento exige atenção |

---

## 4. Qual ferramenta escolher?

| Seu objetivo | Sugestão |
|---|---|
| "Quero fazer meu primeiro app rápido" | **Flet** |
| "Quero um app com visual totalmente personalizado ou um jogo" | **Kivy** |
| "Quero que o app pareça nativo em cada sistema" | **BeeWare** |
| "Já conheço Android com Java e quero usar Python em partes do app" | **Chaquopy** |
| "Preciso de interface sofisticada/profissional e já conheço Qt" | **Qt for Python** |
| "Só quero rodar meus scripts no celular" | Termux / Pydroid (seção 18) |

> **Sugestão para a disciplina:** use **Flet** para o primeiro contato (é o mais amigável) e **Kivy** para entender como funciona um framework de interface "do zero". O Chaquopy é uma ótima ponte para quem vem do material de **Java Mobile**.

---

## 5. Preparando o ambiente base (Python + venv)

Todos os frameworks começam com o mesmo passo: ter o **Python** instalado e usar um **ambiente virtual** (`venv`) para isolar as bibliotecas de cada projeto.

> Use uma versão do Python **suportada pelo framework escolhido**. Frameworks costumam demorar um pouco para suportar a versão mais nova do Python; verifique a documentação.

### 5.1 Windows

1. Baixe o instalador em <https://www.python.org/downloads/>.
2. **Marque a opção "Add python.exe to PATH"** na primeira tela e instale.
3. No **PowerShell**:

   ```powershell
   py --version
   py -m venv .venv
   .venv\Scripts\Activate.ps1
   python -m pip install --upgrade pip
   ```

   > Se o PowerShell bloquear a ativação, execute uma vez:
   > `Set-ExecutionPolicy -Scope CurrentUser RemoteSigned`

4. **Para Kivy/Buildozer:** o Buildozer **não funciona diretamente no Windows**. Use o **WSL2** (Windows Subsystem for Linux):

   ```powershell
   wsl --install -d Ubuntu
   ```

   Depois, siga as instruções de **Linux** dentro do Ubuntu do WSL2.

### 5.2 Linux (Ubuntu/Debian como exemplo)

```bash
sudo apt update
sudo apt install -y python3 python3-pip python3-venv git

python3 --version
python3 -m venv .venv
source .venv/bin/activate
python -m pip install --upgrade pip
```

### 5.3 macOS

1. Instale o Python pelo instalador oficial (<https://www.python.org/downloads/>) ou pelo **Homebrew**:

   ```bash
   brew install python
   ```

2. Crie o ambiente virtual:

   ```bash
   python3 --version
   python3 -m venv .venv
   source .venv/bin/activate
   python -m pip install --upgrade pip
   ```

### 5.4 O que mais será necessário?

| Para... | Você precisará de... |
|---|---|
| Flet (apenas testar no computador ou no celular via app Flet) | Só Python |
| Gerar APK com Flet / BeeWare / Kivy | **JDK** e **Android SDK** (algumas ferramentas baixam automaticamente) |
| Gerar app iOS | **Mac + Xcode** |
| Chaquopy | **Android Studio** (veja o material de Java Mobile) |

> Dica: instale o **Android Studio** (mesmo usando Python), pois ele traz o **Android SDK**, o **emulador** e o `adb`, que serão úteis em todos os frameworks. Veja o arquivo `java_mobile.md` para a instalação nos três sistemas.

---

## 6. Flet: o caminho mais simples para começar

O **Flet** permite criar interfaces modernas usando apenas Python, com um estilo declarativo. Por baixo dos panos, utiliza o **Flutter**.

### 6.1 Instalação

Com o ambiente virtual ativado (qualquer sistema):

```bash
pip install flet
```

### 6.2 Primeiro app: `main.py`

```python
import flet as ft


def main(page: ft.Page):
    page.title = "Meu Primeiro App"
    page.padding = 20

    campo_nome = ft.TextField(label="Digite seu nome")
    resultado = ft.Text(size=20)

    def saudar(e):
        nome = campo_nome.value.strip()
        if nome:
            resultado.value = f"Olá, {nome}!"
        else:
            resultado.value = "Digite seu nome!"
        page.update()

    page.add(
        campo_nome,
        ft.ElevatedButton("Saudar", on_click=saudar),
        resultado,
    )


ft.app(main)
```

> ⚠️ **Nota de versão:** o Flet evolui rápido. Em versões mais recentes, a forma de iniciar o app pode ser `ft.run(main)` em vez de `ft.app(main)`, e alguns nomes de componentes mudam. Se aparecer aviso de *deprecated*, consulte a documentação da versão instalada.

### 6.3 Executando no computador

```bash
flet run main.py
```

Uma janela abrirá com o app. Ao salvar o arquivo, ele **recarrega automaticamente** (*hot reload*).

Para ver no navegador:

```bash
flet run --web main.py
```

### 6.4 Testando no celular

1. Instale o aplicativo **Flet** no celular (Google Play / App Store).
2. Execute `flet run main.py` no computador e siga as instruções para conectar o celular na **mesma rede Wi-Fi** (consulte a documentação para o comando/QR Code da versão atual).

### 6.5 Gerando o APK

```bash
flet build apk
```

Na primeira execução, o Flet pode baixar o **Flutter** e outras dependências, então leva um tempo. O resultado fica na pasta `build/`.

> Para iOS, o comando equivalente exige **macOS com Xcode**.

---

## 7. Kivy + Buildozer: o clássico

O **Kivy** é um framework maduro que desenha a interface por conta própria (não usa componentes nativos). O **Buildozer** é a ferramenta que empacota o app Kivy em **APK/AAB** para Android.

### 7.1 Instalação para desenvolvimento (testar no computador)

```bash
pip install kivy
```

### 7.2 Primeiro app apenas com Python: `main.py`

```python
from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.textinput import TextInput
from kivy.uix.button import Button
from kivy.uix.label import Label


class Tela(BoxLayout):
    def __init__(self, **kwargs):
        super().__init__(orientation="vertical", padding=20, spacing=10, **kwargs)

        self.campo_nome = TextInput(hint_text="Digite seu nome", multiline=False,
                                    size_hint_y=None, height=48)
        botao = Button(text="Saudar", size_hint_y=None, height=48)
        botao.bind(on_press=self.saudar)
        self.resultado = Label(text="Olá, Mundo!", font_size="20sp")

        self.add_widget(self.campo_nome)
        self.add_widget(botao)
        self.add_widget(self.resultado)

    def saudar(self, instancia):
        nome = self.campo_nome.text.strip()
        self.resultado.text = f"Olá, {nome}!" if nome else "Digite seu nome!"


class MeuApp(App):
    def build(self):
        return Tela()


if __name__ == "__main__":
    MeuApp().run()
```

Execute:

```bash
python main.py
```

### 7.3 Versão com linguagem KV (separa visual e lógica)

O Kivy possui uma linguagem própria, a **KV**, parecida com os layouts XML do Android.

```python
from kivy.app import App
from kivy.lang import Builder

KV = """
BoxLayout:
    orientation: "vertical"
    padding: 20
    spacing: 10

    TextInput:
        id: campo_nome
        hint_text: "Digite seu nome"
        multiline: False
        size_hint_y: None
        height: "48dp"

    Button:
        text: "Saudar"
        size_hint_y: None
        height: "48dp"
        on_press: resultado.text = "Olá, " + campo_nome.text + "!"

    Label:
        id: resultado
        text: "Olá, Mundo!"
        font_size: "20sp"
"""


class MeuApp(App):
    def build(self):
        return Builder.load_string(KV)


MeuApp().run()
```

### 7.4 Gerando o APK com Buildozer (Linux, macOS ou WSL2)

> O Buildozer funciona em **Linux** e **macOS**. No **Windows**, use o **WSL2** com Ubuntu.

**Linux (Ubuntu/Debian) – dependências típicas:**

```bash
sudo apt update
sudo apt install -y git zip unzip openjdk-17-jdk python3-pip python3-venv \
    autoconf libtool pkg-config zlib1g-dev libncurses-dev cmake libffi-dev libssl-dev
```

> A lista exata de pacotes muda conforme a distribuição e a versão. Consulte a página *Installation* da documentação do Buildozer.

**macOS (com Homebrew):**

```bash
brew install autoconf automake libtool pkg-config openjdk
```

**Instalar e usar o Buildozer (com o venv ativado):**

```bash
pip install buildozer cython
buildozer init           # cria o arquivo buildozer.spec
```

Edite o `buildozer.spec` com atenção a estas linhas:

```ini
title = Meu App
package.name = meuapp
package.domain = org.exemplo
source.dir = .
source.include_exts = py,png,jpg,kv,atlas
version = 0.1
requirements = python3,kivy
orientation = portrait
android.permissions = INTERNET
```

Compile:

```bash
buildozer -v android debug
```

- A **primeira compilação demora bastante** (baixa SDK, NDK e compila o Python para Android).
- O APK é gerado na pasta `bin/`.

Instalar no celular conectado via USB e já ver os logs:

```bash
buildozer android deploy run logcat
```

---

## 8. BeeWare (Toga + Briefcase): interface nativa

O **BeeWare** é um conjunto de ferramentas:

- **Toga**: biblioteca de interface que usa **widgets nativos** de cada plataforma.
- **Briefcase**: ferramenta que cria o projeto e empacota o app para Android, iOS, Windows, Linux e macOS.

### 8.1 Instalação

```bash
pip install briefcase
```

### 8.2 Criando o projeto

```bash
briefcase new
```

Responda às perguntas do assistente (nome do app, identificador etc.). Entre na pasta criada.

### 8.3 Rodando no computador (modo desenvolvimento)

```bash
briefcase dev
```

### 8.4 Exemplo de `app.py` (Toga)

```python
import toga
from toga.style import Pack
from toga.style.pack import COLUMN


class MeuApp(toga.App):
    def startup(self):
        caixa = toga.Box(style=Pack(direction=COLUMN, margin=20))

        self.campo_nome = toga.TextInput(placeholder="Digite seu nome",
                                         style=Pack(margin_bottom=10))
        botao = toga.Button("Saudar", on_press=self.saudar,
                            style=Pack(margin_bottom=10))
        self.resultado = toga.Label("Olá, Mundo!")

        caixa.add(self.campo_nome)
        caixa.add(botao)
        caixa.add(self.resultado)

        self.main_window = toga.MainWindow(title=self.formal_name)
        self.main_window.content = caixa
        self.main_window.show()

    def saudar(self, widget):
        nome = self.campo_nome.value.strip()
        self.resultado.text = f"Olá, {nome}!" if nome else "Digite seu nome!"


def main():
    return MeuApp()
```

> O arquivo gerado por `briefcase new` já traz um esqueleto parecido. Em versões antigas do Toga, a propriedade de espaçamento chamava-se `padding`; nas atuais, `margin`. Siga o modelo gerado pela sua versão.

### 8.5 Gerando para Android

```bash
briefcase create android
briefcase build android
briefcase run android
```

O Briefcase pode **baixar automaticamente** o JDK e o Android SDK na primeira execução (confirme as perguntas exibidas no terminal). Funciona em **Windows, Linux e macOS**.

Para empacotar o app final:

```bash
briefcase package android
```

---

## 9. Chaquopy: Python dentro de um app Android (Java/Kotlin)

O **Chaquopy** é um **plugin do Gradle** que permite incluir Python em um projeto Android normal (feito com **Java ou Kotlin** no Android Studio). A interface continua em XML/Java; o Python cuida de partes específicas da lógica (ciência de dados, scripts, bibliotecas Python).

### 9.1 Quando usar?

- Você já tem (ou está aprendendo) um app Android em Java.
- Quer aproveitar bibliotecas Python (por exemplo, para processar dados) dentro do app.

### 9.2 Configuração (visão geral)

No `build.gradle` do **módulo** (`app`):

```groovy
plugins {
    id 'com.android.application'
    id 'com.chaquo.python'
}

android {
    defaultConfig {
        ndk {
            abiFilters "arm64-v8a", "x86_64"
        }
    }
}

chaquopy {
    defaultConfig {
        pip {
            install "requests"
        }
    }
}
```

> O plugin também precisa ser declarado no `build.gradle` do projeto, com a **versão** indicada na documentação atual. Siga o guia oficial para sua versão do Android Studio/Gradle.

### 9.3 Código Python: `app/src/main/python/ola.py`

```python
def saudar(nome):
    return f"Olá, {nome}! (mensagem vinda do Python)"
```

### 9.4 Chamando o Python a partir do Java

```java
import com.chaquo.python.Python;
import com.chaquo.python.PyObject;
import com.chaquo.python.android.AndroidPlatform;

// Em MainActivity.onCreate():
if (!Python.isStarted()) {
    Python.start(new AndroidPlatform(this));
}

Python py = Python.getInstance();
PyObject modulo = py.getModule("ola");
String mensagem = modulo.callAttr("saudar", "Maria").toString();

txtResultado.setText(mensagem);
```

> **Dica didática:** este é o melhor caminho para mostrar aos alunos como **Java e Python conversam** dentro do mesmo app.

---

## 10. Qt for Python (PySide6)

O **Qt for Python (PySide6)** é a ligação oficial do Qt com o Python. Também permite criar apps para Android (e iOS), usando **Qt Quick/QML**.

- Para **desktop** é muito usado e maduro.
- Para **Android**, o empacotamento é feito com a ferramenta de implantação do PySide (`pyside6-android-deploy`), que exige configurar SDK/NDK e é mais trabalhoso para iniciantes.
- Recomendado apenas depois de dominar os conceitos com Flet ou Kivy.

Instalação para testes no computador:

```bash
pip install PySide6
```

---

## 11. Conceitos comuns de apps móveis

Independentemente do framework, todo app móvel lida com estes conceitos:

| Conceito | Explicação | Exemplo em Python (Flet/Kivy/Toga) |
|---|---|---|
| **Tela (página)** | Uma "janela" do app | `Page` (Flet), `Screen` (Kivy), `MainWindow` (Toga) |
| **Widget / Controle** | Elemento visual | Botão, campo de texto, rótulo |
| **Layout** | Organiza os widgets | Coluna, linha, grade |
| **Evento** | Reação a uma ação do usuário | `on_click`, `on_press` |
| **Navegação** | Trocar de tela | `page.go()` (Flet), `ScreenManager` (Kivy) |
| **Ciclo de vida** | App iniciado, pausado, retomado, fechado | `on_start`, `on_pause`, `on_resume` (Kivy) |
| **Responsividade** | Adaptar a telas diferentes | Tamanhos relativos, `dp`/`sp` (Kivy) |

### Mapa mental para quem vem do Java/Android

| Android (Java) | Python (equivalente aproximado) |
|---|---|
| `Activity` | Tela/Página (`Screen`, `Page`) |
| `layout.xml` | Arquivo KV (Kivy) ou código declarativo (Flet) |
| `findViewById` | `ids` (Kivy) ou variável do controle (Flet) |
| `setOnClickListener` | `on_click` / `on_press` / `bind` |
| `Intent` | `ScreenManager` / navegação do framework |
| `SharedPreferences` | `JsonStore` (Kivy), `client_storage` (Flet), JSON em arquivo |
| `Toast` | `SnackBar` (Flet), `Popup` (Kivy) |
| `Gradle` | `buildozer.spec`, `pyproject.toml` (Briefcase) |

### Exemplo: navegação simples com ScreenManager (Kivy)

```python
from kivy.app import App
from kivy.lang import Builder
from kivy.uix.screenmanager import ScreenManager, Screen

KV = """
ScreenManager:
    Screen:
        name: "inicio"
        BoxLayout:
            orientation: "vertical"
            Label:
                text: "Tela Inicial"
            Button:
                text: "Ir para Detalhes"
                on_press: root.current = "detalhes"
    Screen:
        name: "detalhes"
        BoxLayout:
            orientation: "vertical"
            Label:
                text: "Tela de Detalhes"
            Button:
                text: "Voltar"
                on_press: root.current = "inicio"
"""


class NavApp(App):
    def build(self):
        return Builder.load_string(KV)


NavApp().run()
```

---

## 12. Armazenamento de dados

| Opção | Quando usar |
|---|---|
| **Arquivo JSON** | Configurações e dados simples |
| **`sqlite3`** (biblioteca padrão do Python) | Banco relacional local — não precisa instalar nada |
| **Armazenamento do framework** | `JsonStore` (Kivy), `page.client_storage` (Flet) |
| **API na nuvem / Firebase** | Dados sincronizados entre aparelhos |

### 12.1 JSON

```python
import json
from pathlib import Path

arquivo = Path("dados.json")

def salvar(dados: dict):
    arquivo.write_text(json.dumps(dados, ensure_ascii=False, indent=2), encoding="utf-8")

def carregar() -> dict:
    if arquivo.exists():
        return json.loads(arquivo.read_text(encoding="utf-8"))
    return {}
```

> ⚠️ **No celular**, não grave arquivos em qualquer pasta! Use o **diretório de dados do app** (no Kivy: `App.user_data_dir`; no Flet/Toga, consulte o caminho próprio do framework). Em um app instalado, a pasta do código costuma ser somente leitura.

### 12.2 SQLite

```python
import sqlite3

conn = sqlite3.connect("escola.db")   # em app real, use a pasta de dados do app
cur = conn.cursor()

cur.execute("""
    CREATE TABLE IF NOT EXISTS alunos (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        idade INTEGER
    )
""")

cur.execute("INSERT INTO alunos (nome, idade) VALUES (?, ?)", ("Maria", 20))
conn.commit()

for linha in cur.execute("SELECT id, nome, idade FROM alunos ORDER BY nome"):
    print(linha)

conn.close()
```

> Sempre use `?` (parâmetros) em vez de concatenar strings: isso evita **SQL Injection**.

---

## 13. Internet e tarefas em segundo plano

### 13.1 Consumindo uma API REST

```python
import requests

resposta = requests.get("https://jsonplaceholder.typicode.com/posts", timeout=10)
resposta.raise_for_status()
posts = resposta.json()
print(f"Recebidos {len(posts)} posts")
```

> Em Kivy/Buildozer, lembre-se de incluir `requests` (e suas dependências, como `urllib3`, `idna`, `charset-normalizer`, `certifi`) em `requirements`, e a permissão `INTERNET`.

### 13.2 Regra de ouro (vale para todos os frameworks)

> **Nunca** faça operações demoradas (rede, banco grande, arquivos pesados) na **thread principal da interface**. O app "congela" e o sistema pode encerrá-lo.

### 13.3 Exemplo com `threading` (conceito geral)

```python
import threading
import requests


def buscar_dados(ao_terminar):
    try:
        r = requests.get("https://jsonplaceholder.typicode.com/posts", timeout=10)
        ao_terminar(f"Recebidos {len(r.json())} posts")
    except Exception as erro:
        ao_terminar(f"Erro: {erro}")


def iniciar_busca(atualizar_tela):
    threading.Thread(target=buscar_dados, args=(atualizar_tela,), daemon=True).start()
```

> Atenção: a atualização da interface a partir de outra thread **depende do framework**:
>
> - **Kivy:** use `Clock.schedule_once(lambda dt: ..., 0)` para voltar à thread principal.
> - **Flet:** pode atualizar controles e chamar `page.update()`; para tarefas assíncronas, use funções `async`.
> - **Toga:** use as facilidades assíncronas (`async`/`await`) do framework.

### 13.4 `async`/`await`

O Flet e o Toga aceitam funções **assíncronas** diretamente nos eventos, o que simplifica chamadas de rede sem travar a tela. Estude `asyncio` quando estiver confortável com o básico.

---

## 14. Permissões e recursos do aparelho

Para acessar **câmera, GPS, vibração, notificações, bateria, contatos** etc.:

| Framework | Como acessar recursos nativos |
|---|---|
| **Kivy** | Biblioteca **Plyer** (API unificada) e **Pyjnius** (chamar classes Java do Android diretamente) |
| **Flet** | Controles/extensões oficiais (ex.: geolocalização, câmera, compartilhamento) |
| **BeeWare** | Módulos do Toga e **Rubicon** (acesso a APIs nativas) |
| **Chaquopy** | Código Java/Kotlin normal do Android |

### Exemplo com Plyer (Kivy)

```python
from plyer import vibrator

vibrator.vibrate(time=0.5)   # vibra por 0,5 segundo
```

Para funcionar no Android:

1. Adicione `plyer` aos `requirements` do `buildozer.spec`.
2. Declare a permissão no `buildozer.spec`:

   ```ini
   android.permissions = VIBRATE
   ```

> **Princípio:** peça **somente** as permissões de que o app realmente precisa e explique ao usuário o motivo.

---

## 15. Testando: emulador e celular físico

### 15.1 Teste rápido no computador

Antes de gerar APK, **sempre teste primeiro no desktop** (`python main.py`, `flet run`, `briefcase dev`). É muito mais rápido. Gere o APK apenas quando o app já estiver funcionando.

### 15.2 Emulador Android

Crie um AVD pelo **Android Studio** (*Tools → Device Manager*), com a imagem do sistema adequada ao seu computador:

- Windows/Linux (Intel/AMD): **x86_64**
- macOS com Apple Silicon: **arm64**

> A virtualização (VT-x/AMD-V, Hyper-V/WHPX, KVM) precisa estar habilitada. Veja o arquivo `java_mobile.md` para os detalhes por sistema operacional.

### 15.3 Celular físico (recomendado)

1. Ative as **Opções do desenvolvedor** (toque 7 vezes em *Número da versão*).
2. Ative a **Depuração USB**.
3. Conecte o cabo e autorize o computador no celular.
4. Confira:

   ```bash
   adb devices
   ```

### 15.4 Logs (essencial para descobrir erros)

```bash
adb logcat | grep -i python        # Linux/macOS
adb logcat | findstr /i python     # Windows (cmd/PowerShell)
```

Em apps Python no Android, `print()` aparece no **logcat**. Quando o app fecha sozinho ao abrir, o motivo quase sempre está ali.

---

## 16. Gerando e publicando o app

### 16.1 Resumo por ferramenta

| Ferramenta | Comando de build (Android) | Saída |
|---|---|---|
| **Flet** | `flet build apk` / `flet build aab` | APK / AAB |
| **Kivy + Buildozer** | `buildozer android debug` / `release` | APK / AAB |
| **BeeWare** | `briefcase package android` | APK / AAB |
| **Chaquopy** | Build normal do Android Studio | APK / AAB |

### 16.2 APK x AAB

| Formato | Descrição |
|---|---|
| **APK** | Instalável direto (testes, distribuição manual) |
| **AAB** | Formato exigido pela **Google Play** para publicação |

### 16.3 Assinatura

Apps de lançamento precisam ser **assinados** com uma *keystore*. **Guarde a keystore e as senhas com muito cuidado**: sem elas, não é possível atualizar o app publicado.

### 16.4 Publicação

- **Google Play:** conta no Play Console (taxa única), ficha da loja, política de privacidade, envio do AAB. As exigências mudam com frequência; consulte: <https://developer.android.com/distribute>
- **App Store (iOS):** exige conta **Apple Developer** (paga) e um **Mac** para a etapa de build/envio.

---

## 17. E o iOS?

- Apps iOS exigem o **Xcode**, que **só roda no macOS**.
- **Flet**, **BeeWare**, **Kivy** e **Qt** conseguem gerar apps iOS, mas a etapa de build final **precisa de um Mac**.
- Quem usa **Windows ou Linux** pode desenvolver e testar a lógica no computador e no Android, e deixar o build iOS para um Mac (ou um serviço de build na nuvem).
- A Apple restringe algumas práticas (como interpretadores dinâmicos). Os frameworks acima trabalham dentro das regras, mas **consulte a documentação** antes de planejar um app para a App Store.

---

## 18. Rodar Python no celular (sem criar app)

Se o objetivo é apenas **executar scripts Python no próprio celular**, existem aplicativos prontos:

| Plataforma | Aplicativo | Descrição |
|---|---|---|
| Android | **Termux** | Terminal Linux no celular; instala Python com `pkg install python` |
| Android | **Pydroid 3** | IDE de Python para Android |
| Android | **QPython** | Interpretador e editor |
| iOS | **Pythonista** | IDE de Python para iPhone/iPad |
| iOS | **Pyto** | Interpretador e editor para iOS |

Exemplo no **Termux**:

```bash
pkg update
pkg install python
python --version
python meu_script.py
```

> Isso **não gera um app instalável** para distribuir; serve para estudo, automação e testes.

---

## 19. Python como "cérebro" (backend) do app

Uma estratégia muito comum na prática: o **app móvel** (em Java, Kotlin, Flutter, Flet...) cuida da interface, e o **Python roda em um servidor**, expondo uma **API REST**.

```
 [App no celular]  ── HTTP/JSON ──►  [API em Python (FastAPI/Flask/Django)]  ──►  [Banco de dados]
```

### Exemplo mínimo com FastAPI

```bash
pip install fastapi uvicorn
```

```python
# api.py
from fastapi import FastAPI

app = FastAPI()

alunos = [{"id": 1, "nome": "Maria"}, {"id": 2, "nome": "João"}]


@app.get("/alunos")
def listar_alunos():
    return alunos
```

Execute:

```bash
uvicorn api:app --reload
```

Acesse `http://127.0.0.1:8000/alunos` (e a documentação automática em `/docs`).

**Vantagens:** o Python fica fora do celular (sem problemas de empacotamento), o servidor pode ser atualizado sem publicar nova versão do app, e o mesmo backend atende Android, iOS e web.

---

## 20. Limitações e cuidados

1. **Tamanho do app:** o APK inclui o interpretador Python e as bibliotecas; apps simples costumam ficar com dezenas de MB.
2. **Tempo de inicialização:** pode ser maior que o de um app nativo.
3. **Compatibilidade de bibliotecas:** nem toda biblioteca do PyPI funciona no celular. Bibliotecas com código compilado (C/C++) precisam de **receitas/builds específicos** para Android. Pacotes pesados (por exemplo, NumPy, Pandas) são possíveis em algumas ferramentas, mas aumentam muito o tamanho e a complexidade do build.
4. **Desempenho:** não é ideal para jogos 3D pesados ou processamento intenso na própria interface.
5. **Acesso a recursos nativos:** nem tudo está pronto; às vezes é preciso escrever código Java/Kotlin (ou usar Pyjnius/Rubicon).
6. **Evolução rápida:** versões de frameworks e do Python mudam; exemplos antigos da internet podem não funcionar mais.
7. **Compilação demorada:** a primeira build (especialmente no Buildozer) pode levar bastante tempo e muito espaço em disco.

> Para apps comerciais grandes e de alto desempenho, o desenvolvimento **nativo** (Java/Kotlin no Android) ou outras tecnologias ainda é a escolha mais segura. Python Mobile brilha em **protótipos, ferramentas internas, apps educacionais, apps de dados e automação**.

---

## 21. Boas práticas

1. **Um ambiente virtual por projeto** (`venv`) e um arquivo `requirements.txt`.
2. **Teste no desktop primeiro**, depois no emulador, depois no aparelho real.
3. **Separe lógica e interface:** deixe as regras de negócio em módulos Python puros, sem depender do framework. Assim você pode testá-las com `pytest` e trocar de framework com menos esforço.
4. **Não bloqueie a interface:** rede e banco em segundo plano.
5. **Trate erros:** `try/except`, validação de campos e mensagens claras.
6. **Fixe versões** das dependências (`pip freeze > requirements.txt`) para builds reproduzíveis.
7. **Use Git** desde o início; inclua um `.gitignore` (ignore `.venv/`, `build/`, `bin/`, `.buildozer/`, `__pycache__/`).
8. **Segurança:** nunca deixe chaves de API ou senhas no código; use HTTPS.
9. **Pense em telas diferentes:** teste em telas pequenas e grandes, em retrato e paisagem.
10. **Leia a documentação da versão instalada:** muitos erros vêm de exemplos desatualizados.

Estrutura sugerida de projeto:

```
meu_app/
├── .venv/
├── requirements.txt
├── main.py              ← ponto de entrada (interface)
├── servicos/            ← acesso a API, banco de dados
│   ├── api.py
│   └── banco.py
├── modelos/             ← classes de dados
│   └── aluno.py
├── assets/              ← imagens, fontes
└── tests/               ← testes automatizados
```

---

## 22. Problemas comuns e soluções

| Problema | Causa provável | Solução |
|---|---|---|
| `pip` instala no Python errado | Ambiente virtual não ativado | Ative o venv; confira com `which python` (Linux/macOS) ou `where python` (Windows) |
| PowerShell bloqueia `Activate.ps1` | Política de execução | `Set-ExecutionPolicy -Scope CurrentUser RemoteSigned` |
| `buildozer` não funciona no Windows | Não é suportado nativamente | Use **WSL2** com Ubuntu |
| Build do Buildozer falha logo no início | Dependências do sistema ausentes ou JDK incompatível | Reveja os pacotes da documentação; use o JDK recomendado |
| Build demora "uma eternidade" | Primeira compilação baixa e compila muita coisa | Seja paciente; as próximas serão mais rápidas |
| App abre e fecha imediatamente | Erro em tempo de execução, biblioteca faltando em `requirements` | Veja o `adb logcat`; adicione a dependência ao `requirements` |
| `ModuleNotFoundError` no celular | Biblioteca não incluída no pacote | Inclua em `requirements` (Buildozer), `pyproject.toml` (Briefcase) ou `pip install` do Chaquopy |
| Sem internet no app | Faltou permissão | Adicione `INTERNET` às permissões |
| Interface trava | Operação demorada na thread principal | Use threads/`async` |
| Exemplo da internet não funciona | Versão diferente do framework | Consulte a documentação da versão instalada; procure por *deprecated* |
| Celular não aparece em `adb devices` | Cabo só de carga, depuração desligada, driver ausente | Troque o cabo; ative Depuração USB; Windows: driver do fabricante; Linux: regras udev |
| Pouco espaço em disco | Caches do SDK/NDK/Buildozer | Limpe `.buildozer/`, `build/` e AVDs não usados |
| Erro de versão do Python | Framework ainda não suporta a versão instalada | Instale uma versão anterior compatível e crie o venv com ela |

---

## 23. Exercícios propostos

### Nível 1 – Básico (rodando no computador)

1. **Olá, Mundo personalizado** (Flet ou Kivy): altere textos, cores e tamanhos.
2. **Contador:** um botão "+1" que incrementa um número na tela.
3. **Calculadora simples:** dois campos numéricos e botões para as quatro operações (trate divisão por zero).
4. **Conversor de temperatura:** Celsius ↔ Fahrenheit.

### Nível 2 – Intermediário

5. **Duas telas:** na primeira, digitar nome e idade; na segunda, mostrar os dados.
6. **Lista de tarefas em memória:** adicionar e remover itens de uma lista.
7. **Persistência:** salvar o nome do usuário em JSON e carregá-lo ao reabrir o app.
8. **Gerar o primeiro APK** e instalar no celular (ou emulador), conferindo o `logcat`.

### Nível 3 – Avançado

9. **To-Do com SQLite:** cadastrar, listar, concluir e excluir tarefas.
10. **Consumo de API:** listar dados de uma API pública com `requests`, sem travar a interface.
11. **App + backend:** criar uma API com FastAPI (seção 19) e um app que a consuma.
12. **Ponte Java + Python (Chaquopy):** app Android em Java que chama uma função Python (seção 9).
13. **Mini-projeto:** app completo de sua escolha (agenda, controle financeiro, cadastro de alunos), com ao menos 3 telas, armazenamento local e validações.

### Desafio de comparação

14. Refaça o **mesmo app** (por exemplo, a calculadora de IMC) em **Android com Java** e em **Flet/Kivy**. Compare: quantidade de linhas, tempo de desenvolvimento, tamanho do APK e aparência.

---

## 24. Glossário

| Termo | Significado |
|---|---|
| **ADB** | *Android Debug Bridge*: ferramenta de linha de comando para falar com o dispositivo Android |
| **APK** | Pacote de instalação do app Android |
| **AAB** | *Android App Bundle*: formato de publicação da Google Play |
| **API REST** | Interface de comunicação via HTTP, geralmente com JSON |
| **AVD** | *Android Virtual Device*: celular virtual (emulador) |
| **Briefcase** | Ferramenta do BeeWare para empacotar apps |
| **Buildozer** | Ferramenta que empacota apps Kivy para Android |
| **Chaquopy** | Plugin Gradle que embute Python em apps Android |
| **CPython** | Implementação oficial do Python |
| **Flet** | Framework Python para apps multiplataforma (baseado em Flutter) |
| **Hot reload** | Atualização do app em execução ao salvar o código |
| **KV** | Linguagem de descrição de interface do Kivy |
| **Keystore** | Arquivo com a chave de assinatura do app |
| **Logcat** | Registro de mensagens do sistema Android |
| **Plyer** | Biblioteca Python com API unificada para recursos do aparelho |
| **Pyjnius** | Biblioteca para chamar classes Java a partir do Python |
| **Toga** | Biblioteca de interface nativa do BeeWare |
| **venv** | Ambiente virtual do Python |
| **WSL2** | Subsistema Linux do Windows |
| **Widget** | Elemento visual da interface |

---

## 25. Referências e links úteis

- **Python:** <https://www.python.org>
- **Flet:** <https://flet.dev>
- **Kivy:** <https://kivy.org>
- **Buildozer:** <https://buildozer.readthedocs.io>
- **BeeWare:** <https://beeware.org>
- **Chaquopy:** <https://chaquo.com/chaquopy/>
- **Qt for Python:** <https://doc.qt.io/qtforpython-6/>
- **Plyer:** <https://plyer.readthedocs.io>
- **FastAPI:** <https://fastapi.tiangolo.com>
- **Android Studio / Android Developers:** <https://developer.android.com>
- **Termux:** <https://termux.dev>

---

> **Observação final:** o ecossistema Python Mobile muda com rapidez. Versões de frameworks, comandos e requisitos de lojas citados aqui servem como orientação; confirme sempre na documentação oficial da versão que você for utilizar antes de iniciar um projeto.
