package com.huskygang.dash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Player class
 *
 * @author Austin Hsieh
 * @version May 17, 2016
 * @author Period: 5
 * @author Assignment: Dash
 *
 * @author Sources:
 */
public class Player extends Sprite
{
    private Body body;

    private long lastDash = 0, dashCooldown = 1;

    private boolean canJump = false;

    private int direction = 0;


    /**
     * Constructor
     * 
     * @param x
     *            starting x location
     * @param y
     *            starting y location
     * @param world
     *            Box2D world
     */
    public Player( float x, float y, World world )
    {
        super( Assets.loadTex( "player_green.png" ) );
        this.setSize( getWidth() / 2, getHeight() / 2 );
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set( x, y );
        body = world.createBody( bodyDef );
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circle = new CircleShape();

        float sclWidth = this.getWidth() * Constants.WORLD_TO_BOX;
        float sclHeight = this.getHeight() * Constants.WORLD_TO_BOX;
        circle.setRadius( sclWidth / 2f );
        fixtureDef.shape = circle;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.1f;
        body.createFixture( fixtureDef );
        body.setUserData( "Player" );
        setSize( sclWidth, sclHeight );
        setOrigin( sclWidth / 2f, sclHeight / 2f );
    }


    /**
     * Set whether the player can jump or not
     * 
     * @param canJump
     *            if the player can jump
     */
    public void setCanJump( boolean canJump )
    {
        this.canJump = canJump;
    }


    /**
     * Getter for player body
     * 
     * @return the body
     */
    public Body getBody()
    {
        return body;
    }


    /**
     * Inherited draw method
     * 
     * @param batch
     *            the SpriteBatch
     */
    @Override
    public void draw( Batch batch )
    {
        super.draw( batch );

        setPosition( body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2 );
        setRotation( body.getTransform().getRotation() * MathUtils.radDeg );
        body.setLinearVelocity( 3 + direction * 1, body.getLinearVelocity().y );

        if ( Gdx.input.isKeyPressed( Keys.W ) || Gdx.input.isKeyPressed( Keys.UP ) )
        {
            jump();
        }
        if ( Gdx.input.isKeyPressed( Keys.A ) || Gdx.input.isKeyPressed( Keys.LEFT ) )
        {
            direction = -3;
        }
        else if ( Gdx.input.isKeyPressed( Keys.D ) || Gdx.input.isKeyPressed( Keys.RIGHT ) )
        {
            direction = 1;
        }
        else
        {
            direction = 0;
        }
    }


    /**
     * Checks if player can jump, then jumps
     */
    public void jump()
    {
        if ( canJump )
        {
            body.setLinearVelocity( body.getLinearVelocity().x, 5f );
        }
    }

}
