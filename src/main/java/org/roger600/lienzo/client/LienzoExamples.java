package org.roger600.lienzo.client;

import org.gwtproject.dom.style.shared.Display;

import com.ait.lienzo.client.core.config.LienzoCoreEntryPoint;
import com.ait.lienzo.client.core.shape.GridLayer;
import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.widget.LienzoPanel2;
import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsMethod;

import static elemental2.dom.DomGlobal.document;

public class LienzoExamples implements EntryPoint {
    HTMLDivElement panelDiv;

    LienzoPanel2   lienzo;

    private Example test;

    public void onModuleLoad() {
        new LienzoCoreEntryPoint().onModuleLoad();
        createTests(new StrokeAndFillingExample("Stroke and Filling"),
                    new GradientsAndShadowsExample("Gradients and Shadows"),
                    new ColorsAndTransparencyExample("Colors and Transparency"),
                    new HorizontalTextAlignmentExample("Horizontal Text Alignment"),
                    new VerticalTextAlignmentExample("Vertical Text Alignment"),
                    new MeasureTextExample("Measure Text"),
                    new ScaledTextExample("Scaled Text"),
                    new TextAroundArcExample("Text around Arc"),
                    new PanAndZoomExample("Pan and Zoom"),
                    new TweeningExample("Tweening"),
                    new TimersExample("Timers"),
                    new DragCirclesExample("Drag Circles"),
                    new DragConstraintsExample("Drag Constraints"),
                    new AnimatedCirclesExample("Animated Circles"),
                    new EventExample("Events"),
                    new SVGTigerExample("SVG Paths Tiger"),
                    new WiresExample("Wires"),
                    new Animate("Animations"),
                    new LionExample("Polygon Lion with Clipping"),
                    new CardinalIntersectsExample("Cardinal Intersects")
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
        HTMLDivElement top = (HTMLDivElement) document.getElementById("top");
        elemental2.dom.Text e1Text = document.createTextNode(test.getTitle());
        e1.appendChild(e1Text);
        e1.addEventListener("click", evt -> {
            top.style.display = Display.NONE.getCssName();
            createPanel(test);
            this.test = test;
            this.test.init(lienzo, top);
            this.test.run();

        });
        Element links = document.getElementById("nav");
        links.appendChild(e1);
    }


//    public static class Test1 extends BaseExample implements Example
//    {
//        public Test1(final String title)
//        {
//            super(title);
//        }
//
//        @Override
//        public void run()
//        {
//            MultiPath path1 = new MultiPath().rect(100, 100, 200, 200)
//                                             .setStrokeColor( "#FFFFFF" ).setFillColor( "#CC0000" ).setDraggable(true);
//            layer.add(path1);
//            layer.draw();
//
//            MultiPath path2 = new MultiPath().rect(0, 0, 200, 200)
//                                             .setStrokeColor( "#FFFFFF" ).setFillColor( "#CC0000" ).setDraggable(true);
//
////        Group group = new Group();
////        group.add(path2);
////
////        group.setY(400);
////
////        l1.add(group);
////
////        l1.draw();
////
////        Console.get().info("hello 1");
////
////        Text text = new Text("test1");
////        text.setStrokeColor("#0000CC").setFillColor("#0000CC").setDraggable(true);
////        text.setFontSize(10);
////        text.setY(10);
////        group.add(text);
//
//
//            Text text2 = new Text("test4");
//            text2.setStrokeColor("#0000CC").setFillColor("#0000CC").setDraggable(true);
//            //text2.setFontSize(100);
//            text2.setFontSize(30);
//            text2.setX(100);
//            text2.setY(350);
//            layer.add(text2);
//
//            layer.draw();
//
//            Console.get().info("hello 2");
//        }
//    }
//
//    public static class Test2 extends BaseExample implements Example
//    {
//        public Test2(final String title)
//        {
//            super(title);
//        }
//
//        @Override
//        public void run()
//        {
//            MultiPath path1 = new MultiPath().rect(100, 100, 200, 200)
//                                             .setStrokeColor( "#FFFFFF" ).setFillColor( "#0000CC" ).setDraggable(true);
//            layer.add(path1);
//            layer.draw();
//
//            MultiPath path2 = new MultiPath().rect(0, 0, 200, 200)
//                                             .setStrokeColor( "#FFFFFF" ).setFillColor( "#CC0000" ).setDraggable(true);
//
//            Text text2 = new Text("test4");
//            text2.setStrokeColor("#0000CC").setFillColor("#0000CC").setDraggable(true);
//            //text2.setFontSize(100);
//            text2.setFontSize(30);
//            text2.setX(100);
//            text2.setY(350);
//            layer.add(text2);
//
//            layer.draw();
//
//            Console.get().info("hello 2");
//        }
//    }

    private void createPanel(Example test)
    {
        if (this.test != null)
        {
            this.test.destroy();
            this.test = null;
        }

        panelDiv = (HTMLDivElement) document.createElement("div");
        panelDiv.style.display = "inline-block";
        HTMLDivElement main = (HTMLDivElement) document.getElementById("main");
        main.appendChild(panelDiv);

        lienzo = new LienzoPanel2(panelDiv, true, test.getWidthOffset(), test.getHeightOffset());
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
