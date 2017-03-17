package main.java.binBeats.lib;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.sound.sampled.*;

/**
 * Für die tatsächliche Ausgabe einer Frequenz auf der Audioausgabe des PCs
 * 
 * @author alex
 */
public class MonoFrequencyPlayer extends FrequencyPlayer {
	public MonoFrequencyPlayer() throws LineUnavailableException, FileNotFoundException, UnsupportedEncodingException {
		sampleRate = 41000;
		sampleSizeInBits = 8;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = true;

		audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		sdl = AudioSystem.getSourceDataLine(audioFormat);
	}
}
