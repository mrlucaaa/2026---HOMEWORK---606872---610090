package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GiocatoreTest {
	Giocatore alessio;
	Giocatore luca;
	
	@BeforeEach
	public void SetUp() {
		alessio=new Giocatore();
		luca=new Giocatore();
		alessio.setCfu(180);
	}
	
	@Test
	void testGetCfu() {
		assertEquals(180, alessio.getCfu());
	}
	
	@Test
	void testGetCfuIniziali() {
		assertEquals(20, luca.getCfu());
	}
	
	@Test
	void testGetBorsa() {
		assertNotNull(alessio.getBorsa());
	}
	
	@Test
	void testBorsaInizialeVuota() {
		assertTrue(alessio.getBorsa().isEmpty());
	}

}