package Game;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class GameOverStage {
	private StackPane pane;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;
	private Integer score;
	private Text text;
	public final Image bg = new Image("images/background.jpg",768, 460.8,false,false);
	private ArrayList<Integer> highscores;
	
	GameOverStage(Integer score,ArrayList<String> highscores){
		this.highscores = new ArrayList <Integer>(3);
		this.pane = new StackPane();
		this.scene = new Scene(pane, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.score = score;
		for(String i:highscores) {
			this.highscores.add(Integer.valueOf(i));
		}
		for(int i = 0;i<3;i++) {
			if(score>this.highscores.get(i)){
				this.highscores.remove(i);
				this.highscores.add(i,score);
				break;
			}
		}
		this.setProperties();
		
		
		
	}
	
	private void setProperties(){

		this.gc.setFill(Color.BLACK);										//set font color of text
		this.gc.fillRect(0,0,GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc.drawImage(this.bg,0,0);
		Font theFont = Font.font("Times New Roman",FontWeight.BOLD,30);//set font type, style and size
		this.gc.setFont(theFont);
		String score = " "+ this.score;
		text = new Text(160 ,55 , score);
		text.setFont(Font.loadFont(getClass()
                .getResourceAsStream("/images/boston caps.ttf"), 60));
		
		text.setFill(Color.WHITE);
		
		this.gc.setFill(Color.WHITE);										//set font color of text
		this.gc.fillText("Game Over!", GameStage.WINDOW_WIDTH*0.4, GameStage.WINDOW_HEIGHT*0.45);
		
		this.gc.fillText(score, GameStage.WINDOW_WIDTH*0.4, GameStage.WINDOW_HEIGHT*0.6);
		//else this.gc.fillText("YOU LOSE!", GameStage.WINDOW_WIDTH*0.4, GameStage.WINDOW_HEIGHT*0.6);
		
		Button exitbtn = new Button("Exit Game");
		this.addEventHandler(exitbtn);
		pane.getChildren().add(this.canvas);
		pane.getChildren().add(exitbtn);
	}
	
	private void addEventHandler(Button btn) {
		// TODO Auto-generated method stub
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
	}
	
	Scene getScene(){
		return this.scene;
	}
}
