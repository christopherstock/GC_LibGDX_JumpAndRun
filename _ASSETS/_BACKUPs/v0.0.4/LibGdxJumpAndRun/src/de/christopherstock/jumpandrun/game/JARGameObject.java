/*  $Id: JARGameObject.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.game;

    import  de.christopherstock.jumpandrun.ui.*;

    /*****************************************************************************
    *   Represents one obstacle block the level consists of.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/game/JARGameObject.java $
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

        public                  GameObjectType              iType                   = null;
        public                  JARSprite                   iSprite                 = null;

        public JARGameObject( GameObjectType aType, JARSprite aSprite )
        {
            iType   = aType;
            iSprite = aSprite;
        }
    }
