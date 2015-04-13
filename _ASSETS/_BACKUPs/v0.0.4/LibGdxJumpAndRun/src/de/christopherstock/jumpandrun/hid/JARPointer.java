/*  $Id: JARPointer.java 66 2013-08-27 14:42:36Z jenetic.bytemare@googlemail.com $
 *  ============================================================================
 */
    package de.christopherstock.jumpandrun.hid;

    import  com.badlogic.gdx.scenes.scene2d.*;

    /*****************************************************************************
    *   Processes pointer operations.
    *
    *   @author     Christopher Stock
    *   @version    0.0.4
    *   @link       $URL: https://libgdxjumpandrun.googlecode.com/svn/tags/v0.0.4/LibGdxJumpAndRun/src/de/christopherstock/jumpandrun/hid/JARPointer.java $
    *****************************************************************************/
    @Deprecated
    @SuppressWarnings( "all" )
    public class JARPointer
    {
        public static final int         SOURCE_TOUCH                = 0;
        public static final int         SOURCE_MOUSE                = 1;

        public static       int         pointerSource               = 0;
        public static       boolean     mouseDown                   = false;

        public static final void attachListeners()
        {
/*
            //get the pointer source - mouse or touch
            if ( Pointer.isTouchDevice() )
            {
                Debug.hid.log( "Initializing pointer-system for TOUCH devices" );
                //alert("initialized as TOUCH device");
                Pointer.pointerSource = Pointer.SOURCE_TOUCH;
            }
            else
            {
                Debug.hid.log( "Initializing pointer-system for MOUSE devices" );
                Pointer.pointerSource = Pointer.SOURCE_MOUSE;
                //alert("initialized as MOUSE device");
            }

            //suppress all touch events for the document
            document.addEventListener(      "touchstart",       Pointer.suppressEvent,          false   );
            document.addEventListener(      "touchmove",        Pointer.suppressEvent,          false   );
            document.addEventListener(      "touchend",         Pointer.suppressEvent,          false   );
            document.addEventListener(      "gesturestart",     Pointer.suppressEvent,          false   );
            document.addEventListener(      "gesturechange",    Pointer.suppressEvent,          false   );
            document.addEventListener(      "gestureend",       Pointer.suppressEvent,          false   );

            //suppress all mouse events for the document
            document.addEventListener(      "contextmenu",      Pointer.suppressEvent,          false   );
            document.addEventListener(      "mousedown",        Pointer.suppressEvent,          false   );
            document.addEventListener(      "mouseup",          Pointer.suppressEvent,          false   );
            document.addEventListener(      "mousemove",        Pointer.suppressEvent,          false   );
            document.addEventListener(      "oncontextmenu",    Pointer.suppressEvent,          false   );
            document.addEventListener(      "onmousedown",      Pointer.suppressEvent,          false   );
            document.addEventListener(      "onmouseup",        Pointer.suppressEvent,          false   );
            document.addEventListener(      "onmousemove",      Pointer.suppressEvent,          false   );

            //assign listeners according to pointer source
            switch ( Pointer.pointerSource )
            {
                case Pointer.SOURCE_TOUCH:
                {
                    //set touch events for the canvas
                    Canvas.OBJECT.addEventListener( "touchstart",       Pointer.handleTouchStart,       false   );
                    Canvas.OBJECT.addEventListener( "touchmove",        Pointer.handleTouchMove,        false   );
                    Canvas.OBJECT.addEventListener( "touchend",         Pointer.handleTouchEnd,         false   );
                    break;
                }

                case Pointer.SOURCE_MOUSE:
                {
                    //set mouse events for the canvas
                    Canvas.OBJECT.addEventListener( "mousedown",        Pointer.handleMouseDown,        false   );
                    Canvas.OBJECT.addEventListener( "mousemove",        Pointer.handleMouseMove,        false   );
                    Canvas.OBJECT.addEventListener( "mouseup",          Pointer.handleMouseUp,          false   );
                    break;
                }
            }
*/
        }

        public void suppressEvent( Event evt )
        {
/*
            //Debug.hid.log( "default event suppressed" );
            evt.preventDefault();
*/
        }

        public void handleMouseDown( Event evt )
        {
/*
            //always suppress the default event
            evt.preventDefault();

            int pointerX = evt.pageX - Canvas.OBJECT.offsetLeft;
            int pointerY = evt.pageY - Canvas.OBJECT.offsetTop;

            JARDebug.hid.info( "mouse down [" + pointerX + "," + pointerY + "]" );

            //flag mouse as pushed
            Pointer.mouseDown = true;

            //delegate to state system
            State.delegatePointerDown( pointerX, pointerY );
*/
        }

        public void handleMouseUp( Event evt )
        {
/*
            //always suppress the default event
            evt.preventDefault();

            int pointerX = evt.pageX - Canvas.OBJECT.offsetLeft;
            int pointerY = evt.pageY - Canvas.OBJECT.offsetTop;

            JARDebug.hid.info( "mouse up [" + pointerX + "," + pointerY + "]" );

            //flag mouse as released
            Pointer.mouseDown = false;

            //delegate to state system
            State.delegatePointerUp( pointerX, pointerY );
*/
        }

        public void handleMouseMove( Event evt )
        {
/*
            //always suppress the default event
            evt.preventDefault();

            //only delegate if mouse is pushed
          //if ( Pointer.mouseDown )
            {
                int pointerX = evt.pageX - Canvas.OBJECT.offsetLeft;
                int pointerY = evt.pageY - Canvas.OBJECT.offsetTop;

                JARDebug.hid.info( "mouse move [" + pointerX + "," + pointerY + "]" );

                //delegate to state system
                State.delegatePointerMove( pointerX, pointerY );
            }
*/
        }

        public void handleTouchStart( Event evt )
        {
/*
            //always suppress the default event
            evt.preventDefault();

            int touchX = evt.targetTouches[ 0 ].pageX - Canvas.OBJECT.offsetLeft;
            int touchY = evt.targetTouches[ 0 ].pageY - Canvas.OBJECT.offsetTop;

            //alert( "touch start [" + touchX + "," + touchY + "]" );
            //Debug.hid.log( "touch start [" + touchX + "," + touchY + "]" );

            //alert( "checkpoint 0" );

            //delegate to state system
            State.delegatePointerDown( touchX, touchY );
*/
        }

        public void handleTouchMove( Event evt )
        {
/*
            //always suppress the default event
            evt.preventDefault();

            int touchX = evt.targetTouches[ 0 ].pageX - Canvas.OBJECT.offsetLeft;
            int touchY = evt.targetTouches[ 0 ].pageY - Canvas.OBJECT.offsetTop;

            //alert( "touch move[" + touchX + "," + touchY + "]" );
            //Debug.hid.log( "touch move [" + touchX + "," + touchY + "]" );

            //delegate to state system
            State.delegatePointerMove( touchX, touchY );
*/
        }

        public void handleTouchEnd( Event evt )
        {
/*
            //always suppress the default event
            evt.preventDefault();

            int touchX = evt.changedTouches[ 0 ].pageX - Canvas.OBJECT.offsetLeft;
            int touchY = evt.changedTouches[ 0 ].pageY - Canvas.OBJECT.offsetTop;

            //alert( "touch end[" + touchX + "," + touchY + "]" );
            //Debug.hid.log( "touch end [" + touchX + "," + touchY + "]" );

            //delegate to state system
            State.delegatePointerUp( touchX, touchY );
*/
        }

        public boolean isTouchDevice()
        {
            return true;
/*
            try
            {
                //check if a touch event can be created
                document.createEvent( "TouchEvent" );

                return true;
            }
            catch ( Exception e )
            {
                return false;
            }
*/
        }
    }
