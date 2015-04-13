/*  $Id: LibAnchor.java 172 2013-09-25 06:50:05Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.libgdx.lib.ui;

    /*****************************************************************************
    *   Specifies all anchors for drawing (image) objects.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/ui/LibAnchor.java $
    *****************************************************************************/
    public enum LibAnchor
    {
        ELeftTop,
        ELeftMiddle,
        ELeftBottom,
        ECenterTop,
        ECenterMiddle,
        ECenterBottom,
        ERightTop,
        ERightMiddle,
        ERightBottom,
        ;

        public final float getOffsetX( float width )
        {
            switch ( this )
            {
                case ELeftTop:
                case ELeftMiddle:
                case ELeftBottom:
                {
                    return 0.0f;
                }
                case ECenterTop:
                case ECenterMiddle:
                case ECenterBottom:
                {
                    return -width / 2;
                }
                case ERightTop:
                case ERightMiddle:
                case ERightBottom:
                {
                    return -width;
                }
            }
            return 0.0f;
        }

        public final float getOffsetY( float height )
        {
            switch ( this )
            {
                case ELeftTop:
                case ECenterTop:
                case ERightTop:
                {
                    return 0.0f;
                }
                case ELeftMiddle:
                case ECenterMiddle:
                case ERightMiddle:
                {
                    return -height / 2;
                }
                case ELeftBottom:
                case ECenterBottom:
                case ERightBottom:
                {
                    return -height;
                }
            }
            return 0.0f;
        }
    }
