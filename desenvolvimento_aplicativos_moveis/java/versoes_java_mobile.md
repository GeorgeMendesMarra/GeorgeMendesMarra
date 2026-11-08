# Versões do Java Mobile

> Guia completo das versões, configurações, perfis e APIs do **Java para dispositivos móveis**: da família **Java ME (J2ME)** até a linguagem Java no **Google Android**.

---

## Sumário

1. [Visão geral: o que é "Java Mobile"?](#1-visão-geral-o-que-é-java-mobile)
2. [Linha do tempo](#2-linha-do-tempo)
3. [Arquitetura do Java ME](#3-arquitetura-do-java-me)
4. [Configurações: CLDC e CDC](#4-configurações-cldc-e-cdc)
5. [Perfis: MIDP, Foundation, Personal e MEEP](#5-perfis-midp-foundation-personal-e-meep)
6. [Comparativo de versões do Java ME](#6-comparativo-de-versões-do-java-me)
7. [Pacotes opcionais (JSRs)](#7-pacotes-opcionais-jsrs)
8. [Especificações guarda-chuva: JTWI e MSA](#8-especificações-guarda-chuva-jtwi-e-msa)
9. [Variantes de fabricantes e outras plataformas Java](#9-variantes-de-fabricantes-e-outras-plataformas-java)
10. [Java no Google Android](#10-java-no-google-android)
11. [Versões do Android e suporte a Java](#11-versões-do-android-e-suporte-a-java)
12. [Java ME vs. Android: diferenças](#12-java-me-vs-android-diferenças)
13. [Rodando aplicações Java ME hoje (emuladores)](#13-rodando-aplicações-java-me-hoje-emuladores)
14. [Java no iOS e multiplataforma](#14-java-no-ios-e-multiplataforma)
15. [Ferramentas de desenvolvimento](#15-ferramentas-de-desenvolvimento)
16. [Exemplos de código](#16-exemplos-de-código)
17. [Qual escolher hoje?](#17-qual-escolher-hoje)
18. [Glossário](#18-glossário)
19. [Referências](#19-referências)

---

## 1. Visão geral: o que é "Java Mobile"?

"Java Mobile" não é um produto único. É um nome popular para **três mundos diferentes**:

| Mundo | O que é | Linguagem | Máquina virtual |
|---|---|---|---|
| **Java ME** (antigo J2ME) | Plataforma oficial da Sun/Oracle para dispositivos com poucos recursos (celulares antigos, feature phones, embarcados) | Java (subconjunto) | KVM / CLDC HotSpot / JVMs de fabricantes |
| **Android** | Sistema operacional do Google; apps escritos em Java ou Kotlin | Java (várias versões da linguagem) + Kotlin | Dalvik (até 4.4) e ART (a partir do 5.0) |
| **Outras soluções** | JavaFX Mobile, RoboVM, Gluon, Codename One, BlackBerry Java etc. | Java | Variadas |

**Resumo rápido:**

- Para **celulares antigos** (Nokia S40/S60, Sony Ericsson, Motorola etc.) → **Java ME (CLDC + MIDP)**.
- Para **smartphones modernos** → **Android** (Java/Kotlin).
- Para **dispositivos embarcados/IoT** → **Java ME Embedded 8** (ou Java SE Embedded).

---

## 2. Linha do tempo

| Ano | Marco |
|---|---|
| 1995 | Lançamento do Java (Sun Microsystems) |
| 1999 | Sun anuncia o **J2ME** (Java 2 Micro Edition) na JavaOne |
| 2000 | **CLDC 1.0** (JSR 30) e **MIDP 1.0** (JSR 37); primeiros celulares com Java (NTT DoCoMo i-appli no Japão, 2001 na prática) |
| 2002 | **MIDP 2.0** (JSR 118) – o grande salto do Java ME |
| 2003 | **CLDC 1.1** (JSR 139) – ponto flutuante |
| 2003–2004 | **CDC 1.1** (JSR 218) e perfis Foundation/Personal |
| 2003 | **JTWI** (JSR 185) – padroniza o mínimo exigido nos aparelhos |
| 2005 | Google adquire a Android Inc. (a aquisição ocorreu em 2005) |
| 2006 | Sun renomeia J2ME para **Java ME** (versões 5.0 do "Java Platform") |
| 2007 | **Android** anunciado; **MSA** (JSR 248) |
| 2008 | **Android 1.0** (API 1), primeiro aparelho HTC Dream/G1 |
| 2009 | **MIDP 3.0** (JSR 271) finalizado; JavaFX Mobile 1.x |
| 2010 | Oracle conclui compra da Sun; Java ME perde espaço para smartphones |
| 2013 | Android Studio anunciado (substitui Eclipse + ADT) |
| 2014 | **Java ME 8** (CLDC 8 + MEEP 8) com linguagem Java 8 parcial; Android 5.0 introduz o **ART** como padrão |
| 2014–2015 | Android passa a suportar Java 7 (4.4 KitKat) |
| 2016 | Android 7.0 migra para **OpenJDK** nas bibliotecas; suporte a recursos do Java 8 |
| 2017 | Google anuncia **Kotlin** como linguagem oficial do Android |
| 2019 | Android "Kotlin-first" |
| 2021–2023 | Suporte a Java 11 e depois Java 17 (linguagem e bibliotecas, em etapas) |
| 2024–2025 | Android 14/15 com atualizações de bibliotecas OpenJDK 17 |

---

## 3. Arquitetura do Java ME

O Java ME é construído em **camadas**:

```
┌───────────────────────────────────────────┐
│   Aplicações (MIDlets, Xlets, IMlets...)  │
├───────────────────────────────────────────┤
│   Pacotes opcionais (JSRs: Bluetooth,     │
│   Localização, 3D, Multimídia, SMS...)    │
├───────────────────────────────────────────┤
│   Perfil (MIDP, Foundation, Personal,     │
│   MEEP)                                   │
├───────────────────────────────────────────┤
│   Configuração (CLDC ou CDC)              │
├───────────────────────────────────────────┤
│   Máquina virtual (KVM, CLDC HI, CDC VM)  │
├───────────────────────────────────────────┤
│   Sistema operacional / hardware          │
└───────────────────────────────────────────┘
```

Conceitos:

- **Configuração** = a JVM + bibliotecas básicas (o "mínimo comum" para uma família de dispositivos).
- **Perfil** = APIs adicionais para um tipo de dispositivo (interface gráfica, rede, armazenamento...).
- **Pacotes opcionais** = APIs extras que o fabricante pode ou não incluir.

---

## 4. Configurações: CLDC e CDC

### 4.1 CLDC – Connected Limited Device Configuration

Para dispositivos **muito limitados**: processador de 16/32 bits, **128 KB a poucos MB** de memória, conexão intermitente e baixa velocidade.

| Característica | CLDC 1.0 | CLDC 1.1 | CLDC 8 |
|---|---|---|---|
| JSR | 30 | 139 | 360 (CLDC 8) |
| Ano | 2000 | 2003 | 2014 |
| Memória mínima | 160 KB (128 KB para a VM+bibliotecas e 32 KB para alocação) | 192 KB | maior (dispositivos 32 bits, ordem de centenas de KB a MB) |
| Ponto flutuante (`float`/`double`) | ❌ Não | ✅ Sim | ✅ Sim |
| `Float`, `Double` classes | ❌ | ✅ | ✅ |
| Referências fracas (`WeakReference`) | ❌ | ✅ | ✅ |
| `Thread` / `Runnable` | ✅ | ✅ (com nomes de threads) | ✅ |
| Verificação de classes | Pré-verificação (preverify) | Pré-verificação | Verificação com StackMap (como Java SE) |
| JNI | ❌ | ❌ | ❌ (usa mecanismos próprios) |
| Reflexão completa | ❌ (só `Class.forName`) | ❌ | Parcial |
| Finalizers | ❌ | ❌ | ❌ |
| Linguagem Java | ~Java 1.3 | ~Java 1.3 | **Subconjunto do Java 8** |
| Pacote `javax.microedition.io` (Generic Connection Framework) | ✅ | ✅ | ✅ |

Pontos importantes:

- Não há `java.util.Vector` e `Hashtable`? **Há**: `Vector`, `Stack`, `Hashtable`, `Enumeration` existem; **não existem** `ArrayList`, `HashMap`, `List`, `Map` (collections do Java 2) nas versões 1.x.
- Não existem **genéricos**, **anotações**, **enum**, **autoboxing** nas versões CLDC 1.x (código era Java 1.3).
- CLDC 8 trouxe `java.util.concurrent` parcial, genéricos e boa parte da linguagem Java 8.

### 4.2 CDC – Connected Device Configuration

Para dispositivos **mais capazes**: 32 bits, **2 MB ou mais** de memória, conexão de rede melhor (set-top boxes, PDAs, telemática automotiva, Blu-ray).

| Característica | CDC 1.0 | CDC 1.1 | CDC 1.1.2 |
|---|---|---|---|
| JSR | 36 | 218 | 218 (manutenção) |
| Base | J2SE 1.3 | J2SE 1.4 | J2SE 1.4 |
| Máquina virtual | CVM (C Virtual Machine) | CVM | CVM |
| Reflexão, JNI, finalizers | ✅ | ✅ | ✅ |
| Memória | ~2 MB ROM / 256 KB RAM+ | idem | idem |
| Perfil típico | Foundation Profile | Foundation + Personal Profile | idem |

---

## 5. Perfis: MIDP, Foundation, Personal e MEEP

### 5.1 MIDP – Mobile Information Device Profile (sobre CLDC)

O perfil clássico dos **celulares**. Aplicações chamam-se **MIDlets**.

| Característica | MIDP 1.0 | MIDP 2.0 | MIDP 2.1 | MIDP 3.0 |
|---|---|---|---|---|
| JSR | 37 | 118 | 118 (manutenção) | 271 |
| Ano | 2000 | 2002 | ~2006 | 2009 |
| Requer | CLDC 1.0 | CLDC 1.0 ou 1.1 | CLDC 1.0/1.1 | CLDC 1.1 |
| Memória não volátil p/ app | 8 KB | 256 KB | 256 KB | 1 MB+ (tipicamente maior) |
| Memória heap | 128 KB | 256 KB | 256 KB | 1 MB |
| Tamanho do JAR | ≥ 50 KB | ≥ 64 KB | ≥ 64 KB | maior |
| Interface gráfica | `Form`, `List`, `TextBox`, `Alert`, `Canvas` | + `CustomItem`, `Spacer`, `StringItem` estendido, layouts | idem | Nova UI, múltiplas telas, **MIDlets rodando simultaneamente** |
| **Game API** (`GameCanvas`, `Sprite`, `TiledLayer`, `LayerManager`) | ❌ | ✅ | ✅ | ✅ |
| Mídia (som) | ❌ (apenas tons via `Manager.playTone` em extensões) | ✅ Audio Building Block (Media API) | ✅ | ✅ |
| Rede | HTTP | **HTTP, HTTPS, sockets, datagramas, SSL/TLS** (opcional), `ServerSocket`, serial | idem | idem + melhorias |
| **Push Registry** (iniciar MIDlet por evento de rede/SMS) | ❌ | ✅ | ✅ | ✅ |
| **Assinatura de MIDlets / domínios de segurança** | ❌ (sandbox simples) | ✅ (X.509, domínios: fabricante, operadora, terceiros) | ✅ | ✅ |
| **RMS** (Record Management System) | ✅ | ✅ | ✅ | ✅ |
| Provisionamento OTA | ✅ (recomendado) | ✅ (especificado) | ✅ | ✅ |
| Compatibilidade com versões anteriores | — | Roda MIDP 1.0 | ✅ | **Roda MIDP 2.0** |
| Recursos da 3.0 | — | — | — | Auto-invoke, **Inter-MIDlet Communication (IMC)**, bibliotecas compartilhadas (**LIBlets**), **Localização/internacionalização**, mais de uma tela/exibição, **Secure Connections**, **Resolução/ações de tela maiores** |

> **Atenção:** ainda que MIDP 3.0 exista como especificação, **poucos aparelhos** o implementaram. O mercado real de feature phones ficou na faixa **MIDP 2.0 / 2.1 + CLDC 1.1**.

### 5.2 Foundation Profile (FP) – sobre CDC

- **JSR 46** (FP 1.0), **JSR 219** (FP 1.1).
- Fornece o núcleo de APIs de rede e I/O de J2SE 1.4 reduzidas.
- **Sem interface gráfica** (é a base para outros perfis).

### 5.3 Personal Basis Profile (PBP) e Personal Profile (PP)

- **PBP**: **JSR 129** (v1.0) e **JSR 217** (v1.1). Interface gráfica leve (baseada em AWT leve) e modelo de aplicações **Xlet**.
- **PP**: **JSR 62** (v1.0) e **JSR 216** (v1.1). Equivalente ao antigo **PersonalJava**; AWT completo (com **applets**).
- Usados em set-top boxes (OCAP/MHP), Blu-ray (BD-J), PDAs.

### 5.4 Java ME Embedded Profile (MEEP) 8

- Lançado com o **Java ME 8** (2014).
- **Sem interface gráfica obrigatória** (focado em IoT e dispositivos embarcados headless).
- Aplicações chamam-se **IMlets** (Information Module).
- Suporte a **Device Access API** (GPIO, I2C, SPI, UART, ADC, DAC, PWM, watchdog, MMIO), **Generic Connection Framework** atualizado, **LCDUI** opcional, **Java ME 8 Security** (TLS), **Logging**, **Deploy**.

| Versão | Observação |
|---|---|
| MEEP 8.0 | 2014 |
| MEEP 8.1 / 8.2 | atualizações do SDK 8.x (2015) |
| MEEP 8.3 | última versão do SDK Oracle Java ME 8.3 (por volta de 2016–2017) |

---

## 6. Comparativo de versões do Java ME

| Pacote | Configuração | Perfil | Dispositivo típico | Aplicação | Linguagem Java |
|---|---|---|---|---|---|
| **J2ME CLDC 1.0 / MIDP 1.0** | CLDC 1.0 | MIDP 1.0 | Celulares 2001–2003 | MIDlet | ~1.3 |
| **J2ME CLDC 1.0 / MIDP 2.0** | CLDC 1.0 | MIDP 2.0 | Celulares 2003–2005 | MIDlet | ~1.3 |
| **J2ME CLDC 1.1 / MIDP 2.0** | CLDC 1.1 | MIDP 2.0 | Celulares 2004–2010 (**o mais difundido**) | MIDlet | ~1.3 |
| **CLDC 1.1 / MIDP 2.1** | CLDC 1.1 | MIDP 2.1 | Celulares avançados | MIDlet | ~1.3 |
| **CLDC 1.1 / MIDP 3.0** | CLDC 1.1 | MIDP 3.0 | Poucos aparelhos | MIDlet | ~1.3 |
| **CDC 1.1.2 / FP 1.1 / PP 1.1** | CDC | FP + PP | PDAs, set-top boxes, automotivo | Xlet/Applet | 1.4 |
| **CDC 1.1.2 / PBP 1.1** | CDC | PBP | Blu-ray (BD-J), TV digital | Xlet | 1.4 |
| **Java ME 8 (CLDC 8 + MEEP 8)** | CLDC 8 | MEEP 8 | IoT, gateways, embarcados | IMlet | Java 8 (subconjunto) |
| **Java ME 8 (CLDC 8 + MIDP 8)** | CLDC 8 | MIDP 8 (compatível) | Dispositivos com UI | MIDlet | Java 8 (subconjunto) |

---

## 7. Pacotes opcionais (JSRs)

Pacotes que acompanham o MIDP em muitos aparelhos:

| JSR | Nome | Descrição |
|---|---|---|
| **JSR 75** | PDA Optional Packages | **FileConnection** (acesso ao sistema de arquivos) e **PIM** (agenda, contatos, tarefas) |
| **JSR 82** | Bluetooth API | `javax.bluetooth` e OBEX (`javax.obex`) |
| **JSR 120** | Wireless Messaging API (WMA 1.0) | SMS e Cell Broadcast |
| **JSR 205** | WMA 2.0 | Adiciona **MMS** |
| **JSR 135** | Mobile Media API (MMAPI) | Áudio, vídeo, captura (câmera), rádio |
| **JSR 172** | J2ME Web Services | Cliente de **XML e SOAP/JAX-RPC** |
| **JSR 177** | SATSA | Comunicação com **smart cards / SIM** (APDU, criptografia) |
| **JSR 179** | Location API | **GPS** e localização |
| **JSR 184** | Mobile 3D Graphics (M3G) | Gráficos **3D** (`javax.microedition.m3g`) |
| **JSR 226** | Scalable 2D Vector Graphics | **SVG** |
| **JSR 239** | Java Bindings for OpenGL ES | **OpenGL ES** (2D/3D acelerado) |
| **JSR 234** | Advanced Multimedia Supplements | Câmera avançada, rádio FM, efeitos |
| **JSR 211** | Content Handler API | Invocar outras aplicações/tipos MIME |
| **JSR 180** | SIP API | VoIP |
| **JSR 229** | Payment API | Pagamentos |
| **JSR 238** | Mobile Internationalization | Formatação/localização |
| **JSR 256** | Mobile Sensor API | Acelerômetro, sensores |
| **JSR 257** | Contactless Communication API | **NFC**, RFID |
| **JSR 272** | Mobile Broadcast Service API | TV móvel (DVB-H) |
| **JSR 290** | Java Language Binding for UI (JavaFX Mobile) | UI para fluxos SVG/interativos |
| **JSR 232** | Mobile Operational Management | Gerenciamento remoto |
| **JSR 253** | Mobile Telephony API | Controle de chamadas |
| **JSR 118/139** | (base) | MIDP e CLDC |
| **JSR 280** | XML API for J2ME | Parser XML (SAX/DOM) |

Observações:

- Os pacotes variam **por aparelho**. Cada fabricante implementa o que quiser (por isso o conhecido problema de **fragmentação**).
- É possível consultar o suporte em tempo de execução com `System.getProperty("microedition.profiles")`, `"microedition.configuration"`, `"microedition.platform"`, `"microedition.locale"`, `"microedition.encoding"` etc.

---

## 8. Especificações guarda-chuva: JTWI e MSA

Como o suporte a pacotes opcionais era confuso, foram criadas especificações que **obrigam** um conjunto mínimo.

### 8.1 JTWI – Java Technology for the Wireless Industry (JSR 185)

- Exige **CLDC 1.0 (ou 1.1) + MIDP 2.0 + WMA 1.1 + MMAPI 1.1**.
- Requisitos mínimos de aparelho: **JAR de pelo menos 64 KB**, **heap de 256 KB**, **tela de pelo menos 125×125 pixels**, **área de armazenamento de 30 KB**, etc.
- Garante que um aplicativo escrito para "JTWI" rode em qualquer aparelho que declare conformidade.

### 8.2 MSA – Mobile Service Architecture

| Versão | JSR | Observação |
|---|---|---|
| **MSA 1.0** | JSR 248 | Inclui CLDC, MIDP 2.0/2.1, WMA 2.0, MMAPI, M3G, Bluetooth, Location, SATSA, SIP, FileConnection/PIM, Web Services, Content Handler etc. (cerca de 20 JSRs) |
| **MSA Subset** | JSR 248 | Subconjunto para aparelhos de médio porte |
| **MSA 2.0** | JSR 249 | Atualização: MIDP 3.0, mais APIs, melhor segurança |

---

## 9. Variantes de fabricantes e outras plataformas Java

| Plataforma | Descrição |
|---|---|
| **Nokia Series 40 / Series 60 (Symbian)** | Java ME com extensões **Nokia UI API**, S60 com MIDP 2.0/2.1 e pacotes adicionais; versões Java Runtime 2.x |
| **Sony Ericsson (JP-x)** | Plataformas "JP-3", "JP-8" etc., que agregavam MIDP 2.0 + JSRs específicos (M3G, Bluetooth...) |
| **Motorola** | Extensões proprietárias (**iDEN**, Motorola APIs) |
| **Samsung** | **SGH**, extensões Samsung; plataforma Bada (2010–2013) tinha C++ nativa |
| **Siemens** | Extensões Siemens (para MIDP 1.0 e 2.0) |
| **NTT DoCoMo (DoJa)** | Perfil **i-appli** com CLDC 1.0 + DoJa 1.0 a 5.1 (Japão) |
| **KDDI/au (EZ-Java / BREW)** | Java + BREW |
| **Sprint / Verizon** | Java ME com extensões das operadoras |
| **BlackBerry Java (RIM)** | Java próprio para BlackBerry OS 4–7 (`net.rim.device.api`), compatível com MIDP 2.0; substituído por BlackBerry 10 (C++/Qt, Android runtime) |
| **Windows Mobile** | JVMs de terceiros (IBM J9, Esmertec **Jbed**) |
| **Palm OS** | JVM (IBM J9) |
| **Java Card** | Java para **smart cards/SIM** (família separada: Java Card 2.x, 3.x) |
| **JavaFX Mobile** | JavaFX Script (1.x), 2009–2010; descontinuado |
| **Java ME Embedded 8** | IoT (Raspberry Pi, microcontroladores ARM Cortex-M) |
| **PhoneME** | Implementação **open source** de CLDC/MIDP e CDC, base de várias VMs |

---

## 10. Java no Google Android

### 10.1 Como o Java funciona no Android

O Android **não executa bytecode Java** (`.class`/`.jar`) diretamente nem é uma JVM da Oracle. O fluxo é:

```
Código-fonte Java / Kotlin  ──►  javac / kotlinc  ──►  .class (bytecode Java)
        ──►  D8 / R8 (desugar + otimização)  ──►  .dex (Dalvik Executable)
        ──►  APK / AAB  ──►  execução no ART (Android Runtime)
```

- **Dalvik** (Android 1.0 a 4.4): VM com JIT, registrador.
- **ART** (opcional no 4.4; padrão a partir do 5.0): AOT, depois JIT+AOT com perfis (a partir do 7.0).
- **Bibliotecas do núcleo**: originalmente **Apache Harmony**; a partir do Android 7.0 (API 24), **OpenJDK** (`libcore`).
- **APIs próprias**: `android.*` (Activity, View, Intent, etc.) e **AndroidX** (Jetpack).

### 10.2 Versões da linguagem Java suportadas

| Versão da linguagem | Suporte no Android | Observações |
|---|---|---|
| **Java 5/6** | Desde o Android 1.0 | Genéricos, anotações, `for-each`, `enum`, autoboxing, varargs; `@Override` em interfaces (Java 6) |
| **Java 7** | Android 4.4 (API 19) e superior (via ferramentas) | Diamond `<>`, `switch` com `String`, multi-catch, literais binários e sublinhados; `try-with-resources` requer minSdk 19 (ou desugar) |
| **Java 8** | Android 7.0 (API 24) nativamente; **desugaring** via D8 em APIs anteriores | **Lambdas**, referências de método, métodos `default` e estáticos em interfaces, **Stream API** (API 24+), `java.time` (API 26+; com *core library desugaring* a partir de API mais baixa), `Optional`, anotações repetíveis |
| **Java 9–10** | Parcial (algumas APIs adicionadas) | Métodos privados em interfaces, `var` (Java 10) via desugar |
| **Java 11** | A partir do Android Gradle Plugin 7.0 e Android 12/13 | `var` em lambdas, APIs adicionadas (parcial); JDK 11 necessário para rodar o AGP 7.x |
| **Java 17** | AGP 8.0+ exige **JDK 17 para executar a build**; linguagem Java 17 suportada com desugar (`sealed`, `records`, text blocks, `switch` expressions, pattern matching `instanceof`) conforme a versão do AGP e a configuração de `compileOptions` | Bibliotecas OpenJDK 17 em Android 14/15 (parcial) |
| **Java 21** | Linguagem em evolução; requer AGP/Android Studio recentes | Consulte a documentação oficial para o estado atual |

> **Regra prática:** o código-fonte pode usar recursos da linguagem mais novos do que a API do dispositivo, graças ao **desugaring** (D8). Mas **bibliotecas** (por exemplo `java.util.stream`, `java.time`) dependem do nível de API do aparelho, a menos que se use o **core library desugaring**.

### 10.3 Configuração no Gradle

```groovy
// build.gradle (módulo app) – Groovy DSL
android {
    namespace 'br.com.exemplo.app'
    compileSdk 35

    defaultConfig {
        applicationId "br.com.exemplo.app"
        minSdk 24
        targetSdk 35
        versionCode 1
        versionName "1.0"
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
        coreLibraryDesugaringEnabled true   // permite java.time etc. em APIs antigas
    }
}

dependencies {
    coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:2.1.4'
}
```

```kotlin
// build.gradle.kts – Kotlin DSL
android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
}
```

### 10.4 Marcos de ferramentas de build

| Ferramenta | Observação |
|---|---|
| **Eclipse + ADT** | Padrão de 2008 a 2014; descontinuado |
| **Ant** | Build inicial |
| **Android Studio (IntelliJ)** | Oficial desde 2013 (versão 1.0 em dezembro de 2014) |
| **Gradle (Android Gradle Plugin – AGP)** | Padrão atual |
| **Jack & Jill** | Toolchain experimental (Android N), descontinuada |
| **D8 / R8** | Substituíram DX e ProGuard (D8 padrão no AGP 3.1+; R8 padrão no AGP 3.4+) |

---

## 11. Versões do Android e suporte a Java

| Versão | Codinome | API | Ano | Runtime | Notas para Java |
|---|---|---|---|---|---|
| 1.0 | — | 1 | 2008 | Dalvik | Primeira versão pública; Harmony |
| 1.1 | — | 2 | 2009 | Dalvik | |
| 1.5 | Cupcake | 3 | 2009 | Dalvik | |
| 1.6 | Donut | 4 | 2009 | Dalvik | |
| 2.0–2.1 | Eclair | 5–7 | 2009 | Dalvik | |
| 2.2 | Froyo | 8 | 2010 | Dalvik + **JIT** | Dalvik ganha JIT |
| 2.3–2.3.7 | Gingerbread | 9–10 | 2010 | Dalvik | |
| 3.0–3.2 | Honeycomb | 11–13 | 2011 | Dalvik | Tablets, Fragments |
| 4.0–4.0.4 | Ice Cream Sandwich | 14–15 | 2011 | Dalvik | |
| 4.1–4.3 | Jelly Bean | 16–18 | 2012 | Dalvik | |
| 4.4 | KitKat | 19–20 | 2013 | Dalvik (ART opcional) | **Java 7** |
| 5.0–5.1 | Lollipop | 21–22 | 2014 | **ART** (padrão) | Multidex nativo |
| 6.0 | Marshmallow | 23 | 2015 | ART | Permissões em tempo de execução |
| 7.0–7.1 | Nougat | 24–25 | 2016 | ART (JIT + AOT) | **OpenJDK**; **Java 8** (lambdas, streams) |
| 8.0–8.1 | Oreo | 26–27 | 2017 | ART | `java.time` nativo, Kotlin oficial |
| 9 | Pie | 28 | 2018 | ART | |
| 10 | — | 29 | 2019 | ART | |
| 11 | — | 30 | 2020 | ART | |
| 12 | — | 31 | 2021 | ART | Atualizações de libcore via Mainline |
| 12L | — | 32 | 2022 | ART | Telas grandes/dobráveis |
| 13 | — | 33 | 2022 | ART | Bibliotecas **OpenJDK 11** (parcial) |
| 14 | — | 34 | 2023 | ART | Bibliotecas **OpenJDK 17** (parcial) |
| 15 | — | 35 | 2024 | ART | Continuação das atualizações OpenJDK 17 |
| 16 | — | 36 | 2025 | ART | Novas APIs; verifique a documentação oficial |

> Versões mais recentes que as listadas podem existir; consulte a página oficial de níveis de API (`developer.android.com`) para o estado atual.

### Como saber a versão do Java do aparelho em tempo de execução

```java
String javaVm      = System.getProperty("java.vm.name");     // "Dalvik" ou "Android Runtime"
String javaVmVer   = System.getProperty("java.vm.version");  // 1.6.0 (Dalvik) / 2.x (ART)
int    apiLevel    = android.os.Build.VERSION.SDK_INT;       // ex.: 34
String release     = android.os.Build.VERSION.RELEASE;       // ex.: "14"
```

---

## 12. Java ME vs. Android: diferenças

| Aspecto | Java ME (MIDP) | Android |
|---|---|---|
| Fabricante | Sun/Oracle (especificação por JCP) | Google (Open Handset Alliance) |
| Distribuição | JAD/JAR (OTA, cartão, cabo) | APK/AAB (Google Play, sideload) |
| Máquina virtual | KVM / CLDC HI / JVM do fabricante | Dalvik / ART |
| Formato executável | `.class` pré-verificado em `.jar` | `.dex` em `.apk` |
| Linguagem | Java 1.3 (CLDC 1.x) a Java 8 parcial (CLDC 8) | Java (até 17+ com desugaring) e Kotlin |
| Tipo de app | MIDlet | Activity, Service, BroadcastReceiver, ContentProvider |
| UI | LCDUI (`Form`, `Canvas`) | Views, Jetpack Compose |
| Armazenamento | RMS (Record Store) + FileConnection | SQLite, Room, arquivos, DataStore, SharedPreferences |
| Rede | Generic Connection Framework | `HttpURLConnection`, OkHttp, Retrofit |
| Segurança | Domínios de segurança, MIDlets assinados | Permissões, sandbox por UID, Play Protect |
| Hardware | Via JSRs opcionais | APIs de sensores, câmera, NFC, etc. nativas |
| Multitarefa | 1 MIDlet por vez (MIDP 1/2) | Multitarefa completa |
| Portabilidade | "Escreva uma vez, teste em todos" (fragmentação) | Fragmentação de versões de API |
| Mercado atual | Praticamente extinto em smartphones; vivo em embarcados | Dominante |
| Compatibilidade entre si | ❌ Não roda MIDlets diretamente | Só via emuladores (J2ME Loader) |

---

## 13. Rodando aplicações Java ME hoje (emuladores)

| Ferramenta | Plataforma | Observação |
|---|---|---|
| **J2ME Loader** | Android | Emulador de MIDlets (`.jar`/`.jad`) em aparelhos Android; open source |
| **FreeJ2ME** | Windows, Linux, macOS | Emulador em Java; **FreeJ2ME-Plus** com melhorias |
| **KEmulator nnmod** | Windows | Emulador com suporte a M3G, Nokia UI e outros |
| **MicroEmulator** | Windows, Linux, macOS | Emulador em Java puro, também como applet |
| **Sun Java Wireless Toolkit (WTK 2.5.2)** | Windows, Linux (antigo) | SDK clássico para CLDC/MIDP |
| **Oracle Java ME SDK 3.x / 8.x** | Windows, Linux | SDK mais recente, com NetBeans e Eclipse |
| **Nokia SDKs** (S40, S60) | Windows | Emuladores específicos |

---

## 14. Java no iOS e multiplataforma

Não há runtime oficial de Java no iOS. As alternativas:

| Solução | Descrição |
|---|---|
| **RoboVM** | Compilava bytecode Java para código nativo iOS (descontinuado em 2015 após aquisição pela Xamarin/Microsoft; continuado como **MobiVM**, open source) |
| **Gluon Mobile / GluonFX** | JavaFX + **GraalVM Native Image** para Android e iOS |
| **Codename One** | Java para iOS, Android, Windows e web (compila para nativo via servidores de build) |
| **J2ObjC** (Google) | Converte código Java (lógica de negócio) para Objective-C |
| **Kotlin Multiplatform** | Compartilha código Kotlin (alternativa moderna) |
| **Flutter / React Native** | Não usam Java, mas concorrem diretamente |

---

## 15. Ferramentas de desenvolvimento

### Java ME

- **JDK** (versão compatível com o SDK; JDK 6/7 para WTK clássico).
- **Sun Java Wireless Toolkit 2.5.2** ou **Oracle Java ME SDK 8.x**.
- **NetBeans** (com Mobility Pack – até a versão 6.9) ou **Eclipse** (com EclipseME / MTJ).
- **ProGuard** para ofuscar e reduzir `.jar`.
- **Antenna** (tarefas Ant para J2ME).
- **Preverifier** (`preverify`), necessário em CLDC 1.x.

### Android

- **Android Studio** (Windows, Linux, macOS).
- **Android SDK** (Platform Tools, Build Tools, Emulator).
- **JDK** (Android Studio inclui o JDK embarcado – JBR).
- **Gradle** e **Android Gradle Plugin**.
- **ADB** (Android Debug Bridge).
- **Firebase**, **Jetpack**, **Room**, **Retrofit**, **Glide** etc.

---

## 16. Exemplos de código

### 16.1 MIDlet clássico (Java ME – CLDC 1.1 / MIDP 2.0)

```java
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;

public class OlaMundo extends MIDlet implements CommandListener {

    private Display display;
    private Form form;
    private Command sair = new Command("Sair", Command.EXIT, 1);

    public OlaMundo() {
        display = Display.getDisplay(this);
        form = new Form("Java ME");
        form.append("Olá, Java Mobile!\n");
        form.append("Configuração: " + System.getProperty("microedition.configuration") + "\n");
        form.append("Perfis: " + System.getProperty("microedition.profiles") + "\n");
        form.addCommand(sair);
        form.setCommandListener(this);
    }

    protected void startApp() {
        display.setCurrent(form);
    }

    protected void pauseApp() { }

    protected void destroyApp(boolean incondicional) { }

    public void commandAction(Command c, Displayable d) {
        if (c == sair) {
            destroyApp(true);
            notifyDestroyed();
        }
    }
}
```

**Arquivo JAD (descritor):**

```
MIDlet-Name: OlaMundo
MIDlet-Version: 1.0.0
MIDlet-Vendor: Exemplo
MIDlet-1: OlaMundo, , OlaMundo
MicroEdition-Profile: MIDP-2.0
MicroEdition-Configuration: CLDC-1.1
MIDlet-Jar-URL: OlaMundo.jar
MIDlet-Jar-Size: 4096
```

### 16.2 Detectando recursos do aparelho (Java ME)

```java
String conf     = System.getProperty("microedition.configuration"); // CLDC-1.1
String perfis   = System.getProperty("microedition.profiles");      // MIDP-2.0
String plataforma = System.getProperty("microedition.platform");    // ex.: Nokia6300
String locale   = System.getProperty("microedition.locale");        // pt-BR
String bluetooth = System.getProperty("bluetooth.api.version");     // 1.1 (se houver JSR 82)
String m3g      = System.getProperty("microedition.m3g.version");   // 1.0 / 1.1
String wma      = System.getProperty("wireless.messaging.sms.smsc");// se houver JSR 120
String gps      = System.getProperty("microedition.location.version"); // 1.0 / 1.1
```

### 16.3 Activity Android (Java)

```java
package br.com.exemplo.app;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setPadding(32, 32, 32, 32);
        tv.setText("Olá, Android!\n"
                 + "Android " + Build.VERSION.RELEASE
                 + " (API " + Build.VERSION.SDK_INT + ")\n"
                 + "VM: " + System.getProperty("java.vm.name"));
        setContentView(tv);
    }
}
```

### 16.4 Recursos do Java 8 no Android (lambda e streams)

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

List<String> nomes = Arrays.asList("Ana", "Bruno", "Carla", "Davi");

List<String> filtrados = nomes.stream()
        .filter(n -> n.length() > 3)
        .map(String::toUpperCase)
        .collect(Collectors.toList());
// Streams exigem API 24+ (ou core library desugaring para algumas APIs)
```

### 16.5 Usando recursos conforme a versão do Android

```java
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    // API 26+: canais de notificação, java.time nativo
    java.time.LocalDate hoje = java.time.LocalDate.now();
} else {
    // fallback para APIs antigas
    java.util.Calendar cal = java.util.Calendar.getInstance();
}
```

---

## 17. Qual escolher hoje?

| Objetivo | Recomendação |
|---|---|
| Criar um app para o público geral | **Android** (Java ou Kotlin) |
| Manter ou estudar aplicações em celulares antigos | **Java ME – CLDC 1.1 + MIDP 2.0** |
| Rodar jogos J2ME no celular moderno | **J2ME Loader** (Android) |
| Rodar jogos J2ME no computador | **FreeJ2ME** ou **KEmulator** |
| IoT/embarcados em Java | **Java ME 8 (MEEP 8)** ou **Java SE Embedded / GraalVM** |
| Compartilhar código entre Android e iOS | **Kotlin Multiplatform**, **Gluon**, **Codename One** |
| Ensino de conceitos de computação móvel | Começar com **MIDP 2.0** (conceitos simples) e evoluir para **Android** |

### Compatibilidade de versões (resumo)

- **Todo código CLDC 1.0 roda em CLDC 1.1**, mas **não o inverso** (se usar `float`).
- **Todo MIDlet MIDP 1.0 roda em MIDP 2.0**; **MIDP 2.0 roda em MIDP 3.0**.
- **Java ME e Android não são binariamente compatíveis**: é preciso reescrever a camada de UI e de acesso a recursos; a lógica de negócio pura (sem LCDUI) pode ser reaproveitada com ajustes.

---

## 18. Glossário

| Termo | Significado |
|---|---|
| **J2ME / Java ME** | Java 2 Micro Edition / Java Micro Edition |
| **CLDC** | Connected Limited Device Configuration |
| **CDC** | Connected Device Configuration |
| **MIDP** | Mobile Information Device Profile |
| **MIDlet** | Aplicação MIDP |
| **Xlet / IMlet** | Aplicação CDC (Xlet) / MEEP (IMlet) |
| **JAD** | Java Application Descriptor |
| **JAR** | Java Archive |
| **KVM** | Kilobyte Virtual Machine |
| **LCDUI** | Liquid Crystal Display User Interface (UI do MIDP) |
| **RMS** | Record Management System |
| **GCF** | Generic Connection Framework |
| **OTA** | Over-The-Air (instalação pela rede) |
| **JSR** | Java Specification Request (Java Community Process) |
| **JTWI** | Java Technology for the Wireless Industry |
| **MSA** | Mobile Service Architecture |
| **DEX** | Dalvik Executable |
| **ART** | Android Runtime |
| **API Level** | Número inteiro que identifica a revisão da API do Android |
| **Desugaring** | Conversão de recursos modernos de linguagem em código compatível com versões antigas |
| **AAB** | Android App Bundle |

---

## 19. Referências

- Java Community Process (JCP): https://jcp.org (JSR 30, 37, 118, 139, 185, 248, 271, 360 etc.)
- Oracle Java ME: https://www.oracle.com/java/technologies/javameoverview.html
- Android Developers – Níveis de API: https://developer.android.com/guide/topics/manifest/uses-sdk-element#api-level-table
- Android Developers – Recursos de linguagem Java: https://developer.android.com/build/jdks e https://developer.android.com/studio/write/java8-support
- J2ME Loader: https://github.com/nikita36078/J2ME-Loader
- FreeJ2ME: https://github.com/hex007/freej2me
- MicroEmulator: https://github.com/barteo/microemulator

---

> **Dica:** como os dados de suporte variam por fabricante e versão, sempre **teste no aparelho real** (Java ME) ou em **emuladores e dispositivos com diferentes níveis de API** (Android).
