Para fazer um formulário em JavaFX que se auto ajusta na tela, você pode usar o método `setMinWidth()` e `setMinHeight()` para definir as dimensões mínimas do formulário. Você também pode usar o método `setPrefWidth()` e `setPrefHeight()` para definir as dimensões preferidas do formulário.

Aqui está um exemplo de como fazer um formulário que se auto ajusta na tela:

```
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Cria um formulário
        Label labelNome = new Label("Nome:");
        TextField textFieldNome = new TextField();
        Button buttonConsultar = new Button("Consultar");

        // Define as dimensões mínimas do formulário
        labelNome.setMinWidth(100);
        textFieldNome.setMinWidth(100);
        buttonConsultar.setMinWidth(100);

        // Define as dimensões preferidas do formulário
        labelNome.setPrefWidth(200);
        textFieldNome.setPrefWidth(200);
        buttonConsultar.setPrefWidth(200);

        // Adiciona os componentes ao formulário
        VBox vBox = new VBox();
        vBox.getChildren().addAll(labelNome, textFieldNome, buttonConsultar);

        // Cria uma cena
        Scene scene = new Scene(vBox, 300, 250);

        // Mostra o formulário
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

Neste exemplo, o formulário terá uma largura mínima de 100 pixels e uma altura mínima de 250 pixels. A largura e a altura preferidas serão de 200 pixels.

Você também pode usar o método `setResizable()` para definir se o formulário pode ser redimensionado pelo usuário. Se você definir este método como `false`, o formulário não poderá ser redimensionado.

Aqui está um exemplo de como definir o formulário como não redimensionável:

```
// Define o formulário como não redimensionável
primaryStage.setResizable(false);
```

Com este código, o formulário não poderá ser redimensionado pelo usuário.
