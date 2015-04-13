/*  $Id: JARFont.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.files.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.Texture.TextureFilter;
    import  com.badlogic.gdx.graphics.g2d.*;

    /***********************************************************************************************
    *   The font system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
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
            presidentBlack  = new JARFont( "desktop/font/font_president.fnt", "desktop/font/font_president_black.png" );
            presidentWhite  = new JARFont( "desktop/font/font_president.fnt", "desktop/font/font_president_white.png" );
            presidentGreen  = new JARFont( "desktop/font/font_president.fnt", "desktop/font/font_president_green.png" );
            presidentRed    = new JARFont( "desktop/font/font_president.fnt",   "desktop/font/font_president_red.png"   );
        }

        public static final void dispose()
        {
            presidentBlack.iFont.dispose();
        }
    }
