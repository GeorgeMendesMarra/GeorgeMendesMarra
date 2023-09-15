import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuJavaFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu em JavaFX");

        // Criação do menu Cadastro
        Menu menuCadastro = new Menu("Cadastro");

        // Opção Cadastro de Cliente
        MenuItem itemCliente = new MenuItem("Cadastro de Cliente");
        itemCliente.setOnAction(e -> {
            // Coloque aqui o código para a ação de Cadastro de Cliente
            System.out.println("Abrir tela de Cadastro de Cliente");
        });

        // Opção Cadastro de Fornecedor
        MenuItem itemFornecedor = new MenuItem("Cadastro de Fornecedor");
        itemFornecedor.setOnAction(e -> {
            // Coloque aqui o código para a ação de Cadastro de Fornecedor
            System.out.println("Abrir tela de Cadastro de Fornecedor");
        });

        // Opção Cadastro de Produtos
        MenuItem itemProdutos = new MenuItem("Cadastro de Produtos");
        itemProdutos.setOnAction(e -> {
            // Coloque aqui o código para a ação de Cadastro de Produtos
            System.out.println("Abrir tela de Cadastro de Produtos");
        });

        // Adicionar as opções de menu ao menu Cadastro
        menuCadastro.getItems().addAll(itemCliente, itemFornecedor, itemProdutos);

        // Criação da barra de menu
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menuCadastro);

        // Layout principal
        VBox vbox = new VBox(menuBar);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
