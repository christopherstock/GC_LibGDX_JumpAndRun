/*  $Id: LibDrawing.java 172 2013-09-25 06:50:05Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.libgdx.lib.ui;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
    import  com.badlogic.gdx.graphics.glutils.*;
    import  com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;

    /**************************************************************************************
    *   Offers drawing operations. Operations that do not draw images should only be used
    *   for debug purposes because their drawing performance is very low!
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    **************************************************************************************/
    public final class LibDrawing
    {
        /**************************************************************************************
        *   Draws a line.
        **************************************************************************************/
        public static final void drawLine( SpriteBatch batch, float x1, float y1, float x2, float y2, Color col )
        {
            //pause current sprite batch
            batch.end();

            //render the filled rect
            ShapeRenderer shapeRenderer = new ShapeRenderer();

            shapeRenderer.setProjectionMatrix(  batch.getProjectionMatrix() );
            shapeRenderer.begin(                ShapeType.Line              );
            shapeRenderer.setColor(             col                         );
            shapeRenderer.line(                 x1, y1, x2, y2              );
            shapeRenderer.end();

            //resume current sprite batch
            batch.begin();
        }

        /**************************************************************************************
        *   Draws and fills a rectangle.
        **************************************************************************************/
        public static final void fillRect( SpriteBatch batch, float x, float y, float width, float height, Color col, boolean alpha )
        {
            //pause current sprite batch
            batch.end();

            //enable alpha blending if desired
            if ( alpha )
            {
                Gdx.gl.glEnable(    GL10.GL_BLEND                                   );
                Gdx.gl.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA  );
            }

            //render the filled rect
            ShapeRenderer shapeRenderer = new ShapeRenderer();
            shapeRenderer.setProjectionMatrix(  batch.getProjectionMatrix() );
            shapeRenderer.begin(                ShapeType.FilledRectangle   );
            shapeRenderer.setColor(             col                         );
            shapeRenderer.filledRect(           x, y, width, height         );
            shapeRenderer.end();

            //disable alpha blending if desired
            if ( alpha )
            {
                Gdx.gl.glDisable(GL10.GL_BLEND);
            }

            //resume current sprite batch
            batch.begin();
        }

        /**************************************************************************************
        *   Draws and fills a triangle.
        **************************************************************************************/
        public static final void fillTriangle( SpriteBatch batch, float x1, float y1, float x2, float y2, float x3, float y3, Color col )
        {
            //pause current sprite batch
            batch.end();

            //render the filled rect
            ShapeRenderer shapeRenderer = new ShapeRenderer();

            shapeRenderer.setProjectionMatrix(  batch.getProjectionMatrix() );
            shapeRenderer.begin(                ShapeType.FilledTriangle    );
            shapeRenderer.setColor(             col                         );
            shapeRenderer.filledTriangle(       x1, y1, x2, y2, x3, y3      );
            shapeRenderer.end();

            //resume current sprite batch
            batch.begin();
        }

        /*****************************************************************************
        *   Draws a string with the specified LibGDX BitmapFont.
        *****************************************************************************/
        public static final void drawFont( SpriteBatch batch, BitmapFont bf, String str, float x, float y, float width, HAlignment hAnk, float scale, BitmapFont shadow )
        {
            //draw scaled shadow if desired
            if ( shadow != null )
            {
                shadow.setScale( scale );
                shadow.drawWrapped( batch, str, x + 1, y + 1, width, hAnk );
            }

            //draw scaled foreground
            bf.setScale( scale );
            bf.drawWrapped( batch, str, x, y, width, hAnk );
        }

        /*****************************************************************************
        *   Draws a libgdx sprite at the specified location with a specified anchor.
        *
        *   @param  img     The image to draw.
        *   @param  x       Drawing position x.
        *   @param  y       Drawing position y.
        *   @param  ank     The anchor for this drawing operation.
        *****************************************************************************/
        public static final void drawSprite
        (
            SpriteBatch     batch,
            Sprite          sprite,
            float           x,
            float           y,
            boolean         flipX,
            LibAnchor       ank,
            int             frameCountX,
            int             frameWidth,
            int             frameHeight,
            int             frameIndex,
            float           alpha,
            LibSpriteTrans  spriteTransformation,
            float           scaleX,
            float           scaleY,
            float           rotation
        )
        {
            //alter x and y according to ank
            {
                x += ank.getOffsetX( sprite.getRegionWidth()  );
                y += ank.getOffsetY( sprite.getRegionHeight() );
            }

            //clip desired frame
            {
                //get offset for image clip to pick
                int clipX = frameWidth  * ( frameIndex % frameCountX );
                int clipY = frameHeight * ( frameIndex / frameCountX );

                //clip sprite region to draw
                sprite.setRegion( clipX, clipY, frameWidth, frameHeight );
            }

            //flip
            {
                //X if desired - Y always
                sprite.flip( flipX, true );
            }

            //set drawing position and size
            {
                sprite.setBounds( x, y, frameWidth, frameHeight );
            }

            //scale and rotate
            {
                //assign origin for scaling and rotation
                float originX = -ank.getOffsetX( sprite.getRegionWidth()  );
                float originY = -ank.getOffsetY( sprite.getRegionWidth()  );
                sprite.setOrigin( originX, originY );

                //set scalation
                sprite.setScale( scaleX, scaleY );

                //set rotation
                sprite.setRotation( rotation );
            }

            //skew
            {
                //transform drawing vertices if desired
                if ( spriteTransformation != null )
                {
                    float[] vertices = sprite.getVertices();
                    spriteTransformation.transformVertices( vertices );
                }
            }

            //draw
            {
                //draw with specified alpha
                sprite.draw( batch, alpha );
            }
        }
    }
