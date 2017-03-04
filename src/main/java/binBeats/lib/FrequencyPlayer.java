package main.java.binBeats.lib;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public abstract class FrequencyPlayer {
	protected int sampleRate;
	protected int sampleSizeInBits;
	protected int frequenz;
	
	protected AudioFormat audioFormat;
	protected SourceDataLine sdl;

	protected boolean isPlaying = false;

	public void setFrequenz(int frequenz) {
		this.frequenz = frequenz;
	}
	
	public int getFrequenz() {
		return frequenz;
	}
	
	public void play() throws LineUnavailableException {
		this.frequenz = frequenz;
		
		sdl.open(audioFormat, sampleRate);
		sdl.start();

		setChannel();

		isPlaying = true;

		new Thread() {
			public void run() {
				while (isPlaying) {
					byte[] buffer;
					buffer = createSinWaveBuffer(1000);
					//buffer = generateSineWavefreq(frequenz, 1);
					sdl.write(buffer, 0, buffer.length);
				}
			}
		}.start();
	}

	public void stop() {
		isPlaying = false;

		if (sdl.isOpen()) {
			sdl.drain();
			sdl.close();
		}
	}

	protected void setChannel() {
		// Wird im StereoFrequencyPlayer überschrieben
	}

	private byte[] generateSineWavefreq(int seconds) {
		int samples = seconds * sampleRate;
		byte[] output = new byte[samples];

		double periode = (double) (sampleRate / frequenz);
		int amplitude = 100;

		for (int i = 0; i < output.length; i++) {
			double winkelfrequenz = (2.0 * Math.PI * i) / periode;
			double wert = (Math.sin(winkelfrequenz) * amplitude);
			output[i] = (byte) wert;
		}

		return output;
	}

	private byte[] createSinWaveBuffer(int ms) {
		int samples = (int) ((ms * sampleRate) / 1000);
		byte[] output = new byte[samples];

		double periode = (double) sampleRate / frequenz;
		int amplitude = 100;

		for (int i = 0; i < output.length; i++) {
			double winkelfrequenz = 2.0 * Math.PI * i / periode;
			double wert = (Math.sin(winkelfrequenz) * amplitude);
			output[i] = (byte) wert;
		}

		return output;
	}
}
