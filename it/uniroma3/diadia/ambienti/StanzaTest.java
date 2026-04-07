package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	private Stanza stanzaVuota;
	private Stanza stanzaConAttrezzo;
	private Attrezzo attrezzo;

	@Before
	public void setUp() {
		// Stato iniziale più semplice possibile (Minimalità)
		this.stanzaVuota = new Stanza("Vuota");
		this.stanzaConAttrezzo = new Stanza("Piena");
		this.attrezzo = new Attrezzo("osso", 1);
		this.stanzaConAttrezzo.addAttrezzo(this.attrezzo);
	}

	// TEST per getStanzaAdiacente
	
	@Test
	public void testGetStanzaAdiacente_DirezioneInesistente() {
		assertNull(this.stanzaVuota.getStanzaAdiacente("nord"));
	}

	@Test
	public void testGetStanzaAdiacente_DirezioneEsistente() {
		Stanza adiacente = new Stanza("Adiacente");
		this.stanzaVuota.impostaStanzaAdiacente("nord", adiacente);
		assertEquals(adiacente, this.stanzaVuota.getStanzaAdiacente("nord"));
	}

	@Test
	public void testImpostaStanzaAdiacente_SovrascriveDirezione() {
		Stanza adiacente1 = new Stanza("Adiacente1");
		Stanza adiacente2 = new Stanza("Adiacente2");
		this.stanzaVuota.impostaStanzaAdiacente("nord", adiacente1);
		this.stanzaVuota.impostaStanzaAdiacente("nord", adiacente2);
		assertEquals(adiacente2, this.stanzaVuota.getStanzaAdiacente("nord"));
	}

	// TEST per hasAttrezzo

	@Test
	public void testHasAttrezzo_StanzaVuota() {
		assertFalse(this.stanzaVuota.hasAttrezzo("osso"));
	}

	@Test
	public void testHasAttrezzo_AttrezzoPresente() {
		assertTrue(this.stanzaConAttrezzo.hasAttrezzo("osso"));
	}

	@Test
	public void testHasAttrezzo_AttrezzoNonPresente() {
		assertFalse(this.stanzaConAttrezzo.hasAttrezzo("lanterna"));
	}

	// TEST per removeAttrezzo

	@Test
	public void testRemoveAttrezzo_StanzaVuota() {
		assertFalse(this.stanzaVuota.removeAttrezzo(this.attrezzo));
	}

	@Test
	public void testRemoveAttrezzo_AttrezzoEsistente() {
		assertTrue(this.stanzaConAttrezzo.removeAttrezzo(this.attrezzo));
		// Verifica secondaria: assicuriamoci che sia davvero sparito
		assertFalse(this.stanzaConAttrezzo.hasAttrezzo("osso")); 
	}
}
