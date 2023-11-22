package com.luna.game.Entities;


public class Player extends Entity {

    private static Player player;

    public Player(String name) {
        super(name);
    }

    public static Player getInstance(String name) {
        if (player == null)
            player = new Player(name);

        return player;
    }

    public static Player getInstance() {
        if (player == null)
            player = new Player("Player 1");

        return player;
    }
}
