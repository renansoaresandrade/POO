package poo.sca.io;

import java.util.ArrayList;

import poo.sca.*;

public class SCAPersistenciaMemoria implements SCAPersistencia {
	private ArrayList<Turma> turmas = new ArrayList<Turma>();
	private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private ArrayList<Curso> cursos = new ArrayList<Curso>();
	private ArrayList<Professor> professores = new ArrayList<Professor>();

	public void salvar(Turma turma) {
		turmas.add(turma);
	}

	public void salvar(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}

	public void salvar(Professor professor) {
		professores.add(professor);
	}

	public void salvar(Curso curso) {
		cursos.add(curso);
	}

	public ArrayList<Turma> recuperarTurmas() {
		return turmas;
	}

	public ArrayList<Disciplina> recuperarDisciplinas() {
		return disciplinas;
	}

	public ArrayList<Curso> recuperarCursos() {
		return cursos;
	}

	public ArrayList<Professor> recuperarProfessores() {
		return professores;
	}

	@Override
	public void salvar(int numTurma, Professor p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void salvar(int numTurma, Curso c) {
		// TODO Auto-generated method stub
		
	}
}