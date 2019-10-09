package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ShapeRendererSample implements InputProcessor, ApplicationListener {




    public static final float WORLD_HEIGHT = 40f;
    public static final float WORLD_WIDTH = 20f;

    OrthographicCamera orthographicCamera;
    Viewport viewport;
    ShapeRenderer shapeRenderer;

    boolean drawCircle = true;
    boolean drawGrid = true;
    boolean drawRectangles = true;
    boolean drawPoints = true;



    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void create() {
        orthographicCamera = new OrthographicCamera();
       viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT, orthographicCamera);
       shapeRenderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width,height);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);

        if(drawGrid){
            drawGrid();
        }

    }

    public void drawGrid(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);

        int worldWidth = (int) WORLD_WIDTH;
        int worldHeight = (int) WORLD_HEIGHT;

        for (int x = -worldWidth; x<worldHeight;x++){

            shapeRenderer.line(x,-worldHeight,x,worldHeight);

        }

        for (int y = -worldHeight;y<worldWidth;y++){
            shapeRenderer.line(-worldWidth,y,worldWidth,y);
        }

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(-worldWidth,0.0f,worldWidth,0.0f);
        shapeRenderer.line(0.0f,-worldHeight,0.0f,worldHeight);
        shapeRenderer.end();

    }



    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
