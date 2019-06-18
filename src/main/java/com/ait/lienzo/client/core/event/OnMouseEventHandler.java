package com.ait.lienzo.client.core.event;

import elemental2.dom.Event;
import elemental2.dom.EventListener;
import elemental2.dom.MouseEvent;
import elemental2.dom.UIEvent;

public interface OnMouseEventHandler
{
    public boolean  onMouseEventBefore(MouseEvent event);

    public void  onMouseEventAfter(MouseEvent event);
}
