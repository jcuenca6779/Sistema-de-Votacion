package ec.edu.puce.elecciones.dominio;

import java.util.ArrayList;
import java.util.List;

public class Curso
{
    private String nombreCurso;
    private List<Estudiante> estudiantes;
    private Mesa mesa;
    public Curso(String ciudad, Mesa mesa)
    {
        this.nombreCurso = ciudad;
     
        this.mesa = mesa;
        
    }
    
	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}



    
	
    
}
