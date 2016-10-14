package graphcreation;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;

public class AddRelationship {
	
	private static GraphDatabaseService db = ConfigConnection.getDb();
	
	private static enum RelTypes implements RelationshipType {
		PRODUCED_IN_REGION,
		WINE_TYPE,
		LIKES_WINE,
		LIKES_WINE_TYPE
	}
	
	public static void wineToRegion(Node nodeWine, Node nodeRegion) {
		try (Transaction tx = db.beginTx()) {
			nodeWine.createRelationshipTo(nodeRegion, RelTypes.PRODUCED_IN_REGION);
			tx.success();
		}
		catch(Exception e) {
			System.out.println("There was an error with the transaction: " + e );
			ConfigConnection.stopDatabase();
			System.exit(0);
		}
	}
	
	public static void wineToType(Node nodeWine, Node nodeType) {
		try (Transaction tx = db.beginTx()) {
			nodeWine.createRelationshipTo(nodeType, RelTypes.WINE_TYPE);
			tx.success();
		}
		catch(Exception e) {
			System.out.println("There was an error with the transaction: " + e );
			ConfigConnection.stopDatabase();
			System.exit(0);
		}
	}

	public static void userToWine(Node nodeUser, Node nodeWine) {
		try (Transaction tx = db.beginTx()) {
			nodeUser.createRelationshipTo(nodeWine, RelTypes.LIKES_WINE);
			tx.success();
		}
		catch(Exception e) {
			System.out.println("There was an error with the transaction: " + e );
			ConfigConnection.stopDatabase();
			System.exit(0);
		}
	}
	
	public static void userToWineType(Node nodeUser, Node nodeWineType) {
		try (Transaction tx = db.beginTx()) {
			nodeUser.createRelationshipTo(nodeWineType, RelTypes.LIKES_WINE_TYPE);
			tx.success();
		}
		catch(Exception e) {
			System.out.println("There was an error with the transaction: " + e );
			ConfigConnection.stopDatabase();
			System.exit(0);
		}
	}
}
