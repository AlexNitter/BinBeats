package test.java.binBeats;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.sound.sampled.LineUnavailableException;

import org.junit.Test;

import main.java.binBeats.lib.Channel;
import main.java.binBeats.lib.MonoFrequencyPlayer;

public class MonoFrequencyPlayerTest {

	@Test
	public void monoFrequencyPlayer_playStop_test() throws LineUnavailableException, InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		MonoFrequencyPlayer player = new MonoFrequencyPlayer();
		player.setFrequency(440);
		player.play();
		Thread.sleep(60000);
		player.stop();
	}
	
	@Test
	public void monoFrequencyPlayer_playForSeconds_test() throws LineUnavailableException, InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		MonoFrequencyPlayer player = new MonoFrequencyPlayer();
		player.setFrequency(400);
		player.setVolume(0f);
		player.playForSeconds(2);
	}
}
