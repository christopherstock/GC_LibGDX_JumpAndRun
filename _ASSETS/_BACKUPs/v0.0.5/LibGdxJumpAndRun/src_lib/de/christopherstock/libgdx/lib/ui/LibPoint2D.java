/*  $Id: LibPoint2D.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.libgdx.lib.ui;

    /*****************************************************************************
    *   Represents a point in 2D space.
    *   This is a portability remaining.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/ui/LibPoint2D.java $
    *****************************************************************************/
    public final class LibPoint2D implements LibCoordinate2D
    {
        public          float       iX       = 0;
        public          float       iY       = 0;

        /*****************************************************************************
        *   Constructs a new point in 2D space.
        *
        *   @param  iX   Point's new x coordinate.
        *   @param  iY   Point's new y coordinate.
        *****************************************************************************/
        public LibPoint2D( float aX, float aY )
        {
            iX = aX;
            iY = aY;
        }

        public LibPoint2D( LibCoordinate2D aCoordinate )
        {
            iX = aCoordinate.getX();
            iY = aCoordinate.getY();
        }

        @Override
        public final float getX()
        {
            return iX;
        }

        @Override
        public final float getY()
        {
            return iY;
        }
    }
