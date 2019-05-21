package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Star;
import com.ait.lienzo.shared.core.types.Color;

public class StarsExample extends BaseExample implements Example {

	public StarsExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		for (int i = 0; i < 40; i++) {  
            final int strokeWidth = Util.randomNumber(2, 10);  
            Star star = new Star((int) (Math.random() * 10), 25, 50);  
            star.setX(Util.generateValueWithinBoundary(width, 50)).setY(Util.generateValueWithinBoundary(height, 50)).setStrokeColor(Color.getRandomHexColor())  
                    .setStrokeWidth(strokeWidth).setFillColor(Color.getRandomHexColor()).setDraggable(true);  
  
            layer.add(star);  
        }  
		
	}

}
