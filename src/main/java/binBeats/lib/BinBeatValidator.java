package main.java.binBeats.lib;

/**
 * Class to indicate if a given BinBeat is valid or not
 */
public class BinBeatValidator {
	private final float CARRIER_FREQUENCY_MIN = 20f; // TODO: set correct value
	private final float CARRIER_FREQUENCY_MAX = 20000f; // TODO: set correct value
	
	private final float CARRIER_VOLUME_MIN = -80f; 
	private final float CARRIER_VOLUME_MAX = 6f;
	
	private final float BEAT_FREQUENCY_MIN = 0f; // TODO: set correct value
	private final float BEAT_FREQUENCY_MAX = 30f; // TODO: set correct value
	
	private final float BEAT_VOLUME_MIN = -80f; 
	private final float BEAT_VOLUME_MAX = 6f;
	
	
	public BinBeatValidator() {}
	
	/**
	 * Returns the allowed minimum base-frequency
	 */
	public float getCarrierFrequencyMin() {
		return CARRIER_FREQUENCY_MIN;
	}
	
	/**
	 * Returns the allowed maximum base-frequency
	 */
	public float getCarrierFrequencyMax() {
		return CARRIER_FREQUENCY_MAX;
	}
	
	/**
	 * Returns the allowed minimum base-frequency-volume
	 */
	public float getCarrierVolumeMin() {
		return CARRIER_VOLUME_MIN;
	}
	
	/**
	 * Returns the allowed maximum base-frequency-volume
	 */
	public float getCarrierVolumeMax() {
		return CARRIER_VOLUME_MAX;
	}
	
	/**
	 * Returns the allowed minimum difference-frequency
	 */
	public float getBeatFrequencyMin() {
		return BEAT_FREQUENCY_MIN;
	}
	
	/**
	 * Returns the allowed maximum difference-frequency
	 */
	public float getBeatFrequencyMax() {
		return BEAT_FREQUENCY_MAX;
	}
	
	/**
	 * Returns the allowed minimum difference-frequency-volume
	 */
	public float getBeatVolumeMin() {
		return BEAT_VOLUME_MIN;
	}
	
	/**
	 * Returns the allowed maximum difference-frequency-volume
	 */
	public float getBeatVolumeMax() {
		return BEAT_VOLUME_MAX;
	}
	
	/**
	 * Indicates whether the given BinBeat is valid or not
	 */
	public ValidationResult validate(BinBeat binBeat) {
		Boolean isValid = false;
		String message = "";
		
		if(binBeat.getCarrierFrequency() < CARRIER_FREQUENCY_MIN || binBeat.getCarrierFrequency() > CARRIER_FREQUENCY_MAX) {
			message = "The given CarrierFrequency is not valid";
		}
		else if(binBeat.getBeatFrquency() < BEAT_FREQUENCY_MIN || binBeat.getBeatFrquency() > BEAT_FREQUENCY_MAX) {
			message = "The given BeatFrequency is not valid";
		}
		else if(binBeat.getCarrierVolume() < CARRIER_VOLUME_MIN || binBeat.getCarrierVolume() > CARRIER_VOLUME_MAX) {
			message = "The given CarrierFrequency volume is not valid";
		}
		else if(binBeat.getBeatVolume() < BEAT_VOLUME_MIN || binBeat.getBeatVolume() > BEAT_VOLUME_MAX) {
			message = "The given BeatFrequency volume is not valid";
		}
		else {
			isValid = true;
		}
		
		return new ValidationResult(isValid, message);
	}
}
