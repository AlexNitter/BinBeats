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

	private List<BinBeat> beatList= new ArrayList<BinBeat>();
	
	public Persistence(){
		
	}
	/**
	 * returns preset and user defined BinBeat settings
	 * @return an ArrayList of BinBeats 
	 * 
	 */
	public List <BinBeat> getBinBeats(){
		return beatList;		//TODO nur eine Kopie zurückgeben oder Methode löschen?
	}
	public void setBinBeats(List<BinBeat>beats){ 
		beatList=beats;		
	}
	private void initBeats(){		//TODO Hilfsmethode. Initialisiert das array, wenn kein xml-file gefunden wurde. 
		
		beatList.add(0, new BinBeat(220, 10.5f, "alpha"));		//TODO to be defined
		beatList.add(1, new BinBeat(220, 18.0f, "beta"));
		beatList.add(2, new BinBeat (220, 52.5f, "gamma"));
		this.serializeBeatListToXML();
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
	 * @param bn name of the BinBeat to be loaded
	 * @return the BinBeat with the given name. If not BinBeat with the given name exists, the return value is null
	 * */	
	public BinBeat loadBinBeat(String bn){
		int position=this.searchBeatName(bn);//TODO Was soll passieren, wenn kein passender String gefunden wird?
		if (position>=0){
			return beatList.get(position);		
		}
		return null;
	}
	
	/**
	 * stores a BinBeat and saves an updated textual XML representation of all stored BinBeats to an XML-file
	 * @param beat the BinBeat to be saved 
	 * @return true, if the BinBeat could be saved - false, if another BinBeat with the same name already exists and the Beat could not be saved 
	 * */
	public boolean saveBinBeat(BinBeat beat){ //TODO Worauf wurde der übergebene Beat bereits überprüft? 
					//[Bedingungen für String: kein Leerstring; !null, nicht mit Whitespacezeichen beginnend]
		String beatName = beat.getBeatName();
		//TODO validatePersistable BinBeat();
		beatName.trim();
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
	 */
	public boolean deleteBinBeat(String beatName){ 	//TODO Anzahl preset BinBeats festlegen 
		int position=this.searchBeatName(beatName);
		if (position >=3){
			beatList.remove(position);
			serializeBeatListToXML();
			return true;
		}
		return false;
	}
	
	private void serializeBeatListToXML(){ 
		
		XMLEncoder encoder=null;
		try{
			encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("beatSettings.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File beatSettings.xml");
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
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("beatSettings.xml")));
		}
		catch(FileNotFoundException e){
			System.out.println("Error: File beatSettings.xml not found");
			initBeats(); 	//TODO:Klären, ob sinnvoll
		}
		Persistence restoredBeatList = (Persistence)decoder.readObject();
		beatList=restoredBeatList.getBinBeats();
		/*System.out.println("\nDeserialisierung: ");
		String check = restoredBeatList.toString();
		System.out.print(check);*/
	}
	
}
