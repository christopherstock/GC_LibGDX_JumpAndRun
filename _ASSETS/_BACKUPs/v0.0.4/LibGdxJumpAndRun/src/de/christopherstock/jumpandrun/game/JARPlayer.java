/*  $Id: JARPlayer.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  com.badlogic.gdx.*;
    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.game.JARBlock.*;
    import  de.christopherstock.jumpandrun.hid.*;
    import  de.christopherstock.jumpandrun.io.*;
    import de.christopherstock.libgdx.lib.math.geom.*;

    /*****************************************************************************
    *   Represents one game character.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARPlayer.java $
    *****************************************************************************/
    public class JARPlayer extends JARGameObject
    {
        public static enum StandingDirection
        {
            ELeft,
            ERight,
            ;
        }

        private                     JARPlayerTemplate   iTemplate                   = null;
        private                     float               iSpeedX                     = 0;
        private                     float               iSpeedJumpX                 = 0;
        private                     float               iJumpPowerY                 = 0;
        public                      JARBlock            iBlock                      = null;
        private                     boolean             iDead                       = false;
        private                     StandingDirection   iStandingDirection          = null;

        /*****************************************************************************
        *   Initialize and reset human player.
        *****************************************************************************/
        public JARPlayer( int x, GameObjectType type, JARPlayerTemplate aTemplate )
        {
            super( type, aTemplate.iSpriteStandRight );

            iTemplate           = aTemplate;

            iSpeedX             = iTemplate.iSpeedX;
            iSpeedJumpX         = iTemplate.iSpeedJumpX;
            iJumpPowerY         = iTemplate.iJumpPowerY;

            iBlock              = new JARBlock( this, 0, 0, LibRect2D.Elevation.ENone, false, PassThrough.ENo );
            iDead               = false;
            iStandingDirection  = ( type == GameObjectType.EPlayer ? StandingDirection.ERight : StandingDirection.ELeft );

            //set player startup position
            JARLevel.current.bringRectOnStartup( iBlock.getRect(), x );
        }

        /*****************************************************************************
        *   Handle the keys the user has pressed.
        *****************************************************************************/
        public void handlePlayerKeys()
        {
            //alter player position
            if ( JARKey.KEY_LEFT.hold )
            {
                moveLeft();
            }
            if ( JARKey.KEY_RIGHT.hold )
            {
                moveRight();
            }
            if ( !JARKey.KEY_LEFT.hold && !JARKey.KEY_RIGHT.hold )
            {
                standStill();
            }

            //check if player shall jump - release up-key afterwards
            if ( JARKey.KEY_UP.hold )
            {
                jump();
                JARKey.KEY_UP.ignoreTillNextRelease();
            }

            //check quit game
            if ( JARKey.KEY_ESC.hold )
            {
                Gdx.app.exit();
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
                    iBlock.setNewSprite( iTemplate.iSpriteStandLeft );
                    break;
                }
                case ERight:
                {
                    iBlock.setNewSprite( iTemplate.iSpriteStandRight );
                    break;
                }
            }
        }

        public void moveLeft()
        {
            //alter standing direction
            iStandingDirection = StandingDirection.ELeft;

            //translate block left
            iBlock.moveLeft( ( iBlock.isStanding() ? iSpeedX : iSpeedJumpX ) );

            //set new sprite
            iBlock.setNewSprite( iTemplate.iSpriteWalkLeft );
        }

        public void moveRight()
        {
            //alter standing direction
            iStandingDirection = StandingDirection.ERight;

            //translate block right
            iBlock.moveRight( ( iBlock.isStanding() ? iSpeedX : iSpeedJumpX ) );

            //set new sprite
            iBlock.setNewSprite( iTemplate.iSpriteWalkRight );
        }

        public void jump()
        {
            iBlock.jump( iJumpPowerY );
        }

        public void animateBlock()
        {
            iBlock.animateBlock();
        }

        public boolean isAlive()
        {
            return !iDead;
        }

        public void kill()
        {
            iDead = true;

            //gain points
            JARLevel.current.gainPoints( 250 );

            //play gnash fx
            JARSound.EKillEnemy.play();
        }
    }
