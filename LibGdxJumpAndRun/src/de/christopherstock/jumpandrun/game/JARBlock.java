/*  $Id: JARBlock.java 182 2013-09-30 14:45:09Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.JARSettings.Colors;
    import  de.christopherstock.jumpandrun.JARSettings.Debug;
    import  de.christopherstock.jumpandrun.JARSettings.Performance;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.jumpandrun.game.object.*;
    import  de.christopherstock.jumpandrun.game.object.JARGameObject.*;
    import  de.christopherstock.jumpandrun.game.object.JARPlayer.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.libgdx.lib.math.*;
    import  de.christopherstock.libgdx.lib.math.geom.*;
    import  de.christopherstock.libgdx.lib.math.geom.LibRect2D.Elevation;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   Represents one obstacle block the level consists of.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARBlock.java $
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
        private                     Elevation               iElevation                      = null;

        private                     BlockState              iState                          = null;
        private                     boolean                 iSwings                         = false;
        private                     LibPoint2D              iSwingAnchor                    = null;

        private                     boolean                 iCollisionMarker                = false;
        private                     float                   iJumpDeltaY                     = 0.0f;
        private                     int                     iSwingAngle                     = 0;
        private                     SwingState              iSwingState                     = null;
        public                      CollisionType           iCollisionType                  = null;

        public JARBlock( JARGameObject aParentGameObject, int aX, int aY, LibRect2D.Elevation aElevated, boolean aSwings, CollisionType aCollisionType )
        {
            iParentGameObject   = aParentGameObject;

            iSwingAnchor        = new LibPoint2D( aX, aY );
            iRect               = new LibRect2D(  aX, aY, iParentGameObject.iSprite.getImage().getFrameWidth(), iParentGameObject.iSprite.getImage().getFrameHeight() );

            iElevation          = aElevated;
            iCollisionType      = aCollisionType;
            iSwings             = aSwings;

            iState              = BlockState.EStanding;
            iCollisionMarker    = false;
            iJumpDeltaY         = 0.0f;
            iSwingAngle         = JARSettings.Item.ITEM_SWING_ANGLE_MAX;
            iSwingState         = SwingState.EDecreasing;
        }

        @SuppressWarnings( "unused" )
        public void draw( SpriteBatch batch, LibCoordinate2D camera, float alpha )
        {
            //flip drawing X if parent game object is a player and he's facing to the right
            boolean flipDrawingX =
            (
                    iParentGameObject instanceof JARPlayer
                &&  ( (JARPlayer)iParentGameObject ).getStandingDirection() == StandingDirection.ERight
            );

            //draw block
            iParentGameObject.iSprite.draw
            (
                batch,
                iRect.iLeft - camera.getX(),
                iRect.iTop  - camera.getY(),
                LibAnchor.ELeftTop,
                alpha,
                flipDrawingX,
                LibUI.SCALATION_NONE,
                LibUI.SCALATION_NONE,
                LibUI.ROTATION_NONE
            );

            //draw debug rects for specified blocks
            if
            (
                    (    Debug.DRAW_DEBUG_BLOCKS_PLAYER  && iParentGameObject.iGameObjectType == GameObjectType.EPlayer )
                ||  (    Debug.DRAW_DEBUG_BLOCKS_ENEMY   && iParentGameObject.iGameObjectType == GameObjectType.EEnemy  )
                ||  (    Debug.DRAW_DEBUG_BLOCKS_ITEM    && iParentGameObject.iGameObjectType == GameObjectType.EItem   )
                ||  (    Debug.DRAW_DEBUG_BLOCKS_WALL    && iParentGameObject.iGameObjectType == GameObjectType.EWall   )
            )
            {
                //only draw for non-colliding blocks!
                if ( iCollisionType != CollisionType.ENonColliding )
                {
                    //only draw if this player is not dead
                    if
                    (
                            !( iParentGameObject instanceof JARPlayer )
                        ||  ( (JARPlayer)iParentGameObject ).isAlive()
                    )
                    {
                        drawDebug( batch, camera );
                    }
                }
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
            float speedSteps = speed / Performance.BLOCK_COLLISION_CHECK_STEP_X;
            if ( speedSteps < 1 ) speedSteps = 1;
            for ( int i = 0; i < speedSteps; ++i )
            {
                iRect.iLeft -= speed / speedSteps;
                if ( JARLevel.getCurrent().checkHorizontalCollisions( iRect ) )
                {
                    iRect.iLeft += speed / speedSteps;
                    if ( i == speedSteps - 1 ) iCollisionMarker = true;
                }
                else
                {
                    iCollisionMarker = false;
                }
            }
        }

        public void moveRight( float speed )
        {
            float speedSteps = speed / Performance.BLOCK_COLLISION_CHECK_STEP_X;
            if ( speedSteps < 1 ) speedSteps = 1;
            for ( int i = 0; i < speedSteps; ++i )
            {
                iRect.iLeft += speed / speedSteps;
                if ( JARLevel.getCurrent().checkHorizontalCollisions( iRect ) )
                {
                    iRect.iLeft -= speed / speedSteps;
                    if ( i == speedSteps - 1 ) iCollisionMarker = true;
                }
                else
                {
                    iCollisionMarker = false;
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
                    // FIRST check if player climbs
                    {
                        JARCollisionInfo    above       = JARLevel.getCurrent().getNearestYAboveRect( iRect, true );
                        float               nextAboveY  = above.iY - iRect.iHeight;

                        //check distance - don't fall for small distances!
                        if ( iRect.iTop - nextAboveY < JARSettings.Player.PLAYER_AUTO_ASCEND_DISTANCE_Y )
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

                    //check if player falls
                    {
                        JARCollisionInfo    below       = JARLevel.getCurrent().getNearestYBelowRect( iRect );
                        float               nextBelowY  = below.iY - iRect.iHeight;

                        //check, if an enemy has been stepped on
                        boolean playerSteppedOnEnemy = (  iParentGameObject.iGameObjectType == GameObjectType.EPlayer && below.iPlayer != null && below.iPlayer.iGameObjectType == GameObjectType.EEnemy );
                        if ( iRect.iTop < nextBelowY || playerSteppedOnEnemy )
                        {
                            //check distance - don't fall for small distances!
                            if ( nextBelowY - iRect.iTop < JARSettings.Player.PLAYER_AUTO_DESCEND_DISTANCE_Y )
                            {
                                //player just steps down
                                iRect.iTop = nextBelowY;

                                //check if player kills an enemy
                                if ( iParentGameObject.iGameObjectType == GameObjectType.EPlayer && below.iPlayer != null && below.iPlayer.iGameObjectType == GameObjectType.EEnemy )
                                {
                                    //kill enemy
                                    below.iPlayer.kill();

                                     //player jumps again!
                                    jump( JARSettings.Player.PLAYER_JUMP_POWER_Y );
                                }
                            }
                            else
                            {
                                //player falls
                                iState      = BlockState.EFallingDown;
                                iJumpDeltaY = JARSettings.Player.PLAYER_GRAVITY_FALL_DOWN;
                            }
                        }
                        else if ( iRect.iTop > nextBelowY )
                        {
                            iRect.iTop = nextBelowY;
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
                        iJumpDeltaY -= JARSettings.Player.PLAYER_GRAVITY_JUMP_UP;

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
                    iJumpDeltaY  += JARSettings.Player.PLAYER_GRAVITY_FALL_DOWN;

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
                            jump( JARSettings.Player.PLAYER_JUMP_POWER_Y );
                        }
                    }
                    break;
                }
            }
        }

        public void animateSwing()
        {
            //only if the block swings
            if ( iSwings )
            {
                //next angle
                switch ( iSwingState )
                {
                    case EDecreasing:
                    {
                        iSwingAngle -= JARSettings.Item.ITEM_SWING_ANIMATION_SPEED;
                        if ( iSwingAngle <= JARSettings.Item.ITEM_SWING_ANGLE_MIN )
                        {
                            iSwingAngle      = JARSettings.Item.ITEM_SWING_ANGLE_MIN;
                            iSwingState = SwingState.EIncreasing;
                        }
                        break;
                    }
                    case EIncreasing:
                    {
                        iSwingAngle += JARSettings.Item.ITEM_SWING_ANIMATION_SPEED;
                        if ( iSwingAngle >= JARSettings.Item.ITEM_SWING_ANGLE_MAX )
                        {
                            iSwingAngle      = JARSettings.Item.ITEM_SWING_ANGLE_MAX;
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
                    JARSettings.Item.ITEM_SWING_ANGLE_RADIUS_X,
                    JARSettings.Item.ITEM_SWING_ANGLE_RADIUS_Y
                );
                mod.iY              -= JARSettings.Item.ITEM_SWING_ANGLE_RADIUS_Y;

                iRect = new LibRect2D( mod.iX, mod.iY, iParentGameObject.iSprite.getImage().getFrameWidth(), iParentGameObject.iSprite.getImage().getFrameHeight() );
            }
        }

        public void setCollisionMarker( boolean collision )
        {
            iCollisionMarker = collision;
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

        public void drawDebug( SpriteBatch batch, LibCoordinate2D camera )
        {
            final int   BORDER_SIZE = 1;
            final float SCALE_XY    = 0.7f;

            float yTop      = iRect.iTop  - camera.getY() + 15;
            float yBottom   = iRect.iTop  - camera.getY() + iRect.iHeight - 35;
            float yAbove    = iRect.iTop  - camera.getY() + 2;
            float yBelow    = iRect.iTop  - camera.getY() + iRect.iHeight - 20;

            //only calculate height information for players
            boolean calcAboveAndBelow = ( iParentGameObject instanceof JARPlayer );

            //distribute area space for wide blocks
            if ( iRect.iWidth > 64 )
            {
                yTop    = yAbove;
                yBottom = yBelow;
            }

            //move bottom specifications up for elevated triangles
            if ( !calcAboveAndBelow )
            {
                switch ( iElevation )
                {
                    case EAscending:
                    {
                        yTop    += iRect.iHeight;
                        yTop    -= iRect.iHeight / 3;
                        yBottom = yTop + 20;
                        break;
                    }
                    case EDescending:
                    {
                        yTop    += iRect.iHeight;
                        yTop    -= iRect.iHeight / 3;
                        yBottom = yTop + 20;
                        break;
                    }
                    case ENone:
                    {
                        yTop    += iRect.iHeight / 3;
                        yTop    -= 10;
                        yBottom = yTop + 20;
                        break;
                    }
                }
            }

            //determine border and fill color
            Color borderColor = null;
            Color fillColor   = ( !iCollisionMarker ? Colors.COLOR_BLOCK_DEFAULT : Colors.COLOR_BLOCK_COLLIDING );
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
                    LibPoint2D leftTop      = new LibPoint2D( iRect.iLeft - camera.getX(),                  iRect.iTop  - camera.getY() + iRect.iHeight         );
                    LibPoint2D rightTop     = new LibPoint2D( iRect.iLeft - camera.getX() + iRect.iWidth,   iRect.iTop  - camera.getY()                         );
                    LibPoint2D leftBottom   = new LibPoint2D( iRect.iLeft - camera.getX(),                  iRect.iTop  - camera.getY() + 2 * iRect.iHeight     );
                    LibPoint2D rightBottom  = new LibPoint2D( iRect.iLeft - camera.getX() + iRect.iWidth,   iRect.iTop  - camera.getY() + iRect.iHeight         );

                    //fill ascending triangle top
                    LibDrawing.fillTriangle
                    (
                        batch,
                        rightTop.getX(),
                        rightTop.getY(),
                        rightBottom.getX(),
                        rightBottom.getY(),
                        leftTop.getX(),
                        leftTop.getY(),
                        fillColor
                    );
                    //fill ascending triangle bottom
                    LibDrawing.fillTriangle
                    (
                        batch,
                        rightBottom.getX(),
                        rightBottom.getY(),
                        leftTop.getX(),
                        leftTop.getY(),
                        leftBottom.getX(),
                        leftBottom.getY(),
                        fillColor
                    );

                    //draw lines
                    LibDrawing.drawLine( batch, leftTop.getX(),    leftTop.getY(),    leftBottom.getX(),  leftBottom.getY(),  borderColor );
                    LibDrawing.drawLine( batch, rightTop.getX(),   rightTop.getY(),   rightBottom.getX(), rightBottom.getY(), borderColor );
                    LibDrawing.drawLine( batch, leftTop.getX(),    leftTop.getY(),    rightTop.getX(),    rightTop.getY(),    borderColor );
                    LibDrawing.drawLine( batch, leftBottom.getX(), leftBottom.getY(), rightBottom.getX(), rightBottom.getY(), borderColor );

                    break;
                }

                case EDescending:
                {
                    LibPoint2D leftTop      = new LibPoint2D( iRect.iLeft - camera.getX(),                  iRect.iTop  - camera.getY()                         );
                    LibPoint2D rightTop     = new LibPoint2D( iRect.iLeft - camera.getX() + iRect.iWidth,   iRect.iTop  - camera.getY() + iRect.iHeight         );
                    LibPoint2D leftBottom   = new LibPoint2D( iRect.iLeft - camera.getX(),                  iRect.iTop  - camera.getY() + iRect.iHeight         );
                    LibPoint2D rightBottom  = new LibPoint2D( iRect.iLeft - camera.getX() + iRect.iWidth,   iRect.iTop  - camera.getY() + 2 * iRect.iHeight     );

                    //fill descending triangle top
                    LibDrawing.fillTriangle
                    (
                        batch,
                        leftTop.getX(),
                        leftTop.getY(),
                        leftBottom.getX(),
                        leftBottom.getY(),
                        rightTop.getX(),
                        rightTop.getY(),
                        fillColor
                    );
                    LibDrawing.fillTriangle
                    (
                        batch,
                        leftBottom.getX(),
                        leftBottom.getY(),
                        rightTop.getX(),
                        rightTop.getY(),
                        rightBottom.getX(),
                        rightBottom.getY(),
                        fillColor
                    );

                    //draw lines
                    LibDrawing.drawLine( batch, leftTop.getX(),    leftTop.getY(),    leftBottom.getX(),  leftBottom.getY(),  borderColor );
                    LibDrawing.drawLine( batch, rightTop.getX(),   rightTop.getY(),   rightBottom.getX(), rightBottom.getY(), borderColor );
                    LibDrawing.drawLine( batch, leftTop.getX(),    leftTop.getY(),    rightTop.getX(),    rightTop.getY(),    borderColor );
                    LibDrawing.drawLine( batch, leftBottom.getX(), leftBottom.getY(), rightBottom.getX(), rightBottom.getY(), borderColor );

                    break;
                }
            }

            //draw top y
            LibDrawing.drawFont
            (
                batch,
                JARFont.EPresidentWhite.iFont,
                LibStringFormat.getSingleton().formatDecimal( iRect.iTop ),
                iRect.iLeft - camera.getX(),
                yTop,
                ( calcAboveAndBelow ? iRect.iWidth - 5 : iRect.iWidth ),
                ( calcAboveAndBelow ? HAlignment.RIGHT : HAlignment.CENTER ),
                SCALE_XY,
                JARFont.EPresidentBlack.iFont
            );

            //draw bottom y
            LibDrawing.drawFont
            (
                batch,
                JARFont.EPresidentWhite.iFont,
                LibStringFormat.getSingleton().formatDecimal( iRect.iTop + iRect.iHeight - 1 ),
                iRect.iLeft - camera.getX(),
                yBottom,
                ( calcAboveAndBelow ? iRect.iWidth - 5 : iRect.iWidth ),
                ( calcAboveAndBelow ? HAlignment.RIGHT : HAlignment.CENTER ),
                SCALE_XY,
                JARFont.EPresidentBlack.iFont
            );

            //draw height information
            if ( calcAboveAndBelow )
            {
                JARCollisionInfo above = JARLevel.getCurrent().getNearestYAboveRect( iRect, false );
                JARCollisionInfo below = JARLevel.getCurrent().getNearestYBelowRect( iRect );

                //draw above
                LibDrawing.drawFont
                (
                    batch,
                    JARFont.EPresidentGreen.iFont,
                    LibStringFormat.getSingleton().formatDecimal( above.iY ),
                    iRect.iLeft - camera.getX() + 5,
                    yAbove,
                    iRect.iWidth - 5,
                    HAlignment.LEFT,
                    SCALE_XY,
                    JARFont.EPresidentBlack.iFont
                );

                //draw below
                LibDrawing.drawFont
                (
                    batch,
                    JARFont.EPresidentRed.iFont,
                    LibStringFormat.getSingleton().formatDecimal( below.iY ),
                    iRect.iLeft - camera.getX() + 5,
                    yBelow,
                    iRect.iWidth - 5,
                    HAlignment.LEFT,
                    SCALE_XY,
                    JARFont.EPresidentBlack.iFont
                );
            }
        }
    }
