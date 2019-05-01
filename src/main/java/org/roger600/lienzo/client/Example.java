package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Viewport;
import com.ait.lienzo.client.widget.LienzoPanel2;

public interface Example
{
    public String getTitle();

    public void init(LienzoPanel2 panel);

    public void run();

    public void destroy();

    public void onResize();

}
