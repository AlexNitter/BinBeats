package main.java.binBeats.lib;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * Base-Class for playing frequencies via javax.sound-API
 */
public abstract class FrequencyPlayer {
	
	// Max size of the sourceDataLine-buffer before cleanup (100MByte)
	private final int maxBytesInBuffer = 1 * 1024 * 1024;
	private int bytesInBuffer = 0;
	
	private Thread playThread;
	
	protected int sampleRate;
	protected int sampleSizeInBits;
	protected float frequency;
	protected float volume;
	protected int channels;
	
	protected AudioFormat audioFormat;
	protected SourceDataLine sdl;
	protected VolumeCalculator calc = new VolumeCalculator();	
	
	protected boolean isPlaying = false;

	
	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}
	
	public float getFrequency() {
		return frequency;
	}
	
	public void setVolume(float volume) {
		this.volume =  volume;//calc.calculateMasterGainVolume(volume);
	}
		
	/**
	 * Creates a new thread and starts to play the frquency until the stop-method gets called
	 * @throws LineUnavailableException
	 */
	public void play() throws LineUnavailableException {		
		sdlStart();

		isPlaying = true;

		// play the sound in a seperate thread to non-block the main(-gui)-thread
		playThread = new Thread() {
			public void run() {
				while (isPlaying) {
					
					// Buffer with sound-data for 1 second
					byte[] buffer = createBuffer();
					
					// clear the buffer when it reaches a given size (causes a unobtrusive click)
					if(bytesInBuffer >= maxBytesInBuffer) {
						sdl.flush();
						bytesInBuffer = 0;
					}
					
					// Write data to sourceDataLine
					bytesInBuffer += sdl.write(buffer, 0, buffer.length);
				}
			}
		};
		
		playThread.start();
	}

	/**
	 * Stops the playing and clears the sourceDataLine-buffer
	 */
	public void stop() {
		isPlaying = false;

		if (sdl.isOpen()) {
			sdl.drain();
			sdl.close();
		}
	}

	/**
	 * Plays the frequency for the given amount of seconds
	 * @param seconds amount of the seconds the sound should be played
	 * @throws LineUnavailableException
	 */
	public void playForSeconds(int seconds) throws LineUnavailableException {
		sdlStart();

		isPlaying = true;
		
		// Buffer with sound-data for 1 second
		byte[] buffer = createBuffer(seconds);
		
		// Write data to sourceDataLine
		sdl.write(buffer, 0, buffer.length);
		sdl.drain();
		sdl.close();
		
		isPlaying = false;
	}
	
	/**
	 * Sets the channel to pan the sound to the left or right audio-channel
	 */
	protected void setChannel() {
		// possiblity for subclasses to override and pan to left or right
	}
	
	private void sdlStart() throws LineUnavailableException {
		sdl.open(audioFormat, sampleRate);
		sdl.start();
		
		setChannel();
		setVolume();
	}

	private void setVolume() {
		if (sdl.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			FloatControl gain = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);

			//gain.setValue(volume);
		}
	}
	
	/**
	 * Generates a byte-array which contains the sin-wave-sound-data for a given amount of seconds
	 * @return 
	 */
	private byte[] createBuffer(int seconds) {
		int samples = sampleRate * seconds * channels; 
		byte[] output = new byte[samples];

		double periode = (double) sampleRate / (frequency / channels);

		for (int i = 0; i < output.length; i++) {
			double winkelfrequenz = 2.0 * Math.PI * i / periode;
			double wert = (Math.sin(winkelfrequenz) * volume);
			output[i] = (byte) wert;
		}

		return output;
	}
	
	/**
	 * Generates a byte-array which contains the sin-wave-sound-data for 1 second
	 * @return 
	 */
	private byte[] createBuffer() {
		return createBuffer(1);
	}
}
