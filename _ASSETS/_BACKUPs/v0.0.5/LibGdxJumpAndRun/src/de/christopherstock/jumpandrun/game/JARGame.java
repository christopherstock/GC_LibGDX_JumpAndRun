/*  $Id: JARGame.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.jumpandrun.hid.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.ui.*;
    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.ui.JARScreen.DrawingLayer;

    /*****************************************************************************
    *   Builds the gamestate.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARGame.java $
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
            //only if not paused
            if ( !paused && JARHUD.overlayPause.isHidden() )
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
                JAREnemies.animateEnemies( JARLevel.getCurrent().iEnemies );

                //animate walls
                JARWall.animateAll( JARLevel.getCurrent().iWalls );

                //animate player
                JARLevel.getCurrent().iPlayer.iBlock.animate();

                //animate HUD
                JARHUD.animate();

                //update camera
                JARCamera.getCurrent().update();
            }

            //tick overlay
            if ( paused )
            {
                JARHUD.overlayPause.fadeIn();
            }
            else
            {
                JARHUD.overlayPause.fadeOut();
            }
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

            //check level change
            try
            {
                if ( JARKey.KEY_1.isHold() ) JARGame.launchNewLevel( JARLevelType.values()[ 0 ] );
                if ( JARKey.KEY_2.isHold() ) JARGame.launchNewLevel( JARLevelType.values()[ 1 ] );
                if ( JARKey.KEY_3.isHold() ) JARGame.launchNewLevel( JARLevelType.values()[ 2 ] );
                if ( JARKey.KEY_4.isHold() ) JARGame.launchNewLevel( JARLevelType.values()[ 3 ] );
                if ( JARKey.KEY_5.isHold() ) JARGame.launchNewLevel( JARLevelType.values()[ 4 ] );
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
