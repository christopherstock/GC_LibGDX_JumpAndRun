/*  $Id: JARScreen.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.*;

    /**************************************************************************************
    *   The version history system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    **************************************************************************************/
    public class JARScreen
    {
        private     static              int                     WIDTH                           = 0;
        private     static              int                     HEIGHT                          = 0;

        private     static              OrthographicCamera      camera                          = null;
        private     static              SpriteBatch             batch                           = null;

        public static final void init()
        {
            //assign screen dimensions
            WIDTH   = Gdx.graphics.getWidth();
            HEIGHT  = Gdx.graphics.getHeight();
            JARDebug.major.info( "Assigned screen dimensions [" + WIDTH + "][" + HEIGHT + "]" );

            //create and init orthographic camera and set ortho mode
            camera = new OrthographicCamera();
            camera.setToOrtho( true, WIDTH, HEIGHT );

            //create sprite batch
            batch = new SpriteBatch();
        }

        public static final void draw()
        {
            //clear screen
            Gdx.gl.glClearColor(  0.0f, 0.0f, 0.0f, 1 );
            Gdx.gl.glClear(       GL10.GL_COLOR_BUFFER_BIT );

            //update camera metrics and tell the SpriteBatch to render in the coordinate system specified by the camera
            camera.update();
            batch.setProjectionMatrix( camera.combined );

            //begin sprite batch
            batch.begin();

            //draw gamescreen
            {
                //draw gamescreen
                JARGame.drawGameScreen( batch );

                //draw HUD
                JARHUD.drawHUD( batch );

                //draw debug console
                //Console.draw( batch );
            }

            //end batch
            batch.end();
/*
            //draw stage
            JARStage.draw();
*/
        }

        public static final void dispose()
        {
            batch.dispose();
        }

        public static final int width()
        {
            return WIDTH;
        }

        public static final int height()
        {
            return HEIGHT;
        }
    }
