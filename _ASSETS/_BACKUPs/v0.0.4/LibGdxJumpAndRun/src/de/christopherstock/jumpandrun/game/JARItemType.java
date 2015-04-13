/*  $Id: JARItemType.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;

    /*****************************************************************************
    *   Represents a collectable item.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARItemType.java $
    *****************************************************************************/
    public class JARItemType
    {
        public static final int ITEM_FX_POINTS_100         = 0;
        public static final int ITEM_FX_POINTS_200         = 1;
        public static final int ITEM_FX_POINTS_300         = 2;
        public static final int ITEM_FX_POINTS_400         = 3;
        public static final int ITEM_FX_POINTS_500           = 4;
        public static final int ITEM_FX_COIN_1               = 5;

        public static        JARItemType ITEM_TYPE_APPLE            = null;
        public static        JARItemType ITEM_TYPE_CHERRY           = null;
        public static        JARItemType ITEM_TYPE_ORANGE           = null;
        public static        JARItemType ITEM_TYPE_PEAR             = null;
        public static        JARItemType ITEM_TYPE_STRAWBERRY       = null;
        public static        JARItemType ITEM_TYPE_COIN             = null;

        public  boolean     iSwings    = false;
        public  JARSprite   iSprite    = null;
        public  int         iFx        = 0;
        public  JARSound    iPickSound = null;

        public JARItemType( boolean aSwings, JARSprite aSprite, int aFx, JARSound aPickSound )
        {
            iSwings     = aSwings;
            iSprite     = aSprite;
            iFx         = aFx;
            iPickSound  = aPickSound;
        }

        public static final void init()
        {
            ITEM_TYPE_COIN         = new JARItemType( false, JARSprite.COIN,        ITEM_FX_COIN_1,     JARSound.EPickUp );
            ITEM_TYPE_APPLE        = new JARItemType( true,  JARSprite.APPLE,       ITEM_FX_POINTS_500, JARSound.EPickUp );
            ITEM_TYPE_CHERRY       = new JARItemType( true,  JARSprite.CHERRY,      ITEM_FX_POINTS_100, JARSound.EPickUp );
            ITEM_TYPE_ORANGE       = new JARItemType( true,  JARSprite.ORANGE,      ITEM_FX_POINTS_200, JARSound.EPickUp );
            ITEM_TYPE_PEAR         = new JARItemType( true,  JARSprite.PEAR,        ITEM_FX_POINTS_300, JARSound.EPickUp );
            ITEM_TYPE_STRAWBERRY   = new JARItemType( true,  JARSprite.STRAWBERRY,  ITEM_FX_POINTS_400, JARSound.EPickUp );
        }
    }
