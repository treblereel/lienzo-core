package com.ait.lienzo.tools.client.event;

public enum EventType
{

    CLICKED("click", 4),
    DOUBLE_CLICKED("doubleclick", 4),


    MOUSE_UP("mouseup", 2),
    MOUSE_MOVE("mousemove", 3),
    MOUSE_DOWN("mousedown", 1),


    MOUSE_OUT("mouseout", 5),
    MOUSE_OVER("mouseover", 6),

    MOUSE_WHEEL("mousewheel", 7),


    TOUCH_CANCEL("touchcancel", 8),

    TOUCH_START("touchstart", 9),
    TOUCH_END("touchend", 10),
    TOUCH_MOVE("touchmove", 11),


//    GESTURE_START("gesturestart", 11),
//    GESTURE_UPDATE("gestureupdate", 11),
//    GESTURE_END("gestureend", 11),


    XXXX("mousewheel", 20);

    private String  type;
    private int code;

    EventType(final String type, final int code)
    {
        this.type = type;
        this.code = code;
    }

    public String getType()
    {
        return this.type;
    }
}
