import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormularioJavaFXComTitulo extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Formulário JavaFX com Título");

        // Criando um título para o formulário
        Label tituloLabel = new Label("Formulário de Cadastro");
        tituloLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Restante do código do formulário, igual ao exemplo anterior...
        TextField nomeField = new TextField();
        // ... (outros campos) ...
        Button salvarButton = new Button("Salvar");
        Button limparButton = new Button("Limpar Campos");
        // ... (ações dos botões) ...

        // Organizando botões horizontalmente
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(salvarButton, limparButton);

        // Criando um layout vertical para organizar os componentes
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(
            tituloLabel,  // Adicionando o título acima dos campos
            nomeLabel, nomeField,
            idadeLabel, idadeField,
            emailLabel, emailField,
            enderecoLabel, enderecoField,
            telefoneLabel, telefoneField,
            buttonBox
        );

        // Criando uma cena com o layout vertical
        Scene scene = new Scene(vbox, 400, 300);

        // Configurando a cena no palco (Stage) e exibindo-o
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
