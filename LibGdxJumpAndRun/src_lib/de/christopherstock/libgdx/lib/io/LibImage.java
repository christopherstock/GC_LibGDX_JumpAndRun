/*  $Id: LibImage.java 181 2013-09-30 14:19:44Z jenetic.bytemare@googlemail.com $
 *  ==============================================================================================================
 */
    package de.christopherstock.libgdx.lib.io;

    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.Pixmap.Format;
    import  de.christopherstock.libgdx.lib.math.geom.LibRect2D.Elevation;

    /*********************************************************************************
    *   Offers functionality for transforming images.
    *
    *   @author     $Author: jenetic.bytemare@googlemail.com $
    *   @version    0.0.6
    *   @see        "$URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/io/LibImage.java $"
    *********************************************************************************/
    public abstract class LibImage
    {
        /*********************************************************************************
        *   Adds a debug bg to the given image
        *********************************************************************************/
        public static final Pixmap addDebugBg( Pixmap src, Color col )
        {
            //create new pixmap with double size
            Pixmap ret = new Pixmap( src.getWidth(), src.getHeight(), Format.RGBA8888 );

            //fill bg
            ret.setColor(       col );
            ret.fillRectangle(  0, 0, ret.getWidth(), ret.getHeight() );

            //draw src pixmap
            ret.drawPixmap( src, 0, 0 );

            return ret;
        }

        /*********************************************************************************
        *   Skews the given pixmap, extending it's size to 200% and transforming
        *   all pixels according to the specified elevation.
        *
        *   @deprecated     Do NOT upsize elevated images anymore!
        *********************************************************************************/
        @Deprecated
        public static final Pixmap upsizeElevated( Pixmap src, Elevation elevation, boolean fillDebugBg )
        {
            //return src pixmap if no elevation is specified
            if ( elevation == Elevation.ENone ) return src;

            //create new pixmap with double size
            Pixmap ret = new Pixmap( src.getWidth(), src.getHeight() * 2, Format.RGBA8888 );

            //debug - fill bg rectangle
            if ( fillDebugBg )
            {
                ret.setColor(       Color.YELLOW );
                ret.fillRectangle(  0, 0, ret.getWidth(), ret.getHeight() );
            }

            //browse all pixels of the src bitmap
            for ( int y = 0; y < src.getHeight(); ++y )
            {
                for ( int x = 0; x < src.getWidth(); ++x )
                {
                    //pick pixel
                    int pixel = src.getPixel( x, y );
                    int destY = y + src.getHeight();

                    //assign new pixel to dst
                    ret.drawPixel( x, destY, pixel );
                }
            }

            //dispose src pixmap
            src.dispose();

            return ret;
        }

        /*********************************************************************************
        *   Skews the given pixmap, extending it's size to 200% and transforming
        *   all pixels according to the specified elevation.
        *
        *   @deprecated     Replaced by sprite effects.
        *********************************************************************************/
        @Deprecated
        public static final Pixmap skewElevated( Pixmap src, Elevation elevation, boolean fillDebugBg )
        {
            //return pixmap if no elevation is specified
            if ( elevation == Elevation.ENone ) return src;

            //create new pixmap
            Pixmap ret = new Pixmap( src.getWidth(), src.getHeight() * 2, Format.RGBA8888 );

            //debug - fill bg rectangle
            if ( fillDebugBg )
            {
                ret.setColor(       Color.YELLOW );
                ret.fillRectangle(  0, 0, ret.getWidth(), ret.getHeight() );
            }

            //browse all pixels of the src bitmap
            for ( int y = 0; y < src.getHeight(); ++y )
            {
                for ( int x = 0; x < src.getWidth(); ++x )
                {
                    //pick pixel
                    int pixel = src.getPixel( x, y );

                    //calc new y
                    int destY = 0;
                    switch ( elevation )
                    {
                        case EAscending:
                        {
                            destY = y + ( ( src.getWidth() - x ) * src.getHeight() / src.getWidth() );
                            break;
                        }
                        case EDescending:
                        {
                            destY = y                   + ( x * src.getHeight() / src.getWidth() );
                            break;
                        }
                        case ENone:
                        {
                            //don't skew image - you should NOT invoke this method in this case because the image size (height) will be changed!
                            destY = y;
                        }
                    }

                    //assign new pixel to dst
                    ret.drawPixel( x, destY, pixel );
                }
            }

            //dispose src pixmap
            src.dispose();

            return ret;
        }
    }
