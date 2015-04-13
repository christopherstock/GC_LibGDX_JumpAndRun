/*  $Id: LibUI.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.libgdx.lib.ui;

    import  com.badlogic.gdx.graphics.*;

    /*****************************************************************************
    *   Settings for the user interface.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/ui/LibUI.java $
    *****************************************************************************/
    public class LibUI
    {
        public          static          final           float           ALPHA_OPAQUE                        = 1.0f;

        public static final Color colorFromRGBA( int argb )
        {
            Color ret = new Color();
            Color.rgba8888ToColor( ret, argb );

            return ret;
        }
    }
