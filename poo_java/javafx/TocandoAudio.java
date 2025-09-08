import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class TocandoAudio extends Application {

    private String audioPath = new File(System.getProperty("user.home") + "/Downloads/Sean_Fournier_-_All_I_ll_Ever_Need.mp3").toURI().toString();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage palco) throws Exception {
        AudioClip clip = new AudioClip(audioPath);
        clip.play();

        StackPane raiz = new StackPane();
        raiz.getChildren().add(new Text("Tocando Música "));

        Scene cena = new Scene(raiz, 600, 100);
        palco.setTitle("Tocando Audio em JavaFX");
        palco.setScene(cena);
        palco.show();
    }
}
