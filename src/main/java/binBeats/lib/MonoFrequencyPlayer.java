package main.java.binBeats.lib;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.sound.sampled.*;

/**
 * Modul to play a given frequency in mono-mode
 */
public class MonoFrequencyPlayer extends FrequencyPlayer {
	public MonoFrequencyPlayer() throws LineUnavailableException, FileNotFoundException, UnsupportedEncodingException {
		sampleRate = 44100;
		sampleSizeInBits = 8;
		channels = 1;
		boolean signed = true;
		boolean bigEndian = true;

		audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		sdl = AudioSystem.getSourceDataLine(audioFormat);
	}
}
