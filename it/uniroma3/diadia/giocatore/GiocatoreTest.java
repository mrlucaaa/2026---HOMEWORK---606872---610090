package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {

	private Giocatore giocatore;

	@Before
	public void setUp() {
		this.giocatore = new Giocatore();
	}

	@Test
	public void testCfuIniziali() {
		assertEquals(20, this.giocatore.getCfu());
	}

	@Test
	public void testSetCfu() {
		this.giocatore.setCfu(10);
		assertEquals(10, this.giocatore.getCfu());
	}

	@Test
	public void testBorsaInizializzata() {
		// Verifica che la borsa non sia null quando creo il giocatore
		assertNotNull(this.giocatore.getBorsa());
	}
}
