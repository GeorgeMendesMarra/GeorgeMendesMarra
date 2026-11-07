# Java Mobile para Iniciantes

Guia introdutório sobre desenvolvimento de aplicações móveis com Java, com instruções para **Windows**, **Linux** e **macOS**.

---

## Sumário

1. [O que é Java Mobile?](#1-o-que-é-java-mobile)
2. [Um pouco de história](#2-um-pouco-de-história)
3. [Panorama atual: quais caminhos existem?](#3-panorama-atual-quais-caminhos-existem)
4. [Android com Java: conceitos fundamentais](#4-android-com-java-conceitos-fundamentais)
5. [Preparando o ambiente](#5-preparando-o-ambiente)
6. [Seu primeiro app (Olá, Mundo)](#6-seu-primeiro-app-olá-mundo)
7. [Estrutura de um projeto Android](#7-estrutura-de-um-projeto-android)
8. [Ciclo de vida de uma Activity](#8-ciclo-de-vida-de-uma-activity)
9. [Interface: layouts e componentes](#9-interface-layouts-e-componentes)
10. [Navegação entre telas (Intent)](#10-navegação-entre-telas-intent)
11. [Listas com RecyclerView](#11-listas-com-recyclerview)
12. [Armazenamento de dados](#12-armazenamento-de-dados)
13. [Tarefas em segundo plano e internet](#13-tarefas-em-segundo-plano-e-internet)
14. [Permissões](#14-permissões)
15. [Testando: emulador e celular físico](#15-testando-emulador-e-celular-físico)
16. [Gerando e publicando o app](#16-gerando-e-publicando-o-app)
17. [Java ME (MIDlets): visão histórica](#17-java-me-midlets-visão-histórica)
18. [Alternativas multiplataforma](#18-alternativas-multiplataforma)
19. [E o iOS?](#19-e-o-ios)
20. [Boas práticas](#20-boas-práticas)
21. [Problemas comuns e soluções](#21-problemas-comuns-e-soluções)
22. [Exercícios propostos](#22-exercícios-propostos)
23. [Glossário](#23-glossário)
24. [Referências e links úteis](#24-referências-e-links-úteis)

---

## 1. O que é Java Mobile?

**Java Mobile** é um termo amplo para o desenvolvimento de aplicativos para dispositivos móveis (celulares, tablets, relógios, TVs etc.) usando a linguagem Java ou a plataforma Java. Na prática, o termo pode se referir a três coisas diferentes:

| Significado | Tecnologia | Situação hoje |
|---|---|---|
| Java nos celulares "antigos" | **Java ME** (Micro Edition), aplicativos chamados **MIDlets** | Histórico; praticamente descontinuado em celulares |
| Java no Android | **Android SDK** + linguagem Java | **O caminho mais usado e o foco deste material** |
| Java em várias plataformas | **Gluon Mobile (JavaFX)**, **Codename One**, **LibGDX** | Alternativas de nicho, úteis em casos específicos |

> **Resumo para o iniciante:** quando alguém hoje fala em "programar em Java para celular", quase sempre quer dizer **criar apps Android usando Java**.

---

## 2. Um pouco de história

- **1995** – Java é lançado pela Sun Microsystems ("escreva uma vez, rode em qualquer lugar").
- **Anos 2000** – Surge o **J2ME / Java ME**, versão enxuta do Java para aparelhos com pouca memória. Milhões de celulares "tijolão" rodavam jogos e apps em Java (os famosos jogos `.jar`/`.jad`).
- **2007–2008** – O Google anuncia o **Android**. Os apps são escritos em Java, mas **não rodam na JVM tradicional**: eram compilados para o formato **DEX** e executados na máquina **Dalvik** (hoje substituída pelo **ART – Android Runtime**).
- **2010 em diante** – Android domina o mercado e Java vira a linguagem oficial do desenvolvimento Android.
- **2017** – O Google passa a suportar oficialmente o **Kotlin**; em **2019** declara a abordagem "Kotlin-first". Java **continua suportado** e existe uma enorme quantidade de código e vagas em Java.

---

## 3. Panorama atual: quais caminhos existem?

```
                    Java Mobile
                         |
      +------------------+------------------+
      |                  |                  |
  Java ME           Android (Java)     Multiplataforma
 (histórico)        (foco do curso)   (Gluon, Codename One,
                                        LibGDX)
```

**Por que aprender Android com Java?**

- Você reaproveita o que já sabe de **POO** (classes, herança, interfaces, polimorfismo, coleções).
- Há muito código corporativo e legado Android escrito em Java.
- A lógica aprendida serve também para Kotlin (as duas linguagens rodam na mesma plataforma e podem conviver no mesmo projeto).

---

## 4. Android com Java: conceitos fundamentais

Antes do código, conheça os "blocos de construção" de um app Android:

| Componente | Para que serve | Exemplo |
|---|---|---|
| **Activity** | Uma tela do aplicativo | Tela de login |
| **Fragment** | Parte reutilizável de uma tela | Aba de uma tela com abas |
| **View** | Elemento visual | Botão, texto, imagem |
| **Layout (XML)** | Descreve a aparência da tela | `activity_main.xml` |
| **Intent** | "Mensagem" para abrir telas ou outros apps | Abrir a câmera, abrir outra Activity |
| **Service** | Trabalho em segundo plano | Tocar música |
| **BroadcastReceiver** | Reage a eventos do sistema | Bateria fraca, boot concluído |
| **ContentProvider** | Compartilha dados entre apps | Lista de contatos |
| **AndroidManifest.xml** | "Documento de identidade" do app | Declara telas, permissões, ícone |
| **Gradle** | Sistema de build | Compila, empacota, baixa bibliotecas |

### Como o código Java vira app?

```
Código Java (.java)
      │  javac / compilador do Android
      ▼
Bytecode (.class)
      │  D8 / R8
      ▼
DEX (.dex)  +  Recursos (XML, imagens)
      │  empacotamento (Gradle)
      ▼
APK / AAB  ──►  instalado no dispositivo e executado pelo ART
```

---

## 5. Preparando o ambiente

A ferramenta oficial é o **Android Studio** (baseado no IntelliJ IDEA), que já inclui o **Android SDK**, o **emulador** e o suporte ao **Gradle**. Ele também traz um **JDK embutido** (JetBrains Runtime), então, em geral, **não é necessário instalar o JDK separadamente** para compilar apps Android.

### 5.1 Requisitos gerais

- Computador de 64 bits.
- **8 GB de RAM** no mínimo (16 GB é bem mais confortável, principalmente com o emulador).
- Vários GB de espaço livre em disco (SDK, imagens do emulador e caches do Gradle ocupam bastante).
- Virtualização habilitada (para o emulador rodar com desempenho aceitável).

> Confira sempre os requisitos atuais na página oficial: <https://developer.android.com/studio>

### 5.2 Windows

1. Baixe o instalador em <https://developer.android.com/studio>.
2. Execute o `.exe` e siga o assistente (mantenha marcados *Android Studio* e *Android Virtual Device*).
3. Na primeira abertura, use o **Setup Wizard** (opção *Standard*) para baixar o SDK e as ferramentas.
4. **Virtualização do emulador:**
   - Habilite a virtualização (Intel VT-x / AMD-V / SVM) na **BIOS/UEFI**, se estiver desligada.
   - No Windows, ative o recurso **"Plataforma do Hipervisor do Windows"** (*Windows Hypervisor Platform*) em *Ativar ou desativar recursos do Windows*. Reinicie o computador.
5. (Opcional) Configure a variável de ambiente para usar `adb` no terminal:
   - Caminho comum do SDK: `C:\Users\SEU_USUARIO\AppData\Local\Android\Sdk`
   - Adicione ao `PATH`: `...\Sdk\platform-tools`

Testando no **PowerShell**:

```powershell
adb version
```

### 5.3 Linux (Ubuntu/Debian como exemplo)

1. Baixe o arquivo `.tar.gz` em <https://developer.android.com/studio>.
2. Extraia, por exemplo em `/opt`:

   ```bash
   sudo tar -xzf android-studio-*-linux.tar.gz -C /opt
   ```

3. Execute o Studio:

   ```bash
   /opt/android-studio/bin/studio.sh
   ```

   (No menu do Studio, use *Tools → Create Desktop Entry* para criar um atalho.)

4. **Virtualização (KVM):**

   ```bash
   sudo apt update
   sudo apt install qemu-kvm
   sudo adduser $USER kvm
   # faça logout/login para aplicar o grupo
   ```

   Verifique se a KVM está disponível:

   ```bash
   ls -l /dev/kvm
   ```

5. **Variáveis de ambiente** (adicione ao `~/.bashrc` ou `~/.zshrc`):

   ```bash
   export ANDROID_HOME=$HOME/Android/Sdk
   export PATH=$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator
   ```

   Depois: `source ~/.bashrc`

6. **Celular físico no Linux:** pode ser necessário configurar regras **udev** para que o sistema reconheça o aparelho. Consulte a documentação oficial *"Run apps on a hardware device"*.

> Em outras distribuições (Fedora, Arch etc.), mude apenas o gerenciador de pacotes (`dnf`, `pacman`...).

### 5.4 macOS

1. Baixe o `.dmg` em <https://developer.android.com/studio> — escolha a versão correta para seu chip: **Apple Silicon (M1/M2/M3...)** ou **Intel**.
2. Abra o `.dmg` e arraste o **Android Studio** para a pasta **Aplicativos**.
3. Abra o Studio e conclua o **Setup Wizard**.
4. **Emulador:** o macOS usa o framework de virtualização nativo (*Hypervisor.framework*), sem configuração extra.
   - Em Mac com **Apple Silicon**, use imagens de sistema **arm64**.
   - Em Mac **Intel**, use imagens **x86_64**.
5. **Variáveis de ambiente** (adicione ao `~/.zshrc`):

   ```bash
   export ANDROID_HOME=$HOME/Library/Android/sdk
   export PATH=$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator
   ```

   Depois: `source ~/.zshrc`

### 5.5 Verificando a instalação (qualquer sistema)

No terminal:

```bash
adb version
```

No Android Studio, abra **Tools → SDK Manager** e confirme que há pelo menos uma **plataforma (API level)** instalada e o item **Android SDK Platform-Tools**.

---

## 6. Seu primeiro app (Olá, Mundo)

### 6.1 Criando o projeto

1. Abra o Android Studio → **New Project**.
2. Escolha o template **"Empty Views Activity"**.

   > ⚠️ **Atenção:** o template **"Empty Activity"** (sem "Views") usa **Jetpack Compose**, que é só Kotlin. Para Java, use **Empty Views Activity**.

3. Preencha:
   - **Name:** `MeuPrimeiroApp`
   - **Package name:** `com.exemplo.meuprimeiroapp`
   - **Language:** **Java**
   - **Minimum SDK:** escolha um nível que cubra a maioria dos aparelhos (o próprio Studio mostra a porcentagem de cobertura).
4. Clique em **Finish** e aguarde o **Gradle sync** (na primeira vez demora, pois baixa dependências).

### 6.2 Layout: `res/layout/activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/editNome"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Digite seu nome"
        android:inputType="textPersonName" />

    <Button
        android:id="@+id/btnSaudar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Saudar" />

    <TextView
        android:id="@+id/txtResultado"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:textSize="20sp"
        android:text="Olá, Mundo!" />
</LinearLayout>
```

### 6.3 Código Java: `MainActivity.java`

```java
package com.exemplo.meuprimeiroapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editNome;
    private Button btnSaudar;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ligando os objetos Java aos elementos do XML
        editNome = findViewById(R.id.editNome);
        btnSaudar = findViewById(R.id.btnSaudar);
        txtResultado = findViewById(R.id.txtResultado);

        // Tratando o clique do botão
        btnSaudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNome.getText().toString().trim();

                if (nome.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Digite seu nome!", Toast.LENGTH_SHORT).show();
                } else {
                    txtResultado.setText("Olá, " + nome + "!");
                }
            }
        });
    }
}
```

Versão com **lambda** (mais enxuta, Java 8+):

```java
btnSaudar.setOnClickListener(v -> {
    String nome = editNome.getText().toString().trim();
    txtResultado.setText(nome.isEmpty() ? "Digite seu nome!" : "Olá, " + nome + "!");
});
```

### 6.4 Executando

1. Escolha um dispositivo na barra superior (emulador ou celular).
2. Clique no botão **Run ▶**.
3. Digite um nome, toque em **Saudar** e veja o resultado.

---

## 7. Estrutura de um projeto Android

```
MeuPrimeiroApp/
├── app/
│   ├── build.gradle(.kts)        ← configuração do módulo (SDK, dependências)
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml   ← declara telas, permissões, ícone
│           ├── java/
│           │   └── com/exemplo/meuprimeiroapp/
│           │       └── MainActivity.java   ← seu código Java
│           └── res/
│               ├── layout/           ← telas em XML
│               ├── values/           ← strings.xml, colors.xml, themes.xml
│               ├── drawable/         ← imagens e ícones vetoriais
│               └── mipmap/           ← ícones do app
├── build.gradle(.kts)            ← configuração do projeto
├── settings.gradle(.kts)
└── gradle/
```

### Trecho importante do `build.gradle` do módulo

```groovy
android {
    namespace 'com.exemplo.meuprimeiroapp'
    compileSdk 34              // versão da API usada para compilar

    defaultConfig {
        applicationId "com.exemplo.meuprimeiroapp"
        minSdk 24              // versão mínima do Android suportada
        targetSdk 34           // versão para a qual o app foi testado
        versionCode 1
        versionName "1.0"
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_11
        targetCompatibility JavaVersion.VERSION_11
    }
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
}
```

> Os números de versão mudam com frequência. Use os valores que o próprio Android Studio sugerir ao criar o projeto.

### `strings.xml` – não escreva textos "soltos"

```xml
<resources>
    <string name="app_name">Meu Primeiro App</string>
    <string name="btn_saudar">Saudar</string>
</resources>
```

Uso no layout: `android:text="@string/btn_saudar"` — facilita **tradução** (internacionalização) e manutenção.

---

## 8. Ciclo de vida de uma Activity

O Android controla quando sua tela é criada, exibida, pausada e destruída. Você reage a isso sobrescrevendo métodos:

```
onCreate() → onStart() → onResume()   ← tela visível e interativa
                              │
                         onPause()    ← outra tela parcialmente à frente
                              │
                         onStop()     ← tela não visível
                              │
                        onDestroy()   ← tela destruída
```

| Método | Quando é chamado | Uso típico |
|---|---|---|
| `onCreate()` | Activity é criada | Montar a tela, ligar views, inicializar variáveis |
| `onStart()` | Vai ficar visível | Preparar elementos visuais |
| `onResume()` | Em primeiro plano, interativa | Retomar animações, sensores |
| `onPause()` | Perde o foco | Salvar dados rápidos, pausar animações |
| `onStop()` | Deixa de ser visível | Liberar recursos pesados |
| `onDestroy()` | Será destruída | Limpeza final |

Exemplo para **observar** o ciclo de vida no Logcat:

```java
private static final String TAG = "CicloDeVida";

@Override protected void onStart()  { super.onStart();  Log.d(TAG, "onStart");  }
@Override protected void onResume() { super.onResume(); Log.d(TAG, "onResume"); }
@Override protected void onPause()  { super.onPause();  Log.d(TAG, "onPause");  }
@Override protected void onStop()   { super.onStop();   Log.d(TAG, "onStop");   }
```

> 💡 **Dica:** gire o celular (ou o emulador). A Activity é **destruída e recriada** por padrão. Por isso é importante salvar o estado (`onSaveInstanceState`) ou usar `ViewModel`.

---

## 9. Interface: layouts e componentes

### Principais layouts

| Layout | Descrição |
|---|---|
| `LinearLayout` | Elementos em linha (horizontal ou vertical) |
| `RelativeLayout` | Posições relativas a outros elementos (mais antigo) |
| `ConstraintLayout` | Layout flexível baseado em restrições (recomendado para telas complexas) |
| `FrameLayout` | Empilha elementos (um sobre o outro) |
| `ScrollView` | Permite rolar o conteúdo |

### Principais componentes (Views)

| Componente | Função |
|---|---|
| `TextView` | Exibe texto |
| `EditText` | Campo de entrada de texto |
| `Button` | Botão |
| `ImageView` | Exibe imagens |
| `CheckBox` / `RadioButton` / `Switch` | Opções de seleção |
| `Spinner` | Lista suspensa |
| `RecyclerView` | Lista eficiente de itens |
| `ProgressBar` | Indicador de progresso |

### Unidades de medida

- **`dp`** (density-independent pixels): para tamanhos e margens.
- **`sp`** (scale-independent pixels): para tamanho de texto (respeita a configuração de fonte do usuário).
- Evite `px`, pois varia conforme a densidade da tela.

### Exemplo: calculadora de IMC (lógica)

```java
btnCalcular.setOnClickListener(v -> {
    try {
        double peso = Double.parseDouble(editPeso.getText().toString());
        double altura = Double.parseDouble(editAltura.getText().toString());
        double imc = peso / (altura * altura);
        txtResultado.setText(String.format("IMC: %.2f", imc));
    } catch (NumberFormatException e) {
        Toast.makeText(this, "Preencha peso e altura corretamente", Toast.LENGTH_SHORT).show();
    }
});
```

---

## 10. Navegação entre telas (Intent)

### 10.1 Declarar a nova Activity no Manifest

```xml
<application ...>
    <activity android:name=".MainActivity" android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>

    <activity android:name=".DetalheActivity" />
</application>
```

> O Android Studio faz isso automaticamente ao criar uma Activity por **File → New → Activity**.

### 10.2 Abrir a segunda tela enviando dados

```java
Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
intent.putExtra("nome", "Maria");
intent.putExtra("idade", 20);
startActivity(intent);
```

### 10.3 Receber os dados na segunda tela

```java
public class DetalheActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        String nome = getIntent().getStringExtra("nome");
        int idade = getIntent().getIntExtra("idade", 0);

        TextView txt = findViewById(R.id.txtDetalhe);
        txt.setText(nome + " - " + idade + " anos");
    }
}
```

### 10.4 Intent implícita (abrir outros apps)

```java
// Abrir um site no navegador
Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"));
startActivity(web);

// Compartilhar texto
Intent share = new Intent(Intent.ACTION_SEND);
share.setType("text/plain");
share.putExtra(Intent.EXTRA_TEXT, "Olá do meu app!");
startActivity(Intent.createChooser(share, "Compartilhar via"));
```

> Para receber **resultado** de outra tela, a forma moderna é a **Activity Result API** (`registerForActivityResult`), pois `startActivityForResult` está obsoleto.

---

## 11. Listas com RecyclerView

O `RecyclerView` exibe listas longas reaproveitando as views que saem da tela (muito eficiente).

**Dependência** (geralmente já vem com o Material, mas se precisar):

```groovy
implementation 'androidx.recyclerview:recyclerview:1.3.2'
```

### 11.1 Modelo

```java
public class Tarefa {
    private final String titulo;
    private boolean concluida;

    public Tarefa(String titulo) { this.titulo = titulo; }
    public String getTitulo() { return titulo; }
    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }
}
```

### 11.2 Layout do item: `item_tarefa.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/txtTitulo"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="16dp"
    android:textSize="18sp" />
```

### 11.3 Adapter

```java
public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder> {

    private final List<Tarefa> tarefas;

    public TarefaAdapter(List<Tarefa> tarefas) { this.tarefas = tarefas; }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtTitulo;
        ViewHolder(View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarefa, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitulo.setText(tarefas.get(position).getTitulo());
    }

    @Override
    public int getItemCount() { return tarefas.size(); }
}
```

### 11.4 Usando na Activity

```java
RecyclerView recycler = findViewById(R.id.recyclerTarefas);
recycler.setLayoutManager(new LinearLayoutManager(this));

List<Tarefa> lista = new ArrayList<>();
lista.add(new Tarefa("Estudar Java"));
lista.add(new Tarefa("Fazer o exercício de Android"));

recycler.setAdapter(new TarefaAdapter(lista));
```

---

## 12. Armazenamento de dados

| Opção | Quando usar |
|---|---|
| **SharedPreferences** | Pequenas configurações (tema, nome do usuário) |
| **Arquivos internos** | Textos, JSON, imagens do app |
| **SQLite** (`SQLiteOpenHelper`) | Banco relacional local, acesso "manual" |
| **Room** | Camada sobre o SQLite, recomendada pelo Google |
| **Firebase / API REST** | Dados na nuvem e sincronização |

### 12.1 SharedPreferences

```java
// Salvar
SharedPreferences prefs = getSharedPreferences("config", MODE_PRIVATE);
prefs.edit()
     .putString("nome", "Maria")
     .putBoolean("modoEscuro", true)
     .apply();

// Ler
String nome = prefs.getString("nome", "Visitante");
boolean escuro = prefs.getBoolean("modoEscuro", false);
```

### 12.2 Room (visão geral)

**Dependências:**

```groovy
implementation 'androidx.room:room-runtime:2.6.1'
annotationProcessor 'androidx.room:room-compiler:2.6.1'
```

**Entidade:**

```java
@Entity(tableName = "alunos")
public class Aluno {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nome;
    public int idade;
}
```

**DAO (acesso aos dados):**

```java
@Dao
public interface AlunoDao {
    @Insert
    void inserir(Aluno aluno);

    @Query("SELECT * FROM alunos ORDER BY nome")
    List<Aluno> listarTodos();

    @Delete
    void excluir(Aluno aluno);
}
```

**Banco:**

```java
@Database(entities = {Aluno.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlunoDao alunoDao();
}
```

**Criando a instância:**

```java
AppDatabase db = Room.databaseBuilder(
        getApplicationContext(), AppDatabase.class, "escola-db").build();
```

> ⚠️ O Room **não permite** consultas na thread principal (UI). Use uma thread separada (veja a próxima seção).

---

## 13. Tarefas em segundo plano e internet

### Regra de ouro

> **Nunca** faça operações demoradas (rede, banco, arquivos grandes) na **thread principal (UI thread)**. Isso trava a tela e pode gerar o erro **ANR** (*Application Not Responding*). Em operações de rede, o Android lança `NetworkOnMainThreadException`.

### 13.1 ExecutorService + Handler

```java
ExecutorService executor = Executors.newSingleThreadExecutor();
Handler mainHandler = new Handler(Looper.getMainLooper());

executor.execute(() -> {
    // 1) Trabalho pesado em segundo plano
    List<Aluno> alunos = db.alunoDao().listarTodos();

    // 2) Voltar para a thread principal para atualizar a tela
    mainHandler.post(() -> {
        txtResultado.setText("Total: " + alunos.size());
    });
});
```

> `AsyncTask` está **obsoleto** (deprecated). Prefira `ExecutorService`, `WorkManager` (tarefas garantidas) ou, em projetos mais avançados, `LiveData`/`ViewModel`.

### 13.2 Consumindo uma API REST com Retrofit

**Dependências:**

```groovy
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

**Permissão de internet** no `AndroidManifest.xml` (fora da tag `<application>`):

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

**Modelo:**

```java
public class Post {
    public int id;
    public String title;
    public String body;
}
```

**Interface da API:**

```java
public interface ApiService {
    @GET("posts")
    Call<List<Post>> listarPosts();
}
```

**Uso:**

```java
Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

ApiService api = retrofit.create(ApiService.class);

api.listarPosts().enqueue(new Callback<List<Post>>() {
    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if (response.isSuccessful() && response.body() != null) {
            txtResultado.setText("Recebidos: " + response.body().size() + " posts");
        }
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        Toast.makeText(MainActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
    }
});
```

> O `enqueue` já executa em segundo plano e devolve o callback na thread principal.

---

## 14. Permissões

Alguns recursos exigem permissão: internet, câmera, localização, contatos, armazenamento, notificações etc.

1. **Declare** no `AndroidManifest.xml`:

   ```xml
   <uses-permission android:name="android.permission.CAMERA" />
   ```

2. Permissões **perigosas** (câmera, localização, microfone...) precisam também ser **solicitadas em tempo de execução**:

   ```java
   private final ActivityResultLauncher<String> pedirCamera =
       registerForActivityResult(new ActivityResultContracts.RequestPermission(),
           concedida -> {
               if (concedida) {
                   Toast.makeText(this, "Permissão concedida!", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(this, "Permissão negada.", Toast.LENGTH_SHORT).show();
               }
           });

   private void verificarCamera() {
       if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
               == PackageManager.PERMISSION_GRANTED) {
           // já pode usar a câmera
       } else {
           pedirCamera.launch(Manifest.permission.CAMERA);
       }
   }
   ```

> **Princípio:** peça **somente** as permissões de que o app realmente precisa e explique ao usuário o motivo.

---

## 15. Testando: emulador e celular físico

### 15.1 Emulador (AVD – Android Virtual Device)

1. **Tools → Device Manager → Create Device**.
2. Escolha um modelo (ex.: *Pixel*).
3. Baixe uma **imagem do sistema** (System Image):
   - Windows/Linux (Intel/AMD): **x86_64**
   - macOS Apple Silicon: **arm64**
4. Finalize e clique em ▶ para iniciar.

### 15.2 Celular físico (recomendado quando possível)

1. No celular: **Configurações → Sobre o telefone → toque 7 vezes em "Número da versão"** para liberar as **Opções do desenvolvedor**.
2. Ative a **Depuração USB** (*USB debugging*).
3. Conecte o cabo USB e **autorize** o computador no celular.
4. Confirme no terminal:

   ```bash
   adb devices
   ```

5. O aparelho aparecerá na lista de dispositivos do Android Studio.

Há também a **Depuração sem fio** (*Wireless debugging*) em versões recentes do Android.

### 15.3 Ferramentas úteis

| Ferramenta | Uso |
|---|---|
| **Logcat** | Mensagens de log e erros do app (`Log.d`, `Log.e`) |
| **Debugger** | Pontos de interrupção (breakpoints) e inspeção de variáveis |
| **Layout Inspector** | Inspecionar a hierarquia de views em execução |
| **adb** | Linha de comando para falar com o dispositivo |

Comandos `adb` básicos:

```bash
adb devices                      # lista dispositivos conectados
adb install app-debug.apk        # instala um APK
adb uninstall com.exemplo.app    # desinstala
adb logcat                       # mostra logs no terminal
```

---

## 16. Gerando e publicando o app

### 16.1 APK x AAB

| Formato | Descrição |
|---|---|
| **APK** | Pacote instalável diretamente (bom para testes e distribuição manual) |
| **AAB** (Android App Bundle) | Formato exigido pela **Google Play** para publicação |

### 16.2 Gerando

- **Build → Build Bundle(s) / APK(s) → Build APK(s)** (APK de depuração).
- **Build → Generate Signed Bundle / APK** (versão de lançamento assinada).

### 16.3 Assinatura

Todo app precisa ser **assinado** com uma chave (*keystore*). **Guarde a keystore e as senhas com muito cuidado**: sem elas, você não consegue atualizar o app publicado.

### 16.4 Publicando na Google Play

1. Crie uma conta no **Google Play Console** (há taxa única de cadastro).
2. Crie o app, preencha ficha da loja, política de privacidade e classificação de conteúdo.
3. Envie o **AAB**, faça testes internos/fechados e depois publique.

> As regras e exigências da loja mudam com frequência (por exemplo, requisitos mínimos de API alvo e de testes para novas contas). Consulte sempre: <https://developer.android.com/distribute>

---

## 17. Java ME (MIDlets): visão histórica

O **Java ME** (Micro Edition) foi a plataforma Java para dispositivos de recursos limitados.

### Arquitetura resumida

```
Aplicação (MIDlet)
      │
    MIDP   ← Perfil (interface, rede, armazenamento)
      │
    CLDC   ← Configuração (JVM reduzida, bibliotecas básicas)
      │
Sistema do aparelho
```

### Exemplo clássico de MIDlet

```java
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public class OlaMidlet extends MIDlet implements CommandListener {
    private Form form;
    private Command sair;

    public void startApp() {
        form = new Form("Olá");
        form.append("Olá, Java ME!");
        sair = new Command("Sair", Command.EXIT, 1);
        form.addCommand(sair);
        form.setCommandListener(this);
        Display.getDisplay(this).setCurrent(form);
    }

    public void pauseApp() { }
    public void destroyApp(boolean incondicional) { }

    public void commandAction(Command c, Displayable d) {
        if (c == sair) {
            destroyApp(true);
            notifyDestroyed();
        }
    }
}
```

### Situação hoje

- Os celulares modernos (Android e iOS) **não executam MIDlets**.
- O Java ME segue existindo em **dispositivos embarcados e IoT** (por exemplo, a versão *Java ME Embedded* e equivalentes), mas é um nicho distante do desenvolvimento de apps para smartphones.
- Para fins didáticos, ele é útil para entender **por que** o Java nasceu pensando em portabilidade e restrição de recursos.

---

## 18. Alternativas multiplataforma

Além do Android "puro", existem opções que permitem usar Java (ou JVM) em mais de uma plataforma:

| Tecnologia | Descrição | Observações |
|---|---|---|
| **Gluon Mobile (JavaFX)** | Apps JavaFX para Android e iOS usando GraalVM | Reaproveita conhecimento de JavaFX/desktop |
| **Codename One** | Escreve-se uma vez em Java e gera apps para Android, iOS e outros | Possui plugin para IDEs; build na nuvem |
| **LibGDX** | Framework de **jogos** em Java (Android, iOS, desktop, web) | Muito usado em jogos 2D/3D simples |
| **Kotlin Multiplatform (KMP)** | Compartilha lógica entre plataformas | Usa Kotlin; interopera com Java no Android |
| **Flutter / React Native** | Alternativas fora do ecossistema Java | Citadas apenas para comparação |

> Para **iniciantes**, comece pelo **Android nativo com Java**. As alternativas fazem mais sentido depois que você domina os conceitos básicos.

---

## 19. E o iOS?

- O iOS usa **Swift/Objective-C**; apps nativos **não são escritos em Java**.
- Para compilar e publicar apps iOS, é necessário o **Xcode**, que **só roda no macOS**.
- Quem usa **Windows ou Linux** não consegue gerar apps iOS localmente de forma nativa; as soluções multiplataforma citadas acima costumam oferecer **build na nuvem** ou exigem um Mac para a etapa final.
- A publicação na App Store exige uma conta **Apple Developer** (paga).

---

## 20. Boas práticas

1. **Separe responsabilidades:** não coloque toda a lógica dentro da Activity. Crie classes de modelo, repositório e (quando avançar) use **MVVM** com `ViewModel` e `LiveData`.
2. **Não bloqueie a UI thread:** rede e banco sempre em segundo plano.
3. **Use recursos externos:** textos em `strings.xml`, cores em `colors.xml`, dimensões em `dimens.xml`.
4. **Evite vazamento de memória:** não guarde referências a `Activity` em campos estáticos.
5. **Trate erros:** `try/catch`, validação de campos, mensagens claras ao usuário.
6. **Pense em várias telas:** teste em telas pequenas, grandes e em modo paisagem.
7. **Acessibilidade:** use `contentDescription` em imagens e contraste adequado.
8. **Segurança:** não deixe chaves de API ou senhas no código; use HTTPS.
9. **Versionamento:** use **Git** desde o primeiro dia (o Android Studio tem integração nativa). Inclua um `.gitignore` adequado (ignore `build/`, `.gradle/`, `local.properties`).
10. **Mantenha-se atualizado:** a plataforma evolui rápido; consulte a documentação oficial.

---

## 21. Problemas comuns e soluções

| Problema | Causa provável | Solução |
|---|---|---|
| **Gradle sync falhou** | Sem internet, proxy, versão incompatível | Verifique a conexão; *File → Sync Project with Gradle Files*; *Invalidate Caches / Restart* |
| **Emulador não inicia ou está lentíssimo** | Virtualização desativada | Ative VT-x/AMD-V na BIOS; Windows: *Windows Hypervisor Platform*; Linux: KVM e grupo `kvm` |
| **`adb` não é reconhecido** | `platform-tools` fora do `PATH` | Adicione `platform-tools` ao `PATH` (veja a seção 5) |
| **Celular não aparece em `adb devices`** | Cabo só de carga, depuração desligada, driver ausente | Troque o cabo; ative Depuração USB; Windows: instale o driver USB do fabricante; Linux: regras udev |
| **`device unauthorized`** | Computador não autorizado no celular | Revogue autorizações USB nas opções do desenvolvedor e conecte novamente |
| **`NetworkOnMainThreadException`** | Rede na thread principal | Use `Retrofit.enqueue`, `ExecutorService` ou `WorkManager` |
| **App fecha ao abrir (crash)** | Exceção não tratada | Leia o **Logcat** filtrando por *Error*; procure a linha `Caused by` |
| **`NullPointerException` com `findViewById`** | ID errado ou `setContentView` ausente | Confira o `R.id.xxx` e se o `setContentView` vem antes |
| **Sem internet no app** | Faltou a permissão | Adicione `android.permission.INTERNET` no Manifest |
| **Texto cortado em telas pequenas** | Tamanhos fixos | Use `wrap_content`, `match_parent`, `ConstraintLayout` e `sp` para texto |
| **Erro de "Java version" no Gradle** | JDK incompatível | Em *Settings → Build Tools → Gradle*, use o **Gradle JDK** embutido do Android Studio |
| **Pouco espaço em disco** | Caches e imagens do emulador | Remova AVDs e imagens de sistema que não usa (SDK Manager / Device Manager) |

---

## 22. Exercícios propostos

### Nível 1 – Básico

1. **Olá, Mundo personalizado:** altere o texto, a cor e o tamanho da fonte do `TextView`.
2. **Contador:** um botão "+1" que incrementa um número exibido na tela.
3. **Calculadora simples:** dois campos numéricos e botões para somar, subtrair, multiplicar e dividir (trate divisão por zero).
4. **Conversor de temperatura:** Celsius ↔ Fahrenheit.

### Nível 2 – Intermediário

5. **Duas telas:** na primeira, digitar nome e idade; na segunda, exibir os dados recebidos via `Intent`.
6. **Lista de compras:** `EditText` + botão "Adicionar" que insere itens em um `RecyclerView`.
7. **Preferências:** salvar o nome do usuário com `SharedPreferences` e exibi-lo ao reabrir o app.
8. **Ciclo de vida:** exiba no Logcat todos os métodos do ciclo de vida e observe o que acontece ao girar a tela e ao apertar *Home*.

### Nível 3 – Avançado

9. **Lista de tarefas (To-Do) com Room:** cadastrar, listar, marcar como concluída e excluir tarefas.
10. **Consumo de API:** listar dados de uma API pública (por exemplo, posts ou CEPs) usando Retrofit.
11. **Mini-projeto:** um app completo de sua escolha (agenda, controle financeiro, cadastro de alunos), com ao menos 3 telas, banco local e validações.

---

## 23. Glossário

| Termo | Significado |
|---|---|
| **Activity** | Tela de um app Android |
| **ADB** | *Android Debug Bridge*: ferramenta de linha de comando para comunicação com o dispositivo |
| **ANR** | *Application Not Responding*: app travado por muito tempo na UI thread |
| **APK** | Pacote de instalação do app Android |
| **AAB** | *Android App Bundle*: formato de publicação da Google Play |
| **API Level** | Número que identifica a versão da plataforma Android |
| **ART** | *Android Runtime*: ambiente que executa os apps |
| **AVD** | *Android Virtual Device*: celular virtual (emulador) |
| **DEX** | Formato de bytecode executado no Android |
| **Gradle** | Ferramenta de automação de build |
| **Intent** | Mensagem para iniciar componentes (telas, serviços) |
| **JDK** | *Java Development Kit*: kit de desenvolvimento Java |
| **Logcat** | Janela de logs do Android Studio |
| **Manifest** | Arquivo `AndroidManifest.xml` com as declarações do app |
| **MIDlet** | Aplicativo Java ME |
| **SDK** | *Software Development Kit*: conjunto de ferramentas de desenvolvimento |
| **UI Thread** | Thread principal, responsável por desenhar a tela |
| **View** | Elemento visual da interface |

---

## 24. Referências e links úteis

- **Android Developers (oficial):** <https://developer.android.com>
- **Download do Android Studio:** <https://developer.android.com/studio>
- **Documentação de Activities:** <https://developer.android.com/guide/components/activities>
- **Guia de Room:** <https://developer.android.com/training/data-storage/room>
- **Retrofit:** <https://square.github.io/retrofit/>
- **Gluon Mobile:** <https://gluonhq.com>
- **Codename One:** <https://www.codenameone.com>
- **LibGDX:** <https://libgdx.com>

---

> **Observação final:** versões de bibliotecas, níveis de API e exigências da Google Play mudam com frequência. Os números usados nos exemplos servem apenas como ilustração; confirme sempre os valores atuais na documentação oficial antes de iniciar um projeto.
