package Game;

//import java.util.Random;
//import javafx.scene.input.MouseEvent;
//import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import javafx.util.Duration;

public class Mole extends Sprite implements Runnable{

	private boolean hidden;
	private int time;//amount of time the mole is alive
	private Hole hole;
	private final static int MOLE_WIDTH = 170;
	private final static int MOLE_LENGTH = 180;
	public final static Image MOLE_IMAGE = new Image("images/mole1.png",Mole.MOLE_WIDTH,Mole.MOLE_LENGTH,false,false);
	private static final Image IMAGE = new Image("images/spritesheetmole1.png");
	private static final int COLUMNS  =   11;
	private static final int COUNT    =  10;
	private static final int OFFSET_X =  300;
	private static final int OFFSET_Y =  400;
	private static final int WIDTH    = 2000;
	private static final int HEIGHT   = 2000;

	//Constructor
	public Mole(Hole hole,int xcor, int ycor){
		super((xcor+5),(ycor-130));
		this.hidden = true;
		this.time = 0;
		this.hole = hole;
		this.loadImage(MOLE_IMAGE);
	}
	
	//render image
	public void render1(GraphicsContext gc){
		gc.drawImage(this.img, this.x, this.y);
    }
	
	
	public void appear() {
		final ImageView imageView = new ImageView(IMAGE);
        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
        imageView.setX(x);
        imageView.setX(y);
        final Animation animation = new MoleFX(
                imageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
	}


	protected void trytohit(){
		if(!hidden){this.hidden = false;}
	}
	protected void unhide(){this.hidden = false;}
	protected void setTime(int time){this.time = time;}
	protected void hide(){}
	protected void adjustSpeed(){}
	protected void getHit(){}
	protected void addScore(){}
	protected boolean getHiddenStatus(){return this.hidden;}

	//Once mole is started decrement time by 1 every second
	//hide once time runs out
	public void run(){
		while(this.time>0){
			this.time = this.time-1;
			System.out.println(time);
			try{Thread.sleep(1000);}catch (InterruptedException e){}
		}
		this.hidden = true;
		this.hole.hideMole();
	}

}