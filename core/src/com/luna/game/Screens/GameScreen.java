package com.luna.game.Screens;

import java.util.List;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.luna.game.Components.ComponentNumberList;
import com.luna.game.Components.Health;
import com.luna.game.Components.Position;
import com.luna.game.Components.SpriteComp;
import com.luna.game.Components.Controls.PlayerControls;
import com.luna.game.Engine.Loader;
import com.luna.game.Engine.RenderingFunctions;
import com.luna.game.Engine.RenderingSystem;
import com.luna.game.Engine.TextureManager;
import com.luna.game.Engine.UI.MessageBox;
import com.luna.game.Entities.Enemy;
import com.luna.game.Entities.Player;
import com.luna.game.Entities.Wall;
import com.luna.game.Utilities.CollisionUtils;
import com.luna.game.Utilities.Constants;

public class GameScreen implements Screen {

	public int SCREEN_HEIGHT = Constants.SCREEN_HEIGHT;
	public int SCREEN_WIDTH = Constants.SCREEN_WIDTH;
	public int WORLD_HEIGHT = Constants.WORLD_HEIGHT;
	public int WORLD_WIDTH = Constants.WORLD_WIDTH;

	final ProjectLuna game;

	private Sprite backgroundSprite;

	private RenderingSystem renderSys;
	private boolean boundaryFlag;

	private PlayerControls pc;
	private Player player;
	private Enemy enemy;
	private Wall wall;
	private List<Wall> walls;

	private TextureManager textureManager;
	private MessageBox messageBox;
	Loader loader;

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	public GameScreen(final ProjectLuna game) {
		this.game = game;

		// -------- CREATE CAMERA --------
		renderSys = new RenderingSystem(game.batch);

		loader = new Loader();
		textureManager = TextureManager.getInstance();
		messageBox = new MessageBox();

		backgroundSprite = new Sprite(textureManager.getBlankSpace());

		backgroundSprite.setPosition(0, 0);
		backgroundSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

		// Walls
		//initWalls();

		// Test Text
		game.font.getData().setScale(WORLD_WIDTH / 700f, WORLD_HEIGHT / 700f);
		game.font.setUseIntegerPositions(false);

		// RENDER
		map = new TmxMapLoader().load("../assets/maps/TestMap.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1f);

		MapProperties prop = map.getProperties();

		int mapWidth = prop.get("width", Integer.class);
		int mapHeight = prop.get("height", Integer.class);
		//int tilePixelWidth = prop.get("tilewidth", Integer.class);
		//int tilePixelHeight = prop.get("tileheight", Integer.class);
		//int xWidth = mapWidth/tilePixelWidth;
		//int yHeight = mapHeight/tilePixelHeight;

		//int mapPixelWidth = mapWidth * tilePixelWidth;
		//int mapPixelHeight = mapHeight * tilePixelHeight;

		TiledMapTileLayer wallLayer = (TiledMapTileLayer)map.getLayers().get("Walls");
		walls = new ArrayList<>();
		 
		for(int x = 0; x < mapWidth; x++) {
			for(int y = 0; y < mapHeight; y++) {
				
				Cell cell = wallLayer.getCell(x, y);
				System.out.println(cell);
				if(cell != null) {
					Wall wall = new Wall("Basic Wall");
					float wallDimensions[] = new float[] {24f, 24f};
					wall.addComponent(new SpriteComp(textureManager.getFilledSpace(), new float[] {x*24, y*24},
							wallDimensions));

					wall.addComponent(new Position(x*24, y*24));

					walls.add(wall);
				}

			}
		}

		// Player
		player = loader.loadPlayer();
		player.getPosition().setPos(null);
		pc = new PlayerControls(player);
		
		// Enemy
		enemy = loader.loadDemon();
	}



	@Override
	public void render(float delta) {

		renderSys.update();

		renderer.setView(renderSys.getCamera());
		renderer.render();

		
		// -------- RENDER COORDINATE SYSTEM TO CAMERA --------
		//backgroundSprite.draw(game.batch);

		// -------- STATS RENDERING --------
	//	game.font.draw(game.batch, PlayerStatsBox.getStats(player),
	//			(float) (WORLD_WIDTH * (0.75f) + 2), (float) (WORLD_HEIGHT - 0.02 * WORLD_HEIGHT));

		// Message Box
		if (messageBox.getMessages() != null) {
	//		game.font.draw(game.batch, messageBox.getMessages(),
	//				(float) (WORLD_WIDTH * (0.75f) + 2),
	//				(float) (WORLD_HEIGHT - 0.1 * WORLD_HEIGHT), (float) (WORLD_WIDTH * .2f), 5,
	//				true); // float targetWidth, int halign, boolean wrap
		}

		// -------- DRAW PLAYER CHARACTER --------
		SpriteComp playerSpriteComponent = player.getSpriteComponent();
		playerSpriteComponent.getSprite().draw(game.batch);

		// -------- DRAW ENEMY CHARACTER --------
		SpriteComp enemySpriteComponent = enemy.getSpriteComponent();
	//	enemySpriteComponent.getSprite().draw(game.batch);

		// -------- DRAW WALLS --------
		for (Wall wall : walls) {
			wall.getSpriteComponent().getSprite().draw(game.batch);
		}

		game.batch.end();

		// -------- USER INPUT (KEYBOARD) --------
		listen(delta);

	}

	@Override
	public void resize(int width, int height) {
		Constants.updateConstants(width, height);
		renderSys.updateCamera(width, height);
		SCREEN_HEIGHT = Constants.SCREEN_HEIGHT;
		SCREEN_WIDTH = Constants.SCREEN_WIDTH;
		WORLD_HEIGHT = Constants.WORLD_HEIGHT;
		WORLD_WIDTH = Constants.WORLD_WIDTH;
		//updateObjects();
	}
	private void updateObjects() {
		game.font.getData().setScale(WORLD_WIDTH / 700f, WORLD_HEIGHT / 700f);
		backgroundSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
		initWalls();
		player = loader.loadPlayer();
		enemy = loader.loadDemon();
		
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

		Sprite playerSprite = ((SpriteComp) player.getComponent(ComponentNumberList.SPRITECOMP)).getSprite();
		boundaryFlag = false;


		if (Gdx.input.isKeyPressed(Keys.I)) {
			pc.openInventory(this.game);
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {

			pc.moveLeft();

			// Make sure character can't leave edge of screen
			if (playerSprite.getX() < 0) {
				boundaryFlag = true;
				pc.moveRight();
			}

			// Make sure wall can't be passed
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				Sprite wallSprite =
						(((SpriteComp) walls.get(i).getComponent(ComponentNumberList.SPRITECOMP)).getSprite());
				if (CollisionUtils.CollisonCheck(playerSprite, wallSprite)) {
					pc.moveRight();
					break;
				}
			}

			// Make sure enemy can't be passed
			if (CollisionUtils.CollisonCheck(playerSprite,
					enemy.getSpriteComponent().getSprite())) {
				pc.moveRight();
				combatTurn(delta);
			}

		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			pc.moveRight();
			// Make sure character can't leave edge of screen
			if (playerSprite.getY() > WORLD_WIDTH - playerSprite.getWidth()) {
				boundaryFlag = true;
				pc.moveLeft();

			}

			// Make sure wall can't be passed
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				Sprite wallSprite =
						(((SpriteComp) walls.get(i).getComponent(ComponentNumberList.SPRITECOMP)).getSprite());
				if (CollisionUtils.CollisonCheck(playerSprite, wallSprite)) {
					pc.moveLeft();
					break;
				}
			}

			// Make sure enemy can't be passed
			if (CollisionUtils.CollisonCheck(playerSprite,
					enemy.getSpriteComponent().getSprite())) {
				pc.moveLeft();
				combatTurn(delta);
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
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				Sprite wallSprite =
						(((SpriteComp) walls.get(i).getComponent(ComponentNumberList.SPRITECOMP)).getSprite());
				if (CollisionUtils.CollisonCheck(playerSprite, wallSprite)) {
					pc.moveDown();
					break;
				}
			}

			// Make sure enemy can't be passed
			if (CollisionUtils.CollisonCheck(playerSprite,
					enemy.getSpriteComponent().getSprite())) {
				pc.moveDown();
				combatTurn(delta);
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
			for (int i = 0; i < walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				Sprite wallSprite =
						(((SpriteComp) walls.get(i).getComponent(ComponentNumberList.SPRITECOMP)).getSprite());
				if (CollisionUtils.CollisonCheck(playerSprite, wallSprite)) {
					pc.moveUp();
				}
			}

			// Make sure enemy can't be passed
			if (CollisionUtils.CollisonCheck(playerSprite,
					enemy.getSpriteComponent().getSprite())) {
				pc.moveUp();
				combatTurn(delta);
			}
		}
	}

	private void combatTurn(float delta) {

		int enemyAttack = enemy.getAttributes().getAttack();
		Health playerHealth = player.getHealth();
		playerHealth.removeHealth((int) (enemyAttack * delta));
		messageBox.addMessage(String.format("%s hit %s for %s damage points!", enemy.getName(),
				player.getName(), delta * enemyAttack));


		int playerAttack = player.getAttributes().getAttack();
		Health enemyHealth = enemy.getHealth();
		enemyHealth.removeHealth((int) (playerAttack * delta));
		messageBox.addMessage(String.format("%s hit %s for %s damage points!", player.getName(),
				enemy.getName(), delta * playerAttack));

	}

	private void initWalls() {

		wall = new Wall("Basic Wall");
		float wallDimensions[] = new float[] {WORLD_WIDTH / 20f, WORLD_HEIGHT / 20f};
		wall.addComponent(new Position(0, 0));
		wall.addComponent(new SpriteComp(textureManager.getFilledSpace(), new float[] {0, 0},
				wallDimensions));
		SpriteComp wallSpriteComponent = wall.getSpriteComponent();


		walls = RenderingFunctions.RenderSquareFromCorner(wallSpriteComponent.getSprite(),
				wallSpriteComponent.getSprite().getWidth(),
				wallSpriteComponent.getSprite().getHeight(), (float) 0, (float) 0, WORLD_HEIGHT,
				WORLD_WIDTH - WORLD_WIDTH / 4);

	}
}
