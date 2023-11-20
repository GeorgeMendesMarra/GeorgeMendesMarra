

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AircraftWar extends Application {
	
	private static final Random RAND = new Random();
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int PLAYER_SIZE = 60;
	
	static final Image PLAYER_IMG = new Image("/images/player.png"); 
	
	static final Image EXPLOSION_IMG = new Image("/images/explosion_sprite.png");
	static final int EXPLOSION_W = 128;
	static final int EXPLOSION_ROWS = 3;
	static final int EXPLOSION_COL = 3;
	static final int EXPLOSION_H = 128;
	static final int EXPLOSION_STEPS = 15;
	
	static final Image ENEMIES_IMG[] = {
			new Image("/images/enemy1.png"),
			new Image("/images/enemy2.png"),
			new Image("/images/enemy3.png"),
			new Image("/images/enemy4.png"),
			new Image("/images/enemy5.png"),
	};
	
	final int MAX_ENEMIES = 10,  MAX_SHOTS = MAX_ENEMIES * 2;
	
	boolean gameOver = false;
	
	private GraphicsContext gc;
	
	Aircraft player;
	List<Shot> shots;
	List<SpaceStuff> spaceStuff;
	List<Enemy> enemies;
	
	private double mouseX;
	private int score;

	@Override
	public void start(Stage stage) throws Exception {
		Canvas canvas = new Canvas(WIDTH, HEIGHT);	
		gc = canvas.getGraphicsContext2D();
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		canvas.setCursor(Cursor.MOVE);
		canvas.setOnMouseMoved(e -> mouseX = e.getX());
		canvas.setOnMouseClicked(e -> {
			if(shots.size() < MAX_SHOTS) shots.add(player.shoot());
			if(gameOver) { 
				gameOver = false;
				setup();
			}
		});
		setup();
		stage.setScene(new Scene(new StackPane(canvas)));
		stage.setTitle("Aircraft war");
		stage.show();
		
	}
	
	private void setup() {
		spaceStuff = new ArrayList<>();
		shots = new ArrayList<>();
		enemies = new ArrayList<>();
		player = new Aircraft(WIDTH / 2, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
		score = 0;
		IntStream.range(0, MAX_ENEMIES).mapToObj(i -> this.newEnemy()).forEach(enemies::add);
	}
	
	private void run(GraphicsContext gc) {
		gc.setFill(Color.grayRgb(20));
		gc.fillRect(0, 0, WIDTH, HEIGHT);

		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(Font.font(15));
		gc.setFill(Color.WHITE);
		gc.fillText("Score: " + score, 40,  15);
	
		
		if(gameOver) {
			gc.setFont(Font.font(30));
			gc.setFill(Color.RED);
			gc.fillText("Game Over", WIDTH / 2, HEIGHT /2);
			return;
		}
		spaceStuff.forEach(SpaceStuff::draw);
	
		player.update();
		player.draw();
		player.posX = (int) mouseX;
		
		enemies.stream().peek(Aircraft::update).peek(Aircraft::draw).forEach(e -> {
			if(player.colide(e) && !player.exploding) {
				player.explode();
			}
		});
		
		
		for (int i = shots.size() - 1; i >=0 ; i--) {
			Shot shot = shots.get(i);
			if(shot.posY < 0 || shot.toRemove)  { 
				shots.remove(i);
				continue;
			}
			shot.update();
			shot.draw();
			for (Enemy enemy : enemies) {
				if(shot.colide(enemy) && !enemy.exploding) {
					score++;
					enemy.explode();
					shot.toRemove = true;
				}
			}
		}
		
		for (int i = enemies.size() - 1; i >= 0; i--){  
			if(enemies.get(i).destroyed)  {
				enemies.set(i, newEnemy());
			}
		}
	
		gameOver = player.destroyed;
		if(RAND.nextInt(10) > 2) {
			spaceStuff.add(new SpaceStuff());
		}
		for (int i = 0; i < spaceStuff.size(); i++) {
			if(spaceStuff.get(i).posY > HEIGHT)
				spaceStuff.remove(i);
		}
	}

	
	public class Aircraft {

		int posX, posY, size;
		boolean exploding, destroyed;
		Image img;
		int explosionStep = 0;
		
		public Aircraft(int posX, int posY, int size,  Image image) {
			this.posX = posX;
			this.posY = posY;
			this.size = size;
			img = image;
		}
		
		public Shot shoot() {
			return new Shot(posX + size / 2 - Shot.size / 2, posY - Shot.size);
		}

		public void update() {
			if(exploding) explosionStep++;
			destroyed = explosionStep > EXPLOSION_STEPS;
		}
		
		public void draw() {
			if(exploding) {
				gc.drawImage(EXPLOSION_IMG, explosionStep % EXPLOSION_COL * EXPLOSION_W, (explosionStep / EXPLOSION_ROWS) * EXPLOSION_H + 1,
						EXPLOSION_W, EXPLOSION_H,
						posX, posY, size, size);
			}
			else {
				gc.drawImage(img, posX, posY, size, size);
			}
		}
	
		public boolean colide(Aircraft other) {
			int d = distance(this.posX + size / 2, this.posY + size /2, 
							other.posX + other.size / 2, other.posY + other.size / 2);
			return d < other.size / 2 + this.size / 2 ;
		}
		
		public void explode() {
			exploding = true;
			explosionStep = -1;
		}

	}
	
	public class Enemy extends Aircraft {
		
		private static final int SPEED = 7;
		
		public Enemy(int posX, int posY, int size, Image image) {
			super(posX, posY, size, image);
		}
		
		public void update() {
			super.update();
			if(!exploding && !destroyed) posY += SPEED;
			if(posY > HEIGHT) destroyed = true;
		}
	}
	
	public class Shot {
		
		public boolean toRemove;

		int posX, posY, speed = 10;

		private Color color;
		
		static final int size = 6;
		
		public Shot(int posX, int posY) {
			this.posX = posX;
			this.posY = posY;
		}

		public void update() {
			posY-=speed;
		}
		
		public void draw() {
			gc.setFill(Color.RED);
			gc.fillOval(posX, posY, size, size);
		}
		
		public boolean colide(Aircraft aircraft) {
			int distance = distance(this.posX + size / 2, this.posY + size / 2, 
					aircraft.posX + aircraft.size / 2, aircraft.posY + aircraft.size / 2);
			return distance  < aircraft.size / 2 + size / 2;
		} 
		
	}
	
	public class SpaceStuff {
		int posX, posY;
		private int h, w, r, g, b;
		private double opacity;
		
		public SpaceStuff() {
			posX = RAND.nextInt(WIDTH);
			posY = 0;
			w = RAND.nextInt(5) + 1;
			h =  RAND.nextInt(5) + 1;
			r = RAND.nextInt(100) + 150;
			g = RAND.nextInt(100) + 150;
			b = RAND.nextInt(100) + 150;
			opacity = RAND.nextFloat();
			if(opacity < 0) opacity *=-1;
			if(opacity > 0.5) opacity = 0.5;
		}
		
		public void draw() {
			if(opacity > 0.8) opacity-=0.01;
			if(opacity < 0.1) opacity+=0.01;
			gc.setFill(Color.rgb(r, g, b, opacity));
			gc.fillOval(posX, posY, w, h);
			posY+=20;
		}
	}
	
	Enemy newEnemy() {
		return new Enemy(50 + RAND.nextInt(WIDTH - 100), 0, PLAYER_SIZE, ENEMIES_IMG[RAND.nextInt(ENEMIES_IMG.length)]);
	}
	
	int distance(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}
	
	public static void main(String[] args) {
		launch();
	}
}
