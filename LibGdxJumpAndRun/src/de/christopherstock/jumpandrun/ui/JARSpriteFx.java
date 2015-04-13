/*  $Id: JARSpriteFx.java 181 2013-09-30 14:19:44Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  de.christopherstock.jumpandrun.JARSettings.Sprites;

    /*****************************************************************************
    *   A sprite is one image that forms several bitmaps from the bitmap data.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JARSpriteFx.java $
    *****************************************************************************/
    public enum JARSpriteFx
    {
        ENone,
        ESkewX1X4Horizontal,
        ESkewX1X4Elevated,
        ;

        public static enum SpriteEffectSkewX1X4Direction
        {
            ESwingLeft,
            ESwingRight,
            ;
        }

        public static final void animate( JARSprite sprite )
        {
            switch ( sprite.getImage().getSpriteFx() )
            {
                case ENone:
                {
                    //do nothing!
                    break;
                }

                case ESkewX1X4Horizontal:
                {
                    switch ( sprite.iSkewX1X4State )
                    {
                        case ESwingLeft:
                        {
                            ++sprite.iSpriteTransTick;

                            sprite.iSpriteTrans.iX1 -= Sprites.SPRITE_SKEW_X1X4_SPEED;
                            sprite.iSpriteTrans.iX4 -= Sprites.SPRITE_SKEW_X1X4_SPEED;

                            if ( sprite.iSpriteTransTick >= Sprites.SPRITE_SKEW_X1X4_MAX )
                            {
                                sprite.iSpriteTransTick = 0;
                                sprite.iSkewX1X4State = SpriteEffectSkewX1X4Direction.ESwingRight;
                            }
                            break;
                        }

                        case ESwingRight:
                        {
                            ++sprite.iSpriteTransTick;

                            sprite.iSpriteTrans.iX1 += Sprites.SPRITE_SKEW_X1X4_SPEED;
                            sprite.iSpriteTrans.iX4 += Sprites.SPRITE_SKEW_X1X4_SPEED;

                            if ( sprite.iSpriteTransTick >= Sprites.SPRITE_SKEW_X1X4_MAX )
                            {
                                sprite.iSpriteTransTick = 0;
                                sprite.iSkewX1X4State = SpriteEffectSkewX1X4Direction.ESwingLeft;
                            }
                            break;
                        }
                    }

                    break;
                }

                case ESkewX1X4Elevated:
                {
                    switch ( sprite.iSkewX1X4State )
                    {
                        case ESwingLeft:
                        {
                            ++sprite.iSpriteTransTick;

                            sprite.iSpriteTrans.iX1 -= Sprites.SPRITE_SKEW_X1X4_SPEED;
                            sprite.iSpriteTrans.iX4 -= Sprites.SPRITE_SKEW_X1X4_SPEED;

                            if ( sprite.iSpriteTransTick >= Sprites.SPRITE_SKEW_X1X4_MAX )
                            {
                                sprite.iSpriteTransTick = 0;
                                sprite.iSkewX1X4State = SpriteEffectSkewX1X4Direction.ESwingRight;
                            }
                            break;
                        }

                        case ESwingRight:
                        {
                            ++sprite.iSpriteTransTick;

                            sprite.iSpriteTrans.iX1 += Sprites.SPRITE_SKEW_X1X4_SPEED;
                            sprite.iSpriteTrans.iX4 += Sprites.SPRITE_SKEW_X1X4_SPEED;

                            if ( sprite.iSpriteTransTick >= Sprites.SPRITE_SKEW_X1X4_MAX )
                            {
                                sprite.iSpriteTransTick = 0;
                                sprite.iSkewX1X4State = SpriteEffectSkewX1X4Direction.ESwingLeft;
                            }
                            break;
                        }
                    }

                    break;
                }
            }
        }
    }
