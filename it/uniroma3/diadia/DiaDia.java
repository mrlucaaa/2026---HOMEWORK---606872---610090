package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 * (da un'idea di Michael Kolling and David J. Barnes) 
 * * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole io; // IL NOSTRO NUOVO GESTORE DI STAMPE E LETTURE

	// Modificato per ricevere IOConsole
	public DiaDia(IOConsole io) {
		this.io = io;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		// Sostituiti Scanner e System.out con la nostra interfaccia IO
		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		// Controllo di sicurezza: se l'utente preme solo invio, il nome è null
		if (comandoDaEseguire.getNome() == null) {
			this.io.mostraMessaggio("Comando sconosciuto");
		} else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("aiuto")) {
			this.aiuto();
		} else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		} else {
			this.io.mostraMessaggio("Comando sconosciuto");
		}
		
		if (this.partita.vinta()) {
			this.io.mostraMessaggio("Hai vinto!");
			return true;
		} else {
			return false;
		}
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		StringBuilder comandi = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++) {
			comandi.append(elencoComandi[i]).append(" ");
		}
		this.io.mostraMessaggio(comandi.toString());
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			this.io.mostraMessaggio("Dove vuoi andare ?");
			return; // Se non c'è direzione ci fermiamo qui
		}
			
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		
		if (prossimaStanza == null) {
			this.io.mostraMessaggio("Direzione inesistente");
		} else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu - 1); 
		}
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Prende un attrezzo dalla stanza e lo mette nella borsa
	 */
	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.io.mostraMessaggio("Cosa vuoi prendere? (es. prendi osso)");
			return;
		}
		
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Attrezzo a = stanzaCorrente.getAttrezzo(nomeAttrezzo);
		
		if (a != null) {
			// Proviamo a metterlo in borsa
			boolean aggiunto = this.partita.getGiocatore().getBorsa().addAttrezzo(a);
			if (aggiunto) {
				stanzaCorrente.removeAttrezzo(a); // Lo togliamo dalla stanza
				this.io.mostraMessaggio("Hai preso: " + a.getNome());
			} else {
				this.io.mostraMessaggio("La borsa è troppo pesante o piena!");
			}
		} else {
			this.io.mostraMessaggio("Attrezzo non presente in questa stanza.");
		}
	}

	/**
	 * Posa un attrezzo dalla borsa nella stanza
	 */
	private void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.io.mostraMessaggio("Cosa vuoi posare? (es. posa osso)");
			return;
		}
		
		Attrezzo a = this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		
		if (a != null) {
			this.partita.getStanzaCorrente().addAttrezzo(a); // Lo mettiamo nella stanza
			this.io.mostraMessaggio("Hai posato: " + a.getNome());
		} else {
			this.io.mostraMessaggio("Non hai questo attrezzo nella borsa!");
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		// Come richiesto dalla slide, creiamo UNA SOLA istanza di IOConsole qui
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}