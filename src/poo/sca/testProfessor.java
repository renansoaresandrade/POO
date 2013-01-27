package poo.sca;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testProfessor {
	
	private Professor p, p1, p2;
	private SCAFacade facade;
	
	@Before
	public void setUp() throws Exception {
		facade = new SCAFacade();
		p = new Professor();
		p1 = new Professor();
		p2 = new Professor();
		p.setNome("JOÃO");
		p.setMatricula(100001);
		p1.setNome("MARIA");
		p1.setMatricula(1001);
		p2.setNome("JOSE");
		p2.setMatricula(1001);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("JOÃO", p.getNome());
		assertEquals("MARIA", p1.getNome());
	}
	
	@Test
	public void testException() {
		try {
			facade.criarProfessor(p);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Matrícula de professor inválido! Máximo 10000", e.getMessage());
		}
		try {
			facade.criarProfessor(p1);
			facade.criarProfessor(p2);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Professor com mesma matrícula!", e.getMessage());
		}
		p2.setNome("");
		try {
			facade.criarProfessor(p2);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Nome inválido: ", e.getMessage());
		}
		p2.setNome("null");
		try {
			facade.criarProfessor(p2);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Nome inválido: null", e.getMessage());
		}
	}

	@Test
	public void testEquals() {
		assertFalse(p.equals(p1));
		assertFalse(p1.getNome().equals(p.getNome()));
	}	

	@Test
	public void testContains()  {
		try {
		facade.criarProfessor(p1);
		Iterator<Professor> professores = facade.getProfessoresIterator();
		ArrayList<Professor> listProfessores = new ArrayList<Professor>();
		while (professores.hasNext()) {
			listProfessores.add(professores.next());
		}
		listProfessores.add(p1);
		assertTrue(listProfessores.contains(p1));
		} catch (SCAException e) {
			fail (e.getMessage());
		}
	}	
	
	
}
