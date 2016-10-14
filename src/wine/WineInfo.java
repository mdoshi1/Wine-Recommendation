package wine;


public class WineInfo {
	
	private WineSpecificInfo specificInfo;
	private String region;
	private String localization;
	private String subRegion;
	private String grapes;
	
	public WineInfo() {
		specificInfo = new WineSpecificInfo();
		region = "";
		localization = "";
		subRegion = "";
		grapes = "";
	}
	
	public WineSpecificInfo getSpecificInfo() { return specificInfo; }
	
	public void setSpecificInfo(WineSpecificInfo specificInfo) { this.specificInfo = specificInfo; }
	
	public String getRegion() { return region; }
	
	public void setRegion(String region) { this.region = region; }
	
	public String getLocalization() { return localization; }
	
	public void setLocalization(String localization) { this.localization = localization; }
	
	public String getSubRegion() { return subRegion; }
	
	public void setSubRegion(String subRegion) { this.subRegion = subRegion; }
	
	public String getGrapes() { return grapes; }
	
	public void setGrapes(String grapes) { this.grapes = grapes; }
}


