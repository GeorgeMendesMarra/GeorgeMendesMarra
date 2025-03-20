import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class CadastroClientes extends Application {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private TextField nomeField, emailField, telefoneField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Conectar ao banco de dados
        conectarBanco();

        // Criar interface JavaFX
        primaryStage.setTitle("Cadastro de Clientes");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Labels e campos de texto para inserir informações do cliente
        Label lblNome = new Label("Nome:");
        GridPane.setConstraints(lblNome, 0, 0);
        nomeField = new TextField();
        GridPane.setConstraints(nomeField, 1, 0);

        Label lblEmail = new Label("Email:");
        GridPane.setConstraints(lblEmail, 0, 1);
        emailField = new TextField();
        GridPane.setConstraints(emailField, 1, 1);

        Label lblTelefone = new Label("Telefone:");
        GridPane.setConstraints(lblTelefone, 0, 2);
        telefoneField = new TextField();
        GridPane.setConstraints(telefoneField, 1, 2);

        // Botões para adicionar, exibir e deletar clientes
        Button btnAdicionar = new Button("Adicionar Cliente");
        GridPane.setConstraints(btnAdicionar, 0, 3);
        btnAdicionar.setOnAction(e -> adicionarCliente());

        Button btnExibir = new Button("Exibir Clientes");
        GridPane.setConstraints(btnExibir, 1, 3);
        btnExibir.setOnAction(e -> exibirClientes());

        Label lblId = new Label("ID do Cliente:");
        GridPane.setConstraints(lblId, 0, 4);
        TextField idField = new TextField();
        GridPane.setConstraints(idField, 1, 4);

        Button btnDeletar = new Button("Deletar Cliente");
        GridPane.setConstraints(btnDeletar, 0, 5);
        btnDeletar.setOnAction(e -> {
            int clienteId = Integer.parseInt(idField.getText());
            deletarCliente(clienteId);
        });

        grid.getChildren().addAll(lblNome, nomeField, lblEmail, emailField, lblTelefone, telefoneField,
                btnAdicionar, btnExibir, lblId, idField, btnDeletar);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para adicionar cliente ao banco de dados
    private void adicionarCliente() {
        try {
            String nome = nomeField.getText();
            String email = emailField.getText();
            String telefone = telefoneField.getText();

            String query = "INSERT INTO clientes (nome, email, telefone) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, telefone);

            preparedStatement.executeUpdate();
            System.out.println("Cliente adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para exibir os clientes cadastrados
    private void exibirClientes() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clientes");

            while (resultSet.next()) {
                System.out.println(
                        "ID: " + resultSet.getInt("id") +
                        ", Nome: " + resultSet.getString("nome") +
                        ", Email: " + resultSet.getString("email") +
                        ", Telefone: " + resultSet.getString("telefone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar cliente do banco de dados
    private void deletarCliente(int clienteId) {
        try {
            String query = "DELETE FROM clientes WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clienteId);

            int resultado = preparedStatement.executeUpdate();
            if (resultado == 1) {
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para conectar ao banco de dados MySQL
    private void conectarBanco() {
        try {
            // Coloque as informações de conexão do seu banco de dados MySQL
            String url = "jdbc:mysql://localhost:3306/nome_do_seu_banco";
            String username = "seu_usuario";
            String password = "sua_senha";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão com o banco de dados estabelecida.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para desconectar do banco de dados
    @Override
    public void stop() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexão com o banco de dados fechada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
