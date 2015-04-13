/*  $Id: JARWall.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.game.JARBlock.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.jumpandrun.ui.JARScreen.DrawingLayer;

    /*****************************************************************************
    *   Represents a wall in the level.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARWall.java $
    *****************************************************************************/
    public class JARWall extends JARGameObject
    {
        public                      DrawingLayer            iDrawingLayer               = null;
      //public                      boolean                 iAnimateFrames              = false;

        public JARWall( int aX, int aY, JARImage aImg, CollisionType aCollisionType, DrawingLayer aDrawingLayer )
        {
            super( GameObjectType.EWall, null );

            iSprite         = new JARSprite( aImg );
            iBlock          = new JARBlock( this, aX, aY, aImg.iElevation, false, aCollisionType );

            iDrawingLayer   = aDrawingLayer;
            //iAnimateFrames  = aAnimateFrames;
        }

        public static final void animateAll( JARWall[] walls )
        {
            //browse all walls
            for ( JARWall wall : walls )
            {
                wall.iBlock.animate();
            }
        }
    }
