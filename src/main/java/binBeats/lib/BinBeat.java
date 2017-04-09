package main.java.binBeats.lib;

/**
 * Represents a BinBeat
 */
public class BinBeat {
	private float carrierFrequency;
	private float beatFrequency;
	private String beatName;
	private float volume;
	
	public BinBeat() {}

	public BinBeat(float carrierFrequency, float beatFrequency) {
		this(carrierFrequency, beatFrequency, "", 100);
	}
	
	public BinBeat(float carrierFrequency, float beatFrequency, String beatName) {
		this(carrierFrequency, beatFrequency, beatName, 100);
	}
	
	public BinBeat(float carrierFrequency, float beatFrequency, float volume) {
		this(carrierFrequency, beatFrequency, "", volume);
	}
	
	public BinBeat(float carrierFrequency, float beatFrequency, String beatName, float volume) {
		this.carrierFrequency = carrierFrequency;
		this.beatFrequency = beatFrequency;
		this.beatName = beatName;
		this.setVolume(volume);
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

	/**
	 * Returns the name of the BinBeat
	 */	
	public String getBeatName(){
		return beatName;
	}
	
	/**
	 * Sets the name of the BinBeat
	 */
	public void setBeatName(String beatName){
		this.beatName = beatName;
	}	

	public String toString() {
		return "UserSetting: [Carrier =" + this.carrierFrequency + ", Beat =" + this.beatFrequency + ", BeatName = " + this.beatName + "]";
	}

	/**
	 * @return the volume of the BinBeat
	 */
	public float getVolume() {
		return volume;
	}

	/**
	 * Sets the volume of the BinBeat.
	 * @param volume volume of the BinBeat
	 */
	public void setVolume(float volume) {
		this.volume = volume;
	}
	
}
