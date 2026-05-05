package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private Borsa borsaVuota;
	private Borsa borsaPiena;
	private Attrezzo piuma;
	private Attrezzo incudine;

	@Before
	public void setUp() {
		// Una borsa standard e una borsa preparata al limite del peso (10kg)
		this.borsaVuota = new Borsa();
		this.borsaPiena = new Borsa();
		
		this.piuma = new Attrezzo("piuma", 1);
		this.incudine = new Attrezzo("incudine", 10);
		
		this.borsaPiena.addAttrezzo(this.incudine);
	}

	// TEST INSERIMENTO

	@Test
	public void testAddAttrezzo_SpazioEPesoDisponibili() {
		assertTrue(this.borsaVuota.addAttrezzo(this.piuma));
		assertEquals(1, this.borsaVuota.getPeso());
	}

	@Test
	public void testAddAttrezzo_TroppoPesante() {
		// La borsaPiena ha già 10kg, non può accettare un altro chilo
		assertFalse(this.borsaPiena.addAttrezzo(this.piuma));
	}

	// TEST RICERCA

	@Test
	public void testGetAttrezzo_Presente() {
		assertEquals(this.incudine, this.borsaPiena.getAttrezzo("incudine"));
	}

	@Test
	public void testGetAttrezzo_Assente() {
		assertNull(this.borsaVuota.getAttrezzo("piuma"));
	}

	// TEST RIMOZIONE

	@Test
	public void testRemoveAttrezzo_Presente() {
		// Rimuovo l'incudine e verifico che mi venga restituita
		assertEquals(this.incudine, this.borsaPiena.removeAttrezzo("incudine"));
		// Verifico che ora la borsa sia effettivamente vuota
		assertTrue(this.borsaPiena.isEmpty());
	}

	@Test
	public void testRemoveAttrezzo_Assente() {
		assertNull(this.borsaVuota.removeAttrezzo("piuma"));
	}

	// TEST CONTROLLI VUOTO

	@Test
	public void testIsEmpty_Vera() {
		assertTrue(this.borsaVuota.isEmpty());
	}

	@Test
	public void testIsEmpty_Falsa() {
		assertFalse(this.borsaPiena.isEmpty());
	}
}