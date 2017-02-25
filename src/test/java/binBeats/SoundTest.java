package test.java.binBeats;

import org.junit.Assert;
import org.junit.Test;
import javax.sound.sampled.*;

public class SoundTest {

	@Test
	public void playSoundMono() throws LineUnavailableException {
		float sampleRate = 44100f;
		int sampleSizeBits = 8;
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

		play(audioFormat, panValue);
	}

	private void play(AudioFormat audioFormat, float panValue) throws LineUnavailableException {
		SourceDataLine sdl = AudioSystem.getSourceDataLine(audioFormat);

		FloatControl.Type pan = FloatControl.Type.PAN;
		if (sdl.isControlSupported(pan)) {
			FloatControl panControl = (FloatControl) sdl.getControl(pan);
			panControl.setValue(panValue);
		} else {
			//Assert.fail("Das SourceDataLine-Objekt unterstützt das Control '" + pan.toString() + "' nicht");
		}

		sdl.open();
		sdl.start();

		byte[] buf = new byte[1];
		for (int i = 0; i < 1000 * (float) 44100 / 1000; i++) {
			double angle = i / ((float) 44100 / 440) * 2.0 * Math.PI;
			buf[0] = (byte) (Math.sin(angle) * 100);
			sdl.write(buf, 0, 1);
		}

		sdl.drain();
		sdl.close();
	}
}
