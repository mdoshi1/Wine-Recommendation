package app;

import java.util.ArrayList;

import graphcreation.ConfigConnection;
import wine.WineInfo;


public class Main {

	public static void main(String[] args) throws Exception {
		
		// Wine data in CSV format for later import into Neo4j
		//dataextraction.ExtractData.main(null);
		
		// Wine data directly imported to Neo4j
		ArrayList<WineInfo> wineList = dataextraction.ExtractData.extractData();
		//graphcreation.CreateGraph.createGraph(wineList);
		//user.CreateUsers.createUsers();
		//ConfigConnection.stopDatabase();
	}
	
}
