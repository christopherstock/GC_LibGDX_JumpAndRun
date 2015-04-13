/*  $Id: JARPlayerTemplate.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.ui.*;

    /*****************************************************************************
    *   A template for an game character.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARPlayerTemplate.java $
    *****************************************************************************/
    public class JARPlayerTemplate
    {
        public          static          JARPlayerTemplate           USER                        = null;
        public          static          JARPlayerTemplate           ENEMY_WEAK                  = null;
        public          static          JARPlayerTemplate           ENEMY_STRONG                = null;

        public                          float                       iSpeedX                     = 0;
        public                          float                       iSpeedJumpX                 = 0;
        public                          float                       iJumpPowerY                 = 0;
        public                          JARSprite                   iSpriteWalkLeft             = null;
        public                          JARSprite                   iSpriteWalkRight            = null;
        public                          JARSprite                   iSpriteStandLeft            = null;
        public                          JARSprite                   iSpriteStandRight           = null;

        public JARPlayerTemplate( float aSpeedX, float aSpeedJumpX, float aJumpPowerY, JARSprite aSpriteWalkLeft, JARSprite aSpriteWalkRight, JARSprite aSpriteStandLeft, JARSprite aSpriteStandRight )
        {
            iSpeedX             = aSpeedX;
            iSpeedJumpX         = aSpeedJumpX;
            iJumpPowerY         = aJumpPowerY;
            iSpriteWalkLeft     = aSpriteWalkLeft;
            iSpriteWalkRight    = aSpriteWalkRight;
            iSpriteStandLeft    = aSpriteStandLeft;
            iSpriteStandRight   = aSpriteStandRight;
        }

        public static void init()
        {
            //user
            USER            = new JARPlayerTemplate( JARSettings.PLAYER_SPEED_WALKING_X, JARSettings.PLAYER_SPEED_JUMPING_X, JARSettings.PLAYER_JUMP_POWER_Y, JARSprite.PLAYER1_WALK_LEFT, JARSprite.PLAYER1_WALK_RIGHT, JARSprite.PLAYER1_STAND_LEFT, JARSprite.PLAYER1_STAND_RIGHT );

            //enemies
            ENEMY_WEAK      = new JARPlayerTemplate( 2, 2, 10.0f, JARSprite.ENEMY1_WALK_LEFT, JARSprite.ENEMY1_WALK_RIGHT, JARSprite.ENEMY1_WALK_LEFT, JARSprite.ENEMY1_WALK_LEFT );
            ENEMY_STRONG    = new JARPlayerTemplate( 5, 5, 10.0f, JARSprite.ENEMY1_WALK_LEFT, JARSprite.ENEMY1_WALK_RIGHT, JARSprite.ENEMY1_WALK_LEFT, JARSprite.ENEMY1_WALK_LEFT );


        }
    }
