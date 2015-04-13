/*  $Id: JAROverlay.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   Represents a fading screen overlay.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JAROverlay.java $
    *****************************************************************************/
    public class JAROverlay
    {
        private                         int                 iMaxTicks                   = 0;
        private                         int                 iTick                       = 0;
        private                         int                 iColor                      = 0;

        public JAROverlay( int aMaxTicks, Color aColor )
        {
            iMaxTicks   = aMaxTicks;
            iColor      = Color.rgba8888( aColor );
        }

        public final void draw( SpriteBatch batch )
        {
            //draw overlay if desired
            if ( iTick > 0 )
            {
                //pick int from col
                int colInt = iColor;

                //pick alpha value
                int alpha = ( colInt & 0xff );

                //modify alpha according to tick
                alpha = alpha * iTick / iMaxTicks;

                //assign updated alpha
                colInt = ( ( colInt & 0x00 ) | alpha );

                //create col from int
                Color col = new Color();
                Color.rgba8888ToColor( col, colInt );

                //draw overlay
                LibDrawing.fillRect( batch, 0, 0, JARScreen.WIDTH, JARScreen.HEIGHT, col, true );
            }
        }

        public final float getAlphaFactor()
        {
            return ( (float)iTick / (float)iMaxTicks );
        }

        public final boolean isHidden()
        {
            return ( iTick == 0 );
        }

        public final void fadeOut()
        {
            iTick -= 1;
            if ( iTick < 0 ) iTick = 0;
        }

        public final void fadeIn()
        {
            iTick += 1;
            if ( iTick > iMaxTicks ) iTick = iMaxTicks;
        }
    }
