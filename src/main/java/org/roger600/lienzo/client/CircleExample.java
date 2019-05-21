package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.shared.core.types.Color;

public class CircleExample extends BaseExample implements Example {

	public CircleExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {

		for (int i = 0; i < 40; i++) {  
			  
            final Circle circle = new Circle(Util.randomNumber(8, 10));  
            circle.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))  
                    .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(2).setFillColor(Color.getRandomHexColor()).setDraggable(true);  
            layer.add(circle);  
  
        }  
		
	}

}
