package com.luna.game.Engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.luna.game.Entities.HostileNpc;
import com.luna.game.Entities.Player;

public class Loader {

    final static int SCREEN_HEIGHT = Utilities.SCREEN_HEIGHT;
	final static int SCREEN_WIDTH = Utilities.SCREEN_WIDTH;
	final int WORLD_HEIGHT = Utilities.WORLD_HEIGHT;
	final int WORLD_WIDTH = Utilities.WORLD_WIDTH;

    public Loader(){

    }

    public Player loadPlayer() {
        return DataUtilities.loadPlayer();
    }

    public HostileNpc loadDemon(){
        return DataUtilities.loadEnemies("Demon");
    }

	public OrthographicCamera loadCamera(){
		OrthographicCamera camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		return camera;
	}
}
