package com.luna.game.Entities.items;

public enum ItemAttribute {
    CONSUMABLE(1), EQUIPPABLE(2), STACKABLE(4);

    private int attribute;

    ItemAttribute(int attribute) {
        this.attribute = attribute;
    }

    public int getValue() {
        return attribute;
    }

}