/*  $Id: LibPoint2D.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.libgdx.lib.math.geom;

    /*****************************************************************************
    *   Represents a point in 2D space.
    *   This is a portability remaining.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/math/geom/LibPoint2D.java $
    *****************************************************************************/
    public final class LibPoint2D
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
    }
