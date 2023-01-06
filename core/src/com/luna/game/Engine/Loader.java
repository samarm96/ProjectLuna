package com.luna.game.Engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.luna.game.Entities.HostileNpc;
import com.luna.game.Entities.Player;

public class Loader {

    final static int SCREEN_HEIGHT = Utilities.SCREEN_HEIGHT;
	final static int SCREEN_WIDTH = Utilities.SCREEN_WIDTH;
	final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
	final int WORLD_WIDTH = Utilities.WORLD_WIDTH;

    public Loader(){

    }

    public Player loadPlayer(){

        Texture playerSpriteTexture = new Texture("../assets/kirby.jpg");

		// Sets the initial location of the character
		float[] location = new float[] {WORLD_WIDTH / 4, WORLD_HEIGHT / 4};

		// Set width/height of player character
		float[] playerDimensions = new float[] {WORLD_WIDTH / 20, WORLD_HEIGHT / 20};
		int playerMaxHealth = 100;
		Player player = new Player(playerSpriteTexture, location, playerDimensions, playerMaxHealth);
		player.setAttack(100);

        return player;

    }

    public HostileNpc loadDemon(){
		Texture demonImage = new Texture("../assets/demon.png");

		float[] demonLocation = new float[] {WORLD_WIDTH / 4, WORLD_HEIGHT / 2};
		float[] enemyDimensions = new float[] {WORLD_WIDTH / 20, WORLD_HEIGHT / 20};
		int enemyHealth = 100;
		HostileNpc enemy = new HostileNpc(demonImage, demonLocation, enemyDimensions, enemyHealth);
		enemy.setAttack(100);

        return enemy;

    }

	public OrthographicCamera loadCamera(){
		OrthographicCamera camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		return camera;
	}
}
