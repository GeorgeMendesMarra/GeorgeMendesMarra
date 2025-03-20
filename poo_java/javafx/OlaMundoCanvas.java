import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class OlaMundoCanvas extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage s) throws Exception {
		// O construtor do Canvas recebe a largura e a altura
		Canvas canvas = new Canvas(300, 300);
		// O objeto principal do Canvas é o GraphicsContext, que usamos para desenhar
		GraphicsContext ctx = canvas.getGraphicsContext2D();
		// estamos prontos para desenhar coisas! Vamos começar mudando a cor
		ctx.setFill(Color.RED);
		// podemos configurar uma fonte para os textos
		ctx.setFont(Font.font("Serif", FontWeight.BOLD, 25));
		// desenhando um texto, o primeiro param é o texto, os seguintes são a pos X e Y
		ctx.fillText("Olá Mundo Canvas", 15, 30);
		// podemos configurar efeitos e novamente trocar a cor
		ctx.setFill(Color.BLUE);
		ctx.setEffect(new BoxBlur());
		ctx.fillText("Olá Mundo Canvas", 15, 60);
		// agora vamos trocar o efeito, cor e desenhar um retângulo(x,y, largura, altura)
		ctx.setEffect(new Reflection());
		ctx.setFill(Color.AQUA);
		ctx.fillRect(15, 90, 240, 20);
		// ou um retângulo sem preenchimento
		ctx.setStroke(Color.GREEN);
		ctx.strokeRect(15, 135, 240, 30);
		// ou circulos (forma oval)
		ctx.setEffect(null);
		ctx.setFill(Color.BROWN);
		ctx.fillOval(15, 175, 90, 25);
		ctx.setStroke(Color.YELLOWGREEN);
		// ou formas ovais sem preenchimento
		ctx.strokeOval(160, 175, 90, 25);
		// ou até desenhar uns poligonos locos, usando diversos pontos X e Y
		double xs[] = {15, 30, 45, 60, 75, 90, 105, 120, 135, 150, 165, 180, 195, 210, 225, 240, 255, 270};
		double ys[] = {205, 235, 250, 265, 205, 235, 205, 205, 235, 250, 265, 205, 235, 205, 205, 235, 250, 205};
		ctx.setFill(Color.MAGENTA);
		ctx.setEffect(new Reflection());
		ctx.fillPolygon(xs, ys, 18);
		// é Isso ^_^ Explore você agora
		s.setScene(new Scene(new StackPane(canvas)));
		s.show();
		s.setTitle("Olá Mundo Canvas");
	}

}
