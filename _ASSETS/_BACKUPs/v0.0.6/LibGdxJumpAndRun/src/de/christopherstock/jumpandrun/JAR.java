/*  $Id: JAR.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  de.christopherstock.jumpandrun.JARSettings.Feature;
    import  de.christopherstock.jumpandrun.JARSettings.General;
    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.game.object.*;
    import  de.christopherstock.jumpandrun.hid.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.jumpandrun.ui.tween.*;
    import  de.christopherstock.libgdx.lib.*;

    /***********************************************************************************************
    *   LibGDX's application class represents the point of entry.
    *
    *   TODO ASAP   Light/Lumination effects?
    *   TODO ASAP   Draw all non-colliding walls in fg after drawing all colliding walls.
    *   TODO ASAP   Nice frames for enemies.
    *   TODO ASAP   Nice frames for player.
    *   TODO HIGH   Death animation for killed enemies ( fall out of the screen etc. ).
    *   TODO HIGH   More enemy behaviours - Shy enemies that retreat on being attacked ( easy levels ). More player properties. Different enemy-properties: walk left/right endlessly etc. :p
    *   TODO HIGH   Snow and falling-down-leaves (ect) - effect. ( In front AND behind the walls! )
    *   TODO INIT   Modified surfaces ( slippery ice, sticky honey etc. ).
    *   TODO INIT   Improve moveLeft and moveRight?? use getNextLeftCollision()?? ( Provoke gaps on moving to a wall! )
    *   TODO LOW    Level map.
    *   TODO LOW    Main menu.
    *   TODO LOW    Create own nice bitmap font with the Hiero Bitmap font tool.
    *   TODO LOW    Player loses on falling out of the screen ( by level setting ).
    *   TODO LOW    New Elevation: rounded ( quarterpiped ) etc.
    *   TODO LOW    Own or enemy HPs?
    *   TODO LOW    Main State machine ( menu, level map, ingame etc. ).
    *   TODO WEAK   Preloader for all resources.
    *   TODO WEAK   Bundled items with serial pickup effects ( e.g. 10 bananas etc. ).
    *   TODO WEAK   Implement UncaughtExceptionHandler and Exception-E-Mails.
    *   TODO WEAK   Kill player on being touched by an enemy.
    *   TODO WEAK   HARD: Block enemy while ascending will stuck him!
    *   TODO WEAK   HARD: Disallow enemies to climb onto other enemies!
    *   TODO WEAK   Touchables for game state(s).
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    ***********************************************************************************************/
    public class JAR implements ApplicationListener
    {
        @Override
        public void create()
        {
            //acclaim
            JARDebug.major.info( "Welcome to [ " + JARVersion.getCurrentVersionDescription() + " ]" );

            //allow textures with dimensions that are not power of 2
            Texture.setEnforcePotImages( false );

            //init screen
            JARScreen.init();

            //load images
            JARImage.loadImages();

            //init sound engine
            JARSound.init();

            //init sprites
            JARSprite.init();

            //init player types
            JARPlayerType.init();

            //init fonts
            JARFont.init();

            //init item types
            JARItemType.init();

            //init tween test
            if ( Feature.TEST_TWEEN_ENGINE ) JARTweenExample.init();

            //init level
            JARGame.launchNewLevel( General.STARTUP_LEVEL );
        }

        @Override
        public void render()
        {
            //process key input
            JARKey.checkKeyInput();

            //handle global keys
            JARGame.handleGlobalKeys();

            //ticker state
            JARGame.tick();

            //draw
            JARScreen.draw();

            //delay main thread
            Lib.sleepCurrentThread( General.THREAD_DELAY_MILLIS );
        }

        @Override
        public void dispose()
        {
            //dispose all native resources
            JARImage.disposeAll();

            //dispose screen resources ( camera )
            JARScreen.dispose();

            //dispose sounds
            JARSound.disposeAll();

            //dispose fonts
            JARFont.disposeAll();
        }

        @Override
        public void resize( int width, int height )
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
