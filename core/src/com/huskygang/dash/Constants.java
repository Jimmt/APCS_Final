package com.huskygang.dash;

import com.badlogic.gdx.Gdx;


public class Constants
{
    public static int WIDTH = Gdx.graphics.getWidth();

    public static int HEIGHT = Gdx.graphics.getHeight();

    public static float WORLD_TO_BOX = 1 / 100f;

    public static float BOX_TO_WORLD = 100f;

    public static float SCL_WIDTH = WIDTH * WORLD_TO_BOX;

    public static float SCL_HEIGHT = HEIGHT * WORLD_TO_BOX;
}
