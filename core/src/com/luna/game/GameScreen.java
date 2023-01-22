package com.luna.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.luna.game.Components.Health;
import com.luna.game.Components.Position;
import com.luna.game.Components.SpriteComp;
import com.luna.game.Components.Controls.EnemyControls;
import com.luna.game.Components.Controls.PlayerControls;
import com.luna.game.Engine.Loader;
import com.luna.game.Engine.RenderingFunctions;
import com.luna.game.Engine.RenderingSystem;
import com.luna.game.Engine.Utilities;
import com.luna.game.Entities.Enemy;
import com.luna.game.Entities.HealthBar;
import com.luna.game.Entities.Player;
import com.luna.game.Entities.Wall;

public class GameScreen implements Screen {

	final static int SCREEN_HEIGHT = Utilities.SCREEN_HEIGHT;
	final static int SCREEN_WIDTH = Utilities.SCREEN_WIDTH;
	final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
	final int WORLD_WIDTH = Utilities.WORLD_WIDTH;

	final ProjectLuna game;
	private boolean missileFlag;
	private OrthographicCamera camera;

	private Sprite backgroundSprite;
	private Sprite missile;
	private PolygonSpriteBatch polyBatch;
	private Polygon playerAttackTriangle;

	private boolean playerAttackFlag = false;

	RenderingSystem renderSys;
	int mapNo;
	private boolean boundaryFlag;

	private EnemyControls enemyControls;

	private Texture wallImage;
	private Texture healthImage;
	private Texture missileImage;
	float originalHealthBarWidth;
	PlayerControls pc;
	HealthBar playerHb;
	HealthBar enemyHb;
	Player player;
	Enemy enemy;
	Wall wall;
	List<Wall> walls;

	public GameScreen(final ProjectLuna game) {
		this.game = game;
		Loader loader = new Loader();

		backgroundSprite = new Sprite(new Texture("../assets/black.jpg"));
		backgroundSprite.setPosition(0, 0);
		backgroundSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

		wallImage = new Texture("../assets/wall.jpg");
		healthImage = new Texture("../assets/red.png");
		missileImage = new Texture("../assets/green.jpg");

		// -------- CREATE CAMERA --------
		renderSys = new RenderingSystem(game.batch);
		camera = renderSys.getCamera();

		// Wall
		wall = new Wall();
		float wallDimensions[] = new float[] { WORLD_WIDTH / 20, WORLD_HEIGHT / 20 };
		wall.addComponent(new Position(0, 0));
		wall.addComponent(new SpriteComp(wallImage, new float[] { 0, 0 }, wallDimensions));

		// Render Map 1
		mapNo = 1;

		// Player
		player = loader.loadPlayer();
		pc = new PlayerControls(player);
		playerHb = new HealthBar(healthImage, player);

		// Enemy
		enemy = loader.loadDemon();
		enemyControls = new EnemyControls(enemy);
		enemyHb = new HealthBar(healthImage, enemy);

		// -------- USED TO RENDER POLYGONS --------
		polyBatch = new PolygonSpriteBatch();
	}

	@Override
	public void render(float delta) {

		renderSys.update();
		// -------- RENDER COORDINATE SYSTEM TO CAMERA --------

		backgroundSprite.draw(game.batch);

		SpriteComp wallSpriteComponent = wall.getSpriteComponent();

		// -------- DRAW PLAYER CHARACTER
		SpriteComp playerSpriteComponent = player.getSpriteComponent();
		playerSpriteComponent.getSprite().draw(game.batch);

		// -------- DRAW ENEMY CHARACTER
		SpriteComp enemySpriteComponent = enemy.getSpriteComponent();
		enemySpriteComponent.getSprite().draw(game.batch);

		// -------- DRAW HEALTHBAR --------
		playerHb.draw(game.batch);
		enemyHb.draw(game.batch);

		// -------- DRAW WALLS --------
		if (mapNo == 1) {

			walls = RenderingFunctions.RenderSquareFromCorner(wallSpriteComponent.getSprite().getTexture(),
					wallSpriteComponent.getSprite().getWidth(), wallSpriteComponent.getSprite().getHeight(), (float) 0,
					(float) 0,
					WORLD_HEIGHT, WORLD_WIDTH - WORLD_WIDTH / 4);
		}

		for (Wall wall : walls) {
			wall.getSpriteComponent().getSprite().draw(game.batch);
			;
		}

		if (missileFlag == true) {
			
			int playerAttack = player.getAttributes().getAttack();
			if (Utilities.CollisonCheck(enemySpriteComponent.getSprite(), missile)) {

				Health  EnemyHealth = (Health) (enemy.getComponent("Health").get());
				 EnemyHealth.removeHealth((int) (playerAttack * delta));
				enemyHb.reduceHealth();
				missileFlag = false;
			}

			for (int i = 0; i < walls.size(); i++) {
				Sprite wallSprite = (((SpriteComp) walls.get(i).getComponent("Sprite").get()).getSprite());
				if (Utilities.CollisonCheck(missile, wallSprite)) {
					missileFlag = false;
					break;
				} else {

					missile.draw(game.batch);
					missile.translate(0, 1 * delta);

				}
			}

		}

		polyBatch.begin();
		polyBatch.setProjectionMatrix(camera.combined);

		// -------- CHECK FOR ENEMY ATTACKS --------
		Polygon enemyAttackTriangle = enemyControls.attack();
		int enemyAttack = enemy.getAttributes().getAttack();
		if (Utilities.PolySpriteCollisionCheck(enemyAttackTriangle, playerSpriteComponent.getSprite())) {
			Health playerHealth = player.getHealth();
			playerHealth.removeHealth((int) (enemyAttack * delta));
			playerHb.reduceHealth();

			drawPolygon(healthImage, enemyAttackTriangle, enemySpriteComponent.getSprite());
		}

		// -------- CHECK FOR PLAYER ATTACKS --------

		if (playerAttackFlag == true) {

			int playerAttack = player.getAttributes().getAttack();
			drawPolygon(healthImage, playerAttackTriangle, playerSpriteComponent.getSprite());

			if (Utilities.PolySpriteCollisionCheck(playerAttackTriangle, enemySpriteComponent.getSprite())) {
				Health enemyHealth = enemy.getHealth();
				enemyHealth.removeHealth((int) (playerAttack * delta));
				enemyHb.reduceHealth();
			}

			playerAttackFlag = false;
		}

		polyBatch.end();
		game.batch.end();

		// -------- USER INPUT (KEYBOARD) --------
		listen(delta);

	}

	private void drawPolygon(Texture image, Polygon polygon, Sprite entity) {

		polyBatch.draw(
				new PolygonRegion(new TextureRegion(image), polygon.getTransformedVertices(),
						new EarClippingTriangulator()
								.computeTriangles(polygon.getTransformedVertices()).toArray()),
				entity.getScaleX(), entity.getScaleY());
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
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {

	}

	private void listen(float delta) {

		Sprite playerSprite = ((SpriteComp)  player.getComponent("Sprite").get()).getSprite();
		boundaryFlag = false;

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {

			pc.moveLeft();

			// Make sure character can't leave edge of screen
			if (playerSprite.getX() < 0) {
				boundaryFlag = true;
				pc.moveRight();
			}
			for (int i = 0; i <  walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				Sprite wallSprite = (((SpriteComp)  walls.get(i).getComponent("Sprite").get()).getSprite());
				if (Utilities.CollisonCheck(playerSprite, wallSprite)) {
					pc.moveRight();
					break;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			pc.moveRight();
			// Make sure character can't leave edge of screen
			if (playerSprite.getY() > WORLD_WIDTH - playerSprite.getWidth()) {
				boundaryFlag = true;
				pc.moveLeft();

			}
			for (int i = 0; i <  walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				Sprite wallSprite = (((SpriteComp)  walls.get(i).getComponent("Sprite").get()).getSprite());
				if (Utilities.CollisonCheck(playerSprite, wallSprite)) {
					pc.moveLeft();
					break;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			// Make sure character can't leave edge of screen
			pc.moveUp();
			if (playerSprite.getY() > WORLD_HEIGHT - playerSprite.getHeight()) {
				boundaryFlag = true;
				pc.moveDown();
			}

			// Make sure wall can't be passed
			for (int i = 0; i <  walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				Sprite wallSprite = (((SpriteComp)  walls.get(i).getComponent("Sprite").get()).getSprite());
				if (Utilities.CollisonCheck(playerSprite, wallSprite)) {
					pc.moveDown();
					break;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {

			pc.moveDown();
			// Make sure character can't leave edge of screen
			if (playerSprite.getY() < 0) {
				boundaryFlag = true;
				pc.moveUp();
			}

			// Make sure wall can't be passed
			for (int i = 0; i <  walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				Sprite wallSprite = (((SpriteComp)  walls.get(i).getComponent("Sprite").get()).getSprite());
				if (Utilities.CollisonCheck(playerSprite, wallSprite)) {
					pc.moveUp();
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.A)) {

			playerAttackTriangle = pc.attack();
			playerAttackFlag = true;

		}

		if (Gdx.input.isKeyPressed(Keys.B)) {
			missile = pc.rangedAttack(missileImage);
			missileFlag = true;

		}

	}

}
