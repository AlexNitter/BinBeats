package main.java.binBeats.lib;

import javax.sound.sampled.LineUnavailableException;

/**
 * Plays a BinBeat
 */
public class BinBeatsPlayer {
	private BinBeat binBeat;
	private StereoFrequencyPlayer playerLinks;
	private StereoFrequencyPlayer playerRechts;
	private BinBeatValidator validator;
	
	public BinBeatsPlayer() throws LineUnavailableException {
		playerLinks = new StereoFrequencyPlayer(Channel.left);
		playerRechts = new StereoFrequencyPlayer(Channel.right);
		validator = new BinBeatValidator();
	}

	/**
	 * Validates the given BinBeat and sets it
	 * @throws IllegalArgumentException
	 */
	public void setBinBeat(BinBeat binBeat) throws IllegalArgumentException {		
		ValidationResult result = validator.Validate(binBeat);
		
		if(!result.isValid()) {
			throw new IllegalArgumentException(result.getMessage());
		}
		
		this.binBeat = binBeat;
		this.playerLinks.setFrequency(binBeat.getCarrierFrequency());
		this.playerRechts.setFrequency(binBeat.getBeatFrquency());
	}
	
	/**
	 * Returns the given BinBeat
	 */
	public BinBeat getBinBeat() {
		return this.binBeat;
	}
	
	/**
	 * Plays the given BinBeat until the stop-method gets called
	 */
	public void play() throws LineUnavailableException {		
		playerRechts.play();
		playerLinks.play();
	}

	/**
	 * Stops the playing
	 */
	public void stop() {
		playerLinks.stop();
		playerRechts.stop();
	}
}
