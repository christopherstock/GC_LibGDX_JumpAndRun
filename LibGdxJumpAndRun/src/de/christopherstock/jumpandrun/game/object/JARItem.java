/*  $Id: JARItem.java 176 2013-09-30 10:49:25Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game.object;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.JARSettings.HUD;
    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.game.JARBlock.CollisionType;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.math.geom.*;
    import  de.christopherstock.libgdx.lib.ui.*;

    /*****************************************************************************
    *   Represents a collectable item.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/object/JARItem.java $
    *****************************************************************************/
    public class JARItem extends JARGameObject
    {
        private static enum ItemState
        {
            ENotPicked,
            EPickedMoving,
            EPickedPauseAfterMoved,
            EPickedFadingOut,
            EGone,
            ;
        }

        private     static  final   LibPoint2D          PICKED_CAMERA_POINT             = new LibPoint2D( 0.0f, 0.0f );

        private                     JARItemType         iItemType                       = null;
        private                     ItemState           iItemState                      = null;

        private                     int                 iPickedTick                     = 0;
        private                     float               iPickStepX                      = 0.0f;
        private                     float               iPickStepY                      = 0.0f;
        private                     float               iAlpha                          = 0.0f;
        private                     boolean             iHidden                         = false;

        public JARItem( int aX, int aY, JARItemType aItemType, boolean aHidden )
        {
            super( GameObjectType.EItem, new JARSprite( aItemType.iImg ) );

            iItemType   = aItemType;
            iItemState  = ItemState.ENotPicked;

            iBlock      = new JARBlock( this, aX, aY, LibRect2D.Elevation.ENone, aItemType.iSwings, CollisionType.EColliding );
            iAlpha      = LibUI.ALPHA_OPAQUE;
            iHidden     = aHidden;

            //set random initial frame index if desired
            if ( JARSettings.Item.ITEM_RANDOM_STARTUP_FRAME )
            {
                iSprite.shuffleCurrentFrameIndex();
            }
        }

        public static void drawAllUnpicked( SpriteBatch batch, LibCoordinate2D camera )
        {
            //browse all items
            for ( JARItem item : JARLevel.getCurrent().iItems )
            {
                //draw if not picked
                switch ( item.iItemState )
                {
                    case ENotPicked:
                    {
                        //draw if not hidden
                        if ( !item.iHidden )
                        {
                            item.iBlock.draw( batch, camera, LibUI.ALPHA_OPAQUE );
                        }
                        break;
                    }

                    case EPickedMoving:
                    case EPickedPauseAfterMoved:
                    case EPickedFadingOut:
                    case EGone:
                    {
                        //don't draw!
                        break;
                    }
                }
            }
        }

        public static void drawAllPicked( SpriteBatch batch )
        {
            //browse all items
            for ( JARItem item : JARLevel.getCurrent().iItems )
            {
                //draw if picked
                switch ( item.iItemState )
                {
                    case ENotPicked:
                    case EGone:
                    {
                        //don't draw!
                        break;
                    }

                    case EPickedMoving:
                    case EPickedPauseAfterMoved:
                    case EPickedFadingOut:
                    {
                        //draw if anim not over
                        item.iBlock.draw( batch, PICKED_CAMERA_POINT, item.iAlpha );
                        break;
                    }
                }
            }
        }

        public static void checkCollisionAll( JARItem[] items, LibRect2D rect, LibCoordinate2D camera )
        {
            //browse all items
            for ( JARItem item : items )
            {
                //check collision if not picked
                switch ( item.iItemState )
                {
                    case ENotPicked:
                    {
                        item.checkCollision( rect, camera );
                        break;
                    }

                    case EPickedFadingOut:
                    case EPickedMoving:
                    case EGone:
                    case EPickedPauseAfterMoved:
                    {
                        //don't check collision
                        break;
                    }
                }
            }
        }

        private void checkCollision( LibRect2D rect, LibCoordinate2D camera )
        {
            //check player collision
            if ( iBlock.getRect().collidesWithRect( rect ) )
            {
                //play pick sound
                iItemType.iPickSound.playSound();

                //get target pick animation point
                LibPoint2D targetAnimationPoint = iItemType.getAccordingTargetAnimationPoint();

                //flag item as picked and translate the block against the camera
                iItemState = ItemState.EPickedMoving;
                iBlock.translate( -camera.getX(), -camera.getY() );

                //calculate pick animation step per tick
                iPickStepX          = ( targetAnimationPoint.iX - iBlock.getRect().iLeft ) / HUD.TICKS_PICKED_ITEMS_MOVE;
                iPickStepY          = ( targetAnimationPoint.iY - iBlock.getRect().iTop  ) / HUD.TICKS_PICKED_ITEMS_MOVE;

                //show according counter
                iItemType.getAccordingCounter().show();
            }
        }

        public static final void animateAll( JARItem[] items )
        {
            //browse all items
            for ( JARItem item : items )
            {
                //check state
                switch ( item.iItemState )
                {
                    case ENotPicked:
                    {
                        //animate block
                        item.iSprite.animateFrame();
                        item.iBlock.animateSwing();
                        break;
                    }

                    case EPickedMoving:
                    {
                        //animate block frame only
                        item.iSprite.animateFrame();
                        item.movePicked();
                        break;
                    }

                    case EPickedPauseAfterMoved:
                    {
                        //animate block frame only
                        item.iSprite.animateFrame();
                        item.pauseAfterMoved();
                        break;
                    }

                    case EPickedFadingOut:
                    {
                        //animate block frame only
                        item.iSprite.animateFrame();
                        item.fadeOutPicked();
                        break;
                    }

                    case EGone:
                    {
                        //don't animate
                        break;
                    }
                }
            }
        }

        private final void movePicked()
        {
            //increase pick animation
            ++iPickedTick;

            //move picked point
            iBlock.getRect().iLeft += iPickStepX;
            iBlock.getRect().iTop  += iPickStepY;

            //switch to next itemState 'pause after moved' if finished
            if ( iPickedTick == HUD.TICKS_PICKED_ITEMS_MOVE )
            {
                iPickedTick = 0;
                iItemState  = ItemState.EPickedPauseAfterMoved;

                //charge the points here
                iItemType.chargeAccordingPoints();
            }
        }

        private final void pauseAfterMoved()
        {
            //increase pick animation
            ++iPickedTick;

            //switch to next itemState 'fade out' if finished
            if ( iPickedTick == HUD.TICKS_PICKED_ITEMS_PAUSE_AFTER_MOVED )
            {
                iPickedTick = 0;
                iItemState  = ItemState.EPickedFadingOut;
            }
        }

        private final void fadeOutPicked()
        {
            //increase pick animation
            ++iPickedTick;

            //assign new alpha
            iAlpha = 1.0f - ( (float)iPickedTick / HUD.TICKS_PICKED_ITEMS_FADE_OUT );

            //switch to next itemState 'gone' if finished
            if ( iPickedTick == HUD.TICKS_PICKED_ITEMS_FADE_OUT )
            {
                iPickedTick = 0;
                iItemState  = ItemState.EGone;
            }
        }
    }
