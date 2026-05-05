package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {

	private Labirinto labirinto;

	@Before
	public void setUp() {
		// Fattorizziamo la creazione del labirinto
		this.labirinto = new Labirinto();
	}

	@Test
	public void testGetStanzaIniziale() {
		// Il labirinto di default parte sempre dall'Atrio
		assertEquals("Atrio", this.labirinto.getStanzaIniziale().getNome());
	}

	@Test
	public void testGetStanzaVincente() {
		// Il labirinto di default finisce sempre in Biblioteca
		assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome());
	}
}