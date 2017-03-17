package test.java.binBeats;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.sound.sampled.LineUnavailableException;

import org.junit.Test;

import main.java.binBeats.lib.Channel;
import main.java.binBeats.lib.MonoFrequencyPlayer;

public class MonoFrequencyPlayerTest {

	@Test
	public void LinearTest() throws LineUnavailableException, InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		MonoFrequencyPlayer player = new MonoFrequencyPlayer();
		player.setFrequenz(440);
		player.play();
		Thread.sleep(2000);
		player.stop();
	}
}
