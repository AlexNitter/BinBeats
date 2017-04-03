package test.java.binBeats;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.binBeats.lib.BinBeat;
import main.java.binBeats.lib.BinBeatValidator;
import main.java.binBeats.lib.ValidationResult;

public class BinBeatValidatorTest {

	@Test
	public void BinBeatValidator_valid_test() {
		BinBeatValidator validator = new BinBeatValidator();
		
		BinBeat binBeat = new BinBeat(validator.getCarrierFrequencyMin() + 10, validator.getBeatFrequencyMin() + 10);
		
		ValidationResult result = validator.validate(binBeat);
		
		assertEquals(result.isValid(),true);
	}
	
	@Test
	public void BinBeatValidator_invalid_1_test() {
		BinBeatValidator validator = new BinBeatValidator();
		
		BinBeat binBeat = new BinBeat(validator.getCarrierFrequencyMin() - 1, validator.getBeatFrequencyMin() + 10);
		
		ValidationResult result = validator.validate(binBeat);
		
		assertEquals(result.isValid(),false);
	}
	
	@Test
	public void BinBeatValidator_invalid_2_test() {
		BinBeatValidator validator = new BinBeatValidator();
		
		BinBeat binBeat = new BinBeat(validator.getCarrierFrequencyMax() + 1, validator.getBeatFrequencyMin() + 10);
		
		ValidationResult result = validator.validate(binBeat);
		
		assertEquals(result.isValid(),false);
	}
	
	@Test
	public void BinBeatValidator_invalid_3_test() {
		BinBeatValidator validator = new BinBeatValidator();
		
		BinBeat binBeat = new BinBeat(validator.getCarrierFrequencyMin() + 10, validator.getBeatFrequencyMin() - 1);
		
		ValidationResult result = validator.validate(binBeat);
		
		assertEquals(result.isValid(),false);
	}
	
	@Test
	public void BinBeatValidator_invalid_4_test() {
		BinBeatValidator validator = new BinBeatValidator();
		
		BinBeat binBeat = new BinBeat(validator.getCarrierFrequencyMin() + 10, validator.getBeatFrequencyMax() + 1);
		
		ValidationResult result = validator.validate(binBeat);
		
		assertEquals(result.isValid(),false);
	}
	
	@Test
	public void BinBeatValidator_invalid_5_test() {
		BinBeatValidator validator = new BinBeatValidator();
		
		BinBeat binBeat = new BinBeat(validator.getCarrierFrequencyMin() + 10, 
									  validator.getBeatFrequencyMin() + 10,
									  validator.getCarrierVolumeMin() - 1,
									  validator.getBeatVolumeMin() + 10);
		
		ValidationResult result = validator.validate(binBeat);
		
		assertEquals(result.isValid(),false);
	}
	
	@Test
	public void BinBeatValidator_invalid_6_test() {
		BinBeatValidator validator = new BinBeatValidator();
		
		BinBeat binBeat = new BinBeat(validator.getCarrierFrequencyMin() + 10, 
									  validator.getBeatFrequencyMin() + 10,
									  validator.getCarrierVolumeMax() + 1,
									  validator.getBeatVolumeMin() + 10);
		
		ValidationResult result = validator.validate(binBeat);
		
		assertEquals(result.isValid(),false);
	}
	
	@Test
	public void BinBeatValidator_invalid_7_test() {
		BinBeatValidator validator = new BinBeatValidator();
		
		BinBeat binBeat = new BinBeat(validator.getCarrierFrequencyMin() + 10, 
									  validator.getBeatFrequencyMin() + 10,
									  validator.getCarrierVolumeMin() + 10,
									  validator.getBeatVolumeMin() - 1);
		
		ValidationResult result = validator.validate(binBeat);
		
		assertEquals(result.isValid(),false);
	}
	
	@Test
	public void BinBeatValidator_invalid_8_test() {
		BinBeatValidator validator = new BinBeatValidator();
		
		BinBeat binBeat = new BinBeat(validator.getCarrierFrequencyMin() + 10, 
									  validator.getBeatFrequencyMin() + 10,
									  validator.getCarrierVolumeMin() + 10,
									  validator.getBeatVolumeMax() + 1);
		
		ValidationResult result = validator.validate(binBeat);
		
		assertEquals(result.isValid(),false);
	}
}
