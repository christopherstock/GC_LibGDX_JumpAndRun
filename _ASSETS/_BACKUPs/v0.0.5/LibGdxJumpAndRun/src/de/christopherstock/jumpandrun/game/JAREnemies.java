/*  $Id: JAREnemies.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.game.level.*;

    /*****************************************************************************
    *   Handles all flows for the enemies.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JAREnemies.java $
    *****************************************************************************/
    public class JAREnemies
    {
        public static final void animateEnemies( JARPlayer[] enemies )
        {
            //move all enemies towards the player
            for ( JARPlayer enemy : enemies )
            {
                if ( enemy.isAlive() )
                {
                    //enemy.moveRight();
                    if ( enemy.iBlock.getRect().iLeft > JARLevel.getCurrent().iPlayer.iBlock.getRect().iLeft )
                    {
                        //move left
                        enemy.moveLeft();

                        //clip
                        if ( enemy.iBlock.getRect().iLeft < JARLevel.getCurrent().iPlayer.iBlock.getRect().iLeft )
                        {
                            enemy.iBlock.getRect().iLeft = JARLevel.getCurrent().iPlayer.iBlock.getRect().iLeft;
                        }

                        //animate frame
                        enemy.iBlock.animate();
                    }
                    else if ( enemy.iBlock.getRect().iLeft < JARLevel.getCurrent().iPlayer.iBlock.getRect().iLeft )
                    {
                        //move right
                        enemy.moveRight();

                        //clip
                        if ( enemy.iBlock.getRect().iLeft > JARLevel.getCurrent().iPlayer.iBlock.getRect().iLeft )
                        {
                            enemy.iBlock.getRect().iLeft = JARLevel.getCurrent().iPlayer.iBlock.getRect().iLeft;
                        }

                        //animate frame
                        enemy.animateBlock();
                    }
                    else
                    {
                        //do not move but animate frame
                        enemy.animateBlock();
                    }

                    //handle enemy jumping / falling
                    enemy.handleY();
                }
            }
        }
    }
