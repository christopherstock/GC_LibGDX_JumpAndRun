/*  $Id: LibDrawingOld.java 172 2013-09-25 06:50:05Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.libgdx.lib.ui;

    import  com.badlogic.gdx.graphics.g2d.*;

    /**************************************************************************************
    *   Contains deprecated drawing functionality.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    **************************************************************************************/
    @Deprecated
    public final class LibDrawingOld
    {
        /*****************************************************************************
        *   Draws an image at the specified location with a specified anchor.
        *
        *   @param  img     The image to draw.
        *   @param  x       Drawing position x.
        *   @param  y       Drawing position y.
        *   @param  ank     The anchor for this drawing operation.
        *****************************************************************************/
        @Deprecated
        public static final void drawImage( SpriteBatch batch, TextureRegion img, float x, float y, LibAnchor ank, float alpha )
        {
            //alter x and y according to ank
            x += ank.getOffsetX( img.getTexture().getWidth()  );
            y += ank.getOffsetY( img.getTexture().getHeight() );

            //draw image
            batch.setColor( 1.0f, 1.0f, 1.0f, alpha );
            batch.draw( img, x, y );
        }

        /*****************************************************************************
        *   Draws an image at the specified location with a specified anchor.
        *
        *   @param  img     The image to draw.
        *   @param  x       Drawing position x.
        *   @param  y       Drawing position y.
        *   @param  ank     The anchor for this drawing operation.
        *****************************************************************************/
        @Deprecated
        public static final void drawImageScaled( SpriteBatch batch, TextureRegion img, float x, float y, LibAnchor ank, float alpha, float scaleX, float scaleY )
        {
            //calc scaled image dimensions
            float imgWidth  = img.getTexture().getWidth()  * scaleX;
            float imgHeight = img.getTexture().getHeight() * scaleY;

            //alter x and y according to ank
            x += ank.getOffsetX( imgWidth  );
            y += ank.getOffsetY( imgHeight );

            //draw image
            batch.setColor( 1.0f, 1.0f, 1.0f, alpha );
            batch.draw( img.getTexture(), x, y, imgWidth, imgHeight );
        }

        /*****************************************************************************
        *   Draws an image at the specified location with a specified anchor.
        *
        *   @param  img     The image to draw.
        *   @param  x       Drawing position x.
        *   @param  y       Drawing position y.
        *   @param  ank     The anchor for this drawing operation.
        *****************************************************************************/
        @Deprecated
        public static final void drawImageScaled( SpriteBatch batch, TextureRegion img, float x, float y, float width, float height, LibAnchor ank, float alpha )
        {
            //alter x and y according to ank
            x += ank.getOffsetX( width  );
            y += ank.getOffsetY( height );

            //draw image
            batch.setColor( 1.0f, 1.0f, 1.0f, alpha );
            batch.draw( img.getTexture(), x, y, width, height );
        }

        /*****************************************************************************
        *   Draws a sprite-image at the specified location with a specified anchor.
        *
        *   @param  img     The image to draw.
        *   @param  x       Drawing position x.
        *   @param  y       Drawing position y.
        *   @param  ank     The anchor for this drawing operation.
        *****************************************************************************/
        @Deprecated
        public static final void drawSprite( SpriteBatch batch, TextureRegion img, float x, float y, LibAnchor ank, int frameCountX, int frameWidth, int frameHeight, int frameIndex, float alpha, boolean flipX )
        {
            //alter x and y according to ank
            x += ank.getOffsetX( frameWidth  );
            y += ank.getOffsetY( frameHeight );

            //get offset for image clip to pick
            int clipX = frameWidth  * ( frameIndex % frameCountX );
            int clipY = frameHeight * ( frameIndex / frameCountX );

            //draw frame with src and dst rect
            batch.setColor( 1.0f, 1.0f, 1.0f, alpha );
            batch.draw( img.getTexture(), x, y, frameWidth, frameHeight, clipX, clipY, frameWidth, frameHeight, flipX, true );

            //draw frame number
            //Drawing.drawTextOutlined( "[" + frame + "]", x, y, "#000000", "#ffffff", "20px Verdana", Anchor.LEFT_TOP );
        }
    }
