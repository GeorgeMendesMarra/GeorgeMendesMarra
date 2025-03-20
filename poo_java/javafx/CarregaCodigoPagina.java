import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CarregaCodigoPagina extends Application {

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage s) throws Exception {
		ProgressIndicator carregando = new ProgressIndicator();
		TextField txtUrl = new TextField("http://www.google.com");
		Button btnCarregar = new Button("Carregar");
		TextArea txtResultado = new TextArea();

		txtUrl.setPrefWidth(250);
		txtResultado.setPrefHeight(400);
		txtResultado.setPrefWidth(300);
		txtResultado.setEditable(false);
		txtResultado.setWrapText(true);
		carregando.setVisible(false);

		VBox raiz = new VBox(20, new HBox(10, txtUrl, btnCarregar),
				new StackPane(txtResultado, carregando));
		s.setScene(new Scene(raiz));

		s.show();
		s.setTitle("Carrega código de página WEB");

		EventHandler<ActionEvent> cenario1 = e -> {
			try {
				txtResultado.setText(carregaPagina(txtUrl.getText()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		};

		EventHandler<ActionEvent> cenario2 = e -> {
			Thread t = new Thread(() -> {
				try {
					txtResultado.setText(carregaPagina(txtUrl.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			t.start();
		};

		EventHandler<ActionEvent> cenario3 = e -> {
			Task<String> tarefaCargaPg = new Task<String>() {

				@Override
				protected String call() throws Exception {
					return carregaPagina(txtUrl.getText());
				}

				@Override
				protected void succeeded() {
					String codigo = getValue();
					txtResultado.setText(codigo);
				}
			};
			Platform.runLater(new Thread(tarefaCargaPg));
		};

		// O melhor cenário
EventHandler<ActionEvent> cenario4 = e -> {
	carregando.setVisible(true);
	Task<String> tarefaCargaPg = new Task<String>() {

		@Override
		protected String call() throws Exception {
			return carregaPagina(txtUrl.getText());
		}

		@Override
		protected void succeeded() {
			String codigo = getValue();
			carregando.setVisible(false);
			txtResultado.setText(codigo);
		}
	};
	Thread t = new Thread(tarefaCargaPg);
	t.setDaemon(true);
	t.start();
};
		btnCarregar.setOnAction(cenario4);
	}

	private static String carregaPagina(String url) throws IOException {
		URL pagina = new URL(url);
		InputStream conteudo = pagina.openStream();
		byte[] buffer = new byte[256];
		StringBuffer codigo = new StringBuffer();
		while (conteudo.read(buffer) != -1) {
			codigo.append(new String(buffer));
		}
		conteudo.close();
		return codigo.toString();
	}
}
