package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	private IO io;
	private String parametro;

	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Comando inesistente! Premi aiuto per una lista di comandi");	
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
		return "non valido";
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	

}
