package Game;

import javafx.scene.image.Image;

public class BigMole extends Mole{
	public final static Image BIGMOLE_IMAGE = new Image("images/mole2.png",Mole.MOLE_WIDTH,Mole.MOLE_LENGTH,false,false);
	public final static int BIGMOLE_HITS = 3;
	BigMole(Hole hole,int xcor, int ycor){
		super(hole,xcor,ycor);
		this.score = 5;
		this.hit = BIGMOLE_HITS;
		this.loadImage(BIGMOLE_IMAGE);
	}
}