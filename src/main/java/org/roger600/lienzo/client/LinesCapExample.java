package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.LineCap;

public class LinesCapExample extends BaseExample implements Example {

	public LinesCapExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		final int middleX = width / 2;  
        final int middleY = height / 2;  
  
        Line line = new Line(middleX - middleX / 2, middleY - 100, middleX + middleX / 2, middleY - 100);  
        line.setStrokeWidth(30).setFillColor(Color.getRandomHexColor()).setStrokeColor(Color.getRandomHexColor()).setLineCap(LineCap.BUTT);  
        layer.add(line);  
  
        line = new Line(middleX - middleX / 2, middleY, middleX + middleX / 2, middleY);  
        line.setStrokeWidth(30).setFillColor(Color.getRandomHexColor()).setStrokeColor(Color.getRandomHexColor()).setLineCap(LineCap.ROUND);  
        layer.add(line);  
  
        line = new Line(middleX - middleX / 2, middleY + 100, middleX + middleX / 2, middleY + 100);  
        line.setStrokeWidth(30).setFillColor(Color.getRandomHexColor()).setStrokeColor(Color.getRandomHexColor()).setLineCap(LineCap.SQUARE);  
        layer.add(line);  
	}

}
