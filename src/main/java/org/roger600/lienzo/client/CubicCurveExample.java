package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.BezierCurve;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.LineCap;

public class CubicCurveExample extends BaseExample implements Example {

	public CubicCurveExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		for (int i = 0; i < 30; i++) {  
            final BezierCurve bezierCurve = new BezierCurve(188, 130, 140, 10, 388, 10, 388, 170);  
            bezierCurve.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))  
                    .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(2).setDraggable(true).setLineCap(LineCap.ROUND);  
            layer.add(bezierCurve);  
        }  
		
	}

}
