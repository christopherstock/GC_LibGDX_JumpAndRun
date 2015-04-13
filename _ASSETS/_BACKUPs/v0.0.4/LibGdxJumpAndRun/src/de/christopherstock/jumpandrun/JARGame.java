/*  $Id: JARGame.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun;

    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.ui.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;

    /*****************************************************************************
    *   Builds the gamestate.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/JARGame.java $
    *****************************************************************************/
    public class JARGame
    {
        public static final void initNewGame()
        {
            //init HUD
            JARHUD.init();

            //reset blocks
            JARLevel.initLevel();

            //start bg sound
            //soundBg1.play();
        }

        public static final void tickGame()
        {
            //handle player jumping / falling
            JARLevel.current.iPlayer.handleY();

            //check items collision with player
            JARItem.checkCollisionAll( JARLevel.current.iItems, JARLevel.current.iPlayer.iBlock.getRect() );

            //animate items
            JARItem.animateAll( JARLevel.current.iItems );

            //animate enemies
            JAREnemies.animateEnemies( JARLevel.current.iEnemies );

            //animate player
            JARLevel.current.iPlayer.iBlock.animateBlock();

            //animate HUD
            JARHUD.animate();
        }

        public static final void drawGameScreen( SpriteBatch batch )
        {
            //update camera
            JARCamera camera = JARCamera.getCurrent();

            //clear screen
            LibDrawing.fillCanvas( batch, Color.BLACK );

            //draw bg
            JARLevel.current.drawLevelBg( batch, camera );

            //draw level
            JARLevel.current.drawLevelFg( batch, camera );

            //draw items
            JARItem.drawAll( batch, JARLevel.current.iItems, camera );

            //draw all enemies
            for ( int i = 0; i < JARLevel.current.iEnemies.length; ++i )
            {
                if ( JARLevel.current.iEnemies[ i ].isAlive() ) JARLevel.current.iEnemies[ i ].iBlock.draw( batch, camera );
            }

            //draw player
            JARLevel.current.iPlayer.iBlock.draw( batch, camera );

            //draw bg overlay
            //JARHUD.drawBgOverlay();
        }
    }
