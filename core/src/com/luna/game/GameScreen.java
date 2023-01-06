package com.luna.game;

import java.util.ArrayList;
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
import com.luna.game.Engine.EnemyControls;
import com.luna.game.Engine.Loader;
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

	Sprite backgroundSprite;

	Polygon attackTriangle;
	PolygonSpriteBatch polyBatch;
	PolygonRegion triReg;
	Polygon playerAttackTriangle;

	private Player player;
	private PlayerControls controls;
	private boolean playerAttackFlag = false;

	int mapNo;
	private boolean boundaryFlag;

	private HostileNpc enemy;
	private EnemyControls enemyControls;

	private Texture wallImage;
	private Texture healthImage;

	private Wall wall;
	private List<Wall> walls;
	private List<HealthBar> healthBars = new ArrayList<>();
	float originalHealthBarWidth;

	public GameScreen(final ProjectLuna game) {
		this.game = game;
		Loader loader = new Loader();

		backgroundSprite = new Sprite(new Texture("../assets/black.jpg"));
		backgroundSprite.setPosition(0, 0);
		backgroundSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

		wallImage = new Texture("../assets/wall.jpg");
		healthImage = new Texture("../assets/red.png");


		// -------- CREATE CAMERA --------
		camera = loader.loadCamera();

		// -------- REPRESENT PLAYER AS A SPRITE --------
		player = loader.loadPlayer();
		controls = new PlayerControls(player);
		//  Represent player healthbar
		healthBars.add(new HealthBar(healthImage, player));
		player.setHealthBar(healthBars.get(0));

		// -------- CREATE ENEMY --------
		enemy = loader.loadDemon();
		enemyControls = new EnemyControls(enemy);
		healthBars.add(new HealthBar(healthImage, enemy));
		enemy.setHealthBar(healthBars.get(1));

		// -------- REPRESENT WALLS AS SPRITES --------
		float wallDimensions[] = new float[] {WORLD_WIDTH / 20, WORLD_HEIGHT / 20};
		float wallInitialLocation[] = new float[] {0, 0};
		wall = new Wall(wallImage, wallInitialLocation, wallDimensions);

		// Render Map 1
		mapNo = 1;

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
		for(HealthBar bar : healthBars){
			bar.draw(game.batch);
		}

		// -------- DRAW WALLS --------
		if(mapNo == 1){
			walls = RenderingFunctions.RenderSquareFromCorner(wallImage, wall.getSprite().getWidth(),
				wall.getSprite().getHeight(), (float) 0, (float) 0, WORLD_HEIGHT,
				WORLD_WIDTH - WORLD_WIDTH / 4);
		}

		for (Wall wall : walls) {
			wall.getSprite().draw(game.batch);
		}

		game.batch.end();

		polyBatch.begin();
		polyBatch.setProjectionMatrix(camera.combined);

		// -------- CHECK FOR ENEMY ATTACKS --------
		Polygon enemyAttackTriangle = enemyControls.attack();

		if (Utilities.isCollision(enemyAttackTriangle, player.getSprite())) {
			player.removeHealth((int) (enemy.getAttack() * delta));
			player.getHealthBar().reduceHealth(player);
			drawPolygon(healthImage, enemyAttackTriangle, enemy);
		}

		// -------- CHECK FOR PLAYER ATTACKS --------
		if (playerAttackFlag == true) {

			drawPolygon(healthImage, playerAttackTriangle, player);

			if (Utilities.isCollision(playerAttackTriangle, enemy.getSprite())) {
				enemy.removeHealth((int) (player.getAttack() * delta));
				enemy.getHealthBar().reduceHealth(enemy);	
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

				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getSprite())) {
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

				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getSprite())) {
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

				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getSprite())) {
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
				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getSprite())) {
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
