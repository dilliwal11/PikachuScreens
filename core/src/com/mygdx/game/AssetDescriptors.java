package com.mygdx.game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetDescriptors {


    public static final AssetDescriptor<TextureAtlas> UI =
            new AssetDescriptor<TextureAtlas>("obstacle.png", TextureAtlas.class);

    private AssetDescriptors() {}



}
