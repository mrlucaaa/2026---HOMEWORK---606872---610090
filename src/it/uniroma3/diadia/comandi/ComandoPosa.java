package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	private IO io;
	private String parametro;
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo a=partita.getGiocatore().getBorsa().getAttrezzo(this.parametro);
		if(a==null) {
			this.io.mostraMessaggio("attrezzo inesistente!");
			return;
		}
		partita.getGiocatore().getBorsa().removeAttrezzo(this.parametro);
		partita.getStanzaCorrente().addAttrezzo(a);
		this.io.mostraMessaggio("posato: " + a);
	}
	

	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}

	@Override
	public void setIo(IO io) {
		this.io=io;
	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}
	
}


