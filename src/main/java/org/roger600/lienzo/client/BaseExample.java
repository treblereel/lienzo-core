package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.mediator.EventFilter;
import com.ait.lienzo.client.core.mediator.MousePanMediator;
import com.ait.lienzo.client.core.mediator.MouseWheelZoomMediator;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.widget.LienzoPanel2;

public abstract class BaseExample implements Example
{
    private String title;
    protected LienzoPanel2 panel;
    protected Layer        layer;

    protected Console      console;

    public BaseExample(final String title)
    {
        this.title = title;
        console = new Console();
    }

    @Override
    public String getTitle()
    {
        return title;
    }

    @Override
    public void init(final LienzoPanel2 panel)
    {
        this.panel = panel;
        this.layer = new Layer();
        this.panel.add(this.layer);

        MouseWheelZoomMediator zoom = new MouseWheelZoomMediator(EventFilter.SHIFT);
        panel.getViewport().pushMediator(zoom);

        MousePanMediator pan = new MousePanMediator(EventFilter.META);
        this.panel.getViewport().pushMediator(pan);
    }

    @Override
    public void destroy()
    {
        panel.destroy();
    }

    @Override
    public void onResize()
    {

    }
}
