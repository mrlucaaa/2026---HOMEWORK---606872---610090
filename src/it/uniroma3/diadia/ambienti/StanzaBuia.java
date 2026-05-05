package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private String attrezzoRichiesto;
	
	public StanzaBuia(String nome, String attrezzoRichiesto) {
		super(nome);
		this.attrezzoRichiesto=attrezzoRichiesto;
	}
	
	@Override 
	public String getDescrizione() {
		if(hasAttrezzo(attrezzoRichiesto)) {
			return super.getDescrizione();
		}
		return "Qui c'è un buio pesto";
	}
}
