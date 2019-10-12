package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Obstacles {


    private static final float BOUNDS_RADIUS = 0.3f;
    public static final float SIZE = 2*BOUNDS_RADIUS;
    private static final float MAX_X_SPEED = 0.25f;
    // Viewport viewport;

    private float x;
    private float y;



    private float widthNew = 1;
    private float heightNew = 1;
    private float ySpeeds =0.1f;
    private boolean hit;

    private Circle bounds;



    public Obstacles(){
        bounds = new Circle(x,y,BOUNDS_RADIUS);
        setSize(SIZE,SIZE);
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

    public float getHeight(){
        return SIZE;
    }


    public void update(){

        setPosition(x,y-ySpeeds);

    }

    public void setSize(float width, float height){
        this.widthNew = width;
        this.heightNew = height;
        updateBounds();
    }

    public void updateBounds(){
        float halfWidth = widthNew/2f;
        float halfHeight = heightNew/2f;

        bounds.setPosition(x+halfWidth,y+halfHeight);
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public boolean isPlayerColliding(Player player) {

        Circle playerBounds = player.getBounds();
        boolean overlaps =  Intersector.overlaps(playerBounds,getBounds());

//        if(overlaps){
//            hit = true;
//        }

        hit = overlaps;

        return overlaps;

    }

    public boolean isNotHit (){return  !hit;}

    public Circle getBounds() {
        return bounds;
    }

}
