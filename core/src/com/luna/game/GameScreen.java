package com.luna.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

	final static int SCREEN_HEIGHT = Gdx.graphics.getHeight();
	final static int SCREEN_WIDTH = Gdx.graphics.getWidth();

	final ProjectLuna game;

	OrthographicCamera camera;

	Player player;
	Sprite playerSprite;
    Texture playerSpriteTexture;
	Texture wallImage;
	float lastPlayerY;
	float lastPlayerX;
	Wall wall;
	List<Wall> walls;

	public GameScreen(final ProjectLuna game) {
		this.game = game;

        playerSpriteTexture = new Texture("../assets/kirby.jpg");
		wallImage = new Texture("../assets/wall.jpg");

		// -------- CREATE CAMERA --------
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// -------- REPRESENT PLAYER AS A RECTANGLE

		float[] location = new float[]{SCREEN_WIDTH / 4, SCREEN_HEIGHT/ 4}; // Sets the initial location of the character
		float[] dimensions = new float[]{40, SCREEN_HEIGHT/20}; // Set width/height of player character

		player = new Player(playerSpriteTexture, location, dimensions);
		playerSprite = player.getSprite();


		// -------- REPRESENT WALLS AS RECTANGLES --------
		wall = new Wall(wallImage);
		float wallDimensions[] = new float[]{40,SCREEN_HEIGHT/20};
		wall.setDimensions(wallDimensions);
		walls = RenderingFunctions.RenderSquareFromCorner(wallImage, wall.getWidth(), wall.getHeight(), (float) 0, (float) 0, SCREEN_HEIGHT-1, SCREEN_WIDTH-SCREEN_WIDTH/4);

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
		game.batch.draw(playerSprite, playerSprite.getX(), playerSprite.getY());
		
		// -------- DRAW WALLS --------
		for(int i = 0; i < walls.size();i++){
			game.batch.draw(wallImage, walls.get(i).getLocation()[0], walls.get(i).getLocation()[1]);
		}
		game.batch.end();

		// -------- USER INPUT (KEYBOARD) --------
		if (Gdx.input.isKeyPressed(Keys.LEFT)){
			lastPlayerX = playerSprite.getX();
			playerSprite.translateX(-1*24 * Gdx.graphics.getDeltaTime());
			// Make sure character can't leave edge of screen
			if (playerSprite.getX() < 0){
				playerSprite.setX(0);
			}
			for(int i = 0; i < walls.size();i++){
				if(Utilites.CollisonCheck(playerSprite.getBoundingRectangle(), walls.get(i).getWall().getBoundingRectangle())){
					playerSprite.setX(lastPlayerX);
				}				
			}

        }
		if (Gdx.input.isKeyPressed(Keys.RIGHT)){
			lastPlayerX = playerSprite.getX();
			playerSprite.translateX(24 * Gdx.graphics.getDeltaTime());

			// Make sure character can't leave edge of screen
			if (playerSprite.getY() > Gdx.graphics.getWidth() - playerSprite.getWidth()){
				playerSprite.setX(Gdx.graphics.getWidth() - playerSprite.getWidth());
			}
			for(int i = 0; i < walls.size();i++){
				if(Utilites.CollisonCheck(playerSprite.getBoundingRectangle(), walls.get(i).getWall().getBoundingRectangle())){
					playerSprite.setX(lastPlayerX);
				}				
			}      				
        }
		if (Gdx.input.isKeyPressed(Keys.UP)){
			lastPlayerY = playerSprite.getY();
			// Make sure character can't leave edge of screen
			if (playerSprite.getY() > Gdx.graphics.getHeight() - playerSprite.getHeight()){
				playerSprite.setY(Gdx.graphics.getHeight() - playerSprite.getHeight());
			}
			// Make sure wall can't be passed
			playerSprite.translateY(24 * Gdx.graphics.getDeltaTime());
			for(int i = 0; i < walls.size();i++){
				if(Utilites.CollisonCheck(playerSprite.getBoundingRectangle(), walls.get(i).getWall().getBoundingRectangle())){
					playerSprite.setY(lastPlayerY);
				}				
			}  		
        }
		if (Gdx.input.isKeyPressed(Keys.DOWN)){
			lastPlayerY = playerSprite.getY();
			playerSprite.translateY(-1*24 * Gdx.graphics.getDeltaTime());
			// Make sure character can't leave edge of screen
			if (playerSprite.getY() < 0){
				playerSprite.setY(0);
			}
			// Make sure wall can't be passed
			for(int i = 0; i < walls.size();i++){
				if(Utilites.CollisonCheck(playerSprite.getBoundingRectangle(), walls.get(i).getWall().getBoundingRectangle())){
					playerSprite.setY(lastPlayerY);
				}				
			}  	
		}

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

}