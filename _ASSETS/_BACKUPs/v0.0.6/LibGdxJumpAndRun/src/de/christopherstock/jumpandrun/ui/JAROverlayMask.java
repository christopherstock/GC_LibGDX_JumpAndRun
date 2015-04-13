/*  $Id: JAROverlayMask.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.JARSettings.HUD;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   Represents a scaling screen overlay mask.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JAROverlayMask.java $
    *****************************************************************************/
    public class JAROverlayMask
    {
        public static enum MaskDirection
        {
            EClosing,
            EOpening,
            ;
        }

        private                         MaskDirection       iDirection                  = null;
        private                         float               iMaxTicks                   = 0;
        private                         float               iTick                       = 0;
        private                         JARSprite           iSprite                     = null;
        private                         Color               iOutsideColor               = null;
        private                         float               iCenterX                    = 0;
        private                         float               iCenterY                    = 0;

        public JAROverlayMask( int aMaxTicks, JARImage aImg, Color aOutsideColor )
        {
            iMaxTicks       = aMaxTicks;
            iSprite            = new JARSprite( aImg );
            iOutsideColor   = aOutsideColor;
        }

        public final void draw( SpriteBatch batch )
        {
            //draw overlay if desired
            if ( iTick > 0 )
            {
                //get scale factor
                float scaleFactor = 0.0f;
                switch ( iDirection )
                {
                    case EClosing:
                    {
                        scaleFactor =                 iTick / iMaxTicks;
                        scaleFactor *= HUD.MASK_UPSCALE;
                        break;
                    }
                    case EOpening:
                    {
                        scaleFactor = ( iMaxTicks - iTick ) / iMaxTicks;
                        scaleFactor *= HUD.MASK_UPSCALE;
                        break;
                    }
                }

                //get scaled image dimensions
                float maskWidth     = JARScreen.WIDTH  * scaleFactor;
                float maskHeight    = JARScreen.HEIGHT * scaleFactor;

                //get scaled image left top position
                float maskX         = iCenterX - ( maskWidth  / 2 );
                float maskY         = iCenterY - ( maskHeight / 2 );

                //get scale factors for x and y dimension
                float scaleFactorX  = maskWidth  / iSprite.getImage().getFrameWidth();
                float scaleFactorY  = maskHeight / iSprite.getImage().getFrameHeight();

                //draw image in the center of the screen
                iSprite.draw
                (
                    batch,
                    iCenterX,
                    iCenterY,
                    LibAnchor.ECenterMiddle,
                    LibUI.ALPHA_OPAQUE,
                    false,
                    scaleFactorX,
                    scaleFactorY,
                    LibUI.ROTATION_NONE
                );

                //draw Outside rectangles - top, bottom, left, right
                LibDrawing.fillRect( batch, 0,                  0,                   JARScreen.WIDTH,                       maskY,                                 iOutsideColor, false );
                LibDrawing.fillRect( batch, 0,                  maskY + maskHeight,  JARScreen.WIDTH,                       JARScreen.HEIGHT - maskY - maskHeight, iOutsideColor, false );
                LibDrawing.fillRect( batch, 0,                  0,                   maskX,                                 JARScreen.HEIGHT,                      iOutsideColor, false );
                LibDrawing.fillRect( batch, maskX + maskWidth,  0,                   JARScreen.WIDTH - maskX - maskWidth,   JARScreen.HEIGHT,                      iOutsideColor, false );
            }
        }

        public final boolean isAnimated()
        {
            return ( iTick > 0 );
        }

        public final void start( MaskDirection aDirection )
        {
            //reset ticks
            iDirection  = aDirection;
            iTick       = iMaxTicks;
        }

        public final void setCenter( float centerX, float centerY )
        {
            //assign mask center
            iCenterX = centerX;
            iCenterY = centerY;
        }

        public final void animate()
        {
            iTick -= 1;
            if ( iTick < 0 ) iTick = 0;
        }
    }
