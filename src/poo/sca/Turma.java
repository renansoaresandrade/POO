package poo.sca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Turma implements Serializable {
	private int numero;
	private Disciplina disciplina;
	private ArrayList<Professor> professores = new ArrayList<Professor>();
	private String horario;
	private String periodo;
	private ArrayList<Curso> cursos = new ArrayList<Curso>();
	
	public Turma() {
		this.horario = "Não definido";
	}
	public Turma(Disciplina disciplina, String periodo, int numero) {
		this.numero = numero;
		this.disciplina = disciplina;
		this.periodo = periodo;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public void addProfessor(Professor professor) {
		professores.add(professor);
	}

	public void addCurso(Curso curso) {
		cursos.add(curso);
	}	
	
	public Iterator<Professor> getProfessoresIterator() {
		return professores.iterator();
	}

	public Iterator<Curso> getCursosIterator() {
		return cursos.iterator();
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
}