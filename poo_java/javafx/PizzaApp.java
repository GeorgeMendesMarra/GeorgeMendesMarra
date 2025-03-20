import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PizzaApp extends Application {

    private Label selectedPizzaLabel;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pizza App");

        selectedPizzaLabel = new Label("Nenhuma pizza selecionada");

        ToggleGroup pizzaToggleGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton("Calabresa");
        radioButton1.setToggleGroup(pizzaToggleGroup);

        RadioButton radioButton2 = new RadioButton("Mussarela");
        radioButton2.setToggleGroup(pizzaToggleGroup);

        RadioButton radioButton3 = new RadioButton("Portuguesa");
        radioButton3.setToggleGroup(pizzaToggleGroup);

        Button orderButton = new Button("Fazer Pedido");
        orderButton.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) pizzaToggleGroup.getSelectedToggle();
            if (selectedRadioButton != null) {
                String selectedPizza = selectedRadioButton.getText();
                selectedPizzaLabel.setText("Pizza selecionada: " + selectedPizza);
            }
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(radioButton1, radioButton2, radioButton3, orderButton, selectedPizzaLabel);

        primaryStage.setScene(new Scene(vbox, 300, 200));
        primaryStage.show();
    }
}
