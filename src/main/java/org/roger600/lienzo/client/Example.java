package org.roger600.lienzo.client;

import com.ait.lienzo.client.widget.LienzoPanel2;

import elemental2.dom.HTMLDivElement;

public interface Example
{
    public String getTitle();

    public void init(LienzoPanel2 panel, HTMLDivElement topDiv);

    public void run();

    public void destroy();

    public void onResize();

    public int getWidthOffset();
    public int getHeightOffset();

}
