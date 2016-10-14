package dataextraction;

import java.util.ArrayList;

import wine.WineInfo;
import wine.WineSpecificInfo;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryException;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;


public class RequestService {

	// Determines speed of SPARQL query
	private static boolean instantRequest = true;
	
	// SPARQL queries
	private static final String queryInstant = " SELECT distinct ?Vins " +
		 	   								   " WHERE { " +
		 	   								   "  ?Vins <http://fr.dbpedia.org/property/vins> [] . " +
		 	   								   
		 	   								   " } " +
		 	   								   " LIMIT 10";
	private static final String queryLong = " PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
									  		" SELECT DISTINCT ?VinsEng " +
									  		" WHERE { " +
									  		"  ?VinsFr <http://dbpedia.org/ontology/abstract> ?com . " +
									  		"  ?VinsFr owl:sameAs ?VinsEng . " +
									  		"  FILTER regex(?com , 'est un vin', 'i') " +
									  		"  FILTER regex(?VinsEng , 'http://dbpedia.org/resource', 'i') " +
									  		" } ";
	
	// SPARQL endpoint
	private static final String sparqlEndpointFr = "http://fr.dbpedia.org/sparql";
	
	/* 
	 * Creates and executes a SPARQL query using dbpedia endpoints. Stores results in 
	 * an ArrayList of Strings representing the names of wines. 
	 */
	public static ArrayList<String> recoverWines() {

		// Empty list of Strings representing wines
		ArrayList<String> wineList = new ArrayList<String>();
		
		// Variables used to execute a SPARQL query
		String sparqlQueryString;
		Query query;
		long timeout = 0;
		
		// Sets sparqlQueryString according to request type (e.g. instant)
		if (instantRequest) 
			sparqlQueryString = queryInstant;
		else {
			sparqlQueryString = queryLong;
			timeout = 600000;
		}
		
		// Tries to parse sparqlQueryString to create a Query
		try {
			query = QueryFactory.create(sparqlQueryString);
		} catch (QueryException e) {
			System.out.println("Error when parsing sparql query.");
			System.exit(0);
			return wineList;
		}
		
		// Creates a QueryExecution to access a SPARQL endpoint over HTTP
		QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpointFr, query);
		if (timeout != 0) 
			qexec.setTimeout(timeout);
		
		// Executes a SELECT query
		ResultSet results = qexec.execSelect();
	
		// Adds all wines to an ArrayList
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution();			
			//System.out.println(solution.get("Vins"));
			wineList.add(solution.get("Vins").toString());
		}
	
		// Closes QueryExecution 
		qexec.close();
		
		return wineList;	
	}
	
	/* 
	 * Retrieves information about each wine from an ArrayList. Creates and executes
	 * a SPARQL query for each wine using dbpedia endpoints. Stores results in an
	 * ArrayList of WineInfo objects.
	 */
	public static ArrayList<WineInfo> organizeWineData(ArrayList<String> wineList) throws Exception {
	    
		// Empty list of wine information
		ArrayList<WineInfo> wineListInfo = new ArrayList<WineInfo>();
		
		int i = 0;
		Query query;
		
		// Loops through list of wine names
		while (i < wineList.size()) {	
			String sparqlQueryString = 
				    " PREFIX prop: <http://fr.dbpedia.org/property/> " +
				    " PREFIX ont: <http://fr.dbpedia.org/ontology/>" +
					" SELECT * WHERE { " +
					"  OPTIONAL { <" + wineList.get(i).toString() + "> prop:cépages ?Grapes . } " +
					"  OPTIONAL { <" + wineList.get(i).toString() + "> prop:régionMère ?RegionLink . } " +
					"  OPTIONAL { ?RegionLink prop:nom ?Region . }" +
					"  OPTIONAL { <" + wineList.get(i).toString() + "> prop:sousRégions ?SubRegion . } " +
					"  OPTIONAL { <" + wineList.get(i).toString() + "> prop:localisation ?Localization . } " +				
					"  OPTIONAL { <" + wineList.get(i).toString() + "> prop:nom ?Name . } " +
					"  OPTIONAL { <" + wineList.get(i).toString() + "> prop:vins ?Wines . } " +
					" } " +
					" LIMIT 1";
				
			// Tries to parse sparqlQueryString to create a Query
			try {
				query = QueryFactory.create(sparqlQueryString);
			} catch (QueryException e) {
				System.out.println("Error when parsing sparql query.");
				System.exit(0);
				return wineListInfo;
			}
				
			// Creates a QueryExecution to access a SPARQL endpoint over HTTP
			QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpointFr, query);
			
			// Executes a SELECT query
			ResultSet results = qexec.execSelect();	    
			      
			// Stores results in WineInfo objects and adds them to list of wine info
			WineSpecificInfo wineInfo = new WineSpecificInfo(); 
		    WineInfo wine = new WineInfo();
			while (results.hasNext()) {
			    	  
				QuerySolution solution = results.nextSolution();     	  	    	 
			    	  
			    if(solution.get("Grapes") != null) 
			    	wine.setGrapes(solution.get("Grapes").toString());
			    
			    // Some wines may have multiple regions (need to deal with this in
			    // further iterations)
			    if(solution.get("RegionLink") != null) {
			    	if(solution.get("Region") != null) 
			    		wine.setRegion(Tools.trimSuffix(solution.get("Region").toString()));
			    	else 
			    		wine.setRegion(Tools.trimSuffix(solution.get("RegionLink").toString()));
			    }
			    			   
			    if(solution.get("SubRegion") != null)
			    	wine.setSubRegion(solution.get("SubRegion").toString());
			    	  		    	  
			    if(solution.get("Localization") != null) 
			    	wine.setLocalization(solution.get("Localization").toString());			    	  	
			    
			    if(solution.get("Name") != null) 
			    	wineInfo.setName(Tools.trimSuffix(solution.get("Name").toString()));
			    		    
			    if(solution.get("Wines") != null) {
			    	String wines = Tools.trimSuffix(solution.get("Wines").toString());
			    	wines = Tools.sortWines(wines);
			    	wineInfo.setWineProduced(wines);
			    }    	 			    	  
			}	
			wine.setSpecificInfo(wineInfo);	    	  
		    wineListInfo.add(wine);			    
			i++;	
		}
		return wineListInfo;
	}
}
