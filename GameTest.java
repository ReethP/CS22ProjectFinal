package Game;

import java.io.File;
import java.util.Timer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.TimerTask;
import java.util.ArrayList;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import java.util.concurrent.TimeUnit;
import javafx.scene.media.MediaPlayer;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;



public class GameTest extends AnimationTimer{
	private Stage stage;
	private Scene menuScene;
	private long start;
	private Scene scene;
	private ArrayList <Hole> holes;
	private GraphicsContext gc;
	public static final float WINDOW_HEIGHT = 460.8f;
	public static final int WINDOW_WIDTH = 768;
	private int spawned;
	public final Image bg = new Image("images/background.jpg",768, 460.8,false,false);
	public final Image timer = new Image("images/timer.png",120, 50,false,false);
	public final Image scoreboard = new Image("images/score.png",200, 70,false,false);
	public final Image flag1 = new Image("images/exit1.png",90, 90,false,false);
	public static Integer counter = 120;
	//public final Media molehit;
	//public final MediaPlayer mediaPlayer;
	private Text time;
	private Group root;
	private Integer myscore;
	private Text text;
	String min;
	String sec;

    //Constructor
    public GameTest(GraphicsContext gc, Scene scene, Stage gamestage,Scene scene1, Group root){
    	this.root =  root;
    	this.stage = gamestage;
    	this.scene = scene1;
    	this.menuScene = scene;
    	this.start = System.nanoTime();
        this.gc = gc;
        this.spawned = 0;
        this.myscore = 0;
        this.holes = new ArrayList<Hole>();
        makeHoles(this.holes);
    	//String musicFile = "soundfiles/HitSFX.mp3";
    	//this.molehit = new Media(new File(musicFile).toURI().toString());
    	//this.mediaPlayer = new MediaPlayer(molehit);
    	this.handleMouseEvent(this.holes);
        timer();
    }
    
    public void handle(long currentNanoTime) {
        //draw background, timer, scoreboard
        //and exit button which is a red flag
    	this.gc.drawImage(this.bg,0,0);
        this.gc.drawImage(this.timer,10,55);
        this.gc.drawImage(this.scoreboard,10,0);
        this.gc.drawImage(this.flag1,680,0);
        
        root.getChildren().remove(text);
        String score = " "+ this.myscore;
		text = new Text(160 ,55 , score);
		text.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/images/boston caps.ttf"), 60));
		
		text.setFill(Color.WHITE);
		root.getChildren().add(text);
		
		root.getChildren().remove(time);
        String mytime = this.min +":"+ this.sec;
		time = new Text(120 ,95 , mytime);
		time.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/images/boston caps.ttf"), 35));
		
		time.setFill(Color.WHITE);
		root.getChildren().add(time);
        
        /*
        Try to spawn moles every 2 seconds using startsec and currentsec
        spawned is necessary as due to the nature of the current implemention
        it will enter into inner if statement multiple times. Spawned will
        ensure that it will only enter the inner if statement once. If spawned
        is 0 it will enter the inner if statement and try to spawn moles.
        Spawned will be equal to 1. Won't enter the innerloop since spawned is
        not equal to zero. Once currentsec and startsec%2 is no longer equal to
        zero it will enter the outer else statement which equates spawned to 0
        allowing the program to try and spawn moles again next time it
        enter the outer if statement
        */
        long currentSec = TimeUnit.NANOSECONDS.toSeconds(currentNanoTime);
        long startSec = TimeUnit.NANOSECONDS.toSeconds(this.start);
        if((currentSec - startSec)%2 == 0){
            if(this.spawned == 0){
            System.out.println(currentSec);
            System.out.println(startSec);
            for(Hole hole:this.holes){
                if(!hole.hasMole()) {
                	hole.tryUnhideMole(this.counter);
                }
            }
            this.spawned = 1;}
            }
            else{
                this.spawned = 0;
                System.out.println(currentSec);
                System.out.println(startSec);
            }

        //Render moles
        for(Hole hole:this.holes){
            if(hole.checkMole()) hole.getMole().render(this.gc);
        }


        //Render holes
        for(Hole hole:this.holes){
        	hole.render(this.gc);
        }
    }
    
    //Initialize holes and add holes to hole array
    private void makeHoles(ArrayList<Hole> holes) {
    	Hole hole1,hole2,hole3,hole4,hole5,hole6,hole7,hole8,hole9;
    	hole1 = new Hole(100,140);
    	hole2 = new Hole(480,140);
    	hole3 = new Hole(10,248);
    	hole4 = new Hole(200,248);
    	hole5 = new Hole(380,248);
    	hole6 = new Hole(560,248);
    	hole7 = new Hole(100,370);
    	hole8 = new Hole(300,370);
    	hole9 = new Hole(500,370);
    	holes.add(hole1);
    	holes.add(hole2);
    	holes.add(hole3);
    	holes.add(hole4);
    	holes.add(hole5);
    	holes.add(hole6);
    	holes.add(hole7);
    	holes.add(hole8);
    	holes.add(hole9);
    }
    
    public void timer() {
        Timer timer = new Timer();//create a new Timer
        timer.scheduleAtFixedRate(new TimerTask(){
        	Integer mm;
        	Integer ss;
            public void run() {
                counter--;//increments the counter
                
                ss = (((counter % 86400) % 3600) % 60);
                sec = ss.toString();
                if(ss<10) sec = "0"+ss.toString();
                
                mm = ((counter %  86400) % 3600) / 60;
                min = mm.toString();
                if(counter==0) {
                	timer.cancel();
                }
            }
        }, 1000, 1000);//this line starts the timer at the same time its executed
    }
    
    //Handle mouse event for holes and moles
    private void handleMouseEvent(ArrayList<Hole> holes){
		this.menuScene.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				if(new Rectangle2D(680, 0, 70, 100).contains(e.getX(),e.getY())) {
					System.out.println("lol");
					stage.setScene(scene);
				}
				for(Hole hole:holes){
					if(hole.checkMole() && hole.getMole().getBounds().contains(e.getX(),e.getY())){
						System.out.println("Mole clicked!");
						hole.getMole().reduceHits();
						if(hole.getMole().getHits() <= 0){
							hole.hideMole();
							myscore+=hole.getMole().addScore();}
						//add score if mole is clicked. Hide the mole
					}
				}
			}
		});
    }

    

}