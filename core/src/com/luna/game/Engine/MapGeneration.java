package com.luna.game.Engine;

import java.util.ArrayList;
import java.util.List;
import com.luna.game.Entities.Enemy;
import com.luna.game.Utilities.DataUtilities;

public class MapGeneration {
    
    public MapGeneration(){

    }

    public List<Enemy> spawnEnemies(String name, int number){
        List<Enemy> enemies = new ArrayList<>();

        for(int i = 0;i<number;i++){
            enemies.add(DataUtilities.loadEnemies(name));
        }

        return enemies;

    }

}
