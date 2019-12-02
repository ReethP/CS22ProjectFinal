package Game;
import java.util.Random;


import javafx.scene.image.Image;

public class Hole extends Sprite{
	private Mole mole;
	private BigMole bigmole;
	private Mole normalmole;
	private SickMole sickmole;
	private boolean hasMole;
	private final static int HOLE_WIDTH = 180;
	private final static int HOLE_LENGTH = 67;
	public final static Image HOLE_IMAGE = new Image("images/dirt.png",Hole.HOLE_WIDTH,Hole.HOLE_LENGTH,false,false);
	public int xcor;
	public int ycor;
	//Constructor
	public Hole(int xcor, int ycor){
		/*create a mole in the hole with the same coordinates
		with the hole and initialize hasmole to be false which
		means the mole is currently dead*/
		super(xcor,ycor);
		this.hasMole = false;
		this.loadImage(HOLE_IMAGE);
		this.xcor = xcor;
		this.ycor = ycor;
		BigMole bigmole = new BigMole(this,this.xcor,this.ycor);
		this.bigmole = bigmole;
		Mole normalmole = new Mole(this,this.xcor,this.ycor);
		this.normalmole = normalmole;
		SickMole sickmole = new SickMole(this,this.xcor,this.ycor);
		this.sickmole = sickmole;
	}
	
	
	

	protected void tryUnhideMole(int currentTime){
		/*
			generate a random number from 1 to 100, if the number generated is
			greater than 50 generate a mole. Generate a a random number from
			0 to 1 and add 2. This will be the duration that mole will be alive
			adding 2 to the random number generated ensures the mole will be alive
			for at least three seconds. Mole implements runnable so created a thread
			with the arguments of the mole and start the mole. Set hasmole to true.
			
			There are also three types of moles. Normal moles, Sick moles, and Gangsta Moles
			If the program will spawn a mole, it will generated another number from 0 to 100
			For the type. If the number is less than or equal to 24 it will spawn a big mole
			Given that the time is less than or equal to 90. This means big moles will only
			spawn after 30 seconds. The same concept is applied to sick moles but after
			1 minute and types are different while the remaining 50 percent chance to spawn
			is reserved for normal moles.
		*/
		Random r = new Random();
		int create = r.nextInt(101);
		int type = r.nextInt(101);
		if(create<50){
			int duration = 0;
			if(type <= 24 && currentTime <= 90) {
				this.mole = this.bigmole;
				duration = r.nextInt(2)+3;
			}else if(type >=25 && type <=49 && currentTime <= 60) {
				this.mole = this.sickmole;
				duration = r.nextInt(2)+2;
			}
			else{
				this.mole = this.normalmole;
				duration = r.nextInt(2)+2;
			}
			this.mole.unhide();
			this.hasMole = true;
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









