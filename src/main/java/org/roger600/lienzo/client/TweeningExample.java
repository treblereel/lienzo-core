package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.animation.AnimationProperties;
import com.ait.lienzo.client.core.animation.AnimationProperty.Properties;
import com.ait.lienzo.client.core.animation.AnimationTweener;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.tools.client.Console;

public class TweeningExample extends BaseExample implements Example
{
    public TweeningExample(final String title)
    {
        super(title);
    }

    @Override
    public void run()
    {
        MultiPath path1 = new MultiPath().rect(0, 0, 200, 200)
                                         .setStrokeColor( "#FFFFFF" ).setFillColor( "#0000CC" ).setDraggable(true);
        layer.add(path1);
        layer.draw();

        AnimationProperties props = new AnimationProperties();
        props.push(Properties.X(200));
        props.push(Properties.Y(200));
        props.push(Properties.SCALE(2));

        path1.animate(AnimationTweener.LINEAR, props, 5000);


        Console.get().info("hello 2");
    }
}
