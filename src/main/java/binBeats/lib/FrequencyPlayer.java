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
		sdl.open(audioFormat, sampleRate);
		sdl.start();

		setChannel();

		isPlaying = true;

		// play the sound in a seperate thread to non-block the main(-gui)-thread
		new Thread() {
			public void run() {
				while (isPlaying) {
					
					// Buffer with sound-data for 1 second
					byte[] buffer = createBuffer();
					
					// Write data to sourceDataLine
					sdl.write(buffer, 0, buffer.length);
					
					try {
						// Wait some time to empty the buffer - TODO: Find the best time
						Thread.sleep(800);
					} catch (InterruptedException e) {}
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


	/**
	 * Generates a byte-array which contains the sin-wave-sound-data for 1 second
	 * @return 
	 * 	
	 */
	private byte[] createBuffer() {
		int samples = sampleRate; 
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
