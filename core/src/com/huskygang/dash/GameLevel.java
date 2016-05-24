package com.huskygang.dash;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


/**
 * Game level class - stores most of the information about game objects
 *
 * @author Austin Hsieh
 * @version May 23, 2016
 * @author Period: 5
 * @author Assignment: Dash-core
 *
 * @author Sources:
 */
public class GameLevel
{
    private LevelScreen levelScreen;

    private SpriteBatch batch;

    private Array<Sprite> sprites;

    private World world;

    private GameCamera gameCamera;

    private Box2DDebugRenderer debugRenderer;

    private Player player;

    private WorldGenerator worldGenerator;

    private GameContactListener contactListener;

    private boolean gameOver, bg1First = true, paused;

    private Sprite background1, background2;


    /**
     * Empty constructor, initializes everything
     * 
     * @param levelScreen
     *            levelScreen that contains this game level
     */
    public GameLevel( LevelScreen levelScreen )
    {
        this.levelScreen = levelScreen;
        sprites = new Array<Sprite>();
        world = new World( new Vector2( 0, -9.81f ), false );
        batch = new SpriteBatch();
        player = new Player( 1, 2, world );
        gameCamera = new GameCamera( player );
        debugRenderer = new Box2DDebugRenderer();

        background1 = new Sprite( getRandomBackground() );
        background2 = new Sprite( getRandomBackground() );
        background1.setSize( Constants.SCL_WIDTH, Constants.SCL_HEIGHT );
        background2.setSize( Constants.SCL_WIDTH, Constants.SCL_HEIGHT );
        background2.setPosition( Constants.SCL_WIDTH, 0 );

        sprites.add( background1 );
        sprites.add( background2 );
        sprites.add( player );
        worldGenerator = new WorldGenerator( world, this );
        contactListener = new GameContactListener( player );

        world.setContactListener( contactListener );

        Terrain startPlatform = new Terrain( 1f, 0, 10f, 1f, true, world );
        sprites.add( startPlatform );
    }


    /**
     * Sets game state to game over.
     */
    public void gameOver()
    {
        if ( !gameOver )
        {
            levelScreen.notifyGameOver();
            gameOver = true;
        }
    }


    /**
     * Pauses or unpauses the game.
     */
    public void pause( boolean pause )
    {
        paused = pause;
    }


    /**
     * Check if the game is over
     * 
     * @return gameOver
     */
    public boolean isGameOver()
    {
        return gameOver;
    }


    /**
     * Check if the game is paused
     * 
     * @return paused
     */
    public boolean isPaused()
    {
        return paused;
    }


    /**
     * Determines whether the game should be ended.
     */
    public void checkGameOver()
    {
        Vector3 playerPos = new Vector3( player.getX(), player.getY(), 0 );
        playerPos = gameCamera.project( playerPos ).scl( Constants.WORLD_TO_BOX );
        if ( playerPos.x >= Constants.SCL_WIDTH || playerPos.x + player.getWidth() <= 0
            || playerPos.y + player.getHeight() < 0 )
        {
            gameOver();
        }
    }


    /**
     * Getter method for primary SpriteBatch
     * 
     * @return the SpriteBatch
     */
    public SpriteBatch getBatch()
    {
        return batch;
    }


    /**
     * Getter method for distance variable of the camera.
     * 
     * @return game camera distance
     */
    public float getDistance()
    {
        return gameCamera.getDistance();
    }


    /**
     * Updates objects in the game
     * 
     * @param delta
     *            time between frames
     */
    public void update( float delta )
    {
        if ( !gameOver && !paused )
        {
            world.step( 1 / 60f, 3, 3 );
        }

        batch.setProjectionMatrix( gameCamera.combined );

        if ( !gameOver && !paused )
        {
            gameCamera.update();
            worldGenerator.generate( gameCamera );
            checkGameOver();
        }

        scrollBackground();

        batch.begin();
        for ( Sprite sprite : sprites )
        {
            sprite.draw( batch );
        }
        worldGenerator.draw( batch );
        batch.end();

        // debugRenderer.render( world, gameCamera.combined );
    }


    /**
     * Generate random texture for scrolling background
     * 
     * @return background texture
     */
    public Texture getRandomBackground()
    {
        return Assets.loadTex( "background" + MathUtils.random( 3 ) + ".png" );
    }


    public void scrollBackground()
    {
        Vector3 bg1Pos = new Vector3( background1.getX(), background1.getY(), 0 );
        Vector3 bg2Pos = new Vector3( background2.getX(), background2.getY(), 0 );
        gameCamera.project( bg1Pos );
        gameCamera.project( bg2Pos );
        if ( bg1First )
        {
            if ( bg2Pos.x <= 0 )
            {
                bg1First = false;
                 background1.setTexture( getRandomBackground() );
                background1.setPosition( background2.getX() + background2.getWidth(), 0 );
            }
        }
        else
        {
            if ( bg1Pos.x <= 0 )
            {
                bg1First = true;
                 background2.setTexture( getRandomBackground() );
                background2.setPosition( background1.getX() + background1.getWidth(), 0 );
            }
        }

    }
}
