package ec.edu.puce.elecciones.dominio;


public class Candidato {
	private int id;
	private String nombre;
	private int votos;
	private String lista;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getLista() {
		return nombre;
	}

	public void setLista(String lista) {
		this.lista=lista;
	}


	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}
		
}
