package org.roger600.lienzo.client;

import java.util.function.Predicate;

import org.roger600.AnimatedCircles;

import com.ait.lienzo.client.core.animation.AnimationProperties;
import com.ait.lienzo.client.core.animation.AnimationProperty.Properties;
import com.ait.lienzo.client.core.animation.AnimationTweener;
import com.ait.lienzo.client.core.config.LienzoCore;
import com.ait.lienzo.client.core.config.LienzoCoreEntryPoint;
import com.ait.lienzo.client.core.shape.GridLayer;
import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.ait.lienzo.client.widget.LienzoPanel2;
import com.ait.lienzo.tools.client.Console;
import com.google.gwt.core.client.EntryPoint;

import elemental2.core.Function;
import elemental2.dom.CSSProperties.HeightUnionType;
import elemental2.dom.CSSProperties.WidthUnionType;
import elemental2.dom.CSSStyleDeclaration;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.Event;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLLinkElement;
import elemental2.dom.MouseEvent;
import elemental2.dom.UIEvent;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsMethod;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

import static elemental2.dom.DomGlobal.document;

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
        createTests("test1", "test2", "test3", "test4");
    }


    @JsMethod
    public void createTests(String... tests)
    {
        for ( String test : tests)
        {
            Function func = (Function) ((JsPropertyMap)this).get("createTest");
            func.apply(this, new Object[] {test});
        }
    }


    @JsMethod
    public void createTest(String test)
    {
        HTMLDivElement e1 = (HTMLDivElement) document.createElement("div");
        elemental2.dom.Text e1Text = document.createTextNode(test);
        e1.appendChild(e1Text);
        e1.addEventListener("click", evt -> {
            createPanel();
            Function func = (Function) ((JsPropertyMap)LienzoTests2.this).get(test);
            func.apply(LienzoTests2.this);
        });
        Element links = document.getElementById("links");
        links.appendChild(e1);
    }

    @JsMethod
    public void test1()
    {
        //lienzo.add()
        Layer l1 = new Layer();
        lienzo.add(l1);

        MultiPath path1 = new MultiPath().rect(100, 100, 200, 200)
                                         .setStrokeColor( "#FFFFFF" ).setFillColor( "#CC0000" ).setDraggable(true);
        l1.add(path1);
        l1.draw();

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
        l1.add(text2);

        l1.draw();

        Console.get().info("hello 2");
    }

    @JsMethod
    public void test2()
    {
        //lienzo.add()
        Layer l1 = new Layer();
        lienzo.add(l1);

        MultiPath path1 = new MultiPath().rect(100, 100, 200, 200)
                                         .setStrokeColor( "#FFFFFF" ).setFillColor( "#0000CC" ).setDraggable(true);
        l1.add(path1);
        l1.draw();

        MultiPath path2 = new MultiPath().rect(0, 0, 200, 200)
                                         .setStrokeColor( "#FFFFFF" ).setFillColor( "#CC0000" ).setDraggable(true);

        Text text2 = new Text("test4");
        text2.setStrokeColor("#0000CC").setFillColor("#0000CC").setDraggable(true);
        //text2.setFontSize(100);
        text2.setFontSize(30);
        text2.setX(100);
        text2.setY(350);
        l1.add(text2);

        l1.draw();

        Console.get().info("hello 2");
    }

    @JsMethod
    public void test3()
    {
        //lienzo.add()
        Layer l1 = new Layer();
        lienzo.add(l1);

        MultiPath path1 = new MultiPath().rect(0, 0, 200, 200)
                                         .setStrokeColor( "#FFFFFF" ).setFillColor( "#0000CC" ).setDraggable(true);
        l1.add(path1);
        l1.draw();

        AnimationProperties props = new AnimationProperties();
        props.push(Properties.X(200));
        props.push(Properties.Y(200));
        props.push(Properties.SCALE(2));

        path1.animate(AnimationTweener.LINEAR, props, 5000);


        Console.get().info("hello 2");
    }

    @JsMethod
    public void test4()
    {
        Layer l1 = new Layer();
        lienzo.add(l1);
        AnimatedCircles test = new AnimatedCircles(l1);
        test.animate();
        this.test = test;

        Console.get().info("hello 2");
    }

    private void createPanel()
    {
        if (lienzo != null)
        {
            lienzo.destroy();
        }

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

    private void createPanelForTest(MyLienzoTest test) {
//
//        screenButtonsPanel.clear();
//        testsPanel.clear();
//        testsPanel.getElement().getStyle().setMargin( 10, Style.Unit.PX );
//        testsPanel.getElement().getStyle().setBorderWidth( 1, Style.Unit.PX );
//        testsPanel.getElement().getStyle().setBorderStyle( Style.BorderStyle.SOLID );
//        testsPanel.getElement().getStyle().setBorderColor( "#000000" );
//
//        final LienzoPanel panel = new LienzoPanel(WIDE,
//                                                  HIGH);
//        applyGrid( panel );
//        final Layer layer = new Layer();
//
//        testsPanel.add( panel );
//        layer.setTransformable(true);
//        panel.add(layer);
//
//        if ( test instanceof HasButtons ) {
//            ( ( HasButtons ) test ).setButtonsPanel( screenButtonsPanel );
//        }
//
//        if ( test instanceof HasMediators ) {
//            addMediators( layer );
//        }
//
//        if ( test instanceof NeedsThePanel ) {
//            ((NeedsThePanel) test).setLienzoPanel(panel);
//        }
//
//        test.test( layer );
//
//        layer.draw();

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
