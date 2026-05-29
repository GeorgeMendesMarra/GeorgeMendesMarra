# Índice

1. [O que vamos construir](#1-o-que-vamos-construir)
2. [Ferramentas necessárias](#2-ferramentas-necessárias)
3. [Passo 1: Instalar e configurar o Oracle XE](#3-passo-1-instalar-e-configurar-o-oracle-xe)
4. [Passo 2: Criar o banco de dados e a tabela](#4-passo-2-criar-o-banco-de-dados-e-a-tabela)
5. [Passo 3: Baixar o driver JDBC do Oracle](#5-passo-3-baixar-o-driver-jdbc-do-oracle)
6. [Passo 4: Criar o projeto no Eclipse/NetBeans/VS Code](#6-passo-4-criar-o-projeto-no-eclipsenetbeansvs-code)
7. [Passo 5: Adicionar o driver ao projeto](#7-passo-5-adicionar-o-driver-ao-projeto)
8. [Passo 6: Criar a classe de conexão com o banco](#8-passo-6-criar-a-classe-de-conexão-com-o-banco)
9. [Passo 7: Criar o formulário (interface gráfica)](#9-passo-7-criar-o-formulário-interface-gráfica)
10. [Passo 8: Criar a classe para salvar os dados](#10-passo-8-criar-a-classe-para-salvar-os-dados)
11. [Passo 9: Juntar tudo no formulário](#11-passo-9-juntar-tudo-no-formulário)
12. [Código completo](#12-código-completo)
13. [Erros comuns e como resolver](#13-erros-comuns-e-como-resolver)

---

## 1. O que vamos construir

Vamos criar um programa Java com uma janela simples para cadastrar usuários (nome, email e idade). Ao clicar em "Salvar", os dados vão para um banco de dados **Oracle XE**.

```
┌─────────────────────────────────────┐
│       CADASTRO DE USUÁRIOS           │
├─────────────────────────────────────┤
│                                     │
│  Nome:    [__________________]      │
│                                     │
│  Email:   [__________________]      │
│                                     │
│  Idade:   [_____]                   │
│                                     │
│  ┌─────────────────────────────┐    │
│  │         SALVAR              │    │
│  └─────────────────────────────┘    │
│                                     │
│  Mensagem: Usuário cadastrado!      │
└─────────────────────────────────────┘
```

## 2. Ferramentas necessárias

| Ferramenta | O que é | Onde baixar |
|------------|---------|--------------|
| Java JDK | Kit de desenvolvimento Java | Oracle Java ou OpenJDK |
| **Oracle Database XE** | Banco de dados Oracle gratuito | [Oracle XE](https://www.oracle.com/br/database/technologies/xe-downloads.html) |
| **SQL Developer** | Interface visual para Oracle (opcional) | Oracle SQL Developer |
| IDE Java | Programas para escrever código | Eclipse, IntelliJ IDEA ou VS Code |
| **JDBC Driver Oracle** | Biblioteca que conecta Java ao Oracle | [Oracle JDBC Driver](https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html) |

## 3. Passo 1: Instalar e configurar o Oracle XE

### 3.1 Instalar o Oracle Database XE

Baixe e instale o **Oracle Database 21c XE** (ou versão mais recente).

**Durante a instalação, você definirá:**
- **Senha do usuário SYS e SYSTEM** (administrador) - **GUARDE ESSA SENHA!**
- Porta padrão: **1521** (para conexões JDBC)
- Exemplo de senha: `123456` (apenas para testes)

### 3.2 Verificar se o Oracle está rodando

Abra o **SQL Developer** ou o **Prompt de Comando** (SQL*Plus):

```bash
sqlplus system@localhost:1521/XE
# Digite a senha que você criou
```

Se aparecer `SQL>` o banco está funcionando.

### 3.3 Configurações importantes do Oracle XE

- **Service Name padrão:** `XE`
- **Porta padrão:** `1521`
- **SID:** `XE`
- **Usuário admin:** `SYSTEM`

## 4. Passo 2: Criar o banco de dados e a tabela

No Oracle, não criamos um "banco de dados" separado como no MySQL. Usamos **schemas** (esquemas) dentro do banco XE.

### 4.1 Criar um usuário (schema) para nossa aplicação

No SQL*Plus ou SQL Developer, execute como usuário SYSTEM:

```sql
-- Criar um usuário para nossa aplicação
CREATE USER cadastro_user IDENTIFIED BY cadastro123;

-- Dar privilégios básicos
GRANT CONNECT, RESOURCE TO cadastro_user;

-- Dar permissão para criar tabelas
ALTER USER cadastro_user QUOTA UNLIMITED ON USERS;
```

### 4.2 Criar a tabela de usuários

Agora conecte como o novo usuário:

```bash
sqlplus cadastro_user@localhost:1521/XE
# Senha: cadastro123
```

Execute:

```sql
-- Criar a tabela (Oracle usa sintaxe um pouco diferente)
CREATE TABLE usuarios (
    id NUMBER PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL UNIQUE,
    idade NUMBER
);

-- Criar uma SEQUENCE para auto-incremento (Oracle não tem AUTO_INCREMENT)
CREATE SEQUENCE seq_usuarios
    START WITH 1
    INCREMENT BY 1;

-- Criar uma TRIGGER para auto-incrementar o ID
CREATE OR REPLACE TRIGGER trg_usuarios_id
BEFORE INSERT ON usuarios
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT seq_usuarios.NEXTVAL INTO :NEW.id FROM DUAL;
    END IF;
END;
/
```

### 4.3 Verificar se a tabela foi criada

```sql
-- Ver todas as tabelas do usuário
SELECT table_name FROM user_tables;

-- Ver a estrutura da tabela
DESCRIBE usuarios;
```

## 5. Passo 3: Baixar o driver JDBC do Oracle

### 5.1 O que é o JDBC Driver Oracle?

É uma biblioteca (arquivo `.jar`) que permite o Java se conectar ao Oracle Database.

### 5.2 Baixar o arquivo

1. Acesse: https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html
2. Baixe o **ojdbc11.jar** (para Java 11+) ou **ojdbc8.jar** (para Java 8)
3. Versão recomendada: **ojdbc11-23.4.0.24.05.jar** ou similar

Guarde esse arquivo `.jar` em um local fácil, por exemplo: `C:\libs\ojdbc11.jar`

## 6. Passo 4: Criar o projeto no Eclipse/NetBeans/VS Code

### 6.1 Criar um novo projeto Java

- **Eclipse:** File → New → Java Project → Nome: `CadastroUsuarioOracle`
- **IntelliJ:** New Project → Java → Nome: `CadastroUsuarioOracle`
- **VS Code:** Crie uma pasta e abra com o VS Code

### 6.2 Criar a estrutura de pastas

```
CadastroUsuarioOracle/
├── src/
│   ├── Main.java              (classe principal)
│   ├── ConexaoBD.java         (conexão com o Oracle)
│   ├── Usuario.java           (objeto usuário)
│   ├── UsuarioDAO.java        (salvar no banco)
│   └── Formulario.java        (interface gráfica)
└── lib/
    └── ojdbc11.jar            (driver Oracle)
```

## 7. Passo 5: Adicionar o driver ao projeto

### No Eclipse:
- Clique com botão direito no projeto → Build Path → Configure Build Path
- Aba Libraries → Add External JARs
- Selecione o arquivo `ojdbc11.jar`
- Clique Apply and Close

### No IntelliJ:
- File → Project Structure → Modules → Dependencies
- Clique no `+` → JARs or Directories
- Selecione o arquivo `.jar`

### No VS Code:
```json
// .vscode/settings.json
{
    "java.project.referencedLibraries": [
        "lib/**/*.jar"
    ]
}
```

## 8. Passo 6: Criar a classe de conexão com o banco

### 8.1 Criar a classe ConexaoBD.java

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por criar a conexão com o Oracle Database
 */
public class ConexaoBD {
    
    // Informações do Oracle (mude conforme sua instalação)
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USUARIO = "cadastro_user";
    private static final String SENHA = "cadastro123";
    
    /**
     * Retorna uma conexão com o banco de dados Oracle
     * @return Connection
     * @throws SQLException se não conseguir conectar
     */
    public static Connection getConexao() throws SQLException {
        try {
            // Registrar o driver Oracle (necessário para versões antigas do Java)
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC Oracle não encontrado!");
            System.err.println("Verifique se o ojdbc.jar foi adicionado ao projeto.");
            e.printStackTrace();
        }
        
        // Criar e retornar a conexão
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
    /**
     * Método de teste rápido para verificar a conexão
     */
    public static void main(String[] args) {
        try {
            Connection conn = getConexao();
            System.out.println("✅ Conexão com Oracle estabelecida com sucesso!");
            System.out.println("Versão do Oracle: " + conn.getMetaData().getDatabaseProductVersion());
            conn.close();
            System.out.println("Conexão fechada.");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ao Oracle!");
            System.err.println("Mensagem: " + e.getMessage());
            System.err.println("Código do erro: " + e.getErrorCode());
        }
    }
}
```

### 8.2 Diferenças importantes da URL de conexão Oracle

| Oracle XE | Formato |
|-----------|---------|
| **Thin (recomendado)** | `jdbc:oracle:thin:@localhost:1521:XE` |
| **Com Service Name** | `jdbc:oracle:thin:@//localhost:1521/XE` |
| **Com TNS** | `jdbc:oracle:thin:@(DESCRIPTION=...)` |

### 8.3 Testar a conexão

Execute a classe `ConexaoBD.java`. Se tudo estiver certo:

```
✅ Conexão com Oracle estabelecida com sucesso!
Versão do Oracle: Oracle Database 21c Express Edition Release 21.0.0.0.0
Conexão fechada.
```

## 9. Passo 7: Criar o formulário (interface gráfica)

### 9.1 Criar a classe Formulario.java

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Formulário para cadastro de usuários com Oracle
 */
public class Formulario extends JFrame {
    
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtIdade;
    private JButton btnSalvar;
    private JLabel lblMensagem;
    
    public Formulario() {
        setTitle("Cadastro de Usuários (Oracle)");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Campo Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(new JLabel("Nome:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        txtNome = new JTextField(20);
        painel.add(txtNome, gbc);
        
        // Campo Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtEmail = new JTextField(20);
        painel.add(txtEmail, gbc);
        
        // Campo Idade
        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(new JLabel("Idade:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        txtIdade = new JTextField(5);
        painel.add(txtIdade, gbc);
        
        // Botão Salvar
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        btnSalvar = new JButton("SALVAR");
        btnSalvar.setBackground(new Color(0, 120, 215));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        painel.add(btnSalvar, gbc);
        
        // Label para mensagens
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        lblMensagem = new JLabel("Preencha os dados acima", SwingConstants.CENTER);
        lblMensagem.setForeground(Color.GRAY);
        painel.add(lblMensagem, gbc);
        
        add(painel, BorderLayout.CENTER);
        
        btnSalvar.addActionListener(e -> salvarUsuario());
    }
    
    private void salvarUsuario() {
        // Implementaremos depois
        lblMensagem.setText("Processando...");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Formulario().setVisible(true));
    }
}
```

## 10. Passo 8: Criar a classe para salvar os dados

### 10.1 Criar a classe Usuario.java

```java
/**
 * Classe que representa um usuário (modelo de dados)
 */
public class Usuario {
    
    private int id;
    private String nome;
    private String email;
    private int idade;
    
    public Usuario() {}
    
    public Usuario(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idade=" + idade +
                '}';
    }
}
```

### 10.2 Criar a classe UsuarioDAO.java

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe responsável por salvar usuários no Oracle
 * IMPORTANTE: O Oracle não tem AUTO_INCREMENT, usamos SEQUENCE + TRIGGER
 */
public class UsuarioDAO {
    
    /**
     * Salva um usuário no banco de dados Oracle
     * Não precisamos incluir o ID, pois a trigger irá gerar automaticamente
     */
    public boolean salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, idade) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Substituir os "?" pelos valores do usuário
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getIdade());
            
            // Executar o comando SQL
            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro Oracle ao salvar usuário:");
            System.err.println("Código: " + e.getErrorCode());
            System.err.println("Mensagem: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Método de teste para o DAO
     */
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = new Usuario("Maria Oliveira", "maria@email.com", 28);
        
        if (dao.salvar(usuario)) {
            System.out.println("✅ Usuário salvo no Oracle com sucesso!");
        } else {
            System.out.println("❌ Erro ao salvar usuário no Oracle.");
        }
    }
}
```

## 11. Passo 9: Juntar tudo no formulário

### 11.1 Atualizar o método salvarUsuario() no Formulario.java

Substitua o método `salvarUsuario()` por:

```java
private void salvarUsuario() {
    // 1. Pegar os dados do formulário
    String nome = txtNome.getText().trim();
    String email = txtEmail.getText().trim();
    String idadeStr = txtIdade.getText().trim();
    
    // 2. Validar os campos
    if (nome.isEmpty() || email.isEmpty() || idadeStr.isEmpty()) {
        lblMensagem.setForeground(Color.RED);
        lblMensagem.setText("❌ Todos os campos são obrigatórios!");
        return;
    }
    
    // 3. Converter idade para número
    int idade;
    try {
        idade = Integer.parseInt(idadeStr);
    } catch (NumberFormatException e) {
        lblMensagem.setForeground(Color.RED);
        lblMensagem.setText("❌ Idade deve ser um número válido!");
        return;
    }
    
    // 4. Validar email (formato básico)
    if (!email.contains("@") || !email.contains(".")) {
        lblMensagem.setForeground(Color.RED);
        lblMensagem.setText("❌ Email inválido!");
        return;
    }
    
    // 5. Criar o objeto Usuario
    Usuario usuario = new Usuario(nome, email, idade);
    
    // 6. Salvar no Oracle
    UsuarioDAO dao = new UsuarioDAO();
    boolean salvou = dao.salvar(usuario);
    
    // 7. Mostrar mensagem de sucesso ou erro
    if (salvou) {
        lblMensagem.setForeground(new Color(0, 150, 0));
        lblMensagem.setText("✅ Usuário cadastrado com sucesso no Oracle!");
        
        // Limpar os campos para um novo cadastro
        txtNome.setText("");
        txtEmail.setText("");
        txtIdade.setText("");
        txtNome.requestFocus();
    } else {
        lblMensagem.setForeground(Color.RED);
        lblMensagem.setText("❌ Erro ao salvar. Email já existe ou erro no banco.");
    }
}
```

## 12. Código completo

### 📁 Arquivo 1: ConexaoBD.java

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USUARIO = "cadastro_user";
    private static final String SENHA = "cadastro123"; // Sua senha
    
    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC Oracle não encontrado!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
    public static void main(String[] args) {
        try {
            Connection conn = getConexao();
            System.out.println("✅ Conexão com Oracle OK!");
            System.out.println("Versão: " + conn.getMetaData().getDatabaseProductVersion());
            conn.close();
        } catch (SQLException e) {
            System.err.println("❌ Erro: " + e.getMessage());
        }
    }
}
```

### 📁 Arquivo 2: Usuario.java

```java
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private int idade;
    
    public Usuario() {}
    
    public Usuario(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
}
```

### 📁 Arquivo 3: UsuarioDAO.java

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
    
    public boolean salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, idade) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getIdade());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro Oracle: " + e.getMessage());
            return false;
        }
    }
}
```

### 📁 Arquivo 4: Formulario.java

```java
import javax.swing.*;
import java.awt.*;

public class Formulario extends JFrame {
    
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtIdade;
    private JButton btnSalvar;
    private JLabel lblMensagem;
    
    public Formulario() {
        setTitle("Cadastro de Usuários (Oracle)");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Nome
        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        txtNome = new JTextField(20);
        painel.add(txtNome, gbc);
        
        // Email
        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        painel.add(txtEmail, gbc);
        
        // Idade
        gbc.gridx = 0; gbc.gridy = 2;
        painel.add(new JLabel("Idade:"), gbc);
        gbc.gridx = 1;
        txtIdade = new JTextField(5);
        painel.add(txtIdade, gbc);
        
        // Botão
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        btnSalvar = new JButton("SALVAR");
        btnSalvar.setBackground(new Color(0, 120, 215));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        painel.add(btnSalvar, gbc);
        
        // Mensagem
        gbc.gridx = 0; gbc.gridy = 4;
        lblMensagem = new JLabel("Preencha os dados", SwingConstants.CENTER);
        lblMensagem.setForeground(Color.GRAY);
        painel.add(lblMensagem, gbc);
        
        add(painel, BorderLayout.CENTER);
        
        btnSalvar.addActionListener(e -> salvarUsuario());
    }
    
    private void salvarUsuario() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String idadeStr = txtIdade.getText().trim();
        
        if (nome.isEmpty() || email.isEmpty() || idadeStr.isEmpty()) {
            lblMensagem.setForeground(Color.RED);
            lblMensagem.setText("❌ Todos os campos são obrigatórios!");
            return;
        }
        
        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
        } catch (NumberFormatException e) {
            lblMensagem.setForeground(Color.RED);
            lblMensagem.setText("❌ Idade deve ser um número!");
            return;
        }
        
        Usuario usuario = new Usuario(nome, email, idade);
        UsuarioDAO dao = new UsuarioDAO();
        
        if (dao.salvar(usuario)) {
            lblMensagem.setForeground(new Color(0, 150, 0));
            lblMensagem.setText("✅ Usuário cadastrado com sucesso no Oracle!");
            txtNome.setText("");
            txtEmail.setText("");
            txtIdade.setText("");
            txtNome.requestFocus();
        } else {
            lblMensagem.setForeground(Color.RED);
            lblMensagem.setText("❌ Erro. Email já cadastrado ou erro no banco.");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Formulario().setVisible(true));
    }
}
```

## 13. Erros comuns e como resolver

### Configuração do Oracle

| Erro | Causa | Solução |
|------|-------|---------|
| **ORA-12505** | SID incorreto | Use `XE` ou verifique no listener.ora |
| **ORA-12514** | Service Name incorreto | Tente `jdbc:oracle:thin:@//localhost:1521/XE` |
| **ORA-01017** | Usuário/senha inválidos | Verifique o usuário `cadastro_user` e senha |
| **ORA-01918** | Usuário não existe | Execute `CREATE USER cadastro_user...` |

### Conexão

| Erro | Causa | Solução |
|------|-------|---------|
| **ClassNotFoundException** | Driver JDBC não adicionado | Adicione `ojdbc11.jar` ao classpath |
| **Connection refused** | Oracle não está rodando | Inicie o serviço Oracle (Windows: serviços.msc) |
| **ORA-12541: TNS:no listener** | Listener não iniciado | Execute `lsnrctl start` no terminal |
| **IO Error: Network adapter** | Firewall bloqueando | Libere a porta 1521 |

### Tabela/Inserts

| Erro | Causa | Solução |
|------|-------|---------|
| **ORA-00942: table or view does not exist** | Tabela não existe | Execute `CREATE TABLE usuarios...` |
| **ORA-00001: unique constraint violated** | Email duplicado | Use um email diferente |
| **ORA-02289: sequence does not exist** | Sequence não criada | Execute `CREATE SEQUENCE seq_usuarios` |
| **ORA-04098: trigger is invalid** | Trigger com erro | Recrie a trigger corretamente |

### Diferenças importantes entre MySQL e Oracle

| Característica | MySQL | Oracle |
|----------------|-------|--------|
| **Auto incremento** | `AUTO_INCREMENT` | `SEQUENCE` + `TRIGGER` |
| **String** | `VARCHAR` | `VARCHAR2` |
| **Limite nome tabela** | 64 caracteres | 30 caracteres |
| **Porta padrão** | 3306 | 1521 |
| **URL JDBC** | `jdbc:mysql://host:porta/banco` | `jdbc:oracle:thin:@host:porta:SID` |
| **Driver** | `com.mysql.cj.jdbc.Driver` | `oracle.jdbc.driver.OracleDriver` |
| **Banco de dados** | `CREATE DATABASE` | Usuário/schema |

## Resumo do fluxo de funcionamento com Oracle

```
1. Usuário digita os dados no formulário
2. Clica no botão SALVAR
3. O programa pega os dados dos campos
4. Cria um objeto Usuario
5. Chama UsuarioDAO.salvar(usuario)
6. O DAO abre uma conexão com o Oracle (ConexaoBD)
7. Prepara e executa o comando INSERT
8. A TRIGGER do Oracle gera automaticamente o ID usando a SEQUENCE
9. Retorna true/false para o formulário
10. O formulário mostra mensagem de sucesso ou erro
```

## Script SQL completo para o Oracle

Salve como `setup_oracle.sql` e execute como SYSTEM:

```sql
-- Criar usuário da aplicação
CREATE USER cadastro_user IDENTIFIED BY cadastro123;
GRANT CONNECT, RESOURCE TO cadastro_user;
ALTER USER cadastro_user QUOTA UNLIMITED ON USERS;

-- Conectar como o novo usuário
CONNECT cadastro_user/cadastro123@localhost:1521/XE;

-- Criar tabela
CREATE TABLE usuarios (
    id NUMBER PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL UNIQUE,
    idade NUMBER
);

-- Criar sequence para auto-incremento
CREATE SEQUENCE seq_usuarios
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Criar trigger para auto-incrementar o ID
CREATE OR REPLACE TRIGGER trg_usuarios_id
BEFORE INSERT ON usuarios
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN
        SELECT seq_usuarios.NEXTVAL INTO :NEW.id FROM DUAL;
    END IF;
END;
/

-- Verificar tabela criada
SELECT table_name FROM user_tables;

-- Testar insert
INSERT INTO usuarios (nome, email, idade) VALUES ('Teste Oracle', 'teste@oracle.com', 25);
COMMIT;

-- Verificar
SELECT * FROM usuarios;
```
