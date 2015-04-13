/*  $Id: JARItemType.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game.object;

    import  de.christopherstock.jumpandrun.JARSettings.HUD;
    import  de.christopherstock.jumpandrun.game.level.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.libgdx.lib.math.geom.*;

    /*****************************************************************************
    *   Represents a collectable item.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/object/JARItemType.java $
    *****************************************************************************/
    public enum JARItemType
    {
        ECoin(          false,  JARImage.EItemCoin,         ItemFx.EFxCoin,         JARSound.EPickUp    ),
        EApple(         true,   JARImage.EItemApple,        ItemFx.EFxPoints500,    JARSound.EPickUp    ),
        ECherry(        true,   JARImage.EItemCherry,       ItemFx.EFxPoints100,    JARSound.EPickUp    ),
        EOrange(        true,   JARImage.EItemOrange,       ItemFx.EFxPoints200,    JARSound.EPickUp    ),
        EPear(          true,   JARImage.EItemPear,         ItemFx.EFxPoints300,    JARSound.EPickUp    ),
        EStrawberry(    true,   JARImage.EItemStrawberry,   ItemFx.EFxPoints400,    JARSound.EPickUp    ),

        ;

        public static enum ItemFx
        {
            EFxPoints100,
            EFxPoints200,
            EFxPoints300,
            EFxPoints400,
            EFxPoints500,
            EFxCoin,
            ;
        }

        protected                   boolean         iSwings                             = false;

        protected                   JARImage        iImg                                = null;
        private                     ItemFx          iFx                                 = null;
        protected                   JARSound        iPickSound                          = null;

        private JARItemType( boolean aSwings, JARImage aImg, ItemFx aFx, JARSound aPickSound )
        {
            iImg        = aImg;
            iSwings     = aSwings;
            iFx         = aFx;
            iPickSound  = aPickSound;
        }

        public static final void init()
        {
        }

        public final LibPoint2D getAccordingTargetAnimationPoint()
        {
            switch ( this )
            {
                case EApple:
                case ECherry:
                case EOrange:
                case EPear:
                case EStrawberry:
                {
                    //right upper corner
                    return new LibPoint2D( JARScreen.WIDTH - HUD.OFFSET_HUD_BORDER_X - iImg.getFrameWidth(), HUD.OFFSET_HUD_BORDER_Y );
                }

                case ECoin:
                {
                    //left upper corner
                    return new LibPoint2D( HUD.OFFSET_HUD_BORDER_X, HUD.OFFSET_HUD_BORDER_Y );
                }
            }
            return null;
        }

        public final JARCounter getAccordingCounter()
        {
            switch ( this )
            {
                case EApple:
                case ECherry:
                case EOrange:
                case EPear:
                case EStrawberry:
                {
                    return JARHUD.counterPoints;
                }

                case ECoin:
                {
                    return JARHUD.counterCoins;
                }
            }
            return null;
        }

        public final void chargeAccordingPoints()
        {
            //perform action according to fx type
            switch ( iFx )
            {
                case EFxPoints100:
                {
                    JARLevel.getCurrent().gainPoints( 100 );
                    break;
                }
                case EFxPoints200:
                {
                    JARLevel.getCurrent().gainPoints( 200 );
                    break;
                }
                case EFxPoints300:
                {
                    JARLevel.getCurrent().gainPoints( 300 );
                    break;
                }
                case EFxPoints400:
                {
                    JARLevel.getCurrent().gainPoints( 400 );
                    break;
                }
                case EFxPoints500:
                {
                    JARLevel.getCurrent().gainPoints( 500 );
                    break;
                }
                case EFxCoin:
                {
                    JARLevel.getCurrent().gainCoins( 1 );
                    break;
                }
            }
        }
    }
