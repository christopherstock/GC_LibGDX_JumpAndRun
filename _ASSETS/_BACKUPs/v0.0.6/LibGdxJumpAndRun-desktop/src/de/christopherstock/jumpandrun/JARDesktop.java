/*  $Id: JARDesktop.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.jumpandrun;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.Files.*;
    import  com.badlogic.gdx.backends.lwjgl.LwjglApplication;
    import  com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
    import  de.christopherstock.jumpandrun.io.*;

    /***********************************************************************************************
    *   The main class for the j2SE platform.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    ***********************************************************************************************/
    public class JARDesktop
    {
        /***********************************************************************************************
        *   A reference to the singleton instance of the application.
        ***********************************************************************************************/
        protected       static      LwjglApplication    appInstance         = null;

        /***********************************************************************************************
        *   The entry point of the J2SE app.
        ***********************************************************************************************/
        public static void main( String[] args )
        {
            //configure the app
            LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
            {
                cfg.title       = JARVersion.getCurrentVersionDescription();
                cfg.useGL20     = false;
                cfg.width       = 854;
                cfg.height      = 480;
                cfg.resizable   = false;

                //set window icon
                cfg.addIcon( JARPath.EImageGlobal.getPath() + "/EWindowIcon32.png", FileType.Internal );
                cfg.addIcon( JARPath.EImageGlobal.getPath() + "/EWindowIcon16.png", FileType.Internal );
            }

            //prepare the app
            ApplicationListener al = new JAR();

            //create the app
            appInstance = new LwjglApplication( al, cfg );
        }
    }
