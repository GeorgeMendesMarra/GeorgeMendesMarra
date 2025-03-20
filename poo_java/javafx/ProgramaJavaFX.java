import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProgramaJavaFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Configura o palco (stage)
        primaryStage.setTitle("Programa JavaFX");

        // Cria um botão
        Button botao = new Button("Clique-me");
        botao.setOnAction(e -> {
            System.out.println("Botão clicado! Mensagem exibida.");
        });

        // Cria um layout e adiciona o botão a ele
        StackPane layout = new StackPane();
        layout.getChildren().add(botao);

        // Cria uma cena e a associa ao palco
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);

        // Exibe o palco
        primaryStage.show();
    }
}
