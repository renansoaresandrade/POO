package poo.sca.ui;

import poo.sca.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SCA {
	private static SCAFacade facade = new SCAFacade();
	
	public static void main (String[] args) {
		exibirMenu();
	}
	
	public static void exibirMenu() {
		String o = JOptionPane.showInputDialog("Bem vindo ao sistema de Gerenciamento Academico!\nEscolha a opção desejada:\n1-Cadastrar Disciplina\n2-Listar Disciplinas\n3-Cadastrar Curso\n4-Listar Cursos\n5-Cadastrar Professor\n6-Listar Professores\n7-Gerenciar Turmas\n0-Sair","Sua opção");		
		lerEntradaUsuario(o);
	}
	
	public static void lerEntradaUsuario(String pergunta) {
		int perguntaI = Integer.parseInt(pergunta);
		String disciplinas = "", cursos = "", professores = "", turmas = "";
		switch (perguntaI) {
		case 1:
			Disciplina d = new Disciplina();
			String codigo = JOptionPane.showInputDialog("Código da disciplina:");
			d.setCodigo(Integer.parseInt(codigo));
			String nome = JOptionPane.showInputDialog("Nome da disciplina:");
			d.setNome(nome);
			try {
				facade.criarDisciplina(d);
			JOptionPane.showMessageDialog(null, "Disciplina adicionado com sucesso!");
			} catch (SCAException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			exibirMenu();
			break;
		case 2:
			Iterator<Disciplina> d1;
			d1 = facade.getDisciplinasIterator();
			while (d1.hasNext()) {
				Disciplina d2 = d1.next();
				disciplinas = disciplinas + "[ Código: " + d2.getCodigo() + " || Nome: " + d2.getNome() + " ] " + "\n";
			}
			JOptionPane.showMessageDialog(null, disciplinas);
			break;
		case 3:
			Curso c = new Curso();
			codigo = JOptionPane.showInputDialog("Código do curso:");
			c.setCodigo(Integer.parseInt(codigo));
			nome = JOptionPane.showInputDialog("Nome do curso:");
			c.setNome(nome);
			try {
				facade.criarCurso(c);
			JOptionPane.showMessageDialog(null, "Cursos adicionado com sucesso!");
			} catch (SCAException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			exibirMenu();
			break;
		case 4:
			Iterator<Curso> c1;
			c1 = facade.getCursosIterator();
			while (c1.hasNext()) {
				Curso c2 = c1.next();
				cursos = cursos + "[ Código: " + c2.getCodigo() + " || Nome: " + c2.getNome() + " ]" + "\n";
			}
			JOptionPane.showMessageDialog(null, cursos);
			break;
		case 5:
			Professor p = new Professor();
			codigo = JOptionPane.showInputDialog("Matrícula do professor:");
			p.setMatricula(Integer.parseInt(codigo));
			nome = JOptionPane.showInputDialog("Nome do professor:");
			p.setNome(nome);
			try {
			facade.criarProfessor(p);
			JOptionPane.showMessageDialog(null, "Professor adicionado com sucesso!");
			} catch (SCAException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			exibirMenu();
			break;
		case 6:
			Iterator<Professor> p1;
			p1 = facade.getProfessoresIterator();
			while (p1.hasNext()) {
				Professor p2 = p1.next();
				professores = professores + "[ Mátricula: " + p2.getMatricula() + " || Nome: " + p2.getNome() + " ]" + "\n";
			}
			JOptionPane.showMessageDialog(null, professores);
			break;
	case 7:
		int op2 = Integer.parseInt(JOptionPane.showInputDialog(null, "1-Criar Turma\n2-Adicionar curso a Turma\n3-Adicionar professor a Turma\n4-Verificar Turmas"));
		switch (op2) {
		case 1:
			Turma t = new Turma();
			codigo = JOptionPane.showInputDialog("Número da turma:");
			t.setNumero(Integer.parseInt(codigo));
			String periodo = JOptionPane.showInputDialog("Período da turma:");
			t.setPeriodo(periodo);
			String horario = JOptionPane.showInputDialog("Horário da turma:");
			t.setHorario(horario);
			String numdiscI = JOptionPane.showInputDialog("Número da disciplina:");
			int numdisc = Integer.parseInt(numdiscI);
			d1 =  facade.getDisciplinasIterator();
			while (d1.hasNext()) {
				Disciplina d2 = d1.next();
				if (d2.getCodigo() == numdisc) {
					t.setDisciplina(d2);
					try {
					facade.criarTurma(t);
					JOptionPane.showMessageDialog(null, "Turma adicionada com sucesso!");
					} catch (SCAException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					}
					exibirMenu();
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "Disciplina não encontrada, tente novamente!");
			exibirMenu();
			break;
		case 2:
			int numTurma = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da turma: "));
			int numCurso = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número do do curso:"));
			try {
			facade.addCursoTurma(numTurma, numCurso);
			JOptionPane.showMessageDialog(null, "Curso adicionado com sucesso!");				
			} catch (SCAException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());				
			}
			exibirMenu();
			break;
		case 3:
			numTurma = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da turma: "));
			int numProfessor = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número do professor:"));
			try {
			facade.addProfessorTurma(numTurma, numProfessor);
			JOptionPane.showMessageDialog(null, "Professor adicionado com sucesso!");				
			} catch (SCAException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());				
			}
			exibirMenu();
			break;
		case 4:
			Iterator<Turma> t1;
			String turmasList = "";
			t1 = facade.getTurmasIterator();
			while (t1.hasNext()) {
				Turma t2 = t1.next();
				turmasList += "[ Num da turma: " + t2.getNumero() + " || Disciplina: " + t2.getDisciplina().getNome() + " ]" + "\n";
			}
			numTurma = Integer.parseInt(JOptionPane.showInputDialog(turmasList, "Digite o número da turma: "));
			t1 = facade.getTurmasIterator();
			while (t1.hasNext()) {
				Turma t2 = t1.next();
				if (t2.getNumero() == numTurma) {
					turmas = "Número da turma: " + t2.getNumero() + "\nHorário: " + t2.getHorario() + "\nPeríodo: " + t2.getPeriodo() + "\nDisciplina: " + t2.getDisciplina().getNome() + "\nProfessor(es): \n";
					p1 = t2.getProfessoresIterator();
					while (p1.hasNext()) {
						Professor p2 = p1.next();
						turmas = turmas + p2.getNome() + "\n";
					}
					turmas = turmas + "\nCurso(s) :";
					c1 = t2.getCursosIterator();
					while (c1.hasNext()) {
						Curso c2 = c1.next();
						turmas = turmas + c2.getNome() + "\n";
					}
				}
				}
			JOptionPane.showMessageDialog(null, turmas);
			exibirMenu();
			break;
		}
		}
	}
}