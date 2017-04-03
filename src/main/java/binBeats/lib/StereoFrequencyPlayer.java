package main.java.binBeats.lib;

import javax.sound.sampled.*;
import javax.sound.sampled.AudioFormat.Encoding;

/**
 * Modul to play a given frequency in stereo-mode
 */
public class StereoFrequencyPlayer extends FrequencyPlayer {
	private Channel channel;
	
	public StereoFrequencyPlayer(Channel channel) throws LineUnavailableException {
		sampleRate = 44100; 
		sampleSizeInBits = 8;
		channels = 2;
		boolean signed = true;
		boolean bigEndian = false;

		this.channel = channel;
		
		audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);		
		sdl = AudioSystem.getSourceDataLine(audioFormat);
	}
	
	@Override
	protected void setChannel() {
		if (sdl.isControlSupported(FloatControl.Type.PAN)) {
			FloatControl pan = (FloatControl) sdl.getControl(FloatControl.Type.PAN);

			switch (channel) {
			case left:
				pan.setValue(-1.0f);
				break;
			case right:
				pan.setValue(1.0f);
				break;
			}
		}
	}
}
