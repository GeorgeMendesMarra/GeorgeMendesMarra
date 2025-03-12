# Instalação do JavaFX no Debian

## 1. Verifique se o Java está instalado
Antes de instalar o JavaFX, certifique-se de que o Java está instalado no seu sistema:

```bash
java -version
```

Se o Java não estiver instalado, instale o OpenJDK:

```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

⚠️ O JavaFX funciona melhor com o **OpenJDK 17 ou superior**.

---

## 2. Baixar e Instalar o JavaFX

### **Opção 1: Instalando via Repositório do Debian**
Você pode instalar o JavaFX diretamente dos repositórios:

```bash
sudo apt install openjfx
```

Depois, verifique a instalação:

```bash
java --module-path /usr/share/openjfx/lib --add-modules javafx.controls -version
```

### **Opção 2: Instalando manualmente o JavaFX SDK**
Se quiser a versão mais recente:

1. Baixe o SDK do JavaFX no site oficial:  
   🔗 https://gluonhq.com/products/javafx/
2. Extraia o arquivo:
   ```bash
   tar -xvzf openjfx-*.tar.gz
   ```
3. Mova para um diretório adequado (exemplo: `/opt/javafx`):
   ```bash
   sudo mv javafx-sdk-* /opt/javafx
   ```
4. Configure as variáveis de ambiente:
   ```bash
   echo 'export PATH=$PATH:/opt/javafx/bin' >> ~/.bashrc
   echo 'export JAVAFX_HOME=/opt/javafx' >> ~/.bashrc
   echo 'export PATH=$JAVAFX_HOME/bin:$PATH' >> ~/.bashrc
   source ~/.bashrc
   ```

---

## 3. Compilar e Executar um Programa com JavaFX
Se você estiver rodando um programa JavaFX manualmente, use o seguinte comando:

```bash
javac --module-path /opt/javafx/lib --add-modules javafx.controls,javafx.fxml MeuPrograma.java
java --module-path /opt/javafx/lib --add-modules javafx.controls,javafx.fxml MeuPrograma
```

Se estiver usando **Maven** ou **Gradle**, me avise para te passar a configuração correta. 🚀
