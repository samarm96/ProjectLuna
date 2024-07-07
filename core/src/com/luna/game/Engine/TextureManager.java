package com.luna.game.Engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class TextureManager {
    
    // 384 x 384
    // 24 x 24 pixels 
    public static final String DEFAULT_PATH = "../assets/Curses_square_24x24.png";
    
    private int width;
    private int height;

    private String textureFilePath;
    private Texture tileset;

    private static TextureManager manager;
    public static TextureManager getInstance() {
        if (manager == null)
            manager = new TextureManager(DEFAULT_PATH, 24, 24);
 
        return manager;
    }

    protected TextureManager(String filePath, int height, int width) {
        this.textureFilePath = filePath;
        this.height = height;
        this.width = width;
        loadTileset();
    }



    private void loadTileset() {
        
		tileset = new Texture(textureFilePath);
        loadTiles();


    }

    private TextureRegion blankSpace;
    private TextureRegion happyFaceUnfilled;
    private TextureRegion happyFaceFilled;
    private TextureRegion fancyPi;
    private TextureRegion heart;
    private TextureRegion filledSpace;

    private void loadTiles() {
        blankSpace = new TextureRegion(tileset, width*0, height*0, width, height);
        happyFaceUnfilled = new TextureRegion(tileset, width*1, height*0, width, height);
        happyFaceFilled = new TextureRegion(tileset, width*2, 0*height, width, height);
        heart = new TextureRegion(tileset, width*3, height*0, width, height);
        fancyPi = new TextureRegion(tileset, width*4, height*1, width, height);
        filledSpace = new TextureRegion(tileset, width*11, height*13, width, height);
    }

    public TextureRegion getBlankSpace() {
        return blankSpace;
    }

    public TextureRegion getHappyFaceUnfilled() {
        return happyFaceUnfilled;
    }

    public TextureRegion getHappyFaceFilled() {
        return happyFaceFilled;
    }

    public TextureRegion getFancyPi() {
        return fancyPi;
    }

    public TextureRegion getFilledSpace() {
        return filledSpace;
    }
 
    public TextureRegion getHeart() {
        return heart;
    }
    
}
