/*  $Id: LibImage.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
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
    *   @see        "$URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/io/LibImage.java $"
    *********************************************************************************/
    public abstract class LibImage
    {
        /*********************************************************************************
        *   Skews the given pixmap, extending it's size to 200% and transforming
        *   all pixels according to the specified elevation.
        *********************************************************************************/
        public static final Pixmap skewElevated( Pixmap src, Elevation elevation )
        {
            //create new pixmap
            Pixmap ret = new Pixmap( src.getWidth(), src.getHeight() * 2, Format.RGBA8888 );

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
                            //you should not invoke this method in this case
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
