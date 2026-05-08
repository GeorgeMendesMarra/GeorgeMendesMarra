import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe responsável por salvar usuários no banco de dados
 * DAO = Data Access Object (Objeto de Acesso a Dados)
 */
public class UsuarioDAO {
    
    /**
     * Salva um usuário no banco de dados
     * @param usuario Objeto com os dados do usuário
     * @return true se salvou com sucesso, false caso contrário
     */
    public boolean salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, idade) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getIdade());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            // Código 1062 = Duplicate entry no MySQL (e-mail já cadastrado)
            if (e.getErrorCode() == 1062) {
                System.err.println("E-mail já cadastrado: " + usuario.getEmail());
            } else {
                System.err.println("Erro ao salvar: " + e.getMessage());
            }
            return false; // Sempre retorna false em caso de erro, sem lançar exceção
        }
    }
    
    /**
     * Método de teste para o DAO
     */
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = new Usuario("João Silva", "joao@email.com", 30);
        
        if (dao.salvar(usuario)) {
            System.out.println("✅ Usuário salvo com sucesso!");
        } else {
            System.out.println("❌ Erro ao salvar usuário.");
        }
    }
}
