package MVC_test;



final public class Options {

	private int volume;
	private int gamerounds;
	
	public Options(){
		volume = 50;
		gamerounds = 5;
	}
	public int getVolume(){
		return volume;
	}
	public int getGameRounds(){
		return gamerounds;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public void setGameRounds(int value) {
		gamerounds = value;
	}
	public void resetValue() {
		volume = 50;
		gamerounds = 5;
	}
}
