package main.java.binBeats.lib;

import javax.sound.sampled.LineUnavailableException;

/**
 * Für das aufbereiten und Ausgeben von binauralen Beats zuständig
 * 
 * @author alex
 */
public class BinBeatsPlayer {
	private int traegerFrequenz;
	private int differenzFrequenz;
	
	private StereoFrequencyPlayer playerLinks;
	private StereoFrequencyPlayer playerRechts;

	public BinBeatsPlayer() throws LineUnavailableException {
		playerLinks = new StereoFrequencyPlayer(Channel.left);
		playerRechts = new StereoFrequencyPlayer(Channel.right);
	}

	public void setTraegerFrequenz(int traegerFrequenz) {
		//TODO fachliche Prüfung der Frequenzen (Schwellwerte, Differenz etc.), ggf. in eigene Klasse auslagern
		
		this.traegerFrequenz = traegerFrequenz;
		this.playerLinks.setFrequenz(traegerFrequenz);
	}
	
	public int getTraegerFrequenz() {
		return traegerFrequenz;
	}
	
	public void setDifferenzFrequenz(int differenzFrequenz) {
		//TODO fachliche Prüfung der Frequenzen (Schwellwerte, Differenz etc.), ggf. in eigene Klasse auslagern
		
		this.differenzFrequenz = differenzFrequenz;
		this.playerRechts.setFrequenz(differenzFrequenz);
	}
	
	public int getDifferenzFrequenz() {
		return differenzFrequenz;	
	}
	
	public void play() throws LineUnavailableException {
		//TODO fachliche Prüfung der Frequenzen (Schwellwerte, Differenz etc.), ggf. in eigene Klasse auslagern
		
		playerRechts.play();
		playerLinks.play();
	}

	public void stop() {
		playerLinks.stop();
		playerRechts.stop();
	}
}
