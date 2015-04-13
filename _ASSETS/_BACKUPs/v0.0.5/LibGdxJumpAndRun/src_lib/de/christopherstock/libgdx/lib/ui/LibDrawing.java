/*  $Id: LibDrawing.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.libgdx.lib.ui;

    import com.badlogic.gdx.*;
    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
    import  com.badlogic.gdx.graphics.glutils.*;
    import  com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;

    /**************************************************************************************
    *   Offers drawing operations. Operations that do not draw images should only be used
    *   for debug purposes because their drawing performance is very low!
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    **************************************************************************************/
    public final class LibDrawing
    {
        public static final void fillRect( SpriteBatch batch, float x, float y, float width, float height, Color col, boolean alpha )
        {
            //pause current sprite batch
            batch.end();

            //enable alpha blending if desired
            if ( alpha )
            {
                Gdx.gl.glEnable(GL10.GL_BLEND);
                Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
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

        public static final void fillLine( SpriteBatch batch, float x1, float y1, float x2, float y2, Color col )
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

        /*****************************************************************************
        *   Draws an image at the specified location with a specified anchor.
        *
        *   @param  img     The image to draw.
        *   @param  x       Drawing position x.
        *   @param  y       Drawing position y.
        *   @param  ank     The anchor for this drawing operation.
        *****************************************************************************/
        public static final void drawImage( SpriteBatch batch, TextureRegion img, float x, float y, LibAnchor ank, float alpha )
        {
            LibDrawing.drawImageScaledClipped( batch, img, x, y, ank, 0, 0, img.getTexture().getWidth(), img.getTexture().getHeight(), alpha );
        }

        public static final void drawImageScaled( SpriteBatch batch, TextureRegion tr, float x, float y, float width, float height, float alpha )
        {
            batch.setColor( 1.0f, 1.0f, 1.0f, alpha );
            batch.draw( tr, x, y, width, height );
        }

        /*****************************************************************************
        *   Draws an image at the specified location with a specified anchor
        *   and scales it to the given destiny dimensions.
        *
        *   @param  img         The image to draw.
        *   @param  x           Drawing position x.
        *   @param  y           Drawing position y.
        *   @param  ank         The anchor for this drawing operation.
        *****************************************************************************/
        public static final void drawImageScaledClipped( SpriteBatch batch, TextureRegion img, float x, float y, LibAnchor ank, float clipX, float clipY, float clipWidth, float clipHeight, float alpha )
        {
            //alter x and y according to ank
            switch ( ank )
            {
                case ELeftTop:       {   x -= 0;                                    y -= 0;                                   break;  }
                case ECenterTop:     {   x -= img.getTexture().getWidth() / 2;      y -= 0;                                   break;  }
                case ERightTop:      {   x -= img.getTexture().getWidth();          y -= 0;                                   break;  }
                case ELeftMiddle:    {   x -= 0;                                    y -= img.getTexture().getHeight() / 2;    break;  }
                case ECenterMiddle:  {   x -= img.getTexture().getWidth() / 2;      y -= img.getTexture().getHeight() / 2;    break;  }
                case ERightMiddle:   {   x -= img.getTexture().getWidth();          y -= img.getTexture().getHeight() / 2;    break;  }
                case ELeftBottom:    {   x -= 0;                                    y -= img.getTexture().getHeight();        break;  }
                case ECenterBottom:  {   x -= img.getTexture().getWidth() / 2;      y -= img.getTexture().getHeight();        break;  }
                case ERightBottom:   {   x -= img.getTexture().getWidth();          y -= img.getTexture().getHeight();        break;  }
            }

            //draw with src and dst rect
            batch.setColor( 1.0f, 1.0f, 1.0f, alpha );
            batch.draw( img.getTexture(), x, y, clipWidth, clipHeight, (int)clipX, (int)clipY, (int)clipWidth, (int)clipHeight, false, true );
        }

        /*****************************************************************************
        *   Draws a sprite-image at the specified location with a specified anchor.
        *
        *   @param  img     The image to draw.
        *   @param  x       Drawing position x.
        *   @param  y       Drawing position y.
        *   @param  ank     The anchor for this drawing operation.
        *****************************************************************************/
        public static final void drawSprite( SpriteBatch batch, TextureRegion img, float x, float y, LibAnchor ank, int frameCountX, int frameCountY, int frame, float alpha )
        {
            int frameWidth  = img.getTexture().getWidth()  / frameCountX;
            int frameHeight = img.getTexture().getHeight() / frameCountY;

            //alter x and y according to ank
            switch ( ank )
            {
                case ELeftTop:       {   x -= 0;                  y -= 0;                           break;  }
                case ECenterTop:     {   x -= frameWidth / 2;     y -= 0;                           break;  }
                case ERightTop:      {   x -= frameWidth;         y -= 0;                           break;  }
                case ELeftMiddle:    {   x -= 0;                  y -= img.getTexture().getHeight() / 2;         break;  }
                case ECenterMiddle:  {   x -= frameWidth / 2;     y -= img.getTexture().getHeight() / 2;         break;  }
                case ERightMiddle:   {   x -= frameWidth;         y -= img.getTexture().getHeight() / 2;         break;  }
                case ELeftBottom:    {   x -= 0;                  y -= img.getTexture().getHeight();             break;  }
                case ECenterBottom:  {   x -= frameWidth / 2;     y -= img.getTexture().getHeight();             break;  }
                case ERightBottom:   {   x -= frameWidth;         y -= img.getTexture().getHeight();             break;  }
            }

            //draw frame
            LibDrawing.drawImageScaledClipped( batch, img, x, y, LibAnchor.ELeftTop, frameWidth * ( frame % frameCountX ), frameHeight * ( frame / frameCountX ) , frameWidth, frameHeight, alpha );

            //draw frame number
            //Drawing.drawTextOutlined( "[" + frame + "]", x, y, "#000000", "#ffffff", "20px Verdana", Anchor.LEFT_TOP );
        }

        public static final void drawString( SpriteBatch batch, BitmapFont bf, String str, float x, float y, float width, HAlignment hAnk, float scale, BitmapFont shadow )
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
    }
