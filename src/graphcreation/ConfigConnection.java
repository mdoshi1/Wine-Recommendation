package graphcreation;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

//import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;


public class ConfigConnection {

	private static final String DB_PATH = "/Users/MC_Doshi/Documents/Neo4j/winetest.graphdb" ; 
	private static GraphDatabaseService db;
	
	public static void startDatabase() {
		File dbFile = new File(DB_PATH);
		db = new GraphDatabaseFactory().newEmbeddedDatabase(dbFile);
	}
	
	public static void clearDatabase() {
		try (Transaction tx = db.beginTx()) {
			
			String query = " MATCH (n) DETACH " +
						   " DELETE n ";		
			db.execute(query);
		    tx.success();
		} catch (Exception e) {
			System.out.println("There was an error with the transaction: " + e);
			ConfigConnection.stopDatabase();
			System.exit(0);
		}
	}
	
	public static GraphDatabaseService getDb() {
		return db;
	}
	
	public String db_path() {
		return DB_PATH;
	}
	
	/*public static ExecutionEngine getEngine() {
		return engine;
	}*/
	
	public static void stopDatabase() {
		registerShutdownHook(db);
	}
	
	private static void registerShutdownHook(final GraphDatabaseService graphDb) {
	    Runtime.getRuntime().addShutdownHook(new Thread() {
	        @Override
	        public void run() {
	            graphDb.shutdown();
	        }
	    });
	}
}