/*  $Id: JARGameObject.java 172 2013-09-25 06:50:05Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game.object;

    import  de.christopherstock.jumpandrun.game.*;
    import  de.christopherstock.jumpandrun.io.*;
    import  de.christopherstock.jumpandrun.ui.*;

    /*****************************************************************************
    *   Represents one obstacle block the level consists of.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/trunk/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/object/JARGameObject.java $
    *****************************************************************************/
    public class JARGameObject
    {
        public static enum GameObjectType
        {
            EPlayer,
            EEnemy,
            EItem,
            EWall,
            ;
        }

        public                  GameObjectType              iGameObjectType         = null;
        public                  JARSprite                   iSprite                 = null;
        public                  JARBlock                    iBlock                  = null;

        public JARGameObject( GameObjectType aType, JARSprite aSprite )
        {
            iGameObjectType = aType;
            iSprite         = aSprite;
        }

        public void setNewSprite( JARImage newImage )
        {
            //only change if sprite is a different one
            if ( iSprite.getImage() != newImage )
            {
                iSprite = new JARSprite( newImage );
            }
        }
    }
