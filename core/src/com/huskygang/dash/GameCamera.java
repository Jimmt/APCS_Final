package com.huskygang.dash;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;


/**
 * Custom game camera
 *
 * @author Austin Hsieh
 * @version May 23, 2016
 * @author Period: 5
 * @author Assignment: Dash-core
 *
 * @author Sources:
 */
public class GameCamera extends OrthographicCamera
{
    private Sprite followSprite;

    private float distance;


    /**
     * Initializes scaled game camera
     * 
     * @param followSprite
     *            sprite to follow
     */
    public GameCamera( Sprite followSprite )
    {
        this.followSprite = followSprite;
        viewportWidth = Constants.WIDTH * Constants.WORLD_TO_BOX;
        viewportHeight = Constants.HEIGHT * Constants.WORLD_TO_BOX;
        position.set( viewportWidth / 2, viewportHeight / 2, 0 );
    }


    /**
     * Update method to be called from game loop
     */
    public void update()
    {
        super.update();
        // followSprite.getY() + followSprite.getHeight() / 2
        // position.set( followSprite.getX() + followSprite.getWidth() / 2,
        // viewportHeight / 2, 0 );
        translate( 3 / 60f, 0 );
        distance += 3 / 60f;
    }


    /**
     * Getter for distance traveled
     * 
     * @return distance traveled
     */
    public float getDistance()
    {
        return distance;
    }
}
