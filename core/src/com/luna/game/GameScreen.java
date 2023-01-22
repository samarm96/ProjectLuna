package com.luna.game;


import java.util.ArrayList;
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
import com.badlogic.gdx.utils.Array;
import com.luna.game.Components.Attributes;
import com.luna.game.Components.Health;
import com.luna.game.Components.Position;
import com.luna.game.Components.SpriteComp;
import com.luna.game.Components.Controls.EnemyControls;
import com.luna.game.Components.Controls.PlayerControls;
import com.luna.game.Components.Controls.TestEnemyControls;
import com.luna.game.Components.Controls.TestPlayerControls;
import com.luna.game.Engine.Loader;
import com.luna.game.Engine.RenderingFunctions;
import com.luna.game.Engine.RenderingSystem;
import com.luna.game.Engine.Utilities;
import com.luna.game.Entities.Entity;
import com.luna.game.Entities.HealthBar;
import com.luna.game.Entities.HostileNpc;
import com.luna.game.Entities.Player;
import com.luna.game.Entities.TestEnemy;
import com.luna.game.Entities.TestHealthBar;
import com.luna.game.Entities.TestPlayer;
import com.luna.game.Entities.TestWall;
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

	private Player player;
	private PlayerControls controls;
	private boolean playerAttackFlag = false;

	RenderingSystem renderSys;
	int mapNo;
	private boolean boundaryFlag;

	private HostileNpc enemy;
	private EnemyControls enemyControls;
	private TestEnemyControls testEnemyControls;
	
	private Texture wallImage;
	private Texture healthImage;
	private Texture missileImage;
	private Wall wall;
	private List<Wall> walls;
	private List<HealthBar> healthBars = new ArrayList<>();
	float originalHealthBarWidth;
	Array<Entity> renderQue;
	TestPlayerControls tpc;

	TestPlayer testPlayer;
	TestEnemy testEnemy;
	TestWall testWall;

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
		//camera = loader.loadCamera();
		renderSys = new RenderingSystem(game.batch);
		camera = renderSys.getCamera();
		// -------- REPRESENT PLAYER AS A SPRITE --------
		player = loader.loadPlayer();
		controls = new PlayerControls(player);

		// Represent player healthbar
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


		renderQue = new Array<Entity>();
		//renderQue.add(player);
		//renderQue.add(enemy);
		//renderQue.add(player.getHealthBar());
		//renderQue.add(enemy.getHealthBar());

		// Render Map 1
		mapNo = 1;

		// Test Player
		Position playerPos = new Position(25,25);
		Attributes playerAttributes = new Attributes(100);
		Health playerHealth = new Health(100, 100);
		SpriteComp playerSpri = new SpriteComp(new Texture("../assets/kirby.jpg"), playerPos.getPos(), new float[]{5,5});
		testPlayer = new TestPlayer();
		testPlayer.addComponent(playerHealth);
		testPlayer.addComponent(playerPos);
		testPlayer.addComponent(playerSpri);
		testPlayer.addComponent(playerAttributes);
		tpc = new TestPlayerControls(testPlayer);

		testPlayerHb = new TestHealthBar(healthImage, testPlayer);

		// Test Enemy
		Position enemyPos = new Position(50,75);
		Attributes enemyAttributes = new Attributes(100);
		Health enemyHealth = new Health(100, 100);
		SpriteComp enemySpri = new SpriteComp(new Texture("../assets/demon.png"), enemyPos.getPos(), new float[]{5,5});
		testEnemy = new TestEnemy();
		testEnemy.addComponent(enemyPos);
		testEnemy.addComponent(enemyAttributes);
		testEnemy.addComponent(enemyHealth);
		testEnemy.addComponent(enemySpri);
		testEnemyControls = new TestEnemyControls(testEnemy);

		testEnemyHb = new TestHealthBar(healthImage, testEnemy);


		// -------- USED TO RENDER POLYGONS --------
		polyBatch = new PolygonSpriteBatch();
	}

	TestHealthBar testPlayerHb;
	TestHealthBar testEnemyHb;

	@Override
	public void render(float delta) {

	
		renderSys.update();
		// -------- RENDER COORDINATE SYSTEM TO CAMERA --------


		backgroundSprite.draw(game.batch);

        for(Entity entity : renderQue){
            entity.getSprite().draw(game.batch);
        }

		// -------- DRAW PLAYER CHARACTER
		//player.getSprite().draw(game.batch);
		SpriteComp spri = (SpriteComp) testPlayer.getComponent("Sprite").get();
		spri.getSprite().draw(game.batch);
		// -------- DRAW ENEMY CHARACTER
		//enemy.getSprite().draw(game.batch);
		SpriteComp enemySpri = (SpriteComp) testEnemy.getComponent("Sprite").get();
		enemySpri.getSprite().draw(game.batch);

		// -------- DRAW HEALTHBAR --------
		testPlayerHb.draw(game.batch);
		testEnemyHb.draw(game.batch);

		// -------- DRAW WALLS --------
		if (mapNo == 1) {
			walls = RenderingFunctions.RenderSquareFromCorner(wallImage,
					wall.getSprite().getWidth(), wall.getSprite().getHeight(), (float) 0, (float) 0,
					WORLD_HEIGHT, WORLD_WIDTH - WORLD_WIDTH / 4);
		}

		for (Wall wall : walls) {
			wall.getSprite().draw(game.batch);
		}

		if (missileFlag == true) {
			int playerAttack = ((Attributes) testPlayer.getComponent("Attributes").get()).getAttack();
			if (Utilities.CollisonCheck(enemySpri.getSprite(), missile)) {

				Health testEnemyHealth = (Health) (testEnemy.getComponent("Health").get());
				testEnemyHealth.removeHealth((int) (playerAttack * delta));
				testEnemyHb.reduceHealth();
				missileFlag = false;
			}

			for (int i = 0; i < walls.size(); i++) {
				if (Utilities.CollisonCheck(missile, walls.get(i).getSprite())) {
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
		Polygon enemyAttackTriangle = testEnemyControls.attack();
		int enemyAttack = ((Attributes) testEnemy.getComponent("Attributes").get()).getAttack();

		if (Utilities.PolySpriteCollisionCheck(enemyAttackTriangle, spri.getSprite())) {
			Health playerHealth = (Health) (testPlayer.getComponent("Health").get());
			playerHealth.removeHealth((int) (enemyAttack * delta));
			testPlayerHb.reduceHealth();

			drawPolygon(healthImage, enemyAttackTriangle, enemySpri.getSprite());
		}

		// -------- CHECK FOR PLAYER ATTACKS --------


		if (playerAttackFlag == true) {

			int playerAttack = ((Attributes) testPlayer.getComponent("Attributes").get()).getAttack();

			drawPolygon(healthImage, playerAttackTriangle, spri.getSprite());

			if (Utilities.PolySpriteCollisionCheck(playerAttackTriangle, enemySpri.getSprite())) {
				Health testEnemyHealth = (Health) (testEnemy.getComponent("Health").get());
				testEnemyHealth.removeHealth((int) (playerAttack * delta));
				testEnemyHb.reduceHealth();
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
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {

	}

	private void listen(float delta) {

		Sprite playerSprite = ((SpriteComp) testPlayer.getComponent("Sprite").get()).getSprite();
		boundaryFlag = false;

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {

			tpc.moveLeft();

			// Make sure character can't leave edge of screen
			if (playerSprite.getX() < 0) {
				boundaryFlag = true;
				tpc.moveRight();
			}
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getSprite())) {
					tpc.moveRight();
					break;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			tpc.moveRight();
			// Make sure character can't leave edge of screen
			if (playerSprite.getY() > WORLD_WIDTH - playerSprite.getWidth()) {
				boundaryFlag = true;
				tpc.moveLeft();

			}
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getSprite())) {
					tpc.moveLeft();
					break;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			// Make sure character can't leave edge of screen
			tpc.moveUp();
			if (playerSprite.getY() > WORLD_HEIGHT - playerSprite.getHeight()) {
				boundaryFlag = true;
				tpc.moveDown();
			}

			// Make sure wall can't be passed
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getSprite())) {
					tpc.moveDown();
					break;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {

			controls.moveDown();
			tpc.moveDown();
			// Make sure character can't leave edge of screen
			if (playerSprite.getY() < 0) {
				boundaryFlag = true;
				tpc.moveUp();
			}

			// Make sure wall can't be passed
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}
				if (Utilities.CollisonCheck(playerSprite, walls.get(i).getSprite())) {
					tpc.moveUp();
				}
			}
		}

		if (Gdx.input.isKeyPressed(Keys.A)) {
			
			playerAttackTriangle = tpc.attack();
			playerAttackFlag = true;


		}

		if (Gdx.input.isKeyPressed(Keys.B)) {
			missile = tpc.rangedAttack(missileImage);
			missileFlag = true;

		}

	}


}
