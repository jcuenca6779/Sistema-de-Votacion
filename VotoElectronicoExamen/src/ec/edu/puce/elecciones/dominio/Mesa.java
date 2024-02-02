package ec.edu.puce.elecciones.dominio;

import java.util.ArrayList; 

public class Mesa
{
    private String nombre;
    private ArrayList<Curso> cursos;
    private int votosTotales;

    
    public Mesa(String nombre)
    {
        this.nombre = nombre;
        this.votosTotales = 0;
        this.cursos = new ArrayList<Curso>();
    }


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVotosTotales() {
		return votosTotales;
	}

	public void setVotosTotales(int votosTotales) {
		this.votosTotales = votosTotales;
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

	
    
}
