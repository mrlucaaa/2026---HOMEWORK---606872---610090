package it.uniroma3.diadia.comandi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {

    private Partita partita;
    private ComandoVai comando;
    private Stanza partenza;
    private Stanza destinazione;

    @BeforeEach
    public void setUp() {
        this.partita = new Partita();
        this.partenza = new Stanza("Partenza");
        this.destinazione = new Stanza("Destinazione");
        this.partenza.impostaStanzaAdiacente("nord", destinazione);
        this.partita.setStanzaCorrente(partenza);

        this.comando = new ComandoVai();
        // Iniettiamo un IO fittizio per disaccoppiare l'output durante i test
        this.comando.setIo(new IO() {
            public void mostraMessaggio(String msg) {}
            public String leggiRiga() { return ""; }
        });
    }

    @Test
    public void testVaiDirezioneEsistente() {
        this.comando.setParametro("nord");
        this.comando.esegui(this.partita);
        assertEquals(this.destinazione, this.partita.getStanzaCorrente());
    }

    @Test
    public void testVaiDirezioneInesistente() {
        this.comando.setParametro("sud");
        this.comando.esegui(this.partita);
        assertEquals(this.partenza, this.partita.getStanzaCorrente());
    }

    @Test
    public void testVaiSenzaParametro() {
        this.comando.setParametro(null);
        this.comando.esegui(this.partita);
        assertEquals(this.partenza, this.partita.getStanzaCorrente());
    }
}
