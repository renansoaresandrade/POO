package poo.sca.io;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import poo.sca.Curso;
import poo.sca.Disciplina;
import poo.sca.Professor;
import poo.sca.Turma;

public class SCAPersistenciaArquivo implements SCAPersistencia {

	private ArrayList<Turma> turmas;
	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Curso> cursos;
	private ArrayList<Professor> professores;	
	
	public void salvar(Turma turma) {
		
	}

	public void salvar(Disciplina disciplina) {
		
	}

	public void salvar(Professor professor) {
		professores.add(professor);
		gravarArquivo(professores, "professores.ser");
	}

	public ArrayList<Turma> recuperarTurmas() {
		return null;
	}

	public ArrayList<Disciplina> recuperarDisciplinas() {
		return null;
	}

	public ArrayList<Professor> recuperarProfessores() {
		ObjectInputStream in;
		professores = new ArrayList<Professor>();
		try {
			in = new ObjectInputStream(new FileInputStream("professores.ser"));
			professores = (ArrayList<Professor>) in.readObject();
			in.close();
		} catch (IOException e1) {
			System.out.println("Arquivo não encontrado!");
			e1.printStackTrace();
		} catch (ClassNotFoundException e2) {
			System.out.println("Nenhuma classe encontrada!");
			e2.printStackTrace();
		}
		return professores;
	}
		public static void gravarArquivo(ArrayList array, String nome) { // Escreve o objeto no arquivo serializable, o objeto é passado por parâmetro
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(nome));
			out.writeObject(array);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
