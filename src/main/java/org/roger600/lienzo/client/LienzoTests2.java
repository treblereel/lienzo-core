package org.roger600.lienzo.client;

import org.roger600.AnimatedCircles;
import org.roger600.Util;

import com.ait.lienzo.client.core.animation.AnimationProperties;
import com.ait.lienzo.client.core.animation.AnimationProperty.Properties;
import com.ait.lienzo.client.core.animation.AnimationTweener;
import com.ait.lienzo.client.core.config.LienzoCoreEntryPoint;
import com.ait.lienzo.client.core.mediator.EventFilter;
import com.ait.lienzo.client.core.mediator.MouseWheelZoomMediator;
import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.GridLayer;
import com.ait.lienzo.client.core.shape.IPrimitive;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Star;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.client.core.types.Point2DArray;
import com.ait.lienzo.client.widget.DragConstraintEnforcer;
import com.ait.lienzo.client.widget.DragContext;
import com.ait.lienzo.client.widget.LienzoPanel2;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.ColorName;
import com.ait.lienzo.tools.client.Console;
import com.google.gwt.core.client.EntryPoint;

import elemental2.core.Function;
import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsMethod;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

import static elemental2.dom.DomGlobal.document;
import static elemental2.dom.DomGlobal.window;

//import com.ait.lienzo.client.widget.LienzoPanel;
//import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.user.client.ui.Button;

public class LienzoTests2 implements EntryPoint {

    public static final int WIDE = 2815; //2815
    public static final int HIGH = 1415; // 1415


    HTMLDivElement panelDiv;

    LienzoPanel2   lienzo;

    private ExampleTest test;

    public void onModuleLoad() {
        new LienzoCoreEntryPoint().onModuleLoad();
        createTests(new Test1("Rectangle"),
                    new Test2("Rectangle2"),
                    new Test3("Tweening"),
                    new DragCircles("Drag Circles"),
                    new DragConstraints("Drag Constraints"),
                    new AnimatedCircles("Animations") );

    }

    @JsMethod
    public void createTests(ExampleTest... tests)
    {
        for ( ExampleTest test : tests)
        {
            createTest(test);
        }
    }


    @JsMethod
    public void createTest(ExampleTest test)
    {
        HTMLDivElement e1 = (HTMLDivElement) document.createElement("div");
        elemental2.dom.Text e1Text = document.createTextNode(test.getTitle());
        e1.appendChild(e1Text);
        e1.addEventListener("click", evt -> {
            createPanel();
            this.test = test;
            this.test.init(lienzo);
            this.test.run();

        });
        Element links = document.getElementById("links");
        links.appendChild(e1);
    }

    public static class Test1 extends BaseExampleTest implements ExampleTest
    {
        public Test1(final String title)
        {
            super(title);
        }

        @Override
        public void run()
        {
            MultiPath path1 = new MultiPath().rect(100, 100, 200, 200)
                                             .setStrokeColor( "#FFFFFF" ).setFillColor( "#CC0000" ).setDraggable(true);
            layer.add(path1);
            layer.draw();

            MultiPath path2 = new MultiPath().rect(0, 0, 200, 200)
                                             .setStrokeColor( "#FFFFFF" ).setFillColor( "#CC0000" ).setDraggable(true);

//        Group group = new Group();
//        group.add(path2);
//
//        group.setY(400);
//
//        l1.add(group);
//
//        l1.draw();
//
//        Console.get().info("hello 1");
//
//        Text text = new Text("test1");
//        text.setStrokeColor("#0000CC").setFillColor("#0000CC").setDraggable(true);
//        text.setFontSize(10);
//        text.setY(10);
//        group.add(text);


            Text text2 = new Text("test4");
            text2.setStrokeColor("#0000CC").setFillColor("#0000CC").setDraggable(true);
            //text2.setFontSize(100);
            text2.setFontSize(30);
            text2.setX(100);
            text2.setY(350);
            layer.add(text2);

            layer.draw();

            Console.get().info("hello 2");
        }
    }

    public static class Test2 extends BaseExampleTest implements ExampleTest
    {
        public Test2(final String title)
        {
            super(title);
        }

        @Override
        public void run()
        {
            MultiPath path1 = new MultiPath().rect(100, 100, 200, 200)
                                             .setStrokeColor( "#FFFFFF" ).setFillColor( "#0000CC" ).setDraggable(true);
            layer.add(path1);
            layer.draw();

            MultiPath path2 = new MultiPath().rect(0, 0, 200, 200)
                                             .setStrokeColor( "#FFFFFF" ).setFillColor( "#CC0000" ).setDraggable(true);

            Text text2 = new Text("test4");
            text2.setStrokeColor("#0000CC").setFillColor("#0000CC").setDraggable(true);
            //text2.setFontSize(100);
            text2.setFontSize(30);
            text2.setX(100);
            text2.setY(350);
            layer.add(text2);

            layer.draw();

            Console.get().info("hello 2");
        }
    }

    public static class Test3 extends BaseExampleTest implements ExampleTest
    {
        public Test3(final String title)
        {
            super(title);
        }

        @Override
        public void run()
        {
            MultiPath path1 = new MultiPath().rect(0, 0, 200, 200)
                                             .setStrokeColor( "#FFFFFF" ).setFillColor( "#0000CC" ).setDraggable(true);
            layer.add(path1);
            layer.draw();

            AnimationProperties props = new AnimationProperties();
            props.push(Properties.X(200));
            props.push(Properties.Y(200));
            props.push(Properties.SCALE(2));

            path1.animate(AnimationTweener.LINEAR, props, 5000);


            Console.get().info("hello 2");
        }
    }

    public static class DragCircles extends BaseExampleTest implements ExampleTest
    {
        public DragCircles(final String title)
        {
            super(title);
        }

        @Override
        public void run()
        {
            int total = 1000;

            for (int i = 0; i < total; i++) {

                final Circle circle = new Circle(10);
                circle.setX(Util.generateValueWithinBoundary(700, 10)).setY(Util.generateValueWithinBoundary(700, 10))
                      .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(2).setFillColor(Color.getRandomHexColor()).setDraggable(true);
                layer.add(circle);

            }
        }
    }

    public static class DragConstraints extends BaseExampleTest implements ExampleTest
    {
        public DragConstraints(final String title)
        {
            super(title);
        }

        @Override
        public void run()
        {

            int width = 700;
            int height = 700;

            // Note that the API w.r.t. custom drag constraints may change.
            // We'll probably allow you set an attribute, rather than having to override
            // the getDragConstraints() method.

            // The rectangle location snaps to a 10 pixel grid.
            Rectangle r = new Rectangle(40, 50);
            DragConstraintEnforcer enforcer = new DragConstraintEnforcer() {
                        @Override
                        public boolean adjust(Point2D dxy) {
                            dxy.setX(snap(dxy.getX()));
                            dxy.setY(snap(dxy.getY()));

                            return true;
                        }

                        private double snap(double x) {
                            int w = 10;
                            return w * Math.round(x / w);
                        }

                        @Override
                        public void startDrag(DragContext dragContext) {
                            // not used
                        }
            };
            r.setDragConstraints(enforcer);
            r.setX(100).setY(200).setFillColor(ColorName.RED).setDraggable(true);
            layer.add(r);

            // The circle can be dragged in a 50 pixel radius from the anchor point (500,300).
            final Point2D anchor = new Point2D(500, 300);
            final Point2D center = new Point2D(0, 0); // center of the circle when the drag starts
            Circle c = new Circle(40);
            enforcer = new DragConstraintEnforcer() {

                        @Override
                        public void startDrag(DragContext dragContext) {
                            IPrimitive<?> node = dragContext.getNode();
                            center.setX(node.getX());
                            center.setY(node.getY());
                        }

                        @Override
                        public boolean adjust(Point2D dxy) {
                            Point2D newCenter = center.add(dxy);
                            double maxRadius = 50;
                            Point2D anchorToCenter = newCenter.sub(anchor);
                            double distFromAnchor = anchorToCenter.getLength();
                            if (distFromAnchor > maxRadius)
                            {
                                // Move the center of the circle to the nearest point on the boundary circle
                                newCenter = anchorToCenter.unit().mul(maxRadius).add(anchor);
                                Point2D newDxy = newCenter.sub(center);
                                dxy.set(newDxy);
                            }
                            return true;
                        }
            };
            c.setLocation(anchor).setFillColor(ColorName.BLUE).setDraggable(true);
            c.setDragConstraints(enforcer);
            layer.add(c);

            // The center of the star snaps to the nearest anchor point
            final Point2DArray anchorPoints = new Point2DArray().fromArrayOfPoint2D(
                    new Point2D(50, 500), new Point2D(100, 400), new Point2D(150, 450),
                    new Point2D(200, 500), new Point2D(250, 400), new Point2D(300, 450));
            final Star star = new Star(5, 20, 40);
            enforcer = new DragConstraintEnforcer() {
                        @Override
                        public void startDrag(DragContext dragContext) {
                            IPrimitive<?> node = dragContext.getNode();
                            center.set(node.getLocation());
                        }

                        @Override
                        public boolean adjust(Point2D dxy) {
                            Point2D newCenter = center.add(dxy);
                            Point2D closestPoint = null;
                            double minDist = Double.MAX_VALUE;
                            for (int i = 0, n = anchorPoints.getLength(); i < n; i++)
                            {
                                Point2D ap = anchorPoints.get(i);
                                double d = newCenter.distance(ap);
                                if (d < minDist)
                                {
                                    minDist = d;
                                    closestPoint = ap;
                                }
                            }
                            Point2D newDxy = closestPoint.sub(center);
                            dxy.set(newDxy);

                            return true;
                        }
            };
            star.setDragConstraints(enforcer);
            star.setLocation(anchorPoints.get(0));
            star.setFillColor(ColorName.DARKORANGE);
            star.setDraggable(true);
            layer.add(star);

            // Draw the anchor points
            for (int i = 0, n = anchorPoints.getLength(); i < n; i++)
            {
                Point2D ap = anchorPoints.get(i);
                Circle pt = new Circle(2);
                pt.setLocation(ap).setFillColor(ColorName.BLACK);
                layer.add(pt);
            }
        }
    }

    private void createPanel()
    {
        if (this.test != null)
        {
            this.test.destroy();
            this.test = null;
        }


        panelDiv = (HTMLDivElement) document.createElement("div");
        panelDiv.style.display = "inline-block";

//        div.style.width = WidthUnionType.of("1000px");
//        div.style.height = HeightUnionType.of("1000px");
        Element content = document.getElementById("content");
        content.appendChild(panelDiv);


        lienzo = new LienzoPanel2(panelDiv, 700, 700);
        applyGrid(lienzo);
    }

    private void addMediators(Layer layer) {
//        final Mediators mediators = layer.getViewport().getMediators();
//        mediators.push( new MouseWheelZoomMediator( zommFilters ) );
//        mediators.push( new MousePanMediator( panFilters ) );
    }

//    private void addButton( final Button button ) {
////
////        if ( buttonsPanelSize >= MAX_BUTTONS_ROW ) {
////
////            buttonsPanelSize = 0;
////            buttonsRowPanel = null;
////        }
////
////
////        if ( null == buttonsRowPanel ) {
////            buttonsRowPanel = new HorizontalPanel();
////            buttonsPanel.add( buttonsRowPanel );
////        }
////
////        buttonsRowPanel.add( button );
////        buttonsPanelSize++;
//    }

    private void applyGrid( final LienzoPanel2 panel) {
        // Grid.
        Line line1 = new Line(0, 0, 0, 0 )
                .setStrokeColor( "#0000FF" )
                .setAlpha( 0.2 );
        Line line2 = new Line( 0, 0, 0, 0 )
                .setStrokeColor( "#00FF00"  )
                .setAlpha( 0.2 );

        line2.setDashArray( 2,
                2 );

        GridLayer gridLayer = new GridLayer(100, line1, 25, line2 );

        panel.setBackgroundLayer( gridLayer );
    }
}
