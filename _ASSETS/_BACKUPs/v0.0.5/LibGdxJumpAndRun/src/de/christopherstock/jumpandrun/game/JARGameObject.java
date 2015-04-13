/*  $Id: JARGameObject.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.ui.*;

    /*****************************************************************************
    *   Represents one obstacle block the level consists of.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARGameObject.java $
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
            iGameObjectType   = aType;
            iSprite = aSprite;
        }
    }
