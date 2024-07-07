package com.luna.game.Entities.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.luna.game.Entities.Entity;

public class Item extends Entity {
    
    private int itemAttributes;
    private int itemUseType;
    private int itemUseTypeValue;
    private ItemTypeId itemTypeID;
    private String itemShortDescription;
    private int itemValue;

    public Item(
        TextureRegion textureRegion, 
        int itemAttributes, 
        ItemTypeId itemTypeID,
        int itemUseType, 
        int itemUseTypeValue, 
        int itemValue) {

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

    public ItemTypeId getItemTypeID() {
        return itemTypeID;
    }

    public void setItemTypeID(ItemTypeId itemTypeID) {
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
