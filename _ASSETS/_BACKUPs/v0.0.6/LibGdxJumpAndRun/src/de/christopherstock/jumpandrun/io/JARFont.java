/*  $Id: JARFont.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun.io;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.files.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.Texture.TextureFilter;
    import  com.badlogic.gdx.graphics.g2d.*;

    /***********************************************************************************************
    *   The font system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    ***********************************************************************************************/
    public enum JARFont
    {
        EPresidentBlack(    "EFontPresident.fnt",   "EFontPresidentBlack.png"   ),
        EPresidentWhite(    "EFontPresident.fnt",   "EFontPresidentWhite.png"   ),
        EPresidentGreen(    "EFontPresident.fnt",   "EFontPresidentGreen.png"   ),
        EPresidentRed(      "EFontPresident.fnt",   "EFontPresidentRed.png"     ),
        ;

        public                          BitmapFont      iFont                   = null;

        public                          String          iURIFontFile            = null;
        public                          String          iURIBitmapFile          = null;

        private JARFont( String urlFnt, String urlBmp )
        {
            iURIFontFile   = JARPath.EFont.getPath() + "/" + urlFnt;
            iURIBitmapFile = JARPath.EFont.getPath() + "/" + urlBmp;
        }

        private void load()
        {
            //crashes for html5
            FileHandle      fnt = Gdx.files.internal( iURIFontFile   );
            FileHandle      bmp = Gdx.files.internal( iURIBitmapFile );

            //create texture and enables smoother scaling
            Texture         t   = new Texture( bmp );
            t.setFilter( TextureFilter.Linear, TextureFilter.Linear );

            //create textureRegion
            TextureRegion   tr  = new TextureRegion( t );
            iFont = new BitmapFont( fnt, tr, true );
        }

        public static final void init()
        {
            //browse all fonts and load them
            for ( JARFont font : values() )
            {
                font.load();
            }
        }

        public static final void disposeAll()
        {
            //browse all fonts and dispose them
            for ( JARFont font : values() )
            {
                font.dispose();
            }
        }

        private final void dispose()
        {
            iFont.dispose();
        }
    }
