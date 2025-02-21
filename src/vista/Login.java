package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField usuario;
    private JPasswordField contraseña;
    private JButton botonLogin, botonRegistro;

    public Login() {
        // Configuramos los textos de los botones de JOptionPane al español
        configurarTextosBotones();

        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal con color
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(100, 149, 237)); // cornflowerblue
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        add(panelPrincipal, BorderLayout.CENTER);

        //Título de la aplicación
        JLabel labelTitulo = new JLabel("Analitycs Films", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Sans Serif", Font.BOLD, 30));
        labelTitulo.setForeground(new Color(255, 127, 80)); // coral
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelPrincipal.add(labelTitulo);

        // Panel interno para los campos y botones
        JPanel panelDeContenido = new JPanel();
        panelDeContenido.setOpaque(false);
        panelDeContenido.setLayout(new BoxLayout(panelDeContenido, BoxLayout.Y_AXIS));
        panelDeContenido.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDeContenido.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Campo de usuario
        JPanel panelDeUsuario = new JPanel();
        panelDeUsuario.setOpaque(false);
        panelDeUsuario.setLayout(new BoxLayout(panelDeUsuario, BoxLayout.Y_AXIS));
        JLabel labelUsuario = new JLabel("Usuario:");
        labelUsuario.setForeground(new Color(0, 0, 0)); // violet
        labelUsuario.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        labelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        usuario = new JTextField(15);
        usuario.setMaximumSize(new Dimension(300, 30));
        usuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelDeUsuario.add(labelUsuario);
        panelDeUsuario.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDeUsuario.add(usuario);

        // Campo para la contraseña del usuario
        JPanel panelContraseña = new JPanel();
        panelContraseña.setOpaque(false);
        panelContraseña.setLayout(new BoxLayout(panelContraseña, BoxLayout.Y_AXIS));
        JLabel labelContraseña = new JLabel("Contraseña:");
        labelContraseña.setForeground(new Color(0, 0, 0)); // violet
        labelContraseña.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        labelContraseña.setAlignmentX(Component.CENTER_ALIGNMENT);
        contraseña = new JPasswordField(15);
        contraseña.setMaximumSize(new Dimension(300, 30));
        contraseña.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContraseña.add(labelContraseña);
        panelContraseña.add(Box.createRigidArea(new Dimension(0, 5)));
        panelContraseña.add(contraseña);

        // Panel de los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setBackground(new Color(173, 255, 47)); // greenyellow
        botonLogin.setForeground(Color.BLACK);
        botonLogin.setFocusPainted(false);
        botonLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonRegistro = new JButton("Registrarse");
        botonRegistro.setBackground(new Color(218, 165, 31)); // goldenrod
        botonRegistro.setForeground(Color.BLACK);
        botonRegistro.setFocusPainted(false);
        botonRegistro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botonRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBotones.add(botonLogin);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 15)));
        panelBotones.add(botonRegistro);

        // Se agregan los elementos a nuestro panel principal
        panelDeContenido.add(panelDeUsuario);
        panelDeContenido.add(Box.createRigidArea(new Dimension(0, 15)));
        panelDeContenido.add(panelContraseña);
        panelDeContenido.add(Box.createRigidArea(new Dimension(0, 20)));
        panelDeContenido.add(panelBotones);
        panelPrincipal.add(panelDeContenido);
    }

    // Método donde configuramos los botones de la ventana emergente JOptionPane en español.
    private void configurarTextosBotones() {
        UIManager.put("OptionPane.okButtonText", "Aceptar");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        UIManager.put("OptionPane.yesButtonText", "Sí");
        UIManager.put("OptionPane.noButtonText", "No");
    }

    // Métodos de los eventos registrados
    public void addLoginListener(ActionListener listener) {
        botonLogin.addActionListener(listener);
    }

    public void addIrRegistroListener(ActionListener listener) {
        botonRegistro.addActionListener(listener);
    }

    public String getNombreUsuario() {
        return usuario.getText();
    }

    public String getContrasena() {
        return new String(contraseña.getPassword());
    }
}