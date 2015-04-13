/*  $Id: JARPlayer.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game.object;

    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.game.JARBlock.*;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.jumpandrun.hid.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.math.geom.*;

    /*****************************************************************************
    *   Represents one game character.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/object/JARPlayer.java $
    *****************************************************************************/
    public class JARPlayer extends JARGameObject
    {
        public static enum StandingDirection
        {
            ELeft,
            ERight,
            ;
        }

        private                     JARPlayerType       iPlayerType                 = null;
        private                     boolean             iDead                       = false;
        private                     StandingDirection   iStandingDirection          = null;

        /*****************************************************************************
        *   Initialize and reset human player.
        *****************************************************************************/
        public JARPlayer( int aStartX, int aStartY, GameObjectType aGameObjectType, JARPlayerType aPlayerType, StandingDirection aStartupStandingDirection )
        {
            super( aGameObjectType, new JARSprite( aPlayerType.iImageStand ) );

            iPlayerType         = aPlayerType;
            iBlock              = new JARBlock( this, 0, 0, LibRect2D.Elevation.ENone, false, CollisionType.EColliding );
            iDead               = false;
            iStandingDirection  = aStartupStandingDirection;

            //set player startup position
            JARLevel.getCurrent().bringRectOnStartup( iBlock.getRect(), aStartX, aStartY );
        }

        /*****************************************************************************
        *   Handle the keys the user has pressed.
        *****************************************************************************/
        public void handlePlayerKeys()
        {
            //alter player position
            if ( JARKey.KEY_LEFT.isHold() )
            {
                moveLeft();
            }
            if ( JARKey.KEY_RIGHT.isHold() )
            {
                moveRight();
            }
            if ( !JARKey.KEY_LEFT.isHold() && !JARKey.KEY_RIGHT.isHold() )
            {
                standStill();
            }

            //check if player shall jump - release up-key afterwards
            if ( JARKey.KEY_UP.isHold() )
            {
                jump();
                JARKey.KEY_UP.ignoreTillNextRelease();
            }
        }

        public void handleY()
        {
            iBlock.handleY();
        }

        public void standStill()
        {
            iBlock.setCollisionMarker( false );
            setNewSprite( iPlayerType.iImageStand );
        }

        public void moveLeft()
        {
            //alter standing direction
            iStandingDirection = StandingDirection.ELeft;

            //translate block left
            iBlock.moveLeft( ( iBlock.isStanding() ? iPlayerType.iSpeedX : iPlayerType.iSpeedJumpX ) );

            //set walking sprite
            setNewSprite( iPlayerType.iImageWalk );
        }

        public void moveRight()
        {
            //alter standing direction
            iStandingDirection = StandingDirection.ERight;

            //translate block right
            iBlock.moveRight( ( iBlock.isStanding() ? iPlayerType.iSpeedX : iPlayerType.iSpeedJumpX ) );

            //set walking sprite
            setNewSprite( iPlayerType.iImageWalk );
        }

        public void jump()
        {
            iBlock.jump( iPlayerType.iJumpPowerY );
        }

        public void animateBlock()
        {
            iBlock.animate();
        }

        public boolean isAlive()
        {
            return !iDead;
        }

        public BlockState getBlockState()
        {
            return iBlock.getState();
        }

        public void kill()
        {
            iDead = true;

            //do not charge points here
            //JARLevel.current.gainPoints( 250 );

            //play gnash fx
            iPlayerType.iDieSound.playSound();
        }

        public StandingDirection getStandingDirection()
        {
            return iStandingDirection;
        }

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
