package Game;
import java.util.Random;


import javafx.scene.image.Image;

public class Hole extends Sprite{
	private Mole mole;
	private boolean hasMole;
	private final static int HOLE_WIDTH = 180;
	private final static int HOLE_LENGTH = 67;
	public final static Image HOLE_IMAGE = new Image("images/dirt.png",Hole.HOLE_WIDTH,Hole.HOLE_LENGTH,false,false);

	//Constructor
	public Hole(int xcor, int ycor){
		/*create a mole in the hole with the same coordinates
		with the hole and initialize hasmole to be false which
		means the mole is currently dead*/
		super(xcor,ycor);
		Mole mole = new Mole(this,xcor,ycor);
		this.mole = mole;
		this.hasMole = false;
		this.loadImage(HOLE_IMAGE);
	}
	

	protected void tryUnhideMole(){
		/*
			generate a random number from 1 to 100, if the number generated is
			greater than 50 generate a mole. Generate a a random number from
			0 to 1 and add 2. This will be the duration that mole will be alive
			adding 2 to the random number generated ensures the mole will be alive
			for at least three seconds. Mole implements runnable so created a thread
			with the arguments of the mole and start the mole. Set hasmole to true
		*/
		Random r = new Random();
		int create = r.nextInt(101);
		if(create<50){
			this.mole.unhide();
			this.hasMole = true;
			int duration = r.nextInt(2)+2;
			this.mole.setTime(duration);
			Thread moleth = new Thread(this.mole);
			moleth.start();
		}
	}
	
	protected boolean checkMole() {return this.hasMole;}
	protected Mole getMole() {return this.mole;}
	protected void hideMole(){this.hasMole = false;}
	protected boolean hasMole(){return this.hasMole;}
}









