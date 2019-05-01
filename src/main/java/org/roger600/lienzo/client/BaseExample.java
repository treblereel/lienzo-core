package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.mediator.EventFilter;
import com.ait.lienzo.client.core.mediator.MousePanMediator;
import com.ait.lienzo.client.core.mediator.MouseWheelZoomMediator;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.lienzo.client.widget.LienzoPanel2;

public abstract class BaseExample implements Example
{
    private String title;
    protected LienzoPanel2 panel;
    protected Layer        layer;

    protected Console      console;

    protected int width;
    protected int height;

    protected int leftPadding = 5;
    protected int topPadding = 5;
    protected int rightPadding = 5;
    protected int bottomPadding = 5;

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

        width = panel.getWidth();
        height = panel.getHeight();

        MouseWheelZoomMediator zoom = new MouseWheelZoomMediator(EventFilter.SHIFT);
        panel.getViewport().pushMediator(zoom);

        MousePanMediator pan = new MousePanMediator(EventFilter.META);
        this.panel.getViewport().pushMediator(pan);
    }

    public void setRandomLocation(Shape shape)
    {
        Util.setLocation(shape, width, height, leftPadding, topPadding, rightPadding, bottomPadding);
    }

    @Override
    public void destroy()
    {
        panel.destroy();
    }


    @Override
    public void onResize()
    {
        width = panel.getWidth();
        height = panel.getHeight();
    }
}
