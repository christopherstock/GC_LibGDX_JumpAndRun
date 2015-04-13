/*  $Id: JARWall.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import de.christopherstock.jumpandrun.game.JARBlock.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import de.christopherstock.libgdx.lib.math.geom.*;

    /*****************************************************************************
    *   Represents a wall in the level.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARWall.java $
    *****************************************************************************/
    public class JARWall extends JARGameObject
    {
        public static final int WALL_STONE_1                   = 0;
        public static final int WALL_STONE_2                   = 1;
        public static final int WALL_STONE_3                   = 2;
        public static final int WALL_STONE_4                   = 3;
        public static final int WALL_STONE_5                   = 4;
        public static final int WALL_STONE_6                   = 5;

        public JARBlock iBlock = null;

        public JARWall( int x, int y, int type, LibRect2D.Elevation elevation, PassThrough climbable )
        {
            super( GameObjectType.EWall, null );

            JARImage wallImg = null;
            switch ( type )
            {
                case JARWall.WALL_STONE_1:     wallImg = JARImage.GAME_FLOOR_STONES_1;    break;
                case JARWall.WALL_STONE_2:     wallImg = JARImage.GAME_FLOOR_STONES_2;    break;
                case JARWall.WALL_STONE_3:     wallImg = JARImage.GAME_FLOOR_STONES_3;    break;
                case JARWall.WALL_STONE_4:     wallImg = JARImage.GAME_FLOOR_STONES_4;    break;
                case JARWall.WALL_STONE_5:     wallImg = JARImage.GAME_FLOOR_STONES_5;    break;
                case JARWall.WALL_STONE_6:     wallImg = JARImage.GAME_FLOOR_STONES_6;    break;
            }

            iSprite = new JARSprite( wallImg, 1, 1, 1 );
            iBlock   = new JARBlock( this, x, y, elevation, false, climbable );
        }
    }
