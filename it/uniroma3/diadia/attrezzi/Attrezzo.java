package it.uniroma3.diadia.attrezzi;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Attrezzo {

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	@Override
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}

	/**
	 * Definisce quando due attrezzi sono considerati uguali.
	 * Sono uguali se hanno lo stesso nome e lo stesso peso.
	 */
	@Override
	public boolean equals(Object o) {
		// Se sono letteralmente lo stesso oggetto in memoria, sono uguali
		if (this == o) return true;
		
		// Se l'altro oggetto è nullo o appartiene a un'altra classe, non sono uguali
		if (o == null || this.getClass() != o.getClass()) return false;
		
		// Ora che so che 'o' è sicuramente un Attrezzo, lo "trasformo" (cast)
		Attrezzo quelloDaConfrontare = (Attrezzo) o;
		
		// Confronto il peso e il nome
		return this.peso == quelloDaConfrontare.peso && 
		       this.nome.equals(quelloDaConfrontare.nome);
	}

	/**
	 * Metodo compagno di equals. Genera un numero identificativo
	 * basato sulle variabili dell'oggetto.
	 */
	@Override
	public int hashCode() {
		return this.nome.hashCode() + this.peso;
	}
}