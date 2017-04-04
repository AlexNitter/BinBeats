package main.java.binBeats.lib;

/**
 * Represents a BinBeat
 */
public class BinBeat {
	private float carrierFrequency;
	private float beatFrequency;
	private float carrierVolume;
	private float beatVolume;
	
	public BinBeat() {}

	public BinBeat(float carrierFrequency, float beatFrequency){
		this(carrierFrequency, beatFrequency, 127, 127);
	}
	
	public BinBeat(float carrierFrequency, float beatFrequency, float carrierVolume, float beatVolume) {
		this.carrierFrequency = carrierFrequency;
		this.beatFrequency = beatFrequency;
		this.carrierVolume = carrierVolume;
		this.beatVolume = beatVolume;
	}
	
	/**
	 * Returns the base-frequency of the BinBeat
	 */
	public float getCarrierFrequency() {
		return carrierFrequency;
	}

	/**
	 * Sets the base-frequency of the BinBeat,
	 * range: 20 - 1500 Hz
	 */
	public void setCarrierFrequency(float carrierFrequency) {
		this.carrierFrequency = carrierFrequency;
	}

	/**
	 * Returns the difference-frequency of the BinBeat
	 */
	public float getBeatFrequency() {
		return beatFrequency;
	}

	/**
	 * Sets the difference-frequency of the BinBeat,
	 * range: 0.5 - 30 Hz
	 */
	public void setBeatFrequency(float beatFrequency) {
		this.beatFrequency = beatFrequency;
	}

	public float getCarrierVolume() {
		return carrierVolume;
	}

	public void setCarrierVolume(float carrierVolume) {
		this.carrierVolume = carrierVolume;
	}

	public float getBeatVolume() {
		return beatVolume;
	}

	public void setBeatVolume(float beatVolume) {
		this.beatVolume = beatVolume;
	}	
}
