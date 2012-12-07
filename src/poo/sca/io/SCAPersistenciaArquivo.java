package poo.sca.io;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import poo.sca.Curso;
import poo.sca.Disciplina;
import poo.sca.Professor;
import poo.sca.Turma;

public class SCAPersistenciaArquivo implements SCAPersistencia {

	private ArrayList<Turma> turmas = new ArrayList<Turma>();
	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Curso> cursos = new ArrayList<Curso>();
	private ArrayList<Professor> professores = new ArrayList<Professor>();	
	File arquivo;
	
	public void salvar (int numTurma, Professor professor) {
		for (int i = 0; i < turmas.size(); i++) {
			if (turmas.get(i).getNumero() == numTurma) {
				turmas.get(i).addProfessor(professor);
			}
		}
		salvarTurma();
	}
	
	public void salvar (int numTurma, Curso curso) {
		for (int i = 0; i < turmas.size(); i++) {
			if (turmas.get(i).getNumero() == numTurma) {
				turmas.get(i).addCurso(curso);
			}
		}
		salvarTurma();
	}
	
	public void salvar(Turma turma) {
		recuperarTurmas();
		turmas.add(turma);
		salvarTurma();
	}

	public void salvarTurma() {
		try {
		File arquivoDisciplinas = new File("turmas.txt");
		if (!(arquivoDisciplinas.exists())) {
			arquivoDisciplinas.createNewFile();
		}
		FileWriter writer = new FileWriter(arquivoDisciplinas);
		PrintWriter pwriter = new PrintWriter(writer);
		String linhaVez = "";
		for (int i = 0; i < turmas.size(); i++) {
			linhaVez = turmas.get(i).getNumero()+"#"+turmas.get(i).getDisciplina().getCodigo()+"#"+turmas.get(i).getHorario()+"#"+turmas.get(i).getPeriodo()+"#";
			Iterator<Professor> iProfessores = turmas.get(i).getProfessoresIterator();
			Iterator<Curso> iCursos = turmas.get(i).getCursosIterator();
			while (iProfessores.hasNext()) {
				Professor p = iProfessores.next();
				linhaVez += p.getMatricula()+":";
			}
			System.out.println(linhaVez.charAt(linhaVez.length()-1));
			if ((String.valueOf(linhaVez.charAt(linhaVez.length()-1)).equals(":"))) {
				linhaVez = linhaVez.substring(0, linhaVez.length()-1);
			}
			linhaVez += "#";
			while (iCursos.hasNext()) {
				Curso c = iCursos.next();
				linhaVez += c.getCodigo()+":";
			}
			if ((String.valueOf(linhaVez.charAt(linhaVez.length()-1)).equals(":"))) {
				linhaVez = linhaVez.substring(0, linhaVez.length()-1);
			}
			linhaVez += System.getProperty("line.separator");
			pwriter.write(linhaVez);
		}
		
		pwriter.flush();
		pwriter.close();
		} catch (IOException e) {
			// falta terminar de tratar exceção
		}
	}
	
	public void salvar(Disciplina disciplina) {
		recuperarDisciplinas();
		try {
		File arquivoDisciplinas = new File("disciplinas.txt");
		if (!(arquivoDisciplinas.exists())) {
			arquivoDisciplinas.createNewFile();
		}
		FileWriter writer = new FileWriter(arquivoDisciplinas);
		PrintWriter pwriter = new PrintWriter(writer);
		for (int i = 0; i < disciplinas.size(); i++) {
			pwriter.write(disciplinas.get(i).getCodigo()+":"+disciplinas.get(i).getNome()+System.getProperty("line.separator"));
		}
		pwriter.write(disciplina.getCodigo()+":"+disciplina.getNome());
		pwriter.flush();
		pwriter.close();
		} catch (IOException e) {
			// falta terminar de tratar exceção
		}
	}

	public void salvar(Professor professor) {
		recuperarProfessores();
		try {
		arquivo = new File("professores.txt");
		if (!(arquivo.exists())) {
			arquivo.createNewFile();
		}
		FileWriter writer = new FileWriter(arquivo);
		PrintWriter pwriter = new PrintWriter(writer);
		for (int i = 0; i < professores.size(); i++) {
			pwriter.write(professores.get(i).getMatricula()+":"+professores.get(i).getNome()+System.getProperty("line.separator"));
		}
		pwriter.write(professor.getMatricula()+":"+professor.getNome());
		pwriter.flush();
		pwriter.close();
		} catch (IOException e) {
			// falta terminar de tratar exceção
		}
	}

	public ArrayList<Turma> recuperarTurmas() {
		turmas = new ArrayList<Turma>();
		try {
		File arquivo = new File("turmas.txt");
		FileReader reader = new FileReader(arquivo);
		BufferedReader breader = new BufferedReader(reader);
		String linha = "";
		Turma t;
		while ((linha = breader.readLine())!=null) {
			t = new Turma();
			String[] listaLinha = linha.split("#");
			t.setNumero(Integer.parseInt(listaLinha[0]));
			t.setHorario(listaLinha[2]);
			t.setPeriodo(listaLinha[3]);
			try {
				String listaCursos[] = listaLinha[5].split(":");
				recuperarDisciplinas();
				recuperarCursos();
				recuperarProfessores();
				for (int i = 0; i < cursos.size(); i++) {
					for (int j = 0; j < listaCursos.length; j++) {
						if (cursos.get(i).getCodigo() == Integer.parseInt(listaCursos[j])) {
							t.addCurso(cursos.get(i));
						}
					}
				}
			} catch (IndexOutOfBoundsException e) { // Não tem curso cadastrado, faltando terminar implementação de exceção
			} catch (NumberFormatException e) { // Não tem curso cadastrado, faltando terminar implementação de exceção
			}
			try {
				String listaProfessores[] = listaLinha[4].split(":");
				recuperarDisciplinas();
				recuperarCursos();
				recuperarProfessores();
				for (int i = 0; i < professores.size(); i++) {
					for (int j = 0; j < listaProfessores.length; j++) {
						if (professores.get(i).getMatricula() == Integer.parseInt(listaProfessores[j])) {
							t.addProfessor(professores.get(i));
						}
					}
				}
			} catch (IndexOutOfBoundsException e) { // Não tem professor cadastrado, faltando terminar implementação de exceção
			} catch (NumberFormatException e) { // Não tem professor cadastrado, faltando terminar implementação de exceção
			}
			for (int i = 0; i < disciplinas.size(); i++) {
				if (disciplinas.get(i).getCodigo() == Integer.parseInt(listaLinha[1])) {
					t.setDisciplina(disciplinas.get(i));
				}
			}
			turmas.add(t);
		}
		} catch (IOException e) {
			// falta terminar de tratar exceção
		} 
		return turmas;
	}

	public ArrayList<Disciplina> recuperarDisciplinas() {
		disciplinas = new ArrayList<Disciplina>();
		try {
		File turmas = new File("disciplinas.txt");
		FileReader reader = new FileReader(turmas);
		BufferedReader breader = new BufferedReader(reader);
		String linha = "";
		Disciplina d;
		while ((linha = breader.readLine())!=null) {
			String[] listaLinha = linha.split(":");
			d = new Disciplina();
			d.setCodigo(Integer.parseInt(listaLinha[0]));
			d.setNome(listaLinha[1]);
			disciplinas.add(d);
		}
		} catch (IOException e) {
			// falta terminar de tratar exceção
		} 
		return disciplinas;
	}

	@Override
	public void salvar(Curso curso) {
		recuperarCursos();
		try {
		arquivo = new File("cursos.txt");
		if (!(arquivo.exists())) {
			arquivo.createNewFile();
		}
		FileWriter writer = new FileWriter(arquivo);
		PrintWriter pwriter = new PrintWriter(writer);
		for (int i = 0; i < cursos.size(); i++) {
			pwriter.write(cursos.get(i).getCodigo()+":"+cursos.get(i).getNome()+System.getProperty("line.separator"));
		}
		pwriter.write(curso.getCodigo()+":"+curso.getNome());
		pwriter.flush();
		pwriter.close();
		} catch (IOException e) {
			// falta terminar de tratar exceção
		}
}

	@Override
	public ArrayList<Curso> recuperarCursos() {
		cursos = new ArrayList<Curso>();
		try {
		File arquivo = new File("cursos.txt");
		FileReader reader = new FileReader(arquivo);
		BufferedReader breader = new BufferedReader(reader);
		String linha = "";
		Curso c;
		while ((linha = breader.readLine())!=null) {
			String[] listaLinha = linha.split(":");
			c = new Curso();
			c.setCodigo(Integer.parseInt(listaLinha[0]));
			c.setNome(listaLinha[1]);
			cursos.add(c);
		}
		} catch (IOException e) {
			// falta terminar de tratar exceção
		} 
		return cursos;
	}

	public ArrayList<Professor> recuperarProfessores() {
		professores = new ArrayList<Professor>();
		try {
		File arquivo = new File("professores.txt");
		FileReader reader = new FileReader(arquivo);
		BufferedReader breader = new BufferedReader(reader);
		String linha = "";
		Professor p;
		while ((linha = breader.readLine())!=null) {
			String[] listaLinha = linha.split(":");
			p = new Professor();
			p.setMatricula(Integer.parseInt(listaLinha[0]));
			p.setNome(listaLinha[1]);	
			professores.add(p);
		}
		} catch (IOException e) {
			// falta terminar de tratar exceção
		} 
		return professores;
	}

}