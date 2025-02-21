package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Registro extends JFrame {
    private JTextField usuario;
    private JPasswordField contraseña;
    private JTextField correo;
    private JButton botonRegistrar;
    private JButton botonCancelar;

    public Registro() {
        // Configuración básica de la ventana
        setTitle("Registro");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con fondo
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Fondo degradado
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(80, 140, 220); // Azul más oscuro
                Color color2 = new Color(220, 240, 255); // Azul claro
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Márgenes internos
        add(panelPrincipal);

        // Título
        JLabel labelTitulo = new JLabel("Analytics Film", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Times", Font.BOLD, 30));
        labelTitulo.setForeground(Color.BLACK);
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        panelPrincipal.add(labelTitulo);

        // Panel para los campos de entrada
        JPanel panelDeCampos = new JPanel();
        panelDeCampos.setOpaque(false);
        panelDeCampos.setLayout(new BoxLayout(panelDeCampos, BoxLayout.Y_AXIS));

        // Campo de Usuario
        JLabel labelUsuario = new JLabel("Nombre de Usuario:");
        labelUsuario.setForeground(Color.BLACK);
        labelUsuario.setFont(new Font("Times", Font.PLAIN, 16));
        labelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        usuario = new JTextField(20);
        usuario.setMaximumSize(new Dimension(300, 30));
        usuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelDeCampos.add(labelUsuario);
        panelDeCampos.add(Box.createRigidArea(new Dimension(0, 5))); // Espaciado
        panelDeCampos.add(usuario);
        panelDeCampos.add(Box.createRigidArea(new Dimension(0, 15))); // Espaciado

        // Campo de Contraseña
        JLabel labelContraseña = new JLabel("Contraseña:");
        labelContraseña.setForeground(Color.BLACK);
        labelContraseña.setFont(new Font("Times", Font.PLAIN, 18));
        labelContraseña.setAlignmentX(Component.CENTER_ALIGNMENT);

        contraseña = new JPasswordField(20);
        contraseña.setMaximumSize(new Dimension(300, 30));
        contraseña.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelDeCampos.add(labelContraseña);
        panelDeCampos.add(Box.createRigidArea(new Dimension(0, 5))); // Espaciado
        panelDeCampos.add(contraseña);
        panelDeCampos.add(Box.createRigidArea(new Dimension(0, 15))); // Espaciado

        // Campo de Correo Electrónico
        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        lblCorreo.setForeground(Color.BLACK);
        lblCorreo.setFont(new Font("Times", Font.PLAIN, 16));
        lblCorreo.setAlignmentX(Component.CENTER_ALIGNMENT);

        correo = new JTextField(20);
        correo.setMaximumSize(new Dimension(300, 30));
        correo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelDeCampos.add(lblCorreo);
        panelDeCampos.add(Box.createRigidArea(new Dimension(0, 5))); // Espaciado
        panelDeCampos.add(correo);
        panelDeCampos.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado

        panelPrincipal.add(panelDeCampos);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));

        botonRegistrar = new JButton("Registrarse");
        botonRegistrar.setBackground(new Color(40, 167, 69)); // Verde
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setFocusPainted(false);
        botonRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botonRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonCancelar = new JButton("Cancelar");
        botonCancelar.setBackground(new Color(220, 53, 69)); // Rojo
        botonCancelar.setForeground(Color.WHITE);
        botonCancelar.setFocusPainted(false);
        botonCancelar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botonCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBotones.add(Box.createHorizontalGlue());
        panelBotones.add(botonRegistrar);
        panelBotones.add(Box.createRigidArea(new Dimension(20, 0))); // Espaciado entre botones
        panelBotones.add(botonCancelar);
        panelBotones.add(Box.createHorizontalGlue());

        panelPrincipal.add(panelBotones);
    }

    // Getters
    public String getNombreUsuario() {
        return usuario.getText();
    }

    public String getContraseña() {
        return new String(contraseña.getPassword());
    }

    public String getCorreo() {
        return correo.getText();
    }

    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    public JButton getBotonCancelar() {
        return botonCancelar;
    }

    // Métodos para añadir los listeners
    public void addRegistrarListener(ActionListener listener) {
        botonRegistrar.addActionListener(listener);
    }

    public void addCancelarListener(ActionListener listener) {
        botonCancelar.addActionListener(listener);
    }
}
