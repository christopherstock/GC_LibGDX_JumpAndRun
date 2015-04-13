/*  $Id: JARLevel.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.game.JARBlock.PassThrough;
    import  de.christopherstock.jumpandrun.game.JARGameObject.GameObjectType;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.math.geom.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   Represents a level.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/JARLevel.java $
    *****************************************************************************/
    public class JARLevel
    {
        public      static          JARLevel        current                     = null;

        public                      int             iCoins                      = 0;
        public                      int             iPoints                     = 0;

        public                      JARWall[]       iWalls                      = null;
        public                      JARPlayer[]     iEnemies                    = null;
        public                      JARPlayer       iPlayer                     = null;
        public                      JARItem[]       iItems                      = null;

        public                      int             iLevelBoundX                = 0;
        public                      int             iLevelBoundY                = 0;

        public JARLevel()
        {
            iCoins          = 0;
            iPoints         = 0;

            iWalls          = null;
            iEnemies        = null;
            iPlayer         = null;
            iItems          = null;

            iLevelBoundX    = 10000;
            iLevelBoundY    = 1000;
        }

        public static final void initLevel()
        {
            //create pristine level instance
            current = new JARLevel();

            //assign blocks, enemies, the player and all items
            current.iWalls = new JARWall[]
            {
                new JARWall( 30 + 256,  current.iLevelBoundY - 310 + 64, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 384,  current.iLevelBoundY - 320 + 64, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 512,  current.iLevelBoundY - 300 + 64, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 640,  current.iLevelBoundY - 290 + 64, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 768,  current.iLevelBoundY - 280 + 64, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 896,  current.iLevelBoundY - 270 + 64, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 1152, current.iLevelBoundY - 260 + 64, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 1408, current.iLevelBoundY - 250 + 64, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),

                new JARWall( 30 + 1664, current.iLevelBoundY - 240 - 32,     JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 1664, current.iLevelBoundY - 240 + 0,      JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 1664, current.iLevelBoundY - 240 + 32,     JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 1664, current.iLevelBoundY - 240 - 2 * 32, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 1664, current.iLevelBoundY - 240 - 3 * 32, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 1664, current.iLevelBoundY - 240 - 4 * 32, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),

                new JARWall( 30 + 1920, current.iLevelBoundY - 230 + 64, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),
                new JARWall( 30 + 2100, current.iLevelBoundY - 230 + 64, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.EYes         ),

                new JARWall( 66,    current.iLevelBoundY - 324, JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.ENo          ),

                new JARWall( 0,     current.iLevelBoundY - 64,  JARWall.WALL_STONE_1, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 128,   current.iLevelBoundY - 64,  JARWall.WALL_STONE_1, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 256,   current.iLevelBoundY - 64,  JARWall.WALL_STONE_1, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 384,   current.iLevelBoundY - 64,  JARWall.WALL_STONE_1, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 512,   current.iLevelBoundY - 64,  JARWall.WALL_STONE_1, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 640,   current.iLevelBoundY - 64,  JARWall.WALL_STONE_1, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 768,   current.iLevelBoundY - 64,  JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 896,   current.iLevelBoundY - 64,  JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 1024,  current.iLevelBoundY - 64,  JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 1152,  current.iLevelBoundY - 64,  JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 1280,  current.iLevelBoundY - 64,  JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 1408,  current.iLevelBoundY - 64,  JARWall.WALL_STONE_2, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 1536,  current.iLevelBoundY - 64,  JARWall.WALL_STONE_3, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 1664,  current.iLevelBoundY - 64,  JARWall.WALL_STONE_3, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 1792,  current.iLevelBoundY - 64,  JARWall.WALL_STONE_3, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 1920,  current.iLevelBoundY - 64,  JARWall.WALL_STONE_3, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 2048,  current.iLevelBoundY - 64,  JARWall.WALL_STONE_3, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
                new JARWall( 2176,  current.iLevelBoundY - 64,  JARWall.WALL_STONE_3, LibRect2D.Elevation.ENone, PassThrough.ENo          ),
            };
            current.iEnemies = new JARPlayer[]
            {
/*
                new JARPlayer( 500,  GameObjectType.EEnemy, JARPlayerTemplate.ENEMY_WEAK ),
                new JARPlayer( 1000, GameObjectType.EEnemy, JARPlayerTemplate.ENEMY_WEAK ),
                new JARPlayer( 1500, GameObjectType.EEnemy, JARPlayerTemplate.ENEMY_WEAK ),
                new JARPlayer( 2000, GameObjectType.EEnemy, JARPlayerTemplate.ENEMY_WEAK ),
                new JARPlayer( 2500, GameObjectType.EEnemy, JARPlayerTemplate.ENEMY_STRONG ),
                new JARPlayer( 3000, GameObjectType.EEnemy, JARPlayerTemplate.ENEMY_STRONG ),
                new JARPlayer( 3500, GameObjectType.EEnemy, JARPlayerTemplate.ENEMY_STRONG ),
                new JARPlayer( 4000, GameObjectType.EEnemy, JARPlayerTemplate.ENEMY_STRONG ),
                new JARPlayer( 4500, GameObjectType.EEnemy, JARPlayerTemplate.ENEMY_STRONG )
*/
            };

            current.iPlayer  = new JARPlayer( 0, GameObjectType.EPlayer, JARPlayerTemplate.USER );

            current.iItems = new JARItem[]
            {
                //new JARItem( 50,    700, JARItemType.ITEM_TYPE_COIN ),
                //new JARItem( 150,   700, JARItemType.ITEM_TYPE_COIN ),
                //new JARItem( 250,   700, JARItemType.ITEM_TYPE_COIN ),
                new JARItem( 550,   700, JARItemType.ITEM_TYPE_CHERRY ),
                new JARItem( 650,   700, JARItemType.ITEM_TYPE_CHERRY ),
                new JARItem( 750,   700, JARItemType.ITEM_TYPE_CHERRY ),
                new JARItem( 1050,  700, JARItemType.ITEM_TYPE_APPLE ),
                new JARItem( 1150,  700, JARItemType.ITEM_TYPE_APPLE ),
                new JARItem( 1250,  700, JARItemType.ITEM_TYPE_APPLE ),
                new JARItem( 1550,  700, JARItemType.ITEM_TYPE_ORANGE ),
                new JARItem( 1650,  700, JARItemType.ITEM_TYPE_ORANGE ),
                new JARItem( 1750,  700, JARItemType.ITEM_TYPE_ORANGE ),
                new JARItem( 2050,  700, JARItemType.ITEM_TYPE_PEAR ),
                new JARItem( 2150,  700, JARItemType.ITEM_TYPE_PEAR ),
                new JARItem( 2250,  700, JARItemType.ITEM_TYPE_PEAR ),
                new JARItem( 2550,  700, JARItemType.ITEM_TYPE_STRAWBERRY ),
                new JARItem( 2650,  700, JARItemType.ITEM_TYPE_STRAWBERRY ),
                new JARItem( 2750,  700, JARItemType.ITEM_TYPE_STRAWBERRY ),
            };
        }

        /*****************************************************************************
        *   Draws the bg using parallax scrolling.
        *****************************************************************************/
        public void drawLevelBg( SpriteBatch batch, JARCamera camera )
        {
            //draw bg image
            if ( JARSettings.scrollBgImageParallax )
            {
                int imgWidth  = (int)JARImage.GAME_BG_HILL.getWidth();
                int imgHeight = (int)JARImage.GAME_BG_HILL.getHeight();
                LibDrawing.drawImage
                (
                    batch,
                    JARImage.GAME_BG_HILL.getTextureRegion(),
                    0 - ( imgWidth  - JARScreen.width()  ) * camera.x / ( iLevelBoundX - JARScreen.width()  ),
                    0 - ( imgHeight - JARScreen.height() ) * camera.y / ( iLevelBoundY - JARScreen.height() ),
                    LibAnchor.LEFT_TOP
                );
            }
            else
            {
                //draw image static on center bottom of the canvas
                LibDrawing.drawImage( batch, JARImage.GAME_BG_HILL.getTextureRegion(), JARScreen.width() / 2, JARScreen.height(), LibAnchor.CENTER_BOTTOM );
            }

            //blend the image to make the fg more visible
            //Drawing.fillCanvas( "rgba( 255, 255, 255, " + Settings.BG_BLENDING + " )" );

            //draw middle layer
            if ( JARSettings.scrollBgImageParallax )
            {
                float imgWidth      = JARImage.GAME_BG_TREES.getWidth();
                float imgHeight     = JARImage.GAME_BG_TREES.getHeight();

                float MAGIC_OFF_Y   = 100;

                LibDrawing.drawImage
                (
                        batch,
                        JARImage.GAME_BG_TREES.getTextureRegion(),
                        0 - ( imgWidth  - JARScreen.width()  ) * camera.x / ( iLevelBoundX - JARScreen.width()  ),
                        0 - 4 * ( imgHeight - JARScreen.height() ) * camera.y / ( iLevelBoundY - JARScreen.height() ) + MAGIC_OFF_Y,
                        LibAnchor.LEFT_TOP
                        );
/*
                float offsetX      =     0;
                float offsetY      =     3 * imgHeight;

                float targetWidth  =     imgWidth;
                float targetHeight = 4 * imgHeight;

                LibDrawing.drawImage
                (
                    batch,
                    JARImage.GAME_BG_TREES,
                    0 - ( targetWidth  - Canvas.WIDTH      ) * camera.x / ( iLevelBoundX - Canvas.WIDTH  ) + offsetX,
                    0 - ( targetHeight - Canvas.HEIGHT     ) * camera.y / ( iLevelBoundY - Canvas.HEIGHT ) + offsetY,
                    Anchor.LEFT_TOP
                );
*/
            }
            else
            {
                //draw image static on center bottom of the canvas
                LibDrawing.drawImage( batch, JARImage.GAME_BG_TREES.getTextureRegion(), JARScreen.width() / 2, JARScreen.height(), LibAnchor.CENTER_BOTTOM );
            }
        }

        public void drawLevelFg( SpriteBatch batch, JARCamera camera )
        {
            //draw blocks in foreground
            for ( int i = 0; i < iWalls.length; ++i )
            {
                //draw block with isometric offset
                iWalls[ i ].iBlock.draw( batch, camera );
            }
        }

        public boolean checkHorizontalCollisions( LibRect2D rect )
        {
            //check collisions on player
            if ( iPlayer.iBlock.checkBlockCollision( rect ) )
            {
                return true;
            }

            //check collision on blocks
            for ( int i = 0; i < iWalls.length; ++i )
            {
                //only collide on non-passable blocks
                if ( iWalls[ i ].iBlock.iPassThrough == PassThrough.ENo )
                {
                    if ( iWalls[ i ].iBlock.checkBlockCollision( rect ) )
                    {
                        return true;
                    }
                }
            }

            //check collisions on enemies
            for ( int i = 0; i < iEnemies.length; ++i )
            {
                if ( iEnemies[ i ].isAlive() )
                {
                    if ( iEnemies[ i ].iBlock.checkBlockCollision( rect ) )
                    {
                        return true;
                    }
                }
            }

            return false;
        }

        /*****************************************************************************
        *   Picks the nearest collision BELOW the given rect
        *   and all available level components.
        *****************************************************************************/
        public final JARCollisionInfo getNearestYBelowRect( LibRect2D rect )
        {
            float     nearestY      = iLevelBoundY;
            JARPlayer nearestPlayer = null;
            JARBlock  nearestBlock  = null;

            //consider player
            if ( iPlayer != null )
            {
                Float blockY = iPlayer.iBlock.getRect().getYonCollisionXrect( rect, LibRect2D.Elevation.ENone );
                if ( blockY != null )
                {
                    //check if the block is UNDER the rect
                    if ( blockY.floatValue() < nearestY && blockY.floatValue() >= rect.iTop + rect.iHeight )
                    {
                        nearestY      = blockY.floatValue();
                        nearestPlayer = iPlayer;
                        nearestBlock  = iPlayer.iBlock;
                    }
                }
            }

            //consider blocks
            if ( iWalls != null )
            {
                for ( int i = 0; i < iWalls.length; ++i )
                {
                    //if ( iWalls[ i ].block.iPassThrough == PassThrough.ENo )
                    {
                        //check elevation below player
                        Float blockY = iWalls[ i ].iBlock.getRect().getYonCollisionXrect( rect, iWalls[ i ].iBlock.getElevation() );
                        if ( blockY != null )
                        {
                            //check if the block is UNDER the rect
                            if ( blockY.floatValue() < nearestY && ( blockY.floatValue() >= rect.iTop + rect.iHeight ) )
                            {
                                nearestY      = blockY.floatValue();
                                nearestPlayer = null;
                                nearestBlock  = iWalls[ i ].iBlock;
                            }
                        }
                    }
                }
            }

            //consider enemies
            if ( iEnemies != null )
            {
                for ( int i = 0; i < iEnemies.length; ++i )
                {
                    if ( iEnemies[ i ].isAlive() )
                    {
                        Float blockY = iEnemies[ i ].iBlock.getRect().getYonCollisionXrect( rect, LibRect2D.Elevation.ENone );
                        if ( blockY != null )
                        {
                            //check if the block is UNDER the rect
                            if ( blockY.floatValue() < nearestY && blockY.floatValue() >= rect.iTop + rect.iHeight )
                            {
                                nearestY      = blockY.floatValue();
                                nearestPlayer = iEnemies[ i ];
                                nearestBlock  = iEnemies[ i ].iBlock;
                            }
                        }
                    }
                }
            }

            //JARDebug.bugfix.info( "Get Y below: [" + rect.iTop + "][" + ( nearestBlock != null ? nearestBlock.iRect.iTop : "null" ) + "]" );

            JARCollisionInfo ret = new JARCollisionInfo( nearestY, nearestPlayer, nearestBlock );
            return ret;
        }

        public JARCollisionInfo getNearestYAboveRect( LibRect2D rect, boolean considerPassThroughs )
        {
            float       nearestY = 0;
            JARBlock    block    = null;
            JARPlayer   player   = null;

            //consider player
            if ( iPlayer != null )
            {
                Float blockY = iPlayer.iBlock.getRect().getYonCollisionXrect( rect, LibRect2D.Elevation.ENone );
                if ( blockY != null )
                {
                    blockY = Float.valueOf( blockY.floatValue() + iPlayer.iBlock.getRect().iHeight );

                    //check if the rect is ABOVE the player's HEAD!
                    if ( blockY.floatValue() > nearestY && blockY.floatValue() <= rect.iTop )
                    {
                        nearestY = blockY.floatValue();
                        block    = iPlayer.iBlock;
                        player   = iPlayer;
                    }
                }
            }

            //consider blocks
            if ( iWalls != null )
            {
                for ( int i = 0; i < iWalls.length; ++i )
                {
                    //ignore elevated ramps above
                    if ( iWalls[ i ].iBlock.getElevation() == LibRect2D.Elevation.ENone )
                    {
                        //only if not pass-through
                        if ( considerPassThroughs || iWalls[ i ].iBlock.iPassThrough == PassThrough.ENo )
                        {
                            Float blockY = iWalls[ i ].iBlock.getRect().getYonCollisionXrect( rect, LibRect2D.Elevation.ENone );
                            if ( blockY != null )
                            {
                                //pick bottom corner for non-passable-walls
                                if ( iWalls[ i ].iBlock.iPassThrough == PassThrough.ENo )
                                {
                                    blockY = Float.valueOf( blockY.floatValue() + iWalls[ i ].iBlock.getRect().iHeight );

                                    //check if the rect is ABOVE the player's HEAD!
                                    if ( blockY.floatValue() > nearestY && blockY.floatValue() <= rect.iTop )
                                    {
                                        nearestY = blockY.floatValue();
                                        block = iWalls[ i ].iBlock;
                                    }
                                }
                                else
                                {
                                    //if ( considerPassThroughs ) JARDebug.bugfix.info( "Check 1: BlockY: [" + blockY + "] playerTop [" + rect.iTop + "] nearestY [" + nearestY + "]" );

                                    //check if the rect is ABOVE the player's FEET!
                                    if ( blockY.floatValue() > nearestY && blockY.floatValue() < rect.iTop + rect.iHeight )
                                    {
                                        nearestY = blockY.floatValue();
                                        block = iWalls[ i ].iBlock;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //consider enemies
            if ( iEnemies != null )
            {
                for ( int i = 0; i < iEnemies.length; ++i )
                {
                    if ( iEnemies[ i ].isAlive() )
                    {
                        //consider blocks
                        Float blockY = iEnemies[ i ].iBlock.getRect().getYonCollisionXrect( rect, LibRect2D.Elevation.ENone );
                        if ( blockY != null )
                        {
                            blockY = Float.valueOf( blockY.floatValue() + iEnemies[ i ].iBlock.getRect().iHeight );

                            //check if the rect is ABOVE the player's HEAD!
                            if ( blockY.floatValue() > nearestY && blockY.floatValue() <= rect.iTop )
                            {
                                nearestY = blockY.floatValue();
                                block    = iEnemies[ i ].iBlock;
                                player   = iEnemies[ i ];
                            }
                        }
                    }
                }
            }

            return new JARCollisionInfo( nearestY, player, block );
        }

        public final void bringRectOnStartup( LibRect2D rect, int x )
        {
            rect.iLeft  = ( x == -1 ? ( JARScreen.width() - rect.iWidth ) / 2 : x );
            JARCollisionInfo target = getNearestYBelowRect( rect );
            rect.iTop   = target.iY - rect.iHeight;
        }

        public final void gainPoints( int gain )
        {
            if ( gain > 0 )
            {
                iPoints += gain;
                JARHUD.showPoints();
            }
        }

        public final void gainCoins( int gain )
        {
            if ( gain > 0 )
            {
                iCoins += gain;
                JARHUD.showCoins();
            }
        }
    }
