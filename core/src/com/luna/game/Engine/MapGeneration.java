package com.luna.game.Engine;

import java.util.ArrayList;
import java.util.List;
import com.luna.game.Entities.HostileNpc;

public class MapGeneration {
    
    public MapGeneration(){

    }

    public List<HostileNpc> spawnEnemies(String name, int number){
        List<HostileNpc> enemies = new ArrayList<>();

        for(int i = 0;i<number;i++){
            enemies.add(DataUtilities.loadEnemies(name));
        }

        return enemies;

    }

}
