/*  $Id: JARCounter.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.io.*;
    import de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   This represents one counter being displayed in the HUD.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JARCounter.java $
    *****************************************************************************/
    public class JARCounter
    {
        public static final int         STATE_APPEARING             = 0;
        public static final int         STATE_STILL                 = 1;
        public static final int         STATE_DISAPPEARING          = 2;

        public              int         icon                        = 0;
        public              int         ticker                      = 0;
        public              int         state                       = 0;

        public JARCounter()
        {
            icon       = 0;
            ticker     = 0;
            state      = STATE_DISAPPEARING;

            if ( JARDebug.ALWAYS_SHOW_COUNTERS ) state = STATE_APPEARING;
        }

        public boolean draw( SpriteBatch batch, String msg, int drawY )
        {
            float pointstringWidth = msg.length() * JARImage.ENumbers.getWidth() / 11;

            //draw points display
            switch ( state )
            {
                case STATE_APPEARING:
                {
                    JARSprite.drawBitmapString( batch, msg + "", JARScreen.width() - 30 + ( pointstringWidth * ticker / JARSettings.HUD_COUNTER_TICKER_TIME_SHOW_HIDE ), drawY, LibAnchor.RIGHT_TOP );
                    return true;
                }
                case STATE_STILL:
                {
                    JARSprite.drawBitmapString( batch, msg + "", JARScreen.width() - 30, drawY, LibAnchor.RIGHT_TOP );
                    return true;
                }
                case STATE_DISAPPEARING:
                {
                    if ( ticker > 0 )
                    {
                        JARSprite.drawBitmapString( batch, msg + "", JARScreen.width() - 30 + pointstringWidth - ( pointstringWidth * ticker / JARSettings.HUD_COUNTER_TICKER_TIME_SHOW_HIDE ), drawY, LibAnchor.RIGHT_TOP );
                        return true;
                    }
                    break;
                }
            }

            return false;
        }

        public void show()
        {
            switch ( state )
            {
                case STATE_APPEARING:
                {
                    //no changes
                    break;
                }
                case STATE_STILL:
                {
                    //reset point duration
                    ticker      = JARSettings.HUD_COUNTER_TICKER_TIME_STILL;
                    break;
                }
                case STATE_DISAPPEARING:
                {
                    state       = STATE_APPEARING;
                    ticker      = JARSettings.HUD_COUNTER_TICKER_TIME_SHOW_HIDE;
                    break;
                }
            }
        }

        public void animate()
        {
            if ( ticker > 0 )
            {
                --ticker;
            }
            else
            {
                switch ( state )
                {
                    case STATE_APPEARING:
                    {
                        ticker = JARSettings.HUD_COUNTER_TICKER_TIME_STILL;
                        state        = STATE_STILL;
                        break;
                    }
                    case STATE_STILL:
                    {
                        ticker = JARSettings.HUD_COUNTER_TICKER_TIME_SHOW_HIDE;
                        state        = STATE_DISAPPEARING;
                        if ( JARDebug.ALWAYS_SHOW_COUNTERS ) state = STATE_STILL;
                        break;
                    }
                    case STATE_DISAPPEARING:
                    {
                        //do nothing
                        break;
                    }
                }
            }
        }
    }
