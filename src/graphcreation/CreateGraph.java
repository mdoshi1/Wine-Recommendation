package graphcreation;

import java.util.ArrayList;

import wine.WineInfo;

import org.neo4j.graphdb.Node;


public class CreateGraph {
	
	public static void createGraph(ArrayList<WineInfo> wineList) {
		
		ConfigConnection.startDatabase();
		ConfigConnection.clearDatabase();
		
		for (int i = 0; i < wineList.size(); i++) {
			WineInfo tempWine = new WineInfo();
			tempWine = wineList.get(i);
			
			if (!tempWine.getSpecificInfo().getName().isEmpty()) {
				Node nodeWine = AddNode.addWineNode(tempWine.getSpecificInfo().getName());
				
				if (!tempWine.getRegion().isEmpty()) {
					Node nodeRegion = AddNode.addRegionNode(tempWine.getRegion());
					AddRelationship.wineToRegion(nodeWine, nodeRegion);
				}
				
				if (!tempWine.getSpecificInfo().getWineProduced().isEmpty()) {
					for (String wine : tempWine.getSpecificInfo().getWineProduced().split(",")) {
						wine = wine.trim();
						Node nodeType = AddNode.addTypeNode(wine);
						AddRelationship.wineToType(nodeWine, nodeType);
					}
				}
			}
		}
	}
}
