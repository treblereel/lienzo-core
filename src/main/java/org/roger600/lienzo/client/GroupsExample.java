package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Star;
import com.ait.lienzo.shared.core.types.Color;

public class GroupsExample extends BaseExample implements Example {

	public GroupsExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		final Group group = new Group();  
        group.setDraggable(true);  
  
        for (int i = 0; i < 5; i++) {  
            final int strokeWidth = Util.randomNumber(2, 10);  
            final Star star = new Star((int) (Math.random() * 10), 25, 50);  
            star.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))  
                    .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillColor(Color.getRandomHexColor());  
            group.add(star);  
        }  
  
        layer.add(group);  
		
	}

}
