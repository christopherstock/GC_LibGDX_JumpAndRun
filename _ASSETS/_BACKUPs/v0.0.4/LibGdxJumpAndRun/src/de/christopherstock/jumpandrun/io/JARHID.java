/*  $Id: JARHID.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.jumpandrun.io;

    import  com.badlogic.gdx.*;
    import  de.christopherstock.jumpandrun.hid.*;

    /**************************************************************************************
    *   Handles user input. Only touch operations are handled. No keys this time!
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    **************************************************************************************/
    public class JARHID
    {
/*
        private         static          int         lastX                   = -1;
        private         static          int         lastY                   = -1;

        private         static          int         firstX                  = -1;
        private         static          int         firstY                  = -1;
*/
        public static final void checkKeyInput()
        {
            //check key down
            JARKey.KEY_UP.hold     = ( Gdx.input.isKeyPressed( Input.Keys.UP       ) );
            JARKey.KEY_DOWN.hold   = ( Gdx.input.isKeyPressed( Input.Keys.DOWN     ) );
            JARKey.KEY_LEFT.hold   = ( Gdx.input.isKeyPressed( Input.Keys.LEFT     ) );
            JARKey.KEY_RIGHT.hold  = ( Gdx.input.isKeyPressed( Input.Keys.RIGHT    ) );

            JARKey.KEY_ESC.hold    = ( Gdx.input.isKeyPressed( Input.Keys.ESCAPE   ) );
        }
/*
        public static final void checkPointerInput()
        {
            //check pointer down
            if ( Gdx.input.isTouched() )
            {
                //check click
                if ( lastX == -1 && lastY == -1 )
                {
                    firstX = Gdx.input.getX();
                    firstY = Gdx.input.getY();

                    //handle click on map window ( buttons are on the stage )
                    JARStrategyLevel.current().clickMapWindow( Gdx.input.getX(), Gdx.input.getY() );
                }
                else
                {
                    //check drag
                    int deltaX = Gdx.input.getX() - lastX;
                    int deltaY = Gdx.input.getY() - lastY;

                    //handle drag if delta occured
                    if ( deltaX != 0 || deltaY != 0 )
                    {
                        //StrategyDebug.bugfix.info( "drag [" + deltaX + "][" + deltaY + "]" );

                        //pass drag to level
                        JARStrategyLevel.current().scrollBy( firstX, firstY, deltaX, deltaY );
                    }
                }

                //assign
                lastX = Gdx.input.getX();
                lastY = Gdx.input.getY();
            }
            else
            {
                lastX = -1;
                lastY = -1;
            }
        }
*/
    }
