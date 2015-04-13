/*  $Id: JAREnemies.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.*;

    /*****************************************************************************
    *   Handles all flows for the enemies.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JAREnemies.java $
    *****************************************************************************/
    public class JAREnemies
    {
        public static final void animateEnemies( JARPlayer[] enemies )
        {
            //move all enemies towards the player
            for ( int i = 0; i < enemies.length; ++i )
            {
                if ( enemies[ i ].isAlive() )
                {
                    //enemies[ i ].moveRight();
                    if ( enemies[ i ].iBlock.getRect().iLeft > JARLevel.current.iPlayer.iBlock.getRect().iLeft )
                    {
                        //move left
                        enemies[ i ].moveLeft();

                        //animate frame
                        enemies[ i ].iBlock.animateBlock();
                    }
                    else if ( enemies[ i ].iBlock.getRect().iLeft < JARLevel.current.iPlayer.iBlock.getRect().iLeft )
                    {
                        //move right
                        enemies[ i ].moveRight();

                        //animate frame
                        enemies[ i ].animateBlock();
                    }
                    else
                    {
                        //do not move but animate frame
                        enemies[ i ].animateBlock();
                    }

                    //handle enemy jumping / falling
                    enemies[ i ].handleY();
                }
            }
        }
    }
