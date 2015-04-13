/*  $Id: JARCamera.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.ui;

    import  de.christopherstock.jumpandrun.*;

    /*****************************************************************************
    *   Builds the gamestate.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/ui/JARCamera.java $
    *****************************************************************************/
    public class JARCamera
    {
        public static final int     RATIO_CENTERING_X            = 2;
        public static final int     RATIO_CENTERING_Y            = 2;

        public static       JARCamera  current                      = new JARCamera();

        public      float     x = 0;
        public      float     y = 0;

        public JARCamera()
        {
        }

        public static final JARCamera getCurrent()
        {
            //calculate scroll-x-offset so camera is centered to player - clip camera to level bounds
            JARCamera.current.x = JARLevel.current.iPlayer.iBlock.getRect().iLeft - JARScreen.width() / JARCamera.RATIO_CENTERING_X + JARLevel.current.iPlayer.iBlock.getRect().iWidth / 2;
            if ( JARCamera.current.x < 0                                                        ) JARCamera.current.x = 0;
            if ( JARCamera.current.x > JARLevel.current.iLevelBoundX - JARScreen.width()     ) JARCamera.current.x = JARLevel.current.iLevelBoundX - JARScreen.width();

            JARCamera.current.y = JARLevel.current.iPlayer.iBlock.getRect().iTop - JARScreen.height() / JARCamera.RATIO_CENTERING_Y + JARLevel.current.iPlayer.iBlock.getRect().iHeight / 2;
            if ( JARCamera.current.y < 0                                                        ) JARCamera.current.y = 0;
            if ( JARCamera.current.y > JARLevel.current.iLevelBoundY - JARScreen.height()    ) JARCamera.current.y = JARLevel.current.iLevelBoundY - JARScreen.height();



            return JARCamera.current;
        }
    }
