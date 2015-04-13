/*  $Id: JARBlock.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
    import  de.christopherstock.jumpandrun.JARSettings.Colors;
    import  de.christopherstock.jumpandrun.JARSettings.Debug;
    import  de.christopherstock.jumpandrun.JARSettings.Game;
    import  de.christopherstock.jumpandrun.game.JARGameObject.GameObjectType;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.math.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   Represents one obstacle block the level consists of.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARBlock.java $
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

        public static enum CollisionType
        {
            EColliding,
            EPassThrough,
            ENonColliding,
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
        public                      CollisionType           iCollisionType                  = null;

        public JARBlock( JARGameObject aParentGameObject, int aX, int aY, LibRect2D.Elevation aElevated, boolean aSwings, CollisionType aCollisionType )
        {
            iParentGameObject   = aParentGameObject;

            iSwingAnchor        = new LibPoint2D( aX, aY );

            iRect               = new LibRect2D( aX, aY, iParentGameObject.iSprite.iImg.getFrameWidth(), iParentGameObject.iSprite.iImg.getFrameHeight() );
            iElevation          = aElevated;
            iCollisionType      = aCollisionType;
            iSwings             = aSwings;

            iFrame              = 0;
            iFrameDelay         = Game.BLOCK_FRAME_ANIMATION_SPEED;
            iState              = BlockState.EStanding;
            iCollision          = false;
            iJumpDeltaY         = 0;
            iSwingAngle         = Game.BLOCK_ANGLE_MAX;
            iSwingState         = SwingState.EDecreasing;
        }

        @SuppressWarnings( "unused" )
        public void draw( SpriteBatch batch, LibCoordinate2D camera, float alpha )
        {
            //draw block
            JARSprite.drawSpriteObj( batch, iParentGameObject.iSprite, iRect.iLeft - camera.getX(), iRect.iTop - camera.getY(), LibAnchor.ELeftTop, iFrame, alpha );

            //draw debug rects for specified blocks
            if
            (
                    (    Debug.DRAW_DEBUG_BLOCKS_PLAYER  && iParentGameObject.iGameObjectType == GameObjectType.EPlayer  )
                ||  (    Debug.DRAW_DEBUG_BLOCKS_ENEMY   && iParentGameObject.iGameObjectType == GameObjectType.EEnemy )
                ||  (    Debug.DRAW_DEBUG_BLOCKS_ITEM    && iParentGameObject.iGameObjectType == GameObjectType.EItem  )
                ||  (    Debug.DRAW_DEBUG_BLOCKS_WALL    && iParentGameObject.iGameObjectType == GameObjectType.EWall  )
            )
            {
                drawDebug( batch, camera );
            }
        }

        public boolean checkHorizontalBlockCollision( LibRect2D otherRect )
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
                case EDescending:
                {
                    //no horizontal collision for ramps!
                    boolean ret = false;
                    return ret;
                }
            }

            return false;
        }

        public void moveLeft( float speed )
        {
            float speedSteps = speed / Game.BLOCK_COLLISION_CHECK_STEP_X;
            if ( speedSteps < 1 ) speedSteps = 1;
            for ( int i = 0; i < speedSteps; ++i )
            {
                iRect.iLeft -= speed / speedSteps;
                if ( JARLevel.getCurrent().checkHorizontalCollisions( iRect ) )
                {
                    iRect.iLeft += speed / speedSteps;
                    if ( i == speedSteps - 1 ) iCollision = true;
                }
                else
                {
                    iCollision = false;
                }
            }
        }

        public void moveRight( float speed )
        {
            float speedSteps = speed / Game.BLOCK_COLLISION_CHECK_STEP_X;
            if ( speedSteps < 1 ) speedSteps = 1;
            for ( int i = 0; i < speedSteps; ++i )
            {
                iRect.iLeft += speed / speedSteps;
                if ( JARLevel.getCurrent().checkHorizontalCollisions( iRect ) )
                {
                    iRect.iLeft -= speed / speedSteps;
                    if ( i == speedSteps - 1 ) iCollision = true;
                }
                else
                {
                    iCollision = false;
                }
            }
        }

        public void jump( float jumpPower )
        {
            //only if the block is standing
            if ( iState == BlockState.EStanding )
            {
                iState          = BlockState.EJumpingUp;
                iJumpDeltaY     = jumpPower;
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
                        JARCollisionInfo    below       = JARLevel.getCurrent().getNearestYBelowRect( iRect );
                        float               nextBelowY  = below.iY - iRect.iHeight;

                        //check, if an enemy has been stepped on
                        boolean enemyWalkedOn = (  iParentGameObject.iGameObjectType == GameObjectType.EPlayer && below.iPlayer != null && below.iPlayer.iGameObjectType == GameObjectType.EEnemy );
                        if ( iRect.iTop < nextBelowY || enemyWalkedOn )
                        {
                            //check distance - don't fall for small distances!
                            if ( nextBelowY - iRect.iTop < Game.PLAYER_AUTO_DESCEND_DISTANCE_Y )
                            {
                                //player just steps down
                                iRect.iTop = nextBelowY;

                                //check if player kills an enemy
                                if ( iParentGameObject.iGameObjectType == GameObjectType.EPlayer && below.iPlayer != null && below.iPlayer.iGameObjectType == GameObjectType.EEnemy )
                                {
                                    //kill enemy
                                    below.iPlayer.kill();

                                     //player jumps again!
                                    jump( Game.PLAYER_JUMP_POWER_Y );
                                }
                            }
                            else
                            {
                                //player falls
                                iState      = BlockState.EFallingDown;
                                iJumpDeltaY = Game.GRAVITY_FALL_DOWN;
                            }
                        }
                        else if ( iRect.iTop > nextBelowY )
                        {
                            iRect.iTop = nextBelowY;
                        }
                    }

                    //check if player climbs
                    {
                        JARCollisionInfo    above       = JARLevel.getCurrent().getNearestYAboveRect( iRect, true );
                        float               nextAboveY  = above.iY - iRect.iHeight;

                        //check distance - don't fall for small distances!
                        if ( iRect.iTop - nextAboveY < Game.PLAYER_AUTO_ASCEND_DISTANCE_Y )
                        {
                            //ignore this for elevated ramps!
                            if ( above.iBlock != null )
                            {
                                switch ( above.iBlock.iElevation )
                                {
                                    case ENone:
                                    {
                                        //player just steps up
                                        iRect.iTop = nextAboveY;

                                        //JARDebug.bugfix.info( "PLAYER STEPPED UP!" );
                                        break;
                                    }

                                    case EAscending:
                                    case EDescending:
                                    {
                                        //no auto upstep
                                        break;
                                    }
                                }
                            }
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
                        JARCollisionInfo    above       = JARLevel.getCurrent().getNearestYAboveRect( iRect, false );

                        //JARDebug.bugfix.info( "jumping - above [" + above.iY + "]" );

                        iRect.iTop  -= iJumpDeltaY;
                        iJumpDeltaY -= Game.GRAVITY_JUMP_UP;

                        //check if player hits the surface
                        if ( iRect.iTop < above.iY )
                        {
                            //clip player to surface
                            iRect.iTop  = above.iY;
                            iJumpDeltaY = 0;
                            iState      = BlockState.EFallingDown;
                        }
                    }
                    break;
                }

                case EFallingDown:
                {
                    //get highest y
                    JARCollisionInfo    below               = JARLevel.getCurrent().getNearestYBelowRect( iRect );
                    float               highestY            = below.iY - iRect.iHeight;

                    //fall down
                    iRect.iTop   += iJumpDeltaY;
                    iJumpDeltaY  += Game.GRAVITY_FALL_DOWN;

                    //check if player hits the ground
                    if ( iRect.iTop > highestY )
                    {
                        //JARDebug.bugfix.info( " change to 'standing'" );

                        //JARDebug.bugfix.info( "PLAYER FELL TO GROUND!" );

                        //reset player to ground
                        iRect.iTop  = highestY; // - iRect.iHeight;
                        iState      = BlockState.EStanding;

                        //check if player kills an enemy
                        if ( iParentGameObject.iGameObjectType == GameObjectType.EPlayer && below.iPlayer != null && below.iPlayer.iGameObjectType == GameObjectType.EEnemy )
                        {
                            //kill enemy
                            below.iPlayer.kill();

                            //player jumps again!
                            jump( Game.PLAYER_JUMP_POWER_Y );
                        }
                    }
                    break;
                }
            }
        }

        public void drawDebug( SpriteBatch batch, LibCoordinate2D camera )
        {
            final int   BORDER_SIZE = 1;
            final float SCALE_XY    = 0.7f;

                  float yTop        = iRect.iTop  - camera.getY() + 15;
                  float yBottom     = iRect.iTop  - camera.getY() + iRect.iHeight - 35;
                  float yAbove      = iRect.iTop  - camera.getY() + 2;
                  float yBelow      = iRect.iTop  - camera.getY() + iRect.iHeight - 20;

            //distribute area space for wide blocks
            if ( iRect.iWidth > 64 )
            {
                yTop    = yAbove;
                yBottom = yBelow;
            }

            //determine border and fill color
            Color borderColor = null;
            Color fillColor   = ( !iCollision ? Colors.COLOR_BLOCK_DEFAULT : Colors.COLOR_BLOCK_COLLIDING );
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

            //draw debug shape
            switch ( iElevation )
            {
                //fill border and rect
                case ENone:
                {
                    LibDrawing.fillRect( batch, iRect.iLeft - camera.getX(),                 iRect.iTop - camera.getY(),                  iRect.iWidth,                   iRect.iHeight,                      borderColor, false );
                    LibDrawing.fillRect( batch, iRect.iLeft - camera.getX() + BORDER_SIZE,   iRect.iTop - camera.getY() + BORDER_SIZE,    iRect.iWidth - 2 * BORDER_SIZE, iRect.iHeight - 2 * BORDER_SIZE,    fillColor,   false );
                    break;
                }

                case EAscending:
                {
                    //fill ascending triangle
                    LibDrawing.fillTriangle( batch, iRect.iLeft - camera.getX() + iRect.iWidth, iRect.iTop - camera.getY(), iRect.iLeft - camera.getX() + iRect.iWidth, iRect.iTop - camera.getY() + iRect.iHeight, iRect.iLeft - camera.getX(), iRect.iTop - camera.getY() + iRect.iHeight, fillColor );
                    break;
                }

                case EDescending:
                {
                    //fill descending triangle
                    LibDrawing.fillTriangle( batch, iRect.iLeft - camera.getX(), iRect.iTop - camera.getY(), iRect.iLeft - camera.getX(), iRect.iTop - camera.getY() + iRect.iHeight, iRect.iLeft - camera.getX() + iRect.iWidth, iRect.iTop - camera.getY() + iRect.iHeight, fillColor );
                    break;
                }
            }

            //draw top y
            LibDrawing.drawString
            (
                batch,
                JARFont.presidentWhite.iFont,
                LibStringFormat.getSingleton().formatDecimal( iRect.iTop ),
                iRect.iLeft - camera.getX(),
                yTop,
                iRect.iWidth - 5,
                HAlignment.RIGHT,
                SCALE_XY,
                JARFont.presidentBlack.iFont
            );

            //draw bottom y
            LibDrawing.drawString
            (
                batch,
                JARFont.presidentWhite.iFont,
                LibStringFormat.getSingleton().formatDecimal( iRect.iTop + iRect.iHeight - 1 ),
                iRect.iLeft - camera.getX(),
                yBottom,
                iRect.iWidth - 5,
                HAlignment.RIGHT,
                SCALE_XY,
                JARFont.presidentBlack.iFont
            );

            //draw height information
            {
                JARCollisionInfo above = JARLevel.getCurrent().getNearestYAboveRect( iRect, false );
                JARCollisionInfo below = JARLevel.getCurrent().getNearestYBelowRect( iRect );

                //draw above
                LibDrawing.drawString
                (
                    batch,
                    JARFont.presidentGreen.iFont,
                    LibStringFormat.getSingleton().formatDecimal( above.iY ),
                    iRect.iLeft - camera.getX() + 5,
                    yAbove,
                    iRect.iWidth - 5,
                    HAlignment.LEFT,
                    SCALE_XY,
                    JARFont.presidentBlack.iFont
                );

                //draw below
                LibDrawing.drawString
                (
                    batch,
                    JARFont.presidentRed.iFont,
                    LibStringFormat.getSingleton().formatDecimal( below.iY ),
                    iRect.iLeft - camera.getX() + 5,
                    yBelow,
                    iRect.iWidth - 5,
                    HAlignment.LEFT,
                    SCALE_XY,
                    JARFont.presidentBlack.iFont
                );
            }
        }

        public void animate()
        {
            animate( true, true );
        }

        public void animate( boolean changeFrame, boolean swing )
        {
            //change frame if desired
            if ( changeFrame )
            {
                if ( iFrameDelay > 0 )
                {
                    --iFrameDelay;
                }
                else
                {
                    //restart delay
                    iFrameDelay = Game.BLOCK_FRAME_ANIMATION_SPEED;

                    //next frame
                    ++iFrame;
                    if ( iFrame >= iParentGameObject.iSprite.iImg.iFrameCount ) iFrame = 0;
                }
            }

            //swing if desired
            if ( swing && iSwings )
            {
                //next angle
                switch ( iSwingState )
                {
                    case EDecreasing:
                    {
                        iSwingAngle -= Game.BLOCK_SWING_ANIMATION_SPEED;
                        if ( iSwingAngle <= Game.BLOCK_ANGLE_MIN )
                        {
                            iSwingAngle      = Game.BLOCK_ANGLE_MIN;
                            iSwingState = SwingState.EIncreasing;
                        }
                        break;
                    }
                    case EIncreasing:
                    {
                        iSwingAngle += Game.BLOCK_SWING_ANIMATION_SPEED;
                        if ( iSwingAngle >= Game.BLOCK_ANGLE_MAX )
                        {
                            iSwingAngle      = Game.BLOCK_ANGLE_MAX;
                            iSwingState = SwingState.EDecreasing;
                        }
                        break;
                    }
                }

                //update block position by translating it by the swing anchor
                LibPoint2D original = new LibPoint2D( iSwingAnchor.iX, iSwingAnchor.iY );
                LibPoint2D mod      = LibMath.sinCosPoint
                (
                    original,
                    iSwingAngle,
                    Game.BLOCK_ANGLE_RADIUS_X,
                    Game.BLOCK_ANGLE_RADIUS_Y
                );
                mod.iY              -= Game.BLOCK_ANGLE_RADIUS_Y;

                iRect = new LibRect2D( mod.iX, mod.iY, iParentGameObject.iSprite.iImg.getFrameWidth(), iParentGameObject.iSprite.iImg.getFrameHeight() );
            }
        }

        public void setNewSprite( JARSprite newSprite )
        {
            //only change if sprite is a different one
            if ( iParentGameObject.iSprite != newSprite )
            {
                iParentGameObject.iSprite   = newSprite;
                iFrame                      = 0;
                iFrameDelay                 = Game.BLOCK_FRAME_ANIMATION_SPEED;
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

        public BlockState getState()
        {
            return iState;
        }

        public void translate( float transX, float transY )
        {
            iRect.iLeft += transX;
            iRect.iTop  += transY;
        }
    }
