package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO {
    private Connection conexion;

    public PeliculaDAO() throws SQLException {
        conexion = ConexionBD.obtenerConexion();
    }

    // Método para obtener las películas de un usuario
    public List<Pelicula> obtenerPeliculasPorUsuario(int usuarioId) {
        String sql = "SELECT * FROM Peliculas WHERE usuario_id = ?";
        List<Pelicula> peliculas = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pelicula pelicula = new Pelicula(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getInt("ano"),
                    rs.getString("genero"), // Cambiado a String
                    rs.getInt("calificacion"),
                    rs.getString("sinopsis"),
                    rs.getString("trailer_url"),
                    rs.getInt("usuario_id")
                );
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculas;
    }

    // Método para eliminar una película
    public boolean eliminarPelicula(int id) {
        String sql = "DELETE FROM Peliculas WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para agregar una película
    public boolean agregarPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO Peliculas (titulo, ano, genero, calificacion, sinopsis, trailer_url, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, pelicula.getTitulo());
            stmt.setInt(2, pelicula.getAño());
            stmt.setString(3, pelicula.getGenero()); // Cambiado a String
            stmt.setInt(4, pelicula.getCalificacion());
            stmt.setString(5, pelicula.getSinopsis());
            stmt.setString(6, pelicula.getTrailerUrl());
            stmt.setInt(7, pelicula.getUsuarioId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al agregar la película: " + e.getMessage());
            return false;
        }
    }

    // Método para actualizar una película
    public boolean actualizarPelicula(Pelicula pelicula) {
        String sql = "UPDATE Peliculas SET titulo = ?, ano = ?, genero = ?, calificacion = ?, sinopsis = ?, trailer_url = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, pelicula.getTitulo());
            stmt.setInt(2, pelicula.getAño());
            stmt.setString(3, pelicula.getGenero()); // Cambiado a String
            stmt.setInt(4, pelicula.getCalificacion());
            stmt.setString(5, pelicula.getSinopsis());
            stmt.setString(6, pelicula.getTrailerUrl());
            stmt.setInt(7, pelicula.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener una película por su ID
    public Pelicula obtenerPelicula(int id) {
        String sql = "SELECT * FROM Peliculas WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pelicula(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getInt("ano"),
                    rs.getString("genero"), // Cambiado a String
                    rs.getInt("calificacion"),
                    rs.getString("sinopsis"),
                    rs.getString("trailer_url"),
                    rs.getInt("usuario_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no encuentra la película
    }

    // Método para buscar películas por título o género
    public List<Pelicula> buscarPeliculas(int usuarioId, String terminoBusqueda) {
        String sql = "SELECT * FROM Peliculas WHERE usuario_id = ? AND (titulo LIKE ? OR genero LIKE ?)";
        List<Pelicula> peliculas = new ArrayList<>();
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setString(2, "%" + terminoBusqueda + "%");
            stmt.setString(3, "%" + terminoBusqueda + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pelicula pelicula = new Pelicula(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getInt("ano"),
                    rs.getString("genero"),
                    rs.getInt("calificacion"),
                    rs.getString("sinopsis"),
                    rs.getString("trailer_url"),
                    rs.getInt("usuario_id")
                );
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculas; // Devuelve la lista de películas encontradas
    }

    // Método para obtener una película por su título
    public Pelicula buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM Peliculas WHERE titulo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pelicula(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getInt("ano"),
                    rs.getString("genero"),
                    rs.getInt("calificacion"),
                    rs.getString("sinopsis"),
                    rs.getString("trailer_url"),
                    rs.getInt("usuario_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no encuentra la película
    }

    // Método para contar el número de películas de un usuario
    public int conteoDePeliculas(int usuarioId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM peliculas WHERE usuario_id = ?";
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
}
