package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.shared.core.types.Color;

public class DragCirclesExample extends BaseExample implements Example
{
    private Circle[] circles;
    int total = 1000;

    public DragCirclesExample(final String title)
    {
        super(title);
    }

    @Override
    public void run()
    {
        this.circles = new Circle[total];

        for (int i = 0; i < total; i++) {

            final Circle circle = new Circle(10);
            circles[i] = circle;
            circle.setX(Util.generateValueWithinBoundary(width, 10)).setY(Util.generateValueWithinBoundary(height, 10))
                  .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(2).setFillColor(Color.getRandomHexColor()).setDraggable(true);
            layer.add(circle);

        }
    }

    @Override public void onResize()
    {
        super.onResize();

        for (int i = 0; i < total; i++) {

            final Circle circle = circles[i];
            circle.setX(Util.generateValueWithinBoundary(width, 10)).setY(Util.generateValueWithinBoundary(height, 10));
        }

        layer.batch();
    }
}
