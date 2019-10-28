package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGame;
import com.mygdx.game.entity.Obstacles;
import com.mygdx.game.entity.Player;


public class GameScreen implements Screen {

 private MyGame myGame;
 private AssetManager assetManager;


    private OrthographicCamera camera;
 private OrthographicCamera hudCamera;
 private Viewport viewport;
 private  Viewport hudViewPort;
 private final   SpriteBatch batch;
 private BitmapFont font;
 private final GlyphLayout layout = new GlyphLayout();

 private ShapeRenderer renderer;

 private Player player;
 private Array<Obstacles> obstacles = new Array<Obstacles>();
 private float obstacleTimer;

 private int lives = 3;
 private int score = 0;

 //private boolean alive = true;

    public static final float WORLD_WIDTH = 6.0f; // world units
    public static final float WORLD_HEIGHT = 10.0f; // world units
    public static final float OBSTACLE_SPAWN_TIME = GameManager.INSTANCE.getSpeed();



    public static final float HUD_WIDTH = 480f;
    public static final float HUD_HEIGHT = 800f;
    public static final int LIVES_START =3;


    public static final String UI_FONT = "ui_font_32.fnt";




    private Texture playerTexture;
    private Texture obstacleTexture;




    public GameScreen(MyGame myGame) {
        this.myGame = myGame;

        this.batch = myGame.getSpriteBatch();

    }




    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT,camera);
        renderer = new ShapeRenderer();
        player = new Player();


        hudCamera = new OrthographicCamera();
        hudViewPort = new FitViewport(HUD_WIDTH,HUD_HEIGHT,hudCamera);

        font = new BitmapFont(Gdx.files.internal(UI_FONT));

        playerTexture = new Texture(Gdx.files.internal("player.png"));
        obstacleTexture = new Texture(Gdx.files.internal("obstacle.png"));


        float startPlayerX = WORLD_WIDTH/2;
        float startPlayerY = 1;

        player.setPosition(startPlayerX,startPlayerY);




    }

    @Override
    public void render(float delta) {


        if(isGameOver()){

            System.out.println("game over");
            GameManager.INSTANCE.updateHighScore(score);
            restartGame(delta);
            return;
        }

        player.update();

        updateObstacles(delta);

        if(isPlayerColiding()){
            System.out.println("collision detected");
            lives--;
        }

        Gdx.gl.glClearColor(180.0f/255.0f, 200f/255.0f, 200.0f/255.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


            renderGamePLay();


            if (Gdx.input.isTouched()) {


                Vector2 screenTouch = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                Vector2 worldTouch = viewport.unproject(new Vector2(screenTouch));
                System.out.println("worldTouch" + worldTouch);

                worldTouch.x = MathUtils.clamp(worldTouch.x, 0, WORLD_WIDTH - player.getWidth());
                player.setPosition(worldTouch.x, 1);

            }


            renderUi();



    }



    private void restartGame(float delta){
        obstacles.clear();
        myGame.setScreen(new MenuScreen(myGame));


        lives = 0;




    }





private boolean isGameOver(){
        return lives <=-1;
}





    private boolean isPlayerColiding(){


        for(Obstacles obstacle: obstacles){

            if (obstacle.isNotHit() && obstacle.isPlayerColliding(player)) {
                return true;
            }
        }


        return false;
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

    private void renderGamePLay ()
    {
        viewport.apply();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        batch.draw(playerTexture,player.getX(),player.getY(),player.getWidth(),player.getHeight());

       // score =0;

        for(Obstacles obstacle: obstacles)
        {
            score= obstacles.size;
            System.out.println(obstacles.size);

            batch.draw(obstacleTexture,obstacle.getX(),obstacle.getY(),obstacle.getWidth()
            ,obstacle.getHeight());

        }

        batch.end();


    }


    private void renderUi(){
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();

        String livesText = "LIVES: " + lives;

        layout.setText(font,livesText);

        font.draw(batch,livesText,20,HUD_HEIGHT - layout.height);


        batch.end();
    }










    @Override
    public void resize(int width, int height) {
            viewport.update(width,height,true);
            hudViewPort.update(width,height,true);

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

        renderer.dispose();

        font.dispose();
        playerTexture.dispose();
        obstacleTexture.dispose();

    }
}
