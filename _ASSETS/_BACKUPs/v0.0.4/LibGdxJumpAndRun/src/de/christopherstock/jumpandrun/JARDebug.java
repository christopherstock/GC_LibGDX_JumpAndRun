/*  $Id: JARDebug.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.jumpandrun;

    import  com.badlogic.gdx.utils.*;

    /**************************************************************************************
    *   The version history system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    **************************************************************************************/
    public class JARDebug
    {
        //specified debug groups
        public      static          Logger          hid                                     = new Logger( "hid",        Logger.INFO     );
        public      static          Logger          sound                                   = new Logger( "sound",      Logger.INFO     );
        public      static          Logger          imageLoad                               = new Logger( "imageLoad",  Logger.NONE     );

        //paramount debug groups
        public      static          Logger          bugfix                                  = new Logger( "bugfix",     Logger.INFO     );
        public      static          Logger          major                                   = new Logger( "major",      Logger.INFO     );
        public      static          Logger          err                                     = new Logger( "err",        Logger.ERROR    );

        public      static          boolean         LOG_TO_SCREEN                           = true;  //( Main.MODE == Main.MODE_DEBUG );

        public      static          boolean         ALWAYS_SHOW_COUNTERS                    = false;

        public      static          boolean         DRAW_DEBUG_BLOCKS_PLAYER                = true;
        public      static          boolean         DRAW_DEBUG_BLOCKS_WALL                  = true;
        public      static          boolean         DRAW_DEBUG_BLOCKS_ENEMY                 = false;
        public      static          boolean         DRAW_DEBUG_BLOCKS_ITEM                  = false;
    }
