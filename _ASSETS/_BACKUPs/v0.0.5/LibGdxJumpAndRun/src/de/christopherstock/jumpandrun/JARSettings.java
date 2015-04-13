/*  $Id: JARSettings.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun;

    import  com.badlogic.gdx.graphics.*;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /***********************************************************************************************
    *   All settings for the jump and run engine.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    ***********************************************************************************************/
    public abstract class JARSettings
    {
        public static abstract class General
        {
            public  static      final       String          PROJECT_NAME                            = "LibGDX - Jump and Run engine";
            public  static      final       String          PROJECT_VERSION                         = JARVersion.values()[ 0 ].getClientVersionNumber();
            public  static      final       long            THREAD_DELAY_MILLIS                     = 0;
            public  static      final       JARLevelType    STARTUP_LEVEL                           = JARLevelType.EEnchantedWoods;
        }

        public static abstract class Debug
        {
            public  static      final       boolean         DRAW_DEBUG_BLOCKS_PLAYER                = false;
            public  static      final       boolean         DRAW_DEBUG_BLOCKS_WALL                  = false;
            public  static      final       boolean         DRAW_DEBUG_BLOCKS_ENEMY                 = false;
            public  static      final       boolean         DRAW_DEBUG_BLOCKS_ITEM                  = false;

            public  static      final       boolean         DRAW_DEBUG_SCREEN_REFERENCE_LINES       = false;
        }

        public static abstract class HUD
        {
            public  static      final       int             OFFSET_HUD_BORDER_X                     = 30;
            public  static      final       int             OFFSET_HUD_BORDER_Y                     = 30;

            public  static      final       int             OFFSET_HUD_COUNTER_MARGIN_X             = 15;

            public  static      final       int             TICKS_PICKED_ITEMS_MOVE                 = 35;
            public  static      final       int             TICKS_PICKED_ITEMS_PAUSE_AFTER_MOVED    = 30;
            public  static      final       int             TICKS_PICKED_ITEMS_FADE_OUT             = 15;

            public  static      final       int             HUD_COUNTER_TICKER_TIME_SHOW_HIDE       = 20;
            public  static      final       int             HUD_COUNTER_TICKER_TIME_VISIBLE         =
            (
                    TICKS_PICKED_ITEMS_MOVE
                +   TICKS_PICKED_ITEMS_PAUSE_AFTER_MOVED
                +   TICKS_PICKED_ITEMS_FADE_OUT
            );

            public  static      final       int             OVERLAY_PAUSE_TICKS_SHOW_HIDE           = 10;
        }

        public static abstract class Colors
        {
            public  static      final       Color           COLOR_PAUSE_OVERLAY                     = LibUI.colorFromRGBA( 0x0000007f );
            public  static      final       Color           COLOR_REFERENCE_LINES                   = LibUI.colorFromRGBA( 0x6565ffff );
            public  static      final       Color           COLOR_BLOCK_COLLIDING                   = LibUI.colorFromRGBA( 0xcc5555ff );
            public  static      final       Color           COLOR_BLOCK_DEFAULT                     = LibUI.colorFromRGBA( 0x777777ff );
        }

        public static abstract class Performance
        {
            public  static      final       boolean         PARALLAX_BG_SCROLLING                   = true;
        }

        public static abstract class Camera
        {
            public  static      final       float           RATIO_CENTERING_X_HEADING_LEFT          = 0.55f;
            public  static      final       float           RATIO_CENTERING_X_HEADING_RIGHT         = 0.15f;
            public  static      final       float           RATIO_CENTERING_Y_STANDING              = 0.7f;
            public  static      final       float           CAM_SPEED_X                             = 12.0f;
            public  static      final       float           CAM_SPEED_Y                             = 8.0f;

            public  static      final       boolean         BUFFERED_CAMERA_HORIZONTAL              = true;
            public  static      final       float           BUFFERED_CAMERA_HORIZONTAL_RATIO        = 0.1f;
            public  static      final       float           BUFFERED_CAMERA_MAX_SPEED_X             = 16.0f;
            public  static      final       float           BUFFERED_CAMERA_MIN_SPEED_X             = 2.0f;

            public  static      final       boolean         BUFFERED_CAMERA_VERTICAL                = true;
            public  static      final       float           BUFFERED_CAMERA_VERTICAL_RATIO          = 0.1f;
            public  static      final       float           BUFFERED_CAMERA_MAX_SPEED_Y             = 14.0f;
            public  static      final       float           BUFFERED_CAMERA_MIN_SPEED_Y             = 1.0f;

            public  static      final       boolean         CAM_FOLLOWS_FALLING_PLAYER              = true;
        }

        public static abstract class Game
        {
            /** Walking speed in pixel per frame. */
            public  static      final       float           PLAYER_SPEED_WALKING_X                  = 8.0f;
            /** Speed for jumping. */
            public  static      final       float           PLAYER_SPEED_JUMPING_X                  = 8.0f;
            /** Player's jump power in y-pixel for 1st jump tick. */
            public  static      final       float           PLAYER_JUMP_POWER_Y                     = 22.5f;

            /** Gravity for jumping up. */
            public  static      final       float           GRAVITY_JUMP_UP                         = 1.1f;
            /** Gravity for falling down. */
            public  static      final       float           GRAVITY_FALL_DOWN                       = 1.5f;

            public  static      final       float           PLAYER_AUTO_ASCEND_DISTANCE_Y           = 20.0f;
            public  static      final       float           PLAYER_AUTO_DESCEND_DISTANCE_Y          = 20.0f;

            public  static      final       int             BLOCK_COLLISION_CHECK_STEP_X            = 1;

            /* Number of delay ticks between two image-frames. */
            public  static      final       int             BLOCK_FRAME_ANIMATION_SPEED             = 10;
            /* Swing animation in degrees per tick. */
            public  static      final       int             BLOCK_SWING_ANIMATION_SPEED             = 2;
            public  static      final       int             BLOCK_ANGLE_MIN                         = 0;
            public  static      final       int             BLOCK_ANGLE_MAX                         = 180;
            public  static      final       int             BLOCK_ANGLE_RADIUS_X                    = 20;
            public  static      final       int             BLOCK_ANGLE_RADIUS_Y                    = 35;
        }
    }
