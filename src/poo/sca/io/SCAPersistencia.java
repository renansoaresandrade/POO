package poo.sca.io;

import java.util.ArrayList;

import poo.sca.*;

public interface SCAPersistencia {
	public abstract void salvar(Turma turma);
	public abstract void salvar(Disciplina disciplina);
	public abstract void salvar(Professor professor);
	public abstract void salvar(Curso curso);
	public abstract void salvar(int numTurma, Professor p);
	public abstract void salvar(int numTurma, Curso c);
	public abstract ArrayList<Turma> recuperarTurmas();
	public abstract ArrayList<Disciplina> recuperarDisciplinas();
	public abstract ArrayList<Curso> recuperarCursos();
	public abstract ArrayList<Professor> recuperarProfessores();

}