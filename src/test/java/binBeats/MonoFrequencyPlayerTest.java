package test.java.binBeats;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.sound.sampled.LineUnavailableException;

import org.junit.Test;

import main.java.binBeats.lib.Channel;
import main.java.binBeats.lib.MonoFrequencyPlayer;

public class MonoFrequencyPlayerTest {

	@Test
	public void PlayStopTest() throws LineUnavailableException, InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		MonoFrequencyPlayer player = new MonoFrequencyPlayer();
		player.play(440);
		Thread.sleep(2000);
		player.stop();
	}
}
