/*  $Id: JARPlayerType.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.JARSettings.Game;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;

    /*****************************************************************************
    *   All types of game characters.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARPlayerType.java $
    *****************************************************************************/
    public enum JARPlayerType
    {
        EUser(          Game.PLAYER_SPEED_WALKING_X,    Game.PLAYER_SPEED_JUMPING_X,    Game.PLAYER_JUMP_POWER_Y,   JARImage.EPlayerWalkLeft,   JARImage.EPlayerWalkRight,  JARImage.EPlayerStandLeft,  JARImage.EPlayerStandRight, JARSound.EKillEnemy ),
        EEnemyWeak(     2,                              2,                              10.0f,                      JARImage.EEnemy2WalkLeft,   JARImage.EEnemy2WalkRight,  JARImage.EEnemy2WalkLeft,   JARImage.EEnemy2WalkRight,  JARSound.EKillEnemy ),
        EEnemyStrong(   5,                              5,                              10.0f,                      JARImage.EEnemy1WalkLeft,   JARImage.EEnemy1WalkRight,  JARImage.EEnemy1WalkRight,  JARImage.EEnemy1WalkRight,  JARSound.EKillEnemy ),
        ;

        public                          float                       iSpeedX                     = 0;
        public                          float                       iSpeedJumpX                 = 0;
        public                          float                       iJumpPowerY                 = 0;
        public                          JARSprite                   iSpriteWalkLeft             = null;
        public                          JARSprite                   iSpriteWalkRight            = null;
        public                          JARSprite                   iSpriteStandLeft            = null;
        public                          JARSprite                   iSpriteStandRight           = null;
        public                          JARSound                    iDieSound                   = null;

        private JARPlayerType( float aSpeedX, float aSpeedJumpX, float aJumpPowerY, JARImage aSpriteWalkLeft, JARImage aSpriteWalkRight, JARImage aSpriteStandLeft, JARImage aSpriteStandRight, JARSound aDieSound )
        {
            iSpeedX             = aSpeedX;
            iSpeedJumpX         = aSpeedJumpX;
            iJumpPowerY         = aJumpPowerY;
            iSpriteWalkLeft     = new JARSprite( aSpriteWalkLeft    );
            iSpriteWalkRight    = new JARSprite( aSpriteWalkRight   );
            iSpriteStandLeft    = new JARSprite( aSpriteStandLeft   );
            iSpriteStandRight   = new JARSprite( aSpriteStandRight  );
            iDieSound           = aDieSound;
        }

        public static void init()
        {
        }
    }
