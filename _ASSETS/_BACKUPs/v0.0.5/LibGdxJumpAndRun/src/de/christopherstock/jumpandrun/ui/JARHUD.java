/*  $Id: JARHUD.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.JARSettings.Colors;
    import  de.christopherstock.jumpandrun.JARSettings.HUD;
    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.JARCounter.CounterAlignment;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   The 'heads up display'. Shows game information like points, hp etc.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JARHUD.java $
    *****************************************************************************/
    public class JARHUD
    {
        public          static          JAROverlay      overlayPause                    = null;

        public          static          JARCounter      counterPoints                   = null;
        public          static          JARCounter      counterCoins                    = null;

        public static final void init()
        {
            //init overlays
            overlayPause  = new JAROverlay( HUD.OVERLAY_PAUSE_TICKS_SHOW_HIDE, Colors.COLOR_PAUSE_OVERLAY );

            //init counters
            counterPoints = new JARCounter( CounterAlignment.ERight );
            counterCoins  = new JARCounter( CounterAlignment.ELeft  );
        }

        public static final void drawHUDScreen( SpriteBatch batch )
        {
            //draw counters
            {
                int drawY = 0;
                drawY = HUD.OFFSET_HUD_BORDER_Y;
                if ( counterCoins.draw( batch, String.valueOf( JARLevel.getCurrent().iCoins ), drawY ) )
                {
                    drawY += JARImage.EGlobalNumbers.getFrameHeight();
                }
                drawY = HUD.OFFSET_HUD_BORDER_Y;
                if ( counterPoints.draw( batch, String.valueOf( JARLevel.getCurrent().iPoints ), drawY ) )
                {
                    drawY += JARImage.EGlobalNumbers.getFrameHeight();
                }
            }

            //draw picked items
            JARItem.drawAllPicked( batch );

            //draw overlays
            {
                overlayPause.draw( batch );
            }

            //draw texts
            if ( !overlayPause.isHidden() )
            {
                float alpha = overlayPause.getAlphaFactor();
                LibDrawing.drawImage( batch, JARImage.ETextPause.getTextureRegion(), JARScreen.WIDTH / 2, JARScreen.HEIGHT / 2, LibAnchor.ECenterMiddle, alpha );
            }
        }

        public static final void animate()
        {
            counterPoints.animate();
            counterCoins.animate();
        }
    }
