package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SerieDAO {
    private Connection conexion;

    public SerieDAO() throws SQLException {
        conexion = ConexionBD.obtenerConexion();
    }

    // Método para obtener las series de un usuario
    public List<Serie> obtenerSeriesDeUsuarios(int usuarioId) {
        String sql = "SELECT * FROM Series WHERE usuario_id = ?";
        List<Serie> series = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Serie serie = new Serie(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getInt("temporadas"),
                    rs.getInt("capitulos"),
                    rs.getString("genero"), // Cambiado a String
                    rs.getInt("ano"),
                    rs.getInt("calificacion"),
                    rs.getString("sinopsis"),
                    rs.getString("trailer_url"),
                    rs.getInt("usuario_id")
                );
                series.add(serie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return series;
    }

    // Método para eliminar una serie
    public boolean eliminarSerie(int id) {
        String sql = "DELETE FROM Series WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para agregar una serie
    public boolean agregarSerie(Serie serie) {
        String sql = "INSERT INTO Series (titulo, temporadas, capitulos, genero, ano, calificacion, sinopsis, trailer_url, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, serie.getTitulo());
            stmt.setInt(2, serie.getTemporadas());
            stmt.setInt(3, serie.getCapitulos());
            stmt.setString(4, serie.getGenero()); // Cambiado a String
            stmt.setInt(5, serie.getAño());
            stmt.setInt(6, serie.getCalificacion());
            stmt.setString(7, serie.getSinopsis());
            stmt.setString(8, serie.getTrailerUrl());
            stmt.setInt(9, serie.getUsuarioId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al agregar la serie: " + e.getMessage());
            return false;
        }
    }

    // Método para actualizar una serie
    public boolean actualizarSerie(Serie serie) {
        String sql = "UPDATE Series SET titulo = ?, temporadas = ?, capitulos = ?, genero = ?, ano = ?, calificacion = ?, sinopsis = ?, trailer_url = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, serie.getTitulo());
            stmt.setInt(2, serie.getTemporadas());
            stmt.setInt(3, serie.getCapitulos());
            stmt.setString(4, serie.getGenero()); // Cambiado a String
            stmt.setInt(5, serie.getAño());
            stmt.setInt(6, serie.getCalificacion());
            stmt.setString(7, serie.getSinopsis());
            stmt.setString(8, serie.getTrailerUrl());
            stmt.setInt(9, serie.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Serie obtenerSerie(int id) {
        String sql = "SELECT * FROM Series WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Serie(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getInt("temporadas"),
                    rs.getInt("capitulos"),
                    rs.getString("genero"), // Cambiado a String
                    rs.getInt("ano"),
                    rs.getInt("calificacion"),
                    rs.getString("sinopsis"),
                    rs.getString("trailer_url"),
                    rs.getInt("usuario_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no encuentra la serie
    }
    
    public int conteoDeSeries(int usuarioId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM series WHERE usuario_id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // Método para buscar series por término (título, género, etc.)
    public List<Serie> buscarSeries(int usuarioId, String terminoBusqueda) {
        String sql = "SELECT * FROM Series WHERE usuario_id = ? AND (titulo LIKE ? OR genero LIKE ?)";
        List<Serie> series = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            String searchTerm = "%" + terminoBusqueda + "%"; // Usar comodín para búsqueda parcial
            stmt.setString(2, searchTerm);
            stmt.setString(3, searchTerm);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Serie serie = new Serie(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getInt("temporadas"),
                    rs.getInt("capitulos"),
                    rs.getString("genero"), // Cambiado a String
                    rs.getInt("ano"),
                    rs.getInt("calificacion"),
                    rs.getString("sinopsis"),
                    rs.getString("trailer_url"),
                    rs.getInt("usuario_id")
                );
                series.add(serie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return series;
    }
}
