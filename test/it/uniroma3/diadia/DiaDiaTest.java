package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DiaDiaTest {

	@Test
	public void testPartitaConSoloFine() {
		String[] comandi = {"fine"};
		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		assertTrue(io.hasNextMessaggio(), "Dovrebbe esserci almeno il messaggio di benvenuto");
		String primoMessaggio = io.nextMessaggio();
		assertTrue(primoMessaggio.contains("Universita'"));
	}

	@Test
	public void testPartitaVinta() {
		// Supponiamo che il percorso per vincere sia "nord" dall'atrio alla biblioteca
		String[] comandi = {"vai nord", "fine"};
		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		// Verifichiamo se tra i messaggi c'è quello di vittoria o il nome della stanza finale
		boolean bibliotecaRaggiunta = false;
		while(io.hasNextMessaggio()) {
			if (io.nextMessaggio().contains("Biblioteca")) {
				bibliotecaRaggiunta = true;
			}
		}
		assertTrue(bibliotecaRaggiunta, "Il giocatore dovrebbe aver raggiunto la Biblioteca");
	}
}
