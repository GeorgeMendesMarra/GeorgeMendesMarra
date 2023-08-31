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
    private TextField cnpjField;
    private TextField emailField;
    private TextField phoneField;
    private TextField cellPhoneField;

    @Override
    public void start(Stage primaryStage) {
        // Criação dos elementos do formulário
        Label nameLabel = new Label("Nome da Empresa:");
        TextField nameField = new TextField();

        Label cnpjLabel = new Label("CNPJ:");
        cnpjField = new TextField();

        Label contactLabel = new Label("Contato:");
        TextField contactField = new TextField();

        Label emailLabel = new Label("E-mail:");
        emailField = new TextField();

        Label phoneLabel = new Label("Telefone Fixo:");
        phoneField = new TextField();

        Label cellPhoneLabel = new Label("Telefone Celular:");
        cellPhoneField = new TextField();

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
                showAlert("Erro de Validação", "Por favor, preencha todos os campos.");
                return false;
            }
        }

        // Validação do CNPJ
        if (!isValidCnpj(cnpjField.getText())) {
            showAlert("Erro de Validação", "CNPJ inválido.");
            return false;
        }

        // Validação do e-mail
        if (!isValidEmail(emailField.getText())) {
            showAlert("Erro de Validação", "E-mail inválido.");
            return false;
        }

        // Validação do telefone fixo
        if (!isValidPhoneNumber(phoneField.getText())) {
            showAlert("Erro de Validação", "Telefone fixo inválido.");
            return false;
        }

        // Validação do telefone celular
        if (!isValidPhoneNumber(cellPhoneField.getText())) {
            showAlert("Erro de Validação", "Telefone celular inválido.");
            return false;
        }

        return true;
    }

    // Função para mostrar alertas de erro
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Função para validar CNPJ
    private boolean isValidCnpj(String cnpj) {
        return cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");
    }

    // Função para validar e-mail
    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

    // Função para validar número de telefone
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{11}");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
