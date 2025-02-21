package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

	private Connection conexion;

    public UsuarioDAO() throws SQLException {
        conexion = ConexionBD.obtenerConexion();
    }

    // Método para comprobar si hay usuarios registrados
    public boolean existenUsuariosRegistrados() {
        String consulta = "SELECT COUNT(*) FROM Usuarios";
        try (PreparedStatement declaracion = conexion.prepareStatement(consulta);
             ResultSet conjuntoResultados = declaracion.executeQuery()) {
            if (conjuntoResultados.next()) {
                return conjuntoResultados.getInt(1) > 0;  // Retorna true si hay al menos un usuario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Retorna false si hay un error
    }
	
	//MÉTODO PARA REGISTRAR UN USUARIO
	
	public boolean registroDeUsuarios(Usuario usuario) {
		
		String sql = "INSERT INTO Usuarios (nombre_usuario, contrasena, correo_electronico) VALUES (?, ?, ?)";
		
		try (Connection conn = ConexionBD.obtenerConexion();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
				stmt.setString(1, usuario.getNombreUsuario());
				stmt.setString(2, usuario.getContraseña());
				stmt.setString(3, usuario.getCorreo());
				
				int rowsAffected = stmt.executeUpdate();
				return rowsAffected > 0; 
		
		}catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
	//METODO PARA INICIAR SESION.
	
	public Usuario inicioSesion(String nombreUsuario, String contrasena) {
        String sql = "SELECT * FROM Usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasena);  // Recuerda validar la contraseña correctamente
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Si el usuario existe, crea un objeto Usuario
                return new Usuario(rs.getInt("id"), 
                                   rs.getString("nombre_usuario"), 
                                   rs.getString("contrasena"), 
                                   rs.getString("correo_electronico"));
            } else {
                return null;  // Usuario no encontrado
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	// Método para validar si el usuario y la contraseña existen en la base de datos
    public boolean validacionDeUsuario(String nombreUsuario, String contrasena) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión desde la clase de conexión
            conn = ConexionBD.obtenerConexion();
            
            // Consulta SQL para verificar si el usuario existe
            String sql = "SELECT * FROM Usuarios WHERE nombre_usuario = ? AND contrasena = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasena);

            // Ejecutar la consulta
            rs = stmt.executeQuery();

            // Si hay un resultado, significa que el usuario y la contraseña coinciden
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
 // Método para registrar un nuevo usuario en la base de datos
    public boolean registroNuevoUsuario(String nombreUsuario, String contrasena, String correo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConexionBD.obtenerConexion();

            String sql = "INSERT INTO Usuarios (nombre_usuario, contrasena, correo_electronico) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasena);
            stmt.setString(3, correo);

            // Ejecutar la inserción
            int filasInsertadas = stmt.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}