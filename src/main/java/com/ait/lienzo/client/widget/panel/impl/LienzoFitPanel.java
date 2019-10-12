package com.ait.lienzo.client.widget.panel.impl;

import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Viewport;
import com.ait.lienzo.client.widget.panel.LienzoPanel;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLDivElement;
import org.gwtproject.dom.client.Style;

import static com.ait.lienzo.client.widget.panel.util.LienzoPanelUtils.getParentFitSize;
import static elemental2.dom.DomGlobal.window;

/**
 * Automatically fits its size to the parent's one.
 */
public class LienzoFitPanel extends LienzoPanel<LienzoFitPanel> {

    private final LienzoPanelImpl panel;
    private final EventListener m_resizeListener;

    public static LienzoFitPanel newPanel(HTMLDivElement element) {
        int[] parentPxSize = getParentFitSize(element);
        return new LienzoFitPanel(LienzoPanelImpl.newPanel(element,
                                                           parentPxSize[0],
                                                           parentPxSize[1]));
    }

    public LienzoFitPanel(LienzoPanelImpl panel) {
        this.panel = panel;
        this.m_resizeListener = (e) ->
        {
            onResize();
        };
        window.addEventListener("resize", m_resizeListener);
    }

    @Override
    public LienzoFitPanel add(Layer layer) {
        panel.add(layer);
        return this;
    }

    @Override
    public LienzoFitPanel setBackgroundLayer(Layer layer) {
        panel.setBackgroundLayer(layer);
        return this;
    }

    public void onResize() {
        int[] pxSize = getFitSize();
        panel.setPixelSize(pxSize[0], pxSize[1]);
    }

    @Override
    public LienzoFitPanel setCursor(Style.Cursor cursor) {
        panel.setCursor(cursor);
        return this;
    }

    @Override
    public int getWidePx() {
        return panel.getWidePx();
    }

    @Override
    public int getHighPx() {
        return panel.getHighPx();
    }

    @Override
    public Viewport getViewport() {
        return panel.getViewport();
    }

    @Override
    public HTMLDivElement getElement() {
        return panel.getElement();
    }

    @Override
    public void destroy() {
        window.removeEventListener("resize", m_resizeListener);
        panel.destroy();
    }

    private int[] getFitSize() {
        return getParentFitSize(panel.getElement());
    }
}
