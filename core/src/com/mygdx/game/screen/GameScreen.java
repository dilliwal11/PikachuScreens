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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entity.Obstacles;
import com.mygdx.game.entity.Player;
import com.mygdx.game.util.ViewportUtils;

import java.util.logging.Handler;

public class GameScreen implements Screen {

 private OrthographicCamera camera;
 private Viewport viewport;
 private ShapeRenderer renderer;

 private Player player;
 private Array<Obstacles> obstacles = new Array<Obstacles>();
 private float obstacleTimer;

    public static final float WORLD_WIDTH = 6.0f; // world units
    public static final float WORLD_HEIGHT = 10.0f; // world units
    public static final float OBSTACLE_SPAWN_TIME = 0.25F;

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
        updateObstacles(delta);
    }

   private void updatePlayer (){

        player.update();

        blockPlayerFromLeaving();
   }

   private void blockPlayerFromLeaving(){
        float playerX = player.getX();

        if(playerX < 0){
            playerX = 0;
        }
        else if (playerX > WORLD_WIDTH){
            playerX = WORLD_WIDTH;
        }

        player.setPosition(playerX,player.getY());



   }

   private void updateObstacles(float delta){

        for(Obstacles obstacles : obstacles){
            obstacles.update();
        }

        createNewObstacle(delta);

   }


   private void createNewObstacle(float delta){

        obstacleTimer += delta;


        if(obstacleTimer >= OBSTACLE_SPAWN_TIME){

            float min = 0f;
            float max = WORLD_WIDTH;

            float obstacleX = MathUtils.random(min,max);
            float obstacleY = WORLD_HEIGHT;

            Obstacles obstacle = new Obstacles();
            obstacle.setPosition(obstacleX,obstacleY);

            obstacles.add(obstacle);
            obstacleTimer = 0f;


        }


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

        for(Obstacles obstacle:obstacles){
            obstacle.drawDebug(renderer);
        }

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
