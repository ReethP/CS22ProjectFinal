package Game;

import javafx.scene.image.Image;

public class SickMole extends Mole{
	public final static Image SICKMOLE_IMAGE = new Image("images/mole4.png",Mole.MOLE_WIDTH,Mole.MOLE_LENGTH,false,false);
	SickMole(Hole hole,int xcor, int ycor){
		super(hole,xcor,ycor);
		this.score = -2;
		this.loadImage(SICKMOLE_IMAGE);
	}
}
