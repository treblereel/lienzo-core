package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Ellipse;
import com.ait.lienzo.shared.core.types.Color;

public class EllipseExample extends BaseExample implements Example {

	public EllipseExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		for (int i = 0; i < 40; i++) {  
			  
            final int strokeWidth = 1;  
            final Ellipse ellipse = new Ellipse(Math.random() * 160, Math.random() * 80);  
            ellipse.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))  
                    .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillColor(Color.getRandomHexColor()).setDraggable(true);  
            layer.add(ellipse);  
        }  
		
	}

}
