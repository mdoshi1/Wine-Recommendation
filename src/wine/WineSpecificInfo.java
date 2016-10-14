package wine;


public class WineSpecificInfo {

	private String name;
	private String wineProduced;
	
	public WineSpecificInfo() {
		name = "";
		wineProduced = "";
	}
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public String getWineProduced() { return wineProduced; }
	
	public void setWineProduced(String wineProduced) { this.wineProduced = wineProduced; }
}
