import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

/**
 * Formulário para cadastro de usuários
 */
public class Formulario extends JFrame {
    
    // Componentes da interface
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtIdade;
    private JButton btnSalvar;
    private JLabel lblMensagem;
    
    public Formulario() {
        // Configurar a janela
        setTitle("Cadastro de Usuários");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centralizar na tela
        setLayout(new BorderLayout());
        
        // Criar o painel principal
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre componentes
        
        // Campo Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(new JLabel("Nome:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        txtNome = new JTextField(20);
        painel.add(txtNome, gbc);
        
        // Campo Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtEmail = new JTextField(20);
        painel.add(txtEmail, gbc);
        
        // Campo Idade
        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(new JLabel("Idade:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        txtIdade = new JTextField(5);
        painel.add(txtIdade, gbc);
        
        // Botão Salvar
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        btnSalvar = new JButton("SALVAR");
        btnSalvar.setBackground(new Color(0, 120, 215));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        painel.add(btnSalvar, gbc);
        
        // Label para mensagens
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        lblMensagem = new JLabel("Preencha os dados acima", SwingConstants.CENTER);
        lblMensagem.setForeground(Color.GRAY);
        painel.add(lblMensagem, gbc);
        
        add(painel, BorderLayout.CENTER);
        
        // Configurar ação do botão Salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarUsuario();
            }
        });
    }
    
    /**
     * Método que será chamado ao clicar no botão Salvar
     */
    private void salvarUsuario() {
        // 1. Pegar os dados do formulário
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String idadeStr = txtIdade.getText().trim();
        
        // 2. Validar os campos
        if (nome.isEmpty() || email.isEmpty() || idadeStr.isEmpty()) {
            lblMensagem.setForeground(Color.RED);
            lblMensagem.setText("❌ Todos os campos são obrigatórios!");
            return;
        }
        
        // 3. Converter idade para número
        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
        } catch (NumberFormatException e) {
            lblMensagem.setForeground(Color.RED);
            lblMensagem.setText("❌ Idade deve ser um número válido!");
            return;
        }
        
        // 4. Criar o objeto Usuario
        Usuario usuario = new Usuario(nome, email, idade);
        
        // 5. Salvar no banco de dados (com tratamento de erros inesperados)
        UsuarioDAO dao = new UsuarioDAO();
        boolean salvou = false;
        try {
            salvou = dao.salvar(usuario);
        } catch (Exception e) {
            lblMensagem.setForeground(Color.RED);
            lblMensagem.setText("❌ Erro inesperado: " + e.getMessage());
            return;
        }
        
        // 6. Mostrar mensagem de sucesso ou erro
        if (salvou) {
            lblMensagem.setForeground(new Color(0, 150, 0));
            lblMensagem.setText("✅ Usuário cadastrado com sucesso!");
            
            // Limpar os campos para um novo cadastro
            txtNome.setText("");
            txtEmail.setText("");
            txtIdade.setText("");
            txtNome.requestFocus();
        } else {
            lblMensagem.setForeground(Color.RED);
            lblMensagem.setText("❌ Erro ao salvar. Verifique se o email já não está cadastrado.");
        }
    }
    
    /**
     * Método principal - inicia o programa
     */
    public static void main(String[] args) {
        // Garantir que a interface será criada na thread correta
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }
}
