import java.awt.*; // Importa as classes do AWT para criar a interface gráfica
import java.awt.event.*; // Importa as classes para lidar com eventos de interface
import java.util.ArrayList; // Importa a classe ArrayList para armazenar os clientes

public class CadastroClientesAWT extends Frame implements ActionListener {

    // Lista para armazenar os objetos Cliente
    private ArrayList<Cliente> clientes = new ArrayList<>();

    // Rótulos para os campos de texto
    private Label lblNome, lblEndereco, lblTelefone;
    // Campos de texto para inserir dados do cliente
    private TextField txtNome, txtEndereco, txtTelefone;
    // Botões para cadastrar e listar clientes
    private Button btnCadastrar, btnListar;
    // Área de texto para exibir a lista de clientes
    private TextArea txtListaClientes;

    // Construtor da classe CadastroClientesAWT
    public CadastroClientesAWT() {
        setTitle("Cadastro de Clientes"); // Define o título da janela
        setSize(500, 400); // Define o tamanho da janela
        setLayout(new GridLayout(6, 2)); // Define o layout da janela como uma grade de 6 linhas e 2 colunas

        // Cria os rótulos e campos de texto
        lblNome = new Label("Nome:");
        txtNome = new TextField();
        lblEndereco = new Label("Endereço:");
        txtEndereco = new TextField();
        lblTelefone = new Label("Telefone:");
        txtTelefone = new TextField();
        // Cria os botões e a área de texto
        btnCadastrar = new Button("Cadastrar");
        btnListar = new Button("Listar Clientes");
        txtListaClientes = new TextArea();
        txtListaClientes.setEditable(false); // Impede a edição da área de texto

        // Adiciona os componentes à janela
        add(lblNome);
        add(txtNome);
        add(lblEndereco);
        add(txtEndereco);
        add(lblTelefone);
        add(txtTelefone);
        add(btnCadastrar);
        add(btnListar);
        add(new Label("Lista de Clientes:"));
        add(txtListaClientes);

        // Adiciona listeners para os botões
        btnCadastrar.addActionListener(this);
        btnListar.addActionListener(this);

        // Adiciona um listener para o evento de fechamento da janela
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0); // Fecha a aplicação ao fechar a janela
            }
        });

        setVisible(true); // Torna a janela visível
    }

    // Método para lidar com eventos de botões
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnCadastrar) { // Se o botão "Cadastrar" for clicado
            // Obtém os dados dos campos de texto
            String nome = txtNome.getText();
            String endereco = txtEndereco.getText();
            String telefone = txtTelefone.getText();

            // Cria um novo objeto Cliente com os dados
            Cliente cliente = new Cliente(nome, endereco, telefone);
            // Adiciona o cliente à lista
            clientes.add(cliente);

            // Limpa os campos de texto
            txtNome.setText("");
            txtEndereco.setText("");
            txtTelefone.setText("");
        } else if (ae.getSource() == btnListar) { // Se o botão "Listar Clientes" for clicado
            txtListaClientes.setText(""); // Limpa a área de texto
            // Percorre a lista de clientes e exibe os dados na área de texto
            for (Cliente cliente : clientes) {
                txtListaClientes.append(cliente.toString() + "\n");
            }
        }
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        new CadastroClientesAWT(); // Cria uma instância da classe CadastroClientesAWT
    }
}

// Classe para representar um cliente
class Cliente {
    private String nome;
    private String endereco;
    private String telefone;

    // Construtor da classe Cliente
    public Cliente(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Método para formatar os dados do cliente para exibição
    public String toString() {
        return "Nome: " + nome + ", Endereço: " + endereco + ", Telefone: " + telefone;
    }
}
