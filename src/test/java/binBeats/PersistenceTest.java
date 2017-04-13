package test.java.binBeats;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import main.java.binBeats.lib.Persistence;
import main.java.binBeats.lib.BinBeat;


public class PersistenceTest {

	@Test
	public void loadBeatTest() {
		Persistence persistableBeats = new Persistence();
		persistableBeats.deserializeBeatListFromXML();
		BinBeat binbeat = persistableBeats.loadBinBeat("Falling Asleep");
		String result = binbeat.getBeatName();
		assertEquals(result, "Falling Asleep");
	}
	

	@Test
	public void saveBeatTest()	
	{	
		Persistence persistableBeats = new Persistence();
		persistableBeats.deserializeBeatListFromXML();
		boolean result= true;
		try {
			result = persistableBeats.saveBinBeat( new BinBeat(432f, 2f, "Falling Asleep") );
		} catch (FileNotFoundException e) {
		}
		
		assertEquals(result, false);
	}
	@Test
	public void deleteBeatTest1() {
		
		Persistence persistableBeats = new Persistence();
		persistableBeats.deserializeBeatListFromXML();
		boolean result=true;
		try {
			result = persistableBeats.deleteBinBeat("Falling Asleep");
		} catch (FileNotFoundException e) {	
		}
	
		assertEquals(result, false);
	}
	@Test
	public void deleteBeatTest2() {
		
		Persistence persistableBeats = new Persistence();
		persistableBeats.deserializeBeatListFromXML();
		boolean result=true;
		try {
			result = persistableBeats.deleteBinBeat("grumblfx");
		} catch (FileNotFoundException e) {	
		}
	
		assertEquals(result, false);
	}
}
