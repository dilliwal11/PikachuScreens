package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGame;

import javax.swing.text.View;

public class OptionScreens extends ScreenAdapter {


    public static final float HUD_WIDTH = 480f;
    public static final float HUD_HEIGHT = 800f;
    public static final String UI_FONT = "ui_font_32.fnt";

    private final MyGame myGame;
    GameScreen gameScreen;

    private Viewport viewport;
    private Stage stage;
    private ImageButton easy;
    boolean flag = false;


    Texture easyTexture, easyPressTexture ,mediumTexture, mediumPressTexture, back,backPress,background,hardchecked,easycheck;




    public OptionScreens(MyGame myGame) {
        this.myGame = myGame;
    }

    @Override
    public void show() {

        viewport = new FitViewport(HUD_WIDTH,HUD_HEIGHT);
        stage = new Stage(viewport,myGame.getSpriteBatch());

        Gdx.input.setInputProcessor(stage);

        createUi();

    }

    private void createUi() {


        Table table = new Table();
        table.defaults().pad(15);

        BitmapFont font = new BitmapFont(Gdx.files.internal(UI_FONT));
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);


        background = new Texture(Gdx.files.internal("background.png"));
        TextureRegion myTextureRegion = new TextureRegion(background);
        Image background = new Image(myTextureRegion);
        easyTexture = new Texture(Gdx.files.internal("easy.png"));
        easycheck = new Texture(Gdx.files.internal("easycheck.png"));
        easyPressTexture = new Texture(Gdx.files.internal("easypress.png"));
        hardchecked = new Texture(Gdx.files.internal("hardcheck.png"));

        mediumTexture = new Texture(Gdx.files.internal("hard.png"));
        mediumPressTexture = new Texture(Gdx.files.internal("hardpress.png"));
        back = new Texture(Gdx.files.internal("back.png"));
        backPress = new Texture(Gdx.files.internal("backpress.png"));



        Label label = new Label("DIFFICULTY",labelStyle);
        label.setPosition(HUD_WIDTH/2,HUD_HEIGHT/2+180, Align.center);



        TextureRegion easyRegion = new TextureRegion(easyTexture);
        TextureRegion mediumRegion = new TextureRegion(mediumTexture);
        TextureRegion backRegion = new TextureRegion(back);
        final ImageButton easy = new ImageButton(new TextureRegionDrawable(easyRegion), new TextureRegionDrawable(easyPressTexture),
                new TextureRegionDrawable(easycheck));
        easy.setPosition(HUD_WIDTH/2,HUD_HEIGHT/2+90,Align.center);



        final ImageButton medium = new ImageButton(new TextureRegionDrawable(mediumRegion),new TextureRegionDrawable(mediumPressTexture),
                new TextureRegionDrawable(hardchecked));
        medium.setPosition(HUD_WIDTH/2,HUD_HEIGHT/2,Align.center);





        medium.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


        //gameScreen.OBSTACLE_SPAWN_TIME = 0.25F;
                GameManager.INSTANCE.updateDiff(0.15f);




            }
        });

        easy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            GameManager.INSTANCE.updateDiff(1.25f);





            }
        });

       // TextureRegion checkRegion = new TextureRegion()

        ImageButton backButton = new ImageButton(new TextureRegionDrawable(backRegion),
                new TextureRegionDrawable(backPress));

        backButton.setPosition(HUD_WIDTH / 2, HUD_HEIGHT / 2 - 180, Align.center);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("back");
                myGame.setScreen(new MenuScreen(myGame));
            }
        });

        stage.addActor(background);
        stage.addActor(label);
        stage.addActor(easy);
        stage.addActor(medium);
        stage.addActor(medium);
        //stage.addActor(check);
        stage.addActor(backButton);



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
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
