package org.roger600.lienzo.client;

import org.gwtproject.dom.style.shared.Display;

import com.ait.lienzo.client.core.event.NodeDragEndEvent;
import com.ait.lienzo.client.core.event.NodeDragEndHandler;
import com.ait.lienzo.client.core.event.NodeDragStartEvent;
import com.ait.lienzo.client.core.event.NodeDragStartHandler;
import com.ait.lienzo.client.core.event.NodeMouseEnterEvent;
import com.ait.lienzo.client.core.event.NodeMouseEnterHandler;
import com.ait.lienzo.client.core.event.NodeMouseExitEvent;
import com.ait.lienzo.client.core.event.NodeMouseExitHandler;
import com.ait.lienzo.client.core.shape.IPrimitive;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.PolyLine;
import com.ait.lienzo.client.core.shape.Polygon;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.RegularPolygon;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.lienzo.client.core.shape.Star;
import com.ait.lienzo.client.core.shape.Triangle;
import com.ait.lienzo.client.core.shape.guides.ToolTip;
import com.ait.lienzo.client.core.shape.wires.IContainmentAcceptor;
import com.ait.lienzo.client.core.shape.wires.IDockingAcceptor;
import com.ait.lienzo.client.core.shape.wires.WiresContainer;
import com.ait.lienzo.client.core.shape.wires.WiresManager;
import com.ait.lienzo.client.core.shape.wires.WiresShape;
import com.ait.lienzo.client.core.types.BoundingBox;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.client.core.types.Point2DArray;
import com.ait.lienzo.client.widget.LienzoPanel2;
import com.ait.lienzo.shared.core.types.ColorName;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLOptionElement;
import elemental2.dom.HTMLSelectElement;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

import static elemental2.dom.DomGlobal.document;

public class WiresDockingExample extends BaseExample implements Example
{


    public WiresDockingExample(final String title)
    {
        super(title);
    }

    @Override
    public void init(final LienzoPanel2 panel, final HTMLDivElement topDiv)
    {
        super.init(panel, topDiv);

    }

    @Override public void run()
    {

//        getToolBarContainer().add(m_label);

        final WiresManager wires_manager = WiresManager.get(layer);

        wires_manager.setContainmentAcceptor(IContainmentAcceptor.ALL);

        wires_manager.setDockingAcceptor(new IDockingAcceptor()
        {
            @Override
            public boolean dockingAllowed(final WiresContainer parent, final WiresShape child)
            {
                return acceptDocking(parent, child);
            }

            @Override
            public boolean acceptDocking(final WiresContainer parent, final WiresShape child)
            {
                final String pd = getUserData(parent);
                final String cd = getUserData(child);
                return "parent".equals(pd) && "dock".equals(cd);
            }

            @Override
            public int getHotspotSize()
            {
                return IDockingAcceptor.HOTSPOT_SIZE;
            }

            private String getUserData(final WiresContainer shape)
            {
                return ((null != shape) && (null != shape.getContainer()) && (null != shape.getContainer().getUserData())) ? shape.getContainer().getUserData().toString() : null;
            }
        });

        final MultiPath  parentMultiPath = new MultiPath().rect(0, 0, 400, 400).setStrokeColor("#000000").setFillColor(ColorName.WHITESMOKE);
        final WiresShape parentShape     = new WiresShape(parentMultiPath);
        parentShape.getContainer().setUserData("parent");
        parentShape.setLocation(new Point2D(500, 200)).setDraggable(true);
        wires_manager.register(parentShape);
        wires_manager.getMagnetManager().createMagnets(parentShape);

        final MultiPath childMultiPath = new MultiPath().rect(0, 0, 100, 100).setStrokeColor(ColorName.RED).setFillColor(ColorName.RED);
        final WiresShape childShape = new WiresShape(childMultiPath);
        childShape.getContainer().setUserData("child");
        childShape.setLocation(new Point2D(50, 200)).setDraggable(true);
        wires_manager.register(childShape);

        wires_manager.getMagnetManager().createMagnets(childShape);

        final MultiPath dockMultiPath = new MultiPath().rect(0, 0, 100, 100).setStrokeColor(ColorName.BLUE).setFillColor(ColorName.BLUE);
        final WiresShape dockShape = new WiresShape(dockMultiPath);
        dockShape.getContainer().setUserData("dock");
        dockShape.setLocation(new Point2D(50, 400)).setDraggable(true);
        wires_manager.register(dockShape);
        wires_manager.getMagnetManager().createMagnets(dockShape);
    }

    @Override public void destroy()
    {
        super.destroy();
    }

}
