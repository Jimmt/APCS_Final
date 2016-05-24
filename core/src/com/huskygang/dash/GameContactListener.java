package com.huskygang.dash;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;


/**
 * Game contact listener for Box2D collisions
 * 
 * @author Shreya Kumar
 * @version May 23, 2016
 * @author Period: TODO
 * @author Assignment: Dash-core
 *
 * @author Sources: TODO
 */
public class GameContactListener implements ContactListener
{
    Player player;


    /**
     * Initializes contact listener with player
     * 
     * @param player
     *            player to keep track of
     */
    public GameContactListener( Player player )
    {
        this.player = player;
    }


    /**
     * Perform actions when contact is initiated
     * 
     * @param contact
     *            the contact object
     */
    @Override
    public void beginContact( Contact contact )
    {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        String ua = (String)a.getUserData();
        String ub = (String)b.getUserData();

        if ( ua.equals( "Terrain" ) && ub.equals( "Player" ) )
        {
            player.setCanJump( true );
        }
        else if ( ub.equals( "Terrain" ) && ua.equals( "Player" ) )
        {
            player.setCanJump( true );
        }
    }


    /**
     * Perform actions when contact is ended
     * 
     * @param contact
     *            the contact object
     */
    @Override
    public void endContact( Contact contact )
    {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        String ua = (String)a.getUserData();
        String ub = (String)b.getUserData();

        if ( ua.equals( "Terrain" ) && ub.equals( "Player" ) )
        {
            player.setCanJump( false );
        }
        else if ( ub.equals( "Terrain" ) && ua.equals( "Player" ) )
        {
            player.setCanJump( false );
        }
    }


    /**
     * Unused method from ContactListener interface
     */
    @Override
    public void preSolve( Contact contact, Manifold oldManifold )
    {
    }


    /**
     * Unused method from ContactListener interface
     */
    @Override
    public void postSolve( Contact contact, ContactImpulse impulse )
    {
    }

}
