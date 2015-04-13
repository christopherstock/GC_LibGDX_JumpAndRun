/*  $Id: LibUI.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.libgdx.lib.ui;

    import  com.badlogic.gdx.graphics.*;

    /*****************************************************************************
    *   Settings for the user interface.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/ui/LibUI.java $
    *****************************************************************************/
    public class LibUI
    {
        public          static          final           float           ALPHA_OPAQUE                        = 1.0f;
        public          static          final           float           SCALATION_NONE                      = 1.0f;
        public          static          final           float           ROTATION_NONE                       = 0.0f;

        public static final Color colorFromRGBA( int argb )
        {
            Color ret = new Color();
            Color.rgba8888ToColor( ret, argb );
            return ret;
        }
    }
