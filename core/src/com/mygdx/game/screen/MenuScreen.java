package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AssetDescriptors;
import com.mygdx.game.MyGame;

public class MenuScreen  implements Screen {



    public static final float HUD_WIDTH = 480f;
    public static final float HUD_HEIGHT = 800f;

    Texture background;
    Texture play;
    Texture playPress;
    Texture highscore;
    Texture highscorepress;


    private final MyGame myGame;


    private Viewport viewport;
    private Stage stage;


    public MenuScreen(MyGame game) {

        this.myGame = game;


    }

    @Override
    public void show() {

        viewport = new FitViewport(HUD_WIDTH,HUD_HEIGHT);

        stage = new Stage(viewport, myGame.getSpriteBatch());

        Gdx.input.setInputProcessor(stage);

        initUi();





        /*stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(button); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui*/



    }

    private void initUi() {

    Table table = new Table();
    Table buttonTable = new Table();
        background = new Texture(Gdx.files.internal("background.png"));
        play = new Texture(Gdx.files.internal("play.png"));
        playPress = new Texture(Gdx.files.internal("playpress.png"));
        highscore = new Texture(Gdx.files.internal(("highscore.png")));
        highscorepress = new Texture(Gdx.files.internal(("highscorepress.png")));
        TextureRegion myTextureRegion = new TextureRegion(background);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);


        table.setBackground(myTexRegionDrawable);


        TextureRegion playTextureRegion = new TextureRegion(play);
        TextureRegion highscoreTR = new TextureRegion(highscore);

        ImageButton playButton = new ImageButton (new TextureRegionDrawable(playTextureRegion),new TextureRegionDrawable(playPress));
        ImageButton highscoreButton = new ImageButton(new TextureRegionDrawable(highscoreTR), new TextureRegionDrawable(highscorepress));
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                System.out.println("play");

              myGame.setScreen(new GameScreen(myGame));
            }
        });


        highscoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                System.out.println("high");
                myGame.setScreen(new HighScoreScreen(myGame));

            }
        });





        buttonTable.defaults().pad(20);
        buttonTable.add(playButton).row();
        buttonTable.add(highscoreButton).row();
        buttonTable.center();



        table.add(buttonTable);
        table.center();
        table.setFillParent(true);
        table.pack();

        stage.addActor(table);


    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    dispose();
    }

    @Override
    public void dispose() {
stage.dispose();
    }
}
