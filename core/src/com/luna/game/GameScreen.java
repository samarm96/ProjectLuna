package com.luna.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.luna.game.Engine.RenderingFunctions;
import com.luna.game.Engine.Utilites;
import com.luna.game.Entities.Entity;
import com.luna.game.Entities.HealthBar;
import com.luna.game.Entities.HostileNpc;
import com.luna.game.Entities.Player;
import com.luna.game.Entities.Wall;

public class GameScreen implements Screen {

	final static int SCREEN_HEIGHT = Utilites.SCREEN_HEIGHT;
	final static int SCREEN_WIDTH = Utilites.SCREEN_WIDTH;

	final ProjectLuna game;

	private OrthographicCamera camera;

	HealthBar bar;

	private Player player;
	private Sprite playerSprite;

	private float lastPlayerY;
	private float lastPlayerX;
	private int playerMaxHealth;

	private HostileNpc enemy;
	private Sprite enemySprite;
	private int enemyHealth;

	private Texture playerSpriteTexture;
	private Texture wallImage;
	private Texture healthImage;
	private Texture demonImage;

	private Wall wall;
	private List<Wall> walls;

	float originalHealthBarWidth;

	public GameScreen(final ProjectLuna game) {
		this.game = game;

		playerSpriteTexture = new Texture("../assets/kirby.jpg");
		wallImage = new Texture("../assets/wall.jpg");
		healthImage = new Texture("../assets/red.png");
		demonImage = new Texture("../assets/demon.png");

		// -------- CREATE CAMERA --------
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// -------- REPRESENT PLAYER AS A SPRITE --------
		// Sets the initial location of the character
		float[] location = new float[] {SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4};

		// Set width/height of player character
		float[] playerDimensions = new float[] {40, SCREEN_HEIGHT / 20};
		playerMaxHealth = 100;
		player = new Player(playerSpriteTexture, location, playerDimensions, playerMaxHealth);
		playerSprite = player.getSprite();


		// -------- REPRESENT HEALTHBAR AS A RECTANGLE --------
		float healthBarBuffer = 10;
		float[] healthBarLocation = new float[]{SCREEN_WIDTH - SCREEN_WIDTH / 4 + healthBarBuffer, SCREEN_HEIGHT / 2};
		bar = new HealthBar(healthImage, healthBarLocation, new float[]{0,0}, player.getHealth());

		// -------- CREATE ENEMY --------
		float[] demonLocation = new float[] {SCREEN_WIDTH / 4, SCREEN_HEIGHT / 2};
		float[] enemyDimensions = new float[] {40, SCREEN_HEIGHT / 20};
		enemyHealth = 100;
		enemy = new HostileNpc(demonImage, demonLocation, enemyDimensions, enemyHealth);
		enemy.setAttack(100);
		enemySprite = enemy.getSprite();

		// -------- REPRESENT WALLS AS SPRITES --------
		float wallDimensions[] = new float[] {40, SCREEN_HEIGHT / 20};
		float wallInitialLocation[] = new float[] {0, 0};
		wall = new Wall(wallImage, wallInitialLocation, wallDimensions);

		walls = RenderingFunctions.RenderSquareFromCorner(wallImage, wall.getSprite().getWidth(),
				wall.getSprite().getHeight(), (float) 0, (float) 0, SCREEN_HEIGHT - 1,
				SCREEN_WIDTH - SCREEN_WIDTH / 4);

	}

	@Override
	public void render(float delta) {

		// Clear screen with set color
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// tell the camera to update its matrices.
		camera.update();

		// -------- RENDER COORDINATE SYSTEM TO CAMERA --------
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();

		// -------- DRAW PLAYER CHARACTER
		drawEntity(player);

		// -------- DRAW ENEMY CHARACTER
		drawEntity(enemy);

		// -------- DRAW HEALTHBAR --------
		drawEntity(bar);

		// -------- DRAW WALLS --------
		for (Wall wall : walls) {
			drawEntity(wall);
		}

		game.batch.end();

		 
		if (Utilites.CollisonCheck(playerSprite, enemySprite)) {
			System.out.println(player.getHealth());
			enemy.attackPlayer(player, Gdx.graphics.getDeltaTime());
			bar.reduceHealth(player);
		}
		

		// -------- USER INPUT (KEYBOARD) --------
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			lastPlayerX = playerSprite.getX();
			playerSprite.translateX(-1 * 24 * Gdx.graphics.getDeltaTime());
			// Make sure character can't leave edge of screen
			if (playerSprite.getX() < 0) {
				playerSprite.setX(0);
			}
			for (int i = 0; i < walls.size(); i++) {
				if (Utilites.CollisonCheck(playerSprite.getBoundingRectangle(),
						walls.get(i).getWall().getBoundingRectangle())) {
					playerSprite.setX(lastPlayerX);
				}
			}

		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			lastPlayerX = playerSprite.getX();
			playerSprite.translateX(24 * Gdx.graphics.getDeltaTime());

			// Make sure character can't leave edge of screen
			if (playerSprite.getY() > Gdx.graphics.getWidth() - playerSprite.getWidth()) {
				playerSprite.setX(Gdx.graphics.getWidth() - playerSprite.getWidth());
			}
			for (int i = 0; i < walls.size(); i++) {
				if (Utilites.CollisonCheck(playerSprite.getBoundingRectangle(),
						walls.get(i).getWall().getBoundingRectangle())) {
					playerSprite.setX(lastPlayerX);
				}
			}
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			lastPlayerY = playerSprite.getY();
			// Make sure character can't leave edge of screen
			if (playerSprite.getY() > Gdx.graphics.getHeight() - playerSprite.getHeight()) {
				playerSprite.setY(Gdx.graphics.getHeight() - playerSprite.getHeight());
			}
			// Make sure wall can't be passed
			playerSprite.translateY(24 * Gdx.graphics.getDeltaTime());
			for (int i = 0; i < walls.size(); i++) {
				if (Utilites.CollisonCheck(playerSprite.getBoundingRectangle(),
						walls.get(i).getWall().getBoundingRectangle())) {
					playerSprite.setY(lastPlayerY);
				}
			}
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			lastPlayerY = playerSprite.getY();
			playerSprite.translateY(-1 * 24 * Gdx.graphics.getDeltaTime());
			// Make sure character can't leave edge of screen
			if (playerSprite.getY() < 0) {
				playerSprite.setY(0);
			}
			// Make sure wall can't be passed
			for (int i = 0; i < walls.size(); i++) {
				if (Utilites.CollisonCheck(playerSprite.getBoundingRectangle(),
						walls.get(i).getWall().getBoundingRectangle())) {
					playerSprite.setY(lastPlayerY);
				}
			}
		}

	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
	}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {

	}

	public void drawEntity(Entity entity) {
		Sprite entitySprite = entity.getSprite();
		game.batch.draw(entitySprite, entitySprite.getX(), entitySprite.getY(),
				entitySprite.getWidth(), entitySprite.getHeight());
	}

}
