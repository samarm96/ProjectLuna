package com.luna.game.Engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import com.badlogic.gdx.utils.Json;
import com.luna.game.DataFiles.EnemyData;
import com.luna.game.DataFiles.PlayerData;
import com.luna.game.Entities.Enemy;
import com.luna.game.Entities.Player;


public class DataUtilities {


    /**
     * Writes the player data to a JSON file
     * @param player
     */
    public static void savePlayer(final Player player) {
        Json json = new Json();
        System.out.println(json.prettyPrint(player));
        try {
            FileWriter fileWriter = new FileWriter(
                "../core/src/com/luna/game/DataFiles/player.json");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(json.prettyPrint(player));
            printWriter.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads player data from a JSON file.
     * @return
     */
    public static Player loadPlayer() {
        Json json = new Json();
        String x = "";
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("../core/src/com/luna/game/DataFiles/player.json"));
            x = reader.lines().collect(Collectors.joining());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PlayerData data = json.fromJson(PlayerData.class, x);

        return data.createPlayer();
    }

    /**
     * Reads a json file and generates an enemy from them.
     * @param name
     * @return
     */
    public static Enemy loadEnemies(String name) {
        String filepath = "../core/src/com/luna/game/DataFiles/" + name + ".json";
        Json json = new Json();
        String x = "";
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(filepath));
            x = reader.lines().collect(Collectors.joining());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EnemyData data = json.fromJson(EnemyData.class, x);

        return data.create();
    }
}
