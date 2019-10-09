package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entity.Player;
import com.mygdx.game.util.ViewportUtils;

import java.util.logging.Handler;

public class GameScreen implements Screen {

 private OrthographicCamera camera;
 private Viewport viewport;
 private ShapeRenderer renderer;

 private Player player;

    public static final float WORLD_WIDTH = 6.0f; // world units
    public static final float WORLD_HEIGHT = 10.0f; // world units


    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT,camera);
        renderer = new ShapeRenderer();
        player = new Player();


        float startPlayerX = WORLD_WIDTH/2;
        float startPlayerY = 1;

        player.setPosition(startPlayerX,startPlayerY);




    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        if(Gdx.input.isTouched()){


            Vector2 screenTouch = new Vector2(Gdx.input.getX(),Gdx.input.getY());
            Vector2 worldTouch = viewport.unproject(new Vector2(screenTouch));
            System.out.println("worldTouch" + worldTouch);

            worldTouch.x = MathUtils.clamp(worldTouch.x,0,WORLD_WIDTH - player.getWidth());
            player.setPosition(worldTouch.x,1);
                   // xSpeed = MAX_X_SPEED;
        }

            update(delta);
        renderDebug();

    }


    private void update (float delta){
        updatePlayer();
    }

   private void updatePlayer (){

        player.update();
   }






    private void renderDebug (){
        ViewportUtils.drawGrid(viewport,renderer);

        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        drawDebug();


        renderer.end();


    }



    private void drawDebug(){

        player.drawDebug(renderer);

    }



    @Override
    public void resize(int width, int height) {
            viewport.update(width,height,true);
            ViewportUtils.debugPixelPerUnit(viewport);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
