/*  $Id: JARSound.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun.io;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.audio.*;

    /***********************************************************************************************
    *   The sound system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
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
          //dropSound           = Gdx.audio.newSound( Gdx.files.internal( "sound/EDrop.wav" ) );
          //rainMusic           = Gdx.audio.newMusic( Gdx.files.internal( "sound/ERain.mp3" ) );

            EPickUp.sound       = Gdx.audio.newSound( Gdx.files.internal( "sound/EFx1.wav" ) );
            EKillEnemy.sound    = Gdx.audio.newSound( Gdx.files.internal( "sound/EFx2.wav" ) );
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
