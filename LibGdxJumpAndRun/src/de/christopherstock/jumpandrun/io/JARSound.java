/*  $Id: JARSound.java 198 2014-07-02 19:27:29Z jenetic.bytemare $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun.io;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.audio.*;

    /***********************************************************************************************
    *   The sound system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    ***********************************************************************************************/
    public enum JARSound
    {
        EPickUp(            "EFxPickUp.wav"             ),
        EKillEnemy(         "EFxKillEnemy.wav"          ),
        EBgEnchantedWoods(  "EBgEnchantedWoods.mp3"     ),

        ;

        private                                     Sound                   sound                           = null;
        private                                     Music                   music                           = null;

        private                                     String                  iURI                            = null;

        private JARSound( String aFileName )
        {
            iURI = JARPath.ESound.getPath() + "/" + aFileName;
        }

        public final void load()
        {
            sound   = Gdx.audio.newSound( Gdx.files.internal( iURI ) );
          //music   = Gdx.audio.newMusic( Gdx.files.internal( iURI ) );
        }

        public static final void init()
        {
            //browse all sounds and load them
            for ( JARSound js : values() )
            {
                js.load();
            }
        }

        public static final void disposeAll()
        {
            //dispose all sounds
            for ( JARSound js : values() )
            {
                js.dispose();
            }
        }

        public final void playSound()
        {
            sound.play();
        }

        public final void dispose()
        {
            if ( sound != null ) sound.dispose();
            if ( music != null ) music.dispose();
        }
    }
