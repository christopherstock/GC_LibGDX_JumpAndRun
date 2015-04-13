/*  $Id: JARSound.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun.io;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.audio.*;

    /***********************************************************************************************
    *   The sound system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    ***********************************************************************************************/
    public enum JARSound
    {
        EPickUp,
        EKillEnemy,
        ;

      //private                                     Music                   rainMusic                       = null;
        private                                     Sound                   sound                           = null;

        public static final void init()
        {
            //dropSound   = Gdx.audio.newSound( Gdx.files.internal( "data/drop.wav" ) );
            //rainMusic   = Gdx.audio.newMusic( Gdx.files.internal( "data/rain.mp3" ) );

            EPickUp.sound   = Gdx.audio.newSound( Gdx.files.internal( "desktop/sound/fx1.wav" ) );
            EKillEnemy.sound   = Gdx.audio.newSound( Gdx.files.internal( "desktop/sound/fx2.wav" ) );
        }

        public static final void dispose()
        {
            //dispose all sounds!
            if ( EPickUp != null ) EPickUp.sound.dispose();
            if ( EKillEnemy != null ) EKillEnemy.sound.dispose();
        }

        public final void play()
        {
            sound.play();
        }
    }
