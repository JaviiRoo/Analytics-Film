package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import controlador.VerPeliculasControlador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.List;
import modelo.Pelicula;

public class VerPeliculas extends JFrame {

    private JTable tablaPeliculas;
    private JButton botonAgregar, botonEliminar, botonActualizar, botonBuscar;
    private JTextField busqueda;
    private DefaultTableModel modelo;
    private VerPeliculasControlador controlador;

    public VerPeliculas() {

        setTitle("Películas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con fondo degradado
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(40, 50, 78);
                Color color2 = new Color(23, 162, 184);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(panelPrincipal);

        // Título superior
        JLabel labelTitulo = new JLabel("Mis Películas", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Sans Serif", Font.BOLD, 30));
        labelTitulo.setForeground(Color.ORANGE);
        panelPrincipal.add(labelTitulo, BorderLayout.NORTH);

        // Configuración de la tabla
        String[] columnasNombres = {"Título", "Año", "Género", "Calificación", "Sinopsis", "Trailer"};
        modelo = new DefaultTableModel(columnasNombres, 0);
        tablaPeliculas = new JTable(modelo);
        tablaPeliculas.setFillsViewportHeight(true);
        tablaPeliculas.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        tablaPeliculas.setRowHeight(25);
        tablaPeliculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPeliculas.setDefaultEditor(Object.class, null); // Deshabilitar edición de celdas

        // Estilo de encabezado de la tabla
        JTableHeader encabezado = tablaPeliculas.getTableHeader();
        encabezado.setFont(new Font("Sans Serif", Font.BOLD, 16));
        encabezado.setBackground(new Color(40, 50, 78));
        encabezado.setForeground(Color.WHITE);

        // Centrar contenido de las celdas
        DefaultTableCellRenderer centrarRenderizado = new DefaultTableCellRenderer();
        centrarRenderizado.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaPeliculas.getColumnCount(); i++) {
            tablaPeliculas.getColumnModel().getColumn(i).setCellRenderer(centrarRenderizado);
        }

        JScrollPane scrollPane = new JScrollPane(tablaPeliculas);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Panel de búsqueda y botones
        JPanel panelBusquedaYBotones = new JPanel();
        panelBusquedaYBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBusquedaYBotones.setOpaque(false);

        // Campo de búsqueda
        busqueda = new JTextField(20);
        panelBusquedaYBotones.add(busqueda);

        // Botón "Buscar"
        botonBuscar = new JButton("Buscar");
        estiloDeBoton(botonBuscar, new Color(23, 162, 184));
        panelBusquedaYBotones.add(botonBuscar);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false);

        // Botón "Agregar Película"
        botonAgregar = new JButton("Agregar Película");
        estiloDeBoton(botonAgregar, new Color(40, 167, 69));
        panelBotones.add(botonAgregar);

        // Botón "Eliminar Película"
        botonEliminar = new JButton("Eliminar Película");
        estiloDeBoton(botonEliminar, new Color(220, 53, 69));
        panelBotones.add(botonEliminar);

        // Botón "Actualizar Película"
        botonActualizar = new JButton("Actualizar Película");
        estiloDeBoton(botonActualizar, new Color(0, 123, 255));
        panelBotones.add(botonActualizar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        panelPrincipal.add(panelBusquedaYBotones, BorderLayout.NORTH);

        // Acción del botón "Buscar"
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPelicula();
            }
        });

        // Mostrar sinopsis completa al hacer clic
        tablaPeliculas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int columnas = tablaPeliculas.getSelectedColumn();
                int filas = tablaPeliculas.getSelectedRow();

                if (columnas == 4 && filas != -1) { // Columna "Sinopsis"
                    String sinopsis = (String) tablaPeliculas.getValueAt(filas, columnas);
                    if (sinopsis != null && !sinopsis.isEmpty()) {
                        mostrarSinopsis(sinopsis);
                    }
                } else if (columnas == 5 && filas != -1) { // Columna "Trailer"
                    String url = (String) tablaPeliculas.getValueAt(filas, columnas);
                    if (url != null && !url.isEmpty()) {
                        try {
                            Desktop.getDesktop().browse(new URI(url));
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error.", "¡Imposible abrir el enlace para el trailer.!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    // Método para mostrar la sinopsis en un JDialog
    private void mostrarSinopsis(String sinopsis) {
        JDialog dialogoSinopsis = new JDialog(this, "Descripción completa", true);
        dialogoSinopsis.setSize(400, 300);
        dialogoSinopsis.setLocationRelativeTo(this);

        JTextArea areaTexto = new JTextArea(sinopsis);
        areaTexto.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        dialogoSinopsis.add(scrollPane);
        dialogoSinopsis.setVisible(true);
    }

    // Método para estilizar los botones
    private void estiloDeBoton(JButton boton, Color colorDeFondo) {
        boton.setFocusPainted(false);
        boton.setFont(new Font("Sans Serif", Font.BOLD, 17));
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorDeFondo);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Método para realizar la búsqueda de películas
    private void buscarPelicula() {
        String terminoBusqueda = busqueda.getText().trim();

        // Si no hay texto en el campo de búsqueda, cargamos todas las películas
        if (terminoBusqueda.isEmpty()) {
            controlador.cargarPeliculas();
        } else {
            controlador.buscarPeliculas(terminoBusqueda);
        }
    }

    // Getters
    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public JButton getBotonActualizar() {
        return botonActualizar;
    }

    public JButton getBotonBuscar() {
        return botonBuscar;
    }

    public JTextField busqueda() {
        return busqueda;
    }

    public JTable getTablaPeliculas() {
        return tablaPeliculas;
    }

    // Método para setear el controlador
    public void setControlador(VerPeliculasControlador controlador) {
        this.controlador = controlador;
    }

    public void mostrarTodasLasPeliculas(List<Pelicula> peliculas) {
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar los datos
        for (Pelicula pelicula : peliculas) {
            modelo.addRow(new Object[] {
                pelicula.getTitulo(),
                pelicula.getAño(),
                pelicula.getGenero(),
                pelicula.getCalificacion(),
                pelicula.getSinopsis(),
                pelicula.getTrailerUrl()
            });
        }
    }
}
