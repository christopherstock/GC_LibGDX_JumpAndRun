/*  $Id: JARHUD.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.io.*;

    /*****************************************************************************
    *   The 'heads up display'. Shows game information like points, hp etc.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JARHUD.java $
    *****************************************************************************/
    public class JARHUD
    {
        public          static          JARCounter      pointCounter                    = null;
        public          static          JARCounter      coinCounter                     = null;

        public static final void init()
        {
            //init counters
            pointCounter = new JARCounter();
            coinCounter  = new JARCounter();
        }

        public static final void drawHUD( SpriteBatch batch )
        {
            int drawY = 30;
            if ( coinCounter.draw( batch, "%% " + JARLevel.current.iCoins  + "", drawY ) )
            {
                drawY += JARImage.ENumbers.getHeight();
            }
            if ( pointCounter.draw( batch, JARLevel.current.iPoints + "", drawY ) )
            {
                drawY += JARImage.ENumbers.getHeight();
            }
        }

        public static final void showPoints()
        {
            pointCounter.show();
        }

        public static final void showCoins()
        {
            coinCounter.show();
        }

        public static final void animate()
        {
            pointCounter.animate();
            coinCounter.animate();
        }
/*
        public static final void drawBgOverlay()
        {
                case Game.GAME_STATE_ACCLAIM:
                case Game.GAME_STATE_PAUSE:
                case Game.GAME_STATE_OVER:
                {
                    targetBgAlpha = OVERLAY_ALPHA_PAUSE;
                    break;
                }

            targetBgAlpha = OVERLAY_ALPHA_RUNNING;

            aimCurrentBgAlpha();
            //Drawing.fillCanvas( "rgba( 255, 255, 255, " + ( currentBgAlpha / 100 ) + " )" );
        }

        public static final void aimCurrentBgAlpha()
        {
            if ( currentBgAlpha < targetBgAlpha )
            {
                currentBgAlpha += OVERLAY_ALPHA_SPEED;
                if ( currentBgAlpha > targetBgAlpha ) currentBgAlpha = targetBgAlpha;
            }
            else if ( currentBgAlpha > targetBgAlpha )
            {
                currentBgAlpha -= OVERLAY_ALPHA_SPEED;
                if ( currentBgAlpha < targetBgAlpha ) currentBgAlpha = targetBgAlpha;
            }
        }
*/
    }
