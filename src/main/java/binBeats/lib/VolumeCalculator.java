package main.java.binBeats.lib;

/**
 * Mapper between the MASTER_GAIN-volume-range (-6 to +80) and the volume-range which is used in the
 * BinBeats-application based on a percent-value (0 to +100) 
 */
public class VolumeCalculator {
	private final float minMasterGainVolume = -80f;
	private final float maxMasterGainVolume = 6f;
	
	private float minPercentValue;
	private float maxPercentValue;
	private float stepsMgToPercent;
	
	public VolumeCalculator() {
		BinBeatValidator validator = new BinBeatValidator();
		minPercentValue = validator.getVolumeMin();
		maxPercentValue = validator.getVolumeMax();
		
		stepsMgToPercent = (maxMasterGainVolume - minMasterGainVolume) / (maxPercentValue - minPercentValue);
	}
	
	/**
	 * The MASTER_GAIN-Control of the sourceDataLine accepts values from the range -80 to +6
	 * This method calculates the MASTER_GAIN-value from a +-0 to +100 range
	 * @param percentValue the volume-value which comes from the GUI (range +-0 to +100)
	 * @return actual MASTER_GAIN-volume-value for the sourceDataLine (range -80 to +6)
	 */
	public float calculateMasterGainVolume(float percentValue) {
		float temp = minMasterGainVolume + stepsMgToPercent * percentValue;
		
		return temp;
	}
}
