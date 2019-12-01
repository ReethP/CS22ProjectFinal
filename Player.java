package Game;
public class Player{
	private String name;
	private int highscore;

	public Player(String name){
		this.name = name;
		this.highscore = 0;}
	public String getName(){return this.name;}
	public int getHighScore(){return this.highscore;}
}