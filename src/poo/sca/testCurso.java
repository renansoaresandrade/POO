package poo.sca;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testCurso {

	private Curso c, c1, c2;
	private SCAFacade facade;
	
	@Before
	public void setUp() throws Exception {
		facade = new SCAFacade();
		c = new Curso();
		c1 = new Curso();
		c2 = new Curso();
		c.setNome("SI");
		c.setCodigo(100001);
		c1.setNome("SI1");
		c1.setCodigo(1001);
		c2.setNome("SI2");
		c2.setCodigo(1001);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("SI", c.getNome());
		assertEquals("SI1", c1.getNome());
	}
	
	@Test
	public void testException() {
		try {
			facade.criarCurso(c);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Código de curso inválido! Máximo 10000", e.getMessage());
		}
		try {
			facade.criarCurso(c1);
			facade.criarCurso(c2);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Curso com mesmo código!", e.getMessage());
		}
		c2.setNome("");
		try {
			facade.criarCurso(c2);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Nome inválido: ", e.getMessage());
		}
		c2.setNome("null");
		try {
			facade.criarCurso(c2);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Nome inválido: null", e.getMessage());
		}
	}

	@Test
	public void testEquals() {
		assertFalse(c.equals(c1));
		assertFalse(c1.getNome().equals(c.getNome()));
	}	

	@Test
	public void testContains()  {
		try {
		facade.criarCurso(c1);
		Iterator<Curso> cursos = facade.getCursosIterator();
		ArrayList<Curso> listCursos = new ArrayList<Curso>();
		while (cursos.hasNext()) {
			listCursos.add(cursos.next());
		}
		listCursos.add(c1);
		assertTrue(listCursos.contains(c1));
		} catch (SCAException e) {
			fail (e.getMessage());
		}
	}	
	
}
