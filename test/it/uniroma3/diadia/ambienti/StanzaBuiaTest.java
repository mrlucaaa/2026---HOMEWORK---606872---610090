package it.uniroma3.diadia.ambienti;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

    private StanzaBuia stanzaBuia;
    private Attrezzo lanterna;

    @BeforeEach
    public void setUp() {
        // La stanza richiede una "lanterna" per illuminarsi
        this.stanzaBuia = new StanzaBuia("Cantina", "lanterna");
        this.lanterna = new Attrezzo("lanterna", 1);
    }

    @Test
    public void testGetDescrizioneSenzaAttrezzo() {
        // Verifichiamo che la descrizione sia esattamente la stringa di buio
        String e = "Qui c'è un buio pesto";
        assertEquals(e, this.stanzaBuia.getDescrizione());
    }

    @Test
    public void testGetDescrizioneConAttrezzo() {
        // Aggiungiamo l'attrezzo richiesto
        this.stanzaBuia.addAttrezzo(this.lanterna);
        
        // Verifichiamo che la stringa restituita non sia più quella di buio
        assertNotEquals("Qui c'è un buio pesto", this.stanzaBuia.getDescrizione());
    }
}