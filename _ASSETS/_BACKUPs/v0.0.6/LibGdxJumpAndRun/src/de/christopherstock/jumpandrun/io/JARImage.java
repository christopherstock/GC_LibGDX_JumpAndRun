/*  $Id: JARImage.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun.io;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.*;
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
        EBgHill1(                       JARPath.EImageBg,           "EHill1.jpg"                                , 1, 1, 1,  null                    ),
        EBgForest1(                     JARPath.EImageBg,           "EForest1.jpg"                              , 1, 1, 1,  null                    ),
        EBgForest2(                     JARPath.EImageBg,           "EForest2.jpg"                              , 1, 1, 1,  null                    ),
        EBgTrees1(                      JARPath.EImageBg,           "ETrees1.png"                               , 1, 1, 1,  null                    ),

        EDecoSignpost1(                 JARPath.EImageDeco,         "ESignpost1.png"                            , 1, 1, 1,  null                    ),
        EDecoTree1(                     JARPath.EImageDeco,         "ETree1.png"                                , 1, 1, 1,  null                    ),
        EDecoRailing1(                  JARPath.EImageDeco,         "ERailing1.png"                             , 1, 1, 1,  null                    ),
        EDecoRailing1Ascending(         JARPath.EImageDeco,         "ERailing1.png"                             , 1, 1, 1,  Elevation.EAscending    ),
        EDecoRailing1Descending(        JARPath.EImageDeco,         "ERailing1.png"                             , 1, 1, 1,  Elevation.EDescending   ),

        EWallGrass1(                    JARPath.EImageWall,         "EGrass1.png"                               , 1, 1, 1,  Elevation.ENone         ),
        EWallGrass1Topping(             JARPath.EImageWall,         "EGrass1Topping.png"                        , 1, 1, 1,  Elevation.ENone         ),
        EWallGrass1Ascending(           JARPath.EImageWall,         "EGrass1.png"                               , 1, 1, 1,  Elevation.EAscending    ),
        EWallGrass1Descending(          JARPath.EImageWall,         "EGrass1.png"                               , 1, 1, 1,  Elevation.EDescending   ),
        EWallGrass1ToppingAscending(    JARPath.EImageWall,         "EGrass1Topping.png"                        , 1, 1, 1,  Elevation.EAscending    ),
        EWallGrass1ToppingDescending(   JARPath.EImageWall,         "EGrass1Topping.png"                        , 1, 1, 1,  Elevation.EDescending   ),
        EWallSand1(                     JARPath.EImageWall,         "ESand1.png"                                , 1, 1, 1,  Elevation.ENone         ),
        EWallStone1(                    JARPath.EImageWall,         "EStone1.png"                               , 1, 1, 1,  Elevation.ENone         ),
        EWallStone1Ascending(           JARPath.EImageWall,         "EStone1.png"                               , 1, 1, 1,  Elevation.EAscending    ),
        EWallStone1Descending(          JARPath.EImageWall,         "EStone1.png"                               , 1, 1, 1,  Elevation.EDescending   ),
        EWallWater1(                    JARPath.EImageWall,         "EWater1.png"                               , 1, 8, 8,  Elevation.ENone         ),
        EWallWood1(                     JARPath.EImageWall,         "EWood1.png"                                , 1, 1, 1,  Elevation.ENone         ),
        EWallWood1Ascending(            JARPath.EImageWall,         "EWood1.png"                                , 1, 1, 1,  Elevation.EAscending    ),
        EWallWood1Descending(           JARPath.EImageWall,         "EWood1.png"                                , 1, 1, 1,  Elevation.EDescending   ),
        EWallWood1ArcLeft(              JARPath.EImageWall,         "EWood1ArcLeft.png"                         , 1, 1, 1,  Elevation.ENone         ),
        EWallWood1ArcRight(             JARPath.EImageWall,         "EWood1ArcRight.png"                        , 1, 1, 1,  Elevation.ENone         ),
        EWallWood1Small(                JARPath.EImageWall,         "EWood1Small.png"                           , 1, 1, 1,  Elevation.ENone         ),

        EGlobalNumbers(                 JARPath.EImageGlobal,       "ENumbers.png"                              , 11, 1, 1, null                    ),
        EGlobalButtonLeft(              JARPath.EImageGlobal,       "EButtonLeft.jpg"                           , 1, 1, 1,  null                    ),
        EGlobalButtonRight(             JARPath.EImageGlobal,       "EButtonRight.jpg"                          , 1, 1, 1,  null                    ),
        EGlobalButtonUp(                JARPath.EImageGlobal,       "EButtonUp.jpg"                             , 1, 1, 1,  null                    ),
        EGlobalButtonDown(              JARPath.EImageGlobal,       "EButtonDown.jpg"                           , 1, 1, 1,  null                    ),
        EGlobalButtonEnter(             JARPath.EImageGlobal,       "EButtonEnter.jpg"                          , 1, 1, 1,  null                    ),
        EGlobalButtonEscape(            JARPath.EImageGlobal,       "EButtonEscape.jpg"                         , 1, 1, 1,  null                    ),
        EGlobalMaskCircle(              JARPath.EImageGlobal,       "EMaskCircle.png"                           , 1, 1, 1,  null                    ),

        EItemStrawberry(                JARPath.EImageItem,         "EItemStrawberry.png"                       , 1, 1, 1,  null                    ),
        EItemOrange(                    JARPath.EImageItem,         "EItemOrange.png"                           , 1, 1, 1,  null                    ),
        EItemCherry(                    JARPath.EImageItem,         "EItemCherry.png"                           , 1, 1, 1,  null                    ),
        EItemPear(                      JARPath.EImageItem,         "EItemPear.png"                             , 1, 1, 1,  null                    ),
        EItemApple(                     JARPath.EImageItem,         "EItemApple.png"                            , 3, 4, 10, null                    ),
        EItemCoin(                      JARPath.EImageItem,         "EItemCoin.png"                             , 5, 4, 20, null                    ),

        EPlayerWalkLeft(                JARPath.EImagePlayer,       "EWalk.png"                                 , 1, 1, 1,  null                    ),
        EPlayerStandLeft(               JARPath.EImagePlayer,       "EStand.png"                                , 1, 1, 1,  null                    ),

        EEnemyKRoolWalk(                JARPath.EImageEnemy,        "EKRoolWalk.png"                            , 1, 1, 1,  null                    ),
        EEnemyKremlinGreenWalk(         JARPath.EImageEnemy,        "EKremlinGreenWalk.png"                     , 9, 1, 9,  null                    ),
        EEnemyHedgehogWalk(             JARPath.EImageEnemy,        "EHedgehogWalk.png"                         , 8, 1, 8,  null                    ),
        EEnemyRatWalk(                  JARPath.EImageEnemy,        "ERatWalk.png"                              , 8, 1, 8,  null                    ),
        EEnemyBigGuyWalk(               JARPath.EImageEnemy,        "EBigGuyWalk.png"                           , 8, 1, 8,  null                    ),

        ETextHelp(                      JARPath.EImageText,         "ETextGameHelp.png"                         , 1, 1, 1,  null                    ),
        ETextPause(                     JARPath.EImageText,         "ETextPaused.png"                           , 1, 1, 1,  null                    ),
        ETextPauseHelp(                 JARPath.EImageText,         "ETextPauseHelp.png"                        , 1, 1, 1,  null                    ),
        ETextLevel1(                    JARPath.EImageText,         "ETextLevel1.png"                           , 1, 1, 1,  null                    ),
        ETextLevel1Caption(             JARPath.EImageText,         "ETextLevel1Caption.png"                    , 1, 1, 1,  null                    ),

        ;

        private                     String                  iURI                            = null;

        private                     Texture                 iTexture                        = null;
        private                     TextureRegion           iTextureRegion                  = null;

        private                     int                     iFramesX                        = 0;
        private                     int                     iFramesY                        = 0;
        private                     int                     iFrameCount                     = 0;
        private                     Elevation               iElevation                      = null;
        private                     int                     iFrameWidth                     = 0;
        private                     int                     iFrameHeight                    = 0;

        private JARImage( JARPath aPath, String aFileName, int aFramesX, int aFramesY, int aFrameCount, Elevation aElevation )
        {
            iURI   = aPath.getPath() + "/" + aFileName;

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
            JARDebug.image.info( "Loading image [" + iURI + "]" );

            //create Pixmap from file
            Pixmap pixmap  = new Pixmap( Gdx.files.internal( iURI ) );

            //initial transformation for ascending or descending images
            if ( iElevation != null && iElevation != Elevation.ENone )
            {
                pixmap = LibImage.skewElevated( pixmap, iElevation );
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
    }
