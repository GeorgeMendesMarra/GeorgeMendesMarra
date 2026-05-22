Usar o Apache Ant no macOS é um processo simples. A melhor forma é automatizar quase tudo usando o gerenciador de pacotes **Homebrew** .

Aqui está o passo a passo:

### 🍺 Método 1: Instalação via Homebrew (Recomendado)

Este é o método mais rápido e fácil, pois o Homebrew baixa, instala e configura o Ant para você automaticamente.

1.  **Instalar o Homebrew (se você ainda não o tiver):**
    Abra o Terminal (em `Aplicativos/Utilitários`) e cole o seguinte comando. Ele instalará o Homebrew no seu Mac .
    ```bash
    /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
    ```

2.  **Instalar o Ant:**
    Com o Homebrew instalado, a instalação do Ant é feita com um único comando :
    ```bash
    brew install ant
    ```

3.  **Verificar a Instalação:**
    Para ter certeza de que tudo deu certo, digite o comando abaixo. Se você vir a versão do Ant (ex: `Apache Ant(TM) version 1.10.x`), a instalação foi um sucesso .
    ```bash
    ant -version
    ```

### 🛠️ Método 2: Instalação Manual (Para Controle Total)

Caso prefira não usar o Homebrew, você pode instalar o Ant manualmente. Isso lhe dá mais controle sobre onde a ferramenta é instalada.

1.  **Baixar o Binário:**
    Acesse o site oficial do Apache Ant e baixe o arquivo `.tar.gz` da distribuição binária (ex: `apache-ant-1.10.x-bin.tar.gz`) .

2.  **Extrair e Mover os Arquivos:**
    No Terminal, navegue até a pasta onde o arquivo foi baixado (normalmente `~/Downloads`). Em seguida, descompacte-o e mova a pasta resultante para o diretório `/usr/local/`. Você usará comandos como :
    ```bash
    # Vá para a pasta de Downloads
    cd ~/Downloads

    # Descompacte o arquivo (substitua pelo nome do arquivo que você baixou)
    tar -xvzf apache-ant-1.10.x-bin.tar.gz

    # Mova a pasta descompactada para /usr/local (pode precisar de senha de administrador)
    sudo cp -rf apache-ant-1.10.x /usr/local/
    ```

3.  **Configurar o Ambiente (Variáveis de Path):**
    Você precisa informar ao sistema onde encontrar os executáveis do Ant. Para isso, editaremos seu arquivo de perfil do shell.
    *   **Abra o arquivo de configuração:** O macOS moderno usa o Zsh, então você provavelmente editará o arquivo `~/.zshrc`. Se você usa o Bash, o arquivo é `~/.bash_profile` .
    ```bash
    nano ~/.zshrc
    ```
    *   **Adicione as linhas:** Cole as seguintes linhas no final do arquivo. Lembre-se de substituir `apache-ant-1.10.x` pelo nome exato da pasta que você moveu.
    ```bash
    # Variáveis para o Apache Ant
    export ANT_HOME=/usr/local/apache-ant-1.10.x
    export PATH=$PATH:$ANT_HOME/bin
    ```
    *   **Salve e saia:** No `nano`, pressione `Ctrl+O` para salvar, `Enter` para confirmar e `Ctrl+X` para sair.
    *   **Recarregue o arquivo:** Para que as mudanças entrem em vigor no terminal atual, execute:
    ```bash
    source ~/.zshrc
    ```

4.  **Verificar a Instalação:** Assim como no primeiro método, confirme com `ant -version` .

### ✅ Verificando o Pré-requisito: Java

O Ant é uma ferramenta para Java, portanto, você precisa ter o Java Development Kit (JDK) instalado no seu Mac. Para verificar, digite no Terminal:
```bash
javac -version
```
Se o comando não for encontrado, você precisará instalar o JDK antes de prosseguir com a instalação do Ant .

### ▶️ Usando o Apache Ant

1.  **O Arquivo de Build (`build.xml`):** O Ant funciona através de um arquivo de configuração chamado `build.xml`, que deve ficar na pasta raiz do seu projeto Java. Ele descreve as tarefas de build (compilar, testar, gerar JAR, etc.) .

2.  **Executando:** Depois de configurar o `build.xml`, execute o build pelo Terminal, dentro da pasta do seu projeto :
    ```bash
    ant
    ```
    Este comando executa a tarefa padrão definida no seu `build.xml`. Se você quiser executar uma tarefa específica (ex: `clean`), use `ant clean` .
