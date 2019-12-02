package Game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.animation.PauseTransition;
import javafx.scene.canvas.GraphicsContext;

//Heavily influenced by GameStage class from GUI exercise
public class GameStage extends Stage{
	public static final float WINDOW_HEIGHT = 460.8f;
	public static final int WINDOW_WIDTH = 768;
	private Scene scene;
	private Scene mainscene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTest gametest;
	
	
	
	//the class constructor
	public GameStage(Stage stage,Scene menuScene) {
		this.stage = stage;
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);	
		this.mainscene = menuScene;
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);	
		this.gc = canvas.getGraphicsContext2D();
		//instantiate an animation timer
		this.gametest = new GameTest(this.gc,this.scene, this.stage, this.mainscene, this.root,this);
	}

	//method to add the stage elements
	public void setStage() {
		//set stage elements here	     
		this.root.getChildren().add(canvas);
		this.stage.setTitle("Whack-A-Mole!");
		this.stage.setScene(this.scene);
		
		//invoke the start method of the animation timer
		this.gametest.start();
//		setGameOver(this.condition);
		
		this.stage.show();
	}
	
    public Scene getScene(Scene scene) {
        return this.scene;
    }
    public Scene getMainScene() {
        return this.mainscene;
    }
    
    public Stage getStage() {
        return this.stage;
    }
	
	protected void setGameOver(Integer score){
		System.out.println("Marker");
		System.out.println(score);
		PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
		pause.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg) {
				// TODO Auto-generated method stub
				GameOverStage gameover = new GameOverStage(score);
				stage.setScene(gameover.getScene());
			}
		});
		pause.play();
	}
	
	
	
}

