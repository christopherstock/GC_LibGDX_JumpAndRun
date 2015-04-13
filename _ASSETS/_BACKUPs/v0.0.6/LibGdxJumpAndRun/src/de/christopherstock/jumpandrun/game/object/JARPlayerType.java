/*  $Id: JARPlayerType.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game.object;

    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.io.*;

    /*****************************************************************************
    *   All types of game characters.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/object/JARPlayerType.java $
    *****************************************************************************/
    public enum JARPlayerType
    {
        EUser(                  JARSettings.Player.PLAYER_SPEED_WALKING_X,    JARSettings.Player.PLAYER_SPEED_JUMPING_X,    JARSettings.Player.PLAYER_JUMP_POWER_Y,   JARImage.EPlayerWalkLeft,           JARImage.EPlayerStandLeft,          JARSound.EKillEnemy     ),

        EEnemyKremlinGreen(     2,                              2,                              10.0f,                      JARImage.EEnemyKremlinGreenWalk,    JARImage.EEnemyKremlinGreenWalk,    JARSound.EKillEnemy         ),
        EEnemyKRool(            5,                              5,                              10.0f,                      JARImage.EEnemyKRoolWalk,           JARImage.EEnemyKRoolWalk,           JARSound.EKillEnemy         ),
        EEnemyHedgehog(         2,                              5,                              10.0f,                      JARImage.EEnemyHedgehogWalk,        JARImage.EEnemyHedgehogWalk,        JARSound.EKillEnemy         ),
        EEnemyRat(              2,                              5,                              10.0f,                      JARImage.EEnemyRatWalk,             JARImage.EEnemyRatWalk,             JARSound.EKillEnemy         ),
        EEnemyBigGuy(           2,                              5,                              10.0f,                      JARImage.EEnemyBigGuyWalk,          JARImage.EEnemyBigGuyWalk,          JARSound.EKillEnemy         ),
        ;

        public                          float                       iSpeedX                     = 0;
        public                          float                       iSpeedJumpX                 = 0;
        public                          float                       iJumpPowerY                 = 0;
        public                          JARImage                    iImageWalk                  = null;
        public                          JARImage                    iImageStand                 = null;
        public                          JARSound                    iDieSound                   = null;

        private JARPlayerType( float aSpeedX, float aSpeedJumpX, float aJumpPowerY, JARImage aImageWalkLeft, JARImage aImageStandLeft, JARSound aDieSound )
        {
            iSpeedX         = aSpeedX;
            iSpeedJumpX     = aSpeedJumpX;
            iJumpPowerY     = aJumpPowerY;
            iImageWalk      = aImageWalkLeft;
            iImageStand     = aImageStandLeft;
            iDieSound       = aDieSound;
        }

        public static void init()
        {
        }
    }
