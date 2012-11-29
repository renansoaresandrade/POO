package poo.sca;

import java.util.ArrayList;
import java.util.Iterator;

import poo.sca.io.*;

public class SCAFacade {
	private SCAPersistenciaMemoria persistencia = new SCAPersistenciaMemoria();
	
	public void criarDisciplina(Disciplina d) throws SCAException {
		ArrayList<Disciplina> disciplinas = persistencia.recuperarDisciplinas();
		for (int i = 0; i < disciplinas.size(); i++) {
			if (disciplinas.get(i).getCodigo() == d.getCodigo()) {
				throw new SCAException("Disciplina com mesmo código!");
			}
		}
		persistencia.salvar(d);
	}

	public void criarCurso(Curso c) throws SCAException {
		ArrayList<Curso> cursos = persistencia.recuperarCursos();
		for (int i = 0; i < cursos.size(); i++) {
			if (cursos.get(i).getCodigo() == c.getCodigo()) {
				throw new SCAException("Curso com mesmo código!");
			}
		}
		persistencia.salvar(c);
	}	

	public void criarProfessor(Professor p) throws SCAException {
		ArrayList<Professor> professores = persistencia.recuperarProfessores();
		for (int i = 0; i < professores.size(); i++) {
			if (professores.get(i).getMatricula() == p.getMatricula()) {
				throw new SCAException("Professor com mesma matrícula!");
			}
		}
		persistencia.salvar(p);
	}		
	
	public Iterator<Disciplina> getDisciplinasIterator() {
		return persistencia.recuperarDisciplinas().iterator();
	}
	
	public Iterator<Curso> getCursosIterator() {
		return persistencia.recuperarCursos().iterator();
	}

	public Iterator<Professor> getProfessoresIterator() {
		return persistencia.recuperarProfessores().iterator();
	}
	
	public void criarTurma(Turma t) throws SCAException {
		ArrayList<Turma> turmas = persistencia.recuperarTurmas();
		for (int i = 0; i < turmas.size(); i++) {
			if (turmas.get(i).getNumero() == t.getNumero()) {
				if (turmas.get(i).getPeriodo().equals(t.getPeriodo())) {
					if (turmas.get(i).getDisciplina().equals(t.getDisciplina())) {
						throw new SCAException("Turma já existe!");
					}
 				}
			}
		}
		persistencia.salvar(t);			
	}
	
	public Iterator<Turma> getTurmasIterator() {
		return persistencia.recuperarTurmas().iterator();
	}
			
}
