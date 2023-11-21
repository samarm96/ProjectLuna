package com.luna.game.Engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.luna.game.Entities.Enemy;
import com.luna.game.Entities.Player;
import com.luna.game.Utilities.DataUtilities;
import com.luna.game.Utilities.Constants;

public class Loader {

    public Loader(){

    }

    public Player loadPlayer() {
        return DataUtilities.loadPlayer();
    }

    public Enemy loadDemon(){
        return DataUtilities.loadEnemies("Demon");
    }

	public OrthographicCamera loadCamera(){
		OrthographicCamera camera = new OrthographicCamera(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		return camera;
	}
}
