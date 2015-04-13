/*  $Id: JARKey.java 172 2013-09-25 06:50:05Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.hid;

    import  com.badlogic.gdx.*;

    /*****************************************************************************
    *   Represents one pressed key.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/hid/JARKey.java $
    *****************************************************************************/
    public class JARKey
    {
        public      static  final   JARKey      KEY_LEFT                        = new JARKey();
        public      static  final   JARKey      KEY_UP                          = new JARKey();
        public      static  final   JARKey      KEY_RIGHT                       = new JARKey();
        public      static  final   JARKey      KEY_DOWN                        = new JARKey();

        public      static  final   JARKey      KEY_ESC                         = new JARKey();

        public      static  final   JARKey      KEY_M                           = new JARKey();
        public      static  final   JARKey      KEY_B                           = new JARKey();
        public      static  final   JARKey      KEY_N                           = new JARKey();

        public      static  final   JARKey      KEY_1                           = new JARKey();
        public      static  final   JARKey      KEY_2                           = new JARKey();
        public      static  final   JARKey      KEY_3                           = new JARKey();
        public      static  final   JARKey      KEY_4                           = new JARKey();
        public      static  final   JARKey      KEY_5                           = new JARKey();

        private                     boolean     iHold                           = false;
        private                     boolean     iNeedsRelease                   = false;

        public JARKey()
        {
            iHold                   = false;
            iNeedsRelease           = false;
        }

        public boolean isHold()
        {
            return iHold;
        }

        public void setHold( boolean physicallyHold )
        {
            if ( iNeedsRelease )
            {
                if ( physicallyHold )
                {
                    //ignore till released
                }
                else
                {
                    iNeedsRelease = false;
                }
            }
            else
            {
                iHold = physicallyHold;
            }
        }

        public void ignoreTillNextRelease()
        {
            //release the key
            iHold                    = false;

            //only allow repress after release
            iNeedsRelease            = true;
        }

        public static final void checkKeyInput()
        {
            KEY_UP.setHold(     Gdx.input.isKeyPressed( Input.Keys.UP       ) );
            KEY_DOWN.setHold(   Gdx.input.isKeyPressed( Input.Keys.DOWN     ) );
            KEY_LEFT.setHold(   Gdx.input.isKeyPressed( Input.Keys.LEFT     ) );
            KEY_RIGHT.setHold(  Gdx.input.isKeyPressed( Input.Keys.RIGHT    ) );

            KEY_ESC.setHold(    Gdx.input.isKeyPressed( Input.Keys.ESCAPE   ) );

            KEY_M.setHold(      Gdx.input.isKeyPressed( Input.Keys.M        ) );
            KEY_B.setHold(      Gdx.input.isKeyPressed( Input.Keys.B        ) );
            KEY_N.setHold(      Gdx.input.isKeyPressed( Input.Keys.N        ) );

            KEY_1.setHold(      Gdx.input.isKeyPressed( Input.Keys.NUM_1    ) );
            KEY_2.setHold(      Gdx.input.isKeyPressed( Input.Keys.NUM_2    ) );
            KEY_3.setHold(      Gdx.input.isKeyPressed( Input.Keys.NUM_3    ) );
            KEY_4.setHold(      Gdx.input.isKeyPressed( Input.Keys.NUM_4    ) );
            KEY_5.setHold(      Gdx.input.isKeyPressed( Input.Keys.NUM_5    ) );
        }
    }
