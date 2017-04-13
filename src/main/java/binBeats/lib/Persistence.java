package main.java.binBeats.lib;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
	private final int NUMBER_OF_PRESETS=6;
	private final String [] NAME_OF_PRESETS = {"Falling Asleep","Trance","Deep Meditation","Creativity","Concentrated Learning","Problem Solving"};
	
	private List<BinBeat> beatList= new ArrayList<BinBeat>();
	
	public Persistence(){
	}
	
	/**
	 * returns preset and user defined BinBeat settings
	 * @return a copy of the ArrayList of BinBeats 
	 */
	public List <BinBeat> getBinBeats(){
		List<BinBeat> beatListCopy = this.beatList;
		return beatListCopy;		
	}
	
	/**
	 * Converts the beat list to an array.
	 * @return the beat list as array
	 */
	public BinBeat[] getBinBeatsArray() {
		BinBeat[] beatArray = new BinBeat[this.beatList.size()];
		beatArray = beatList.toArray(beatArray);
		return beatArray;
	}
	
	/**
	 * Sets the beat list and replaces all previously existing items.
	 * @param beatList an ArrayList of BinBeats
	 * */
	public void setBinBeats(List<BinBeat> beatList){
		this.beatList = beatList;
	}
	
	/**
	 * Sets the beat list and replaces all previously existing items.
	 * @param beatArray an Array of BinBeats
	 */
	public void setBinBeats(BinBeat[] beatArray) {
		this.beatList = new ArrayList<BinBeat>(Arrays.asList(beatArray));
	}
	
	public String []getNameOfPresets(){		
		return this.NAME_OF_PRESETS;
	}
	private void initBeats(){		//initializes the ArrayList, if no XML-file is found.
		
		beatList.add(0, new BinBeat( 432f, 2f, NAME_OF_PRESETS[0] ) );		
		beatList.add(1, new BinBeat( 432f, 4f, NAME_OF_PRESETS[1] ) );
		beatList.add(2, new BinBeat( 432f, 5f, NAME_OF_PRESETS[2] ) );
		beatList.add(3, new BinBeat( 432f, 6f, NAME_OF_PRESETS[3] ) );
		beatList.add(4, new BinBeat( 432f, 8f, NAME_OF_PRESETS[4] ) );
		beatList.add(5, new BinBeat( 432f,21f, NAME_OF_PRESETS[5] ) );
	}
	
	public String toString(){
		String beats="";
		for(BinBeat beat:getBinBeats()){
			beats += beat.getBeatName() + ", ";
		}
		return beats;
	}
	
	private int searchBeatName(String searchedBeatName){ 
		int position = -1;
		int length=beatList.size();
		for(int i=0; i<length; i++){
			BinBeat bb= beatList.get(i);
			String beatName=bb.getBeatName();
			if(beatName.equals(searchedBeatName)){ 
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
	 * @throws IllegalArgumentException if the given BinBeat is not valid	
	 * */
	public boolean saveBinBeat(BinBeat beat) throws FileNotFoundException, IllegalArgumentException {
		BinBeatValidator beatvalidator= new BinBeatValidator();
		ValidationResult result = beatvalidator.validate(beat,true);
		if (! result.isValid() ){
			throw new IllegalArgumentException("Invalid BinBeat");
		}
		String beatName = beat.getBeatName();
		beatName=beatName.trim();
		int position = this.searchBeatName(beatName);		 
		if (position < 0){					//if no BinBeat with the same name is found
			beatList.add(beat);
			serializeBeatListToXML();		//throws FileNotFoundException
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
		if (position >=NUMBER_OF_PRESETS){	 
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
			System.out.println("\nDeserialisierung: ");		//TODO: Sp�ter l�schen
			String check = restoredBeatList.toString();
			System.out.println(check); 
		}
		catch(FileNotFoundException e){
			initBeats(); 		
		}
	}
}
