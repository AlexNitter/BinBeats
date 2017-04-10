package main.java.binBeats.lib;

/**
 * Class to indicate if a given BinBeat is valid or not
 */
public class BinBeatValidator {
	private final float CARRIER_FREQUENCY_MIN = 20f;
	private final float CARRIER_FREQUENCY_MAX = 1500f;
	
	private final float VOLUME_MIN = 0f; 
	private final float VOLUME_MAX = 100f;
	
	private final float BEAT_FREQUENCY_MIN = 0.5f;
	private final float BEAT_FREQUENCY_MAX = 30f;
		
	
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
	public float getVolumeMin() {
		return VOLUME_MIN;
	}
	
	/**
	 * Returns the allowed maximum base-frequency-volume
	 */
	public float getVolumeMax() {
		return VOLUME_MAX;
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
	 * Indicates whether the given BinBeat is valid or not
	 * @param binBeat The beat to validate
	 * @param validateName Flag to decide if the name of the beat should be validated or not
	 * @return ValidationResult which contains a Boolean-flag to indicate whether the beat is valid or not and a message
	 */
	public ValidationResult validate(BinBeat binBeat, Boolean validateName) {
		Boolean isValid = false;
		String message = "";
		String beatName = binBeat.getBeatName();
		if(validateName && beatName.trim() == ""){
			message = "The BinBeat must contain a name;";
		}
		
		if(binBeat.getCarrierFrequency() < CARRIER_FREQUENCY_MIN || binBeat.getCarrierFrequency() > CARRIER_FREQUENCY_MAX) {
			message = "The given CarrierFrequency is not valid";
		}
		else if(binBeat.getBeatFrequency() < BEAT_FREQUENCY_MIN || binBeat.getBeatFrequency() > BEAT_FREQUENCY_MAX) {
			message = "The given BeatFrequency is not valid";
		}
		else if(binBeat.getVolume() < VOLUME_MIN || binBeat.getVolume() > VOLUME_MAX) {
			message = "The given CarrierFrequency volume is not valid";
		}
		else {
			isValid = true;
		}
		
		return new ValidationResult(isValid, message);
	}
	
	/**
	 * Indicates whether the given BinBeat is valid or not (without validating the name)
	 * @param binBeat The beat to validate
	 * @return ValidationResult which contains a Boolean-flag to indicate whether the beat is valid or not and a message
	 */
	public ValidationResult validate(BinBeat binBeat) {
		return validate(binBeat, false);
	}
}
