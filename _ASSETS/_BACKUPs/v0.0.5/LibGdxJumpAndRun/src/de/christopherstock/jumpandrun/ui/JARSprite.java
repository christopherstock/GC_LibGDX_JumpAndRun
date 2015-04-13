/*  $Id: JARSprite.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   A sprite is one image that forms several bitmaps from the bitmap data.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JARSprite.java $
    *****************************************************************************/
    public class JARSprite
    {
        public      static          JARSprite       NUMBERS                     = null;

        public                      JARImage        iImg                        = null;

        public JARSprite( JARImage aImg )
        {
            iImg         = aImg;
        }

        public static final void init()
        {
            NUMBERS = new JARSprite( JARImage.EGlobalNumbers );
        }

        public static final void drawSpriteObj( SpriteBatch batch, JARSprite sprite, float x, float y, LibAnchor ank, int frame, float alpha )
        {
            LibDrawing.drawSprite( batch, sprite.iImg.getTextureRegion(), x, y, ank, sprite.iImg.iFramesX, sprite.iImg.iFramesY, frame, alpha );
        }

        /*****************************************************************************
        *   Draws an image with the number-sprite.
        *
        *   @param  str     The string to draw.
        *   @param  x       The string position x.
        *   @param  y       The string position y.
        *   @param  ank     The string anchor for drawing.
        *****************************************************************************/
        public static final void drawBitmapString( SpriteBatch batch, String str, float x, float y, LibAnchor ank, float alpha )
        {
            int             frame       = 0;
            TextureRegion   img         = NUMBERS.iImg.getTextureRegion();
            int             frameWidth  = img.getTexture().getWidth() / 11;
            int             stringWidth = ( str.length() * frameWidth );
            float           drawX       = x;
            float           drawY       = y;

            //consider anchor
            switch( ank )
            {
                case ELeftTop:       {   drawX -= 0;                   drawY -= 0;                                  break;  }
                case ECenterTop:     {   drawX -= stringWidth / 2;     drawY -= 0;                                  break;  }
                case ERightTop:      {   drawX -= stringWidth;         drawY -= 0;                                  break;  }
                case ELeftMiddle:    {   drawX -= 0;                   drawY -= img.getTexture().getHeight() / 2;   break;  }
                case ECenterMiddle:  {   drawX -= stringWidth / 2;     drawY -= img.getTexture().getHeight() / 2;   break;  }
                case ERightMiddle:   {   drawX -= stringWidth;         drawY -= img.getTexture().getHeight() / 2;   break;  }
                case ELeftBottom:    {   drawX -= 0;                   drawY -= img.getTexture().getHeight();       break;  }
                case ECenterBottom:  {   drawX -= stringWidth / 2;     drawY -= img.getTexture().getHeight();       break;  }
                case ERightBottom:   {   drawX -= stringWidth;         drawY -= img.getTexture().getHeight();       break;  }
            }

            //browse all chars
            for ( int i = 0; i < str.length(); ++i )
            {
                switch ( str.charAt( i ) )
                {
                    case ' ':       frame = -1;                                                             break;
                    case '%':       frame = 10;                                                             break;
                    default:        frame = 0 + Integer.parseInt( String.valueOf( str.charAt( i ) ) );      break;
                }

                //draw frame if desired
                if ( frame == -1 )
                {
                    drawX += frameWidth / 2;
                }
                else
                {
                    //LibDrawing.drawSprite( batch, JARImage.PRELOADER_NUMBERS, drawX, drawY, Anchor.LEFT_TOP, 11, 1, frame );

                    drawSpriteObj( batch, NUMBERS, drawX, drawY, LibAnchor.ELeftTop, frame, alpha );

                    drawX += frameWidth;
                }
            }
        }
    }
