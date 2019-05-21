package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.shared.core.types.Color;

public class RectangleExample extends BaseExample implements Example {

	public RectangleExample(String title) {
		super(title);
	}
	
	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		for (int i = 0; i < 30; i++) {  
            final int strokeWidth = 1;  
            final Rectangle rectangle = new Rectangle(Math.random() * 220, Math.random() * 160);  
            rectangle.setX(Util.generateValueWithinBoundary(width, 0)).setY(Util.generateValueWithinBoundary(height, 0))  
                    .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillColor(Color.getRandomHexColor()).setDraggable(true);  
            layer.add(rectangle);  
        }  
		
	}

}
