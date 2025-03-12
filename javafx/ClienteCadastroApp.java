import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteCadastroApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Cadastro de Clientes");

        TextField nomeField = new TextField();
        TextField emailField = new TextField();
        TextField telefoneField = new TextField();
        TextField enderecoField = new TextField();
        TextField cidadeField = new TextField();

        Button cadastrarButton = new Button("Cadastrar");

        VBox vbox = new VBox(10, 
            new Label("Nome:"),
            nomeField,
            new Label("Email:"),
            emailField,
            new Label("Telefone:"),
            telefoneField,
            new Label("Endereço:"),
            enderecoField,
            new Label("Cidade:"),
            cidadeField,
            cadastrarButton
        );

        Scene scene = new Scene(vbox, 300, 250);
        stage.setScene(scene);

        stage.show();

        cadastrarButton.setOnAction(event -> {
            String nome = nomeField.getText();
            String email = emailField.getText();
            String telefone = telefoneField.getText();
            String endereco = enderecoField.getText();
            String cidade = cidadeField.getText();

            if (nome.isEmpty() || email.isEmpty()) {
                System.out.println("Nome e Email são campos obrigatórios.");
            } else if (!isValidEmail(email)) {
                System.out.println("Email inválido.");
            } else {
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String sql = "INSERT INTO clientes (nome, email, telefone, endereco, cidade) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, nome);
                    stmt.setString(2, email);
                    stmt.setString(3, telefone);
                    stmt.setString(4, endereco);
                    stmt.setString(5, cidade);

                    stmt.executeUpdate();
                    System.out.println("Cliente cadastrado com sucesso!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}

class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/seubanco";
    private static final String USER = "seuusuario";
    private static final String PASSWORD = "suasenha";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
