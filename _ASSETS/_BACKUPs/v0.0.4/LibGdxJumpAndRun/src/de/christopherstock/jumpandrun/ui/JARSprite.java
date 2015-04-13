/*  $Id: JARSprite.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
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
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JARSprite.java $
    *****************************************************************************/
    public class JARSprite
    {
        public      static          JARSprite       PLAYER1_WALK_LEFT           = null;
        public      static          JARSprite       PLAYER1_WALK_RIGHT          = null;
        public      static          JARSprite       PLAYER1_STAND_LEFT          = null;
        public      static          JARSprite       PLAYER1_STAND_RIGHT         = null;
        public      static          JARSprite       ENEMY1_WALK_LEFT            = null;
        public      static          JARSprite       ENEMY1_WALK_RIGHT           = null;

        public      static          JARSprite       COIN                        = null;
        public      static          JARSprite       APPLE                       = null;
        public      static          JARSprite       CHERRY                      = null;
        public      static          JARSprite       ORANGE                      = null;
        public      static          JARSprite       PEAR                        = null;
        public      static          JARSprite       STRAWBERRY                  = null;
        public      static          JARSprite       NUMBERS                     = null;

        public                      JARImage        iImg                        = null;
        public                      int             iFramesX                    = 0;
        public                      int             iFramesY                    = 0;
        public                      int             iFrameCount                 = 0;
        public                      int             iFrameWidth                 = 0;
        public                      int             iFrameHeight                = 0;

        public JARSprite( JARImage aImg, int aFramesX, int aFramesY, int aFrameCount )
        {
            iImg         = aImg;
            iFramesX     = aFramesX;
            iFramesY     = aFramesY;
            iFrameCount  = aFrameCount;
            iFrameWidth  = (int)( iImg.getWidth()  / iFramesX );
            iFrameHeight = (int)( iImg.getHeight() / iFramesY );
        }

        public static final void init()
        {
            COIN                = new JARSprite( JARImage.EItemCoin                 , 5,  4, 20 );

            PLAYER1_WALK_LEFT   = new JARSprite( JARImage.GAME_PLAYER_WALK_LEFT     , 1,  1, 1  );
            PLAYER1_WALK_RIGHT  = new JARSprite( JARImage.GAME_PLAYER_WALK_RIGHT    , 1,  1, 1  );
            PLAYER1_STAND_LEFT  = new JARSprite( JARImage.GAME_PLAYER_STAND_LEFT    , 1,  1, 1  );
            PLAYER1_STAND_RIGHT = new JARSprite( JARImage.GAME_PLAYER_STAND_RIGHT   , 1,  1, 1  );

            ENEMY1_WALK_LEFT    = new JARSprite( JARImage.EEnemy1WalkLeft           , 1,  1, 1  );
            ENEMY1_WALK_RIGHT   = new JARSprite( JARImage.EEnemy1WalkRight          , 1,  1, 1  );

            APPLE               = new JARSprite( JARImage.EItemApple           , 1,  1, 1  );
            CHERRY              = new JARSprite( JARImage.EItemCherry          , 1,  1, 1  );
            ORANGE              = new JARSprite( JARImage.EItemOrange          , 1,  1, 1  );
            PEAR                = new JARSprite( JARImage.EItemPear            , 1,  1, 1  );
            STRAWBERRY          = new JARSprite( JARImage.EItemStrwaberry      , 1,  1, 1  );

            NUMBERS             = new JARSprite( JARImage.ENumbers         , 11, 1, 1  );
        }

        public static final void drawSpriteObj( SpriteBatch batch, JARSprite sprite, float x, float y, LibAnchor ank, int frame )
        {
            LibDrawing.drawSprite( batch, sprite.iImg.getTextureRegion(), x, y, ank, sprite.iFramesX, sprite.iFramesY, frame );
        }

        /*****************************************************************************
        *   Draws an image with the number-sprite.
        *
        *   @param  str     The string to draw.
        *   @param  x       The string position x.
        *   @param  y       The string position y.
        *   @param  ank     The string anchor for drawing.
        *****************************************************************************/
        public static final void drawBitmapString( SpriteBatch batch, String str, float x, float y, LibAnchor ank )
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
                case LEFT_TOP:       {   drawX -= 0;                   drawY -= 0;                                  break;  }
                case CENTER_TOP:     {   drawX -= stringWidth / 2;     drawY -= 0;                                  break;  }
                case RIGHT_TOP:      {   drawX -= stringWidth;         drawY -= 0;                                  break;  }
                case LEFT_MIDDLE:    {   drawX -= 0;                   drawY -= img.getTexture().getHeight() / 2;   break;  }
                case CENTER_MIDDLE:  {   drawX -= stringWidth / 2;     drawY -= img.getTexture().getHeight() / 2;   break;  }
                case RIGHT_MIDDLE:   {   drawX -= stringWidth;         drawY -= img.getTexture().getHeight() / 2;   break;  }
                case LEFT_BOTTOM:    {   drawX -= 0;                   drawY -= img.getTexture().getHeight();       break;  }
                case CENTER_BOTTOM:  {   drawX -= stringWidth / 2;     drawY -= img.getTexture().getHeight();       break;  }
                case RIGHT_BOTTOM:   {   drawX -= stringWidth;         drawY -= img.getTexture().getHeight();       break;  }
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

                    drawSpriteObj( batch, NUMBERS, drawX, drawY, LibAnchor.LEFT_TOP, frame );

                    drawX += frameWidth;
                }
            }
        }
    }
