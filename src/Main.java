import java.sql.SQLException;

import controlador.RegistroControlador;
import controlador.UsuarioControlador;
import modelo.UsuarioDAO;
import vista.Login;
import vista.Registro;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Crear una instancia de UsuarioDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Comprobar si hay usuarios registrados en la base de datos
        if (usuarioDAO.existenUsuariosRegistrados()) {
            // Si hay usuarios, mostrar la vista de login
            Login loginVista = new Login();
            UsuarioControlador loginControlador = new UsuarioControlador(usuarioDAO, loginVista);
        } else {
            // Si no hay usuarios, mostrar la vista de registro
            Registro registroVista = new Registro();
            RegistroControlador registroControlador = new RegistroControlador(usuarioDAO, registroVista);
        }
    }
}

