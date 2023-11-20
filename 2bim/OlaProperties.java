import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class OlaProperties extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage s) throws Exception {
		VBox raiz = new VBox(10);
		raiz.getChildren().add(new Label("Sem Binding"));
		raiz.getChildren().addAll(olaSemBind());
		raiz.getChildren().add(new Separator(Orientation.HORIZONTAL));
		raiz.getChildren().add(new Label("Com Binding"));
		raiz.getChildren().addAll(olaComBind());
		raiz.getChildren().add(new Separator(Orientation.HORIZONTAL));
		raiz.getChildren()
				.add(new Label("Usando expressões de StringProperty"));
		raiz.getChildren().addAll(OlaExpressoesBind());
		raiz.getChildren().add(new Separator(Orientation.HORIZONTAL));
		raiz.getChildren().add(new Label("Usando DoubleExpressions"));
		raiz.getChildren().addAll(olaExpressoesDoubleBind());
		raiz.getChildren().add(new Separator(Orientation.HORIZONTAL));
		raiz.getChildren().add(new Label("Observando Properties"));
		raiz.getChildren().addAll(olaListeners());
		s.setScene(new Scene(raiz));
		s.setWidth(600);
		s.setHeight(720);
		s.show();
		s.setTitle("Testando Binding");

	}

	private static Node[] olaSemBind() {
		TextField txtNome = new TextField();
		Label lblNome = new Label();
		txtNome.setOnKeyReleased(e -> {
			lblNome.setText(txtNome.getText());
		});
		lblNome.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		return new Node[] { txtNome, lblNome };
	}

	private static Node[] olaComBind() {
		TextField txtNome = new TextField();
		Label lblNome = new Label();
		lblNome.textProperty().bind(txtNome.textProperty());
		lblNome.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		return new Node[] { txtNome, lblNome };
	}

	private static Node[] OlaExpressoesBind() {
		TextField txtNome = new TextField();
		TextField txtSaudacao = new TextField();
		Label lblNome = new Label();
		lblNome.textProperty().bind(
				txtNome.textProperty().concat(", ")
						.concat(txtSaudacao.textProperty()));
		lblNome.setWrapText(true);
		lblNome.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		return new Node[] { txtNome, txtSaudacao, lblNome };
	}

	private static Node[] olaExpressoesDoubleBind() {
		Slider sld1 = new Slider(0, 100, 50);
		Slider sld2 = new Slider(0, 100, 50);
		Label lblResultado = new Label();
		lblResultado.textProperty().bind(
				sld1.valueProperty().add(sld2.valueProperty()).asString());
		lblResultado.setWrapText(true);
		lblResultado.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		return new Node[] { sld1, sld2, lblResultado };
	}

	private static Node[] olaListeners() {
		String valores[] = { "Garrafa", "Copo", "Monitor", "Notebook",
				"Celular" };
		// na próxima falamos de FXCollections
		ComboBox<String> cmbValores = new ComboBox<String>(
				FXCollections.observableArrayList(valores));
		Label lblAntigo = new Label();
		Label lblNovo = new Label();
		cmbValores.valueProperty().addListener((obs, velho, novo) -> {
			lblAntigo.setText("Valor Antigo: " + velho);
			lblNovo.setText("Valor Novo: " + novo);
		});
		Font f = Font.font("Verdana", FontWeight.BOLD, 30);
		lblNovo.setFont(f);
		lblAntigo.setFont(f);
		return new Node[] { cmbValores, lblAntigo, lblNovo };
	}

}
