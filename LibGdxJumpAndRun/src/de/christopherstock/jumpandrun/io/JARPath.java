/*  $Id: JARPath.java 172 2013-09-25 06:50:05Z jenetic.bytemare@googlemail.com $
 *  ================================================================================================
 */
    package de.christopherstock.jumpandrun.io;

    /***********************************************************************************************
    *   The image system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.6
    ***********************************************************************************************/
    public enum JARPath
    {
        EImageBg(       "image/bg"      ),
        EImageDeco(     "image/deco"    ),
        EImageWall(     "image/wall"    ),
        EImageGlobal(   "image/global"  ),
        EImageItem(     "image/item"    ),
        EImagePlayer(   "image/player"  ),
        EImageEnemy(    "image/enemy"   ),
        EImageText(     "image/text"    ),

        ESound(         "sound"         ),

        EFont(          "font"          ),

        ;

        private                     String                  iPath                           = null;

        private JARPath( String aPath )
        {
            iPath       = aPath;
        }

        public final String getPath()
        {
            return iPath;
        }
    }
