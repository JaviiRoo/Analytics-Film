package controlador;

import modelo.Pelicula;
import modelo.PeliculaDAO;
import vista.VerPeliculas;
import vista.FormularioPelicula;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerPeliculasControlador {
    private PeliculaDAO peliculaDAO;
    private VerPeliculas vista;
    private int usuarioId;

    public VerPeliculasControlador(PeliculaDAO peliculaDAO, VerPeliculas vista, int usuarioId) {
        this.peliculaDAO = peliculaDAO;
        this.vista = vista;
        this.usuarioId = usuarioId;

        // Establecer el controlador en la vista
        this.vista.setControlador(this);

        cargarPeliculas();

        // Listener para el botón "Agregar Película"
        vista.getBotonAgregar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPelicula();
            }
        });

        // Listener para el botón "Eliminar Película"
        vista.getBotonEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPelicula();
            }
        });

        // Listener para el botón "Actualizar Película"
        vista.getBotonActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = vista.getTablaPeliculas().getSelectedRow();

                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(vista, "Por favor, seleccione una película para actualizar.");
                    return;
                }

                String titulo = vista.getTablaPeliculas().getValueAt(filaSeleccionada, 0).toString();
                Pelicula pelicula = peliculaDAO.buscarPorTitulo(titulo);
                if (pelicula != null) {
                    actualizarPelicula(pelicula.getId()); // Llamar al método para actualizar la película
                } else {
                    JOptionPane.showMessageDialog(vista, "No se encontró la película seleccionada.");
                }
            }
        });

        // Listener para el campo de búsqueda
        vista.busqueda().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPeliculas(null);
            }
        });
    }

    public void cargarPeliculas() {
        List<Pelicula> peliculas = peliculaDAO.obtenerPeliculasPorUsuario(usuarioId);
        vista.mostrarTodasLasPeliculas(peliculas);
    }

    private void agregarPelicula() {
        FormularioPelicula formulario = new FormularioPelicula(vista);

        formulario.getBtnGuardar().addActionListener(e -> {
            try {
                String titulo = formulario.getTitulo();
                int ano = formulario.getAño();
                String genero = formulario.getGenero();
                int calificacion = formulario.getCalificacion();
                String sinopsis = formulario.getSinopsis();
                String trailerUrl = formulario.getTrailerUrl();

                Pelicula pelicula = new Pelicula(titulo, ano, genero, calificacion, sinopsis, trailerUrl, usuarioId);

                if (peliculaDAO.agregarPelicula(pelicula)) {
                    JOptionPane.showMessageDialog(vista, "Película agregada correctamente.");
                    cargarPeliculas();
                    formulario.dispose();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al agregar la película.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage());
            }
        });

        formulario.getBtnCancelar().addActionListener(e -> formulario.dispose());

        formulario.setVisible(true);
    }

    // Método para eliminar película
    private void eliminarPelicula() {
        try {
            int filaSeleccionada = vista.getTablaPeliculas().getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vista, "Por favor, seleccione una película para eliminar.");
                return;
            }

            String titulo = vista.getTablaPeliculas().getValueAt(filaSeleccionada, 0).toString();
            Pelicula pelicula = peliculaDAO.buscarPorTitulo(titulo);
            if (pelicula == null) {
                JOptionPane.showMessageDialog(vista, "No se encontró la película seleccionada.");
                return;
            }

            // Confirmación con botones en español
            int confirmacion = JOptionPane.showOptionDialog(
                vista, 
                "¿Está seguro de que desea eliminar esta película?", 
                "Confirmación", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                new Object[] { "Sí", "No" }, // Botones personalizados
                "Sí" // El botón predeterminado
            );

            if (confirmacion == 0) { // Si el usuario presiona "Sí"
                if (peliculaDAO.eliminarPelicula(pelicula.getId())) {
                    JOptionPane.showMessageDialog(vista, "Película eliminada correctamente.");
                    cargarPeliculas();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al eliminar la película.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage());
        }
    }

    public void actualizarPelicula(int peliculaId) {
        Pelicula pelicula = peliculaDAO.obtenerPelicula(peliculaId);
        if (pelicula == null) {
            JOptionPane.showMessageDialog(vista, "No se encontró la película seleccionada.");
            return;
        }

        FormularioPelicula formulario = new FormularioPelicula(vista);

        formulario.setTitulo(pelicula.getTitulo());
        formulario.setAño(pelicula.getAño());
        formulario.setGenero(pelicula.getGenero());
        formulario.setCalificacion(pelicula.getCalificacion());
        formulario.setSinopsis(pelicula.getSinopsis());
        formulario.setTrailerUrl(pelicula.getTrailerUrl());

        formulario.getBtnGuardar().addActionListener(e -> {
            try {
                pelicula.setTitulo(formulario.getTitulo());
                pelicula.setAño(formulario.getAño());
                pelicula.setGenero(formulario.getGenero());
                pelicula.setCalificacion(formulario.getCalificacion());
                pelicula.setSinopsis(formulario.getSinopsis());
                pelicula.setTrailerUrl(formulario.getTrailerUrl());

                if (peliculaDAO.actualizarPelicula(pelicula)) {
                    JOptionPane.showMessageDialog(vista, "Película actualizada correctamente.");
                    cargarPeliculas();
                    formulario.dispose();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al actualizar la película.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage());
            }
        });

        formulario.getBtnCancelar().addActionListener(e -> formulario.dispose());
        formulario.setVisible(true);
    }

    // Método para buscar películas
    public void buscarPeliculas(String terminoBusqueda) {
        System.out.println("Buscando películas con el término: " + terminoBusqueda);
        List<Pelicula> peliculas = peliculaDAO.buscarPeliculas(usuarioId, terminoBusqueda);
        vista.mostrarTodasLasPeliculas(peliculas);
    }
}
