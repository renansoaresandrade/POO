package poo.sca;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testDisciplina {
	
	private Disciplina d, d1, d2;
	private SCAFacade facade;
	
	@Before
	public void setUp() throws Exception {
		facade = new SCAFacade();
		d = new Disciplina();
		d1 = new Disciplina();
		d2 = new Disciplina();
		d.setNome("POO");
		d.setCodigo(100001);
		d1.setNome("POO1");
		d1.setCodigo(1001);
		d2.setNome("POO2");
		d2.setCodigo(1001);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("POO", d.getNome());
		assertEquals("POO1", d1.getNome());
	}
	
	@Test
	public void testException() {
		try {
			facade.criarDisciplina(d);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Código de disciplina inválido! Máximo 10000", e.getMessage());
		}
		try {
			facade.criarDisciplina(d1);
			facade.criarDisciplina(d2);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Disciplina com mesmo código!", e.getMessage());
		}
		d2.setNome("");
		try {
			facade.criarDisciplina(d2);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Nome inválido: ", e.getMessage());
		}
		d2.setNome("null");
		try {
			facade.criarDisciplina(d2);
			fail("[Erro] É pra lançar uma exceção aqui!");
		} catch (SCAException e) {
			assertEquals("Nome inválido: null", e.getMessage());
		}
	}

	@Test
	public void testEquals() {
		assertFalse(d.equals(d1));
		assertFalse(d1.getNome().equals(d.getNome()));
	}	

	@Test
	public void testContains()  {
		try {
		facade.criarDisciplina(d1);
		Iterator<Disciplina> disciplinas = facade.getDisciplinasIterator();
		ArrayList<Disciplina> listDisciplinas = new ArrayList<Disciplina>();
		while (disciplinas.hasNext()) {
			listDisciplinas.add(disciplinas.next());
		}
		listDisciplinas.add(d1);
		assertTrue(listDisciplinas.contains(d1));
		} catch (SCAException e) {
			fail (e.getMessage());
		}
	}	
	
	
}
