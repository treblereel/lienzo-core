package org.roger600.lienzo.client;

import org.roger600.Util;

import com.ait.lienzo.client.core.animation.AnimationProperties;
import com.ait.lienzo.client.core.animation.AnimationProperty.Properties;
import com.ait.lienzo.client.core.animation.AnimationTweener;
import com.ait.lienzo.client.core.config.LienzoCoreEntryPoint;
import com.ait.lienzo.client.core.shape.Arc;
import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.Ellipse;
import com.ait.lienzo.client.core.shape.GridLayer;
import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.lienzo.client.core.shape.Star;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.core.types.LinearGradient;
import com.ait.lienzo.client.core.types.RadialGradient;
import com.ait.lienzo.client.core.types.Shadow;
import com.ait.lienzo.client.widget.LienzoPanel2;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.ColorName;
import com.ait.lienzo.shared.core.types.TextAlign;
import com.ait.lienzo.tools.client.Console;
import com.ait.lienzo.tools.client.Timer;
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
        createTests(new StrokeAndFillingExample("Stroke and Filling"),
                    new GradientsAndShadowsExample("Gradients and Shadows"),
                    new ColorsAndTransparency("Colors and Transparency"),
                    new TweeningExample("Tweening"),
                    new TimersExample("Timers"),
                    new DragCirclesExample("Drag Circles"),
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

    public static class TweeningExample extends BaseExample implements Example
    {
        public TweeningExample(final String title)
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

    public static class StrokeAndFillingExample extends BaseExample implements Example
    {
        private Circle[] circles;
        private Text[] texts;


        private int total = 3;

        public StrokeAndFillingExample(final String title)
        {
            super(title);
        }

        @Override
        public void run()
        {
            final String strokeColor = Color.getRandomHexColor();
            final String fillColor = Color.getRandomHexColor();
            Text         text;

            circles = new Circle[total];
            texts = new Text[total];

            for (int i = 0; i < total; i++) {

                final Circle circle = new Circle(60);
                circles[i] = circle;

                if (i == 0) {
                    text = new Text("Stroke", "oblique normal bold", 24).setTextAlign(TextAlign.CENTER).setStrokeColor(strokeColor)
                                                                        .setStrokeWidth(2);
                    texts[i] = text;
                    circle.setStrokeColor(strokeColor).setStrokeWidth(2);
                } else if (i == 1) {
                    text = new Text("Fill", "oblique normal bold", 24).setTextAlign(TextAlign.CENTER).setFillColor(fillColor);
                    texts[i] = text;
                    circle.setFillColor(fillColor);
                } else if (i == 2) {
                    text = new Text("Stroke & Fill", "oblique normal bold", 24).setTextAlign(TextAlign.CENTER)
                                                                               .setStrokeColor(strokeColor).setStrokeWidth(2).setFillColor(fillColor);
                    texts[i] = text;
                    circle.setFillColor(fillColor).setStrokeColor(strokeColor).setStrokeWidth(2);
                } else {
                    throw new RuntimeException();
                }
                layer.add(circle);
                layer.add(text);
            }

            setLocation();

            layer.draw();
        }

        @Override
        public void onResize()
        {
            super.onResize();

            setLocation();

            layer.batch();
        }

        private void setLocation()
        {
            int          x = (int) (width * .25);
            int          y = (int) (height * .50);

            for (int i = 0; i < circles.length; i++) {
                int   xOffSet = x * (i+1);
                Shape shape   = circles[i];
                shape.setX(xOffSet).setY(y);

                final Text text   = texts[i];
                text.setX(xOffSet).setY(y - 70);
            }
        }
    }

    public static class GradientsAndShadowsExample extends BaseExample implements Example
    {
        private Shape[] shapes;
        private int total = 4;

        public GradientsAndShadowsExample(final String title)
        {
            super(title);
        }

        @Override
        public void run()
        {

            shapes = new Shape[total * 5];

            int j = 0;
            for (int i = 0; i < total; i++) {

                final int strokeWidth = 1;

                final RadialGradient radialGradient = new RadialGradient(0, 0, 0, 0, 0, 40);
                radialGradient.addColorStop(0.0, Color.getRandomHexColor());
                radialGradient.addColorStop(1.0, Color.getRandomHexColor());

                final Circle circle = new Circle(Util.randomNumber(8, 10));
                circle.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))
                      .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillGradient(radialGradient)
                      .setShadow(new Shadow(Color.getRandomHexColor(), 50, 0, 0)).setDraggable(true);
                layer.add(circle);
                shapes[j++] = circle;

                final LinearGradient linearGradient = new LinearGradient(0, -50, 0, 50);
                linearGradient.addColorStop(0, Color.getRandomHexColor());
                linearGradient.addColorStop(0.30, Color.getRandomHexColor());
                linearGradient.addColorStop(1, Color.getRandomHexColor());

                final Rectangle rectangle = new Rectangle(Math.random() * 160, Math.random() * 100);
                rectangle.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))
                         .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillGradient(linearGradient)
                         .setShadow(new Shadow(Color.getRandomHexColor(), 50, 0, 0)).setDraggable(true);
                layer.add(rectangle);
                shapes[j++] = rectangle;

                final Star star = new Star((int) (Math.random() * 10), 25, 50);
                star.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))
                    .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillGradient(linearGradient)
                    .setShadow(new Shadow(Color.getRandomHexColor(), 20, 10, 10)).setDraggable(true);
                layer.add(star);
                shapes[j++] = star;

                final Arc arc = new Arc((int) (Math.random() * 80), 0, (Math.PI * 2) / 2);
                arc.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))
                   .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillGradient(radialGradient)
                   .setShadow(new Shadow(Color.getRandomHexColor(), 50, 0, 0)).setDraggable(true);
                layer.add(arc);
                shapes[j++] = arc;

                final Ellipse ellipse = new Ellipse(Math.random() * 120, Math.random() * 60);
                ellipse.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))
                       .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(strokeWidth).setFillGradient(linearGradient)
                       .setShadow(new Shadow(Color.getRandomHexColor(), 50, 0, 0)).setDraggable(true);
                layer.add(ellipse);
                shapes[j++] = ellipse;

                layer.draw();
            }
        }

        @Override
        public void onResize()
        {
            super.onResize();

            for (int j = 0; j < shapes.length; j++) {
                final Shape shape = shapes[j];
                shape.setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125));
            }

            layer.batch();
        }
    }

    public static class ColorsAndTransparency extends BaseExample implements Example
    {
        private Shape[] shapes;

        private int total = 10;

        public ColorsAndTransparency(final String title)
        {
            super(title);
        }

        @Override
        public void run()
        {
            shapes = new Shape[total];

            // create the rectangles
            for (int i = 0; i < total; i++) {
                final Rectangle rectangle = new Rectangle(120, 60);
                rectangle.setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(1).setFillColor(Color.getRandomHexColor())
                         .setAlpha(Math.random() * 1).setDraggable(true);
                layer.add(rectangle);
                shapes[i] = rectangle;
            }

            setLocation();
        }

        @Override
        public void onResize()
        {
            super.onResize();

            setLocation();

            layer.batch();
        }

        private void setLocation()
        {
            int xOffSet = 40;
            int yOffSet = 40;

            int x = width  / 2 - (xOffSet * total/2);
            int y = height / 2 - (yOffSet * total/2);

            for (int j = 0; j < shapes.length; j++) {
                final Shape shape = shapes[j];
                shape.setX(x).setY(y);
                x += xOffSet;
                y += yOffSet;
            }
        }
    }

    public static class TimersExample extends BaseExample implements Example
    {
        private Shape[] shapes;
        public TimersExample(final String title)
        {
            super(title);
        }

        @Override
        public void run()
        {
            shapes = new Shape[5];

            int x = width / 2;

            Text text1 = new Text("Click to hide and show once.", "oblique normal", 16)
                    .setTextAlign(TextAlign.CENTER).setStrokeColor(ColorName.BLACK).setStrokeWidth(2)
                    .setX(x).setY(80);
            layer.add(text1);
            shapes[0] = text1;

            final Circle circ1 = new Circle(50);
            circ1.setX(x).setY(150);
            circ1.setFillColor(ColorName.YELLOWGREEN);
            circ1.setStrokeColor(ColorName.YELLOWGREEN);
            circ1.setDraggable(true);
            layer.add(circ1);
            shapes[1] = circ1;

            final Timer scheduledTimer1 = new Timer() {
                @Override
                public void run() {
                    circ1.setVisible(false);
                    layer.batch();
                    Timer scheduledTimer2 = new Timer() {
                        @Override
                        public void run() {
                            circ1.setVisible(true);
                            layer.batch();

                        }
                    };
                    scheduledTimer2.schedule(1000);
                }
            };

            circ1.addNodeMouseClickHandler((e) ->{
                scheduledTimer1.schedule(1000);
            });

            Text text2 = new Text("Click to repeat hide and show.", "oblique normal", 16)
                    .setTextAlign(TextAlign.CENTER).setStrokeColor(ColorName.BLACK).setStrokeWidth(2)
                    .setX(x).setY(250);
            layer.add(text2);
            shapes[2] = text2;

            Text text3 = new Text("Click again to stop.", "oblique normal", 16)
                    .setTextAlign(TextAlign.CENTER).setStrokeColor(ColorName.BLACK).setStrokeWidth(2)
                    .setX(x).setY(280);
            layer.add(text3);
            shapes[3] = text3;


            final Circle circ2 = new Circle(50);
            circ2.setX(x).setY(350);
            circ2.setFillColor(ColorName.BLUEVIOLET );
            circ2.setStrokeColor(ColorName.BLUEVIOLET);
            circ2.setDraggable(true);
            layer.add(circ2);
            shapes[4] = circ2;

            final Timer intervalTimer1 = new Timer() {
                @Override
                public void run()
                {
                    circ2.setVisible(!circ2.isVisible());
                    layer.batch();
                }
            };
            circ2.addNodeMouseClickHandler((e) ->{
                if (intervalTimer1.isRunning())
                {
                    intervalTimer1.cancel();
                }
                else
                {
                    intervalTimer1.scheduleRepeating(1000);
                }

            });

            layer.batch();

        }

        @Override
        public void onResize()
        {
            super.onResize();

            setLocation();

            layer.batch();
        }

        private void setLocation()
        {
            int x = width  / 2;

            for (int j = 0; j < shapes.length; j++) {
                final Shape shape = shapes[j];
                shape.setX(x);
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
