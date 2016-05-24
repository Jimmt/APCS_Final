package com.huskygang.dash;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


/**
 * Generates the random objects that the player must jump and climb on.
 *
 * @author Austin Hsieh
 * @version May 23, 2016
 * @author Period: 5
 * @author Assignment: Dash-core
 *
 * @author Sources:
 */
public class WorldGenerator
{
    Array<Terrain> terrainList;

    World world;

    float terrainDistance = 3f, lastTerrainX = 0f;

    float edgeX = 4f, spawnY = 1f, targetSpawnY = 1f;

    int platformCounter = 0;

    GameLevel gameLevel;


    /**
     * Initializes the WorldGenerator with a physics World and a reference to
     * the level to check distance.
     * 
     * @param world
     *            the box2d world
     * @param gameLevel
     *            the game level
     */
    public WorldGenerator( World world, GameLevel gameLevel )
    {
        this.world = world;
        this.gameLevel = gameLevel;

        terrainList = new Array<Terrain>();
        spawnY = MathUtils.random( 0.5f, 1.5f );
    }


    /**
     * Generate terrain based on the edge of the camera viewport
     * 
     * @param camera
     *            the game camera
     */
    public void generate( GameCamera camera )
    {
        edgeX = camera.position.x;

        if ( edgeX - lastTerrainX >= terrainDistance )
        {

            lastTerrainX = edgeX;
            float w = MathUtils.random( 0.5f, 2.5f ), h = 0.7f;
            terrainDistance = MathUtils.random( w * 1.25f, w * 2.25f );

            platformCounter++;
            if ( platformCounter == 4 )
            {
                platformCounter = 0;
                targetSpawnY = MathUtils.random( 2f, Constants.SCL_HEIGHT - 1f );
            }
            if ( spawnY < targetSpawnY )
            {
                spawnY += 0.5f;
            }
            else
            {
                spawnY -= 0.5f;
            }
            
            Terrain terrain = new Terrain( edgeX + camera.viewportWidth / 2 + w / 2f, spawnY, w, h, false, world );
            randomizeAngularVelocity( terrain );
            randomizeLinearVelocity( terrain );
            terrainList.add( terrain );
        }
    }


    /**
     * Randomize the angular velocity of a terrain based on the distance.
     * 
     * @param terrain
     *            the terrain to set angular velocity on
     */
    public void randomizeAngularVelocity( Terrain terrain )
    {
        float magnitude = (float)Math.min( gameLevel.getDistance() / 100.0, 1 );
        terrain.body.setAngularVelocity( MathUtils.random( -magnitude, magnitude ) );
    }


    /**
     * Randomize the linear velocity of a terrain based on the distance.
     * 
     * @param terrain
     *            the terrain to set linear velocity on
     */
    public void randomizeLinearVelocity( Terrain terrain )
    {
        float magnitude = (float)Math.min( gameLevel.getDistance() / 50.0, 1 );
        terrain.body.setLinearVelocity( MathUtils.random( -0.5f * magnitude, 0.5f * magnitude ), 0 );
    }


    /**
     * Draw objects using the batch.
     * 
     * @param batch
     *            the SpriteBatch to draw with
     */
    public void draw( Batch batch )
    {
        for ( Terrain terrain : terrainList )
        {
            terrain.draw( batch );
        }
    }
}
