/*  $Id: LibSpriteTrans.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.libgdx.lib.ui;

    import  com.badlogic.gdx.graphics.g2d.*;

    /**************************************************************************************
    *   Defines all transformations for all vertices ( corners ) of a rectangular sprite.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    **************************************************************************************/
    public final class LibSpriteTrans
    {
        public              float               iX1                     = 0.0f;
        public              float               iY1                     = 0.0f;

        public              float               iX2                     = 0.0f;
        public              float               iY2                     = 0.0f;

        public              float               iX3                     = 0.0f;
        public              float               iY3                     = 0.0f;

        public              float               iX4                     = 0.0f;
        public              float               iY4                     = 0.0f;

        public final void transformVertices( float[] vertices )
        {
            vertices[ SpriteBatch.X1 ] += iX1;
            vertices[ SpriteBatch.Y1 ] += iY1;

            vertices[ SpriteBatch.X2 ] += iX2;
            vertices[ SpriteBatch.Y2 ] += iY2;

            vertices[ SpriteBatch.X3 ] += iX3;
            vertices[ SpriteBatch.Y3 ] += iY3;

            vertices[ SpriteBatch.X4 ] += iX4;
            vertices[ SpriteBatch.Y4 ] += iY4;
        }
    }
