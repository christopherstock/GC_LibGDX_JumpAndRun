/*  $Id: LibSpriteTrans.java 178 2013-09-30 12:25:35Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.libgdx.lib.ui;

    import  com.badlogic.gdx.graphics.g2d.*;

    /**************************************************************************************
    *   Defines all transformations for all vertices ( corners ) of a rectangular sprite.
    *
    *   TODO ASAP prune ??
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    **************************************************************************************/
    public final class LibSpriteTrans
    {
        public              float               iX1                     = 0.0f;
        public              float               iX2                     = 0.0f;
        public              float               iX3                     = 0.0f;
        public              float               iX4                     = 0.0f;

        public              float               iY1                     = 0.0f;
        public              float               iY2                     = 0.0f;
        public              float               iY3                     = 0.0f;
        public              float               iY4                     = 0.0f;

        public              float               iC1                     = 0.0f;
        public              float               iC2                     = 0.0f;
        public              float               iC3                     = 0.0f;
        public              float               iC4                     = 0.0f;

        public              float               iU1                     = 0.0f;
        public              float               iU2                     = 0.0f;
        public              float               iU3                     = 0.0f;
        public              float               iU4                     = 0.0f;

        public              float               iV1                     = 0.0f;
        public              float               iV2                     = 0.0f;
        public              float               iV3                     = 0.0f;
        public              float               iV4                     = 0.0f;

        public final void transformVertices( float[] vertices )
        {
            vertices[ SpriteBatch.X1 ] += iX1;
            vertices[ SpriteBatch.X2 ] += iX2;
            vertices[ SpriteBatch.X3 ] += iX3;
            vertices[ SpriteBatch.X4 ] += iX4;

            vertices[ SpriteBatch.Y1 ] += iY1;
            vertices[ SpriteBatch.Y2 ] += iY2;
            vertices[ SpriteBatch.Y3 ] += iY3;
            vertices[ SpriteBatch.Y4 ] += iY4;

            vertices[ SpriteBatch.C1 ] += iC1;
            vertices[ SpriteBatch.C2 ] += iC2;
            vertices[ SpriteBatch.C3 ] += iC3;
            vertices[ SpriteBatch.C4 ] += iC4;

            vertices[ SpriteBatch.U1 ] += iU1;
            vertices[ SpriteBatch.U2 ] += iU2;
            vertices[ SpriteBatch.U3 ] += iU3;
            vertices[ SpriteBatch.U4 ] += iU4;

            vertices[ SpriteBatch.V1 ] += iV1;
            vertices[ SpriteBatch.V2 ] += iV2;
            vertices[ SpriteBatch.V3 ] += iV3;
            vertices[ SpriteBatch.V4 ] += iV4;
        }
    }
