package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Arc;
import com.ait.lienzo.shared.core.types.Color;

public class ArcsExample extends BaseExample implements Example {

	public ArcsExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		for (int i = 0; i < 30; i++) {  
			  
            final Arc arc = new Arc((int) (Util.randomNumber(10, 10)), 0, (Math.PI * 2) / 2);  
            arc.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))  
                    .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(2).setFillColor(Color.getRandomHexColor()).setDraggable(true)  
                    .setRotationDegrees(Util.randomNumber(3, 10));  
            layer.add(arc);  
        }  
		
	}

}
