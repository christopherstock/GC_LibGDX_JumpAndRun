/*  $Id: JARScreen.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.JARSettings.Feature;
    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.ui.tween.*;

    /**************************************************************************************
    *   The version history system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    **************************************************************************************/
    public class JARScreen
    {
        public static enum DrawingLayer
        {
            EBehindPlayer,
            EBeforePlayer,
            ;
        }

        public      static              int                     WIDTH                           = 0;
        public      static              int                     HEIGHT                          = 0;

        private     static              OrthographicCamera      camera                          = null;
        private     static              SpriteBatch             batch                           = null;

        public static final void init()
        {
            //assign screen dimensions
            WIDTH   = Gdx.graphics.getWidth();
            HEIGHT  = Gdx.graphics.getHeight();
          //JARDebug.major.info( "Assigned screen dimensions [" + WIDTH + "][" + HEIGHT + "]" );

            //create and init orthographic camera and set ortho mode
            camera = new OrthographicCamera();
            camera.setToOrtho( true, WIDTH, HEIGHT );

            //create sprite batch - enable blending ( default setting )
            batch = new SpriteBatch();
            batch.enableBlending();
        }

        public static final void draw()
        {
            //clear screen
            Gdx.gl.glClearColor(  0.0f, 0.0f, 0.0f, 1.0f );
            Gdx.gl.glClear(       GL10.GL_COLOR_BUFFER_BIT );

            //update camera metrics and tell the SpriteBatch to render in the coordinate system specified by the camera
            camera.update();
            batch.setProjectionMatrix( camera.combined );

            //begin sprite batch
            batch.begin();

            //draw gamescreen, HUD and debug
            JARGame.drawGameScreen( batch );
            JARHUD.draw( batch );
            JARDebug.drawDebugScreen( batch );

            //draw tween test
            if ( Feature.TEST_TWEEN_ENGINE ) JARTweenExample.draw( batch );

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
    }
