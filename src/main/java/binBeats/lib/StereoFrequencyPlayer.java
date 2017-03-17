package main.java.binBeats.lib;

import javax.sound.sampled.*;
import javax.sound.sampled.AudioFormat.Encoding;

/**
 * Für die tatsächliche Ausgabe einer Frequenz auf der Audioausgabe des PCs
 * 
 * @author alex
 */
public class StereoFrequencyPlayer extends FrequencyPlayer {
	private Channel kanal;
	
	public StereoFrequencyPlayer(Channel kanal) throws LineUnavailableException {
		sampleRate = 44100; //16 * 1024;
		sampleSizeInBits = 8;
		int channels = 2;
		boolean signed = true;
		boolean bigEndian = false;

		this.kanal = kanal;
		
		audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);		
		sdl = AudioSystem.getSourceDataLine(audioFormat);
	}

	@Override
	public void setFrequenz(int frequenz) {
		this.frequenz = frequenz / 2;
	}
	
	@Override
	protected void setChannel() {
		if (sdl.isControlSupported(FloatControl.Type.PAN)) {
			FloatControl pan = (FloatControl) sdl.getControl(FloatControl.Type.PAN);

			switch (kanal) {
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
