/*  $Id: JARCounter.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.JARSettings.HUD;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   This represents one counter being displayed in the HUD.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JARCounter.java $
    *****************************************************************************/
    public class JARCounter
    {
        public static enum CounterAlignment
        {
            ELeft,
            ERight,
            ;
        }

        private static enum CounterState
        {
            EHidden,
            EShowing,
            EVisible,
            EHiding,
            ;
        }

        private             CounterAlignment    iAlignment                  = null;
        private             CounterState        iState                      = null;
        private             int                 iTicker                     = 0;
        private             int                 iBorderWidth                = 0;

        public JARCounter( CounterAlignment aAlignment )
        {
            iAlignment  = aAlignment;

            iTicker     = 0;
            iState      = CounterState.EHidden;

            switch ( iAlignment )
            {
                case ELeft:
                {
                    iBorderWidth = HUD.OFFSET_HUD_BORDER_X + JARImage.EItemCoin.getFrameWidth() + HUD.OFFSET_HUD_COUNTER_MARGIN_X;
                    break;
                }
                case ERight:
                {
                    iBorderWidth = HUD.OFFSET_HUD_BORDER_X + JARImage.EItemApple.getFrameWidth() + HUD.OFFSET_HUD_COUNTER_MARGIN_X;
                    break;
                }
            }
        }

        public boolean draw( SpriteBatch batch, String msg, int drawY )
        {
            float pointstringWidth = ( msg.length() * JARImage.EGlobalNumbers.getFrameWidth() ) + iBorderWidth;

            //draw points display
            switch ( iState )
            {
                case EShowing:
                {
                    switch ( iAlignment )
                    {
                        case ELeft:
                        {
                            float x = iBorderWidth - ( pointstringWidth * iTicker / HUD.HUD_COUNTER_TICKER_TIME_SHOW_HIDE );
                            JARSprite.drawBitmapString( batch, msg, x, drawY, LibAnchor.ELeftTop, LibUI.ALPHA_OPAQUE );
                            break;
                        }
                        case ERight:
                        {
                            float x = JARScreen.WIDTH - iBorderWidth + ( pointstringWidth * iTicker / HUD.HUD_COUNTER_TICKER_TIME_SHOW_HIDE );
                            JARSprite.drawBitmapString( batch, msg, x, drawY, LibAnchor.ERightTop, LibUI.ALPHA_OPAQUE );
                            break;
                        }
                    }
                    return true;
                }

                case EVisible:
                {
                    switch ( iAlignment )
                    {
                        case ELeft:
                        {
                            float x = iBorderWidth;
                            JARSprite.drawBitmapString( batch, msg, x, drawY, LibAnchor.ELeftTop, LibUI.ALPHA_OPAQUE );
                            break;
                        }
                        case ERight:
                        {
                            float x = JARScreen.WIDTH - iBorderWidth;
                            JARSprite.drawBitmapString( batch, msg, x, drawY, LibAnchor.ERightTop, LibUI.ALPHA_OPAQUE );
                            break;
                        }
                    }
                    return true;
                }

                case EHiding:
                {
                    switch ( iAlignment )
                    {
                        case ELeft:
                        {
                            float x = iBorderWidth - pointstringWidth + ( pointstringWidth * iTicker / HUD.HUD_COUNTER_TICKER_TIME_SHOW_HIDE );
                            JARSprite.drawBitmapString( batch, msg, x, drawY, LibAnchor.ELeftTop, LibUI.ALPHA_OPAQUE );
                            break;
                        }
                        case ERight:
                        {
                            float x = JARScreen.WIDTH - iBorderWidth + pointstringWidth - ( pointstringWidth * iTicker / HUD.HUD_COUNTER_TICKER_TIME_SHOW_HIDE );
                            JARSprite.drawBitmapString( batch, msg, x, drawY, LibAnchor.ERightTop, LibUI.ALPHA_OPAQUE );
                            break;
                        }
                    }
                    return true;
                }

                case EHidden:
                {
                    //don't draw
                    break;
                }
            }

            return false;
        }

        public void show()
        {
            switch ( iState )
            {
                case EShowing:
                {
                    //no changes
                    break;
                }

                case EVisible:
                {
                    //reset ticker time
                    iTicker      = HUD.HUD_COUNTER_TICKER_TIME_VISIBLE;
                    break;
                }

                case EHiding:
                {
                    //change to state 'show'
                    iState       = CounterState.EShowing;
                    iTicker      = HUD.HUD_COUNTER_TICKER_TIME_SHOW_HIDE - iTicker;
                    break;
                }

                case EHidden:
                {
                    //change to state 'show'
                    iTicker      = HUD.HUD_COUNTER_TICKER_TIME_SHOW_HIDE;
                    iState       = CounterState.EShowing;
                    break;
                }
            }
        }

        public void animate()
        {
            if ( iTicker > 0 )
            {
                --iTicker;
            }
            else
            {
                switch ( iState )
                {
                    case EShowing:
                    {
                        //substract showing time so it will fade out directly after the according item faded out
                        iTicker  = HUD.HUD_COUNTER_TICKER_TIME_VISIBLE - HUD.HUD_COUNTER_TICKER_TIME_SHOW_HIDE;
                        iState   = CounterState.EVisible;
                        break;
                    }

                    case EVisible:
                    {
                        iTicker  = HUD.HUD_COUNTER_TICKER_TIME_SHOW_HIDE;
                        iState   = CounterState.EHiding;
                        break;
                    }

                    case EHiding:
                    {
                        iState = CounterState.EHidden;
                        break;
                    }

                    case EHidden:
                    {
                        //do nothing
                        break;
                    }
                }
            }
        }
    }
