/*  $Id: JARDebug.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.jumpandrun;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  com.badlogic.gdx.utils.*;
    import  de.christopherstock.jumpandrun.JARSettings.Camera;
    import  de.christopherstock.jumpandrun.JARSettings.Colors;
    import  de.christopherstock.jumpandrun.JARSettings.Debug;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /**************************************************************************************
    *   The version history system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    **************************************************************************************/
    public class JARDebug
    {
        //specified debug groups
        public      static          Logger          tween                                   = new Logger( "tween",      Logger.INFO     );
        public      static          Logger          camera                                  = new Logger( "camera",     Logger.NONE     );
        public      static          Logger          hid                                     = new Logger( "hid",        Logger.NONE     );
        public      static          Logger          sound                                   = new Logger( "sound",      Logger.NONE     );
        public      static          Logger          image                                   = new Logger( "imageLoad",  Logger.NONE     );

        //paramount debug groups
        public      static          Logger          bugfix                                  = new Logger( "bugfix",     Logger.INFO     );
        public      static          Logger          major                                   = new Logger( "major",      Logger.INFO     );
        public      static          Logger          err                                     = new Logger( "err",        Logger.ERROR    );

        public static final void drawDebugScreen( SpriteBatch batch )
        {
            //screen reference lines
            if ( Debug.DRAW_DEBUG_SCREEN_REFERENCE_LINES )
            {
                drawScreenReferenceLines( batch );
            }
        }

        private static final void drawScreenReferenceLines( SpriteBatch batch )
        {
            //vertical
            {
                LibDrawing.drawLine( batch, JARScreen.WIDTH * Camera.RATIO_CENTERING_X_HEADING_RIGHT, 0, JARScreen.WIDTH * Camera.RATIO_CENTERING_X_HEADING_RIGHT, JARScreen.HEIGHT, Colors.COLOR_REFERENCE_LINES );
                LibDrawing.drawLine( batch, JARScreen.WIDTH * Camera.RATIO_CENTERING_X_HEADING_LEFT,  0, JARScreen.WIDTH * Camera.RATIO_CENTERING_X_HEADING_LEFT,  JARScreen.HEIGHT, Colors.COLOR_REFERENCE_LINES );
            }

            //horizontal
            {
                LibDrawing.drawLine( batch, 0, JARScreen.HEIGHT * Camera.RATIO_CENTERING_Y_STANDING, JARScreen.WIDTH, JARScreen.HEIGHT * Camera.RATIO_CENTERING_Y_STANDING, Colors.COLOR_REFERENCE_LINES );
            }
        }
    }
