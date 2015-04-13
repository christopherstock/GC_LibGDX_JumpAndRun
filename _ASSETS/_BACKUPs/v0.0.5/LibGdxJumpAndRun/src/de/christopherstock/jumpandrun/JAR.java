/*  $Id: JAR.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  de.christopherstock.jumpandrun.JARSettings.General;
    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.hid.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.*;

    /***********************************************************************************************
    *   LibGDX's application class represents the point of entry.
    *
    *   TODO ASAP   Compound objects? ( five floor-walls to one calculated wall OR full block of wall ).
    *   TODO ASAP   Try screen overlay effect ( black mask with transparent form ).
    *   TODO ASAP   Nice frames for player and enemies?
    *   TODO ASAP   Libgdx-functions that skew image for dynamic creation of ascending and descending images?
    *   TODO HIGH   Prune all fixed sizes / magic numbers in JARLevelObject!
    *   TODO HIGH   Shy enemies that retreat on being attacked ( easy levels ). More player properties. Different enemy-properties: walk left/right endlessly etc. :p
    *   TODO HIGH   Snow and falling-down-leaves (ect) - effect. ( In front AND behind the walls! )
    *   TODO INIT   Modified surfaces ( slippery ice, sticky honey etc. ).
    *   TODO INIT   Combined scale- and alpha-effect on picking items etc.?
    *   TODO INIT   Improve moveLeft and moveRight?? use getNextLeftCollision()?? ( Provoke gaps on moving to a wall! )
    *   TODO LOW    Level map.
    *   TODO LOW    Main menu.
    *   TODO LOW    Create own nice bitmap font with the Hiero Bitmap font tool.
    *   TODO LOW    Player loses on falling out of the screen ( level setting ).
    *   TODO LOW    New Elevation: rounded ( quarterpiped ) etc.
    *   TODO LOW    Own or enemy HPs?
    *   TODO LOW    State machine.
    *   TODO WEAK   Preloader for all resources.
    *   TODO WEAK   Bundled items with serial pickup effects ( e.g. 10 bananas etc. ).
    *   TODO WEAK   Implement UncaughtExceptionHandler and Exception-E-Mails.
    *   TODO WEAK   Animate grass in the wind?
    *   TODO WEAK   Touchables for game state(s).
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
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
            JARImage.disposeImages();

            //dispose screen resources ( camera )
            JARScreen.dispose();

            //dispose sounds
            JARSound.dispose();

            //dispose fonts
            JARFont.dispose();
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
