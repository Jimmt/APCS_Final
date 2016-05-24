package com.huskygang.dash;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


/**
 * Class for caching textures and useful UI objects.
 *
 * @author Austin Hsieh
 * @version May 23, 2016
 * @author Period: 5
 * @author Assignment: Dash-core
 *
 * @author Sources:
 */
public class Assets
{
    public static HashMap<String, Texture> texMap;

    public static LabelStyle labelStyle;

    public static TextButtonStyle textButtonStyle;


    /**
     * Static initializer to avoid NullPointerExceptions
     */
    public static void initialize()
    {
        texMap = new HashMap<String, Texture>();
        BitmapFont darwinFont = getFonts( "OS_X_Darwin.ttf" );
        labelStyle = new LabelStyle();
        labelStyle.font = darwinFont;
        labelStyle.fontColor = Color.WHITE;

        textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = new Image( Assets.loadTex( "button.png" ) ).getDrawable();
        textButtonStyle.over = new Image( Assets.loadTex( "button_hover.png" ) ).getDrawable();
        textButtonStyle.down = new Image( Assets.loadTex( "button_clicked.png" ) ).getDrawable();
        textButtonStyle.font = darwinFont;
    }


    /**
     * Load a BitmapFont from a TrueType font.
     * 
     * @param path
     *            font location
     * @return the BitmapFont
     */
    public static BitmapFont getFonts( String path )
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal( path ) );
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 36;
        BitmapFont font = generator.generateFont( parameter );
        generator.dispose();
        return font;
    }


    /**
     * Creates a new texture and stores it in the texture map, or retrieves a
     * texture from the texture map if it already exists.
     * 
     * @param loc
     *            texture path
     * @return the Texture
     */
    public static Texture loadTex( String loc )
    {
        Texture tex = new Texture( Gdx.files.internal( loc ) );
        tex.setFilter( TextureFilter.Linear, TextureFilter.Linear );
        texMap.put( loc, tex );
        return tex;
    }
}
