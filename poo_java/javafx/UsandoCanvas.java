import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UsandoCanvas extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	static enum Pincel {
		OVAL("Oval"), RETANGULO("Retângulo");

		private final String nome;

		private Pincel(String nome) {
			this.nome = nome;
		}

		@Override
		public String toString() {
			return nome;
		}
	}

	static enum Modo {
		PINCEL("Pincel"), TEXTO("Texto"), RETANGULO("Retângulo"), CIRCULO(
				"Círculo");

		private final String nome;

		private Modo(String nome) {
			this.nome = nome;
		}

		@Override
		public String toString() {
			return nome;
		}

	}

	// uma interface funcional para manusear o context gráfico
	static interface ManuseiaContexto {
		public void configura(MouseEvent m, GraphicsContext ctx);
	}

	static final Effect EFEITOS[] = { null, new Reflection(), new BoxBlur() };

	@Override
	public void start(Stage s) throws Exception {

		Canvas canvas = new Canvas(800, 600);
		// Canvas de sombra, para fazer a pré visualização de algumas coisas
		Canvas sombra = new Canvas(800, 600);
		BorderPane raiz = new BorderPane();
		ColorPicker corLinha = new ColorPicker();
		ColorPicker corFundo = new ColorPicker();
		TextField txtTexto = new TextField();
		Slider sldEspessura = new Slider(1, 40, 20);
		ChoiceBox<Pincel> pinceis = new ChoiceBox<>();
		ChoiceBox<Modo> modos = new ChoiceBox<>();
		ChoiceBox<String> efeitos = new ChoiceBox<>();

		// configurações gerais
		pinceis.getItems().addAll(Pincel.values());
		modos.getItems().addAll(Modo.values());
		efeitos.getItems().addAll(
				Stream.of(EFEITOS)
						.map(e -> e == null ? "Nenhum" : e.getClass()
								.getSimpleName()).collect(Collectors.toList()));
		sldEspessura.setShowTickMarks(true);
		pinceis.getSelectionModel().select(0);
		modos.getSelectionModel().select(0);
		efeitos.getSelectionModel().select(0);
		corLinha.setValue(Color.BLACK);
		sombra.setDisable(true);

		// Organizando os controles do paint
		GridPane pnlControles = new GridPane();

		pnlControles.add(new Label("Linha"), 0, 0);
		pnlControles.add(corLinha, 1, 0);

		pnlControles.add(new Label("Limpar"), 0, 1);
		pnlControles.add(corFundo, 1, 1);

		pnlControles.add(new Label("Espessura"), 0, 2);
		pnlControles.add(sldEspessura, 1, 2);

		pnlControles.add(new Label("Modo"), 0, 3);
		pnlControles.add(modos, 1, 3);

		pnlControles.add(new Label("Tipo de Pincel"), 0, 4);
		pnlControles.add(pinceis, 1, 4);

		pnlControles.add(new Label("Efeito"), 0, 5);
		pnlControles.add(efeitos, 1, 5);

		pnlControles.add(new Label("Texto"), 0, 6);
		pnlControles.add(txtTexto, 1, 6);

		pnlControles.setVgap(10);
		pnlControles.setHgap(10);
		pnlControles.setTranslateX(2);
		pnlControles.setTranslateY(10);

		raiz.setLeft(pnlControles);
		raiz.setCenter(new Group(canvas, sombra));
		sombra.setOpacity(0.1);

		Consumer<GraphicsContext> limpa = ctx -> {
			ctx.setFill(corFundo.getValue());
			ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		};

		ManuseiaContexto manuseiaContexto = (e, ctx) -> {
			double v = sldEspessura.getValue();
			Pincel tipo = pinceis.getValue();
			Modo acao = modos.getValue();
			double x = e.getX() - v / 2;
			double y = e.getY() - v / 2;
			Color selCorLinha = corLinha.getValue();
			int efeito = efeitos.getSelectionModel().getSelectedIndex();
			Font f = Font.font(v);
			ctx.setEffect(EFEITOS[efeito]);
			ctx.setFill(selCorLinha);
			ctx.setFont(f);
			ctx.setStroke(selCorLinha);
			switch (acao) {
			case PINCEL:
				switch (tipo) {
				case OVAL:
					ctx.fillOval(x, y, v, v);
					break;
				case RETANGULO:
					ctx.fillRect(x, y, v, v);
					break;
				default:
					break;
				}
				break;
			case TEXTO:
				ctx.fillText(txtTexto.getText(), x, e.getY());
				break;
			case RETANGULO:
				ctx.strokeRect(x, y, v, v);
				break;
			case CIRCULO:
				ctx.strokeOval(x, y, v, v);
				break;
			}
		};

		EventHandler<? super MouseEvent> desenha = e -> {
			GraphicsContext ctx;
			if (e.getEventType() == MouseEvent.MOUSE_MOVED) {
				ctx = sombra.getGraphicsContext2D();
				limpa.accept(ctx);
			} else {
				ctx = canvas.getGraphicsContext2D();
			}
			manuseiaContexto.configura(e, ctx);
		};

		canvas.setOnMouseDragged(desenha);
		canvas.setOnMouseReleased(desenha);
		canvas.setOnMousePressed(desenha);
		canvas.setOnMouseMoved(desenha);

		txtTexto.disableProperty().bind(
				modos.valueProperty()
					.isEqualTo(Modo.PINCEL)
					.or(modos.valueProperty().isEqualTo(Modo.RETANGULO)
					.or(modos.valueProperty().isEqualTo(Modo.CIRCULO)))
				);
		pinceis.disableProperty().bind(
				modos.valueProperty()
						.isEqualTo(Modo.TEXTO)
						.or(modos.valueProperty().isEqualTo(Modo.RETANGULO)
						.or(modos.valueProperty().isEqualTo(Modo.CIRCULO)))
				);
		corFundo.setOnAction(e -> limpa.accept(canvas.getGraphicsContext2D()));
		limpa.accept(canvas.getGraphicsContext2D());
		s.setScene(new Scene(new Pane(raiz)));
		s.show();
	}
}
