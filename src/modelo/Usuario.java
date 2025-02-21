package modelo;

public class Usuario {

	private int id;
	private String nombreDeUsuario;
	private String contraseña;
	private String correo;
	
	//Constructor
	
	public Usuario (int id, String nombreUsuario, String contrasena, String correo) {
		
		this.id = id;
		this.nombreDeUsuario = nombreUsuario;
		this.contraseña = contrasena;
		this.correo = correo;
	}
	
	//GETS Y SETS
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getNombreUsuario() { return nombreDeUsuario; }
	public void setNombreUsuario (String nombreUsuario) { this.nombreDeUsuario = nombreUsuario; }
	
	public String getContraseña() { return contraseña; }
	public void setContraseña (String contrasena) { this.contraseña = contrasena; }
	
	public String getCorreo() { return correo; }
	public void setCorreo (String correo) { this.correo = correo; }
	
}

