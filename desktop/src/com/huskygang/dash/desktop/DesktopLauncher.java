package com.huskygang.dash.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.huskygang.dash.Dash;


public class DesktopLauncher
{
    public static void main( String[] arg )
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 800;
        config.samples = 1;
        new LwjglApplication( new Dash(), config );
    }
}
