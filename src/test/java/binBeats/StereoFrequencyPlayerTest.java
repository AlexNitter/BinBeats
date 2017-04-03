package test.java.binBeats;

import javax.sound.sampled.LineUnavailableException;

import org.junit.Test;

import main.java.binBeats.lib.Channel;
import main.java.binBeats.lib.MonoFrequencyPlayer;
import main.java.binBeats.lib.StereoFrequencyPlayer;

public class StereoFrequencyPlayerTest {

	@Test
	public void stereoFrequencyPlayer_playStop_test() throws LineUnavailableException, InterruptedException {
		StereoFrequencyPlayer player = new StereoFrequencyPlayer(Channel.right);
		player.setFrequency(460);
		player.play();
		Thread.sleep(60000);
		player.stop();
	}
	
	@Test
	public void stereoFrequencyPlayer_playForSeconds_test() throws LineUnavailableException, InterruptedException {
		StereoFrequencyPlayer player = new StereoFrequencyPlayer(Channel.right);
		player.setFrequency(440);
		player.playForSeconds(2);
	}
}
