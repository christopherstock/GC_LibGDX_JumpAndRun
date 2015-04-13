/*  $Id: JARCollisionInfo.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    /*****************************************************************************
    *   Represents one colliding object.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARCollisionInfo.java $
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
