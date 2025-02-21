package modelo;

public class Pelicula {
    private int id;
    private String titulo;
    private int año; // Año de lanzamiento
    private String genero; // Género de la película como cadena de texto
    private int calificacion; // Calificación del usuario (0-10)
    private String sinopsis; // Descripción breve de la película
    private String trailerUrl; // URL del tráiler de la película
    private int usuarioId; // ID del usuario al que pertenece la película

    // Constructor con todos los atributos
    public Pelicula(int id, String titulo, int año, String genero, int calificacion, String sinopsis, String trailerUrl, int usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.año = año;
        this.genero = genero;
        this.calificacion = calificacion;
        this.sinopsis = sinopsis;
        this.trailerUrl = trailerUrl;
        this.usuarioId = usuarioId;
    }

    // Constructor sin el id (por ejemplo, para cuando aún no se ha guardado en la base de datos)
    public Pelicula(String titulo, int año, String genero, int calificacion, String sinopsis, String trailerUrl, int usuarioId) {
        this.titulo = titulo;
        this.año = año;
        this.genero = genero;
        this.calificacion = calificacion;
        this.sinopsis = sinopsis;
        this.trailerUrl = trailerUrl;
        this.usuarioId = usuarioId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int ano) {
        this.año = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", ano=" + año +
                ", genero='" + genero + '\'' +
                ", calificacion=" + calificacion +
                ", sinopsis='" + sinopsis + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                ", usuarioId=" + usuarioId +
                '}';
    }
}
