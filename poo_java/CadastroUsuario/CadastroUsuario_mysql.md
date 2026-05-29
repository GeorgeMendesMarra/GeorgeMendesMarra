## Índice
1. [O que vamos construir](#1-o-que-vamos-construir)
2. [Ferramentas necessárias](#2-ferramentas-necessárias)
3. [Passo 1: Instalar e configurar o MySQL](#3-passo-1-instalar-e-configurar-o-mysql)
4. [Passo 2: Criar o banco de dados e a tabela](#4-passo-2-criar-o-banco-de-dados-e-a-tabela)
5. [Passo 3: Baixar o driver JDBC do MySQL](#5-passo-3-baixar-o-driver-jdbc-do-mysql)
6. [Passo 4: Criar o projeto no Eclipse/NetBeans/VS Code](#6-passo-4-criar-o-projeto-no-eclipse-netbeans-vs-code)
7. [Passo 5: Adicionar o driver ao projeto](#7-passo-5-adicionar-o-driver-ao-projeto)
8. [Passo 6: Criar a classe de conexão com o banco](#8-passo-6-criar-a-classe-de-conexão-com-o-banco)
9. [Passo 7: Criar o formulário (interface gráfica)](#9-passo-7-criar-o-formulário-interface-gráfica)
10. [Passo 8: Criar a classe para salvar os dados](#10-passo-8-criar-a-classe-para-salvar-os-dados)
11. [Passo 9: Juntar tudo no formulário](#11-passo-9-juntar-tudo-no-formulário)
12. [Código completo](#12-código-completo)
13. [Erros comuns e como resolver](#13-erros-comuns-e-como-resolver)

---

## 1. O que vamos construir

Vamos criar um programa Java com uma janela simples para **cadastrar usuários** (nome, email e idade). Ao clicar em "Salvar", os dados vão para um banco de dados MySQL.

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

---

## 2. Ferramentas necessárias

| Ferramenta | O que é | Onde baixar |
|------------|---------|-------------|
| **Java JDK** | Kit de desenvolvimento Java | [Oracle Java](https://www.oracle.com/java/technologies/downloads/) ou [OpenJDK](https://adoptium.net/) |
| **MySQL Server** | Banco de dados | [MySQL Community Server](https://dev.mysql.com/downloads/mysql/) |
| **MySQL Workbench** | Interface visual para gerenciar o MySQL (opcional) | [MySQL Workbench](https://dev.mysql.com/downloads/workbench/) |
| **IDE Java** | Programas para escrever código | [Eclipse](https://www.eclipse.org/downloads/), [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) ou [VS Code](https://code.visualstudio.com/) |
| **JDBC Driver** | Biblioteca que conecta Java ao MySQL | [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) |

---

## 3. Passo 1: Instalar e configurar o MySQL

### 3.1 Instalar o MySQL Server

Baixe e instale o MySQL Server. Durante a instalação, você vai criar uma **senha para o usuário root** (administrador). **GUARDE ESSA SENHA!**

> Exemplo de senha: `123456` (apenas para teste, não use isso em produção!)

### 3.2 Verificar se o MySQL está rodando

Abra o **MySQL Workbench** ou o **Prompt de Comando** e conecte:

```bash
mysql -u root -p
# Digite a senha que você criou
```

Se aparecer `mysql>` o banco está funcionando.

---

## 4. Passo 2: Criar o banco de dados e a tabela

### 4.1 Criar o banco de dados

No MySQL Workbench ou no terminal, execute:

```sql
-- Criar o banco de dados
CREATE DATABASE cadastro_db;

-- Usar este banco de dados
USE cadastro_db;
```

### 4.2 Criar a tabela de usuários

Agora vamos criar uma tabela para guardar os dados do formulário:

```sql
-- Criar a tabela
CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    idade INT
);
```

**Explicação:**
- `id`: número que identifica cada usuário (cresce automático)
- `nome`: nome do usuário (obrigatório - NOT NULL)
- `email`: email (obrigatório e único)
- `idade`: idade do usuário (opcional)

### 4.3 Verificar se a tabela foi criada

```sql
-- Mostrar todas as tabelas
SHOW TABLES;

-- Ver a estrutura da tabela
DESCRIBE usuarios;
```

---

## 5. Passo 3: Baixar o driver JDBC do MySQL

### 5.1 O que é o JDBC Driver?

É uma biblioteca (arquivo `.jar`) que permite o Java "conversar" com o MySQL. Sem ele, o Java não entende o MySQL.

### 5.2 Baixar o arquivo

1. Acesse: https://dev.mysql.com/downloads/connector/j/
2. Escolha **Platform Independent** (Independente de plataforma)
3. Baixe o arquivo `.zip`
4. Extraia e procure pelo arquivo **mysql-connector-j-X.X.XX.jar** (ex: `mysql-connector-j-9.5.0.jar`)

**Guarde esse arquivo .jar em um local fácil de lembrar**, por exemplo: `C:\libs\mysql-connector-j-9.5.0.jar`

---

## 6. Passo 4: Criar o projeto no Eclipse/NetBeans/VS Code

### 6.1 Criar um novo projeto Java

- **No Eclipse:** File → New → Java Project → Nome: `CadastroUsuario`
- **No IntelliJ:** New Project → Java → Nome: `CadastroUsuario`
- **No VS Code:** Crie uma pasta e abra com o VS Code

### 6.2 Criar a estrutura de pastas

```
CadastroUsuario/
├── src/
│   ├── Main.java              (classe principal)
│   ├── ConexaoBD.java         (conexão com o banco)
│   ├── Usuario.java           (objeto usuário)
│   ├── UsuarioDAO.java        (salvar no banco)
│   └── Formulario.java        (interface gráfica)
└── lib/
    └── mysql-connector-j-9.5.0.jar  (driver)
```

---

## 7. Passo 5: Adicionar o driver ao projeto

### 7.1 Como adicionar a biblioteca .jar

#### No Eclipse:
1. Clique com botão direito no projeto → `Build Path` → `Configure Build Path`
2. Aba `Libraries` → `Add External JARs`
3. Selecione o arquivo `mysql-connector-j-X.X.XX.jar`
4. Clique `Apply and Close`

#### No IntelliJ:
1. File → Project Structure → Modules → Dependencies
2. Clique no `+` → `JARs or Directories`
3. Selecione o arquivo `.jar`
4. OK

#### No VS Code:
1. Crie uma pasta `lib` no projeto
2. Copie o `.jar` para dentro da pasta `lib`
3. No arquivo `.vscode/settings.json` (ou no launch.json), adicione:

```json
"java.project.referencedLibraries": [
    "lib/**/*.jar"
]
```

---

## 8. Passo 6: Criar a classe de conexão com o banco

Agora vamos escrever o código Java que faz a ponte entre o programa e o MySQL.

### 8.1 Criar a classe `ConexaoBD.java`

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por criar a conexão com o banco de dados MySQL
 */
public class ConexaoBD {
    
    // Informações do banco de dados (mude conforme sua instalação)
    private static final String URL = "jdbc:mysql://localhost:3306/cadastro_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456"; // Use a senha que você definiu
    
    /**
     * Retorna uma conexão com o banco de dados
     * @return Connection
     * @throws SQLException se não conseguir conectar
     */
    public static Connection getConexao() throws SQLException {
        try {
            // Registrar o driver (para versões mais antigas do Java)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado! Verifique se o .jar foi adicionado ao projeto.");
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
            System.out.println("✅ Conexão com o banco de dados estabelecida com sucesso!");
            conn.close();
            System.out.println("Conexão fechada.");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ao banco de dados!");
            System.err.println("Mensagem: " + e.getMessage());
        }
    }
}
```

### 8.2 Testar a conexão

Execute a classe `ConexaoBD.java`. Se tudo estiver certo, você verá:

```
✅ Conexão com o banco de dados estabelecida com sucesso!
Conexão fechada.
```

**Se der erro:**
- Verifique se o MySQL está rodando
- Confira o usuário (`root`) e a senha
- Confira o nome do banco (`cadastro_db`)

---

## 9. Passo 7: Criar o formulário (interface gráfica)

Vamos usar **Swing**, que já vem com o Java (não precisa instalar nada extra).

### 9.1 Criar a classe `Formulario.java`

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Formulário para cadastro de usuários
 */
public class Formulario extends JFrame {
    
    // Componentes da interface
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtIdade;
    private JButton btnSalvar;
    private JLabel lblMensagem;
    
    public Formulario() {
        // Configurar a janela
        setTitle("Cadastro de Usuários");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centralizar na tela
        setLayout(new BorderLayout());
        
        // Criar o painel principal
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre componentes
        
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
        
        // Configurar ação do botão Salvar (vamos implementar depois)
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarUsuario();
            }
        });
    }
    
    /**
     * Método que será chamado ao clicar no botão Salvar
     * (Vamos implementar depois de criar o DAO)
     */
    private void salvarUsuario() {
        // TODO: Implementar após criar UsuarioDAO
        lblMensagem.setText("Processando...");
    }
    
    /**
     * Método principal - inicia o programa
     */
    public static void main(String[] args) {
        // Garantir que a interface será criada na thread correta
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }
}
```

### 9.2 Testar o formulário

Execute `Formulario.java`. Você deve ver a janela com os campos.

---

## 10. Passo 8: Criar a classe para salvar os dados

Agora vamos criar duas classes:
1. **Usuario.java** - representa um usuário (objeto)
2. **UsuarioDAO.java** - salva o usuário no banco (DAO = Data Access Object)

### 10.1 Criar a classe `Usuario.java`

```java
/**
 * Classe que representa um usuário (modelo de dados)
 */
public class Usuario {
    
    private int id;
    private String nome;
    private String email;
    private int idade;
    
    // Construtor vazio
    public Usuario() {}
    
    // Construtor com dados
    public Usuario(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getIdade() {
        return idade;
    }
    
    public void setIdade(int idade) {
        this.idade = idade;
    }
    
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

### 10.2 Criar a classe `UsuarioDAO.java`

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe responsável por salvar usuários no banco de dados
 * DAO = Data Access Object (Objeto de Acesso a Dados)
 */
public class UsuarioDAO {
    
    /**
     * Salva um usuário no banco de dados
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
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Método de teste para o DAO
     */
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = new Usuario("João Silva", "joao@email.com", 30);
        
        if (dao.salvar(usuario)) {
            System.out.println("✅ Usuário salvo com sucesso!");
        } else {
            System.out.println("❌ Erro ao salvar usuário.");
        }
    }
}
```

### 10.3 Explicação do código DAO

| Linha | Explicação |
|-------|------------|
| `String sql = "INSERT INTO usuarios..."` | Comando SQL para inserir dados |
| `ConexaoBD.getConexao()` | Pega a conexão com o banco |
| `conn.prepareStatement(sql)` | Prepara o comando para ser executado |
| `stmt.setString(1, usuario.getNome())` | Coloca o valor do nome no primeiro `?` |
| `stmt.setString(2, usuario.getEmail())` | Coloca o email no segundo `?` |
| `stmt.setInt(3, usuario.getIdade())` | Coloca a idade no terceiro `?` |
| `stmt.executeUpdate()` | Executa o INSERT no banco |
| `linhasAfetadas > 0` | Se inseriu algum registro, deu certo |

---

## 11. Passo 9: Juntar tudo no formulário

Agora vamos completar o método `salvarUsuario()` no `Formulario.java`.

### 11.1 Atualizar a classe `Formulario.java`

Substitua o método `salvarUsuario()` pelo código abaixo:

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
    
    // 4. Criar o objeto Usuario
    Usuario usuario = new Usuario(nome, email, idade);
    
    // 5. Salvar no banco de dados
    UsuarioDAO dao = new UsuarioDAO();
    boolean salvou = dao.salvar(usuario);
    
    // 6. Mostrar mensagem de sucesso ou erro
    if (salvou) {
        lblMensagem.setForeground(new Color(0, 150, 0));
        lblMensagem.setText("✅ Usuário cadastrado com sucesso!");
        
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

### 11.2 Adicionar import se necessário

No topo do arquivo `Formulario.java`, adicione:

```java
import java.awt.Color;
```

---

## 12. Código completo

Vou reunir todos os arquivos para você copiar.

### 📁 Arquivo 1: `ConexaoBD.java`

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    
    private static final String URL = "jdbc:mysql://localhost:3306/cadastro_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456"; // MUDE PARA SUA SENHA
    
    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
    public static void main(String[] args) {
        try {
            Connection conn = getConexao();
            System.out.println("✅ Conexão OK!");
            conn.close();
        } catch (SQLException e) {
            System.err.println("❌ Erro: " + e.getMessage());
        }
    }
}
```

### 📁 Arquivo 2: `Usuario.java`

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
    
    // Getters e Setters
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

### 📁 Arquivo 3: `UsuarioDAO.java`

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
            System.err.println("Erro ao salvar: " + e.getMessage());
            return false;
        }
    }
}
```

### 📁 Arquivo 4: `Formulario.java`

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formulario extends JFrame {
    
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtIdade;
    private JButton btnSalvar;
    private JLabel lblMensagem;
    
    public Formulario() {
        setTitle("Cadastro de Usuários");
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
        
        // Ação do botão
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

---

## 13. Erros comuns e como resolver

| Erro | Causa | Solução |
|------|-------|---------|
| `ClassNotFoundException` | Driver JDBC não foi adicionado ao projeto | Adicione o arquivo `.jar` ao classpath |
| `Access denied for user 'root'` | Senha do MySQL está errada | Verifique a senha usada na instalação |
| `Unknown database 'cadastro_db'` | Banco de dados não foi criado | Execute `CREATE DATABASE cadastro_db;` |
| `Table 'cadastro_db.usuarios' doesn't exist` | Tabela não foi criada | Execute `CREATE TABLE usuarios...` |
| `Duplicate entry 'email' for key` | Email já está cadastrado | Use um email diferente |
| `Connection refused` | MySQL não está rodando | Inicie o serviço do MySQL |
| `Communications link failure` | Firewall bloqueando | Libere a porta 3306 ou desative o firewall (apenas testes) |

---

## Resumo do fluxo de funcionamento

```
1. Usuário digita os dados no formulário
2. Clica no botão SALVAR
3. O programa pega os dados dos campos (txtNome, txtEmail, txtIdade)
4. Cria um objeto Usuario com esses dados
5. Chama UsuarioDAO.salvar(usuario)
6. O DAO abre uma conexão com o banco (ConexaoBD)
7. Prepara e executa o comando INSERT
8. Retorna true/false para o formulário
9. O formulário mostra mensagem de sucesso ou erro
```

---
