/*  $Id: JARLevel.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game.level;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.JARSettings.Performance;
    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.game.JARBlock.CollisionType;
    import  de.christopherstock.jumpandrun.game.object.*;
    import  de.christopherstock.jumpandrun.game.object.JARGameObject.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.jumpandrun.ui.JARScreen.DrawingLayer;
    import  de.christopherstock.libgdx.lib.math.geom.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   Represents a level.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/level/JARLevel.java $
    *****************************************************************************/
    public class JARLevel
    {
        private     static          JARLevel        current                     = null;

        public                      JARLevelType    iType                       = null;
        public                      int             iCoins                      = 0;
        public                      int             iPoints                     = 0;

        public                      JARPlayer       iPlayer                     = null;
        public                      JARWall[]       iWalls                      = null;
        public                      JARPlayer[]     iEnemies                    = null;
        public                      JARItem[]       iItems                      = null;

        public JARLevel( JARLevelType aType )
        {
            //assign type
            iType           = aType;

            //reset properties
            iCoins          = 0;
            iPoints         = 0;
        }

        public static final void initLevel( JARLevelType newLevelType )
        {
            //create pristine level instance
            current = new JARLevel( newLevelType );

            //assign blocks, enemies, the player and all items
            current.iWalls      = current.iType.getOriginalWalls();
            current.iEnemies    = current.iType.getOriginalEnemies();
            current.iItems      = current.iType.getOriginalItems();
            current.iPlayer     = new JARPlayer( current.iType.iStartX, current.iType.iStartY, GameObjectType.EPlayer, JARPlayerType.EUser, current.iType.iStartupStandingDirection );
        }

        public static final JARLevel getCurrent()
        {
            return current;
        }

        /*****************************************************************************
        *   Draws the bg using parallax scrolling.
        *****************************************************************************/
        public void drawLevelBg( SpriteBatch batch, LibCoordinate2D camera )
        {
            //draw bg image
            if ( Performance.PARALLAX_BG_SCROLLING )
            {
                float imgWidth  = iType.iSpriteBg.getImage().getTotalWidth();
                float imgHeight = iType.iSpriteBg.getImage().getTotalHeight();

                iType.iSpriteBg.draw
                (
                    batch,
                    0 - ( imgWidth  - JARScreen.WIDTH  ) * camera.getX() / ( iType.iLevelWidth  - JARScreen.WIDTH  ),
                    0 - ( imgHeight - JARScreen.HEIGHT ) * camera.getY() / ( iType.iLevelHeight - JARScreen.HEIGHT ),
                    LibAnchor.ELeftTop,
                    LibUI.ALPHA_OPAQUE,
                    false,
                    LibUI.SCALATION_NONE,
                    LibUI.SCALATION_NONE,
                    LibUI.ROTATION_NONE
                );
            }
            else
            {
                //draw image static on center bottom of the canvas
                iType.iSpriteBg.draw
                (
                    batch,
                    JARScreen.WIDTH / 2, JARScreen.HEIGHT,
                    LibAnchor.ECenterBottom,
                    LibUI.ALPHA_OPAQUE,
                    false,
                    LibUI.SCALATION_NONE,
                    LibUI.SCALATION_NONE,
                    LibUI.ROTATION_NONE
                );
            }

            //blend the image to make the fg more visible
            //Drawing.fillCanvas( "rgba( 255, 255, 255, " + Settings.BG_BLENDING + " )" );

            //draw middle layer
            if ( Performance.PARALLAX_BG_SCROLLING )
            {
                float imgWidth      = iType.iSpriteMiddle.getImage().getTotalWidth();
                float imgHeight     = iType.iSpriteMiddle.getImage().getTotalHeight();

                float MAGIC_OFF_Y   = 100;

                iType.iSpriteMiddle.draw
                (
                    batch,
                    0 -     ( imgWidth  - JARScreen.WIDTH  ) * camera.getX() / ( iType.iLevelWidth  - JARScreen.WIDTH  ),
                    0 - 4 * ( imgHeight - JARScreen.HEIGHT ) * camera.getY() / ( iType.iLevelHeight - JARScreen.HEIGHT ) + MAGIC_OFF_Y,
                    LibAnchor.ELeftTop,
                    LibUI.ALPHA_OPAQUE,
                    false,
                    LibUI.SCALATION_NONE,
                    LibUI.SCALATION_NONE,
                    LibUI.ROTATION_NONE
                );
            }
            else
            {
                //draw image static on center bottom of the canvas
                iType.iSpriteMiddle.draw
                (
                    batch,
                    JARScreen.WIDTH / 2,
                    JARScreen.HEIGHT,
                    LibAnchor.ECenterBottom,
                    LibUI.ALPHA_OPAQUE,
                    false,
                    LibUI.SCALATION_NONE,
                    LibUI.SCALATION_NONE,
                    LibUI.ROTATION_NONE
                );
            }
        }

        public void drawLevelWalls( SpriteBatch batch, LibCoordinate2D camera, DrawingLayer drawingLayer )
        {
            //browse all walls
            for ( JARWall wall : iWalls )
            {
                //only draw if drawing layer matches
                if ( wall.iDrawingLayer == drawingLayer )
                {
                    //draw wall
                    wall.iBlock.draw( batch, camera, LibUI.ALPHA_OPAQUE );
                }
            }
        }

        public boolean checkHorizontalCollisions( LibRect2D rect )
        {
            //check collision on level bounds
            if
            (
                    rect.iLeft < 0
                ||  rect.iLeft > iType.iLevelWidth - rect.iWidth
            )
            {
                return true;
            }

            //check collisions on player
            if ( iPlayer.iBlock.checkHorizontalBlockCollision( rect ) )
            {
                return true;
            }

            //check collision on blocks
            for ( JARWall wall : iWalls )
            {
                //only collide on non-passable blocks
                if ( wall.iBlock.iCollisionType == CollisionType.EColliding )
                {
                    if ( wall.iBlock.checkHorizontalBlockCollision( rect ) )
                    {
                        return true;
                    }
                }
            }

            //check collisions on enemies
            for ( JARPlayer enemy : iEnemies )
            {
                if ( enemy.isAlive() )
                {
                    if ( enemy.iBlock.checkHorizontalBlockCollision( rect ) )
                    {
                        return true;
                    }
                }
            }

            return false;
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
                for ( JARWall wall : iWalls )
                {
                    //ignore non-colliding blocks
                    if ( wall.iBlock.iCollisionType != CollisionType.ENonColliding )
                    {
                        //only if not pass-through
                        if ( considerPassThroughs || wall.iBlock.iCollisionType == CollisionType.EColliding )
                        {
                            Float blockY = wall.iBlock.getRect().getYonCollisionXrect( rect, LibRect2D.Elevation.ENone );
                            if ( blockY != null )
                            {
                                //pick bottom corner for non-passable-walls
                                if
                                (
                                        wall.iBlock.iCollisionType == CollisionType.EColliding
                                )
                                {
                                    blockY = Float.valueOf( blockY.floatValue() + wall.iBlock.getRect().iHeight );

                                    //check if the rect is ABOVE the player's HEAD!
                                    if ( blockY.floatValue() > nearestY && blockY.floatValue() <= rect.iTop )
                                    {
                                        nearestY = blockY.floatValue();
                                        block = wall.iBlock;
                                    }
                                }
                                else
                                {
                                    //if ( considerPassThroughs ) JARDebug.bugfix.info( "Check 1: BlockY: [" + blockY + "] playerTop [" + rect.iTop + "] nearestY [" + nearestY + "]" );

                                    //check if the rect is ABOVE the player's FEET!
                                    if ( blockY.floatValue() > nearestY && blockY.floatValue() < rect.iTop + rect.iHeight )
                                    {
                                        nearestY = blockY.floatValue();
                                        block = wall.iBlock;
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
                for ( JARPlayer enemy : iEnemies )
                {
                    //only if alive
                    if ( enemy.isAlive() )
                    {
                        //consider blocks
                        Float blockY = enemy.iBlock.getRect().getYonCollisionXrect( rect, LibRect2D.Elevation.ENone );
                        if ( blockY != null )
                        {
                            blockY = Float.valueOf( blockY.floatValue() + enemy.iBlock.getRect().iHeight );

                            //check if the rect is ABOVE the player's HEAD!
                            if ( blockY.floatValue() > nearestY && blockY.floatValue() <= rect.iTop )
                            {
                                nearestY = blockY.floatValue();
                                block    = enemy.iBlock;
                                player   = enemy;
                            }
                        }
                    }
                }
            }

            //JARDebug.bugfix.info( "aboveY: [" + nearestY + "]" );

            JARCollisionInfo ret = new JARCollisionInfo( nearestY, player, block );
            return ret;
        }

        /*****************************************************************************
        *   Picks the nearest collision BELOW the given rect
        *   and all available level components.
        *****************************************************************************/
        public final JARCollisionInfo getNearestYBelowRect( LibRect2D rect )
        {
            float     nearestY      = iType.iLevelHeight;
            JARPlayer nearestPlayer = null;
            JARBlock  nearestBlock  = null;

            //consider player
            if ( iPlayer != null )
            {
                Float blockY = iPlayer.iBlock.getRect().getYonCollisionXrect( rect, LibRect2D.Elevation.ENone );
                if ( blockY != null )
                {
                    //check if the block is UNDER the rect's FEET
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
                //JARDebug.bugfix.info( "Check below [" + iWalls.length + "] walls .." );

                for ( JARWall wall : iWalls )
                {
                    //ignore non-colliding blocks
                    if ( wall.iBlock.iCollisionType != CollisionType.ENonColliding )
                    {
                        //check elevation below player
                        Float blockY = wall.iBlock.getRect().getYonCollisionXrect( rect, wall.iBlock.getElevation() );
                        if ( blockY != null )
                        {
                            //JARDebug.bugfix.info( " possible Y [" + blockY + "]" );

                            //check block's elevation
                            switch ( wall.iBlock.getElevation() )
                            {
                                case ENone:
                                {
                                    //check if the block is UNDER the rect's FEET
                                    if ( blockY.floatValue() < nearestY && ( blockY.floatValue() >= rect.iTop + rect.iHeight ) )
                                    {
                                        nearestY      = blockY.floatValue();
                                        nearestPlayer = null;
                                        nearestBlock  = wall.iBlock;
                                    }
                                    break;
                                }

                                case EAscending:
                                case EDescending:
                                {
                                    //check passability
                                    if ( wall.iBlock.iCollisionType == CollisionType.EPassThrough )
                                    {
                                        //check if the block is UNDER the rect's VERTICAL MIDDLE! ( give it a try )
                                        if ( blockY.floatValue() < nearestY && ( blockY.floatValue() >= rect.iTop + rect.iHeight / 2 ) )
                                        {
                                            nearestY      = blockY.floatValue();
                                            nearestPlayer = null;
                                            nearestBlock  = wall.iBlock;
                                        }
                                    }
                                    else
                                    {
                                        //check if the block is UNDER the rect's HEAD
                                        if ( blockY.floatValue() < nearestY && ( blockY.floatValue() >= rect.iTop ) )
                                        {
                                            nearestY      = blockY.floatValue();
                                            nearestPlayer = null;
                                            nearestBlock  = wall.iBlock;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            //consider enemies
            if ( iEnemies != null )
            {
                for ( JARPlayer enemy : iEnemies )
                {
                    if ( enemy.isAlive() )
                    {
                        Float blockY = enemy.iBlock.getRect().getYonCollisionXrect( rect, LibRect2D.Elevation.ENone );
                        if ( blockY != null )
                        {
                            //check if the block is UNDER the rect's FEET
                            if ( blockY.floatValue() < nearestY && blockY.floatValue() >= rect.iTop + rect.iHeight )
                            {
                                nearestY      = blockY.floatValue();
                                nearestPlayer = enemy;
                                nearestBlock  = enemy.iBlock;
                            }
                        }
                    }
                }
            }

            //JARDebug.bugfix.info( " belowY: [" + nearestY + "]" );

            JARCollisionInfo ret = new JARCollisionInfo( nearestY, nearestPlayer, nearestBlock );
            return ret;
        }

        public final void bringRectOnStartup( LibRect2D rect, int x, int y )
        {
            //assign startup location
            rect.iLeft  = x;
            rect.iTop   = y;

            //adjust y
            JARCollisionInfo target = getNearestYBelowRect( rect );
            rect.iTop   = target.iY - rect.iHeight;
        }

        public final void gainPoints( int gain )
        {
            if ( gain > 0 )
            {
                iPoints += gain;
            }
        }

        public final void gainCoins( int gain )
        {
            if ( gain > 0 )
            {
                iCoins += gain;
            }
        }
    }
