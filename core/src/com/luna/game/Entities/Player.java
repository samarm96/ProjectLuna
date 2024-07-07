package com.luna.game.Entities;


public class Player extends Entity {

    private static Player player;

    private Player(String name) {
        super(name);
    }

    public static Player getPlayer(String name) {
        if (player == null)
            player = new Player(name);

        return player;
    }

    public static Player getPlayer() {
        if (player == null)
            player = new Player("Player 1");

        return player;
    }
}
