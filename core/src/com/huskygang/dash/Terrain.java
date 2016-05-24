package com.huskygang.dash;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


/**
 * General obstacle class
 *
 * @author Austin Hsieh
 * @version May 23, 2016
 * @author Period: 5
 * @author Assignment: Dash-core
 *
 * @author Sources:
 */
public class Terrain extends Sprite
{
    Body body;


    /**
     * Initializes Terrain and sets transform properties of terrain
     * 
     * @param x
     *            starting x position
     * @param y
     *            starting y position
     * @param width
     *            width
     * @param height
     *            height
     * @param isStatic
     *            if the body is static
     * @param world
     *            box2d world
     */
    public Terrain( float x, float y, float width, float height, boolean isStatic, World world )
    {
        super( Assets.loadTex( "platform.png" ) );

        setSize( width, height );

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set( x, y );
        bodyDef.gravityScale = 0.0f;

        bodyDef.type = isStatic ? BodyType.StaticBody : BodyType.DynamicBody;
        body = world.createBody( bodyDef );
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape box = new PolygonShape();
        box.setAsBox( width / 2, height / 2 );
        fixtureDef.friction = 0.1f;
        fixtureDef.density = 1.0f;
        fixtureDef.shape = box;
        body.createFixture( fixtureDef );
        body.setUserData( "Terrain" );

        setOrigin( getWidth() / 2, getHeight() / 2 );
        setPosition( body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2 );
        setRotation( body.getTransform().getRotation() * MathUtils.radDeg );
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
    }

}
