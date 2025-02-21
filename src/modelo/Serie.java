package modelo;

public class Serie {
    private int id;
    private String titulo;
    private int temporadas;
    private int capitulos;
    private String genero; // Género de la serie como cadena de texto
    private int año;
    private int calificacion;
    private String sinopsis;
    private String trailerUrl;
    private int usuarioId;

    // Constructor para crear una nueva serie sin ID
    public Serie(String titulo, int temporadas, int capitulos, String genero, int año, int calificacion, String sinopsis, String trailerUrl, int usuarioId) {
        this.titulo = titulo;
        this.temporadas = temporadas;
        this.capitulos = capitulos;
        this.genero = genero;
        this.año = año;
        this.calificacion = calificacion;
        this.sinopsis = sinopsis;
        this.trailerUrl = trailerUrl;
        this.usuarioId = usuarioId;
    }

    // Constructor para obtener una serie de la base de datos
    public Serie(int id, String titulo, int temporadas, int capitulos, String genero, int año, int calificacion, String sinopsis, String trailerUrl, int usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.temporadas = temporadas;
        this.capitulos = capitulos;
        this.genero = genero;
        this.año = año;
        this.calificacion = calificacion;
        this.sinopsis = sinopsis;
        this.trailerUrl = trailerUrl;
        this.usuarioId = usuarioId;
    }

    // Getters y setters
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

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int ano) {
        this.año = ano;
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
}
