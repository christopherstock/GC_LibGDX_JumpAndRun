/*  $Id: StrategyScreen.java 179 2013-04-11 12:37:39Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.jumpandrun;

    import  android.os.Bundle;
    import  com.badlogic.gdx.backends.android.AndroidApplication;
    import  com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

    public class MainActivity extends AndroidApplication
    {
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate( savedInstanceState );

            AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
            cfg.useGL20 = false;

            initialize( new JAR(), cfg );
        }
    }
