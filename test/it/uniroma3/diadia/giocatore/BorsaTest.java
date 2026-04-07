package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	
	private Borsa borsaVuota;
	private Borsa borsaPiena;
	private Borsa borsaMista; // Usata per testare lo scorrimento dell'array
	
	private Attrezzo piuma;
	private Attrezzo incudine;
	private Attrezzo sasso;
	private Attrezzo pietra;
	private Attrezzo sassolino;
	
	@BeforeEach
	public void setUp() {
		// Inizializzazione borse
		borsaVuota = new Borsa();
		borsaPiena = new Borsa();
		borsaMista = new Borsa();
		
		// Inizializzazione attrezzi
		piuma = new Attrezzo("piuma", 1);
		incudine = new Attrezzo("incudine", 10);
		sasso = new Attrezzo("sasso", 4);
		pietra = new Attrezzo("pietra", 3);
		sassolino = new Attrezzo("sassolino", 2);
		
		// Setup borsa piena (peso: 10)
		borsaPiena.addAttrezzo(incudine);
		
		borsaMista.addAttrezzo(sasso);      
		borsaMista.addAttrezzo(pietra);     
		borsaMista.addAttrezzo(sassolino);  
	}

	// --- TEST INSERIMENTO ---

	@Test
	void testAddAttrezzo_SpazioEPesoDisponibili() {
		assertTrue(borsaVuota.addAttrezzo(piuma));
		assertEquals(1, borsaVuota.getPeso(), "Il peso deve aggiornarsi a 1kg");
		assertTrue(borsaVuota.hasAttrezzo("piuma"));
	}
	
	@Test
	void testAddAttrezzo_RifiutaSeTroppoPesante() {
		assertFalse(borsaPiena.addAttrezzo(piuma));
		assertEquals(10, borsaPiena.getPeso(), "Il peso non deve superare i 10kg");
	}

	// --- TEST RICERCA ---

	@Test
	void testGetAttrezzo_Presente() {
		assertEquals(incudine, borsaPiena.getAttrezzo("incudine"));
	}
	
	@Test
	void testGetAttrezzo_Assente() {
		assertNull(borsaVuota.getAttrezzo("spada"));
	}

	// --- TEST RIMOZIONE E SCORRIMENTO ARRAY ---

	@Test
	void testRemoveAttrezzo_PrimoElemento() {
		assertEquals(sasso, borsaMista.removeAttrezzo("sasso"));
		
		assertFalse(borsaMista.hasAttrezzo("sasso"), "Il sasso non deve più essere in borsa");
		assertEquals(5, borsaMista.getPeso(), "Il peso deve scendere da 9kg a 5kg");
	}
	
	@Test
	void testRemoveAttrezzo_ElementoInMezzo() {
		assertEquals(pietra, borsaMista.removeAttrezzo("pietra"));
		
		assertFalse(borsaMista.hasAttrezzo("pietra"), "La pietra non deve più essere in borsa");
		assertEquals(6, borsaMista.getPeso(), "Il peso deve scendere da 9kg a 6kg");
	}
	
	@Test
	void testRemoveAttrezzo_UltimoElemento() {
		assertEquals(sassolino, borsaMista.removeAttrezzo("sassolino"));
		
		assertFalse(borsaMista.hasAttrezzo("sassolino"), "Il sassolino non deve più essere in borsa");
		assertEquals(7, borsaMista.getPeso(), "Il peso deve scendere da 9kg a 7kg");
	}
	
	@Test	
	void testRemoveAttrezzo_Assente() {
		assertNull(borsaMista.removeAttrezzo("spada"));
		assertEquals(9, borsaMista.getPeso(), "Il peso non deve cambiare se la rimozione fallisce");
	}

	// --- TEST CONTROLLI VUOTO ---

	@Test
	void testIsEmpty_Vera() {
		assertTrue(borsaVuota.isEmpty());
	}
	
	@Test
	void testIsEmpty_Falsa() {
		assertFalse(borsaPiena.isEmpty());
	}
}