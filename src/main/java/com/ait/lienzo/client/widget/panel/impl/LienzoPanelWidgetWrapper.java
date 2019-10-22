package com.ait.lienzo.client.widget.panel.impl;

import com.ait.lienzo.client.widget.panel.LienzoPanel;
import org.gwtproject.user.client.ui.FlowPanel;
import org.gwtproject.user.client.ui.IsWidget;
import org.gwtproject.user.client.ui.Widget;

public class LienzoPanelWidgetWrapper
        extends LienzoPanelDelegate<LienzoPanelWidgetWrapper>
        implements IsWidget  {

    private final LienzoPanel panel;
    private final FlowPanel container;

    public LienzoPanelWidgetWrapper(LienzoPanel panel) {
        this.panel = panel;
        this.container = new FlowPanel();
        this.container.getElement().setInnerHTML(panel.getElement().innerHTML);
    }

    @Override
    protected LienzoPanel getPanel() {
        return panel;
    }

    @Override
    public Widget asWidget() {
        return container;
    }
}
