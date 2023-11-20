import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class TocandoVideo extends Application {

	private String VIDEO_URL = getClass().getResource(
			"/video/creativecommons.mp4").toString();

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage palco) throws Exception {

		Media media = new Media(VIDEO_URL); // 1
		MediaPlayer mediaPlayer = new MediaPlayer(media); // 2
		MediaView mediaView = new MediaView(mediaPlayer); // 3

		StackPane raiz = new StackPane();
		raiz.getChildren().add(mediaView); // 4
		Scene cena = new Scene(raiz, 600, 400);
		palco.setTitle("Tocando Video em JavaFX");
		palco.setScene(cena);
		palco.show();

		mediaPlayer.play(); // 4
	}
}
