/*  $Id: JARImage.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun.io;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  com.badlogic.gdx.scenes.scene2d.utils.*;
    import  de.christopherstock.jumpandrun.*;

    /***********************************************************************************************
    *   The image system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    ***********************************************************************************************/
    public enum JARImage
    {
        ENumbers(               "desktop/global/numbers.png" ),

        EItemStrwaberry(        "desktop/game/item_strawberry.png" ),
        EItemOrange(            "desktop/game/item_orange.png" ),
        EItemCherry(            "desktop/game/item_cherry.png" ),
        EItemPear(              "desktop/game/item_pear.png" ),
        EItemApple(             "desktop/game/item_apple.png" ),
        EItemCoin(              "desktop/game/item_coin.png" ),

        GAME_PLAYER_WALK_LEFT        ( "desktop/player/player_walk_left.png" ),
        GAME_PLAYER_WALK_RIGHT       ( "desktop/player/player_walk_right.png" ),
        GAME_PLAYER_STAND_LEFT       ( "desktop/player/player_stand_left.png" ),
        GAME_PLAYER_STAND_RIGHT      ( "desktop/player/player_stand_right.png" ),

        EEnemy1WalkLeft(            "desktop/enemies/enemy1_walk_left.png" ),
        EEnemy1WalkRight(           "desktop/enemies/enemy1_walk_right.png" ),

        GAME_HELP                    ( "desktop/game/text_game_help.png" ),
        GAME_PAWSED                  ( "desktop/game/text_pawsed.png" ),
        GAME_PAWSE_HELP              ( "desktop/game/text_paws_help.png" ),
        GAME_FLOOR_STONES_1          ( "desktop/floor/floor_stones1.png" ),
        GAME_FLOOR_STONES_2          ( "desktop/floor/floor_stones2.png" ),
        GAME_FLOOR_STONES_3          ( "desktop/floor/floor_stones3.png" ),
        GAME_FLOOR_STONES_4          ( "desktop/floor/floor_stones4.png" ),
        GAME_FLOOR_STONES_5          ( "desktop/floor/floor_stones5.png" ),
        GAME_FLOOR_STONES_6          ( "desktop/floor/floor_stones6.png" ),
        GAME_LEVEL_1                 ( "desktop/game/text_level1.png" ),
        GAME_LEVEL_1_CAPTION         ( "desktop/game/text_level1_caption.png" ),
        GAME_BG_HILL                 ( "desktop/bg/bg_hill.jpg" ),
        GAME_BG_TREES                ( "desktop/bg/bg_trees.png" ),
        GAME_BUTTON_LEFT             ( "desktop/global/buttonLeft.jpg" ),
        GAME_BUTTON_RIGHT            ( "desktop/global/buttonRight.jpg" ),
        GAME_BUTTON_UP               ( "desktop/global/buttonUp.jpg" ),
        GAME_BUTTON_DOWN             ( "desktop/global/buttonDown.jpg" ),
        GAME_BUTTON_ENTER            ( "desktop/global/buttonEnter.jpg" ),
        GAME_BUTTON_ESCAPE           ( "desktop/global/buttonEscape.jpg" ),

        ;

        private                     String                  iPath                           = null;

        private                     Texture                 iTexture                        = null;
        private                     TextureRegion           iTextureRegion                  = null;

        private JARImage( String aPath )
        {
            iPath = aPath;
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
            JARDebug.imageLoad.info( "Loading image [" + iPath + "]" );

            iTexture        = new Texture( Gdx.files.internal( iPath ) );
            iTextureRegion  = new TextureRegion( iTexture );

            //DO flip vertical because we now use the y-reverted coordinate-system with y down!
            iTextureRegion.flip( false, true );
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

        public final TextureRegionDrawable getTextureRegionDrawable()
        {
            return new TextureRegionDrawable( iTextureRegion );
        }

        public final float getWidth()
        {
            return iTextureRegion.getTexture().getWidth();
        }

        public final float getHeight()
        {
            return iTextureRegion.getTexture().getHeight();
        }
    }
