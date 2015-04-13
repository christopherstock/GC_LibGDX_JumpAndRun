/*  $Id: LibMath.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.libgdx.lib.math.geom;

    /*****************************************************************************
    *   Offers additional math functionality.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/math/geom/LibMath.java $
    *****************************************************************************/
    public class LibMath
    {
        /*****************************************************************************
        *   Delivers the sin value of the given angle in degrees.
        *
        *   @param  degrees     An angle to get the sin for. Not in radiants but in degrees.
        *   @return             The sin-value for the specified angle.
        *****************************************************************************/
        public static final double sinDeg( float degrees )
        {
            return Math.sin( degrees * Math.PI / 180.0 );
        }

        /*****************************************************************************
        *   Delivers the cos value of the given angle in degrees.
        *
        *   @param  degrees     An angle to get the cos for. Not in radiants but in degrees.
        *   @return             The cos-value for the specified angle.
        *****************************************************************************/
        public static final double cosDeg( float degrees )
        {
            return Math.cos( degrees * Math.PI / 180.0 );
        }

        /*****************************************************************************
        *   Returns the distant point from a specified point and distance.
        *
        *   @param  pnt         The center point.
        *   @param  degrees     The angle for the distance. In degrees.
        *   @param  distX       The distance X from center.
        *   @param  distY       The distance Y from center.
        *   @return             The distant point.
        *****************************************************************************/
        public static final LibPoint2D sinCosPoint( LibPoint2D pnt, int degrees, int distX, int distY )
        {
            return new LibPoint2D
            (
                (float)( pnt.iX + cosDeg( degrees ) * distX ),
                (float)( pnt.iY + sinDeg( degrees ) * distY )
            );
        }
    }
