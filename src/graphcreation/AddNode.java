package graphcreation;

import java.util.HashMap;
import java.util.Map;

import wine.WineInfo;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;


public class AddNode {
	
	private static GraphDatabaseService db = ConfigConnection.getDb();
	
	public static Node addWineNode(String wineName) {
		Node node = null;
				
		try (Transaction tx = db.beginTx()) {
			
			String query = "MERGE (wine:Wine { name: { Name } }) RETURN wine";
			Map<String, Object> parameters = new HashMap<>();
		    parameters.put("Name", wineName);
			
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
	
	public static Node addRegionNode(String wineRegion) {	
		Node node = null;
				
		try (Transaction tx = db.beginTx()) {
			
			String query = "MERGE (region:Region { name: { Name } }) RETURN region";
			Map<String, Object> parameters = new HashMap<>();
		    parameters.put("Name", wineRegion);
			
			Result results = db.execute(query, parameters);
			
			while (results.hasNext()) {
		         Map<String, Object> row = results.next();
		         for (String key : results.columns()) {
		             node = (Node)row.get(key);
		         }
		     }
	    
		    tx.success();
		} catch (Exception e) {
			System.out.println("There was an with the transaction: " + e);
			ConfigConnection.stopDatabase();
			System.exit(0);
		}
		return node;
	}
	
	public static Node addTypeNode(String wineType) {
		Node node = null;
				
		try (Transaction tx = db.beginTx()) {
			
			String query = "MERGE (wineType:WineType { name: { Name } }) RETURN wineType";
			Map<String, Object> parameters = new HashMap<>();
		    parameters.put("Name", wineType);
			
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
	
	public static Node addUserNode(String name) {
		Node node = null;
				
		try (Transaction tx = db.beginTx()) {
			
			String query = "CREATE (user:User { name: { Name } }) RETURN user";
			Map<String, Object> parameters = new HashMap<>();
		    parameters.put("Name", name);
			
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
