package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Partita {

	private Labirinto labirinto;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	private boolean finita;
	
	public Partita(){
		// Deleghiamo la creazione!
		this.labirinto = new Labirinto();
		this.giocatore = new Giocatore();
		
		// Chiediamo al labirinto da dove partire
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.finita = false;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 */
	public boolean vinta() {
		// Chiediamo al labirinto qual è la stanza vincente per fare il confronto
		return this.stanzaCorrente == this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 */
	public boolean isFinita() {
		// I cfu ora li chiediamo al giocatore!
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	public void setFinita() {
		this.finita = true;
	}

	// Metodi ponte: DiaDia chiamerà questi, e la Partita li girerà al Giocatore
	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);		
	}	
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
}