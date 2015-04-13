/*  $Id: JARFont.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
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
    *   @version    0.0.5
    ***********************************************************************************************/
    public class JARFont
    {
        public          static          JARFont         presidentBlack          = null;
        public          static          JARFont         presidentWhite          = null;
        public          static          JARFont         presidentGreen          = null;
        public          static          JARFont         presidentRed            = null;

        public                          BitmapFont      iFont                   = null;

        public JARFont( String urlFnt, String urlBmp )
        {
            //crashes for html5
            FileHandle      fnt = Gdx.files.internal( urlFnt );
            FileHandle      bmp = Gdx.files.internal( urlBmp );

            //create texture and enables smoother scaling
            Texture         t   = new Texture( bmp );
            t.setFilter( TextureFilter.Linear, TextureFilter.Linear );

            //create textureRegion
            TextureRegion   tr  = new TextureRegion( t );
            iFont = new BitmapFont( fnt, tr, true );
        }

        public static final void init()
        {
            //create fonts
            presidentBlack  = new JARFont( "font/EFontPresident.fnt", "font/EFontPresidentBlack.png" );
            presidentWhite  = new JARFont( "font/EFontPresident.fnt", "font/EFontPresidentWhite.png" );
            presidentGreen  = new JARFont( "font/EFontPresident.fnt", "font/EFontPresidentGreen.png" );
            presidentRed    = new JARFont( "font/EFontPresident.fnt", "font/EFontPresidentRed.png"   );
        }

        public static final void dispose()
        {
            presidentBlack.iFont.dispose();
        }
    }
