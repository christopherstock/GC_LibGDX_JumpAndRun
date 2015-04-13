/*  $Id: JARGame.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.JARSettings.Feature;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.jumpandrun.game.object.*;
    import  de.christopherstock.jumpandrun.hid.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.math.geom.*;
    import  de.christopherstock.libgdx.lib.ui.*;
    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.ui.JAROverlayMask.*;
    import  de.christopherstock.jumpandrun.ui.JARScreen.DrawingLayer;
    import  de.christopherstock.jumpandrun.ui.tween.*;

    /*****************************************************************************
    *   Builds the gamestate.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARGame.java $
    *****************************************************************************/
    public class JARGame
    {
        private         static          boolean         paused                  = false;

        public static final void togglePause()
        {
            paused = !paused;
        }

        public static final void launchNewLevel( JARLevelType newLevelType )
        {
            //init HUD
            JARHUD.init();

            //reset level
            JARLevel.initLevel( newLevelType );

            //reset camera
            JARCamera.getCurrent().reset();

            //start bg sound
            //soundBg1.play();
        }

        public static final void tick()
        {
            //only if not paused and if the pause-pane is hidden
            if ( !paused && JARHUD.overlayPanePause.isHidden() )
            {
                //handle player's keys
                JARLevel.getCurrent().iPlayer.handlePlayerKeys();

                //handle player jumping / falling
                JARLevel.getCurrent().iPlayer.handleY();

                //check items collision with player
                JARItem.checkCollisionAll( JARLevel.getCurrent().iItems, JARLevel.getCurrent().iPlayer.iBlock.getRect(), JARCamera.getCurrent() );

                //animate items
                JARItem.animateAll( JARLevel.getCurrent().iItems );

                //animate enemies
                JARPlayer.animateEnemies( JARLevel.getCurrent().iEnemies );

                //animate walls
                JARWall.animateAll( JARLevel.getCurrent().iWalls );

                //animate player
                JARLevel.getCurrent().iPlayer.iBlock.animate();

                //animate HUD
                JARHUD.animate();

                //update camera
                JARCamera.getCurrent().update();

                //animate tween test
                if ( Feature.TEST_TWEEN_ENGINE ) JARTweenExample.tick();
            }

            //animate overlays
            JARHUD.tick( paused );
        }

        public static final void drawGameScreen( SpriteBatch batch )
        {
            LibCoordinate2D camera = JARCamera.getCurrent();

            //clear screen
            LibDrawing.fillRect( batch, 0, 0, JARScreen.WIDTH, JARScreen.HEIGHT, Color.BLACK, false );

            //draw bg
            JARLevel.getCurrent().drawLevelBg( batch, camera );

            //draw walls ( behind player )
            JARLevel.getCurrent().drawLevelWalls( batch, camera, DrawingLayer.EBehindPlayer );

            //draw all unpicked items
            JARItem.drawAllUnpicked( batch, camera );

            //draw all enemies
            for ( JARPlayer enemy : JARLevel.getCurrent().iEnemies )
            {
                if ( enemy.isAlive() )
                {
                    enemy.iBlock.draw( batch, camera, LibUI.ALPHA_OPAQUE );
                }
                else
                {
                    enemy.iBlock.draw( batch, camera, 0.5f );
                }
            }

            //draw player
            JARLevel.getCurrent().iPlayer.iBlock.draw( batch, camera, LibUI.ALPHA_OPAQUE );

            //draw walls ( before player )
            JARLevel.getCurrent().drawLevelWalls( batch, camera, DrawingLayer.EBeforePlayer );
        }

        public static final void handleGlobalKeys()
        {
            //check quit
            if ( JARKey.KEY_M.isHold() )
            {
                Gdx.app.exit();
            }

            //check pause
            if ( JARKey.KEY_ESC.isHold() )
            {
                JARGame.togglePause();
                JARKey.KEY_ESC.ignoreTillNextRelease();
            }

            //check mask closing effect start
            if ( JARKey.KEY_N.isHold() )
            {
                JARHUD.overlayMaskCircle.start( MaskDirection.EClosing );
                JARKey.KEY_N.ignoreTillNextRelease();
            }

            //check mask opening effect start
            if ( JARKey.KEY_B.isHold() )
            {
                JARHUD.overlayMaskCircle.start( MaskDirection.EOpening );
                JARKey.KEY_B.ignoreTillNextRelease();
            }

            //check level change
            try
            {
                if ( JARKey.KEY_1.isHold() )
                {
                    JARGame.launchNewLevel( JARLevelType.values()[ 0 ] );
                    JARKey.KEY_1.ignoreTillNextRelease();
                }
                if ( JARKey.KEY_2.isHold() )
                {
                    JARGame.launchNewLevel( JARLevelType.values()[ 1 ] );
                    JARKey.KEY_2.ignoreTillNextRelease();
                }
                if ( JARKey.KEY_3.isHold() )
                {
                    JARGame.launchNewLevel( JARLevelType.values()[ 2 ] );
                    JARKey.KEY_3.ignoreTillNextRelease();
                }
                if ( JARKey.KEY_4.isHold() )
                {
                    JARGame.launchNewLevel( JARLevelType.values()[ 3 ] );
                    JARKey.KEY_4.ignoreTillNextRelease();
                }
                if ( JARKey.KEY_5.isHold() )
                {
                    JARGame.launchNewLevel( JARLevelType.values()[ 4 ] );
                    JARKey.KEY_5.ignoreTillNextRelease();
                }
            }
            catch ( Exception e )
            {
                JARDebug.major.error( "Caught an Exception on changing level!", e );
            }
        }

        public static final boolean isPaused()
        {
            return paused;
        }
    }
