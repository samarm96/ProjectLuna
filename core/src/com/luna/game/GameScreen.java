package com.luna.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.luna.game.Components.Health;
import com.luna.game.Components.Position;
import com.luna.game.Components.SpriteComp;
import com.luna.game.Components.Controls.PlayerControls;
import com.luna.game.Engine.Loader;
import com.luna.game.Engine.RenderingFunctions;
import com.luna.game.Engine.RenderingSystem;
import com.luna.game.Engine.TextureManager;
import com.luna.game.Engine.Utilities;
import com.luna.game.Engine.UI.MessageBox;
import com.luna.game.Engine.UI.PlayerStatsBox;
import com.luna.game.Entities.Enemy;
import com.luna.game.Entities.Player;
import com.luna.game.Entities.Wall;

public class GameScreen implements Screen {

	public int SCREEN_HEIGHT = Utilities.SCREEN_HEIGHT;
	public int SCREEN_WIDTH = Utilities.SCREEN_WIDTH;
	public int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
	public int WORLD_WIDTH = Utilities.WORLD_WIDTH;

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
	
	public GameScreen(final ProjectLuna game) {
		this.game = game;

		final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
		final int WORLD_WIDTH = Utilities.WORLD_WIDTH;
		Loader loader = new Loader();
        textureManager = TextureManager.getInstance();        
		messageBox = new MessageBox();

		backgroundSprite = new Sprite(textureManager.getBlankSpace());

		backgroundSprite.setPosition(0, 0);
		backgroundSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

		// -------- CREATE CAMERA --------
		renderSys = new RenderingSystem(game.batch);

		// Player
		player = loader.loadPlayer();
		pc = new PlayerControls(player);

		// Enemy
		enemy = loader.loadDemon();
		
		// Walls
		initWalls();

		// Test Text
		game.font.getData().setScale(WORLD_WIDTH/700f, WORLD_HEIGHT/700f);
		game.font.setUseIntegerPositions(false);

	}

	private void initWalls() {

		wall = new Wall("Basic Wall");
		float wallDimensions[] = new float[] { WORLD_WIDTH / 20f, WORLD_HEIGHT / 20f};
		wall.addComponent(new Position(0, 0));
		wall.addComponent(new SpriteComp(textureManager.getFilledSpace(), new float[] { 0, 0 }, wallDimensions));
		SpriteComp wallSpriteComponent = wall.getSpriteComponent();

	
		walls = RenderingFunctions.RenderSquareFromCorner(
			wallSpriteComponent.getSprite(),
			wallSpriteComponent.getSprite().getWidth(), wallSpriteComponent.getSprite().getHeight(), (float) 0,
			(float) 0,
			WORLD_HEIGHT, WORLD_WIDTH - WORLD_WIDTH / 4);

	}

	@Override
	public void render(float delta) {

		renderSys.update();

		// -------- RENDER COORDINATE SYSTEM TO CAMERA --------
		backgroundSprite.draw(game.batch);

		// -------- STATS RENDERING --------
		game.font.draw(game.batch, PlayerStatsBox.getStats(player), (float) (WORLD_WIDTH * (0.75f) + 2), (float) (WORLD_HEIGHT-0.02*WORLD_HEIGHT));

		// Message Box
		if(messageBox.getMessages() != null) {
			game.font.draw(game.batch, messageBox.getMessages(), (float) (WORLD_WIDTH * (0.75f) + 2), (float) (WORLD_HEIGHT-0.1*WORLD_HEIGHT));
			
		}

		// -------- DRAW PLAYER CHARACTER --------
		SpriteComp playerSpriteComponent = player.getSpriteComponent();
		playerSpriteComponent.getSprite().draw(game.batch);

		// -------- DRAW ENEMY CHARACTER --------
		SpriteComp enemySpriteComponent = enemy.getSpriteComponent();
		enemySpriteComponent.getSprite().draw(game.batch);

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

		Utilities.SCREEN_WIDTH = width;
		Utilities.SCREEN_HEIGHT = height;
		Utilities.WORLD_WIDTH = width;
		Utilities.WORLD_HEIGHT = height;

		SCREEN_WIDTH = Utilities.SCREEN_WIDTH;
		SCREEN_HEIGHT = Utilities.SCREEN_HEIGHT;
		WORLD_HEIGHT = height;
		WORLD_WIDTH = width;

		renderSys.updateCamera();
		
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

			// Make sure wall can't be passed
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

			// Make sure enemy can't be passed
			if(Utilities.CollisonCheck(playerSprite, enemy.getSpriteComponent().getSprite())) {
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

			// Make sure enemy can't be passed
			if(Utilities.CollisonCheck(playerSprite, enemy.getSpriteComponent().getSprite())) {
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

			// Make sure enemy can't be passed
			if(Utilities.CollisonCheck(playerSprite, enemy.getSpriteComponent().getSprite())) {
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
			for (int i = 0; i <  walls.size(); i++) {
				if (boundaryFlag == true) {
					break;
				}

				Sprite wallSprite = (((SpriteComp)  walls.get(i).getComponent("Sprite").get()).getSprite());
				if (Utilities.CollisonCheck(playerSprite, wallSprite)) {
					pc.moveUp();
				}
			}

			// Make sure enemy can't be passed
			if(Utilities.CollisonCheck(playerSprite, enemy.getSpriteComponent().getSprite())) {
				pc.moveUp();
				combatTurn(delta);
			}
		}
	}

	private void combatTurn(float delta) {
			
			int enemyAttack = enemy.getAttributes().getAttack();
			Health playerHealth = player.getHealth();
			playerHealth.removeHealth((int) (enemyAttack * delta));
			messageBox.addMessage(String.format("%s hit %s for %s damage points!", enemy.getName(), player.getName(), delta*enemyAttack));


			int playerAttack = player.getAttributes().getAttack();
			Health enemyHealth = enemy.getHealth();
			enemyHealth.removeHealth((int) (playerAttack * delta));
			messageBox.addMessage(String.format("%s hit %s for %s damage points!", player.getName(), enemy.getName(), delta*playerAttack));

			System.out.println("Player Health: " + player.getHealth().getHealth());
			System.out.println("Enemy Health: " + enemy.getHealth().getHealth());
	}

}
