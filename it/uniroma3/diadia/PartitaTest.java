package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PartitaTest {

	private Partita partita;

	@Before
	public void setUp() {
		// La partita si inizializza da sola con giocatore e labirinto
		this.partita = new Partita();
	}

	// TEST per isFinita

	@Test
	public void testIsFinita_InizioPartita() {
		// All'inizio i CFU sono 20 e non si è vinto, non deve essere finita
		assertFalse(this.partita.isFinita());
	}

	@Test
	public void testIsFinita_ZeroCfu() {
		this.partita.setCfu(0);
		assertTrue(this.partita.isFinita());
	}

	@Test
	public void testIsFinita_ImpostataManualmente() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}

	// TEST per vinta

	@Test
	public void testVinta_InizioPartita() {
		// Appena inizia il gioco ci si trova nell'Atrio, quindi non è vinta
		assertFalse(this.partita.vinta());
	}

	// TEST per i CFU 

	@Test
	public void testGetCfu_Iniziali() {
		assertEquals(20, this.partita.getCfu());
	}

	@Test
	public void testSetCfu_Decremento() {
		this.partita.setCfu(15);
		assertEquals(15, this.partita.getCfu());
	}
}
