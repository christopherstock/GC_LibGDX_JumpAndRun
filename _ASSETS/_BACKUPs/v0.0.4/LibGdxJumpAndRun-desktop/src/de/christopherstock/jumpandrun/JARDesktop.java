/*  $Id: JARDesktop.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.jumpandrun;

    import  com.badlogic.gdx.backends.lwjgl.LwjglApplication;
    import  com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

    /***********************************************************************************************
    *   The main class for the j2se platform.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    ***********************************************************************************************/
    public class JARDesktop
    {
        protected       static      LwjglApplication    appInstance         = null;

        public static void main( String[] args )
        {
            LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

            cfg.title       = JARVersion.getVersion();
            cfg.useGL20     = false;
            cfg.width       = 854;
            cfg.height      = 480;
            cfg.resizable   = false;

            appInstance = new LwjglApplication( new JAR(), cfg );
        }
    }
