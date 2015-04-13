/*  $Id: LibDrawing.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.libgdx.lib.ui;

    import  com.badlogic.gdx.graphics.*;
    import  com.badlogic.gdx.graphics.g2d.*;
    import  com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
    import  com.badlogic.gdx.graphics.glutils.*;
    import  com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;
    import  com.badlogic.gdx.math.*;
    import  de.christopherstock.jumpandrun.ui.*;

    /**************************************************************************************
    *   Offers arithmetic functionality.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    **************************************************************************************/
    public final class LibDrawing
    {
        public static final void drawImage( SpriteBatch batch, TextureRegion tr, float x, float y )
        {
            batch.draw( tr, x, y );
        }

        public static final void fillRect( SpriteBatch batch, float x, float y, float width, float height, Color col )
        {
            //paws current sprite batch
            batch.end();

            //render the filled rect
            ShapeRenderer shapeRenderer = new ShapeRenderer();

            shapeRenderer.setProjectionMatrix(  batch.getProjectionMatrix() );
            shapeRenderer.begin(                ShapeType.FilledRectangle   );
            shapeRenderer.setColor(             col                         );
            shapeRenderer.filledRect(           x, y, width, height         );
            shapeRenderer.end();

            //resume current sprite batch
            batch.begin();
        }

        public static final void fillLine( SpriteBatch batch, float offX, float offY, Vector2 p1, Vector2 p2, Color col )
        {
            //paws current sprite batch
            batch.end();

            //render the filled rect
            ShapeRenderer shapeRenderer = new ShapeRenderer();

            shapeRenderer.setProjectionMatrix(  batch.getProjectionMatrix() );
            shapeRenderer.begin(                ShapeType.Line              );
            shapeRenderer.setColor(             col                         );
            shapeRenderer.line(                 offX + p1.x, offY - p1.y, offX + p2.x, offY - p2.y      );   //inverse height for drawing downwards
            shapeRenderer.end();

            //resume current sprite batch
            batch.begin();
        }

        /*****************************************************************************
        *   Fills all pixels of the canvas with the given fill color.
        *
        *   @param  col     A fill color.
        *****************************************************************************/
        public static final void fillCanvas( SpriteBatch batch, Color col )
        {
            fillRect( batch, 0, 0, JARScreen.width(), JARScreen.height(), col );
        }

        /*****************************************************************************
        *   Draws an image at the specified location with a specified anchor.
        *
        *   @param  img     The image to draw.
        *   @param  x       Drawing position x.
        *   @param  y       Drawing position y.
        *   @param  ank     The anchor for this drawing operation.
        *****************************************************************************/
        public static final void drawSprite( SpriteBatch batch, TextureRegion img, float x, float y, LibAnchor ank, int frameCountX, int frameCountY, int frame )
        {
            int frameWidth  = img.getTexture().getWidth()  / frameCountX;
            int frameHeight = img.getTexture().getHeight() / frameCountY;

            //alter x and y according to ank
            switch ( ank )
            {
                case LEFT_TOP:       {   x -= 0;                  y -= 0;                           break;  }
                case CENTER_TOP:     {   x -= frameWidth / 2;     y -= 0;                           break;  }
                case RIGHT_TOP:      {   x -= frameWidth;         y -= 0;                           break;  }
                case LEFT_MIDDLE:    {   x -= 0;                  y -= img.getTexture().getHeight() / 2;         break;  }
                case CENTER_MIDDLE:  {   x -= frameWidth / 2;     y -= img.getTexture().getHeight() / 2;         break;  }
                case RIGHT_MIDDLE:   {   x -= frameWidth;         y -= img.getTexture().getHeight() / 2;         break;  }
                case LEFT_BOTTOM:    {   x -= 0;                  y -= img.getTexture().getHeight();             break;  }
                case CENTER_BOTTOM:  {   x -= frameWidth / 2;     y -= img.getTexture().getHeight();             break;  }
                case RIGHT_BOTTOM:   {   x -= frameWidth;         y -= img.getTexture().getHeight();             break;  }
            }

            //draw frame
            LibDrawing.drawImageScaledClipped( batch, img, x, y, LibAnchor.LEFT_TOP, frameWidth * ( frame % frameCountX ), frameHeight * ( frame / frameCountX ) , frameWidth, frameHeight );

            //draw frame number
            //Drawing.drawTextOutlined( "[" + frame + "]", x, y, "#000000", "#ffffff", "20px Verdana", Anchor.LEFT_TOP );
        }

        /*****************************************************************************
        *   Draws an image at the specified location with a specified anchor.
        *
        *   @param  img     The image to draw.
        *   @param  x       Drawing position x.
        *   @param  y       Drawing position y.
        *   @param  ank     The anchor for this drawing operation.
        *****************************************************************************/
        public static final void drawImage( SpriteBatch batch, TextureRegion img, float x, float y, LibAnchor ank )
        {
            LibDrawing.drawImageScaledClipped( batch, img, x, y, ank, 0, 0, img.getTexture().getWidth(), img.getTexture().getHeight() );
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
        public static final void drawImageScaledClipped( SpriteBatch batch, TextureRegion img, float x, float y, LibAnchor ank, float clipX, float clipY, float clipWidth, float clipHeight )
        {
            //alter x and y according to ank
            switch ( ank )
            {
                case LEFT_TOP:       {   x -= 0;                                    y -= 0;                                   break;  }
                case CENTER_TOP:     {   x -= img.getTexture().getWidth() / 2;      y -= 0;                                   break;  }
                case RIGHT_TOP:      {   x -= img.getTexture().getWidth();          y -= 0;                                   break;  }
                case LEFT_MIDDLE:    {   x -= 0;                                    y -= img.getTexture().getHeight() / 2;    break;  }
                case CENTER_MIDDLE:  {   x -= img.getTexture().getWidth() / 2;      y -= img.getTexture().getHeight() / 2;    break;  }
                case RIGHT_MIDDLE:   {   x -= img.getTexture().getWidth();          y -= img.getTexture().getHeight() / 2;    break;  }
                case LEFT_BOTTOM:    {   x -= 0;                                    y -= img.getTexture().getHeight();        break;  }
                case CENTER_BOTTOM:  {   x -= img.getTexture().getWidth() / 2;      y -= img.getTexture().getHeight();        break;  }
                case RIGHT_BOTTOM:   {   x -= img.getTexture().getWidth();          y -= img.getTexture().getHeight();        break;  }
            }

            //draw with src and dst rect
            batch.draw( img.getTexture(), x, y, clipWidth, clipHeight, (int)clipX, (int)clipY, (int)clipWidth, (int)clipHeight, false, true );
        }

        public static final void drawString( SpriteBatch batch, BitmapFont bf, String str, float x, float y, float width, HAlignment hAnk, float scale )
        {
            //scale
            bf.setScale( scale );

            //draw wrapped
            bf.drawWrapped( batch, str, x, y, width, hAnk );
        }

        public static final Color colorFromARGB( int argb )
        {
            Color ret = new Color();
            Color.rgb888ToColor( ret, argb );

            return ret;
        }
    }
