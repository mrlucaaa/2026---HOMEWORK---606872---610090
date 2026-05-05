package it.uniroma3.diadia;

public class IOSimulator implements IO {
	private String[] righeDaLeggere;
	private int indiceRigheDaLeggere;
	private String[] messaggiProdotti;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiLetti;

	public IOSimulator(String[] righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.indiceRigheDaLeggere = 0;
		this.messaggiProdotti = new String[1000]; 
		this.indiceMessaggiProdotti = 0;
		this.indiceMessaggiLetti = 0;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti[this.indiceMessaggiProdotti] = messaggio;
		this.indiceMessaggiProdotti++;
	}

	@Override
	public String leggiRiga() {
		String riga = this.righeDaLeggere[this.indiceRigheDaLeggere];
		this.indiceRigheDaLeggere++;
		return riga;
	}

	public String nextMessaggio() {
		String messaggio = this.messaggiProdotti[this.indiceMessaggiLetti];
		this.indiceMessaggiLetti++;
		return messaggio;
	}

	public boolean hasNextMessaggio() {
		return this.indiceMessaggiLetti < this.indiceMessaggiProdotti;
	}
}
