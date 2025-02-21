package vista;

import modelo.PeliculaDAO;
import modelo.SerieDAO;
import modelo.UsuarioDAO;

import java.sql.SQLException;
import javax.swing.*;

import controlador.UsuarioControlador;
import controlador.VerPeliculasControlador;
import controlador.VerSeriesControlador;

import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {
    private int usuarioId;
    private String nombreDeUsuario;
    private UsuarioControlador usuarioControlador;
    private JLabel labelPeliculasVistas, labelSeriesVistas;

    public VentanaPrincipal(int usuarioId, String nombreUsuario, UsuarioControlador usuarioControlador) {
        this.usuarioId = usuarioId;
        this.nombreDeUsuario = nombreUsuario;
        this.usuarioControlador = usuarioControlador;

        setTitle("Bienvenido a Analytics Film.");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new GridLayout(4, 1, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(240, 240, 240));
        add(panelPrincipal, BorderLayout.CENTER);

        // Tarjeta de saludo
        JPanel tarjetaDeSaludo = creacionDeTarjeta("¡Bienvenido, " + nombreUsuario + "!", new Color(40, 167, 69), "👋");
        panelPrincipal.add(tarjetaDeSaludo);

        // Tarjetas de estadísticas
        JPanel panelDeEstadisticas = new JPanel(new GridLayout(1, 2, 10, 10));
        panelDeEstadisticas.setOpaque(false);
        int peliculasVistas = usuarioControlador.obtenerPeliculasVistas(usuarioId);
        int seriesVistas = usuarioControlador.obtenerSeriesVistas(usuarioId);
        labelPeliculasVistas = new JLabel("Películas vistas: " + peliculasVistas, SwingConstants.CENTER);
        labelSeriesVistas = new JLabel("Series vistas: " + seriesVistas, SwingConstants.CENTER);
        panelDeEstadisticas.add(creacionDeTarjetaConLabel(labelPeliculasVistas, new Color(255, 193, 7), "🎬"));
        panelDeEstadisticas.add(creacionDeTarjetaConLabel(labelSeriesVistas, new Color(255, 87, 34), "📺"));
        panelPrincipal.add(panelDeEstadisticas);

        // Tarjetas de acciones
        JPanel panelDeOpciones = new JPanel(new GridLayout(1, 2, 10, 10));
        panelDeOpciones.setOpaque(false);
        panelDeOpciones.add(creacionTarjetaDeInteraccion("Ver Películas", new Color(0, 123, 255), "🎥", this::abrirVerPeliculas));
        panelDeOpciones.add(creacionTarjetaDeInteraccion("Ver Series", new Color(76, 175, 80), "📺", this::abrirVerSeries));
        panelPrincipal.add(panelDeOpciones);

        // Tarjeta de cerrar sesión
        JPanel tarjetaDeCerrarSesion = creacionTarjetaDeInteraccion("Cerrar Sesión", new Color(220, 53, 69), "❌", this::cerrarSesion);
        panelPrincipal.add(tarjetaDeCerrarSesion);
    }

    private JPanel creacionDeTarjeta(String texto, Color color, String icono) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(color);
        tarjeta.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelIcono = new JLabel(icono, SwingConstants.CENTER);
        labelIcono.setFont(new Font("Sans Serif", Font.PLAIN, 40));
        labelIcono.setForeground(Color.WHITE);
        labelIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelTexto = new JLabel(texto, SwingConstants.CENTER);
        labelTexto.setFont(new Font("Sans Serif", Font.BOLD, 18));
        labelTexto.setForeground(Color.WHITE);
        labelTexto.setAlignmentX(Component.CENTER_ALIGNMENT);

        tarjeta.add(labelIcono);
        tarjeta.add(Box.createRigidArea(new Dimension(0, 10)));
        tarjeta.add(labelTexto);
        return tarjeta;
    }

    private JPanel creacionDeTarjetaConLabel(JLabel label, Color color, String icono) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(color);
        tarjeta.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelIcono = new JLabel(icono, SwingConstants.CENTER);
        labelIcono.setFont(new Font("Sans Serif", Font.PLAIN, 40));
        labelIcono.setForeground(Color.WHITE);
        labelIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

        label.setFont(new Font("Sans Serif", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        tarjeta.add(labelIcono);
        tarjeta.add(Box.createRigidArea(new Dimension(0, 10)));
        tarjeta.add(label);
        return tarjeta;
    }

    private JPanel creacionTarjetaDeInteraccion(String texto, Color color, String icono, Runnable accion) {
        JPanel tarjeta = creacionDeTarjeta(texto, color, icono);
        tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tarjeta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                accion.run();
            }
        });
        return tarjeta;
    }

    private void abrirVerPeliculas() {
        VerPeliculas verPeliculas = new VerPeliculas();
        try {
            new VerPeliculasControlador(new PeliculaDAO(), verPeliculas, usuarioId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        verPeliculas.setVisible(true);
        actualizarEstadisticas();
    }

    private void abrirVerSeries() {
        VerSeries verSeries = new VerSeries();
        try {
            new VerSeriesControlador(new SerieDAO(), verSeries, usuarioId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        verSeries.setVisible(true);
        actualizarEstadisticas();
    }

    private void cerrarSesion() {
        dispose();
        Login login = new Login();
        try {
            new UsuarioControlador(new UsuarioDAO(), login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Sesión cerrada.");
    }

    // Método para actualizar las estadísticas en la interfaz de usuario
    public void actualizarEstadisticas() {
        int peliculasVistas = usuarioControlador.obtenerPeliculasVistas(usuarioId);
        int seriesVistas = usuarioControlador.obtenerSeriesVistas(usuarioId);

        labelPeliculasVistas.setText("Películas vistas: " + peliculasVistas);
        labelSeriesVistas.setText("Series vistas: " + seriesVistas);
    }
}
