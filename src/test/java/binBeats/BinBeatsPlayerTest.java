package test.java.binBeats;


import javax.sound.sampled.LineUnavailableException;
import org.junit.Test;
import main.java.binBeats.lib.BinBeatsPlayer;

public class BinBeatsPlayerTest {

	@Test
	public void PlayStopTest() throws LineUnavailableException, InterruptedException {
		BinBeatsPlayer player = new BinBeatsPlayer();
		player.setTraegerFrequenz(145);
		player.setDifferenzFrequenz(145);
		player.play();
		Thread.sleep(500);
		player.setDifferenzFrequenz(150);
		Thread.sleep(500);
		player.setDifferenzFrequenz(155);
		Thread.sleep(500);
		player.setDifferenzFrequenz(160);
		Thread.sleep(500);
		player.stop();
	}
}
