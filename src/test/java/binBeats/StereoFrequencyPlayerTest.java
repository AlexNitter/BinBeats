package test.java.binBeats;

import javax.sound.sampled.LineUnavailableException;

import org.junit.Test;

import main.java.binBeats.lib.Channel;
import main.java.binBeats.lib.MonoFrequencyPlayer;
import main.java.binBeats.lib.StereoFrequencyPlayer;

public class StereoFrequencyPlayerTest {

	@Test
	public void PlayStopTest() throws LineUnavailableException, InterruptedException {
		StereoFrequencyPlayer player = new StereoFrequencyPlayer(Channel.right);
		player.setFrequenz(440);
		player.play();
		Thread.sleep(1000);
		player.setFrequenz(450);
		Thread.sleep(1000);
		player.stop();
	}
}
