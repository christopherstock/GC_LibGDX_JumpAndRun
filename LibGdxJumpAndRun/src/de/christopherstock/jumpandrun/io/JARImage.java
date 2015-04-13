/*  $Id: JARImage.java 190 2014-02-17 09:34:53Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun.io;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.JARSettings.Debug;
    import  de.christopherstock.jumpandrun.ui.*;
    import  de.christopherstock.jumpandrun.ui.JARSprite.*;
    import  de.christopherstock.libgdx.lib.io.*;
    import  de.christopherstock.libgdx.lib.math.geom.LibRect2D.Elevation;

    /***********************************************************************************************
    *   The image system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    ***********************************************************************************************/
    public enum JARImage
    {
        EBgHill1(                       JARPath.EImageBg,           "EHill1.jpg"                , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EBgForest1(                     JARPath.EImageBg,           "EForest1.jpg"              , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EBgForest2(                     JARPath.EImageBg,           "EForest2.jpg"              , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EBgTrees1(                      JARPath.EImageBg,           "ETrees1.png"               , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),

        EDecoSignpost1(                 JARPath.EImageDeco,         "ESignpost1.png"            , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EDecoTree1(                     JARPath.EImageDeco,         "ETree1.png"                , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EDecoRailing1(                  JARPath.EImageDeco,         "ERailing1.png"             , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EDecoRailing1Ascending(         JARPath.EImageDeco,         "ERailing1.png"             , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.EAscending,   JARSpriteFx.ENone                   ),
        EDecoRailing1Descending(        JARPath.EImageDeco,         "ERailing1.png"             , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.EDescending,  JARSpriteFx.ENone                   ),

        EWallGrass1(                    JARPath.EImageWall,         "EGrass1.png"               , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EWallGrass1Ascending(           JARPath.EImageWall,         "EGrass1.png"               , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.EAscending,   JARSpriteFx.ENone                   ),
        EWallGrass1Descending(          JARPath.EImageWall,         "EGrass1.png"               , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.EDescending,  JARSpriteFx.ENone                   ),
        EWallGrass1Topping(             JARPath.EImageWall,         "EGrass1Topping.png"        , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ESkewX1X4Horizontal     ),
        EWallGrass1ToppingAscending(    JARPath.EImageWall,         "EGrass1Topping.png"        , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.EAscending,   JARSpriteFx.ESkewX1X4Elevated       ),
        EWallGrass1ToppingDescending(   JARPath.EImageWall,         "EGrass1Topping.png"        , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.EDescending,  JARSpriteFx.ESkewX1X4Elevated       ),
        EWallSand1(                     JARPath.EImageWall,         "ESand1.png"                , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EWallStone1(                    JARPath.EImageWall,         "EStone1.png"               , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EWallStone1Ascending(           JARPath.EImageWall,         "EStone1.png"               , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.EAscending,   JARSpriteFx.ENone                   ),
        EWallStone1Descending(          JARPath.EImageWall,         "EStone1.png"               , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.EDescending,  JARSpriteFx.ENone                   ),
        EWallWater1(                    JARPath.EImageWall,         "EWater1.png"               , 1,    8,  8,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EWallWood1(                     JARPath.EImageWall,         "EWood1.png"                , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EWallWood1Ascending(            JARPath.EImageWall,         "EWood1.png"                , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.EAscending,   JARSpriteFx.ENone                   ),
        EWallWood1Descending(           JARPath.EImageWall,         "EWood1.png"                , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.EDescending,  JARSpriteFx.ENone                   ),
        EWallWood1ArcLeft(              JARPath.EImageWall,         "EWood1ArcLeft.png"         , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EWallWood1ArcRight(             JARPath.EImageWall,         "EWood1ArcRight.png"        , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EWallWood1Small(                JARPath.EImageWall,         "EWood1Small.png"           , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),

        EGlobalNumbers(                 JARPath.EImageGlobal,       "ENumbers.png"              , 11,   1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EGlobalButtonLeft(              JARPath.EImageGlobal,       "EButtonLeft.jpg"           , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EGlobalButtonRight(             JARPath.EImageGlobal,       "EButtonRight.jpg"          , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EGlobalButtonUp(                JARPath.EImageGlobal,       "EButtonUp.jpg"             , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EGlobalButtonDown(              JARPath.EImageGlobal,       "EButtonDown.jpg"           , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EGlobalButtonEnter(             JARPath.EImageGlobal,       "EButtonEnter.jpg"          , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EGlobalButtonEscape(            JARPath.EImageGlobal,       "EButtonEscape.jpg"         , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EGlobalMaskCircle(              JARPath.EImageGlobal,       "EMaskCircle.png"           , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),

        EItemStrawberry(                JARPath.EImageItem,         "EStrawberry.png"           , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EItemOrange(                    JARPath.EImageItem,         "EOrange.png"               , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EItemCherry(                    JARPath.EImageItem,         "ECherry.png"               , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EItemPear(                      JARPath.EImageItem,         "EPear.png"                 , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EItemApple(                     JARPath.EImageItem,         "EApple.png"                , 3,    4,  10, FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EItemCoin(                      JARPath.EImageItem,         "ECoin.png"                 , 5,    4,  20, FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),

        EPlayerWalk(                    JARPath.EImagePlayer,       "EWalk.png"                 , 10,   2,  20, FrameAnimationTick.EEvery2ndTick,          Elevation.ENone,        JARSpriteFx.ENone                   ),
        EPlayerStand(                   JARPath.EImagePlayer,       "EStand.png"                , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),

        EEnemyKRoolWalk(                JARPath.EImageEnemy,        "EKRoolWalk.png"            , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EEnemyKremlinGreenWalk(         JARPath.EImageEnemy,        "EKremlinGreenWalk.png"     , 9,    1,  9,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EEnemyHedgehogWalk(             JARPath.EImageEnemy,        "EHedgehogWalk.png"         , 8,    1,  8,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EEnemyRatWalk(                  JARPath.EImageEnemy,        "ERatWalk.png"              , 8,    1,  8,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        EEnemyBigGuyWalk(               JARPath.EImageEnemy,        "EBigGuyWalk.png"           , 8,    1,  8,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),

        ETextHelp(                      JARPath.EImageText,         "ETextGameHelp.png"         , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        ETextPause(                     JARPath.EImageText,         "ETextPaused.png"           , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        ETextPauseHelp(                 JARPath.EImageText,         "ETextPauseHelp.png"        , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        ETextLevel1(                    JARPath.EImageText,         "ETextLevel1.png"           , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),
        ETextLevel1Caption(             JARPath.EImageText,         "ETextLevel1Caption.png"    , 1,    1,  1,  FrameAnimationTick.EEvery10thTick,         Elevation.ENone,        JARSpriteFx.ENone                   ),

        ;

        private                     String                  iURI                            = null;

        private                     Texture                 iTexture                        = null;
        private                     TextureRegion           iTextureRegion                  = null;
        private                     Elevation               iElevation                      = null;
        private                     JARSpriteFx             iSpriteFx                       = null;
        private                     FrameAnimationTick     iFrameAnimationDelay            = null;

        private                     int                     iFramesX                        = 0;
        private                     int                     iFramesY                        = 0;
        private                     int                     iFrameCount                     = 0;
        private                     int                     iFrameWidth                     = 0;
        private                     int                     iFrameHeight                    = 0;

        private JARImage( JARPath aPath, String aFileName, int aFramesX, int aFramesY, int aFrameCount, FrameAnimationTick aFrameAnimationDelay, Elevation aElevation, JARSpriteFx aSpriteFx )
        {
            iURI                    = aPath.getPath() + "/" + aFileName;

            iFramesX                = aFramesX;
            iFramesY                = aFramesY;
            iFrameCount             = aFrameCount;
            iElevation              = aElevation;
            iSpriteFx               = aSpriteFx;
            iFrameAnimationDelay    = aFrameAnimationDelay;
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
            JARDebug.image.info( "Loading image [" + iURI + "]" );

            //create Pixmap from file
            Pixmap pixmap  = new Pixmap( Gdx.files.internal( iURI ) );

            //initial transformation for ascending or descending images
            if ( iElevation != Elevation.ENone )
            {
                //only if the debug workaround is enabled
                if ( Debug.DRAW_ELEVATED_BG )
                {
                    //pixmap = LibImage.upsizeElevated( pixmap, iElevation, Debug.DRAW_ELEVATED_BG );
                    pixmap = LibImage.addDebugBg( pixmap, Color.YELLOW );
                }
            }

            //create Texture from Pixmap and wrap texture into TextureRegion
            iTexture        = new Texture( pixmap );
            iTextureRegion  = new TextureRegion( iTexture );

            //DO flip vertical because we now use the y-reverted coordinate-system with y down!
            iTextureRegion.flip( false, true );

            //calculate frame dimensions
            iFrameWidth  = iTexture.getWidth()  / iFramesX;
            iFrameHeight = iTexture.getHeight() / iFramesY;

            //dispose pixmap
            pixmap.dispose();
        }

        public static final void disposeAll()
        {
            for ( JARImage img : values() )
            {
                img.dispose();
            }
        }

        public final void dispose()
        {
            iTexture.dispose();
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

        public final int getFramesX()
        {
            return iFramesX;
        }

        public final int getFrameCount()
        {
            return iFrameCount;
        }

        public final Elevation getElevation()
        {
            return iElevation;
        }

        public final JARSpriteFx getSpriteFx()
        {
            return iSpriteFx;
        }

        public final FrameAnimationTick getFrameAnimationDelay()
        {
            return iFrameAnimationDelay;
        }
    }
