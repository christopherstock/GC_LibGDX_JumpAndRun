/*  $Id: JARTweenExample.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui.tween;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  de.christopherstock.jumpandrun.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  aurelienribon.tweenengine.*;
    import  aurelienribon.tweenengine.equations.*;

    /*****************************************************************************
    *   Tries tweens.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/tween/JARTweenExample.java $
    *****************************************************************************/
    public class JARTweenExample
    {
        protected   static                  TweenManager    tweenManager            = null;
        protected   static                  TweenCallback   windCallback            = null;

        protected   static                  Sprite          testSprite              = null;

        public static final void init()
        {
            //create manager
            tweenManager = new TweenManager();

            //create testwise sprite
            testSprite = new Sprite( JARImage.EWallGrass1Topping.getTextureRegion() );

            //create wind callback
            windCallback = new TweenCallback()
            {
                @Override
                public void onEvent( int type, BaseTween<?> source )
                {
                    float d = 1.0f;                             //MathUtils.random() * 0.5f + 0.5f;     // duration in seconds
                    float t = testSprite.getHeight() * 0.5f;    //-0.5f * testSprite.getHeight();       // amplitude

                    Tween.to(       testSprite, JARTweenAccessor.SKEW_X1X4, d   )
                    .target(        t, t                                        )
                    .ease(          Sine.INOUT                                  )
                    .repeatYoyo(    1, 0                                        )
                    .setCallback(   windCallback                                )
                    .start(         tweenManager                                )
                    ;
                }
            };

            //register this Tween-Accessor
            Tween.registerAccessor( Sprite.class, new JARTweenAccessor() );
            Tween.call( windCallback ).start( tweenManager );

            JARDebug.tween.info( "Initializing tween test" );
        }

        public static final void draw( SpriteBatch batch )
        {
            //draw the sprite
            //LibDrawing.drawSprite( batch, testSprite, 0, 0, false, LibAnchor.ELeftTop, 1, (int)testSprite.getWidth(), (int)testSprite.getHeight(), 0, 1.0f );

            testSprite.draw( batch );
        }

        public static final void tick()
        {
            //update the tween
            tweenManager.update( Gdx.graphics.getDeltaTime() );
        }
    }
