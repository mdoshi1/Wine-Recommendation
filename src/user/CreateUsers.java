package user;

import graphcreation.AddNode;
import graphcreation.AddRelationship;

import org.neo4j.graphdb.Node;

public class CreateUsers {
	
	public static void createUsers() {
		
		UserInfo[] users = generateSampleUsers();
		for (UserInfo user : users) {
			Node nodeUser = AddNode.addUserNode(user.getName());
			for (String wineName : user.getWineLikes()) {
				Node nodeWine = RetrieveNode.retrieveWineNode(wineName);
				AddRelationship.userToWine(nodeUser, nodeWine);
			}			
			for (String wineType : user.getWineTypeLikes()) {
				Node nodeWineType = RetrieveNode.retrieveWineTypeNode(wineType);
				AddRelationship.userToWineType(nodeUser, nodeWineType);
			}
		}
 	}
	
	private static UserInfo[] generateSampleUsers() {
		String user1 = "Michael";
		String user2 = "Jasmine";
		String user3 = "Karina";
		String user4 = "Jissella";
		String user5 = "David";
		
		String[] wineLikes1 = {"Côtes-de-gascogne", "Bandol AOC", "Frankstein"};
		String[] wineLikes2 = {"Côtes-de-gascogne", "Vistrenque"};
		String[] wineLikes3 = {"Bandol AOC", "Vin de sable"};
		String[] wineLikes4 = {"Sartène"};
		String[] wineLikes5 = {"Frankstein", "Pouilly-Fumé"};
		
		String[] wineTypeLikes1 = {"rouge", "blanc"};
		String[] wineTypeLikes2 = {"rouge"};
		String[] wineTypeLikes3 = {"rouge", "rosé"};
		String[] wineTypeLikes4 = {"blanc"};
		String[] wineTypeLikes5 = {"rosé", "blanc"};
		
		UserInfo userInfo1 = new UserInfo(user1, wineLikes1, wineTypeLikes1);
		UserInfo userInfo2 = new UserInfo(user2, wineLikes2, wineTypeLikes2);
		UserInfo userInfo3 = new UserInfo(user3, wineLikes3, wineTypeLikes3);
		UserInfo userInfo4 = new UserInfo(user4, wineLikes4, wineTypeLikes4);
		UserInfo userInfo5 = new UserInfo(user5, wineLikes5, wineTypeLikes5);
		
		UserInfo[] users = {userInfo1, userInfo2, userInfo3, userInfo4, userInfo5};
		
		return users;
	}
}
