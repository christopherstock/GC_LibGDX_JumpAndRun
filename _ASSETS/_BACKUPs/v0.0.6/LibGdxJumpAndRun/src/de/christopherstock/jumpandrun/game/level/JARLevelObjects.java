/*  $Id: JARLevelObjects.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game.level;

    import  java.util.*;
    import  de.christopherstock.jumpandrun.game.JARBlock.CollisionType;
    import  de.christopherstock.jumpandrun.game.object.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.JARScreen.DrawingLayer;
    import  de.christopherstock.libgdx.lib.math.geom.LibRect2D.*;

    /*****************************************************************************
    *   Bundles multiple walls to one compound object.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/level/JARLevelObjects.java $
    *****************************************************************************/
    public class JARLevelObjects
    {
        /*****************************************************************************
        *   Creates a wooden bridge.
        *****************************************************************************/
        public static final Vector<JARWall> createFloor( int x, int y, int width, JARImage imgWall, JARImage imgTopping )
        {
            Vector<JARWall> ret = new Vector<JARWall>();

            int drawX = x;

            //browse width
            for ( int i = 0; i < width; ++i )
            {
                //add wall
                ret.add( new JARWall( drawX, y, imgWall, CollisionType.EColliding, DrawingLayer.EBeforePlayer ) );

                //add topping if specified
                if ( imgTopping != null )
                {
                    ret.add( new JARWall( drawX, y - imgWall.getFrameHeight(), imgTopping, CollisionType.ENonColliding, DrawingLayer.EBeforePlayer ) );
                }

                //increase drawing location
                drawX += imgWall.getFrameWidth();
            }

            return ret;
        }

        /*****************************************************************************
        *   Creates a wooden bridge.
        *****************************************************************************/
        public static final Vector<JARWall> createBridge( int x, int y, int width )
        {
            Vector<JARWall> ret = new Vector<JARWall>();

            int drawX = x;

            //water
            drawX += 64;
            ret.add( new JARWall( drawX,        y,       JARImage.EWallWater1,              CollisionType.ENonColliding,    DrawingLayer.EBehindPlayer ) );

            //left socket
            drawX -= 64;
            ret.add( new JARWall( drawX,        y - 64,  JARImage.EWallWood1Ascending,      CollisionType.EColliding,       DrawingLayer.EBehindPlayer ) );
            ret.add( new JARWall( drawX,        y - 128, JARImage.EDecoRailing1Ascending,   CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );
            drawX += 64;

            //browse width
            for ( int i = 0; i < width; ++i )
            {
                drawX += 64;

                //railing
                ret.add( new JARWall( drawX,    y - 128, JARImage.EDecoRailing1,            CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );
                //rack
                ret.add( new JARWall( drawX,    y - 64,  JARImage.EWallWood1,               CollisionType.EColliding,       DrawingLayer.EBehindPlayer ) );
                //water
                drawX += 64;
                ret.add( new JARWall( drawX,    y,       JARImage.EWallWater1,              CollisionType.ENonColliding,    DrawingLayer.EBehindPlayer ) );
            }

            //right socket
            drawX += 64;
            ret.add( new JARWall( drawX,        y - 64,  JARImage.EWallWood1Descending,     CollisionType.EColliding,       DrawingLayer.EBehindPlayer ) );
            ret.add( new JARWall( drawX,        y - 128, JARImage.EDecoRailing1Descending,  CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );

            //small and arced wood
            drawX += 64;
            ret.add( new JARWall( drawX,        y,       JARImage.EWallWood1Small,          CollisionType.EColliding,       DrawingLayer.EBehindPlayer ) );
            ret.add( new JARWall( drawX - 64,   y,       JARImage.EWallWood1ArcRight,       CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );

            drawX = x;
            ret.add( new JARWall( drawX,        y,       JARImage.EWallWood1Small,          CollisionType.EColliding,       DrawingLayer.EBehindPlayer ) );
            ret.add( new JARWall( drawX + 64,   y,       JARImage.EWallWood1ArcLeft,        CollisionType.ENonColliding,    DrawingLayer.EBeforePlayer ) );

            return ret;
        }

        /*****************************************************************************
        *   Creates a stair of the specified length.
        *****************************************************************************/
        public static final Vector<JARWall> createElevation( int x, int y, int width, Elevation elevation, JARImage imgWall, JARImage imgTopping )
        {
            Vector<JARWall> ret = new Vector<JARWall>();

            int drawX = x;
            int drawY = y;

            //browse width
            for ( int i = 0; i < width; ++i )
            {
                //floor
                ret.add( new JARWall( drawX, drawY, imgWall, CollisionType.EColliding, DrawingLayer.EBehindPlayer ) );

                //topping if desired
                if ( imgTopping != null )
                {
                    ret.add( new JARWall( drawX, drawY - imgWall.getFrameHeight() / 2, imgTopping, CollisionType.ENonColliding, DrawingLayer.EBeforePlayer ) );
                }

                drawX += imgWall.getFrameWidth();

                switch ( elevation )
                {
                    case EAscending:
                    {
                        drawY -= imgWall.getFrameHeight() / 2;
                        break;
                    }

                    case EDescending:
                    {
                        drawY += imgWall.getFrameHeight() / 2;
                        break;
                    }

                    default:
                    {
                        break;
                    }
                }
            }

            return ret;
        }

        /*****************************************************************************
        *   Creates a block of walls with the specified width and height.
        *****************************************************************************/
        public static final Vector<JARWall> createBlock( int x, int y, int width, int height, JARImage imgFill, CollisionType colliding )
        {
            Vector<JARWall> ret = new Vector<JARWall>();

            int drawX = 0;
            int drawY = 0;

            //browse height
            drawY = y;
            for ( int i = 0; i < height; ++i )
            {
                //browse width
                drawX = x;
                for ( int j = 0; j < width; ++j )
                {
                    //if collision is enabled, enable collision for all border blocks
                    boolean collision =
                    (
                            colliding == CollisionType.EColliding
                        &&  (
                                    i == 0
                                ||  j == 0
                                ||  i == height - 1
                                ||  j == width  - 1
                            )
                    );

                    ret.add( new JARWall( drawX, drawY, imgFill, ( collision ? CollisionType.EColliding : CollisionType.ENonColliding ), DrawingLayer.EBehindPlayer ) );

                    drawX += imgFill.getFrameWidth();
                }
                drawY += imgFill.getFrameHeight() / 2;
            }

            return ret;
        }
    }
