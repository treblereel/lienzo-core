package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.shared.core.types.Color;

public class LinesExample extends BaseExample implements Example {

	public LinesExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		
		final double x1 = width * 0.25;  
        double y1 = height * 0.15;  
          
        final double x2 = width * 0.75;  
        double y2 = height * 0.15;  
  
        for (int i = 0; i < 10; i++) {  
            Line line = new Line(x1,y1, x2, y2);  
            line.setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(1+i).setFillColor(Color.getRandomHexColor());  
            layer.add(line);  
            y1 += 50;  
            y2 += 50;  
        }  
		
	}

}
