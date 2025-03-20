import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CadastroClientesApp1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Clientes");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        TextField rgField = new TextField();
        rgField.setPromptText("RG (somente números)");

        TextField cpfField = new TextField();
        cpfField.setPromptText("CPF (somente números)");

        RadioButton masculinoRadioButton = new RadioButton("Masculino");
        RadioButton femininoRadioButton = new RadioButton("Feminino");
        RadioButton outrosRadioButton = new RadioButton("Outros");
        ToggleGroup generoToggleGroup = new ToggleGroup();
        masculinoRadioButton.setToggleGroup(generoToggleGroup);
        femininoRadioButton.setToggleGroup(generoToggleGroup);
        outrosRadioButton.setToggleGroup(generoToggleGroup);

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");

        TextField enderecoField = new TextField();
        enderecoField.setPromptText("Endereço");

        DatePicker dataNascimentoPicker = new DatePicker();
        dataNascimentoPicker.setPromptText("Data de Nascimento");

        Button cadastrarButton = new Button("Cadastrar");
        cadastrarButton.setOnAction(event -> {
            // Aqui você pode adicionar a lógica para validar e cadastrar os dados
            // Exemplo de validação de campos:
            if (!rgField.getText().matches("\\d+")) {
                showAlert("Erro de RG", "O RG deve conter apenas números.");
                return;
            }
            if (!cpfField.getText().matches("\\d{11}")) {
                showAlert("Erro de CPF", "O CPF deve conter exatamente 11 números.");
                return;
            }
            if (generoToggleGroup.getSelectedToggle() == null) {
                showAlert("Erro de Gênero", "Selecione uma opção de gênero.");
                return;
            }
            // Você pode adicionar mais validações e lógica de cadastro aqui

            // Se tudo estiver válido, você pode prosseguir com o cadastro
            // Exemplo: chamar um método para cadastrar os dados em um banco de dados
            // ou em uma estrutura de dados local.
        });

        grid.add(new Label("RG:"), 0, 0);
        grid.add(rgField, 1, 0);
        grid.add(new Label("CPF:"), 0, 1);
        grid.add(cpfField, 1, 1);

        HBox generoBox = new HBox(10, masculinoRadioButton, femininoRadioButton, outrosRadioButton);
        grid.add(new Label("Gênero:"), 0, 2);
        grid.add(generoBox, 1, 2);

        grid.add(new Label("Nome:"), 0, 3);
        grid.add(nomeField, 1, 3);
        grid.add(new Label("Endereço:"), 0, 4);
        grid.add(enderecoField, 1, 4);
        grid.add(new Label("Data de Nascimento:"), 0, 5);
        grid.add(dataNascimentoPicker, 1, 5);
        grid.add(cadastrarButton, 1, 6);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
