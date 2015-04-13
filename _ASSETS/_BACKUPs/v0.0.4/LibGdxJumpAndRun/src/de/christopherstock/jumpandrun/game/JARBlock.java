/*  $Id: JARBlock.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.game.JARGameObject.GameObjectType;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.math.geom.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   Represents one obstacle block the level consists of.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARBlock.java $
    *****************************************************************************/
    public class JARBlock
    {
        public static enum BlockState
        {
            EStanding,
            EJumpingUp,
            EFallingDown,
            ;
        }

        public static enum SwingState
        {
            EIncreasing,
            EDecreasing,
            ;
        }

        public static enum PassThrough
        {
            EYes,
            ENo,
            ;
        }

        public static enum VerticalDirection
        {
            EAbove,
            EBelow,
            ;
        }

        private                     LibRect2D               iRect                           = null;

        private                     JARGameObject           iParentGameObject               = null;
        private                     LibRect2D.Elevation     iElevation                      = null;

        private                     BlockState              iState                          = null;
        private                     boolean                 iSwings                         = false;
        private                     LibPoint2D              iSwingAnchor                    = null;

        private                     int                     iFrame                          = 0;
        private                     int                     iFrameDelay                     = 0;
        private                     boolean                 iCollision                      = false;
        private                     float                   iJumpDeltaY                     = 0.0f;
        private                     int                     iSwingAngle                     = 0;
        private                     SwingState              iSwingState                     = null;
        public                      PassThrough             iPassThrough                    = null;
      //private                     int                     iJumpBlocker                    = 0;

        public JARBlock( JARGameObject aParentGameObject, int aX, int aY, LibRect2D.Elevation aElevated, boolean aSwings, PassThrough aClimbable )
        {
            iParentGameObject   = aParentGameObject;

            iSwingAnchor        = new LibPoint2D( aX, aY );

            iRect               = new LibRect2D( aX, aY, iParentGameObject.iSprite.iFrameWidth, iParentGameObject.iSprite.iFrameHeight );
            iElevation          = aElevated;
            iPassThrough        = aClimbable;
            iSwings             = aSwings;

            iFrame              = 0;
            iFrameDelay         = JARSettings.BLOCK_FRAME_ANIMATION_SPEED;
            iState              = BlockState.EStanding;
            iCollision          = false;
            iJumpDeltaY         = 0;
            iSwingAngle         = JARSettings.BLOCK_ANGLE_MAX;
            iSwingState         = SwingState.EDecreasing;
        }

        public void draw( SpriteBatch batch, JARCamera camera )
        {
            //check if block swings
            if ( iSwings )
            {
                //translate block by swing anchor
                LibPoint2D original = new LibPoint2D( iSwingAnchor.iX - camera.x, iSwingAnchor.iY - camera.y );
                LibPoint2D mod      = LibMath.sinCosPoint( original, iSwingAngle, JARSettings.BLOCK_ANGLE_RADIUS_X, JARSettings.BLOCK_ANGLE_RADIUS_Y );
                mod.iY           -= JARSettings.BLOCK_ANGLE_RADIUS_Y;

                iRect = new LibRect2D( mod.iX + camera.x, mod.iY + camera.y, iParentGameObject.iSprite.iFrameWidth, iParentGameObject.iSprite.iFrameHeight );

                JARSprite.drawSpriteObj( batch, iParentGameObject.iSprite, mod.iX, mod.iY, LibAnchor.LEFT_TOP, iFrame );
            }
            else
            {
                JARSprite.drawSpriteObj( batch, iParentGameObject.iSprite, iRect.iLeft - camera.x, iRect.iTop - camera.y, LibAnchor.LEFT_TOP, iFrame );
            }

            //draw debug rects for specified blocks
            if
            (
                    (    JARDebug.DRAW_DEBUG_BLOCKS_PLAYER  && iParentGameObject.iType == GameObjectType.EPlayer  )
                ||  (    JARDebug.DRAW_DEBUG_BLOCKS_ENEMY   && iParentGameObject.iType == GameObjectType.EEnemy )
                ||  (    JARDebug.DRAW_DEBUG_BLOCKS_ITEM    && iParentGameObject.iType == GameObjectType.EItem  )
                ||  (    JARDebug.DRAW_DEBUG_BLOCKS_WALL    && iParentGameObject.iType == GameObjectType.EWall  )
            )
            {
                drawDebug( batch, camera );
            }
        }

        public boolean checkBlockCollision( LibRect2D otherRect )
        {
            //no x/y collision on elevated blocks!
            switch ( iElevation )
            {
                case ENone:
                {
                    boolean ret = otherRect.collidesWithRect( iRect );
                    return ret;
                }

                case EAscending:
                {
                    boolean ret = false;
                    return ret;
                }

                case EDescending:
                {
                    boolean ret = false;
                    return ret;
                }
            }

            return false;
        }

        public void moveLeft( float speed )
        {
            float speedSteps = speed / JARSettings.BLOCK_COLLISION_CHECK_STEP_X;
            if ( speedSteps < 1 ) speedSteps = 1;
            for ( int i = 0; i < speedSteps; ++i )
            {
                iRect.iLeft -= speed / speedSteps;
                if ( JARLevel.current.checkHorizontalCollisions( iRect ) )
                {
                    iRect.iLeft += speed / speedSteps;
                    if ( i == speedSteps - 1 ) iCollision = true;
                }
                else
                {
                    iCollision = false;
                }
            }

            //bound level
            if ( iRect.iLeft < 0 ) iRect.iLeft = 0;
        }

        public void moveRight( float speed )
        {
            float speedSteps = speed / JARSettings.BLOCK_COLLISION_CHECK_STEP_X;
            if ( speedSteps < 1 ) speedSteps = 1;
            for ( int i = 0; i < speedSteps; ++i )
            {
                iRect.iLeft += speed / speedSteps;
                if ( JARLevel.current.checkHorizontalCollisions( iRect ) )
                {
                    iRect.iLeft -= speed / speedSteps;
                    if ( i == speedSteps - 1 ) iCollision = true;
                }
                else
                {
                    iCollision = false;
                }
            }

            //bound level
            if ( iRect.iLeft > JARLevel.current.iLevelBoundX - iRect.iWidth ) iRect.iLeft = JARLevel.current.iLevelBoundX - iRect.iWidth;
        }

        public void jump( float jumpPower )
        {
            //only if the block is standing
            if ( iState == BlockState.EStanding )
            {
                iState          = BlockState.EJumpingUp;
                iJumpDeltaY     = jumpPower;
/*
                //if ( iJumpBlocker == 0 )
                {
                }
                else
                {
                    //--iJumpBlocker;
                }
*/
            }
        }

        public void handleY()
        {
            //JARDebug.bugfix.info( "state " + iState );

            //animate according to player state
            switch ( iState )
            {
                case EStanding:
                {
                    //check if player falls
                    {
                        JARCollisionInfo    below       = JARLevel.current.getNearestYBelowRect( iRect );
                        float               nextBelowY  = below.iY - iRect.iHeight;

                        //check, if an enemy has been stepped on
                        boolean enemyWalkedOn = (  iParentGameObject.iType == GameObjectType.EPlayer && below.iPlayer != null && below.iPlayer.iType == GameObjectType.EEnemy ); //JARDebug.bugfix.info( "Enemy walked on " + iRect.iTop + " " + highestY );
                        if ( iRect.iTop < nextBelowY || enemyWalkedOn )
                        {
                            //check distance - don't fall for small distances!
                            if ( nextBelowY - iRect.iTop < JARSettings.PLAYER_AUTO_DESCEND_DISTANCE_Y )
                            {
                                //player just steps down
                                iRect.iTop = nextBelowY;

                                //check if player kills an enemy
                                if ( iParentGameObject.iType == GameObjectType.EPlayer && below.iPlayer != null && below.iPlayer.iType == GameObjectType.EEnemy )
                                {
                                    //kill enemy
                                    below.iPlayer.kill();

                                     //player jumps again!
                                    jump( JARSettings.PLAYER_JUMP_POWER_Y );
                                }
                            }
                            else
                            {
                                //player falls
                                iState      = BlockState.EFallingDown;
                                iJumpDeltaY = JARSettings.GRAVITY_FALL_DOWN;
                            }
                        }
                        else if ( iRect.iTop > nextBelowY )
                        {
                            iRect.iTop = nextBelowY;
                        }
                    }

                    //check if player climbs
                    {
                        JARCollisionInfo    above       = JARLevel.current.getNearestYAboveRect( iRect, true );
                        float               nextAboveY  = above.iY - iRect.iHeight;

                        //check distance - don't fall for small distances!
                        if ( iRect.iTop - nextAboveY < JARSettings.PLAYER_AUTO_ASCEND_DISTANCE_Y )
                        {
                            //player just steps up
                            iRect.iTop = nextAboveY;
                        }
                    }
                    break;
                }

                case EJumpingUp:
                {
                    if ( iJumpDeltaY < 0 )
                    {
                        iState = BlockState.EFallingDown;
                        iJumpDeltaY = 0;
                    }
                    else
                    {
                        //get lowest y above player
                        JARCollisionInfo    above       = JARLevel.current.getNearestYAboveRect( iRect, false );

                        //JARDebug.bugfix.info( "jumping - above [" + above.iY + "]" );

                        iRect.iTop  -= iJumpDeltaY;
                        iJumpDeltaY -= JARSettings.GRAVITY_JUMP_UP;

                        //check if player hits the surface
                        if ( iRect.iTop < above.iY )
                        {
                            //clip player to surface
                            iRect.iTop  = above.iY;
                            iJumpDeltaY = 0;
                            iState      = BlockState.EFallingDown;
                        }

                        //check if player falls through ramps .. avoid this
                        if ( false )
                        {
                            JARCollisionInfo    below       = JARLevel.current.getNearestYBelowRect( iRect );
                            float               highestY    = below.iY - iRect.iHeight;

                            if
                            (
                                    below.iBlock != null
                                &&  below.iBlock.iElevation != LibRect2D.Elevation.ENone
                                &&  iRect.iTop > highestY
                            )
                            {
                                iRect.iTop = highestY;
                            }
                        }
                    }
                    break;
                }

                case EFallingDown:
                {
                    //get highest y
                    JARCollisionInfo    below               = JARLevel.current.getNearestYBelowRect( iRect );
                    float               highestY            = below.iY - iRect.iHeight;

                    //fall down
                    iRect.iTop   += iJumpDeltaY;
                    iJumpDeltaY  += JARSettings.GRAVITY_FALL_DOWN;

                    //check if player hits the ground
                    if ( iRect.iTop > highestY )
                    {
                        //JARDebug.bugfix.info( " change to 'standing'" );

                        //reset player to ground
                        iRect.iTop  = highestY; // - iRect.iHeight;
                        iState      = BlockState.EStanding;

                        //check if player kills an enemy
                        if ( iParentGameObject.iType == GameObjectType.EPlayer && below.iPlayer != null && below.iPlayer.iType == GameObjectType.EEnemy )
                        {
                            //kill enemy
                            below.iPlayer.kill();

                            //player jumps again!
                            jump( JARSettings.PLAYER_JUMP_POWER_Y );
                        }
                    }
                    break;
                }
            }
        }

        public void drawDebug( SpriteBatch batch, JARCamera camera )
        {
            final int   BORDER_SIZE = 1;
            final float SCALE_XY    = 0.7f;

                  float yTop        = iRect.iTop  - camera.y + 15;
                  float yBottom     = iRect.iTop  - camera.y + iRect.iHeight - 35;
                  float yAbove      = iRect.iTop  - camera.y + 2;
                  float yBelow      = iRect.iTop  - camera.y + iRect.iHeight - 20;

            //distribute area space for wide blocks
            if ( iRect.iWidth > 64 )
            {
                yTop    = yAbove;
                yBottom = yBelow;
            }

            //determine border and fill color
            Color borderColor = null;
            Color fillColor   = ( !iCollision ? Color.GRAY  : Color.RED    );
            switch ( iState )
            {
                case EStanding:
                {
                    borderColor = Color.WHITE;
                    break;
                }

                case EJumpingUp:
                {
                    borderColor = Color.GREEN;
                    break;
                }

                case EFallingDown:
                {
                    borderColor = Color.RED;
                    break;
                }
            }

            //draw border and rect
            LibDrawing.fillRect( batch, iRect.iLeft - camera.x,                 iRect.iTop - camera.y,                  iRect.iWidth,                   iRect.iHeight,                      borderColor );
            LibDrawing.fillRect( batch, iRect.iLeft - camera.x + BORDER_SIZE,   iRect.iTop - camera.y + BORDER_SIZE,    iRect.iWidth - 2 * BORDER_SIZE, iRect.iHeight - 2 * BORDER_SIZE,    fillColor   );

            //draw top y
            LibDrawing.drawString
            (
                batch,
                JARFont.presidentBlack.iFont,
                LibStringFormat.getSingleton().formatDecimal( iRect.iTop ),
                iRect.iLeft - camera.x,
                yTop,
                iRect.iWidth - 5,
                HAlignment.RIGHT,
                SCALE_XY
            );

            //draw bottom y
            LibDrawing.drawString
            (
                batch,
                JARFont.presidentBlack.iFont,
                LibStringFormat.getSingleton().formatDecimal( iRect.iTop + iRect.iHeight - 1 ),
                iRect.iLeft - camera.x,
                yBottom,
                iRect.iWidth - 5,
                HAlignment.RIGHT,
                SCALE_XY
            );

            //draw height information for all blocks
            if ( true ) // || iParentGameObject instanceof JARPlayer )
            {
                JARCollisionInfo above = JARLevel.current.getNearestYAboveRect( iRect, false );
                JARCollisionInfo below = JARLevel.current.getNearestYBelowRect( iRect );

                //draw above
                LibDrawing.drawString
                (
                    batch,
                    JARFont.presidentGreen.iFont,
                    LibStringFormat.getSingleton().formatDecimal( above.iY ),
                    iRect.iLeft - camera.x + 5,
                    yAbove,
                    iRect.iWidth - 5,
                    HAlignment.LEFT,
                    SCALE_XY
                );

                //draw below
                LibDrawing.drawString
                (
                    batch,
                    JARFont.presidentRed.iFont,
                    LibStringFormat.getSingleton().formatDecimal( below.iY ),
                    iRect.iLeft - camera.x + 5,
                    yBelow,
                    iRect.iWidth - 5,
                    HAlignment.LEFT,
                    SCALE_XY
                );
            }
        }

        public void animateBlock()
        {
            //change frame after delay
            if ( iFrameDelay > 0 )
            {
                --iFrameDelay;
            }
            else
            {
                //restart delay
                iFrameDelay = JARSettings.BLOCK_FRAME_ANIMATION_SPEED;

                //next frame
                ++iFrame;
                if ( iFrame >= iParentGameObject.iSprite.iFrameCount ) iFrame = 0;
                //Console.append(frame);
            }

            //swing if desired
            if ( iSwings )
            {
                //next angle
                switch ( iSwingState )
                {
                    case EDecreasing:
                    {
                        iSwingAngle -= JARSettings.BLOCK_SWING_ANIMATION_SPEED;
                        if ( iSwingAngle <= JARSettings.BLOCK_ANGLE_MIN )
                        {
                            iSwingAngle      = JARSettings.BLOCK_ANGLE_MIN;
                            iSwingState = SwingState.EIncreasing;
                        }
                        break;
                    }
                    case EIncreasing:
                    {
                        iSwingAngle += JARSettings.BLOCK_SWING_ANIMATION_SPEED;
                        if ( iSwingAngle >= JARSettings.BLOCK_ANGLE_MAX )
                        {
                            iSwingAngle      = JARSettings.BLOCK_ANGLE_MAX;
                            iSwingState = SwingState.EDecreasing;
                        }
                        break;
                    }
                }
            }
        }

        public void setNewSprite( JARSprite newSprite )
        {
            //only change if sprite is a different one
            if ( iParentGameObject.iSprite != newSprite )
            {
                iParentGameObject.iSprite   = newSprite;
                iFrame                      = 0;
                iFrameDelay                 = JARSettings.BLOCK_FRAME_ANIMATION_SPEED;
            }
        }

        public void setCollision( boolean collision )
        {
            iCollision = collision;
        }

        public boolean isStanding()
        {
            return ( iState == BlockState.EStanding );
        }

        public LibRect2D.Elevation getElevation()
        {
            return iElevation;
        }

        public LibRect2D getRect()
        {
            return iRect;
        }
    }
