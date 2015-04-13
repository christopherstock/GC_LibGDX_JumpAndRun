/*  $Id: LibFile.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ==============================================================================================================
 */
    package de.christopherstock.libgdx.lib.io;

    import  com.badlogic.gdx.*;
    import  com.badlogic.gdx.files.*;

    /*********************************************************************************
    *   Handles file writing and reading
    *
    *   @author     $Author: jenetic.bytemare@googlemail.com $
    *   @version    0.0.6
    *   @see        "$URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/io/LibFile.java $"
    *********************************************************************************/
    public abstract class LibFile
    {
        public static final void writeExternalFile( String filename, String content )
        {
            FileHandle fh = Gdx.files.external( filename );
            fh.writeString( content, false, "UTF-8" );
        }

        public static final String readExternalFile( String filename )
        {
            FileHandle fh = Gdx.files.external( filename );
            return fh.readString();
        }
    }
