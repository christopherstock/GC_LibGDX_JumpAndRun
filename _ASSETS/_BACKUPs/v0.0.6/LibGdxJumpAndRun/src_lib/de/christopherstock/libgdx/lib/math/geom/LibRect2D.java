/*  $Id: LibRect2D.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.libgdx.lib.math.geom;

    /*****************************************************************************
    *   Represents a rectangular in 2D space.
    *   This is a portability remaining.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/math/geom/LibRect2D.java $
    *****************************************************************************/
    public class LibRect2D
    {
        public static enum Elevation
        {
            ENone,
            EDescending,
            EAscending,
            ;
        }

        public                      float           iLeft           = 0.0f;
        public                      float           iTop            = 0.0f;
        public                      float           iWidth          = 0.0f;
        public                      float           iHeight         = 0.0f;

        /*****************************************************************************
        *   Constructs a new rectangular.
        *
        *   @param  iX       Location x for the new rect.
        *   @param  iY       Location y for the new rect.
        *   @param  iWidth   Width for the new rect.
        *   @param  Height  Height for the new rect.
        *****************************************************************************/
        public LibRect2D( float aX, float aY, float aWidth, float aHeight )
        {
            iLeft                       = aX;
            iTop                        = aY;
            iWidth                      = aWidth;
            iHeight                     = aHeight;
        }

        /*****************************************************************************
        *   Checks if the given rect contains the given point.
        *
        *   @param  rect1   Rect to check point containment.
        *   @param  x       Point x.
        *   @param  y       Point y.
        *   @return         <code>true</code> if the point lies in the rectangle.
        *                   Otherwise <code>false</code>.
        *****************************************************************************/
        public boolean containsPoint( int x, int y )
        {
            boolean ret =
            (
                    x  >=  iLeft
                &&  x  <   iLeft    + iWidth
                &&  y  >=  iTop
                &&  y  <   iTop     + iHeight
            );

            return ret;
        }

        /*****************************************************************************
        *   Checks if the two given rects intersect.
        *
        *   @param  rect2   2nd rect to check for intersection.
        *   @return         <code>true</code> if the rects collide.
        *                   Otherwise <code>false</code>.
        *****************************************************************************/
        public boolean collidesWithRect( LibRect2D rect2 )
        {
            boolean ret =
            (
                    !equalsWithRect( rect2 )
                &&  iLeft + iWidth  >  rect2.iLeft
                &&  iLeft           <  rect2.iLeft + rect2.iWidth
                &&  iTop  + iHeight >  rect2.iTop
                &&  iTop            <  rect2.iTop + rect2.iHeight
            );
            return ret;
        }

        public Float getYonCollisionXrect( LibRect2D rect2, Elevation elevated )
        {
            if
            (
                    !equalsWithRect( rect2 )
                &&  iLeft           < rect2.iLeft + rect2.iWidth
                &&  iLeft + iWidth  > rect2.iLeft
            )
            {
                switch ( elevated )
                {
                    case ENone:
                    {
                        return Float.valueOf( iTop );
                    }

                    case EDescending:
                    {
                        //calculate elevated height
                        float progress   = rect2.iLeft - iLeft;
                        if ( progress < 0 ) progress = 0;
                        if ( progress > iWidth ) progress = iWidth;
                        float collisionY = ( ( iHeight / 2 ) * progress / iWidth );
                        float ret        = iTop + collisionY;
                        return Float.valueOf( ret );
                    }

                    case EAscending:
                    {
                        //calculate elevated height
                        float progress = rect2.iLeft + rect2.iWidth - iLeft;
                        if ( progress < 0       ) progress = 0;
                        if ( progress > iWidth  ) progress = iWidth;
                        float collisionY = ( iHeight / 2 ) - ( ( iHeight / 2 ) * progress / iWidth );
                        float ret        = iTop + collisionY;
                        return Float.valueOf( ret );
                    }
                }
            }

            return null;
        }

        public boolean equalsWithRect( LibRect2D rect2 )
        {
            boolean ret =
            (
                    iLeft   ==  rect2.iLeft
                &&  iWidth  ==  rect2.iWidth
                &&  iTop    ==  rect2.iTop
                &&  iHeight ==  rect2.iHeight
            );
            return ret;
        }

        public LibPoint2D getCenter()
        {
            return new LibPoint2D( iLeft + iWidth / 2, iTop + iHeight / 2 );
        }
    }
