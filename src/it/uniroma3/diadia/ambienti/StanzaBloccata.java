package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{
	
	private String direzioneBloccata;
	private String attrezzoSbloccante;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata=direzioneBloccata;
		this.attrezzoSbloccante=attrezzoSbloccante;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(this.direzioneBloccata.equals(direzione)) {
			if(!hasAttrezzo(attrezzoSbloccante)) {
				return this;
			}
		}
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		String descrizione = super.getDescrizione();
		if (!this.hasAttrezzo(this.attrezzoSbloccante)) {
			String avviso = "\nLa direzione " + this.direzioneBloccata + " è inaccessibile. Posa l'attrezzo " + this.attrezzoSbloccante + " per sbloccarla!";
			descrizione = descrizione + avviso;
		}
		return descrizione;
	}
}
