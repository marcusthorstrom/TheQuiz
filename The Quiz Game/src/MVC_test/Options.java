package MVC_test;



final public class Options {

	private boolean volume;
	private int gamerounds;
	
	public Options(){
		volume = true;
		gamerounds = 5;
	}
	public boolean getVolume(){
		return volume;
	}
	public int getGameRounds(){
		return gamerounds;
	}
	public void setVolume(boolean volume) {
		this.volume = volume;
	}
	public void setGameRounds(int value) {
		gamerounds = value;
	}
	public boolean resetVolume() {
		volume = true;
		return volume;
	}
	public int resetGamerounds() {
		gamerounds = 5;
		return gamerounds;
	}
}
