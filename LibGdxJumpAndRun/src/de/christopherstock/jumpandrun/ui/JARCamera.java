/*  $Id: JARCamera.java 172 2013-09-25 06:50:05Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  de.christopherstock.jumpandrun.JARSettings.Camera;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.libgdx.lib.math.geom.*;

    /*****************************************************************************
    *   Handles the camera logic.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JARCamera.java $
    *****************************************************************************/
    public class JARCamera implements LibCoordinate2D
    {
        private     static          JARCamera       current                         = new JARCamera();

        private                     float           iCurrentX                       = 0.0f;
        private                     float           iCurrentY                       = 0.0f;

        private                     float           iTargetX                        = 0.0f;
        private                     float           iTargetY                        = 0.0f;

        private                     float           iAssignmentX                    = 0.0f;
        private                     float           iAssignmentY                    = 0.0f;

        private                     boolean         iFollowYTillLanded              = false;

        public JARCamera()
        {
        }

        public static final JARCamera getCurrent()
        {
            return current;
        }

        @Override
        public final float getX()
        {
            return iCurrentX;
        }

        @Override
        public final float getY()
        {
            return iCurrentY;
        }

        public final void reset()
        {
            //update camera
            update();

            //directly assign target
            iCurrentX           = iTargetX;
            iCurrentY           = iTargetY;
            iAssignmentX        = iTargetX;
            iAssignmentY        = iTargetY;
            iFollowYTillLanded  = false;
        }

        public final void update()
        {
            //JARDebug.camera.info( "Set camera. player looks [" + JARLevel.current.iPlayer.getStandingDirection() + "]" );

            //reference player's rect
            LibRect2D playerRect = JARLevel.getCurrent().iPlayer.iBlock.getRect();

            //set camera target X and Y
            setTargetX( playerRect );
            setTargetY( playerRect );

            //set camera assignment X and Y
            setAssignmentX( playerRect );
            setAssignmentY( playerRect );

            //set camera current X and Y
            setCurrentX();
            setCurrentY();

            //JARDebug.camera.info( "Cam currentX [" + iCurrentX + "] targetX [" + iTargetX + "] currentY [" + iCurrentY + "] targetY [" + iTargetY + "]" );
        }

        private void setTargetX( LibRect2D playerRect )
        {
            //set target X
            switch ( JARLevel.getCurrent().iPlayer.getStandingDirection() )
            {
                case ELeft:
                {
                    iTargetX = playerRect.iLeft - ( JARScreen.WIDTH * Camera.RATIO_CENTERING_X_HEADING_LEFT - playerRect.iWidth / 2 );
                    break;
                }

                case ERight:
                {
                    iTargetX = playerRect.iLeft - ( JARScreen.WIDTH * Camera.RATIO_CENTERING_X_HEADING_RIGHT - playerRect.iWidth / 2 );
                    break;
                }
            }

            //clip target X
            if ( iTargetX < 0                                                           ) iTargetX = 0;
            if ( iTargetX > JARLevel.getCurrent().iType.iLevelWidth - JARScreen.WIDTH   ) iTargetX = JARLevel.getCurrent().iType.iLevelWidth - JARScreen.WIDTH;
        }

        private void setTargetY( LibRect2D playerRect )
        {
            //set target Y
            iTargetY = playerRect.iTop - ( JARScreen.HEIGHT * Camera.RATIO_CENTERING_Y_STANDING - playerRect.iHeight / 2 );

            //clip target Y
            if ( iTargetY < 0                                                           ) iTargetY = 0;
            if ( iTargetY > JARLevel.getCurrent().iType.iLevelHeight - JARScreen.HEIGHT ) iTargetY = JARLevel.getCurrent().iType.iLevelHeight - JARScreen.HEIGHT;
        }

        private void setAssignmentX( LibRect2D playerRect )
        {
            //directly assign horizontal target
            iAssignmentX = iTargetX;
        }

        private void setAssignmentY( LibRect2D playerRect )
        {
            //assign vertical target according to state
            switch ( JARLevel.getCurrent().iPlayer.getBlockState() )
            {
                case EStanding:
                {
                    //assign vertical target immediately
                    iAssignmentY        = iTargetY;
                    iFollowYTillLanded  = false;
                    break;
                }

                case EFallingDown:
                {
                    if ( Camera.CAM_FOLLOWS_FALLING_PLAYER )
                    {
                        if ( iFollowYTillLanded )
                        {
                            iAssignmentY    = iTargetY;
                            iCurrentY       = iTargetY;
                        }
                        else
                        {
                            //get delta of player's current y and target y and camera-delta
                            float playerFallingDelta      = playerRect.iTop - iTargetY;
                            float playerFallingDeltaLimit = JARScreen.HEIGHT * Camera.RATIO_CENTERING_Y_STANDING - playerRect.iHeight / 2;
                            float cameraFallingDelta      = iTargetY - iAssignmentY;
                          //float cameraFallingDeltaLimit = JARScreen.HEIGHT * Camera.RATIO_MAX_CAMERA_FALLING_DISTANCE;

                          //JARDebug.camera.info( "Player falls. currentY [" + iCurrentY + "] targetY [" + iTargetY + "] assignmentY [" + iAssignmentY + "] playerFallingDelta [" + playerFallingDelta + "] playerFallingDeltaLimit [" + playerFallingDeltaLimit + "] cameraFallingDelta [" + cameraFallingDelta + "]" );

                            //directly change the camera on falling if the player falls over the vertical center of the screen and the camera moves down
                            if
                            (

                                    playerFallingDelta >= playerFallingDeltaLimit
                                &&  cameraFallingDelta >  0.0f
                            )
                            {
                                //JARDebug.camera.info( " Camera sticks to falling player." );

                                iFollowYTillLanded  = true;
                                iAssignmentY        = iTargetY;
                                iCurrentY           = iTargetY;
                            }
                        }
                    }
                    break;
                }

                case EJumpingUp:
                {
                    //do not assign vertical target while jumping
                    iFollowYTillLanded = false;
                    break;
                }
            }
        }

        private void setCurrentX()
        {
            if ( iCurrentX < iAssignmentX )
            {
                if ( Camera.BUFFERED_CAMERA_HORIZONTAL )
                {
                    //get difference
                    float deltaX = iAssignmentX - iCurrentX;
                    deltaX *= Camera.BUFFERED_CAMERA_HORIZONTAL_RATIO;
                    if ( deltaX > Camera.BUFFERED_CAMERA_MAX_SPEED_X ) deltaX = Camera.BUFFERED_CAMERA_MAX_SPEED_X;
                    if ( deltaX < Camera.BUFFERED_CAMERA_MIN_SPEED_X ) deltaX = Camera.BUFFERED_CAMERA_MIN_SPEED_X;
                    iCurrentX += deltaX;
                }
                else
                {
                    iCurrentX += Camera.CAM_SPEED_X;
                }
                if ( iCurrentX > iAssignmentX ) iCurrentX = iAssignmentX;
            }
            else if ( iCurrentX > iAssignmentX )
            {
                if ( Camera.BUFFERED_CAMERA_HORIZONTAL )
                {
                    //get difference
                    float deltaX = iCurrentX - iAssignmentX;
                    deltaX *= Camera.BUFFERED_CAMERA_HORIZONTAL_RATIO;
                    if ( deltaX > Camera.BUFFERED_CAMERA_MAX_SPEED_X ) deltaX = Camera.BUFFERED_CAMERA_MAX_SPEED_X;
                    if ( deltaX < Camera.BUFFERED_CAMERA_MIN_SPEED_X ) deltaX = Camera.BUFFERED_CAMERA_MIN_SPEED_X;
                    iCurrentX -= deltaX;
                }
                else
                {
                    iCurrentX -= Camera.CAM_SPEED_X;
                }
                if ( iCurrentX < iAssignmentX ) iCurrentX = iAssignmentX;
            }
        }

        private void setCurrentY()
        {
            if ( iCurrentY < iAssignmentY )
            {
/*
                if ( Camera.BUFFERED_CAMERA_VERTICAL )
                {
                    //get difference
                    float deltaY = iAssignmentY - iCurrentY;
                    deltaY *= Camera.BUFFERED_CAMERA_VERTICAL_RATIO;
                    if ( deltaY > Camera.BUFFERED_CAMERA_MAX_SPEED_Y ) deltaY = Camera.BUFFERED_CAMERA_MAX_SPEED_Y;
                    if ( deltaY < Camera.BUFFERED_CAMERA_MIN_SPEED_Y ) deltaY = Camera.BUFFERED_CAMERA_MIN_SPEED_Y;
                    iCurrentY += deltaY;
                }
                else
*/
                {
                    iCurrentY += Camera.CAM_SPEED_Y;
                }
                if ( iCurrentY > iAssignmentY ) iCurrentY = iAssignmentY;
            }
            else if ( iCurrentY > iAssignmentY )
            {
                if ( Camera.BUFFERED_CAMERA_VERTICAL )
                {
                    //get difference
                    float deltaY = iCurrentY - iAssignmentY;
                    deltaY *= Camera.BUFFERED_CAMERA_VERTICAL_RATIO;
                    if ( deltaY > Camera.BUFFERED_CAMERA_MAX_SPEED_Y ) deltaY = Camera.BUFFERED_CAMERA_MAX_SPEED_Y;
                    if ( deltaY < Camera.BUFFERED_CAMERA_MIN_SPEED_Y ) deltaY = Camera.BUFFERED_CAMERA_MIN_SPEED_Y;
                    iCurrentY -= deltaY;
                }
                else
                {
                    iCurrentY -= Camera.CAM_SPEED_Y;
                }
                if ( iCurrentY < iAssignmentY ) iCurrentY = iAssignmentY;
            }
        }
    }
