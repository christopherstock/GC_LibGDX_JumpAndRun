/*  $Id: JARItem.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.*;
    import de.christopherstock.jumpandrun.game.JARBlock.PassThrough;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import de.christopherstock.libgdx.lib.math.geom.*;

    /*****************************************************************************
    *   Represents a collectable item.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARItem.java $
    *****************************************************************************/
    public class JARItem extends JARGameObject
    {
        public      static  final       int             ITEM_TYPE_APPLE                 = 0;
        public      static  final       int             ITEM_TYPE_CHERRY                = 1;
        public      static  final       int             ITEM_TYPE_ORANGE                = 2;
        public      static  final       int             ITEM_TYPE_PEAR                  = 3;
        public      static  final       int             ITEM_TYPE_STRAWBERRY            = 4;
        public      static  final       int             ITEM_TYPE_COIN                  = 5;

        private                         JARItemType     iTemplate                       = null;
        private                         JARBlock        iBlock                          = null;
        private                         boolean         iPicked                         = false;

        public JARItem( int aX, int aY, JARItemType aTemplate )
        {
            super( GameObjectType.EItem, aTemplate.iSprite );

            iTemplate       = aTemplate;
            iBlock          = new JARBlock( this, aX, aY, LibRect2D.Elevation.ENone, iTemplate.iSwings, PassThrough.ENo );
            iPicked         = false;
        }

        public static void drawAll( SpriteBatch batch, JARItem[] items, JARCamera camera )
        {
            //browse all items
            for ( int i = 0; i < items.length; ++i )
            {
                //draw if not picked
                if ( !items[ i ].iPicked )
                {
                    items[ i ].draw( batch, camera );
                }
            }
        }

        public void draw( SpriteBatch batch, JARCamera camera )
        {
            iBlock.draw( batch, camera );
        }

        public static void checkCollisionAll( JARItem[] items, LibRect2D rect )
        {
            //browse all items
            for ( int i = 0; i < items.length; ++i )
            {
                //check if not picked
                if ( !items[ i ].iPicked )
                {
                    items[ i ].checkCollision( rect );
                }
            }
        }

        public void checkCollision( LibRect2D rect )
        {
            //check player collision
            if ( iBlock.getRect().collidesWithRect( rect ) )
            {
                //play pick sound
                JARSound.EPickUp.play();

                //flag item as picked
                iPicked = true;

                //perform action according to fx type
                switch ( iTemplate.iFx )
                {
                    case JARItemType.ITEM_FX_POINTS_100:
                    {
                        JARLevel.current.gainPoints( 100 );
                        break;
                    }
                    case JARItemType.ITEM_FX_POINTS_200:
                    {
                        JARLevel.current.gainPoints( 200 );
                        break;
                    }
                    case JARItemType.ITEM_FX_POINTS_300:
                    {
                        JARLevel.current.gainPoints( 300 );
                        break;
                    }
                    case JARItemType.ITEM_FX_POINTS_400:
                    {
                        JARLevel.current.gainPoints( 400 );
                        break;
                    }
                    case JARItemType.ITEM_FX_POINTS_500:
                    {
                        JARLevel.current.gainPoints( 500 );
                        break;
                    }
                    case JARItemType.ITEM_FX_COIN_1:
                    {
                        JARLevel.current.gainCoins( 1 );
                        break;
                    }
                }
            }
        }

        public static final void animateAll( JARItem[] items )
        {
            //browse all items
            for ( int i = 0; i < items.length; ++i )
            {
                //animate if not picked
                if ( !items[ i ].iPicked )
                {
                    items[ i ].animate();
                }
            }
        }

        public void animate()
        {
            //animate the block
            iBlock.animateBlock();
        }
    }
