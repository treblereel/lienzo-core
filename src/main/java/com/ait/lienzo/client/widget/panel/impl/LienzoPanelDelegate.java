package com.ait.lienzo.client.widget.panel.impl;

import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Viewport;
import com.ait.lienzo.client.widget.panel.LienzoPanel;
import com.google.gwt.dom.client.Style;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLDivElement;

import static com.ait.lienzo.client.widget.panel.util.LienzoPanelUtils.getParentFitSize;
import static elemental2.dom.DomGlobal.window;

public abstract class LienzoPanelDelegate<T extends LienzoPanelDelegate> extends LienzoPanel<T> {

    protected abstract LienzoPanel getPanel();

    @Override
    public T add(Layer layer) {
        getPanel().add(layer);
        return cast();
    }

    @Override
    public T setBackgroundLayer(Layer layer) {
        getPanel().setBackgroundLayer(layer);
        return cast();
    }

    @Override
    public T setCursor(Style.Cursor cursor) {
        getPanel().setCursor(cursor);
        return cast();
    }

    @Override
    public int getWidePx() {
        return getPanel().getWidePx();
    }

    @Override
    public int getHighPx() {
        return getPanel().getHighPx();
    }

    @Override
    public Viewport getViewport() {
        return getPanel().getViewport();
    }

    @Override
    public HTMLDivElement getElement() {
        return getPanel().getElement();
    }

    @Override
    public void destroy() {
        getPanel().destroy();
    }

    @SuppressWarnings("unchecked")
    private T cast() {
        return (T) this;
    }

}
