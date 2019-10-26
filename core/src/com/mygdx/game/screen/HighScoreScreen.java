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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGame;

public class HighScoreScreen extends ScreenAdapter {

    public static final float HUD_WIDTH = 480f;
    public static final float HUD_HEIGHT = 800f;

    public static final String UI_FONT = "ui_font_32.fnt";

    private final MyGame myGame;

    private Viewport viewport;
    private Stage stage;


    Texture background;
    Texture back;
    Texture backPress;



    public HighScoreScreen(MyGame myGame) {
        this.myGame = myGame;
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
    public void show() {
      viewport = new FitViewport(HUD_WIDTH,HUD_HEIGHT);
      stage = new Stage(viewport, myGame.getSpriteBatch());


        Gdx.input.setInputProcessor(stage);

        createUi();



    }

    private void createUi() {

        Table table = new Table();

        BitmapFont font = new BitmapFont(Gdx.files.internal(UI_FONT));
        background = new Texture(Gdx.files.internal("background.png"));
        back = new Texture(Gdx.files.internal("back.png"));
        backPress = new Texture(Gdx.files.internal("backpress.png"));
        TextureRegion myTextureRegion = new TextureRegion(background);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        TextureRegion backRegion = new TextureRegion(back);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

        table.setBackground(myTexRegionDrawable);

        Label highScoreText = new Label("HIGHSCORE",labelStyle);
        Label highScoreLabel = new Label(GameManager.INSTANCE.getHighScoreString(),labelStyle);


        ImageButton backButton = new ImageButton(new TextureRegionDrawable(backRegion),
                new TextureRegionDrawable(backPress));

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("back");
                myGame.setScreen(new MenuScreen(myGame));
            }
        });


        Table contentTable = new Table();
        contentTable.defaults().pad(20);
        contentTable.setBackground(new TextureRegionDrawable(myTextureRegion));


        contentTable.add(highScoreText).row();
        contentTable.add(highScoreLabel).row();
        contentTable.add(backButton);

        contentTable.center();

        table.add(contentTable);
        table.center();
        table.setFillParent(true);

        table.pack();

        stage.addActor(table);





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
