package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

public class LabirintoTest {

	Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		labirinto=new Labirinto();
	}
	
	@Test
	void testGetStanzaVincente() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}
	
	
	@Test
	void testGetStanzaIniziale() {
		assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
	}

}
