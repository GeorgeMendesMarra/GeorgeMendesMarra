import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuJavaFX3 extends Application {

    @Override
    public void start(Stage stage) {

        // Cria o menu bar
        MenuBar menuBar = new MenuBar();

        // Cria o menu de cadastros
        Menu menuCadastros = new Menu("Cadastros");

        // Cria os itens de menu
        MenuItem menuItemCadastroClientes = new MenuItem("Clientes");
        MenuItem menuItemCadastroFornecedores = new MenuItem("Fornecedores");
        MenuItem menuItemCadastroProdutos = new MenuItem("Produtos");

        // Adiciona os itens de menu ao menu de cadastros
        menuCadastros.getItems().addAll(menuItemCadastroClientes, menuItemCadastroFornecedores, menuItemCadastroProdutos);

        // Adiciona o menu de cadastros ao menu bar
        menuBar.getMenus().add(menuCadastros);

        // Cria a cena
        Scene scene = new Scene(menuBar);

        // Define o título da janela
        stage.setTitle("Menu JavaFX");

        // Define a cena na janela
        stage.setScene(scene);

        // Mostra a janela
        stage.show();

        // Abre o cadastro de clientes
        menuItemCadastroClientes.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("clientes.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Abre o cadastro de fornecedores
        menuItemCadastroFornecedores.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fornecedores.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Abre o cadastro de produtos
        menuItemCadastroProdutos.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("produtos.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
