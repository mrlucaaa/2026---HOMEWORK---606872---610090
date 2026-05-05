package it.uniroma3.diadia.comandi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

    private Partita partita;
    private ComandoPrendi comando;
    private Stanza stanza;
    private Attrezzo attrezzo;

    @BeforeEach
    public void setUp() {
        this.partita = new Partita();
        this.stanza = new Stanza("StanzaVuota");
        this.attrezzo = new Attrezzo("martello", 2);
        this.stanza.addAttrezzo(attrezzo);
        this.partita.setStanzaCorrente(stanza);

        this.comando = new ComandoPrendi();
        this.comando.setIo(new IO() {
            public void mostraMessaggio(String msg) {}
            public String leggiRiga() { return ""; }
        });
    }

    @Test
    public void testPrendiAttrezzoPresente() {
        this.comando.setParametro("martello");
        this.comando.esegui(this.partita);
        // Asseriamo che l'attrezzo sia finito nella borsa
        assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
    }

    @Test
    public void testPrendiAttrezzoPresenteRimossoDallaStanza() {
        this.comando.setParametro("martello");
        this.comando.esegui(this.partita);
        // Asseriamo che l'attrezzo non sia più nella stanza
        assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("martello"));
    }

    @Test
    public void testPrendiAttrezzoAssente() {
        this.comando.setParametro("spada");
        this.comando.esegui(this.partita);
        // La borsa deve rimanere vuota se l'attrezzo non c'è
        assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
    }
}