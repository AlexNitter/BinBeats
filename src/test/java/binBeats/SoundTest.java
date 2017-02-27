package test.java.binBeats;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.sound.sampled.*;

public class SoundTest {

	@Test
	public void playSoundMono() throws LineUnavailableException, FileNotFoundException, UnsupportedEncodingException {
		// Anzahl der Abtastungen (Samples) des Signals pro Sekunde
		// Erlaubt: 8000, 11025, 16000, 22050, 44100
		float sampleRate = 44100; 
		
		// Größe jedes einzelnen Samples
		// Erlaubt: 8, 16
		int sampleSizeBits = 8;
		
		// Erlaubt: 1 = mono, 2 = stereo
		int channels = 1;
		
		boolean signed = true;
		boolean bigEndian = false;
		float panValue = 1f;

		AudioFormat audioFormat = new AudioFormat(sampleRate, sampleSizeBits, channels, signed, bigEndian);

		play(audioFormat, panValue);
	}

	@Test
	public void playSoundStereo() throws LineUnavailableException {
		AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
		float sampleRate = 44100f;
		int sampleSizeBits = 8;
		int channels = 1;
		int frameSize = channels * 2;
		float frameRate = (float) sampleSizeBits;
		boolean bigEndian = false;
		float panValue = 1f;

		AudioFormat audioFormat = new AudioFormat(encoding, sampleRate, sampleSizeBits, channels, frameSize, frameRate,
				bigEndian);

		// play(audioFormat, panValue);
	}

	private void play(AudioFormat audioFormat, float panValue)
			throws LineUnavailableException, FileNotFoundException, UnsupportedEncodingException {
		SourceDataLine sdl = AudioSystem.getSourceDataLine(audioFormat);

		FloatControl.Type pan = FloatControl.Type.PAN;
		if (sdl.isControlSupported(pan)) {
			FloatControl panControl = (FloatControl) sdl.getControl(pan);
			panControl.setValue(panValue);
		} else {
			// Assert.fail("Das SourceDataLine-Objekt unterstützt das Control '"
			// + pan.toString() + "' nicht");
		}

		sdl.open();
		sdl.start();

		byte[] buf = new byte[1];
		double frequenz = 440;

		PrintWriter writer = new PrintWriter("/home/alex/VM_Share/werte_" + frequenz + ".csv", "UTF-8");
		writer.println("i;angle;wert");

		for (int i = 0; i < 1000 * (float) 440 / 1000; i++) {
			double angle = i / ((float) frequenz) * 2.0 * Math.PI;
			double wert = (Math.sin(angle) * 100);
			buf[0] = (byte) wert;

			//writer.println(i + ";" + String.format("%.2f", angle) + ";" + String.format("%.2f", wert));
			sdl.write(buf, 0, 1);
		}

		writer.close();

		sdl.drain();
		sdl.close();
	}

	//@Test
	public void play2() throws LineUnavailableException {
		final AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 2, true, true);
		SourceDataLine line = AudioSystem.getSourceDataLine(af);
		line.open(af, SAMPLE_RATE);
		line.start();

		boolean forwardNotBack = true;

		for (double freq = 400; freq <= 800;) {
			byte[] toneBuffer = createSinWaveBuffer(freq, 20);
			int count = line.write(toneBuffer, 0, toneBuffer.length);

			if (forwardNotBack) {
				freq += 20;
				forwardNotBack = false;
			} else {
				freq -= 10;
				forwardNotBack = true;
			}
		}

		line.drain();
		line.close();
	}

	private final int SAMPLE_RATE = 16 * 1024;

	public byte[] createSinWaveBuffer(double freq, int ms) {
		int samples = (int) ((ms * SAMPLE_RATE) / 1000);
		byte[] output = new byte[samples];
		//
		double period = (double) SAMPLE_RATE / freq;
		for (int i = 0; i < output.length; i++) {
			double angle = 2.0 * Math.PI * i / period;
			output[i] = (byte) (Math.sin(angle) * 127f);
		}

		return output;
	}
}
