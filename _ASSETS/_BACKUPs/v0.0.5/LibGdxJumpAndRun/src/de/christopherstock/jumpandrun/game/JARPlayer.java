/*  $Id: JARPlayer.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.game.JARBlock.*;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.jumpandrun.hid.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   Represents one game character.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARPlayer.java $
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
            super( aGameObjectType, aPlayerType.iSpriteStandLeft );

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
            iBlock.setCollision( false );

            //set standing sprite
            switch ( iStandingDirection )
            {
                case ELeft:
                {
                    iBlock.setNewSprite( iPlayerType.iSpriteStandLeft );
                    break;
                }
                case ERight:
                {
                    iBlock.setNewSprite( iPlayerType.iSpriteStandRight );
                    break;
                }
            }
        }

        public void moveLeft()
        {
            //alter standing direction
            iStandingDirection = StandingDirection.ELeft;

            //translate block left
            iBlock.moveLeft( ( iBlock.isStanding() ? iPlayerType.iSpeedX : iPlayerType.iSpeedJumpX ) );

            //set new sprite
            iBlock.setNewSprite( iPlayerType.iSpriteWalkLeft );
        }

        public void moveRight()
        {
            //alter standing direction
            iStandingDirection = StandingDirection.ERight;

            //translate block right
            iBlock.moveRight( ( iBlock.isStanding() ? iPlayerType.iSpeedX : iPlayerType.iSpeedJumpX ) );

            //set new sprite
            iBlock.setNewSprite( iPlayerType.iSpriteWalkRight );
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
            iPlayerType.iDieSound.play();
        }

        public StandingDirection getStandingDirection()
        {
            return iStandingDirection;
        }
    }
