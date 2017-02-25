package main.java.binBeats.lib;

import javax.sound.sampled.LineUnavailableException;

/**
 * Für das aufbereiten und Ausgeben von binauralen Beats zuständig
 * 
 * @author alex
 */
public class BinBeatsPlayer {
	private FrequencyPlayer playerLinks;
	private FrequencyPlayer playerRechts;

	public BinBeatsPlayer() throws LineUnavailableException {
		playerLinks = new FrequencyPlayer();
		playerRechts = new FrequencyPlayer();
	}

	public void play(int frequenzLinks, int frequenzRechts) {
		//TODO fachliche Prüfung der Frequenzen (Schwellwerte, Differenz etc.), ggf. in eigene Klasse auslagern
		
		playerLinks.play(frequenzLinks, Channel.left);
		playerRechts.play(frequenzRechts, Channel.right);
	}

	public void stop() {
		playerLinks.stop();
		playerRechts.stop();
	}
}
