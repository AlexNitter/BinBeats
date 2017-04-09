package main.java.binBeats.lib;

import javax.sound.sampled.LineUnavailableException;

/**
 * Plays a BinBeat
 */
public class BinBeatsPlayer {
	private BinBeat binBeat;
	private StereoFrequencyPlayer playerLeft;
	private StereoFrequencyPlayer playerRight;
	private BinBeatValidator validator;
	
	public BinBeatsPlayer() throws LineUnavailableException {
		playerLeft = new StereoFrequencyPlayer(Channel.left);
		playerRight = new StereoFrequencyPlayer(Channel.right);
		validator = new BinBeatValidator();
	}

	/**
	 * Validates the given BinBeat and sets it
	 * @throws IllegalArgumentException
	 */
	public void setBinBeat(BinBeat binBeat) throws IllegalArgumentException {		
		ValidationResult result = validator.validate(binBeat);
		
		if(!result.isValid()) {
			throw new IllegalArgumentException(result.getMessage());
		}
		
		this.binBeat = binBeat;
		setVolume();
		
		this.playerLeft.setFrequency(binBeat.getCarrierFrequency());
		/* Frequency for the right-side player is calculated from
		 * CarrierFrequency + BeatFrequency
		 * unless it exceeds the upper bound of 1500 Hz
		 */
		if(binBeat.getCarrierFrequency() + binBeat.getBeatFrequency() <= validator.getCarrierFrequencyMax()) {
			this.playerRight.setFrequency(binBeat.getCarrierFrequency() + binBeat.getBeatFrequency());
		} else {
			this.playerRight.setFrequency(binBeat.getCarrierFrequency() - binBeat.getBeatFrequency());
		}
	}
	
	/**
	 * Returns the given BinBeat
	 */
	public BinBeat getBinBeat() {
		return this.binBeat;
	}
	
	/**
	 * Sets the volume of the player to the volume of the BinBeat 
	 */
	public void setVolume() {		
		playerLeft.setVolume(binBeat.getVolume());
		playerRight.setVolume(binBeat.getVolume());
	}
	
	/**
	 * Plays the given BinBeat until the stop-method gets called
	 */
	public void play() throws LineUnavailableException {		
		playerLeft.play();
		playerRight.play();
	}

	/**
	 * Stops the playing
	 */
	public void stop() {
		playerLeft.stop();
		playerRight.stop();
	}
}
