package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{
	private IO io;

	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("" + partita.getGiocatore().getCfu() + " cfu");
		io.mostraMessaggio("" + partita.getGiocatore().getBorsa());
		
	}

	@Override
	public void setParametro(String parametro) {
		
	}

	@Override
	public void setIo(IO io) {
		this.io=io;
	}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
