package main.java.binBeats.lib;

/**
 * Represents a BinBeat
 */
public class BinBeat {
	private float carrierFrequency;
	private float beatFrquency;
	
	public BinBeat() {}

	public BinBeat(float carrierFrequency, float beatFrequency) {
		this.carrierFrequency = carrierFrequency;
		this.beatFrquency = beatFrequency;
	}
	
	/**
	 * Returns the base-frequency of the BinBeat
	 */
	public float getCarrierFrequency() {
		return carrierFrequency;
	}

	/**
	 * Sets the base-frequency of the BinBeat
	 */
	public void setCarrierFrequency(float carrierFrequency) {
		this.carrierFrequency = carrierFrequency;
	}

	/**
	 * Returns the difference-frequency of the BinBeat
	 */
	public float getBeatFrquency() {
		return beatFrquency;
	}

	/**
	 * Sets the difference-frequency of the BinBeat
	 */
	public void setBeatFrquency(float beatFrquency) {
		this.beatFrquency = beatFrquency;
	}	
}
