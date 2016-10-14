package user;

public class UserInfo {
	private String name;
	private String[] wineLikes;
	private String[] wineTypeLikes;
	
	public UserInfo(String name, String[] wineLikes, String[] wineTypeLikes) {
		this.name = name;
		this.wineLikes = wineLikes;
		this.wineTypeLikes = wineTypeLikes;
	}
	
	public String getName() { return name; }
	
	public String[] getWineLikes() { return wineLikes; }
	
	public String[] getWineTypeLikes() { return wineTypeLikes; }
}
