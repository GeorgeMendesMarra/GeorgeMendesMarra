import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por criar a conexão com o banco de dados MySQL
 */
public class ConexaoBD {
    
    // Informações do banco de dados (mude conforme sua instalação)
    private static final String URL = "jdbc:mysql://localhost:3306/cadastro_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456"; // Use a senha que você definiu
    
    /**
     * Retorna uma conexão com o banco de dados
     * @return Connection
     * @throws SQLException se não conseguir conectar
     */
    public static Connection getConexao() throws SQLException {
        try {
            // Registrar o driver (para versões mais antigas do Java)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado! Verifique se o .jar foi adicionado ao projeto.");
            e.printStackTrace();
        }
        
        // Criar e retornar a conexão
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
    /**
     * Método de teste rápido para verificar a conexão
     */
    public static void main(String[] args) {
        try {
            Connection conn = getConexao();
            System.out.println("✅ Conexão com o banco de dados estabelecida com sucesso!");
            conn.close();
            System.out.println("Conexão fechada.");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ao banco de dados!");
            System.err.println("Mensagem: " + e.getMessage());
        }
    }
}
