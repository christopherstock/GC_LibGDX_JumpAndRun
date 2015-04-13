/*  $Id: JARWall.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game.object;

    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.game.JARBlock.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.jumpandrun.ui.JARScreen.DrawingLayer;

    /*****************************************************************************
    *   Represents a wall in the level.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/object/JARWall.java $
    *****************************************************************************/
    public class JARWall extends JARGameObject
    {
        public                      DrawingLayer            iDrawingLayer               = null;


        public JARWall( int aX, int aY, JARImage aImg, CollisionType aCollisionType, DrawingLayer aDrawingLayer )
        {
            super( GameObjectType.EWall, new JARSprite( aImg ) );

            iBlock          = new JARBlock( this, aX, aY, aImg.getElevation(), false, aCollisionType );

            iDrawingLayer   = aDrawingLayer;
        }

        public static final void animateAll( JARWall[] walls )
        {
            //browse all walls
            for ( JARWall wall : walls )
            {
                //animate frame and swing
                wall.iBlock.animate();

                //skew specified walls
                switch ( wall.iSprite.getImage() )
                {
                    case EWallGrass1Topping:
                    {
                        //skew!
                        wall.iSprite.animateSkewX1X4();



                        break;
                    }

                    default:
                    {
                        break;
                    }
                }
            }
        }
    }
