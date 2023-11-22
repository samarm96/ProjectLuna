package com.luna.game.Entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InventoryItem extends Entity {


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

    public enum ItemUseType {
        ITEM_RESTORE_HEALTH(1), ITEM_RESTORE_MP(2), ITEM_DAMAGE(4), WEAPON_ONEHAND(
                8), WEAPON_TWOHAND(16), WAND_ONEHAND(32), WAND_TWOHAND(64), ARMOR_SHIELD(
                        128), ARMOR_HELMET(
                                256), ARMOR_CHEST(512), ARMOR_FEET(1024), QUEST_ITEM(2048);

        private int itemUseType;

        ItemUseType(int itemUseType) {
            this.itemUseType = itemUseType;
        }

        public int getValue() {
            return itemUseType;
        }
    }
    public enum ItemTypeID {
        NONE
    }


    private int itemAttributes;
    private int itemUseType;
    private int itemUseTypeValue;
    private ItemTypeID itemTypeID;
    private String itemShortDescription;
    private int itemValue;

    public InventoryItem(TextureRegion textureRegion, int itemAttributes, ItemTypeID itemTypeID,
            int itemUseType, int itemUseTypeValue, int itemValue) {
        super(itemTypeID.name());

        this.itemTypeID = itemTypeID;
        this.itemAttributes = itemAttributes;
        this.itemUseType = itemUseType;
        this.itemUseTypeValue = itemUseTypeValue;
        this.itemValue = itemValue;
    }


    public int getItemUseTypeValue() {
        return itemUseTypeValue;
    }

    public void setItemUseTypeValue(int itemUseTypeValue) {
        this.itemUseTypeValue = itemUseTypeValue;
    }

    public int getItemValue() {
        return itemValue;
    }

    public void setItemValue(int itemValue) {
        this.itemValue = itemValue;
    }

    public ItemTypeID getItemTypeID() {
        return itemTypeID;
    }

    public void setItemTypeID(ItemTypeID itemTypeID) {
        this.itemTypeID = itemTypeID;
    }

    public int getItemAttributes() {
        return itemAttributes;
    }

    public void setItemAttributes(int itemAttributes) {
        this.itemAttributes = itemAttributes;
    }

    public int getItemUseType() {
        return itemUseType;
    }

    public void setItemUseType(int itemUseType) {
        this.itemUseType = itemUseType;
    }

    public String getItemShortDescription() {
        return itemShortDescription;
    }

    public void setItemShortDescription(String itemShortDescription) {
        this.itemShortDescription = itemShortDescription;
    }



}
