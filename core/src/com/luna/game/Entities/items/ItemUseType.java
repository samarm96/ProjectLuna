package com.luna.game.Entities.items;

public enum ItemUseType {
    ITEM_RESTORE_HEALTH(1), 
    ITEM_RESTORE_MP(2), 
    ITEM_DAMAGE(4), 
    WEAPON_ONEHAND(8), 
    WEAPON_TWOHAND(16), 
    WAND_ONEHAND(32), 
    WAND_TWOHAND(64), 
    ARMOR_SHIELD(128), 
    ARMOR_HELMET(256), 
    ARMOR_CHEST(512), 
    ARMOR_FEET(1024), 
    QUEST_ITEM(2048);

    private int itemUseType;

    ItemUseType(int itemUseType) {
        this.itemUseType = itemUseType;
    }

    public int getValue() {
        return itemUseType;
    }
}
