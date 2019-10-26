package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.HighScoreScreen;
import com.mygdx.game.screen.MenuScreen;

public class MyGame extends Game {



	private SpriteBatch spriteBatch;


	public void create() {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);





		spriteBatch = new SpriteBatch();


		setScreen(new HighScoreScreen(this));


	}

	@Override
	public void dispose() {

		spriteBatch.dispose();
	}




	public SpriteBatch getSpriteBatch(){
		return spriteBatch;
	}



}
