package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.MyGame;

public class GameManager {


    public static final GameManager INSTANCE = new GameManager();

    private int highscore;
    private static final  String HIGH_SCORE_KEY = "highscore";

    private Preferences PREFS;


    private GameManager(){
        PREFS = Gdx.app.getPreferences(MyGame.class.getSimpleName());
        highscore = PREFS.getInteger(HIGH_SCORE_KEY,0);


    }


    public void updateHighScore (int score){
        if(score<highscore){
            return;
        }

        highscore = score;
        PREFS.putInteger(HIGH_SCORE_KEY,highscore);
        PREFS.flush();



    }

    public String getHighScoreString(){
        return String.valueOf(highscore);
    }






}
