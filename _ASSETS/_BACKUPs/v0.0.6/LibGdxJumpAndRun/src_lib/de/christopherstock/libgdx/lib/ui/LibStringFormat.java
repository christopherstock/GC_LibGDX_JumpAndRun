/*  $Id: LibStringFormat.java 173 2013-09-25 06:53:34Z jenetic.bytemare@googlemail.com $
 *  ==============================================================================================================
 */
    package de.christopherstock.libgdx.lib.ui;

    import  java.text.*;
    import  java.util.*;

    /*******************************************************************************************************
    *   Formats strings and date objects.
    *
    *   @author     $Author: jenetic.bytemare@googlemail.com $
    *   @version    0.0.6
    *   @see        "$URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.6/LibGdxJumpAndRun/src_lib/de/christopherstock/libgdx/lib/ui/LibStringFormat.java $"
    ********************************************************************************************************/
    public class LibStringFormat
    {
        /** The singleton instance of this class. */
        private     static          LibStringFormat     singleton                       = null;

        /** The String format to format a number. */
        private                     NumberFormat        formatNumber                    = null;

        /** The String format to format a decimal. */
        private                     DecimalFormat       formatDecimal                   = null;

        /** The String format to format a percent value. */
        private                     NumberFormat        formatPercent                   = null;

        /** The short String format to format a date and time. */
        private                     DateFormat          formatDateTimeShort             = null;

        /** The medium String format to format a date and time. */
        private                     DateFormat          formatDateTimeMedium            = null;

        /** The String format to format a time. */
        private                     DateFormat          formatTimeMedium                 = null;

        /** The String format to format a date. */
        private                     DateFormat          formatDateMedium                = null;

        /*******************************************************************************************************
        *   Creates a new instance of the string formatter.
        ********************************************************************************************************/
        private LibStringFormat()
        {
            formatNumber            = NumberFormat.getNumberInstance(                                           Locale.getDefault() );
            formatPercent           = NumberFormat.getPercentInstance(                                          Locale.getDefault() );
            formatDecimal           = new DecimalFormat( "0.0", new DecimalFormatSymbols(                       Locale.getDefault() ) );
            formatDateTimeShort     = DateFormat.getDateTimeInstance(   DateFormat.MEDIUM, DateFormat.SHORT,    Locale.getDefault() );
            formatDateTimeMedium    = DateFormat.getDateTimeInstance(   DateFormat.MEDIUM, DateFormat.MEDIUM,   Locale.getDefault() );
            formatDateMedium        = DateFormat.getDateInstance(       DateFormat.MEDIUM,                      Locale.getDefault() );
            formatTimeMedium        = DateFormat.getTimeInstance(       DateFormat.MEDIUM,                      Locale.getDefault() );
        }

        /*******************************************************************************************************
        *   Returns the singleton instance of this class.
        *
        *   @return     This class' singleton instance.
        ********************************************************************************************************/
        public static final LibStringFormat getSingleton()
        {
            if ( singleton == null ) singleton = new LibStringFormat();
            return singleton;
        }

        /*******************************************************************************************************
        *   Reinitializes the singleton instance. Used to update local formats when the locale has changed.
        ********************************************************************************************************/
        public static final void updateLocale()
        {
            singleton = new LibStringFormat();
        }

        /*********************************************************************************
        *   Formats the given number to a readable string. e.g. 9483455.12 to 9.483.455,12.
        *
        *   @param      value   The number to format as a readable string.
        *   @return             The formatted value as a number-string.
        *********************************************************************************/
        public final String formatNumber( float value )
        {
            return formatNumber.format( value );
        }

        /*********************************************************************************
        *   Formats the given number to a readable string. e.g. 9483455.12 to 9.483.455,12.
        *
        *   @param      value   The number to format as a readable string.
        *   @return             The formatted value as a number-string.
        *********************************************************************************/
        public final String formatDecimal( float value )
        {
            return formatDecimal.format( value );
        }

        /*********************************************************************************
        *   Formats the given number to a percent value. e.g. 0.53f to 53%.
        *
        *   @param      value   The number to format as a percent value.
        *   @return             The formatted value as a percent-string.
        *********************************************************************************/
        public final String formatPercent( float value )
        {
            return formatPercent.format( value );
        }

        /*******************************************************************************************************
        *   Formats the given date to a date- and time-string.
        *
        *   @param  millis  The timestamp to get the date- and time-String from.
        *   @return         The current date and time as a string ( e.g. "19.07.2010 20:11" )
        ********************************************************************************************************/
        public final String formatDateTimeShort( long millis )
        {
            return formatDateTimeShort.format( new Date( millis ) );
        }

        /*******************************************************************************************************
        *   Formats the current date to a date- and time-string.
        *
        *   @return         The current date and time as a string ( e.g. "19.07.2010 20:11:18" )
        ********************************************************************************************************/
        public final String formatDateTimeMedium()
        {
            return formatDateTimeMedium( new Date() );
        }

        /*********************************************************************************
        *   Formats the given number of millis to a readable date and time.
        *
        *   @param      millis  The number of millis to format as a date-time.
        *   @return             The formatted value as a date-time-string ( e.g. "19.07.2010 20:11:18" ).
        *********************************************************************************/
        public final String formatDateTimeMedium( long millis )
        {
            return formatDateTimeMedium( new Date( millis ) );
        }

        /*******************************************************************************************************
        *   Formats the specified date to a date- and time-string.
        *
        *   @param  date    A date to format as a date-time-String.
        *   @return         The date and time as a string ( e.g. "19.07.2010 20:11:18" ).
        ********************************************************************************************************/
        public final String formatDateTimeMedium( Date date )
        {
            return formatDateTimeMedium.format( date );
        }

        /*******************************************************************************************************
        *   Formats the given number of millis to a data-string.
        *
        *   @param  millis  The number of millis passed since the epoche.
        *   @return         The date as a String ( e.g. "28.03.2005" )
        ********************************************************************************************************/
        public final String formatDateMedium( long millis )
        {
            return formatDateMedium( new Date( millis ) );
        }

        /*******************************************************************************************************
        *   Formats a date to a date-string.
        *
        *   @param  date    A date to format as a date-String.
        *   @return         The date as string. ( e.g. "17.08.2010" )
        ********************************************************************************************************/
        public final String formatDateMedium( Date date )
        {
            return formatDateMedium.format( date );
        }

        /*******************************************************************************************************
        *   Formats the given number of millis to a data-string.
        *
        *   @param  millis  The number of millis passed since the epoche.
        *   @return         A String-array with two elements.
        *                   The first element is the formatted date ( e.g. "28.03.2005" ).
        *                   The second element is the formatted time ( e.g. "20:11:18" ).
        ********************************************************************************************************/
        public final String[] formatSplittedDateTimeMedium( long millis )
        {
            Date currentDate = new Date( millis );

            return new String[]
            {
                formatDateMedium.format( currentDate ),
                formatTimeMedium.format( currentDate )
            };
        }
    }
