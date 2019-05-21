package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.RegularPolygon;
import com.ait.lienzo.client.core.types.Shadow;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.LineJoin;

public class PolygonsExample extends BaseExample implements Example {

	public PolygonsExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		for (int i = 0; i < 40; i++) {  
            final int strokeWidth = Util.randomNumber(2, 10);  
            final RegularPolygon polygon = new RegularPolygon(8, 60);  
            polygon.setShadow(new Shadow("black", 6, 6, 6)).setFillColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth)  
                    .setStrokeColor(Color.getRandomHexColor()).setLineJoin(LineJoin.ROUND).setX(Util.generateValueWithinBoundary(width, 125))  
                    .setY(Util.generateValueWithinBoundary(height, 125)).setDraggable(true);  
            layer.add(polygon);  
        }  
		
	}

}
