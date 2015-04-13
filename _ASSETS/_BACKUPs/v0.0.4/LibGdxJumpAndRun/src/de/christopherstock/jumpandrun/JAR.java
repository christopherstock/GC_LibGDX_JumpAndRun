/*  $Id: JAR.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun;

    import  com.badlogic.gdx.ApplicationListener;
    import  de.christopherstock.jumpandrun.JARSettings.General;
    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.*;

    /***********************************************************************************************
    *   LibGDX's application class represents the point of entry.
    *
    *   TODO ASAP   Jumping needs key release.
    *   TODO ASAP   Setup own colors.
    *   TODO ASAP   Fix screen icon.
    *   TODO INIT   Enable initial scaling factor for each component, enemy, etc.
    *   TODO INIT   ZOOM-effect on picking items?? + alpha :p
    *   TODO WEAK   Touchables for game state(s)
    *   TODO WEAK   Improve moveLeft and moveRight! use getNextLeftCollision()?? ( Provoke gaps on moving to a wall! )
    *   TODO WEAK   nice frames for player and enemies?
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    ***********************************************************************************************/
    public class JAR implements ApplicationListener
    {
        @Override
        public void create()
        {
            //acclaim
            JARDebug.major.info( "Welcome to [" + JARVersion.getVersion() + "]" );

            //init screen and redundant canvas
            JARScreen.init();

            //load images
            JARImage.loadImages();

            //init sound engine
            JARSound.init();

            //init sprites
            JARSprite.init();

            //init player templates
            JARPlayerTemplate.init();

            //init fonts
            JARFont.init();

            //init item types
            JARItemType.init();

            //init level
            JARGame.initNewGame();
        }

        @Override
        public void render()
        {
            //process key input
            JARHID.checkKeyInput();

            //handle player's keys
            JARLevel.current.iPlayer.handlePlayerKeys();

            //ticker state
            JARGame.tickGame();

            //draw
            JARScreen.draw();

            //delay main thread
            Lib.sleepCurrentThread( General.THREAD_DELAY_MILLIS );
        }

        @Override
        public void dispose()
        {
            //dispose all native resources
            JARImage.disposeImages();

            //dispose screen resources ( camera )
            JARScreen.dispose();

            //dispose sounds
            JARSound.dispose();

            //dispose fonts
            JARFont.dispose();
        }

        @Override
        public void resize(int width, int height)
        {
        }

        @Override
        public void pause()
        {
        }

        @Override
        public void resume()
        {
        }
    }
