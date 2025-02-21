package controlador;

import modelo.PeliculaDAO;
import modelo.SerieDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.Login;
import vista.Registro;
import vista.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioControlador {
    private UsuarioDAO usuarioDAO;
    private Login login;
    private int usuarioId;
    private PeliculaDAO peliculaDAO;
    private SerieDAO serieDAO;

    public UsuarioControlador(UsuarioDAO usuarioDAO, Login login) {
        this.usuarioDAO = usuarioDAO;
        this.login = login;
        try {
            this.peliculaDAO = new PeliculaDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.serieDAO = new SerieDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Añadir listeners para los botones
        this.login.addLoginListener(new LoginListener());
        this.login.addIrRegistroListener(new IrAlRegistroListener());
        // Mostrar la vista
        this.login.setVisible(true);
    }

    // Listener para el botón de login
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String usuario = login.getNombreUsuario();
            String contrasena = login.getContrasena();

            Usuario user = usuarioDAO.inicioSesion(usuario, contrasena);

            if (user != null) {
                usuarioId = user.getId();
                JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE); // Mensaje en español con botón "Aceptar"

                // Cerrar la ventana de login asegurándonos de que se está eliminando
                if (login != null) {
                    login.dispose();
                    login = null; // Eliminar referencia para evitar problemas
                }

                // Abrir la ventana principal
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(usuarioId, user.getNombreUsuario(), UsuarioControlador.this);
                ventanaPrincipal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE); // Mensaje en español con botón "Aceptar"
            }
        }
    }

    // Listener para el botón de registro
    class IrAlRegistroListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Registro registro = new Registro();
            RegistroControlador registroControlador = new RegistroControlador(usuarioDAO, registro);
            login.dispose();  // Cerrar la ventana de login
        }
    }

    public int obtenerPeliculasVistas(int usuarioId) {
        return peliculaDAO.conteoDePeliculas(usuarioId);
    }

    public int obtenerSeriesVistas(int usuarioId) {
        return serieDAO.conteoDeSeries(usuarioId);
    }
}
