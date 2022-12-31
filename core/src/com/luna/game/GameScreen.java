package com.luna.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.luna.game.Engine.EnemyControls;
import com.luna.game.Engine.PlayerControls;
import com.luna.game.Engine.RenderingFunctions;
import com.luna.game.Engine.Utilities;
import com.luna.game.Entities.Entity;
import com.luna.game.Entities.HealthBar;
import com.luna.game.Entities.HostileNpc;
import com.luna.game.Entities.Player;
import com.luna.game.Entities.Wall;

public class GameScreen implements Screen {

	final static int SCREEN_HEIGHT = Utilities.SCREEN_HEIGHT;
	final static int SCREEN_WIDTH = Utilities.SCREEN_WIDTH;
	final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
	final int WORLD_WIDTH = Utilities.WORLD_WIDTH;

	final ProjectLuna game;

	private OrthographicCamera camera;

	HealthBar bar;
	HealthBar enemyBar;

	Sprite backgroundSprite;

	Polygon attackTriangle;
	PolygonSpriteBatch polyBatch;
	PolygonRegion triReg;
	Polygon playerAttackTriangle;

	private Player player;
	private PlayerControls controls;
	private boolean playerAttackFlag = false;
	private int playerMaxHealth;



	private boolean boundaryFlag;

	private HostileNpc enemy;
	private int enemyHealth;
	private EnemyControls enemyControls;

	private Texture playerSpriteTexture;
	private Texture wallImage;
	private Texture healthImage;
	private Texture demonImage;

	private Wall wall;
	private List<Wall> walls;

	float originalHealthBarWidth;

	public GameScreen(final ProjectLuna game) {
		this.game = game;

		backgroundSprite = new Sprite(new Texture("../assets/black.jpg"));
		backgroundSprite.setPosition(0, 0);
		backgroundSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

		playerSpriteTexture = new Texture("../assets/kirby.jpg");
		wallImage = new Texture("../assets/wall.jpg");
		healthImage = new Texture("../assets/red.png");
		demonImage = new Texture("../assets/demon.png");


		// -------- CREATE CAMERA --------
		camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		// -------- REPRESENT PLAYER AS A SPRITE --------
		// Sets the initial location of the character
		float[] location = new float[] {WORLD_WIDTH / 4, WORLD_HEIGHT / 4};

		// Set width/height of player character
		float[] playerDimensions = new float[] {WORLD_WIDTH / 20, WORLD_HEIGHT / 20};
		playerMaxHealth = 100;
		player = new Player(playerSpriteTexture, location, playerDimensions, playerMaxHealth);
		player.setAttack(100);
		controls = new PlayerControls(player);


		//  Represent player healthbar
		float healthBarBuffer = 5;
		float[] healthBarLocation = new float[] {(float) WORLD_WIDTH - WORLD_WIDTH / 4, WORLD_HEIGHT / 2};

		bar = new HealthBar(healthImage, healthBarLocation, new float[] {0, 0}, player.getHealth(), healthBarBuffer);

		// -------- CREATE ENEMY --------
		float[] demonLocation = new float[] {WORLD_WIDTH / 4, WORLD_HEIGHT / 2};
		float[] enemyDimensions = new float[] {WORLD_WIDTH / 20, WORLD_HEIGHT / 20};
		enemyHealth = 100;
		enemy = new HostileNpc(demonImage, demonLocation, enemyDimensions, enemyHealth);
		enemy.setAttack(100);

		enemyControls = new EnemyControls(enemy);

		//  Represent enemy healthbar
		Vector2 loc = new Vector2();
		enemy.getSprite().getBoundingRectangle().getCenter(loc);
		float[] enemyHealthBarLocation = new float[] {loc.x, loc.y + enemy.getSprite().getHeight()/2 };

		enemyBar = new HealthBar(healthImage, enemyHealthBarLocation, new float[] {0, 0}, enemy.getHealth(), 0);


		// -------- REPRESENT WALLS AS SPRITES --------
		float wallDimensions[] = new float[] {WORLD_WIDTH / 20, WORLD_HEIGHT / 20};
		float wallInitialLocation[] = new float[] {0, 0};
		wall = new Wall(wallImage, wallInitialLocation, wallDimensions);

		walls = RenderingFunctions.RenderSquareFromCorner(wallImage, wall.getSprite().getWidth(),
				wall.getSprite().getHeight(), (float) 0, (float) 0, WORLD_HEIGHT,
				WORLD_WIDTH - WORLD_WIDTH / 4);


		// -------- USED TO RENDER POLYGONS --------
		polyBatch = new PolygonSpriteBatch();
	}

	@Override
	public void render(float delta) {

		// Clear screen with set color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// tell the camera to update its matrices.
		camera.update();

		// -------- RENDER COORDINATE SYSTEM TO CAMERA --------
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();

		backgroundSprite.draw(game.batch);

		// -------- DRAW PLAYER CHARACTER
		player.getSprite().draw(game.batch);

		// -------- DRAW ENEMY CHARACTER
		enemy.getSprite().draw(game.batch);

		// -------- DRAW HEALTHBAR --------
		bar.getSprite().draw(game.batch);
		enemyBar.getSprite().draw(game.batch);

		// -------- DRAW WALLS --------
		for (Wall wall : walls) {
			wall.getSprite().draw(game.batch);
		}

		game.batch.end();

		polyBatch.begin();
		polyBatch.setProjectionMatrix(camera.combined);

		// -------- CHECK FOR ENEMY ATTACKS --------
		Polygon enemyAttackTriangle = enemyControls.attack();

		// Sprite playerSprite = player.getSprite();

		/**
		 * float[] vertices = new float[] {
		 * 
		 * playerSprite.getX(), playerSprite.getY(), playerSprite.getX() + playerSprite.getWidth(),
		 * playerSprite.getY(), playerSprite.getX() + playerSprite.getWidth(), playerSprite.getY() +
		 * playerSprite.getHeight(), playerSprite.getX(), playerSprite.getY() +
		 * playerSprite.getHeight()
		 * 
		 * };
		 * 
		 * Polygon rPoly = new Polygon(vertices);
		 * 
		 * polyBatch.draw( new PolygonRegion(new TextureRegion(healthImage), rPoly.getVertices(),
		 * new EarClippingTriangulator().computeTriangles(vertices).toArray()),
		 * player.getSprite().getScaleX(), player.getSprite().getScaleY());
		 */

		if (Utilities.isCollision(enemyAttackTriangle, player.getSprite())) {
			player.removeHealth((int) (enemy.getAttack() * delta));
			bar.reduceHealth(player);
			drawPolygon(healthImage, enemyAttackTriangle, enemy);
		}

		// -------- CHECK FOR PLAYER ATTACKS --------
		if (playerAttackFlag == true) {

			drawPolygon(healthImage, playerAttackTriangle, player);

			if (Utilities.isCollision(playerAttackTriangle, enemy.getSprite())) {
				enemy.removeHealth((int) (player.getAttack() * delta));
				enemyBar.reduceHealth(enemy);	
			}

			playerAttackFlag = false;
		}
		polyBatch.end();

		// -------- USER INPUT (KEYBOARD) --------
		listen(delta);

	}

	private void drawPolygon(Texture image, Polygon polygon, Entity entity){

		polyBatch.draw(
			new PolygonRegion(
				new TextureRegion(image),
				polygon.getTransformedVertices(),
				new EarClippingTriangulator().computeTriangles(polygon.getTransformedVertices()).toArray()),
			entity.getSprite().getScaleX(), 
			entity.getSprite().getScaleY());
	}

	@Override
	public void resize(int width, int height) {

	}

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

	private void listen(float delta) {

		Sprite playerSprite = player.getSprite();
		boundaryFlag = false;

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {

			controls.moveLeft();

			// Make sure character can't leave edge of screen
			if (playerSprite.getX() < 0) {
				boundaryFlag = true;
				controls.moveRight();
			}
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getWall())) {
					controls.moveRight();
					break;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			controls.moveRight();

			// Make sure character can't leave edge of screen
			if (playerSprite.getY() > WORLD_WIDTH - playerSprite.getWidth()) {
				boundaryFlag = true;
				controls.moveLeft();

			}
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getWall())) {
					controls.moveLeft();
					break;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			// Make sure character can't leave edge of screen
			controls.moveUp();

			if (playerSprite.getY() > WORLD_HEIGHT - playerSprite.getHeight()) {
				boundaryFlag = true;
				controls.moveDown();
			}

			// Make sure wall can't be passed
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getWall())) {
					controls.moveDown();
					break;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {

			controls.moveDown();

			// Make sure character can't leave edge of screen
			if (playerSprite.getY() < 0) {
				boundaryFlag = true;
				controls.moveUp();
			}

			// Make sure wall can't be passed
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}
				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getWall())) {
					controls.moveUp();
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.A)) {

			playerAttackTriangle = controls.attack();
			playerAttackFlag = true;


		}

	}


}
