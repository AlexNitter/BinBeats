package main.java.binBeats.lib;

import javax.sound.sampled.LineUnavailableException;

/**
 * Für das aufbereiten und Ausgeben von binauralen Beats zuständig
 * 
 * @author alex
 */
public class BinBeatsPlayer {
	private StereoFrequencyPlayer playerLinks;
	private StereoFrequencyPlayer playerRechts;

	public BinBeatsPlayer() throws LineUnavailableException {
		playerLinks = new StereoFrequencyPlayer(Channel.left);
		playerRechts = new StereoFrequencyPlayer(Channel.right);
	}

	public void play(int frequenzLinks, int frequenzRechts) throws LineUnavailableException {
		//TODO fachliche Prüfung der Frequenzen (Schwellwerte, Differenz etc.), ggf. in eigene Klasse auslagern
		
		
		playerRechts.play(frequenzRechts);
		playerLinks.play(frequenzLinks);
	}

	public void stop() {
		playerLinks.stop();
		playerRechts.stop();
	}
}
