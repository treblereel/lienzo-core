package com.ait.lienzo.client.widget.panel.impl;

import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Viewport;
import com.ait.lienzo.client.widget.panel.LienzoPanel;
import com.google.gwt.dom.client.Style;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLDivElement;

import static com.ait.lienzo.client.widget.panel.util.LienzoPanelUtils.getParentFitSize;
import static elemental2.dom.DomGlobal.window;

/**
 * Automatically fits its size to the parent's one.
 */
public class LienzoFitPanel extends LienzoPanelDelegate<LienzoFitPanel> {

    private final LienzoPanelImpl panel;
    private final EventListener m_resizeListener;

    public static LienzoFitPanel newPanelForParent(HTMLDivElement element) {
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
        // TODO: lienzo-to-native - Adding the event listener for the whole window - may cause issues when multiple live instances running
        window.addEventListener("resize", m_resizeListener);
    }

    public void onResize() {
        int[] pxSize = getFitSize();
        panel.setPixelSize(pxSize[0], pxSize[1]);
    }

    @Override
    protected LienzoPanel getPanel() {
        return panel;
    }

    @Override
    public void destroy() {
        window.removeEventListener("resize", m_resizeListener);
        super.destroy();
    }

    private int[] getFitSize() {
        return getParentFitSize(panel.getElement());
    }
}
