package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	private Partita partita;

	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
	}

	// --- TEST SULLA VITTORIA ---

	@Test
	void testVinta_InizioPartita() {
		assertFalse(partita.vinta());
	}

	@Test
	void testVinta_VittoriaRaggiunta() {
		Stanza stanzaVincente = partita.getLabirinto().getStanzaVincente();
		partita.setStanzaCorrente(stanzaVincente);
		
		assertTrue(partita.vinta(), "La partita dovrebbe risultare vinta se ci si trova nella stanza vincente");
	}

	// --- TEST SULLA FINE DELLA PARTITA ---

	@Test
	void testIsFinita_InizioPartita() {
		assertFalse(partita.isFinita());
	}

	@Test
	void testIsFinita_FinitaManualmente() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}

	@Test
	void testIsFinita_ZeroCfu() {
		partita.getGiocatore().setCfu(0);
		
		assertTrue(partita.isFinita(), "La partita deve finire quando i CFU arrivano a zero");
	}

	@Test
	void testIsFinita_PartitaVinta() {
		// Teletrasportiamo il giocatore nella stanza finale
		Stanza stanzaVincente = partita.getLabirinto().getStanzaVincente();
		partita.setStanzaCorrente(stanzaVincente);
		
		// Se la partita è vinta, isFinita() deve restituire true
		assertTrue(partita.isFinita(), "La partita deve considerarsi finita se è stata vinta");
	}
}