/*  $Id: Lib.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  ==============================================================================================================
 */
    package de.christopherstock.libgdx.lib;

    import  java.util.*;

    /*********************************************************************************
    *   Holds static utility functionality.
    *
    *   @author     $Author: jenetic.bytemare@googlemail.com $
    *   @version    0.0.5
    *   @see        "$URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.5/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/Lib.java $"
    *********************************************************************************/
    public abstract class Lib
    {
        /** The amount of centimeters for one inch. */
        public  static  final   float                   CM_PER_INCH                     = 2.54f;

        /** The amount of milliseconds for one minute. */
        public  static  final   long                    MILLIS_PER_SECOND               = 1000;

        /** The amount of milliseconds for one minute. */
        public  static  final   long                    MILLIS_PER_MINUTE               = 60 * MILLIS_PER_SECOND;

        /** The amount of milliseconds for one hour. */
        public  static  final   long                    MILLIS_PER_HOUR                 = 60 * MILLIS_PER_MINUTE;

        /** The amount of milliseconds for one day. */
        public  static  final   long                    MILLIS_PER_DAY                  = 24 * MILLIS_PER_HOUR;

        /** The amount of milliseconds for one week. */
        public  static  final   long                    MILLIS_PER_WEEK                 = 7 * MILLIS_PER_DAY;

        /** The amount of milliseconds for an avarage month. */
        public  static  final   long                    MILLIS_PER_MONTH                = 30 * MILLIS_PER_DAY;

        /** The amount of milliseconds for an avarage year. */
        public  static  final   long                    MILLIS_PER_YEAR                 = 365 * MILLIS_PER_DAY;

        /** The number of bytes in 1 KB. */
        public  static  final   long                    BYTES_1_KB                      = 1024;

        /** The number of bytes in 256 KB. */
        public  static  final   long                    BYTES_256_KB                    = 256  * BYTES_1_KB;

        /** The number of bytes in 1 MegaByte. */
        public  static  final   long                    BYTES_1_MB                      = 1024 * BYTES_1_KB;

        /** The number of bytes in 1 GigaByte. */
        public  static  final   long                    BYTES_1_GB                      = 1024 * BYTES_1_MB;

        /** The number of bytes in 1 TerraByte. */
        public  static  final   long                    BYTES_1_TB                      = 1024 * BYTES_1_GB;

        /*********************************************************************************
        *   Checks if an object is contained in a stack of objects.
        *
        *   @param  haystack    The stack to check presence of the needle.
        *   @param  needle      The object to search for in the haystack.
        *   @return             <code>true</code> if the needle is contained in the haystack.
        *                       Otherwise <code>false</code>.
        *********************************************************************************/
        public static final boolean containsObject( Object[] haystack, Object needle )
        {
            for ( Object hay : haystack )
            {
                if ( hay == needle ) return true;
            }

            return false;
        }

        /*********************************************************************************
        *   Checks if a String is contained in a stack of Strings.
        *
        *   @param  haystack    The stack to check presence of the needle.
        *   @param  needle      The String to search for in the haystack.
        *   @return             <code>true</code> if the needle is contained in the haystack.
        *                       Otherwise <code>false</code>.
        *********************************************************************************/
        public static final boolean containsString( String[] haystack, String needle )
        {
            for ( String hay : haystack )
            {
                if ( hay.equals( needle ) ) return true;
            }

            return false;
        }

        /*********************************************************************************
        *   Checks if one of many Strings is contained in a List of Strings.
        *
        *   @param  haystack    The stack to check presence of any needle.
        *   @param  needles     The Strings to search in the haystack.
        *   @return             <code>true</code> if any of the specified needles
        *                       is contained in the haystack. Otherwise <code>false</code>.
        *********************************************************************************/
        public static final boolean containsStrings( List<String> haystack, String[] needles )
        {
            for ( String needle : needles )
            {
                if ( !haystack.contains( needle ) )
                {
                    return false;
                }
            }

            return true;
        }

        /*********************************************************************************
        *   Checks if an int is contained in a stack of ints.
        *
        *   @param  haystack    The stack to check presence of the needle.
        *   @param  needle      The int to search for in the haystack.
        *   @return             <code>true</code> if the needle is contained in the haystack.
        *                       Otherwise <code>false</code>.
        *********************************************************************************/
        public static final boolean contains( int[] haystack, int needle )
        {
            for ( int hay : haystack )
            {
                if ( hay == needle ) return true;
            }

            return false;
        }

        /*********************************************************************************
        *   Delays the current Thread for the specified amount of milliseconds.
        *
        *   @param  millis  The number of milliseconds to delay the current Thread for.
        *********************************************************************************/
        public static final void sleepCurrentThread( long millis )
        {
            try
            {
                Thread.sleep( millis );
            }
            catch ( InterruptedException ie ) {}
        }

        /*********************************************************************************
        *   Checks if all elements of the given subset are contained in the specified superset.
        *
        *   @param  subset      The subset collection.
        *   @param  superset    The superset collection.
        *   @return             <code>true</code> if all elements of the subset are contained
        *                       in the superset. Otherwise <code>false</code>.
        *********************************************************************************/
        public static final boolean isSubsetOf( Collection<String> subset, Collection<String> superset )
        {
            for ( String string : subset )
            {
                if ( !superset.contains( string ) )
                {
                    return false;
                }
            }
            return true;
        }
    }
