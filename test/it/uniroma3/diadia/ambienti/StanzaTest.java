package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	Stanza atrio;
	Stanza biblioteca;
	Stanza campus;
	Attrezzo chiave;
	Attrezzo spada;
	Attrezzo libro;
	
	@BeforeEach
	public void setUp() {
		atrio=new Stanza("Atrio");
		biblioteca=new Stanza("Biblioteca");
		campus=new Stanza("Campus");
		chiave=new Attrezzo("Chiave", 2);
		spada=new Attrezzo("Spada", 6);
		libro=new Attrezzo("Libro", 4);
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		biblioteca.addAttrezzo(libro);
	}

	@Test
	 void testStanzaAdiacente() {
		assertEquals(biblioteca, atrio.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testStanzaAdiacenteNull() {
		assertEquals(null, atrio.getStanzaAdiacente("est"));
	}
	
	@Test
	void testNuovaStanzaAdiacente() {
		atrio.impostaStanzaAdiacente("nord", campus);
		assertEquals(campus, atrio.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetAttrezzo() {
		assertEquals(libro, biblioteca.getAttrezzo("Libro"));
	}
	
	@Test
	void testGetAttrezzoNull() {
		assertEquals(null, biblioteca.getAttrezzo("Chiave"));
	}
	
	@Test
	void testHasAttrezzo() {
		assertTrue(biblioteca.hasAttrezzo("Libro"));
	}
	
	@Test
	void testHasAttrezzoNull() {
		assertFalse(campus.hasAttrezzo("Libro"));
	}
	
	@Test
	void testRemoveAttrezzo() {
		assertTrue(biblioteca.removeAttrezzo(libro));
	}
	
	@Test
	void testRemoveAttrezzoNull() {
		assertFalse(biblioteca.removeAttrezzo(chiave));
	}
	
	@Test
	void testGetAttrezzoRimosso() {
		biblioteca.removeAttrezzo(libro);
		assertEquals(null, biblioteca.getAttrezzo("Libro"));
	}

}
