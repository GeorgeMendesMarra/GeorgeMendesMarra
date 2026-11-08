# Versões do Python Mobile

> Guia completo das versões, frameworks, ferramentas e características do **Python para dispositivos móveis**: Python no **Google Android**, no **iOS** e em plataformas antigas (Symbian, microcontroladores etc.).

---

## Sumário

1. [Visão geral: o que é "Python Mobile"?](#1-visão-geral-o-que-é-python-mobile)
2. [Linha do tempo](#2-linha-do-tempo)
3. [Versões do Python e suporte móvel](#3-versões-do-python-e-suporte-móvel)
4. [Suporte oficial do CPython a Android e iOS](#4-suporte-oficial-do-cpython-a-android-e-ios)
5. [Como o Python roda no celular](#5-como-o-python-roda-no-celular)
6. [Frameworks e ferramentas: comparativo](#6-frameworks-e-ferramentas-comparativo)
7. [Kivy, Buildozer e python-for-android](#7-kivy-buildozer-e-python-for-android)
8. [BeeWare (Toga e Briefcase)](#8-beeware-toga-e-briefcase)
9. [Flet](#9-flet)
10. [Chaquopy](#10-chaquopy)
11. [Qt for Python (PySide6)](#11-qt-for-python-pyside6)
12. [Apps para programar no próprio celular](#12-apps-para-programar-no-próprio-celular)
13. [Plataformas antigas e embarcadas](#13-plataformas-antigas-e-embarcadas)
14. [Python no Google Android: versões compatíveis](#14-python-no-google-android-versões-compatíveis)
15. [Python no iOS](#15-python-no-ios)
16. [Limitações do Python em dispositivos móveis](#16-limitações-do-python-em-dispositivos-móveis)
17. [Alternativas: web, PWA e backend](#17-alternativas-web-pwa-e-backend)
18. [Ferramentas por sistema operacional](#18-ferramentas-por-sistema-operacional)
19. [Exemplos de código](#19-exemplos-de-código)
20. [Qual escolher hoje?](#20-qual-escolher-hoje)
21. [Python Mobile vs. Java Mobile](#21-python-mobile-vs-java-mobile)
22. [Glossário](#22-glossário)
23. [Referências](#23-referências)

---

## 1. Visão geral: o que é "Python Mobile"?

Diferentemente do Java (que tem uma plataforma oficial, o Java ME, e é linguagem de primeira classe no Android), o Python **não é uma linguagem nativa** de Android nem de iOS. "Python Mobile" é, na prática, o conjunto de **técnicas** para empacotar um interpretador Python dentro de um app móvel:

| Abordagem | Ideia | Exemplos |
|---|---|---|
| **Framework multiplataforma em Python** | Você escreve a interface e a lógica em Python; a ferramenta gera o APK/IPA | Kivy, BeeWare (Toga), Flet |
| **Python embarcado em app nativo** | App Android/iOS em Java/Kotlin/Swift que chama Python | Chaquopy, Python-Apple-support, Qt for Python |
| **Python como script no aparelho** | O Python roda como app de terminal/IDE no celular | Pydroid 3, QPython, Termux, Pythonista, Pyto |
| **Python no navegador** | O Python roda em WebAssembly | Pyodide, PyScript |
| **Python como backend** | O app móvel (qualquer linguagem) consome uma API Python | Django, Flask, FastAPI |
| **Python em microcontroladores** | Subconjunto de Python em hardware limitado | MicroPython, CircuitPython |

---

## 2. Linha do tempo

| Ano | Marco |
|---|---|
| 1991 | Primeira versão pública do Python (0.9) |
| 2000 | Python 2.0 |
| ~2005 | **PyS60** (Python for S60): Python em celulares Nokia/Symbian |
| 2008 | Python 3.0 (quebra de compatibilidade com o 2.x) |
| 2008 | Android 1.0 (Java como linguagem oficial) |
| 2009 | **SL4A** (Scripting Layer for Android): scripts Python no Android |
| 2011 | **Kivy 1.0**: primeiro framework Python multiplataforma com suporte móvel |
| 2012–2013 | **python-for-android** e **Pygame Subset for Android** (PGS4A) |
| 2012 | **Pythonista** (iOS) |
| 2014 | **MicroPython 1.0** (microcontroladores) |
| 2016 | Pythonista 3 (Python 3 no iOS) |
| ~2017 | **Chaquopy** (Python em apps Android com Gradle) |
| 2020 | Fim de vida (EOL) do Python 2.7 |
| 2021 | **Kivy 2.0** (apenas Python 3) |
| 2022 | **Flet** (Python + Flutter) |
| 2023–2024 | **PEP 730** (iOS) e **PEP 738** (Android): suporte oficial no CPython |
| Out/2024 | **Python 3.13**: Android e iOS como plataformas oficiais "Tier 3" |
| Out/2025 | **Python 3.14**: distribuição de binários oficiais para Android e iOS (conforme as notas de versão) |

---

## 3. Versões do Python e suporte móvel

| Versão | Lançamento | Fim de suporte (EOL) | Destaques | Situação no mobile |
|---|---|---|---|---|
| **2.7** | 2010 | Jan/2020 | Última da série 2.x | ❌ Obsoleta. Usada por PyS60 (2.5), SL4A, Kivy 1.x antigo. **Não use.** |
| **3.5–3.7** | 2015–2018 | 2020–2023 | `async/await`, f-strings (3.6), dataclasses (3.7) | ❌ Obsoletas; Pythonista 3 ainda usa 3.6 (legado) |
| **3.8** | Out/2019 | Out/2024 | Operador `:=` (walrus), parâmetros só-posicionais | ⚠️ Fim de vida; ainda em algumas versões antigas de Chaquopy/p4a |
| **3.9** | Out/2020 | Out/2025 | União de dicionários (`\|`), tipos genéricos nativos | ⚠️ Fim de vida |
| **3.10** | Out/2021 | Out/2026 | `match`/`case`, mensagens de erro melhores | ⚠️ Fim de vida (Out/2026) |
| **3.11** | Out/2022 | Out/2027 | Muito mais rápido (~25%), `ExceptionGroup`, `tomllib` | ✅ Suportado por Kivy/p4a, Chaquopy, BeeWare |
| **3.12** | Out/2023 | Out/2028 | F-strings mais flexíveis, sintaxe de tipos genérica, remoção do `distutils` | ✅ Muito usado hoje (Kivy, Chaquopy, Flet) |
| **3.13** | Out/2024 | Out/2029 | **Android/iOS Tier 3**, REPL novo, *free-threading* e JIT experimentais | ✅ Primeira versão com suporte oficial a Android/iOS |
| **3.14** | Out/2025 | Out/2030 | Binários oficiais Android/iOS, anotações adiadas, *t-strings*, *free-threading* suportado | ✅ Recomendada para novos projetos, conforme o framework |
| **3.15** | prevista p/ Out/2026 | Out/2031 | Em desenvolvimento/lançamento | ⏳ Verifique o status atual do framework antes de usar |

> **Regra prática:** use a versão **mais recente que o seu framework suporta** (hoje, geralmente 3.12 a 3.14). Dependências com código nativo (NumPy, Pillow, cryptography etc.) precisam ter **receitas/wheels para Android/iOS** na versão escolhida.

---

## 4. Suporte oficial do CPython a Android e iOS

Os documentos PEP definem o suporte oficial:

| PEP | Plataforma | Desde | Categoria |
|---|---|---|---|
| **PEP 730** | iOS | Python 3.13 | Tier 3 |
| **PEP 738** | Android | Python 3.13 | Tier 3 |

**O que "Tier 3" significa:** a plataforma tem suporte da comunidade e testes automáticos, mas os *core developers* não garantem correção imediata de falhas; os binários oficiais passaram a ser distribuídos nas versões seguintes.

### Detalhes do Android (CPython 3.13+)

| Item | Valor |
|---|---|
| API mínima | **API 21** (Android 5.0 Lollipop) |
| ABIs oficiais | `arm64-v8a` (ARM 64 bits) e `x86_64` (emulador) |
| ABIs não oficiais | `armeabi-v7a` (ARM 32 bits) e `x86` — podem ser compilados por terceiros (Chaquopy, p4a) |
| `sys.platform` | `"android"` |
| `platform.system()` | `"Android"` |
| Função útil | `platform.android_ver()` |
| Implementação | Biblioteca C **Bionic** (libc do Android), não glibc |
| Distribuição | Pacote com `libpython` + stdlib; integração via Gradle/CMake |

### Detalhes do iOS (CPython 3.13+)

| Item | Valor |
|---|---|
| Arquiteturas | `arm64` (aparelho), `arm64` e `x86_64` (simulador) |
| `sys.platform` | `"ios"` |
| `platform.system()` | `"iOS"` |
| Função útil | `platform.ios_ver()` |
| Formato | `Python.xcframework` (XCFramework) |
| Requer | macOS + Xcode |
| Regras da Apple | Módulos nativos devem ser embalados como *frameworks*, sem `fork`, sem execução de código gerado dinamicamente (JIT) |

---

## 5. Como o Python roda no celular

```
Seu código .py ─► Empacotador (Buildozer, Briefcase, Flet, Gradle) ─►
   ├─ Interpretador CPython compilado para ARM (libpython)
   ├─ Biblioteca padrão (stdlib) reduzida
   ├─ Bibliotecas de terceiros (wheels/receitas nativas)
   └─ Ponte para o sistema nativo (JNI no Android; Objective-C/Swift no iOS)
        ▼
   APK/AAB (Android)  ou  IPA (iOS)  ─►  Execução no aparelho
```

Pontos importantes:

- O Python **é interpretado** (bytecode `.pyc`); não há compilação para código nativo por padrão.
- O app **inclui o interpretador**, por isso o APK começa com cerca de **10–30 MB** (varia conforme ABIs e bibliotecas).
- A **interface** pode ser desenhada pelo próprio framework (Kivy, Flet/Flutter) ou usar **widgets nativos** (Toga).
- O acesso a recursos do sistema (câmera, GPS, sensores, notificações) é feito por **pontes**: Pyjnius/Plyer (Android), Pyobjus (iOS), Rubicon (BeeWare), Chaquopy (Java ↔ Python).

---

## 6. Frameworks e ferramentas: comparativo

| Ferramenta | Tipo | Interface (UI) | Android | iOS | Desktop | Licença | Maturidade |
|---|---|---|---|---|---|---|---|
| **Kivy** | Framework | Própria (OpenGL ES, KV Language) | ✅ | ✅ | ✅ | MIT | Alta (2011) |
| **KivyMD** | Biblioteca para Kivy | Material Design | ✅ | ✅ | ✅ | MIT | Alta |
| **BeeWare (Toga)** | Framework | **Widgets nativos** | ✅ | ✅ | ✅ | BSD | Média/Alta |
| **Flet** | Framework | Flutter (Material) | ✅ | ✅ | ✅ | Apache 2.0 | Média (jovem, evolui rápido) |
| **Chaquopy** | Plugin Gradle | Nativa Android (Java/Kotlin) + Python | ✅ | ❌ | ❌ | MIT | Alta |
| **Qt for Python (PySide6)** | Framework | Qt Widgets/QML | ✅ (em evolução) | ✅ (em evolução) | ✅ | LGPL/Comercial | Média no mobile |
| **python-for-android (p4a)** | Toolchain | — (base do Buildozer) | ✅ | ❌ | ❌ | MIT | Alta |
| **kivy-ios** | Toolchain | — | ❌ | ✅ | ❌ | MIT | Média |
| **Ren'Py** | Engine de visual novels | Própria | ✅ | ✅ | ✅ | MIT | Alta |
| **Pydroid 3** | App/IDE | Terminal/Editor | ✅ | ❌ | ❌ | Proprietário | Alta |
| **Termux** | Terminal Linux | CLI | ✅ | ❌ | ❌ | GPL | Alta |
| **QPython** | App/IDE | Terminal/Editor | ✅ | ❌ | ❌ | Proprietário | Média |
| **Pythonista** | App/IDE | Editor + UI própria | ❌ | ✅ | ❌ | Proprietário | Média (Python 3.6) |
| **Pyto** | App/IDE | Editor | ❌ | ✅ | ❌ | Proprietário | Média |
| **SL4A / Py4A** | Scripts | — | ✅ (antigo) | ❌ | ❌ | Apache 2.0 | ❌ Abandonado (~2015) |
| **PGS4A** | Pygame no Android | Pygame | ✅ (antigo) | ❌ | ❌ | LGPL | ❌ Obsoleto |
| **PyS60** | Python para Symbian | Nativa S60 | ❌ | ❌ | ❌ | Nokia | ❌ Obsoleto (2010) |
| **MicroPython** | Interpretador embarcado | — | ❌ | ❌ | ❌ | MIT | Alta |
| **Pyodide / PyScript** | Python no navegador | HTML/DOM | via PWA | via PWA | ✅ | MPL/Apache | Alta |

---

## 7. Kivy, Buildozer e python-for-android

### Versões do Kivy

| Versão | Ano | Python | Observações |
|---|---|---|---|
| **1.0–1.11** | 2011–2019 | 2.7 e 3.x | Série legada; suporte a Python 2 |
| **2.0** | 2021 | 3.6+ | **Apenas Python 3**; SDL2 como backend |
| **2.1** | 2022 | 3.7+ | Melhorias de desempenho e suporte a Apple Silicon |
| **2.2** | 2023 | 3.7–3.11 | Novos widgets, correções |
| **2.3.x** | 2023–2024 | 3.8–3.12/3.13 | Linha estável atual (2.3.0 em 2023; 2.3.1 em 2024) |
| **3.0 (dev)** | em desenvolvimento | 3.9+ (planejado) | Modernização; verificar o site oficial |

### Características do Kivy

- UI própria (não usa widgets nativos): visual idêntico em todas as plataformas.
- **Linguagem KV** para separar layout e lógica.
- **Multitoque** (gestos), animações, OpenGL ES 2.0.
- Módulos de recursos: `plyer` (câmera, GPS, vibração, notificações, bateria, acelerômetro...), `pyjnius` (acesso à API Java do Android), `pyobjus` (iOS).
- Bibliotecas complementares: **KivyMD** (Material Design), **Kivy Garden**, **KivEnt** (jogos), **Kivy Reloader** (hot-reload).

### Buildozer

Ferramenta de linha de comando que automatiza o **python-for-android**.

| Item | Detalhe |
|---|---|
| Arquivo de configuração | `buildozer.spec` |
| Gera | APK (debug/release), **AAB** (Google Play), pacotes iOS (via kivy-ios, no macOS) |
| Sistema para compilar Android | **Linux** ou **macOS** (no Windows, use **WSL2**) |
| Dependências | Python, JDK, Android SDK/NDK (baixados automaticamente), Cython, Git |
| Comandos principais | `buildozer init`, `buildozer -v android debug`, `buildozer android release`, `buildozer android deploy run logcat` |

**Trecho típico do `buildozer.spec`:**

```ini
[app]
title = Meu App
package.name = meuapp
package.domain = br.com.exemplo
source.dir = .
source.include_exts = py,png,jpg,kv,atlas
version = 0.1
requirements = python3,kivy
orientation = portrait
fullscreen = 0

android.permissions = INTERNET
android.api = 35
android.minapi = 21
android.archs = arm64-v8a, armeabi-v7a
android.release_artifact = aab
```

### python-for-android (p4a)

- Transforma um app Python em APK/AAB, usando **receitas** (*recipes*) para compilar bibliotecas nativas (numpy, pillow, sqlite3, openssl...).
- **Bootstraps**: `sdl2` (padrão do Kivy), `webview` (app com WebView + servidor Flask/Django local), `service_only` (serviços em segundo plano).
- Suporta as ABIs: `armeabi-v7a`, `arm64-v8a`, `x86`, `x86_64`.

---

## 8. BeeWare (Toga e Briefcase)

O **BeeWare** é uma suíte para criar apps nativos com Python.

| Componente | Função |
|---|---|
| **Toga** | Biblioteca de **widgets nativos** (Android, iOS, Windows, macOS, Linux, web) |
| **Briefcase** | Empacota o app: cria projeto, compila, executa e gera instaladores (APK, AAB, IPA, MSI, DMG, AppImage...) |
| **Rubicon-ObjC** | Ponte Python ↔ Objective-C/Swift (iOS/macOS) |
| **Rubicon-Java** | Ponte Python ↔ Java (Android) |
| **Python-Apple-support** | Distribuição do Python para iOS, macOS, tvOS e watchOS |
| **Chaquopy** | Briefcase usa o Chaquopy como base para rodar Python no Android |

Características:

- Os controles são **nativos** (a aparência segue a plataforma).
- Suporta Python **3.9 a 3.13** (conforme a versão do Briefcase; verifique a documentação).
- Android: compila com Gradle; iOS: exige macOS + Xcode.

**Fluxo de trabalho com o Briefcase:**

```bash
python -m pip install briefcase
briefcase new                    # cria um projeto
briefcase dev                    # executa no desktop (desenvolvimento)
briefcase create android         # gera o projeto Android
briefcase build android          # compila
briefcase run android            # executa no emulador/dispositivo
briefcase package android        # gera APK/AAB para publicação
# iOS (somente no macOS): briefcase create iOS / build iOS / run iOS
```

---

## 9. Flet

O **Flet** permite criar apps com Python usando os componentes do **Flutter**, sem escrever Dart.

| Característica | Detalhe |
|---|---|
| Interface | Widgets Material/Cupertino do Flutter, descritos em Python |
| Plataformas | Android, iOS, Windows, macOS, Linux e **web** |
| Empacotamento | `flet build apk`, `flet build aab`, `flet build ipa`, `flet build web` |
| Base | **Serious Python** (CPython embarcado em app Flutter) |
| Requisitos | **Flutter SDK** (instalado/gerenciado pela ferramenta), Android SDK, JDK; Xcode para iOS |
| Hot reload | `flet run` |
| Pontos fortes | Código curto, bom visual, mesma base para web e mobile |
| Pontos de atenção | Projeto jovem: a API muda entre versões; verifique a documentação da versão instalada |

---

## 10. Chaquopy

O **Chaquopy** é um plugin do Gradle que permite usar Python **dentro de um app Android escrito em Java/Kotlin**.

| Característica | Detalhe |
|---|---|
| Versões de Python | 3.8 a 3.13, conforme a versão do plugin (consulte a documentação) |
| Integra com | Android Studio / Gradle |
| Acesso Java ↔ Python | API completa nos dois sentidos |
| Pacotes pip | Instala pacotes pelo bloco `pip { install ... }`; há repositório próprio com *wheels* compiladas para Android (NumPy, SciPy, pandas, scikit-learn, OpenCV, cryptography e outras) |
| ABIs | `armeabi-v7a`, `arm64-v8a`, `x86`, `x86_64` |
| API mínima | Normalmente **API 21** (confira na versão do plugin) |
| Uso típico | Reaproveitar bibliotecas Python (ciência de dados, IA, criptografia) em apps nativos |

**Configuração (`app/build.gradle`):**

```groovy
plugins {
    id 'com.android.application'
    id 'com.chaquo.python'    // use a versão mais recente do plugin
}

android {
    defaultConfig {
        ndk { abiFilters "arm64-v8a", "x86_64" }
        python {
            version "3.12"
            pip {
                install "numpy"
                install "requests"
            }
        }
    }
}
```

**Chamando Python a partir do Java:**

```java
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

if (!Python.isStarted()) {
    Python.start(new AndroidPlatform(this));
}
Python py = Python.getInstance();
PyObject modulo = py.getModule("calculos");          // arquivo calculos.py
int resultado = modulo.callAttr("somar", 2, 3).toInt();
```

---

## 11. Qt for Python (PySide6)

- Binding oficial do **Qt** para Python (projeto Qt Company).
- Suporte móvel por meio da ferramenta `pyside6-android-deploy` (Android) e ferramentas equivalentes para iOS; o recurso evolui a cada versão do Qt 6.
- Permite interface em **QML** (declarativa) ou **Qt Widgets**.
- **Licença:** LGPL v3 (uso aberto) ou licença comercial.
- Indicado para quem já usa Qt no desktop e quer reaproveitar o código.

---

## 12. Apps para programar no próprio celular

| App | Plataforma | Python | Observações |
|---|---|---|---|
| **Pydroid 3** | Android | 3.x (versão da loja) | IDE completa, `pip` com repositório próprio, suporte a bibliotecas científicas |
| **QPython 3L / 3x** | Android | 3.x | Terminal e editor; suporte a scripts e QPYPI |
| **Termux** | Android | Qualquer versão disponível nos repositórios (`pkg install python`) | Terminal Linux completo; instalação preferencial pelo **F-Droid** |
| **Pythonista 3** | iOS/iPadOS | 3.6 (antigo) | Bibliotecas de UI para iOS (`ui`, `scene`) |
| **Pyto** | iOS/iPadOS | 3.x recente | Acesso a frameworks da Apple; Jupyter |
| **Carnets / a-Shell** | iOS/iPadOS | 3.x | Jupyter local e shell com Python |
| **Pydroid / Jupyter** | Android | 3.x | Notebooks no celular |

---

## 13. Plataformas antigas e embarcadas

| Plataforma | Python | Observações |
|---|---|---|
| **PyS60 (Symbian S60)** | 2.2 → 2.5.4 | Nokia (2005–2010); abandonada |
| **Maemo / MeeGo (Nokia N900, N9)** | 2.5 / 2.7 | PyQt, PyMaemo |
| **webOS (Palm)** | 2.x | Via serviços nativos |
| **Sailfish OS** | 3.x | PyOtherSide (Python + QML) |
| **Ubuntu Touch** | 3.x | Apps com Python + QML |
| **MicroPython** | Subconjunto do Python 3.4+ | Microcontroladores (ESP32, Raspberry Pi Pico, STM32); `machine`, GPIO, I2C, SPI |
| **CircuitPython (Adafruit)** | Derivado do MicroPython | Placas Adafruit, Raspberry Pi Pico |
| **Raspberry Pi** | CPython completo | Embarcado Linux; fora do conceito "mobile" clássico |

---

## 14. Python no Google Android: versões compatíveis

### 14.1 Compatibilidade por versão do Android

| Android | API | Ano | Python moderno (3.9+)? | Observações |
|---|---|---|---|---|
| 1.0–4.4 (KitKat) | 1–19 | 2008–2013 | ❌ | Somente SL4A/p4a antigos com Python 2.7/3.x legado |
| **5.0–5.1 (Lollipop)** | **21–22** | 2014 | ✅ | **API mínima** das ferramentas modernas (Kivy, Chaquopy, CPython 3.13) |
| 6.0 (Marshmallow) | 23 | 2015 | ✅ | Permissões em tempo de execução |
| 7.0–7.1 (Nougat) | 24–25 | 2016 | ✅ | Algumas ferramentas (ex.: BeeWare/Toga) podem exigir API 24 ou superior |
| 8.0–8.1 (Oreo) | 26–27 | 2017 | ✅ | Canais de notificação |
| 9 (Pie) | 28 | 2018 | ✅ | Restrições de tráfego HTTP em texto puro |
| 10 | 29 | 2019 | ✅ | *Scoped Storage*; execução de binários a partir de pastas de dados restrita (impacta o Termux) |
| 11 | 30 | 2020 | ✅ | Visibilidade de pacotes |
| 12 / 12L | 31–32 | 2021–2022 | ✅ | |
| 13 | 33 | 2022 | ✅ | Permissão para notificações |
| 14 | 34 | 2023 | ✅ | Tipos de serviços em primeiro plano |
| 15 | 35 | 2024 | ✅ | *Edge-to-edge* por padrão |
| 16 | 36 | 2025 | ✅ | Consulte a documentação oficial para mudanças recentes |

> Versões do Android posteriores à 16 podem existir; consulte a página oficial de níveis de API.

### 14.2 Pontos que afetam apps Python no Android

- **`minSdk`**: use **21** (ou 24, conforme a ferramenta) para atingir a grande maioria dos aparelhos.
- **`targetSdk`**: o Google Play exige que novos apps e atualizações mirem uma versão recente da API (a exigência sobe todos os anos; confira as regras vigentes).
- **ABIs**: publique `arm64-v8a` (obrigatório na Play Store para apps com código nativo); `armeabi-v7a` é opcional para aparelhos antigos de 32 bits.
- **Páginas de memória de 16 KB**: o Google Play passou a exigir compatibilidade com páginas de 16 KB para apps com bibliotecas nativas (incluindo as do Python); atualize frameworks e NDK para versões compatíveis.
- **Permissões**: câmera, localização, microfone, armazenamento e notificações exigem solicitação em tempo de execução (via `plyer`, `pyjnius`, `android.permissions` do p4a ou API do framework).
- **Armazenamento**: use diretórios privados do app (`App.user_data_dir` no Kivy) em vez de caminhos absolutos.
- **AAB**: a Play Store exige **Android App Bundle (.aab)** para novos apps (APK serve para testes e distribuição fora da loja).

### 14.3 Detectando a plataforma em tempo de execução

```python
import sys, platform

if sys.platform == "android":                 # CPython 3.13+ oficial
    print("Android:", platform.android_ver())
elif sys.platform == "ios":
    print("iOS:", platform.ios_ver())

# Kivy (qualquer versão):
from kivy.utils import platform as plataforma    # 'android', 'ios', 'win', 'linux', 'macosx'
if plataforma == "android":
    from android.permissions import request_permissions, Permission
    request_permissions([Permission.CAMERA])
```

---

## 15. Python no iOS

| Item | Detalhe |
|---|---|
| Suporte oficial | CPython 3.13+ (PEP 730, Tier 3) |
| Ferramentas | **BeeWare/Briefcase**, **kivy-ios**, **Flet**, **Python-Apple-support**, Qt for Python |
| Requisitos | **macOS** com **Xcode** e conta Apple Developer para distribuir |
| Publicação | App Store (IPA via Xcode/Briefcase/`flet build ipa`) |
| Restrições | Sem `fork()`, sem JIT/execução dinâmica de código nativo, módulos nativos como *frameworks* assinados |
| Apps de terceiros | Pythonista, Pyto, Carnets, a-Shell |

---

## 16. Limitações do Python em dispositivos móveis

| Limitação | Explicação |
|---|---|
| **Módulos ausentes** | `tkinter`, `curses`, `readline`, `idlelib`, `turtle` e outros módulos de desktop geralmente não estão disponíveis |
| **Processos** | `multiprocessing` e `fork` são restritos (principalmente no iOS); prefira `threading` e `asyncio` |
| **`subprocess`** | Muito limitado; no Android só executa binários permitidos |
| **`pip` no aparelho** | Não existe em apps empacotados: as dependências são resolvidas **no momento do build** |
| **Bibliotecas nativas** | Pacotes com extensões em C/Rust precisam de *wheels* ou receitas para Android/iOS (nem todos existem) |
| **Tamanho do app** | O interpretador e as bibliotecas aumentam o APK/IPA (tipicamente 10–30 MB ou mais) |
| **Desempenho** | Interpretado: inadequado para jogos 3D pesados ou processamento intensivo sem bibliotecas nativas (NumPy, OpenCV) |
| **Tempo de inicialização** | Mais lento que apps nativos (o interpretador precisa carregar) |
| **Segundo plano** | Serviços em segundo plano são limitados pelas regras do Android e do iOS |
| **GIL** | O *Global Interpreter Lock* limita o paralelismo real (o modo *free-threading* do 3.13+/3.14 ainda é experimental/emergente no mobile) |
| **Acesso a APIs nativas** | Depende de pontes (Pyjnius, Plyer, Rubicon, Chaquopy); nem toda API tem wrapper pronto |
| **Debug** | Logs via `adb logcat` (Android) ou Console/Xcode (iOS) |

---

## 17. Alternativas: web, PWA e backend

| Alternativa | Como funciona | Quando usar |
|---|---|---|
| **PWA com backend Python** | Site responsivo (Flask/Django/FastAPI) instalável como app | Apps de formulários, painéis e cadastros |
| **App nativo/híbrido + API Python** | App em Kotlin/Java/Flutter/React Native consumindo uma API REST | Quando a performance e a UX nativa são importantes |
| **WebView + servidor local** | Bootstrap `webview` do p4a com Flask dentro do APK | Reaproveitar um site Flask como app |
| **Pyodide / PyScript** | Python via WebAssembly no navegador do celular | Demonstrações, ensino e protótipos |
| **Jupyter / Colab** | Notebooks no navegador | Ensino e ciência de dados |
| **Kotlin Multiplatform / Flutter / React Native** | Alternativas que não usam Python no cliente | Produtos comerciais de larga escala |

---

## 18. Ferramentas por sistema operacional

| Objetivo | Windows | Linux | macOS |
|---|---|---|---|
| Python (CPython) | Instalador do python.org | `apt`/`dnf` ou pyenv | Instalador/Homebrew |
| Desenvolver apps **Android** com Kivy/Buildozer | **WSL2** (Ubuntu) | Nativo | Nativo |
| Desenvolver apps **Android** com BeeWare | Nativo (Briefcase baixa JDK/SDK) | Nativo | Nativo |
| Desenvolver apps **Android** com Flet | Nativo (Flutter + Android SDK) | Nativo | Nativo |
| Chaquopy | Android Studio | Android Studio | Android Studio |
| Desenvolver para **iOS** | ❌ Requer Mac | ❌ Requer Mac | ✅ Xcode |
| Emulador Android | Android Studio AVD | Android Studio AVD | Android Studio AVD |
| Depuração por cabo | `adb` (Platform Tools) | `adb` | `adb` |
| IDEs | PyCharm, VS Code | PyCharm, VS Code | PyCharm, VS Code |

---

## 19. Exemplos de código

### 19.1 Kivy (Android/iOS/Desktop)

```python
# main.py
from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.button import Button
from kivy.uix.label import Label


class OlaApp(App):
    def build(self):
        self.contador = 0
        layout = BoxLayout(orientation="vertical", padding=20, spacing=10)
        self.rotulo = Label(text="Olá, Python Mobile!", font_size="24sp")
        botao = Button(text="Clique aqui", size_hint=(1, 0.3))
        botao.bind(on_press=self.clicou)
        layout.add_widget(self.rotulo)
        layout.add_widget(botao)
        return layout

    def clicou(self, instancia):
        self.contador += 1
        self.rotulo.text = f"Cliques: {self.contador}"


OlaApp().run()
```

### 19.2 Kivy com linguagem KV

```python
# main.py
from kivy.app import App

class ContadorApp(App):
    pass

ContadorApp().run()
```

```kv
# contador.kv
BoxLayout:
    orientation: "vertical"
    padding: 20
    Label:
        id: rotulo
        text: "0"
        font_size: "48sp"
    Button:
        text: "Somar"
        on_press: rotulo.text = str(int(rotulo.text) + 1)
```

### 19.3 BeeWare / Toga

```python
import toga


class MeuApp(toga.App):
    def startup(self):
        caixa = toga.Box()
        rotulo = toga.Label("Olá, Toga!")
        caixa.add(rotulo)

        self.main_window = toga.MainWindow(title=self.formal_name)
        self.main_window.content = caixa
        self.main_window.show()


def main():
    return MeuApp("Meu App", "br.com.exemplo.meuapp")
```

### 19.4 Flet

```python
import flet as ft


def main(page: ft.Page):
    page.title = "Contador"
    texto = ft.Text("0", size=40)

    def somar(e):
        texto.value = str(int(texto.value) + 1)
        page.update()

    page.add(texto, ft.ElevatedButton("Somar", on_click=somar))


ft.app(main)   # em versões recentes do Flet, também existe ft.run(main)
```

```bash
pip install flet
flet run main.py          # executa com hot reload
flet build apk            # gera APK
flet build aab            # gera AAB (Google Play)
flet build ipa            # gera IPA (macOS)
```

### 19.5 Acessando recursos nativos com Plyer (Kivy)

```python
from plyer import vibrator, notification, gps

vibrator.vibrate(0.5)  # vibra por 0,5 s (precisa da permissão VIBRATE)

notification.notify(
    title="Python Mobile",
    message="Notificação enviada pelo Python!",
    timeout=5,
)
```

### 19.6 Acessando a API Java com Pyjnius (Android)

```python
from jnius import autoclass

PythonActivity = autoclass("org.kivy.android.PythonActivity")
Toast = autoclass("android.widget.Toast")
activity = PythonActivity.mActivity

# Em Android, chamadas de UI devem rodar na thread de interface
from android.runnable import run_on_ui_thread

@run_on_ui_thread
def mostrar_toast(texto):
    Toast.makeText(activity, texto, Toast.LENGTH_SHORT).show()

mostrar_toast("Olá do Python!")
```

### 19.7 Servidor Flask dentro do app (bootstrap webview)

```ini
# buildozer.spec
requirements = python3,flask
p4a.bootstrap = webview
```

```python
# main.py
from flask import Flask

app = Flask(__name__)

@app.route("/")
def inicio():
    return "<h1>Olá do Flask no Android!</h1>"

if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5000)
```

---

## 20. Qual escolher hoje?

| Objetivo | Recomendação |
|---|---|
| Aprender e fazer protótipos rápidos, com visual próprio e jogos 2D | **Kivy** (+ KivyMD) |
| App com visual nativo de cada plataforma | **BeeWare (Toga + Briefcase)** |
| Código curto, visual moderno, mesma base para web e mobile | **Flet** |
| App Android nativo (Java/Kotlin) que precisa de bibliotecas Python (IA, dados) | **Chaquopy** |
| Já usa Qt no desktop | **Qt for Python (PySide6)** |
| Programar e testar no próprio celular | **Pydroid 3** ou **Termux** (Android); **Pyto/Pythonista** (iOS) |
| Visual novel | **Ren'Py** |
| Hardware/IoT | **MicroPython** ou **CircuitPython** |
| Produto comercial de grande escala | Considere **Kotlin/Flutter/React Native** com **backend Python** |
| Ensino de lógica e apps simples | **Kivy** ou **Flet** (pouca configuração) |

---

## 21. Python Mobile vs. Java Mobile

| Aspecto | Python Mobile | Java Mobile (Android / Java ME) |
|---|---|---|
| Status no Android | Não oficial (ferramentas de terceiros; CPython Tier 3 desde 3.13) | **Oficial** (Java e Kotlin) |
| Status no iOS | Possível (BeeWare, Kivy, Flet) | Não oficial (Gluon, Codename One) |
| Execução | Interpretador embarcado no app | ART (Android), bytecode compilado para DEX |
| Desempenho | Menor (interpretado) | Maior |
| Tamanho do app | Maior (inclui o interpretador) | Menor |
| Acesso às APIs nativas | Por pontes (Pyjnius, Plyer, Rubicon) | Direto |
| Curva de aprendizado | Mais suave | Mais íngreme |
| Bibliotecas de IA/dados | Excelente (NumPy, pandas, TensorFlow via backend/Chaquopy) | Menos abrangente |
| Ferramentas | Buildozer, Briefcase, Flet, Chaquopy | Android Studio, Gradle (padrão do mercado) |
| Tecnologia legada | PyS60, SL4A (abandonados) | Java ME / MIDP (praticamente extinto em smartphones) |
| Melhor uso | Protótipos, ensino, apps simples, integração com IA | Apps nativos de produção e de larga escala |

---

## 22. Glossário

| Termo | Significado |
|---|---|
| **CPython** | Implementação oficial do Python (em C) |
| **PEP** | *Python Enhancement Proposal* (proposta de melhoria do Python) |
| **Tier 3** | Nível de suporte de plataforma do CPython com suporte da comunidade |
| **APK / AAB** | Pacote Android / Android App Bundle (formato exigido pela Play Store) |
| **IPA** | Pacote de aplicativo iOS |
| **ABI** | *Application Binary Interface*: arquitetura do processador (`arm64-v8a`, `x86_64`...) |
| **NDK** | *Native Development Kit* do Android (compilação de código C/C++) |
| **JNI** | *Java Native Interface* (ponte Java ↔ código nativo) |
| **p4a** | python-for-android |
| **Receita (recipe)** | Script do p4a que compila uma biblioteca para Android |
| **Bootstrap** | Modelo base do p4a (`sdl2`, `webview`, `service_only`) |
| **Wheel** | Pacote binário do Python (`.whl`) |
| **Pyjnius / Pyobjus** | Pontes Python ↔ Java / Objective-C |
| **Plyer** | API Python unificada para recursos do dispositivo |
| **Toga / Briefcase** | Widgets nativos / empacotador do BeeWare |
| **Serious Python** | Biblioteca que embute o Python em apps Flutter (usada pelo Flet) |
| **Free-threading** | Modo do Python sem GIL (experimental no 3.13, suportado no 3.14) |
| **WSL2** | *Windows Subsystem for Linux* |

---

## 23. Referências

- Python (downloads e ciclo de vida das versões): https://www.python.org/downloads/ e https://devguide.python.org/versions/
- PEP 730 (iOS): https://peps.python.org/pep-0730/
- PEP 738 (Android): https://peps.python.org/pep-0738/
- Documentação do Python para Android e iOS: https://docs.python.org/3/using/android.html e https://docs.python.org/3/using/ios.html
- Kivy: https://kivy.org
- Buildozer: https://buildozer.readthedocs.io
- python-for-android: https://python-for-android.readthedocs.io
- BeeWare (Toga e Briefcase): https://beeware.org
- Flet: https://flet.dev
- Chaquopy: https://chaquo.com/chaquopy/
- Qt for Python: https://doc.qt.io/qtforpython-6/
- MicroPython: https://micropython.org
- Android Developers – Níveis de API: https://developer.android.com/guide/topics/manifest/uses-sdk-element#api-level-table

---

> **Dica:** o ecossistema de Python Mobile muda rápido. Antes de iniciar um projeto, confira, na documentação do framework escolhido, **qual versão do Python** e **qual `minSdk`/`targetSdk`** são suportados, e teste em **aparelho real** e em emuladores com diferentes níveis de API.
