package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Player {


    private static final float BOUNDS_RADIUS = 0.4f;
    public static final float SIZE = 2*BOUNDS_RADIUS;
    private static final float MAX_X_SPEED = 0.25f;
   // Viewport viewport;

    private float x;
    private float y;


    private Circle bounds;



    public Player(){
        bounds = new Circle(x,y,BOUNDS_RADIUS);
    }

    public void drawDebug(ShapeRenderer renderer){
        renderer.circle(bounds.x,bounds.y,bounds.radius,30);
    }

    public void setPosition (float x, float y){
        this.x = x;
        this.y =y;

        updateBounds();
    }


    public float getWidth(){
        return SIZE;
    }


    public void update(){

        float xSpeed = 0;



//         if(Gdx.input.isTouched(1) && Gdx.input.getX()<x){
//            xSpeed = -MAX_X_SPEED;
//        }
//
//        x += xSpeed;
        updateBounds();

    }



    public void updateBounds(){

        bounds.setPosition(x,y);
    }

}
