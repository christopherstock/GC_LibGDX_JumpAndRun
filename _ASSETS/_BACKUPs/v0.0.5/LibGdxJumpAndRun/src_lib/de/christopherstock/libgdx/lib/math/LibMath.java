/*  $Id: LibMath.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.libgdx.lib.math;

    import  de.christopherstock.libgdx.lib.ui.*;

    /**************************************************************************************
    *   Offers arithmetic functionality.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    **************************************************************************************/
    public abstract class LibMath
    {
        /**************************************************************************************
        *   Creates a random integer of the specified range.
        *   There is no need to set a random seed.
        *   {@link Math#random()} does this on the first invocation.
        **************************************************************************************/
        public static final int getRandom( int min, int max )
        {
            int     random  = min + (int)( Math.random() * ( ( max - min ) + 1 ) );
            return  random;
        }

        /**************************************************************************************
        *   Delivers the sin-value from a degree-value.
        *
        *   @param  degrees     The degrees to get the sin-value for.
        *   @return             The sin-value from -1.0f to 1.0f of the given degree value.
        **************************************************************************************/
        public static final float sinDeg( float degrees )
        {
            return (float)Math.sin( degrees * Math.PI / 180.0f );
        }

        /**************************************************************************************
        *   Delivers the cos-value from a degree-value.
        *
        *   @param  degrees     The degrees to get the cos-value for.
        *   @return     The cos-value from -1.0f to 1.0f of the given degree value.
        **************************************************************************************/
        public static final float cosDeg( float degrees )
        {
            return (float)Math.cos( degrees * Math.PI / 180.0f );
        }

        /*****************************************************************************
        *   Returns the distant point from a specified point and distance.
        *
        *   @param  p           The center point.
        *   @param  degrees     The angle for the distance <b>in degrees</b>.
        *   @param  distX       The distance X from center.
        *   @param  distY       The distance Y from center.
        *   @return             The distant point.
        *****************************************************************************/
        public static final LibPoint2D sinCosPoint( LibPoint2D p, int degrees, int distX, int distY )
        {
            return new LibPoint2D
            (
                ( p.iX + cosDeg( degrees ) * distX ),
                ( p.iY + sinDeg( degrees ) * distY )
            );
        }

        public static final float normalizeAngle( float aAngle )
        {
            float angle = aAngle;
            while ( angle <    0.0f ) angle += 360.0f;
            while ( angle >= 360.0f ) angle -= 360.0f;
            return angle;
        }

        public static final float getAngleBetweenPoints( LibCoordinate2D p1, LibCoordinate2D p2 )
        {
            double distX = p2.getX() - p1.getX();
            double distY = p2.getY() - p1.getY();
            double angle = 0.0;

            if ( distX == 0.0 )
            {
                if ( distY == 0.0 )     angle = 0.0;
                else if( distY > 0.0 )  angle = Math.PI / 2.0;
                else                    angle = ( Math.PI * 3.0 ) / 2.0;
            }
            else if ( distY == 0.0 )
            {
                if ( distX > 0.0 )      angle = 0.0;
                else                    angle = Math.PI;
            }
            else
            {
                if      ( distX < 0.0 ) angle = Math.atan( distY / distX ) + Math.PI;
                else if ( distY < 0.0 ) angle = Math.atan( distY / distX ) + ( 2 * Math.PI );
                else                    angle = Math.atan( distY / distX );
            }

            //convert to degree
            float ret = (float)( ( angle * 180 ) / Math.PI );

            return normalizeAngle( ret );
        }
    }
