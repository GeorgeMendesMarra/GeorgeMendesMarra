import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuJavaFX1 extends Application {

    @Override
    public void start(Stage stage) {

        // Cria o menu bar
        MenuBar menuBar = new MenuBar();

        // Cria o menu de cadastros
        Menu menuCadastros = new Menu("Cadastros");

        // Cria os itens de menu
        MenuItem menuItemCadastroClientes = new MenuItem("Clientes");
        MenuItem menuItemCadastroProdutos = new MenuItem("Produtos");
        MenuItem menuItemCadastroFornecedores = new MenuItem("Fornecedores");

        // Adiciona os itens de menu ao menu de cadastros
        menuCadastros.getItems().addAll(menuItemCadastroClientes, menuItemCadastroProdutos, menuItemCadastroFornecedores);

        // Cria o menu de ajuda
        Menu menuAjuda = new Menu("Ajuda");

        // Cria os itens de menu
        MenuItem menuItemSobre = new MenuItem("Sobre");

        // Adiciona os itens de menu ao menu de ajuda
        menuAjuda.getItems().add(menuItemSobre);

        // Adiciona o menu de cadastros e o menu de ajuda ao menu bar
        menuBar.getMenus().addAll(menuCadastros, menuAjuda);

        // Cria a cena
        Scene scene = new Scene(menuBar);

        // Define o título da janela
        stage.setTitle("Menu JavaFX");

        // Define a cena na janela
        stage.setScene(scene);

        // Mostra a janela
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
