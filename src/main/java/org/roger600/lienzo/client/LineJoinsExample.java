package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Star;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.LineJoin;

public class LineJoinsExample extends BaseExample implements Example {

	public LineJoinsExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		for (int i = 0; i < 5; i++) {  
			  
            Star star = new Star(5, 30, 80);  
            star.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))  
                    .setStrokeColor(Color.getRandomHexColor()).setFillColor(Color.getRandomHexColor())  
                    .setLineJoin(LineJoin.BEVEL).setStrokeWidth(15).setDraggable(true);  
            layer.add(star);  
  
            star = new Star(10, 30, 80);  
            star.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))  
                    .setStrokeColor(Color.getRandomHexColor()).setFillColor(Color.getRandomHexColor())  
                    .setLineJoin(LineJoin.MITER).setStrokeWidth(15).setDraggable(true);  
            layer.add(star);  
  
            star = new Star(7, 30, 80);  
            star.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))  
                    .setStrokeColor(Color.getRandomHexColor()).setFillColor(Color.getRandomHexColor())  
                    .setLineJoin(LineJoin.ROUND).setStrokeWidth(15).setDraggable(true);  
            layer.add(star);  
        }  
		
	}

}
