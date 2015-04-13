package de.christopherstock.jumpandrun.hid;

/*  $Id: JARKey.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */

    /*****************************************************************************
    *   Represents one pressed key.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/hid/JARKey.java $
    *****************************************************************************/
    public class JARKey
    {
        public static final JARKey KEY_LEFT                        = new JARKey();
        public static final JARKey KEY_UP                          = new JARKey();
        public static final JARKey KEY_RIGHT                       = new JARKey();
        public static final JARKey KEY_DOWN                        = new JARKey();
        public static final JARKey KEY_ESC                         = new JARKey();

        public              boolean             hold                        = false;
        public              boolean             needsRelease                = false;

        public JARKey()
        {
            hold                   = false;
            needsRelease           = false;
        }

        public void ignoreTillNextRelease()
        {
            //release the key
            hold = false;

            //only allow repress after release
            needsRelease = true;
        }
    }
