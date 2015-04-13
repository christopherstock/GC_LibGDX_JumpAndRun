/*  $Id: JARHUD.java 172 2013-09-25 06:50:05Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.JARSettings.Colors;
    import  de.christopherstock.jumpandrun.JARSettings.HUD;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.jumpandrun.game.object.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.JARCounter.CounterAlignment;
    import  de.christopherstock.libgdx.lib.math.geom.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   The 'heads up display'. Shows game information like points, hp etc.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JARHUD.java $
    *****************************************************************************/
    public class JARHUD
    {
        public          static          JARSprite       textPause                       = null;

        public          static          JAROverlayPane  overlayPanePause                = null;
        public          static          JAROverlayMask  overlayMaskCircle               = null;

        public          static          JARCounter      counterPoints                   = null;
        public          static          JARCounter      counterCoins                    = null;

        public static final void init()
        {
            //init texts
            textPause           = new JARSprite( JARImage.ETextPause );

            //init overlays
            overlayPanePause    = new JAROverlayPane( HUD.OVERLAY_PAUSE_TICKS_SHOW_HIDE,          Colors.COLOR_OVERLAY_PANE_PAUSE  );
            overlayMaskCircle   = new JAROverlayMask( HUD.OVERLAY_MASK_CIRCLE_TICKS_SHOW_HIDE,    JARImage.EGlobalMaskCircle, Colors.COLOR_OVERLAY_MASK_CIRCLE_OUTSIDE  );

            //init counters
            counterPoints       = new JARCounter( CounterAlignment.ERight );
            counterCoins        = new JARCounter( CounterAlignment.ELeft  );
        }

        public static final void draw( SpriteBatch batch )
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
                overlayPanePause.draw(  batch );
                overlayMaskCircle.draw( batch );
            }

            //draw texts
            if ( !overlayPanePause.isHidden() )
            {
                float alpha = overlayPanePause.getAlphaFactor();
                textPause.draw
                (
                    batch,
                    JARScreen.WIDTH  / 2,
                    JARScreen.HEIGHT / 2,
                    LibAnchor.ECenterMiddle,
                    alpha,
                    false,
                    LibUI.SCALATION_NONE,
                    LibUI.SCALATION_NONE,
                    LibUI.ROTATION_NONE
                );
            }
        }

        public static final void animate()
        {
            counterPoints.animate();
            counterCoins.animate();
        }

        public static final void tick( boolean gamePaused )
        {
            //animate overlays
            if ( gamePaused )
            {
                overlayPanePause.fadeIn();
            }
            else
            {
                overlayPanePause.fadeOut();
            }

            //animate masks
            {
                //animate circle if not hidden
                if ( overlayMaskCircle.isAnimated() )
                {
                    //set overlay circle mask to player
                    LibPoint2D playerCenter = JARLevel.getCurrent().iPlayer.iBlock.getRect().getCenter();
                    playerCenter.iX -= JARCamera.getCurrent().getX();
                    playerCenter.iY -= JARCamera.getCurrent().getY();
                    overlayMaskCircle.setCenter( playerCenter.getX(), playerCenter.getY() );

                    //animate overlay circle
                    overlayMaskCircle.animate();
                }
            }
        }
    }
