package main.java.binBeats.lib;

/**
 * Represents a BinBeat
 */
public class BinBeat {
	private int carrierFrequency;
	private float beatFrequency;
	private String beatName;
	private float carrierVolume;
	private float beatVolume;
	
	public BinBeat() {}

	public BinBeat(int carrierFrequency, float beatFrequency){
		this(carrierFrequency, beatFrequency, "", 127, 127);
	}
	
	public BinBeat(int carrierFrequency, float beatFrequency, String beatName){
		this(carrierFrequency, beatFrequency, beatName, 127,127);
	}
	
	public BinBeat(int carrierFrequency, float beatFrequency, String beatName, float carrierVolume, float beatVolume) {
		this.carrierFrequency = carrierFrequency;
		this.beatFrequency = beatFrequency;
		this.beatName = beatName;
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
	public void setCarrierFrequency(int carrierFrequency) {
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
	 * Returns the name of a BinBeat
	 * */	
	public String getBeatName(){
		return beatName;
	}
	
	/**
	 * Sets the name of the BinBeat
	 * 
	 * 
	 * */
	public void setBeatName(String beatName){
		this.beatName = beatName;
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

	public String toString() {
		return "UserSetting: [Carrier =" + this.carrierFrequency + ", Beat =" + this.beatFrequency + ", BeatName = " + this.beatName + "]";
	}
	
}
