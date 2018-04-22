package game;
	
import game.input.TargetInputEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	
	private static final double TARGET_FPS = 60;
	private static final double TARGET_UPDATE_RATE = 30;
	
	private Timeline renderLoop, updateLoop;
	private long frameTime;
	private long updateTime;
	private GraphicsContext gc;
	private GameCore game;
	
	private float fps, ups;
	private Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		primaryStage.setTitle("Avoid the Purple 4");
		primaryStage.setResizable(false);
		
		Group root = new Group();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		Canvas canvas = new Canvas(1280, 720);
		assert canvas.getWidth() > 0 : "Actual: " + canvas.getWidth();
		assert canvas.getHeight() > 0 : "Actual: " + canvas.getHeight();
		root.getChildren().add(canvas);
		
		game = new GameCore();
		gc = canvas.getGraphicsContext2D();
        gc.setTextBaseline(VPos.CENTER);
		initializeLoop();
		frameTime = System.currentTimeMillis();
		updateTime = frameTime;
		renderLoop.play();
		updateLoop.play();
		
		// key listener
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ke) {
				game.inMgner.inputs.add(new TargetInputEvent(0, ke.getCode()));
				
			}
		
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ke) {
				game.inMgner.releases.add(new TargetInputEvent(0, ke.getCode()));
				
			}
		
		});
		
		primaryStage.show();
	}
	
	/**
	 * Setup the render and update loops
	 */
	private void initializeLoop() {
		renderLoop = new Timeline();
		renderLoop.setCycleCount(Timeline.INDEFINITE);
		
		// keyframe render loop
		KeyFrame kfRender = new KeyFrame(Duration.seconds(1.0 / TARGET_FPS),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						float delta = (System.currentTimeMillis() - frameTime) / 1000.0f;
						fps = 1f / delta;
						gc.clearRect(0, 0, primaryStage.getWidth(), primaryStage.getHeight());
						game.render(gc, delta);
						frameTime = System.currentTimeMillis();
					}
		});
		
		renderLoop.getKeyFrames().add(kfRender);
		
		updateLoop = new Timeline();
		updateLoop.setCycleCount(Timeline.INDEFINITE);
		
		// keyframe render loop
		KeyFrame kfUpdate = new KeyFrame(Duration.seconds(1.0 / TARGET_UPDATE_RATE),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						float delta = (System.currentTimeMillis() - updateTime) / 1000.0f;
						ups = 1f / delta;
						game.update(delta);
						updateTime = System.currentTimeMillis();
						primaryStage.setTitle(String.format("Avoid the Purple 4 (fps: %.0f ups: %.0f)", fps, ups));
					}
		});
		
		updateLoop.getKeyFrames().add(kfUpdate);
	}
	

	
}
