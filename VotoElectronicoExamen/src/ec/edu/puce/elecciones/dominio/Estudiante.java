package ec.edu.puce.elecciones.dominio;

import java.util.ArrayList;

public class Estudiante {
	private String Ncedula;
	private String nombre;
	private Curso curso;
    private Candidato voto;
    private boolean haVotado = false; 

	public String getCedula() {
		return Ncedula;
	}

	public void setCedula(String cedula) {
		this.Ncedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	public Candidato getVoto() {
		return voto;
	}

	public void setVoto(Candidato voto) {
		this.voto = voto;
	}

	public boolean isHaVotado() {
		return haVotado;
	}

	public void setHaVotado(boolean haVotado) {
		this.haVotado = haVotado;
	}
	
	

}
