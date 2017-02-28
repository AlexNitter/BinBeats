package test.java.binBeats;


import javax.sound.sampled.LineUnavailableException;
import org.junit.Test;
import main.java.binBeats.lib.BinBeatsPlayer;

public class BinBeatsPlayerTest {

	@Test
	public void PlayStopTest() throws LineUnavailableException, InterruptedException {
		BinBeatsPlayer player = new BinBeatsPlayer();
		player.play(145, 150);
		Thread.sleep(5000);
		player.stop();
	}
}
