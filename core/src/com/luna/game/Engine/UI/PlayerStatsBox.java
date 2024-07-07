package com.luna.game.Engine.UI;

import com.luna.game.Entities.Player;

public class PlayerStatsBox {
    
    public PlayerStatsBox() {
    }

    public static String getStats(Player player) {
        StringBuffer sb = new StringBuffer();

        sb.append("Name: " + player.getName() + "\n");
        sb.append(String.format(
            "Health: %s/%s\n",
            player.getHealth().getHealth(),player.getHealth().getMaxHealth()));
            
        sb.append(
            "Attack: " + player.getAttributes().getAttack() + "\n\n");

        return sb.toString();
    }

}
