package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.QuadraticCurve;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.LineCap;

public class QuadraticCurveExample extends BaseExample implements Example {

	public QuadraticCurveExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		for (int i = 0; i < 30; i++) {  
            final int strokeWidth = Util.randomNumber(2, 10);  
            final QuadraticCurve quadraticCurve = new QuadraticCurve(130, 130, 200, 0, 230, 130);  
            quadraticCurve.setX(Util.generateValueWithinBoundary(width, 40)).setY(Util.generateValueWithinBoundary(height, 100))  
                    .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setDraggable(true).setLineCap(LineCap.ROUND);  
            layer.add(quadraticCurve);  
        }  
		
	}

}
