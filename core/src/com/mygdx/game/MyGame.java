package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class MyGame extends Game {


	Sprite sprite;
    SpriteBatch batch;
    Texture img;
    float x;
    float y;

	public void create (){


		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		sprite = new Sprite(img);
		sprite.setPosition(Gdx.graphics.getWidth()/2-sprite.getWidth()/2,Gdx.graphics.getHeight()/2-sprite.getWidth()/2);

		/*shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		pos = new Vector3(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
*/


	}

	public void render(){

		if(Gdx.input.isTouched()){

			sprite.setPosition(Gdx.input.getX(),Gdx.graphics.getBackBufferHeight()-Gdx.input.getY());
		}
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();
		batch.draw(sprite,sprite.getX(),sprite.getY());
		batch.end();




		/*camera.update();

		if(Gdx.input.isTouched()) {

		pos.set(Gdx.input.getX(),Gdx.input.getY(),0);

		camera.unproject(pos);

		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)){

			//pos.set(25,25);
		}



		Gdx.gl.glClearColor(1,1,1,1);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.GRAY);
		shapeRenderer.circle(pos.x,pos.y,64);
		shapeRenderer.end();*/

	}

	public void dispose (){
		//shapeRenderer.dispose();
		img.dispose();
	}



}
