package it.uniroma3.diadia.comandi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {

    private Partita partita;
    private ComandoPosa comando;
    private Attrezzo attrezzo;

    @BeforeEach
    public void setUp() {
        this.partita = new Partita();
        this.partita.setStanzaCorrente(new Stanza("StanzaVuota"));
        
        // Mettiamo un attrezzo direttamente nella borsa per lo stato iniziale
        this.attrezzo = new Attrezzo("martello", 2);
        this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);

        this.comando = new ComandoPosa();
        this.comando.setIo(new IO() {
            public void mostraMessaggio(String msg) {}
            public String leggiRiga() { return ""; }
        });
    }

    @Test
    public void testPosaAttrezzoPresenteInBorsa() {
        this.comando.setParametro("martello");
        this.comando.esegui(this.partita);
        // Asseriamo che l'attrezzo sia finito nella stanza
        assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("martello"));
    }

    @Test
    public void testPosaAttrezzoRimossoDallaBorsa() {
        this.comando.setParametro("martello");
        this.comando.esegui(this.partita);
        // Asseriamo che la borsa sia ora vuota
        assertTrue(this.partita.getGiocatore().getBorsa().isEmpty());
    }

    @Test
    public void testPosaAttrezzoNonInBorsa() {
        this.comando.setParametro("spada");
        this.comando.esegui(this.partita);
        // La stanza deve rimanere vuota se non avevamo l'attrezzo
        assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
    }
}