import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TabPaneTeste extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage s) throws Exception {
		// criamos o painel
		TabPane painelAbas = new TabPane();
		// criando paineis de abas, o construtor recebe o nome da aba
		Tab aba1 = new Tab("Aba 1");
		Tab aba2 = new Tab("Aba 2");
		Tab aba3 = new Tab("Aba 3");
		// configuramos o conteúdo de cada aba, que aceita qualquer tipo de Nó
		aba1.setContent(new Label("Configurando como conteúdo um label..."));
		aba2.setContent(new Button("Um botão de conteúdo"));
		aba3.setContent(new Rectangle(150, 50));
		// abas podem ser fechadas, ou não. Por padrão elas são.
		aba1.setClosable(false);
		aba2.setClosable(false);
		aba3.setClosable(true);
		// podemos adicionar ícones as abas
		aba1.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(
				"coracao.png"))));
		aba2.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(
				"fogo.png"))));
		aba3.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(
				"agua.png"))));
		// agora adicionamos todas as abas de vez
		painelAbas.getTabs().addAll(aba1, aba2, aba3);

		// Usa os valores do enum Side para configurar que lado ficarão as abas
	//	painelAbas.setSide(Side.BOTTOM);
		s.setScene(new Scene(painelAbas));
		s.setTitle("Testando Painel de Abas");
		s.show();
		s.setWidth(400);
		s.setWidth(300);

	}
}
