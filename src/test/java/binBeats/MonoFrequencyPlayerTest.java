package test.java.binBeats;

import javax.sound.sampled.LineUnavailableException;

import org.junit.Test;

import main.java.binBeats.lib.Channel;
import main.java.binBeats.lib.MonoFrequencyPlayer;

public class MonoFrequencyPlayerTest {

	@Test
	public void PlayStopTest() throws LineUnavailableException, InterruptedException {
		MonoFrequencyPlayer player = new MonoFrequencyPlayer();
		player.play(440);
		Thread.sleep(1000);
		player.stop();
		
		/*player.play(450);
		Thread.sleep(1000);
		player.stop();*/
		
		player.play(460);
		Thread.sleep(1000);
		player.stop();
	}
}
