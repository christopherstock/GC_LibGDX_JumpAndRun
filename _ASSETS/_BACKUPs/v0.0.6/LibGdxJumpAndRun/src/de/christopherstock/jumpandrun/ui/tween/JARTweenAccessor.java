/*  $Id: JARTweenAccessor.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui.tween;

    import  com.badlogic.gdx.graphics.g2d.*;
    import  aurelienribon.tweenengine.*;

    /*****************************************************************************
    *   Tries tweens.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/tween/JARTweenAccessor.java $
    *****************************************************************************/
    class JARTweenAccessor implements TweenAccessor<Sprite>
    {
        protected       static      final       int             SKEW_X1X4               = 0;

        @Override
        public int getValues( Sprite sprite, int tweenType, float[] returnValues )
        {
            switch ( tweenType )
            {
                case SKEW_X1X4:
                {
                    float[] vs = sprite.getVertices();
                    returnValues[ 0 ] = vs[ SpriteBatch.X1 ] - sprite.getX();
                    returnValues[ 1 ] = vs[ SpriteBatch.X4 ] - sprite.getX() - sprite.getWidth();
                    return 2;
                }
            }

            return -1;
        }

        @Override
        public void setValues( Sprite sprite, int tweenType, float[] newValues )
        {
            switch ( tweenType )
            {
                case SKEW_X1X4:
                {
                    float   x1 = sprite.getX();
                    float   x4 = x1 + sprite.getWidth();
                    float[] vs = sprite.getVertices();
                    vs[ SpriteBatch.X1 ] = x1 + newValues[ 0 ];
                    vs[ SpriteBatch.X4 ] = x4 + newValues[ 1 ];
                    break;
                }
            }
        }
    }
