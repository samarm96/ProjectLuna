package com.luna.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.luna.game.Entities.Player;

public class InventoryScreen implements Screen {

    ProjectLuna game;
    private Stage stage;
    private Player player;
    public InventoryScreen(ProjectLuna game) {
        player = Player.getInstance();
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Skin UISkin = new Skin(Gdx.files.internal("uiskin.json"));

        Label title = new Label("Inventory", UISkin);
        Label dpLabel = new Label("Defense: ", UISkin);
        Label apLabel = new Label("Attack : ", UISkin);
        Label apVal = new Label(String.valueOf(player.getAttributes().getAttack()), UISkin);

        Table labelTable = new Table();
        labelTable.setPosition(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4);
        labelTable.add(title).align(Align.left);
        labelTable.row();
        labelTable.row();
        labelTable.add(dpLabel).align(Align.left);
        labelTable.row();
        labelTable.row();
        labelTable.add(apLabel).align(Align.left);
        labelTable.add(apVal).align(Align.left);
        stage.addActor(labelTable);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }

}
