package main.java.binBeats.lib;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed for storing BinBeats and archiving the graphs of BinBeats. 
 * It stores BinBeats in an ArrayList. A textual XML representation of the properties of JavaBean BinBeats can be 
 * written to an XML-file. The class can also be used to read XML representations of BinBeats 
 * and reconstruct an ArrayList of previously saved JavaBean BinBeats.
 * @author Lund
 * @version 1.0
 * */ 

public class Persistence {

	private final String FILENAME = "beatSettings.xml";
	private List<BinBeat> beatList= new ArrayList<BinBeat>();
	
	public Persistence(){
	}
	/**
	 * returns preset and user defined BinBeat settings
	 * @return an ArrayList of BinBeats 
	 */
	public List <BinBeat> getBinBeats(){
		return beatList;		
	}
	public void setBinBeats(List<BinBeat>beats){ 
		beatList=beats;		
	}
	private void initBeats(){		//Hilfsmethode. Initialisiert die Array-List, wenn kein xml-file gefunden wurde. 
		
		beatList.add(0, new BinBeat(432f, 2f, "Falling Asleep"));		//TODO to be defined
		beatList.add(1, new BinBeat(432f, 4f, "Trance"));
		beatList.add(2, new BinBeat (432f, 5f, "Deep Medition"));
		beatList.add(3, new BinBeat (432f, 6f, "Creativity"));
		beatList.add(4, new BinBeat (432f, 8f,"Concenrated Learning"));
		beatList.add(5, new BinBeat(432f, 21f, "Problem Solving"));
	}
	
	public String toString(){ //TODO löschen, nach erfolgreichen Tests
		String beats="";
		for(BinBeat beat:getBinBeats()){
			beats += beat.getBeatName() + ", ";
		}
		return beats;
	}
	
	private int searchBeatName(String bn){ 
		int position = -1;
		int length=beatList.size();
		for(int i=0; i<length; i++){
			BinBeat bb= beatList.get(i);
			String beatName=bb.getBeatName();
			if(beatName.equals(bn)){ 
				position=i;			 
				return position;
			}
		}
		return position;
		
	}
	/** 
	 * loads a BinBeat
	 * @param beatName name of the BinBeat to be loaded
	 * @return the BinBeat with the given name. If not BinBeat with the given name exists, the return value is null
	 * */	
	public BinBeat loadBinBeat(String beatName){
		beatName=beatName.trim();
		int position=this.searchBeatName(beatName);
		if (position>=0){
			return beatList.get(position);		
		}
		return null;
	}
	
	/**
	 * stores a BinBeat and saves an updated textual XML representation of all stored BinBeats to an XML-file
	 * @param beat the BinBeat to be saved 
	 * @return true, if the BinBeat could be saved - false, if another BinBeat with the same name already exists and the Beat could not be saved 
	 * @throws FileNotFoundException if an XML-file does not exists and cannot be created or written into
	 * */
	public boolean saveBinBeat(BinBeat beat) throws FileNotFoundException {
		BinBeatValidator beatvalidator= new BinBeatValidator();
			//TODO soll der BeatName hier oder im UI geprüft werden?
		ValidationResult result = beatvalidator.validate(beat,true);
		if (! result.isValid() ){
			//TODO throw InvalidArgument o.ä
			return false; 
		}
		String beatName = beat.getBeatName();
		beatName=beatName.trim();
		int position = this.searchBeatName(beatName);		 
		if (position < 0){		//TODO Anzahl preset BinBeats müssen noch definiert werden
			beatList.add(beat);
			serializeBeatListToXML();
			return true;
		}
		return false;
	}
	
	/**
	 * deletes a BinBeat  
	 * @param beatName name of the BinBeat to be deleted
	 * @return true, if the BinBeat could be deleted - false, if no user defined BinBeat with the given name exists 
	 * @throws FileNotFoundException if an XML-file does not exists and cannot be created or written into
	 */
	public boolean deleteBinBeat(String beatName) throws FileNotFoundException{ 	
		beatName=beatName.trim();
		int position=this.searchBeatName(beatName);
		if (position >=6){				//TODO Anzahl preset BinBeats festlegen 
			beatList.remove(position);
			serializeBeatListToXML();
			return true;
		}
		return false;
	}
	
	private void serializeBeatListToXML() throws FileNotFoundException{ 
		
		XMLEncoder encoder=null;
		try{
			encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(FILENAME)));
		}catch(FileNotFoundException fileNotFound){
			throw new FileNotFoundException("file does not exists and cannot be created or written into");
		}
		encoder.writeObject(this);
		encoder.close();
	}
	
	/**
	 * reads XML representations of BinBeats and reconstructs an ArrayList<BinBeat> of JavaBean 
	 * BinBeat-presettings and user defined BinBeat settings.
	 * */
	public void deserializeBeatListFromXML(){
		XMLDecoder decoder=null;
		try{
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(FILENAME))); 
			Persistence restoredBeatList = (Persistence)decoder.readObject();
			decoder.close();
			this.beatList = restoredBeatList.beatList;	
			System.out.println("\nDeserialisierung: ");		//TODO: Später löschen
			String check = restoredBeatList.toString();
			System.out.print(check); 
		}
		catch(FileNotFoundException e){
			initBeats(); 		
		}
	}
}
