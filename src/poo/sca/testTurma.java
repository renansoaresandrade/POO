package poo.sca;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testTurma {
	
	Turma t;
	SCAFacade facade;

	@Before
	public void setUp() throws Exception {
		facade = new SCAFacade();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testException() {
		t = new Turma();
		Professor p = new Professor();
		
		try {
			facade.criarTurma(t);
		} catch (SCAException e) {
			// TODO Auto-generated catch block
			assertEquals("Não há cursos cadastrados.", e.getMessage());
		}
		Curso c = new Curso();
		t.addCurso(c);
		
		try {
			facade.criarTurma(t);
		} catch (SCAException e) {
			// TODO Auto-generated catch block
			assertEquals("Não há professores cadastrados.", e.getMessage());
		}
		Turma t2 = new Turma();
		t2.addCurso(c);
		t2.addProfessor(p);
		t.addProfessor(p);

		try {
			facade.criarTurma(t);
		} catch (SCAException e) {
			// TODO Auto-generated catch block
			assertEquals("Não existem disciplinas cadastradas!", e.getMessage());
		}
		
		Disciplina d = new Disciplina();
		d.setCodigo(1);
		
		t.setDisciplina(d);
		t2.setDisciplina(d);

		try {
			facade.criarTurma(t);
			facade.criarTurma(t2);
		} catch (SCAException e) {
			// TODO Auto-generated catch block
			assertEquals("Disciplina com mesmo código já existe!", e.getMessage());
		}		
		
	}

}
