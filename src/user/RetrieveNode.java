package user;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

import graphcreation.ConfigConnection;

public class RetrieveNode {
	
	private static GraphDatabaseService db = ConfigConnection.getDb();
	
	public static Node retrieveWineNode(String wineName) {
		Node node = null;
		
		try (Transaction tx = db.beginTx()) {
			
			String query = "MATCH (wine:Wine) WHERE wine.name = { wineName } return wine";
			Map<String, Object> parameters = new HashMap<>();
		    parameters.put("wineName", wineName);
			
			Result results = db.execute(query, parameters);
			
			while (results.hasNext()) {
		         Map<String, Object> row = results.next();
		         for (String key : results.columns()) {
		             node = (Node)row.get(key);
		         }
		     }
		    
		    tx.success();
		} catch (Exception e) {
			System.out.println("There was an error with the transaction: " + e);
			ConfigConnection.stopDatabase();
			System.exit(0);
		}
		return node;
	}
	
	public static Node retrieveWineTypeNode(String wineType) {
		Node node = null;
		
		try (Transaction tx = db.beginTx()) {
			
			String query = "MATCH (wineType:WineType) WHERE wineType.name = { wineType } return wineType";
			Map<String, Object> parameters = new HashMap<>();
		    parameters.put("wineType", wineType);
			
			Result results = db.execute(query, parameters);
			
			while (results.hasNext()) {
		         Map<String, Object> row = results.next();
		         for (String key : results.columns()) {
		             node = (Node)row.get(key);
		         }
		     }
		    
		    tx.success();
		} catch (Exception e) {
			System.out.println("There was an error with the transaction: " + e);
			ConfigConnection.stopDatabase();
			System.exit(0);
		}
		return node;
	}
}
