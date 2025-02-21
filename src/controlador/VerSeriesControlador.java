package controlador;

import modelo.Serie;
import modelo.SerieDAO;
import vista.VerSeries;
import vista.FormularioSerie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerSeriesControlador {
    private SerieDAO serieDAO;
    private VerSeries vista;
    private int usuarioId;

    public VerSeriesControlador(SerieDAO serieDAO, VerSeries vista, int usuarioId) {
        this.serieDAO = serieDAO;
        this.vista = vista;
        this.usuarioId = usuarioId;

        // Establecer el controlador en la vista
        this.vista.setControlador(this);

        cargarSeries();

        // Listener para el botón "Agregar Serie"
        vista.getBotonAgregar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarSerie();
            }
        });

        // Listener para el botón "Eliminar Serie"
        vista.getBotonEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarSerie();
            }
        });

        // Listener para el botón "Actualizar Serie"
        vista.getBotonActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = vista.getTablaSeries().getSelectedRow();

                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(vista, "Por favor, seleccione una serie para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int serieId = Integer.parseInt(vista.getTablaSeries().getValueAt(filaSeleccionada, 0).toString());
                    actualizarSerie(serieId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, "El valor seleccionado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Listener para el botón "Buscar"
        vista.getBotonBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarSeries();
            }
        });
    }

    private void cargarSeries() {
        List<Serie> series = serieDAO.obtenerSeriesDeUsuarios(usuarioId);
        vista.mostrarTodasLasSeries(series);
    }

    private void buscarSeries() {
        String terminoBusqueda = vista.Busqueda().getText().trim();

        if (terminoBusqueda.isEmpty()) {
            cargarSeries(); // Si no hay término de búsqueda, cargamos todas las series
            return;
        }

        List<Serie> seriesEncontradas = serieDAO.buscarSeries(usuarioId, terminoBusqueda);
        vista.mostrarTodasLasSeries(seriesEncontradas);
    }

    private void agregarSerie() {
        FormularioSerie formulario = new FormularioSerie(vista);

        formulario.getBotonGuardar().addActionListener(e -> {
            try {
                String titulo = formulario.getTitulo();
                int temporadas = formulario.getTemporadas();
                int capitulos = formulario.getCapitulos();
                int ano = formulario.getAño();
                String genero = formulario.getGenero(); // Ahora el género es un String
                int calificacion = formulario.getCalificacion();
                String sinopsis = formulario.getSinopsis();
                String trailerUrl = formulario.getTrailerUrl(); // Capturar el tráiler

                Serie serie = new Serie(titulo, temporadas, capitulos, genero, ano, calificacion, sinopsis, trailerUrl, usuarioId);

                if (serieDAO.agregarSerie(serie)) {
                    JOptionPane.showMessageDialog(vista, "Serie agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarSeries();
                    formulario.dispose();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al agregar la serie.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        formulario.getBotonCancelar().addActionListener(e -> formulario.dispose());

        formulario.setVisible(true);
    }

    private void eliminarSerie() {
        try {
            int filaSeleccionada = vista.getTablaSeries().getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vista, "Por favor, seleccione una serie para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int serieId = Integer.parseInt(vista.getTablaSeries().getValueAt(filaSeleccionada, 0).toString());

                // Cambio aquí: los botones "Sí" y "No" en lugar de "Yes" y "No"
                int confirmacion = JOptionPane.showOptionDialog(
                    vista, 
                    "¿Está seguro de que desea eliminar esta serie?", 
                    "Confirmación", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    new Object[] { "Sí", "No" }, // Botones personalizados en español
                    "Sí" // El botón predeterminado
                );

                if (confirmacion == 0) { // Si el usuario presiona "Sí"
                    if (serieDAO.eliminarSerie(serieId)) {
                        JOptionPane.showMessageDialog(vista, "Serie eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        cargarSeries();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Error al eliminar la serie.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El valor seleccionado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarSerie(int serieId) {
        Serie serie = serieDAO.obtenerSerie(serieId);
        if (serie == null) {
            JOptionPane.showMessageDialog(vista, "No se encontró la serie.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        FormularioSerie formulario = new FormularioSerie(vista);

        formulario.setTitulo(serie.getTitulo());
        formulario.setTemporadas(serie.getTemporadas());
        formulario.setCapitulos(serie.getCapitulos());
        formulario.setAño(serie.getAño());
        formulario.setGenero(serie.getGenero()); // Ahora el género es un String
        formulario.setCalificacion(serie.getCalificacion());
        formulario.setSinopsis(serie.getSinopsis());
        formulario.setTrailerUrl(serie.getTrailerUrl()); // Prellenar el campo del tráiler

        formulario.getBotonGuardar().addActionListener(e -> {
            try {
                serie.setTitulo(formulario.getTitulo());
                serie.setTemporadas(formulario.getTemporadas());
                serie.setCapitulos(formulario.getCapitulos());
                serie.setAño(formulario.getAño());
                serie.setGenero(formulario.getGenero());
                serie.setCalificacion(formulario.getCalificacion());
                serie.setSinopsis(formulario.getSinopsis());
                serie.setTrailerUrl(formulario.getTrailerUrl());

                if (serieDAO.actualizarSerie(serie)) {
                    JOptionPane.showMessageDialog(vista, "Serie actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarSeries();
                    formulario.dispose();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al actualizar la serie.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        formulario.getBotonCancelar().addActionListener(e -> formulario.dispose());
        formulario.setVisible(true);
    }
}
