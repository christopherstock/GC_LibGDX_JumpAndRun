/*  $Id: JARImage.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun.io;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.libgdx.lib.ui.LibRect2D.Elevation;

    /***********************************************************************************************
    *   The image system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    ***********************************************************************************************/
    public enum JARImage
    {
        EBgHill1(                       "image/bg/EHill1.jpg"                                   , 1, 1, 1,  null                    ),
        EBgForest1(                     "image/bg/EForest1.jpg"                                 , 1, 1, 1,  null                    ),
        EBgForest2(                     "image/bg/EForest2.jpg"                                 , 1, 1, 1,  null                    ),
        EBgTrees1(                      "image/bg/ETrees1.png"                                  , 1, 1, 1,  null                    ),

        EDecoSignpost1(                 "image/deco/ESignpost1.png"                             , 1, 1, 1,  null                    ),
        EDecoTree1(                     "image/deco/ETree1.png"                                 , 1, 1, 1,  null                    ),
        EDecoRailing1(                  "image/deco/ERailing1.png"                              , 1, 1, 1,  null                    ),
        EDecoRailing1Ascending(         "image/deco/ERailing1Ascending.png"                     , 1, 1, 1,  null                    ),
        EDecoRailing1Descending(        "image/deco/ERailing1Descending.png"                    , 1, 1, 1,  null                    ),

        EWallGrass1(                    "image/wall/EGrass1.png"                                , 1, 1, 1,  Elevation.ENone         ),
        EWallGrass1Topping(             "image/wall/EGrass1Topping.png"                         , 1, 1, 1,  Elevation.ENone         ),
        EWallGrass1Ascending(           "image/wall/EGrass1Ascending.png"                       , 1, 1, 1,  Elevation.EAscending    ),
        EWallGrass1Descending(          "image/wall/EGrass1Descending.png"                      , 1, 1, 1,  Elevation.EDescending   ),
        EWallGrass1ToppingAscending(    "image/wall/EGrass1ToppingAscending.png"                , 1, 1, 1,  Elevation.ENone         ),
        EWallGrass1ToppingDescending(   "image/wall/EGrass1ToppingDescending.png"               , 1, 1, 1,  Elevation.ENone         ),
        EWallSand1(                     "image/wall/ESand1.png"                                 , 1, 1, 1,  Elevation.ENone         ),
        EWallStone1(                    "image/wall/EStone1.png"                                , 1, 1, 1,  Elevation.ENone         ),
        EWallStone1Ascending(           "image/wall/EStone1Ascending.png"                       , 1, 1, 1,  Elevation.EAscending    ),
        EWallStone1Descending(          "image/wall/EStone1Descending.png"                      , 1, 1, 1,  Elevation.EDescending   ),
        EWallWater1(                    "image/wall/EWater1.png"                                , 1, 8, 8,  Elevation.ENone         ),
        EWallWood1(                     "image/wall/EWood1.png"                                 , 1, 1, 1,  Elevation.ENone         ),
        EWallWood1ArcLeft(              "image/wall/EWood1ArcLeft.png"                          , 1, 1, 1,  Elevation.ENone         ),
        EWallWood1ArcRight(             "image/wall/EWood1ArcRight.png"                         , 1, 1, 1,  Elevation.ENone         ),
        EWallWood1Small(                "image/wall/EWood1Small.png"                            , 1, 1, 1,  Elevation.ENone         ),
        EWallWood1Ascending(            "image/wall/EWood1Ascending.png"                        , 1, 1, 1,  Elevation.EAscending    ),
        EWallWood1Descending(           "image/wall/EWood1Descending.png"                       , 1, 1, 1,  Elevation.EDescending   ),

        EGlobalNumbers(                 "image/global/ENumbers.png"                             , 11, 1, 1, null                    ),
        EGlobalButtonLeft(              "image/global/EButtonLeft.jpg"                          , 1, 1, 1,  null                    ),
        EGlobalButtonRight(             "image/global/EButtonRight.jpg"                         , 1, 1, 1,  null                    ),
        EGlobalButtonUp(                "image/global/EButtonUp.jpg"                            , 1, 1, 1,  null                    ),
        EGlobalButtonDown(              "image/global/EButtonDown.jpg"                          , 1, 1, 1,  null                    ),
        EGlobalButtonEnter(             "image/global/EButtonEnter.jpg"                         , 1, 1, 1,  null                    ),
        EGlobalButtonEscape(            "image/global/EButtonEscape.jpg"                        , 1, 1, 1,  null                    ),

        EItemStrawberry(                "image/item/EItemStrawberry.png"                        , 1, 1, 1,  null                    ),
        EItemOrange(                    "image/item/EItemOrange.png"                            , 1, 1, 1,  null                    ),
        EItemCherry(                    "image/item/EItemCherry.png"                            , 1, 1, 1,  null                    ),
        EItemPear(                      "image/item/EItemPear.png"                              , 1, 1, 1,  null                    ),
        EItemApple(                     "image/item/EItemApple.png"                             , 1, 1, 1,  null                    ),
        EItemCoin(                      "image/item/EItemCoin.png"                              , 5, 4, 20, null                    ),

        EPlayerWalkLeft(                "image/player/EPlayerWalkLeft.png"                      , 1, 1, 1,  null                    ),
        EPlayerWalkRight(               "image/player/EPlayerWalkRight.png"                     , 1, 1, 1,  null                    ),
        EPlayerStandLeft(               "image/player/EPlayerStandLeft.png"                     , 1, 1, 1,  null                    ),
        EPlayerStandRight(              "image/player/EPlayerStandRight.png"                    , 1, 1, 1,  null                    ),

        EEnemy1WalkLeft(                "image/enemy/EEnemy1WalkLeft.png"                       , 1, 1, 1,  null                    ),
        EEnemy1WalkRight(               "image/enemy/EEnemy1WalkRight.png"                      , 1, 1, 1,  null                    ),
        EEnemy2WalkLeft(                "image/enemy/EEnemy2WalkLeft.png"                       , 9, 1, 9,  null                    ),
        EEnemy2WalkRight(               "image/enemy/EEnemy2WalkRight.png"                      , 9, 1, 9,  null                    ),

        ETextHelp(                      "image/text/ETextGameHelp.png"                          , 1, 1, 1,  null                    ),
        ETextPause(                     "image/text/ETextPaused.png"                            , 1, 1, 1,  null                    ),
        ETextPauseHelp(                 "image/text/ETextPauseHelp.png"                         , 1, 1, 1,  null                    ),
        ETextLevel1(                    "image/text/ETextLevel1.png"                            , 1, 1, 1,  null                    ),
        ETextLevel1Caption(             "image/text/ETextLevel1Caption.png"                     , 1, 1, 1,  null                    ),

        ;

        private                     String                  iPath                           = null;

        private                     Texture                 iTexture                        = null;
        private                     TextureRegion           iTextureRegion                  = null;

        public                      int                     iFramesX                        = 0;
        public                      int                     iFramesY                        = 0;
        public                      int                     iFrameCount                     = 0;
        public                      Elevation               iElevation                      = null;
        private                     int                     iFrameWidth                     = 0;
        private                     int                     iFrameHeight                    = 0;

        private JARImage( String aPath, int aFramesX, int aFramesY, int aFrameCount, Elevation aElevation )
        {
            iPath       = aPath;

            iFramesX    = aFramesX;
            iFramesY    = aFramesY;
            iFrameCount = aFrameCount;
            iElevation  = aElevation;
        }

        public static final void loadImages()
        {
            for ( JARImage img : values() )
            {
                img.loadImage();
            }
        }

        private final void loadImage()
        {
            JARDebug.image.info( "Loading image [" + iPath + "]" );

            iTexture        = new Texture( Gdx.files.internal( iPath ) );
            iTextureRegion  = new TextureRegion( iTexture );

            //DO flip vertical because we now use the y-reverted coordinate-system with y down!
            iTextureRegion.flip( false, true );

            iFrameWidth  = iTexture.getWidth()  / iFramesX;
            iFrameHeight = iTexture.getHeight() / iFramesY;
        }

        public static final void disposeImages()
        {
            for ( JARImage img : values() )
            {
                img.disposeImage();
            }
        }

        public final void disposeImage()
        {
            iTexture.dispose();
        }

        public final Sprite createNewSprite()
        {
            return new Sprite( iTextureRegion );
        }

        public final TextureRegion getTextureRegion()
        {
            return iTextureRegion;
        }

        public final float getTotalWidth()
        {
            return iTexture.getWidth();
        }

        public final float getTotalHeight()
        {
            return iTexture.getHeight();
        }

        public final int getFrameWidth()
        {
            return iFrameWidth;
        }

        public final int getFrameHeight()
        {
            return iFrameHeight;
        }
    }
