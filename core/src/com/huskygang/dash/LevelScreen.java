package com.huskygang.dash;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;


/**
 * Screen that shows the game
 * 
 * @author Shreya Kumar
 * @version May 23, 2016
 * @author Period: 5
 * @author Assignment: Dash-core
 *
 * @author Sources:
 */
public class LevelScreen extends BaseScreen
{
    OrthographicCamera uiCamera;

    GameLevel gameLevel;

    SpriteBatch uiBatch;

    TextButton restartButton, startButton;

    Label distanceLabel, gameOverLabel;

    Label[] titleLabel;

    Stage uiStage;


    /**
     * Initializes LevelScreen and its objects
     * 
     * @param dash
     *            Dash object for switching screens
     */
    public LevelScreen( final Dash dash )
    {
        super( dash );
        gameLevel = new GameLevel( this );
        uiCamera = new OrthographicCamera( Constants.WIDTH, Constants.HEIGHT );
        uiBatch = new SpriteBatch();

        String text = "DASH";
        titleLabel = new Label[text.length()];
        distanceLabel = new Label( "Distance: ", Assets.labelStyle );
        gameOverLabel = new Label( "Game Over", Assets.labelStyle );
        gameOverLabel.setVisible( false );
        gameOverLabel.setColor( 1, 1, 1, 0 );
        restartButton = new TextButton( "Restart", Assets.textButtonStyle );
        startButton = new TextButton( "Start", Assets.textButtonStyle );

        uiStage = new Stage( new StretchViewport( Constants.WIDTH, Constants.HEIGHT ) );
        uiStage.addActor( distanceLabel );
        uiStage.addActor( gameOverLabel );
        uiStage.addActor( restartButton );
        uiStage.addActor( startButton );

        for ( int i = 0; i < text.length(); i++ )
        {
            titleLabel[i] = new Label( String.valueOf( text.charAt( i ) ), Assets.labelStyle );
            uiStage.addActor( titleLabel[i] );
        }
        restartButton.addListener( new ClickListener()
        {
            public void clicked( InputEvent e, float x, float y )
            {
                dash.setScreen( new LevelScreen( dash ) );
            }
        } );
        startButton.addListener( new ClickListener()
        {
            public void clicked( InputEvent e, float x, float y )
            {
                gameLevel.pause( false );
                dash.firstRun = false;
                startButton.addAction( Actions.sequence( Actions.fadeOut( 1.0f ), Actions.visible( false ) ) );
                for ( int i = 0; i < titleLabel.length; i++ )
                {
                    Label label = titleLabel[i];
                    label.addAction( Actions.sequence( Actions.delay( i / 10f ),
                        Actions.parallel( Actions.moveBy( 0f, 30f, 1f ), Actions.fadeOut( 1.0f ) ),
                        Actions.visible( false ) ) );
                }
            }
        } );
        restartButton.setVisible( false );
        restartButton.setColor( 1, 1, 1, 0 );
        startButton.setVisible( dash.firstRun );
        for ( Label label : titleLabel )
            label.setVisible( dash.firstRun );
        Gdx.input.setInputProcessor( uiStage );

    }


    /**
     * Event notification when game is over
     */
    public void notifyGameOver()
    {
        gameOverLabel.addAction( Actions.sequence( Actions.visible( true ), Actions.fadeIn( 0.5f ) ) );
        restartButton.addAction( Actions.sequence( Actions.visible( true ), Actions.fadeIn( 0.5f ) ) );
    }


    /**
     * Inherited render method
     * 
     * @param delta
     *            delta time
     */
    @Override
    public void render( float delta )
    {
        super.render( delta );
        gameLevel.update( delta );

        if ( dash.firstRun )
        {
            gameLevel.pause( true );
        }

        DecimalFormat df = new DecimalFormat( "#.0" );
        distanceLabel.setText( "Distance: " + df.format( gameLevel.getDistance() ) );
        distanceLabel.setPosition( Constants.WIDTH / 2f - distanceLabel.getPrefWidth() / 2f,
            Constants.HEIGHT - distanceLabel.getHeight() * 2 );

        gameOverLabel.setPosition( Constants.WIDTH / 2f - gameOverLabel.getWidth() / 2f,
            Constants.HEIGHT / 2f + gameOverLabel.getHeight() / 2f );
        restartButton.setPosition( Constants.WIDTH / 2f - restartButton.getWidth() / 2f,
            Constants.HEIGHT / 2f - restartButton.getHeight() );

        for ( int i = 0; i < titleLabel.length; i++ )
        {
            Label label = titleLabel[i];
            if ( label.getActions().size == 0 )
            {
                label.setPosition( Constants.WIDTH / 2f - 2 * label.getWidth() * ( 1.75f - i ),
                    Constants.HEIGHT / 2f + label.getHeight() / 2f );
            }
        }
        startButton.setPosition( Constants.WIDTH / 2f - startButton.getWidth() / 2f,
            Constants.HEIGHT / 2f - startButton.getHeight() );

        uiBatch.setProjectionMatrix( uiCamera.combined );

        uiBatch.begin();
        uiStage.act( delta );
        uiStage.draw();

        uiBatch.end();
        uiCamera.update();
    }
}
