/*  $Id: JARVersion.java 132 2013-09-12 07:50:01Z jenetic.bytemare@googlemail.com $
 *  =======================================================================================
 */
    package de.christopherstock.jumpandrun;

    import  de.christopherstock.jumpandrun.JARSettings.*;

    /**************************************************************************************
    *   The version history system.
    *
    *   @author     Christopher Stock
    *   @version    0.0.5
    **************************************************************************************/
    enum JARVersion
    {
        V_0_0_5(    "0.0.5",    "12.09.2013, 09:04:02", "BASE",     "New level object: Filled blocks. New level object: Stairs. Elevated grass with toppings. Unified image resource names. Different sounds for items and enemy-kills. Moved elevation-information to JARImage. Invisible (secret) items. Correct getWidth and getHeight for images. Really nice counter presentation and upcounting on picking items. Item transfer animation to counter-corner after being picked. Separate animateFrame() from animateSwing(). Improved all for loops. Moved block swing animation from draw() to animate(). Pruned WallType. Fixed getRandom-function. Draw items on fixed position on HUD layer after being picked. Dismissed: Only update the camera when the player is moving left or right? Check DKC. Fade in pause text according to overlay fade factor. Read and save files. Buffered vertical camera turns. Buffered horizontal camera turns. Separated camera functions. Improved renaming for all resources. Multiple foreground layers ( before and behind player ). Implemented smooth vertical camera centering. Encapsulate screen overlays to class. Fading for pause overlay. Solved images-dimension-power-of-two - limitation. Dismissed: Different vertical screen center on falling. Improve vertical camera handling - correct camera on falling. Correct cases and resourcename for all enum constants. Trigger collision on colliding with level bounds. JARPlayerType To enum. JARItemType to enum. Alpha for images. Draw scaled images. Alpha for drawing operations. Implement pause key. Non-colliding foreground arc and railing for bridge. Fix enemy direction-change-flickering on moving above them. Dismissed: Create wall with custom ( dynamic created ) sprite. Create animated walls. Moved frame-information from JARSprite to JARImage. Improved floor: transparent, non-colliding grass. Params for objects ( Bridge width etc. ). Same deco objects may be in front of or behind the player. Drawing layer for walls ( before and behind player ). Create non-colliding walls ( deco objects ). Counters that slide from left. Enabled startup y. Debug lines that illustrate screen center. Improved/smoothened camera flow by setting different camera targets according to current view direction. Create bundled wall-objects ( e.g. bridge, long floor etc. ). Connected elevation to wallType! Created enum 'WallType' with JARImage as field. System for different levels. Change levels with numeric keys. Fix auto-climb on passable ramps ( ascending, right ) !? Create passable ramps. Fix jumping against elevated ramps from bottom. Fix walking in both directions on elevated ramps. Draw debug triangles for elevated ramps. Improved debug colors. Setup own colors. Fixed screen icon. Jumping needs key release. Fixed ignoreTillNextKeyRelease. Non-colliding objects." ),
        V_0_0_4(    "0.0.4",    "27.08.2013, 16:37:42", "BASE",     "Fixed climb-up-error on walking into pass-through-blocks. Fixed passthrough bug on jumping through walls. Displaying nearest y below and above each block. Debug out heights. Font system. Walls that allow pass-through-jumping from bottom. Unified and pruned all classes. Fixed landing on enemy from edge not killing them. Enriched version system." ),
        V_0_0_3(    "0.0.3",    "16.08.2013, 01:41:01", "BASE",     "Encapsulated JARBlock. Extracted interface 'game object'. Checked elevated blocks. Prune strategy code and resources. Animated Sprites. Fixed counter. Fixed sprites. Pruned messy Fresslemania template code! Fixed: block for swining items must swing too! Pruned all js-occurences of 'this.'. 'i' prefix for all instance fields. 'a' prefix for all constructor params. Implemented sounds. Pruned all compilation warnings. Restructured packages. Corrected collision rects for all elements. Exit app via esc key. implement Block.changeSprite() sprite system improved key-system - one object per key unify Item-class with Block-class for framed and swinging blocks! separated HUD counters" ),
        V_0_0_2(    "0.0.2",    "26.06.2013, 23:31:11", "BASE",     "Completed porting JavaScript code. Adjustment in progress." ),
        V_0_0_1(    "0.0.1",    "28.04.2013, 22:24:23", "BASE",     "New LibGdx-Version 0.9.8. Setting up project." ),

        ;

        /** The version string of the client version. */
        private         String              iClientVersionNumber        = null;
        /** The integer version code for the Android Manifest. */
        @SuppressWarnings( "unused" )
        private         int                 iVersionCode                = 0;
        /** The version string of the backend version. */
        private         String              iBackendVersionNumber       = null;
        /** The completion time of this version. */
        private         String              iReleaseDate                = null;
        /** The internal codename of this version. */
        private         String              iCodeName                   = null;
        /** The log for this version contains latest changes. */
        private         String              iLog                        = null;

        /******************************************************************************************
        *   Creates one app version enum constant.
        *
        *   @param  aClientVersionNumber    The version string for this client.
        *   @param  aReleaseDate            The last compilation time of this version.
        *   @param  aCodename               The internal codename for this version.
        *   @param  aLog                    A log-text with latest changes for this version.
        ******************************************************************************************/
        private JARVersion( String aClientVersionNumber, String aReleaseDate, String aCodename, String aLog )
        {
            iClientVersionNumber    = aClientVersionNumber;
            iReleaseDate            = aReleaseDate;
            iCodeName               = aCodename;
            iLog                    = aLog;
        }

        /******************************************************************************************
        *   Returns the release date.
        *
        *   @return     The last release date for this version.
        ******************************************************************************************/
        public final String getCompileTime()
        {
            return iReleaseDate;
        }

        /******************************************************************************************
        *   Returns the version string for the client version.
        *
        *   @return     The version string for the client.
        ******************************************************************************************/
        public final String getClientVersionNumber()
        {
            return iClientVersionNumber;
        }

        /******************************************************************************************
        *   Returns the version string for the backend version.
        *
        *   @return     The version string for the backend.
        ******************************************************************************************/
        public final String getBackendVersionNumber()
        {
            return iBackendVersionNumber;
        }

        /******************************************************************************************
        *   Shows the current version number.
        *
        *   @return     The version-number of the latest version.
        ******************************************************************************************/
        public static final String getCurrentVersionDescription()
        {
            String ret = ( General.PROJECT_NAME + ", Version [" + values()[ 0 ].iClientVersionNumber + "] codename [" + values()[ 0 ].iCodeName + "] released on [" + values()[ 0 ].iReleaseDate + "]" );
            return ret;
        }

        /******************************************************************************************
        *   Returns a list of the project history.
        *
        *   @return     Returns a list of all versions with all version-numbers, codenames,
        *               release-dates and the according log.
        ******************************************************************************************/
        public static final String getVersionLog()
        {
            StringBuffer ret = new StringBuffer();
            for ( JARVersion version : values() )
            {
                ret.append( version.iClientVersionNumber + " " + version.iCodeName + " " + version.iReleaseDate + " " + version.iLog + "\n" );
            }
            ret.append( "\n" );
            return ret.toString();
        }
    }
