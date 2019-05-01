package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.animation.AnimationProperties;
import com.ait.lienzo.client.core.animation.AnimationProperty.Properties;
import com.ait.lienzo.client.core.animation.AnimationTweener;
import com.ait.lienzo.client.core.config.LienzoCoreEntryPoint;
import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.GridLayer;
import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.widget.LienzoPanel2;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.tools.client.Console;
import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsMethod;

import static elemental2.dom.DomGlobal.document;

//import com.ait.lienzo.client.widget.LienzoPanel;
//import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.user.client.ui.Button;

public class LienzoTests2 implements EntryPoint {

    public static final int WIDE = 2815; //2815
    public static final int HIGH = 1415; // 1415


    HTMLDivElement panelDiv;

    LienzoPanel2   lienzo;

    private Example test;

    public void onModuleLoad() {
        new LienzoCoreEntryPoint().onModuleLoad();
        createTests(new Test1("Rectangle"),
                    new Test2("Rectangle2"),
                    new Test3("Tweening"),
                    new DragCirclesTest("Drag Circles"),
                    new DragConstraintsExample("Drag Constraints"),
                    new AnimatedCirclesExample("Animations"),
                    new EventExample("Event Test"),
                    new WiresExample("Wires Test")
                   );
    }

    @JsMethod
    public void createTests(Example... tests)
    {
        for ( Example test : tests)
        {
            createTest(test);
        }
    }


    @JsMethod
    public void createTest(Example test)
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
        Element links = document.getElementById("nav");
        links.appendChild(e1);
    }

    public static class Test1 extends BaseExample implements Example
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

    public static class Test2 extends BaseExample implements Example
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

    public static class Test3 extends BaseExample implements Example
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

    public static class DragCirclesTest extends BaseExample implements Example
    {
        private int width;
        private int height;
        private Circle[] circles;
        int total = 1000;

        public DragCirclesTest(final String title)
        {
            super(title);
        }

        @Override
        public void run()
        {
            this.width = panel.getWidth();
            this.height = panel.getHeight();
            this.circles = new Circle[total];

            for (int i = 0; i < total; i++) {

                final Circle circle = new Circle(10);
                circles[i] = circle;
                circle.setX(Util.generateValueWithinBoundary(width, 10)).setY(Util.generateValueWithinBoundary(height, 10))
                      .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(2).setFillColor(Color.getRandomHexColor()).setDraggable(true);
                layer.add(circle);

            }
        }

        @Override public void onResize()
        {
            this.width = panel.getWidth();
            this.height = panel.getHeight();

            for (int i = 0; i < total; i++) {

                final Circle circle = circles[i];
                circle.setX(Util.generateValueWithinBoundary(width, 10)).setY(Util.generateValueWithinBoundary(height, 10));
            }

            layer.batch();
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
        HTMLDivElement content = (HTMLDivElement) document.getElementById("content");
        content.appendChild(panelDiv);

        lienzo = new LienzoPanel2(panelDiv, true);
        applyGrid(lienzo);

        DomGlobal.window.addEventListener("resize", (e) ->
        {
            test.onResize();
        });
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
