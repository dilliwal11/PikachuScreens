package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class MyGame extends Game {


	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private Vector3 pos;
SpriteBatch batch;
Texture img;
float x;
float y;

	public void create (){
		this.create();

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");


		/*shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		pos = new Vector3(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
*/


	}

	public void render(){
this.render();

		Gdx.gl.glClearColor(1,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(Gdx.input.isTouched()){

			y = y+4;
		}
		batch.begin();
		batch.draw(img,x,y);
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
	}



}
