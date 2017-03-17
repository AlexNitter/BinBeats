package test.java.binBeats;

import javax.sound.sampled.LineUnavailableException;

import org.junit.Test;

import main.java.binBeats.lib.Channel;
import main.java.binBeats.lib.MonoFrequencyPlayer;
import main.java.binBeats.lib.StereoFrequencyPlayer;

public class StereoFrequencyPlayerTest {

	@Test
	public void LinearTest() throws LineUnavailableException, InterruptedException {
		StereoFrequencyPlayer player = new StereoFrequencyPlayer(Channel.right);
		player.setFrequenz(440);
		player.play();
		Thread.sleep(300000);
		player.stop();
	}
}
