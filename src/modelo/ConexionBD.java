package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // URL de la base de datos MySQL a la que nos conectamos.
    private static final String URL = "jdbc:mysql://localhost:3306/analytics_film";
    // Usuario de nuestra base de datos. 
    private static final String USER = "root";  
    // Contraseña de usuario de nuestra base de datos. Por defecto la tenemos vacía.
    private static final String PASSWORD = "";

    // Método para obtener la conexión a nuestra base de datos
    public static Connection obtenerConexion() throws SQLException {
        try {
            // Cargamos el driver JDBC de MySQL para la conexión.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Se establece y devuelve la conexión realizada.
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            // Lanzamos una excepción si el driver no se encuentra en el proyecto.
            throw new SQLException("Error al cargar el driver de MySQL.", e);
        }
    }
    
}
 