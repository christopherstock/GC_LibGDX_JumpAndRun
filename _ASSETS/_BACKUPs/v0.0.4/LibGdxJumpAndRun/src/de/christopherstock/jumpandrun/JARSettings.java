/*  $Id: JARSettings.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun;

    /***********************************************************************************************
    *   All settings.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    ***********************************************************************************************/
    public abstract class JARSettings
    {
        public static abstract class General
        {
            public  static      final       String          PROJECT_NAME                            = "LibGDX - Jump and Run engine";
            public  static      final       String          PROJECT_VERSION                         = JARVersion.values()[ 0 ].getClientVersionNumber();
            public  static      final       long            THREAD_DELAY_MILLIS                     = 0;
        }

        public static abstract class HUD
        {
            public  static      final       boolean         BOX_2D_TEST                             = false;

            public  static      final       float           HUD_SCREEN_WIDTH_RATIO                  = 0.2f;
            public  static      final       float           HUD_BORDER_RATIO                        = 0.1f;
            public  static      final       float           HUD_BUTTON_RATIO                        = 0.75f;
            public  static      final       float           HUD_AVATAR_RATIO                        = 0.5f;

            public  static      final       float           INDICATOR_UNIT_SELECTION_ROTATION       = 0.5f;
            public  static      final       float           INDICATOR_UNIT_MOVE_ROTATION            = 0.25f;
            public  static      final       float           INDICATOR_UNIT_ATTACK_ROTATION          = 0.25f;

            public  static      final       float           INDICATOR_BUILDING_SELECTION_ROTATION   = 0.5f;

            public  static      final       float           INDICATOE_ALPHA_DELTA                   = 0.075f;
        }

            //gravity
            public  static      final       float           GRAVITY_JUMP_UP                         = 1.1f;
            public  static      final       float           GRAVITY_FALL_DOWN                       = 2.0f;

            /** Speed in pixel per frame. */
            public  static      final       int             PLAYER_SPEED_WALKING_X                  = 10;
            public  static      final       int             PLAYER_SPEED_JUMPING_X                  = 10;
            public  static      final       int             PLAYER_AUTO_ASCEND_DISTANCE_Y           = 20;
            public  static      final       int             PLAYER_AUTO_DESCEND_DISTANCE_Y          = 20;

            /** Player's jump power in y-pixels per tick. */
            public  static      final       float           PLAYER_JUMP_POWER_Y                     = 18.0f;


            public  static      final       int             BLOCK_COLLISION_CHECK_STEP_X            = 1;

            public  static      final       int             HUD_COUNTER_TICKER_TIME_SHOW_HIDE       = 10;
            public  static      final       int             HUD_COUNTER_TICKER_TIME_STILL           = 50;
            public  static      final       float           BG_BLENDING                             = 0.5f;
            public  static      final       boolean         scrollBgImageParallax                   = true;
            /* Number of delay ticks between two image-frames. */
            public  static      final       int             BLOCK_FRAME_ANIMATION_SPEED             = 5;
            /* Swing animation in degrees per tick. */
            public  static      final       int             BLOCK_SWING_ANIMATION_SPEED             = 2;
            public  static      final       int             BLOCK_ANGLE_MIN                         = 0;
            public  static      final       int             BLOCK_ANGLE_MAX                         = 180;
            public  static      final       int             BLOCK_ANGLE_RADIUS_X                    = 20;
            public  static      final       int             BLOCK_ANGLE_RADIUS_Y                    = 35;
    }
