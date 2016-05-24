package com.huskygang.dash;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Dash extends Game
{
    public boolean firstRun = true;

    public Dash()
    {
    }


    @Override
    public void create()
    {
        Assets.initialize();
        setScreen( new LevelScreen( this ) );
    }


    @Override
    public void dispose()
    {
        super.dispose();
    }


    @Override
    public void render()
    {
        super.render();
    }


    @Override
    public void resize( int width, int height )
    {
        super.resize( width, height );
    }


    @Override
    public void pause()
    {
        super.pause();
    }


    @Override
    public void resume()
    {
        super.resume();
    }
}
