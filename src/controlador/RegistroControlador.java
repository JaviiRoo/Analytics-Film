package controlador;

import modelo.UsuarioDAO;
import vista.Login;
import vista.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class RegistroControlador {
    private UsuarioDAO usuarioDAO;
    private Registro registro;

    public RegistroControlador(UsuarioDAO usuarioDAO, Registro registro) {
        this.usuarioDAO = usuarioDAO;
        this.registro = registro;

        // Añadir listeners a los botones
        this.registro.addRegistrarListener(new RegistrarListener());
        this.registro.addCancelarListener(new CancelarListener());

        // Mostrar la vista de registro
        this.registro.setVisible(true);
    }
    
    // Listener para el botón de registrar
    class RegistrarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String usuario = registro.getNombreUsuario();
            String contrasena = registro.getContraseña();
            String correo = registro.getCorreo();

            if (usuarioDAO.registroNuevoUsuario(usuario, contrasena, correo)) {
                JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE); // Mensaje en español con botón "Aceptar"
                // Volvemos a la vista de Login
                registro.dispose(); // Cerrar ventana de registro
                Login login = new Login();
                UsuarioControlador loginControlador = new UsuarioControlador(usuarioDAO, login);
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE); // Mensaje en español con botón "Aceptar"
            }
        }
    }

    // Listener para el botón de cancelar
    class CancelarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Lógica para cancelar el registro y volver al login
            registro.dispose();  // Cerrar la ventana de registro
            // Volver a la ventana de login aquí
            Login login = new Login();
            login.setVisible(true);
        }
    }
}
