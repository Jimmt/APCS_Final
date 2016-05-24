package com.huskygang.dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;


/**
 * Parent class for screens
 *
 * @author Shreya Kumar
 * @version May 23, 2016
 * @author Period: 5
 * @author Assignment: Dash-core
 *
 * @author Sources:
 */
public class BaseScreen implements Screen
{
    protected Dash dash;

    OrthographicCamera camera;


    /**
     * Initializes screen with basic objects.
     * 
     * @param dash
     *            Dash object for changing screens.
     */
    public BaseScreen( Dash dash )
    {
        this.dash = dash;
        camera = new OrthographicCamera();
    }


    /**
     * Clears the screen and draws.
     * 
     * @param delta
     *            time from last frame to current frame
     */
    @Override
    public void render( float delta )
    {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        camera.update();
    }


    /**
     * Below are inherited methods that aren't used.
     */

    @Override
    public void show()
    {
    }


    @Override
    public void resize( int width, int height )
    {
    }


    @Override
    public void pause()
    {
    }


    @Override
    public void resume()
    {
    }


    @Override
    public void hide()
    {
    }


    @Override
    public void dispose()
    {
    }
}
