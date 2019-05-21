package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.shared.core.types.Color;

public class RoundedCornersExample extends BaseExample implements Example {

	public RoundedCornersExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		for (int i = 0; i < 30; i++) {  
            final int strokeWidth = 1;  
  
            final Rectangle rectangle = new Rectangle(generateNumber(220), generateNumber(160), 20);  
            rectangle.setX(Util.generateValueWithinBoundary(width, 0)).setY(Util.generateValueWithinBoundary(height, 0))  
                    .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillColor(Color.getRandomHexColor()).setDraggable(true);  
            layer.add(rectangle);  
  
        }  
	}
	
	private double generateNumber(int number) {  
        double result = Math.random() * number;  
        return result < 100 ? generateNumber(number) : result;  
    }  
    	
}
