package vista;

import javax.swing.*;
import java.awt.*;

public class FormularioSerie extends JDialog {

    private JTextField txtTitulo, txtTemporadas, txtAño, txtCapitulos, txtCalificacion, txtTrailer, txtGenero, txtSinopsis;;
    private JButton botonGuardar, botonCancelar;

    public FormularioSerie(Frame owner) {
        super(owner, "Formulario de serie.", true);
        setLayout(new GridBagLayout());
        setSize(600, 600);
        setLocationRelativeTo(owner);
        getContentPane().setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Encabezado del formulario
        JLabel encabezado = new JLabel("Añada su nueva serie:", JLabel.CENTER);
        encabezado.setFont(new Font("Segoe UI", Font.BOLD, 20));
        encabezado.setForeground(new Color(52, 73, 94));
        gbc.gridwidth = 2;
        add(encabezado, gbc);

        // Fuente personalizada
        Font labelFuente = new Font("Segoe UI", Font.BOLD, 14);
        Font fuenteCampoTexto = new Font("Segoe UI", Font.PLAIN, 14);
        Font botonFuente = new Font("Segoe UI", Font.BOLD, 14);

        // Título
        gbc.gridwidth = 1;
        gbc.gridy++;
        addLabel("Título:", labelFuente, gbc);
        gbc.gridx++;
        txtTitulo = crearCampoDeTexto(fuenteCampoTexto);
        add(txtTitulo, gbc);

        // Temporadas
        gbc.gridy++;
        gbc.gridx = 0;
        addLabel("Temporadas:", labelFuente, gbc);
        gbc.gridx++;
        txtTemporadas = crearCampoDeTexto(fuenteCampoTexto);
        add(txtTemporadas, gbc);

        // Año
        gbc.gridy++;
        gbc.gridx = 0;
        addLabel("Año:", labelFuente, gbc);
        gbc.gridx++;
        txtAño = crearCampoDeTexto(fuenteCampoTexto);
        add(txtAño, gbc);

        // Capítulos
        gbc.gridy++;
        gbc.gridx = 0;
        addLabel("Capítulos:", labelFuente, gbc);
        gbc.gridx++;
        txtCapitulos = crearCampoDeTexto(fuenteCampoTexto);
        add(txtCapitulos, gbc);

        // Calificación
        gbc.gridy++;
        gbc.gridx = 0;
        addLabel("Calificación (0-10):", labelFuente, gbc);
        gbc.gridx++;
        txtCalificacion = crearCampoDeTexto(fuenteCampoTexto);
        add(txtCalificacion, gbc);

        // Género
        gbc.gridy++;
        gbc.gridx = 0;
        addLabel("Género:", labelFuente, gbc);
        gbc.gridx++;
        txtGenero = crearCampoDeTexto(fuenteCampoTexto);
        add(txtGenero, gbc);

        // Sinopsis
        gbc.gridy++;
        gbc.gridx = 0;
        addLabel("Sinopsis:", labelFuente, gbc);
        gbc.gridx++;
        txtSinopsis = crearCampoDeTexto(fuenteCampoTexto);
        add(txtSinopsis, gbc);

        // Tráiler
        gbc.gridy++;
        gbc.gridx = 0;
        addLabel("Tráiler (URL):", labelFuente, gbc);
        gbc.gridx++;
        txtTrailer = crearCampoDeTexto(fuenteCampoTexto);
        add(txtTrailer, gbc);

        // Botones
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
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
    private void addLabel(String text, Font font, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(new Color(52, 73, 94));
        add(label, gbc);
    }

    // Método para crear campos de texto con estilo
    private JTextField crearCampoDeTexto(Font font) {
        JTextField textField = new JTextField(20);
        textField.setFont(font);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        textField.setBackground(Color.WHITE);
        return textField;
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

    // Métodos getter y setter (igual que antes)
    public String getTitulo() {
        return txtTitulo.getText();
    }

    public int getTemporadas() {
        try {
            return Integer.parseInt(txtTemporadas.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El número de temporadas debe ser un número válido.");
        }
    }

    public int getAño() {
        try {
            return Integer.parseInt(txtAño.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El año debe ser un número válido.");
        }
    }

    public int getCapitulos() {
        try {
            return Integer.parseInt(txtCapitulos.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El número de capítulos debe ser un número válido.");
        }
    }

    public int getCalificacion() {
        try {
            int calificacion = Integer.parseInt(txtCalificacion.getText().trim());
            if (calificacion < 0 || calificacion > 10) {
                throw new IllegalArgumentException("La calificación debe estar entre 0 y 10.");
            }
            return calificacion;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La calificación debe ser un número válido entre 0 y 10.");
        }
    }

    public String getGenero() {
        return txtGenero.getText();
    }

    public String getSinopsis() {
        return txtSinopsis.getText();
    }

    public String getTrailerUrl() {
        return txtTrailer.getText();
    }

    public JButton getBotonGuardar() {
        return botonGuardar;
    }

    public JButton getBotonCancelar() {
        return botonCancelar;
    }

    public void setTitulo(String titulo) {
        txtTitulo.setText(titulo);
    }

    public void setTemporadas(int temporadas) {
        txtTemporadas.setText(String.valueOf(temporadas));
    }

    public void setAño(int ano) {
        txtAño.setText(String.valueOf(ano));
    }

    public void setCapitulos(int capitulos) {
        txtCapitulos.setText(String.valueOf(capitulos));
    }

    public void setCalificacion(int calificacion) {
        txtCalificacion.setText(String.valueOf(calificacion));
    }

    public void setGenero(String genero) {
        txtGenero.setText(genero);
    }

    public void setSinopsis(String sinopsis) {
        txtSinopsis.setText(sinopsis);
    }

    public void setTrailerUrl(String trailerUrl) {
        txtTrailer.setText(trailerUrl);
    }
}