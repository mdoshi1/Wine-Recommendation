package dataextraction;

public class Tools {
	
	/*
	 * Trims the suffix "@fr" from a given String if the String contains it
	 */
	public static String trimSuffix(String word) {
		
		// Checks if word contains the suffix "@fr" and trims if true
		if (word.contains("@fr")) {
			int suffixIndex = word.indexOf('@');
			word = word.substring(0, suffixIndex);
		}
		return word;
	}
	
	/*
	 * Sorts a given String of wine(s) into a general, comma-separated String of
	 * wine(s)
	 * Only the three main wine colors are used (rouge, blanc, and rosé)
	 * Further iterations can factor in more wine colors and details
	 */
	public static String sortWines(String wineString) {
		String wines = "";
		
		// If wineString is actually an integer, there are no wines to sort
		if (wineString.contains("//"))
			return wines;
		
		wineString = wineString.toLowerCase();
		
		if (wineString.contains("rouge")) 
			wines = wines + "rouge,";
		
		if (wineString.contains("blanc")) 
			wines = wines + "blanc,";
		
		if (wineString.contains("rosé")) 
			wines = wines + "rosé,";
		
		return wines;
	}
}
