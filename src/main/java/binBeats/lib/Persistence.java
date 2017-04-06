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
 * This class is designed for storing BinBeats and archiving the graphs of JavaBean BinBeats. 
 * A textual XML representation of the properties of an ArrayList of JavaBean BinBeats can be 
 * written to a XML-file. The class can also be used to read XML representations of BinBeats 
 * and reconstruct an ArrayList of JavaBean BinBeats.
 * */ 

public class Persistence {

	
	private List<BinBeat> beatList= new ArrayList<BinBeat>();
	
	public Persistence(){
		
	}
	/**
	 * returns an ArrayList<BinBeat> of preset and user defined BinBeat settings
	 * */
	public List <BinBeat> getBinBeats(){
		return beatList;
	}
	public void setBinBeats(List<BinBeat>beats){ 
		beatList=beats;
	}
	private void initBeats(){		//TODO zur Zeit nur Hilfsmethode. Definieren, ob und in welchen Fällen init aufgerufen wird 
		
		beatList.add(new BinBeat(220, 10.5f, "alpha"));		//to be defined
		beatList.add(new BinBeat(220, 18.0f, "beta"));
		beatList.add(new BinBeat (220, 52.5f, "gamma"));
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
	 * searches for a BinBeat with a given beatname 
	 * returns the BinBeat with the given beatname
	 * */	
	public BinBeat loadBinBeat(String bn){
		int position=this.searchBeatName(bn);//TODO Was soll passieren, wenn kein passender String gefunden wird?
		return beatList.get(position);		//return was anderes, wenn Beat nicht gefunden wird
	}
	
	/**
	 * adds a BinBeat to the end of anArrayList and saves a textual XML representation to an XML-file
	 * */
	public boolean saveBinBeat(BinBeat b){ //TODO Worauf wurde der übergebene Beat bereits überprüft? 
					//[Bedingungen für String: kein Leerstring; !null, nicht mit Whitespacezeichen beginnend]
		String beatName = b.getBeatName();
		int position = this.searchBeatName(beatName);
		if (position >=3){		//TODO Anzahl preset BinBeats müssen noch definiert werden
			beatList.add(b);
			serializeBeatListToXML();
			return true;
		}
		return false;
	}
	
	/**
	 * searches for a BinBeat with the given beatname and deletes the BinBeat from the ArrayList 
	 * and from the XML-file
	 * @param bn
	 */
	public boolean deleteBinBeat(String bn){ 	//TODO Anzahl preset BinBeats festlegen 
		int position=this.searchBeatName(bn);
		if (position >=3){
			beatList.remove(position);
			serializeBeatListToXML();
			return true;
		}
		return false;
	}
	
	public void serializeBeatListToXML(){ 
		
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
	public void deserializeBeatListFromXML(){	//TODO auf private stellen? 
		XMLDecoder decoder=null;
		try{
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("beatSettings.xml")));
		}
		catch(FileNotFoundException e){
			System.out.println("Error: File beatSettings.xml not found");
		}
		Persistence restoredBeatList = (Persistence)decoder.readObject();
		beatList=restoredBeatList.getBinBeats();
		/*System.out.println("\nDeserialisierung: ");
		String check = restoredBeatList.toString();
		System.out.print(check);*/
	}
	
	
	
}
