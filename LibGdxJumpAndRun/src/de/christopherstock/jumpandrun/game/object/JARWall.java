/*  $Id: JARWall.java 182 2013-09-30 14:45:09Z jenetic.bytemare@googlemail.com $
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
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/object/JARWall.java $
    *****************************************************************************/
    public class JARWall extends JARGameObject
    {
        public                      DrawingLayer            iDrawingLayer               = null;

        public JARWall( int aX, int aY, JARImage aImg, CollisionType aCollisionType, DrawingLayer aDrawingLayer, boolean aSwings )
        {
            super( GameObjectType.EWall, new JARSprite( aImg ) );

            iBlock          = new JARBlock( this, aX, aY, aImg.getElevation(), aSwings, aCollisionType );
            iDrawingLayer   = aDrawingLayer;
        }

        public static final void animateAll( JARWall[] walls )
        {
            //browse all walls
            for ( JARWall wall : walls )
            {
                //animate frame and swing
                wall.iSprite.animateFrame();
                wall.iBlock.animateSwing();

                //animate sprite fx
                JARSpriteFx.animate( wall.iSprite );
            }
        }
    }
