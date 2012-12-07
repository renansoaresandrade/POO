package poo.sca;

import java.util.ArrayList;
import java.util.Iterator;

import poo.sca.io.*;

public class SCAFacade {
	private SCAPersistencia persistencia = new SCAPersistenciaArquivo();
	
	public void criarDisciplina(Disciplina d) throws SCAException {
		ArrayList<Disciplina> disciplinas = persistencia.recuperarDisciplinas();
		for (int i = 0; i < disciplinas.size(); i++) {
			if (disciplinas.get(i).getCodigo() == d.getCodigo()) {
				throw new SCAException("Disciplina com mesmo c�digo!");
			}
		}
		persistencia.salvar(d);
	}

	public void addProfessorTurma(int numTurma, int numProfessor) throws SCAException {
		ArrayList<Professor> professores = persistencia.recuperarProfessores();
		ArrayList<Turma> turmas = persistencia.recuperarTurmas();
		Professor p = new Professor();
		for (int i = 0; i < professores.size(); i++) {
			if (professores.get(i).getMatricula() == numProfessor) {
				p = professores.get(i);
				break;
			}
			if (i == professores.size()-1) {
				throw new SCAException("Professor n�o existe!");	
			}
		}
		for (int i = 0; i < turmas.size(); i++) {
			if (turmas.get(i).getNumero() == numTurma) {
				Iterator<Professor> pInterator = turmas.get(i).getProfessoresIterator();
				while (pInterator.hasNext()) {
					p = pInterator.next();
					if (p.getMatricula() == numProfessor) {
						throw new SCAException("Professor j� cadastrado na turma!");	
					}
				}
				break;
			}
			if (i == turmas.size()-1) {
				throw new SCAException("Turma n�o existe!");	
			}
		}
		persistencia.salvar(numTurma, p);
	}
	
	public void addCursoTurma(int numTurma, int numCurso) throws SCAException {
		ArrayList<Curso> cursos = persistencia.recuperarCursos();
		ArrayList<Turma> turmas = persistencia.recuperarTurmas();
		Curso c = new Curso();
		for (int i = 0; i < cursos.size(); i++) {
			if (cursos.get(i).getCodigo() == numCurso) {
				c = cursos.get(i);
				break;
			}
			if (i == cursos.size()-1) {
				throw new SCAException("Curso n�o existe!");	
			}
		}
		for (int i = 0; i < turmas.size(); i++) {
			if (turmas.get(i).getNumero() == numTurma) {
				Iterator<Curso> cInterator = turmas.get(i).getCursosIterator();
				while (cInterator.hasNext()) {
					c = cInterator.next();
					if (c.getCodigo() == numCurso) {
						throw new SCAException("Curso j� cadastrado na turma!");	
					}
				}
				break;
			}
			if (i == turmas.size()-1) {
				throw new SCAException("Turma n�o existe!");	
			}
		}
		persistencia.salvar(numTurma, c);
	}
	
	public void criarCurso(Curso c) throws SCAException {	
		ArrayList<Curso> cursos = persistencia.recuperarCursos();
		for (int i = 0; i < cursos.size(); i++) {
			if (cursos.get(i).getCodigo() == c.getCodigo()) {
				throw new SCAException("Curso com mesmo c�digo!");
			}
		}
		persistencia.salvar(c);
	}	

	public void criarProfessor(Professor p) throws SCAException {
		ArrayList<Professor> professores = persistencia.recuperarProfessores();
		for (int i = 0; i < professores.size(); i++) {
			if (professores.get(i).getMatricula() == p.getMatricula()) {
				throw new SCAException("Professor com mesma matr�cula!");
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
						throw new SCAException("Turma j� existe!");
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
