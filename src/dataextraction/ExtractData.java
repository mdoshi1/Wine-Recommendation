package dataextraction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import wine.WineInfo;


public class ExtractData {
	
	public static void main(String[] args) throws Exception {
		ArrayList<WineInfo> wineList = new ArrayList<WineInfo>();
		wineList = RequestService.organizeWineData(RequestService.recoverWines());
		generateCSV(wineList);
	}
	
	public static ArrayList<WineInfo> extractData() throws Exception {
		ArrayList<WineInfo> wineList = new ArrayList<WineInfo>();
		wineList = RequestService.organizeWineData(RequestService.recoverWines());
		return wineList;
	}
	
	/*
	 * Generates a CSV file with wine data to be imported into Neo4j
	 */
	private static void generateCSV(ArrayList<WineInfo> wineList) {
		try {
		    FileWriter writer = new FileWriter("/Users/MC_Doshi/Documents/test3.csv");
			 
		    // Appends the appropriate titles		    
		    writer.append("id");
		    writer.append(',');
		    
		    writer.append("Name");
		    writer.append(',');
		    writer.append("Region");
		    writer.append(',');
		    writer.append("Wines");	    
		    writer.append('\n');

		    // Loops through list of WineInfo objects and appends all wine data
		    int id = 1;	    
		    for (WineInfo info : wineList) {
		    	
		    	writer.append("" + id);
		    	id++;
		    	writer.append(',');
		    	
		    	writer.append(info.getSpecificInfo().getName());
		    	writer.append(',');
		    	writer.append(info.getRegion());
		    	writer.append(',');
		    	writer.append(info.getSpecificInfo().getWineProduced());	    	
		    	writer.append('\n');
		    	
		    }
	
		    // Flushes and closes FileWriter
		    writer.flush();
		    writer.close();
		    
		// Catches IOExceptions from creating/writing to a file
		} catch(IOException e) {
		     e.printStackTrace();
		} 
	}

}
