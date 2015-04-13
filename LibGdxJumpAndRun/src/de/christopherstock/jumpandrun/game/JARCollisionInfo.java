/*  $Id: JARCollisionInfo.java 182 2013-09-30 14:45:09Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.game.object.*;

    /*****************************************************************************
    *   Represents one colliding object.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARCollisionInfo.java $
    *****************************************************************************/
    public class JARCollisionInfo
    {
        public              float               iY                  = 0.0f;

        //TODO ASAP to gameObject ??
        public              JARPlayer           iPlayer             = null;

        public              JARBlock            iBlock              = null;

        public JARCollisionInfo( float aY, JARPlayer aPlayer, JARBlock aBlock )
        {
            iY      = aY;
            iPlayer = aPlayer;
            iBlock  = aBlock;
        }
    }
