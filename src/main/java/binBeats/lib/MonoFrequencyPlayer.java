package main.java.binBeats.lib;

import javax.sound.sampled.*;

/**
 * Für die tatsächliche Ausgabe einer Frequenz auf der Audioausgabe des PCs
 * 
 * @author alex
 */
public class MonoFrequencyPlayer {
	private Boolean isPlaying = false;

	private int sampleRate = 16000;
	private int sampleSizeInBits = 8;

	private AudioFormat audioFormat;
	private SourceDataLine sdl;

	public MonoFrequencyPlayer() throws LineUnavailableException {
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;

		audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		sdl = AudioSystem.getSourceDataLine(audioFormat);
	}

	/**
	 * Spielt die Frequenz auf dem Kanal ab
	 * 
	 * @param frequency
	 *            Die abzuspielende Frequenz
	 * @param channel
	 *            Der Kanal (links oder rechts), auf dem die Frequenz abgespielt
	 *            werden soll
	 * @throws LineUnavailableException
	 */
	public void play(int frequenz) throws LineUnavailableException {
		sdl.open();
		sdl.start();
		isPlaying = true;

		new Thread() {
			public void run() {
				while (isPlaying) {
					byte[] buffer = createSinWaveBuffer(frequenz, 50);
					sdl.write(buffer, 0, buffer.length);
				}
			}
		}.start();
	}

	/**
	 * Stoppt die Audioausgabe
	 */
	public void stop() {
		isPlaying = false;

		if (sdl.isOpen()) {
			sdl.drain();
			sdl.close();
		}
	}

	private byte[] createSinWaveBuffer(double frequenz, int ms) {
		int samples = (int) ((ms * sampleRate) / 1000);
		byte[] output = new byte[samples];
		
		double periode = (double) sampleRate / frequenz;
		int amplitude = 100;
		
		for (int i = 0; i < output.length; i++) {
			double winkelfrequenz = 2.0 * Math.PI * i / periode;
			output[i] = (byte) (Math.sin(winkelfrequenz) * amplitude);
		}

		return output;
	}
}
