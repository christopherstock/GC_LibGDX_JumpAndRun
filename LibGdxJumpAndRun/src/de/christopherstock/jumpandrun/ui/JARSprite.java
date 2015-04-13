/*  $Id: JARSprite.java 190 2014-02-17 09:34:53Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.JARSettings.Sprites;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.JARSpriteFx.SpriteEffectSkewX1X4Direction;
    import  de.christopherstock.libgdx.lib.math.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   A sprite is one image that forms several bitmaps from the bitmap data.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JARSprite.java $
    *****************************************************************************/
    public class JARSprite
    {
        public static enum FrameAnimationTick
        {
            EEveryTick(         0       ),
            EEvery2ndTick(      1       ),
            EEvery10thTick(     9       ),
            ;

            private                 int         iDelayTicks                     = 0;

            private FrameAnimationTick( int aDelayTick )
            {
                iDelayTicks = aDelayTick;
            }

            public int getDelayTicks()
            {
                return iDelayTicks;
            }
        }

        private     static      JARSprite                       NUMBERS                     = null;

        private                 JARImage                        iImg                        = null;
        private                 Sprite                          iSprite                     = null;

        private                 int                             iCurrentFrameIndex          = 0;
        private                 int                             iFrameDelay                 = 0;

        protected               LibSpriteTrans                  iSpriteTrans                = null;
        protected               int                             iSpriteTransTick            = 0;
        protected               SpriteEffectSkewX1X4Direction   iSkewX1X4State              = null;

        public JARSprite( JARImage aImg )
        {
            iImg                = aImg;
            iSprite             = new Sprite( iImg.getTextureRegion() );
            iSpriteTrans        = new LibSpriteTrans();
            iFrameDelay         = iImg.getFrameAnimationDelay().getDelayTicks();
            iCurrentFrameIndex  = 0;

            iSkewX1X4State      = SpriteEffectSkewX1X4Direction.ESwingLeft;
            iSpriteTransTick    = Sprites.SPRITE_SKEW_X1X4_MAX / 2;

            //create initial sprite-transformation according to elevation
            switch ( iImg.getElevation() )
            {
                case ENone:
                {
                    break;
                }
                case EAscending:
                {
                    iSpriteTrans.iY1 += iImg.getFrameHeight();
                    iSpriteTrans.iY2 += iImg.getFrameHeight();
                    break;
                }
                case EDescending:
                {
                    iSpriteTrans.iY3 += iImg.getFrameHeight();
                    iSpriteTrans.iY4 += iImg.getFrameHeight();
                    break;
                }
            }
        }

        public static final void init()
        {
            NUMBERS = new JARSprite( JARImage.EGlobalNumbers );
        }

        public final void draw
        (
            SpriteBatch batch,
            float       x,
            float       y,
            LibAnchor   ank,
            float       alpha,
            boolean     flipX,
            float       scaleX,
            float       scaleY,
            float       rotation
        )
        {
            //draw the sprite object
            LibDrawing.drawSprite
            (
                batch,
                iSprite,
                x,
                y,
                flipX,
                ank,
                iImg.getFramesX(),
                iImg.getFrameWidth(),
                iImg.getFrameHeight(),
                iCurrentFrameIndex,
                alpha,
                iSpriteTrans,
                scaleX,
                scaleY,
                rotation
            );
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
            int             frameWidth  = NUMBERS.getImage().getFrameWidth();
            int             stringWidth = ( str.length() * frameWidth );
            float           drawX       = x;
            float           drawY       = y;

            //consider anchor
            switch( ank )
            {
                case ELeftTop:       {   drawX -= 0;                   drawY -= 0;                                          break;  }
                case ECenterTop:     {   drawX -= stringWidth / 2;     drawY -= 0;                                          break;  }
                case ERightTop:      {   drawX -= stringWidth;         drawY -= 0;                                          break;  }
                case ELeftMiddle:    {   drawX -= 0;                   drawY -= NUMBERS.getImage().getTotalHeight() / 2;    break;  }
                case ECenterMiddle:  {   drawX -= stringWidth / 2;     drawY -= NUMBERS.getImage().getTotalHeight() / 2;    break;  }
                case ERightMiddle:   {   drawX -= stringWidth;         drawY -= NUMBERS.getImage().getTotalHeight() / 2;    break;  }
                case ELeftBottom:    {   drawX -= 0;                   drawY -= NUMBERS.getImage().getTotalHeight();        break;  }
                case ECenterBottom:  {   drawX -= stringWidth / 2;     drawY -= NUMBERS.getImage().getTotalHeight();        break;  }
                case ERightBottom:   {   drawX -= stringWidth;         drawY -= NUMBERS.getImage().getTotalHeight();        break;  }
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
                    NUMBERS.setCurrentFrameIndex( frame );
                    NUMBERS.draw
                    (
                        batch,
                        drawX,
                        drawY,
                        LibAnchor.ELeftTop,
                        alpha,
                        false,
                        LibUI.SCALATION_NONE,
                        LibUI.SCALATION_NONE,
                        LibUI.ROTATION_NONE
                    );

                    drawX += frameWidth;
                }
            }
        }

        public final JARImage getImage()
        {
            return iImg;
        }

        public final void animateFrame()
        {
            //animate sprite frame
            if ( iFrameDelay > 0 )
            {
                --iFrameDelay;
            }
            else
            {
                //restart delay
                iFrameDelay = iImg.getFrameAnimationDelay().getDelayTicks();

                //next frame
                ++iCurrentFrameIndex;

                if ( iCurrentFrameIndex >= getImage().getFrameCount() )
                {
                    iCurrentFrameIndex = 0;
                }
/*
                //debug specific images
                if ( iImg == JARImage.EPlayerWalkLeft )
                {
                    JARDebug.spriteFrame.info( "Changed to frame [" + iCurrentFrameIndex + "] / [" + getImage().getFrameCount() + "]" );
                }
*/
            }
        }

        public final void setCurrentFrameIndex( int newFrameIndex )
        {
            iCurrentFrameIndex = newFrameIndex;
        }

        public final void shuffleCurrentFrameIndex()
        {
            iCurrentFrameIndex = LibMath.getRandom( 0, getImage().getFrameCount() - 1 );
        }
    }