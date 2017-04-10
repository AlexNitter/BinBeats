package test.java.binBeats;


import javax.sound.sampled.LineUnavailableException;
import org.junit.Test;

import main.java.binBeats.lib.BinBeat;
import main.java.binBeats.lib.BinBeatsPlayer;

public class BinBeatsPlayerTest {

	@Test
	public void binBeatsPlayer_playStop_test() throws LineUnavailableException, InterruptedException {
		BinBeat binBeat = new BinBeat(440, 20, 80);
		
		BinBeatsPlayer player = new BinBeatsPlayer();
		player.setBinBeat(binBeat);
		player.play();
		Thread.sleep(2000);
		player.stop();
	}
}
