package test.java.binBeats;


import javax.sound.sampled.LineUnavailableException;
import org.junit.Test;
import main.java.binBeats.lib.BinBeatsPlayer;

public class BinBeatsPlayerTest {

	@Test
	public void LinearBinBeatsTest() throws LineUnavailableException, InterruptedException {
		BinBeatsPlayer player = new BinBeatsPlayer();
		player.setTraegerFrequenz(440);
		player.setDifferenzFrequenz(460);
		player.play();
		Thread.sleep(300000);
		player.stop();
	}
	
	@Test
	public void GradientBinBeatsTest() throws LineUnavailableException, InterruptedException {
		int traegerFrequenz = 440;
		int differenzFrquenz = 440;
		
		BinBeatsPlayer player = new BinBeatsPlayer();
		player.setTraegerFrequenz(traegerFrequenz);
		player.setDifferenzFrequenz(differenzFrquenz);
		player.play();
		
		while(differenzFrquenz <= traegerFrequenz + 30) {
			Thread.sleep(2000);
			differenzFrquenz++;
			player.setDifferenzFrequenz(differenzFrquenz);
		}
		
		player.stop();
	}
}
