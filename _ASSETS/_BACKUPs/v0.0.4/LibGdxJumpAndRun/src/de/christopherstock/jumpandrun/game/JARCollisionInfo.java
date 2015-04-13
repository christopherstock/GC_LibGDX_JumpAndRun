/*  $Id: JARCollisionInfo.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    /*****************************************************************************
    *   Represents one colliding object.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARCollisionInfo.java $
    *****************************************************************************/
    public class JARCollisionInfo
    {
        public              float               iY                  = 0.0f;
        public              JARPlayer           iPlayer             = null;
        public              JARBlock            iBlock              = null;

        public JARCollisionInfo( float aY, JARPlayer aPlayer, JARBlock aBlock )
        {
            iY      = aY;
            iPlayer = aPlayer;
            iBlock  = aBlock;
        }
    }
