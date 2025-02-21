package vista;

import javax.swing.*;
import java.awt.*;

public class FormularioPelicula extends JDialog {
	
    private JTextField txtTitulo, txtAño, txtCalificacion, txtSinopsis, txtTrailer, txtGenero;
    private JButton botonGuardar, botonCancelar;

    public FormularioPelicula(Frame owner) {
        super(owner, "Formulario de película.", true);
        setLayout(new GridBagLayout());
        setSize(600, 500);
        setLocationRelativeTo(owner);
        getContentPane().setBackground(new Color(240, 248, 255)); // Fondo claro y moderno

        // Configuración de GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15); // Márgenes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Encabezado del formulario
        JLabel encabezado = new JLabel("Añada su nueva película:", JLabel.CENTER);
        encabezado.setFont(new Font("Segoe UI", Font.BOLD, 20));
        encabezado.setForeground(new Color(52, 73, 94)); // Azul oscuro
        gbc.gridwidth = 2; // Ocupa dos columnas
        add(encabezado, gbc);

        // Fuente personalizada
        Font labelFuente = new Font("Segoe UI", Font.BOLD, 14);
        Font fuenteCampoTexto= new Font("Segoe UI", Font.PLAIN, 14);
        Font botonFuente = new Font("Segoe UI", Font.BOLD, 14);

        // Título
        gbc.gridwidth = 1;
        gbc.gridy++;
        añadirLabel("Título:", labelFuente, gbc);
        gbc.gridx++;
        txtTitulo = crearCampoTexto(fuenteCampoTexto);
        add(txtTitulo, gbc);

        // Año
        gbc.gridy++;
        gbc.gridx = 0;
        añadirLabel("Año:", labelFuente, gbc);
        gbc.gridx++;
        txtAño = crearCampoTexto(fuenteCampoTexto);
        add(txtAño, gbc);

        // Género
        gbc.gridy++;
        gbc.gridx = 0;
        añadirLabel("Género:", labelFuente, gbc);
        gbc.gridx++;
        txtGenero = crearCampoTexto(fuenteCampoTexto);
        add(txtGenero, gbc);

        // Calificación
        gbc.gridy++;
        gbc.gridx = 0;
        añadirLabel("Calificación (0-10):", labelFuente, gbc);
        gbc.gridx++;
        txtCalificacion = crearCampoTexto(fuenteCampoTexto);
        add(txtCalificacion, gbc);

        // Sinopsis
        gbc.gridy++;
        gbc.gridx = 0;
        añadirLabel("Sinopsis:", labelFuente, gbc);
        gbc.gridx++;
        txtSinopsis = crearCampoTexto(fuenteCampoTexto);
        add(txtSinopsis, gbc);

        // Tráiler
        gbc.gridy++;
        gbc.gridx = 0;
        añadirLabel("Tráiler (URL):", labelFuente, gbc);
        gbc.gridx++;
        txtTrailer = crearCampoTexto(fuenteCampoTexto);
        add(txtTrailer, gbc);

        // Botones
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Ocupa dos columnas
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botonPanel.setBackground(new Color(240, 248, 255));

        botonGuardar = crearBoton("Guardar", new Color(39, 174, 96), botonFuente);
        botonCancelar = crearBoton("Cancelar", new Color(192, 57, 43), botonFuente);

        botonPanel.add(botonGuardar);
        botonPanel.add(botonCancelar);
        add(botonPanel, gbc);
    }

    // Método para crear etiquetas con estilo
    private void añadirLabel(String text, Font font, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(new Color(52, 73, 94)); // Azul oscuro
        add(label, gbc);
    }

    // Método para crear campos de texto con estilo
    private JTextField crearCampoTexto(Font font) {
        JTextField campoTexto = new JTextField(20);
        campoTexto.setFont(font);
        campoTexto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        campoTexto.setBackground(Color.WHITE);
        return campoTexto;
    }

    // Método para crear botones con estilo
    private JButton crearBoton(String text, Color color, Font font) {
        JButton boton = new JButton(text);
        boton.setFont(font);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });

        return boton;
    }

    // Métodos getter y setter (sin cambios)
    public String getTitulo() {
        return txtTitulo.getText();
    }

    public int getAño() {
        try {
            return Integer.parseInt(txtAño.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El año debe ser un número válido.");
        }
    }

    public String getGenero() {
        return txtGenero.getText();
    }

    public int getCalificacion() {
        try {
            return Integer.parseInt(txtCalificacion.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La calificación debe ser un número válido entre 0 y 10.");
        }
    }

    public String getSinopsis() {
        return txtSinopsis.getText();
    }

    public String getTrailerUrl() {
        return txtTrailer.getText();
    }

    public JButton getBtnGuardar() {
        return botonGuardar;
    }

    public JButton getBtnCancelar() {
        return botonCancelar;
    }

    public void setTitulo(String titulo) {
        txtTitulo.setText(titulo);
    }

    public void setAño(int ano) {
        txtAño.setText(String.valueOf(ano));
    }

    public void setGenero(String genero) {
        txtGenero.setText(genero);
    }

    public void setCalificacion(int calificacion) {
        txtCalificacion.setText(String.valueOf(calificacion));
    }

    public void setSinopsis(String sinopsis) {
        txtSinopsis.setText(sinopsis);
    }

    public void setTrailerUrl(String trailerUrl) {
        txtTrailer.setText(trailerUrl);
    }
}
