# Índice

1. [O que vamos construir](#1-o-que-vamos-construir)
2. [Ferramentas necessárias](#2-ferramentas-necessárias)
3. [Passo 1: Instalar e configurar o PostgreSQL](#3-passo-1-instalar-e-configurar-o-postgresql)
4. [Passo 2: Criar o banco de dados e a tabela](#4-passo-2-criar-o-banco-de-dados-e-a-tabela)
5. [Passo 3: Baixar o driver JDBC do PostgreSQL](#5-passo-3-baixar-o-driver-jdbc-do-postgresql)
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

Vamos criar um programa Java com uma janela simples para cadastrar usuários (nome, email e idade). Ao clicar em "Salvar", os dados vão para um banco de dados **PostgreSQL**.

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
| **PostgreSQL Server** | Banco de dados open-source | [postgresql.org/download](https://www.postgresql.org/download/) |
| **pgAdmin** | Interface visual para PostgreSQL (opcional) | Incluído na instalação do PostgreSQL |
| IDE Java | Programas para escrever código | Eclipse, IntelliJ IDEA ou VS Code |
| **JDBC Driver PostgreSQL** | Biblioteca que conecta Java ao PostgreSQL | [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/download/) |

## 3. Passo 1: Instalar e configurar o PostgreSQL

### 3.1 Instalar o PostgreSQL

Baixe e instale o PostgreSQL (versão 15 ou 16 recomendada).

**Durante a instalação, você definirá:**
- **Senha do usuário postgres** (administrador) - **GUARDE ESSA SENHA!**
- Porta padrão: **5432**
- Exemplo de senha: `123456` (apenas para testes)

### 3.2 Verificar se o PostgreSQL está rodando

Abra o **pgAdmin** ou o **Prompt de Comando** (psql):

```bash
psql -U postgres -h localhost -p 5432
# Digite a senha que você criou
```

Se aparecer `postgres=#` o banco está funcionando.

### 3.3 Configurações importantes do PostgreSQL

- **Porta padrão:** `5432`
- **Usuário admin:** `postgres`
- **Banco de dados padrão:** `postgres`

## 4. Passo 2: Criar o banco de dados e a tabela

### 4.1 Criar o banco de dados

No pgAdmin ou no terminal psql, execute:

```sql
-- Criar o banco de dados
CREATE DATABASE cadastro_db;

-- Conectar ao novo banco (no psql use: \c cadastro_db)
\c cadastro_db;
```

### 4.2 Criar a tabela de usuários

```sql
-- Criar a tabela (PostgreSQL usa SERIAL para auto-incremento)
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    idade INTEGER
);
```

**Explicação:**
- `id SERIAL PRIMARY KEY` - auto-incremento (como o AUTO_INCREMENT do MySQL)
- `VARCHAR(100)` - texto de até 100 caracteres
- `NOT NULL` - campo obrigatório
- `UNIQUE` - não permite emails duplicados
- `INTEGER` - número inteiro

### 4.3 Verificar se a tabela foi criada

```sql
-- Listar todas as tabelas
\dt

-- Ver a estrutura da tabela
\d usuarios

-- Ou usando SQL
SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';
```

## 5. Passo 3: Baixar o driver JDBC do PostgreSQL

### 5.1 O que é o JDBC Driver PostgreSQL?

É uma biblioteca (arquivo `.jar`) que permite o Java se conectar ao PostgreSQL.

### 5.2 Baixar o arquivo

1. Acesse: https://jdbc.postgresql.org/download/
2. Baixe a versão mais recente (ex: **postgresql-42.7.3.jar** para Java 8+)
3. Versão recomendada: `postgresql-42.7.3.jar` ou superior

Guarde esse arquivo `.jar` em um local fácil, por exemplo: `C:\libs\postgresql-42.7.3.jar`

## 6. Passo 4: Criar o projeto no Eclipse/NetBeans/VS Code

### 6.1 Criar um novo projeto Java

- **Eclipse:** File → New → Java Project → Nome: `CadastroUsuarioPostgreSQL`
- **IntelliJ:** New Project → Java → Nome: `CadastroUsuarioPostgreSQL`
- **VS Code:** Crie uma pasta e abra com o VS Code

### 6.2 Criar a estrutura de pastas

```
CadastroUsuarioPostgreSQL/
├── src/
│   ├── Main.java              (classe principal - opcional)
│   ├── ConexaoBD.java         (conexão com o PostgreSQL)
│   ├── Usuario.java           (objeto usuário)
│   ├── UsuarioDAO.java        (salvar no banco)
│   └── Formulario.java        (interface gráfica)
└── lib/
    └── postgresql-42.7.3.jar  (driver PostgreSQL)
```

## 7. Passo 5: Adicionar o driver ao projeto

### No Eclipse:
- Clique com botão direito no projeto → Build Path → Configure Build Path
- Aba Libraries → Add External JARs
- Selecione o arquivo `postgresql-42.7.3.jar`
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
 * Classe responsável por criar a conexão com o PostgreSQL
 */
public class ConexaoBD {
    
    // Informações do PostgreSQL (mude conforme sua instalação)
    private static final String URL = "jdbc:postgresql://localhost:5432/cadastro_db";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "123456"; // Use a senha que você definiu
    
    /**
     * Retorna uma conexão com o banco de dados PostgreSQL
     * @return Connection
     * @throws SQLException se não conseguir conectar
     */
    public static Connection getConexao() throws SQLException {
        try {
            // Registrar o driver PostgreSQL (para versões mais antigas do Java)
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC PostgreSQL não encontrado!");
            System.err.println("Verifique se o postgresql.jar foi adicionado ao projeto.");
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
            System.out.println("✅ Conexão com PostgreSQL estabelecida com sucesso!");
            System.out.println("Versão do PostgreSQL: " + conn.getMetaData().getDatabaseProductVersion());
            conn.close();
            System.out.println("Conexão fechada.");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ao PostgreSQL!");
            System.err.println("Mensagem: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
        }
    }
}
```

### 8.2 Formato da URL de conexão PostgreSQL

| Formato | Exemplo |
|---------|---------|
| **Padrão** | `jdbc:postgresql://localhost:5432/cadastro_db` |
| **Com parâmetros** | `jdbc:postgresql://localhost:5432/cadastro_db?ssl=false` |
| **Com usuário/senha na URL** | `jdbc:postgresql://localhost:5432/cadastro_db?user=postgres&password=123456` |

### 8.3 Testar a conexão

Execute a classe `ConexaoBD.java`. Se tudo estiver certo:

```
✅ Conexão com PostgreSQL estabelecida com sucesso!
Versão do PostgreSQL: 15.4
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
 * Formulário para cadastro de usuários com PostgreSQL
 */
public class Formulario extends JFrame {
    
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtIdade;
    private JButton btnSalvar;
    private JLabel lblMensagem;
    
    public Formulario() {
        setTitle("Cadastro de Usuários (PostgreSQL)");
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
        
        // Configurar ação do botão (será implementada depois)
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
 * Classe responsável por salvar usuários no PostgreSQL
 * PostgreSQL usa SERIAL para auto-incremento, então não precisamos incluir o ID
 */
public class UsuarioDAO {
    
    /**
     * Salva um usuário no banco de dados PostgreSQL
     * @param usuario Objeto com os dados do usuário
     * @return true se salvou com sucesso, false caso contrário
     */
    public boolean salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, idade) VALUES (?, ?, ?)";
        
        // Usamos try-with-resources para garantir que a conexão será fechada
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Substituir os "?" pelos valores do usuário
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getIdade());
            
            // Executar o comando SQL
            int linhasAfetadas = stmt.executeUpdate();
            
            // Se inseriu pelo menos 1 linha, deu certo
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuário no PostgreSQL:");
            System.err.println("Mensagem: " + e.getMessage());
            System.err.println("Código do erro: " + e.getSQLState());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Método de teste para o DAO
     */
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = new Usuario("Carlos Souza", "carlos@email.com", 35);
        
        if (dao.salvar(usuario)) {
            System.out.println("✅ Usuário salvo no PostgreSQL com sucesso!");
        } else {
            System.out.println("❌ Erro ao salvar usuário no PostgreSQL.");
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
    
    // 3. Validar formato do email
    if (!email.contains("@") || !email.contains(".")) {
        lblMensagem.setForeground(Color.RED);
        lblMensagem.setText("❌ Email inválido!");
        return;
    }
    
    // 4. Converter idade para número
    int idade;
    try {
        idade = Integer.parseInt(idadeStr);
        if (idade <= 0 || idade > 150) {
            lblMensagem.setForeground(Color.RED);
            lblMensagem.setText("❌ Idade deve ser entre 1 e 150!");
            return;
        }
    } catch (NumberFormatException e) {
        lblMensagem.setForeground(Color.RED);
        lblMensagem.setText("❌ Idade deve ser um número válido!");
        return;
    }
    
    // 5. Criar o objeto Usuario
    Usuario usuario = new Usuario(nome, email, idade);
    
    // 6. Salvar no banco de dados
    UsuarioDAO dao = new UsuarioDAO();
    boolean salvou = dao.salvar(usuario);
    
    // 7. Mostrar mensagem de sucesso ou erro
    if (salvou) {
        lblMensagem.setForeground(new Color(0, 150, 0));
        lblMensagem.setText("✅ Usuário cadastrado com sucesso no PostgreSQL!");
        
        // Limpar os campos para um novo cadastro
        txtNome.setText("");
        txtEmail.setText("");
        txtIdade.setText("");
        txtNome.requestFocus();
    } else {
        lblMensagem.setForeground(Color.RED);
        lblMensagem.setText("❌ Erro ao salvar. Verifique se o email já não está cadastrado.");
    }
}
```

### 11.2 Verificar se todos os imports estão corretos

No topo do arquivo `Formulario.java`, deve ter:

```java
import javax.swing.*;
import java.awt.*;
```

## 12. Código completo

### 📁 Arquivo 1: ConexaoBD.java

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/cadastro_db";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "123456"; // MUDE PARA SUA SENHA
    
    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC PostgreSQL não encontrado!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
    public static void main(String[] args) {
        try {
            Connection conn = getConexao();
            System.out.println("✅ Conexão com PostgreSQL OK!");
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
    
    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nome='" + nome + "', email='" + email + "', idade=" + idade + "}";
    }
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
            System.err.println("Erro PostgreSQL: " + e.getMessage());
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
        setTitle("Cadastro de Usuários (PostgreSQL)");
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
        
        if (!email.contains("@") || !email.contains(".")) {
            lblMensagem.setForeground(Color.RED);
            lblMensagem.setText("❌ Email inválido!");
            return;
        }
        
        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
            if (idade <= 0 || idade > 150) {
                lblMensagem.setForeground(Color.RED);
                lblMensagem.setText("❌ Idade deve ser entre 1 e 150!");
                return;
            }
        } catch (NumberFormatException e) {
            lblMensagem.setForeground(Color.RED);
            lblMensagem.setText("❌ Idade deve ser um número!");
            return;
        }
        
        Usuario usuario = new Usuario(nome, email, idade);
        UsuarioDAO dao = new UsuarioDAO();
        
        if (dao.salvar(usuario)) {
            lblMensagem.setForeground(new Color(0, 150, 0));
            lblMensagem.setText("✅ Usuário cadastrado com sucesso!");
            txtNome.setText("");
            txtEmail.setText("");
            txtIdade.setText("");
            txtNome.requestFocus();
        } else {
            lblMensagem.setForeground(Color.RED);
            lblMensagem.setText("❌ Erro ao salvar. Verifique o email.");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Formulario().setVisible(true));
    }
}
```

## 13. Erros comuns e como resolver

### Configuração do PostgreSQL

| Erro | Causa | Solução |
|------|-------|---------|
| **Connection refused** | PostgreSQL não está rodando | Inicie o serviço PostgreSQL |
| **FATAL: database "cadastro_db" does not exist** | Banco não foi criado | Execute `CREATE DATABASE cadastro_db;` |
| **FATAL: password authentication failed** | Senha incorreta | Verifique a senha do usuário postgres |
| **FATAL: no pg_hba.conf entry** | Configuração de autenticação | Edite pg_hba.conf e reinicie |

### Conexão

| Erro | Causa | Solução |
|------|-------|---------|
| **ClassNotFoundException** | Driver JDBC não adicionado | Adicione `postgresql-42.7.3.jar` ao classpath |
| **The connection attempt failed** | Porta errada ou serviço parado | Verifique se a porta 5432 está ouvindo |
| **Timeout** | Firewall bloqueando | Libere a porta 5432 no firewall |

### Tabela/Inserts

| Erro | Causa | Solução |
|------|-------|---------|
| **ERROR: relation "usuarios" does not exist** | Tabela não existe | Execute `CREATE TABLE usuarios...` |
| **ERROR: duplicate key value violates unique constraint** | Email duplicado | Use um email diferente |
| **ERROR: null value in column "nome"** | Campo obrigatório vazio | Preencha todos os campos |
| **ERROR: value too long for type character varying(100)** | Texto muito longo | Limite o tamanho do texto |

### Diferenças importantes entre SGBDs

| Característica | MySQL | Oracle | PostgreSQL |
|----------------|-------|--------|-------------|
| **Auto incremento** | `AUTO_INCREMENT` | `SEQUENCE` + `TRIGGER` | `SERIAL` ou `IDENTITY` |
| **String** | `VARCHAR` | `VARCHAR2` | `VARCHAR` |
| **Booleano** | `BOOLEAN` (8.0+) | Não tem (`NUMBER(1)`) | `BOOLEAN` |
| **Porta padrão** | 3306 | 1521 | 5432 |
| **URL JDBC** | `jdbc:mysql://host:porta/banco` | `jdbc:oracle:thin:@host:porta:SID` | `jdbc:postgresql://host:porta/banco` |
| **Driver** | `com.mysql.cj.jdbc.Driver` | `oracle.jdbc.driver.OracleDriver` | `org.postgresql.Driver` |
| **Case sensitive** | Depende do collation | Sim (nomes são maiúsculos) | Não (nomes são minúsculos) |

## Resumo do fluxo de funcionamento com PostgreSQL

```
1. Usuário digita os dados no formulário
2. Clica no botão SALVAR
3. O programa pega os dados dos campos
4. Valida os dados (campos preenchidos, email válido, idade válida)
5. Cria um objeto Usuario com esses dados
6. Chama UsuarioDAO.salvar(usuario)
7. O DAO abre uma conexão com o PostgreSQL (ConexaoBD)
8. Prepara e executa o comando INSERT
9. O PostgreSQL gera automaticamente o ID usando SERIAL
10. Retorna true/false para o formulário
11. O formulário mostra mensagem de sucesso ou erro
12. Limpa os campos para um novo cadastro
```

## Script SQL completo para PostgreSQL

Salve como `setup_postgresql.sql` e execute no pgAdmin ou psql:

```sql
-- Conectar como postgres e criar o banco
CREATE DATABASE cadastro_db;

-- Conectar ao novo banco
\c cadastro_db;

-- Criar a tabela de usuários
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    idade INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Verificar a tabela criada
\d usuarios

-- Inserir um registro de teste
INSERT INTO usuarios (nome, email, idade) 
VALUES ('Usuário Teste', 'teste@postgresql.com', 25);

-- Verificar o registro inserido
SELECT * FROM usuarios;

-- Criar um índice para busca por email (opcional, mas recomendado)
CREATE INDEX idx_usuarios_email ON usuarios(email);
```

## Vantagens do PostgreSQL para este projeto

1. **Gratuito e open-source** - Sem custos de licenciamento
2. **SERIAL é simples** - Auto-incremento fácil como no MySQL
3. **Excelente documentação** - Muita informação disponível
4. **Comunidade ativa** - Fácil encontrar ajuda
5. **Cross-platform** - Funciona em Windows, Linux e Mac
6. **JDBC driver maduro** - Estável e bem testado

