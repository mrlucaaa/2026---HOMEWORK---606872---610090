package it.uniroma3.diadia.ambienti;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

    private StanzaBloccata stanzaBloccata;
    private Stanza stanzaAdiacente;
    private Attrezzo grimaldello;

    @BeforeEach
    public void setUp() {
        // La stanza è bloccata a "nord" e si sblocca con un "grimaldello"
        this.stanzaBloccata = new StanzaBloccata("Cella", "nord", "grimaldello");
        this.stanzaAdiacente = new Stanza("Corridoio");
        this.grimaldello = new Attrezzo("grimaldello", 2);
        
        // Impostiamo l'adiacenza nella direzione bloccata
        this.stanzaBloccata.impostaStanzaAdiacente("nord", this.stanzaAdiacente);
    }

    @Test
    public void testGetStanzaAdiacenteDirezioneBloccataSenzaAttrezzo() {
        // Tentiamo di andare a nord senza chiave: dobbiamo rimanere nella stanza corrente
        assertEquals(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente("nord"));
    }

    @Test
    public void testGetStanzaAdiacenteDirezioneBloccataConAttrezzo() {
        // Posiamo l'attrezzo sbloccante nella stanza
        this.stanzaBloccata.addAttrezzo(this.grimaldello);
        
        // Ora andando a nord dobbiamo finire nella stanza adiacente
        assertEquals(this.stanzaAdiacente, this.stanzaBloccata.getStanzaAdiacente("nord"));
    }

    @Test
    public void testGetStanzaAdiacenteDirezioneLibera() {
        // Impostiamo un'altra adiacenza in una direzione NON bloccata
        Stanza stanzaSud = new Stanza("Stanza a Sud");
        this.stanzaBloccata.impostaStanzaAdiacente("sud", stanzaSud);
        
        // Verifichiamo che la direzione libera funzioni sempre, anche senza chiave
        assertEquals(stanzaSud, this.stanzaBloccata.getStanzaAdiacente("sud"));
    }
}