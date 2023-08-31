import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroFornecedorForm extends Application {

    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        // Criação dos elementos do formulário
        Label nameLabel = new Label("Nome da Empresa:");
        TextField nameField = new TextField();

        Label cnpjLabel = new Label("CNPJ:");
        TextField cnpjField = new TextField();

        Label contactLabel = new Label("Contato:");
        TextField contactField = new TextField();

        Label emailLabel = new Label("E-mail:");
        TextField emailField = new TextField();

        Label phoneLabel = new Label("Telefone Fixo:");
        TextField phoneField = new TextField();

        Label cellPhoneLabel = new Label("Telefone Celular:");
        TextField cellPhoneField = new TextField();

        Button submitButton = new Button("Cadastrar");
        Button clearButton = new Button("Limpar Campos");

        // Layout do formulário usando um GridPane
        GridPane gridPane = new GridPane();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(cnpjLabel, 0, 1);
        gridPane.add(cnpjField, 1, 1);
        gridPane.add(contactLabel, 0, 2);
        gridPane.add(contactField, 1, 2);
        gridPane.add(emailLabel, 0, 3);
        gridPane.add(emailField, 1, 3);
        gridPane.add(phoneLabel, 0, 4);
        gridPane.add(phoneField, 1, 4);
        gridPane.add(cellPhoneLabel, 0, 5);
        gridPane.add(cellPhoneField, 1, 5);
        gridPane.add(submitButton, 0, 6);
        gridPane.add(clearButton, 1, 6);

        // Configuração do evento do botão "Cadastrar"
        submitButton.setOnAction(event -> {
            if (validateFields(nameField, cnpjField, contactField, emailField, phoneField, cellPhoneField)) {
                String nome = nameField.getText();
                String cnpj = cnpjField.getText();
                String contato = contactField.getText();
                String email = emailField.getText();
                String telefoneFixo = phoneField.getText();
                String telefoneCelular = cellPhoneField.getText();

                // Conexão ao banco de dados e inserção dos dados
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seu_banco_de_dados", "seu_usuario", "sua_senha");

                    String insertQuery = "INSERT INTO fornecedores (nome, cnpj, contato, email, telefone_fixo, telefone_celular) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, nome);
                    preparedStatement.setString(2, cnpj);
                    preparedStatement.setString(3, contato);
                    preparedStatement.setString(4, email);
                    preparedStatement.setString(5, telefoneFixo);
                    preparedStatement.setString(6, telefoneCelular);
                    preparedStatement.executeUpdate();

                    System.out.println("Fornecedor cadastrado com sucesso!");

                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Erro ao cadastrar fornecedor: " + e.getMessage());
                }
            }
        });

        // Configuração do evento do botão "Limpar Campos"
        clearButton.setOnAction(event -> {
            nameField.clear();
            cnpjField.clear();
            contactField.clear();
            emailField.clear();
            phoneField.clear();
            cellPhoneField.clear();
        });

        // Configuração da cena e do palco
        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cadastro de Fornecedor");
        primaryStage.show();
    }

    // Função para validar campos
    private boolean validateFields(TextField... fields) {
        for (TextField field : fields) {
            if (field.getText().isEmpty()) {
                // Se algum campo estiver vazio, mostra uma mensagem de erro
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro de Validação");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, preencha todos os campos.");
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
