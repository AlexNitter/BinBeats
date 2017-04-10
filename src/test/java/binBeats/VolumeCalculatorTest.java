package test.java.binBeats;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.binBeats.lib.VolumeCalculator;

public class VolumeCalculatorTest {

	@Test
	public void calculateMasterGainVolume() {
		VolumeCalculator calc = new VolumeCalculator();
		
		assertTrue(calc.calculateMasterGainVolume(0f) == -80f);
		assertTrue(calc.calculateMasterGainVolume(50f) == -37f);
		assertTrue(calc.calculateMasterGainVolume(100f) == 6f);
	}
}
