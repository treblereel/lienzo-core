package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Slice;
import com.ait.lienzo.shared.core.types.ColorName;

public class SliceGroupExample extends BaseExample implements Example {

	public SliceGroupExample(String title) {
		super(title);
	}

	public void destroy() {
        super.destroy();
    }

	@Override
	public void run() {
		final int w = width/2;  
        final int h = height/2;  
          
        final SliceGroup s = new SliceGroup(w);  
        s.setRotation(-Math.PI / 2);  
        s.setX(w - w/2).setY(h + w/2);  
          
        layer.add(s); 
	}
	
	private class SliceGroup extends Group {  
  	  
        public SliceGroup(double w) {  
  
            add(new Rectangle(w, w).setStrokeColor(ColorName.BLACK.getValue()));  
  
            final double r = w / 4;  
              
            Slice s = new Slice(r, 0, Math.PI / 2, true);  
            s.setX(r).setY(r);  
            s.setFillColor(ColorName.RED.getValue());  
            s.setAlpha(0.5);  
            s.setDraggable(true);  
            add(s);  
  
            s = new Slice(r, 0.75 * Math.PI, 3 * Math.PI / 2, true);  
            s.setX(3 * r).setY(r);  
            s.setScale(0.5);  
            s.setFillColor(ColorName.GREEN.getValue());  
            s.setAlpha(0.5);  
            s.setDraggable(true);  
            add(s);  
  
            s = new Slice(r, 0, Math.PI);  
            s.setX(r).setY(3 * r);  
            s.setRotation(Math.PI / 4);  
            s.setFillColor(ColorName.BLUE.getValue());  
            s.setAlpha(0.5);  
            s.setDraggable(true);  
            add(s);  
        }  
  
    }  

}
