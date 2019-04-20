package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.widget.LienzoPanel2;
import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.CSSProperties.HeightUnionType;
import elemental2.dom.CSSProperties.WidthUnionType;
import elemental2.dom.CSSStyleDeclaration;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.Event;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.MouseEvent;

import static elemental2.dom.DomGlobal.document;

//import com.ait.lienzo.client.widget.LienzoPanel;
//import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.user.client.ui.Button;

public class LienzoTests2 implements EntryPoint {

    public static final int WIDE = 2815; //2815
    public static final int HIGH = 1415; // 1415
//
//    private final IEventFilter[] zommFilters = new IEventFilter[] { EventFilter.CONTROL };
//    private final IEventFilter[] panFilters = new IEventFilter[] { EventFilter.SHIFT };
//
//    private final static MyLienzoTest[] TESTS = new MyLienzoTest[] {
//            new BoundingBoxTests(),
//            new SelectionManagerTests(),
//            new TextWrapTests(),
//            new AutoMagnetsConnectorsTests(),
//            new CardinalIntersectSimpleTest(),
//            new WiresDragHandlersTests(),
//            new DragHandlersTests(),
//            new SVGPicturesTests(),
//            new ContainerTests(),
//            new SVGTests(),
//            new UXSVGTests(),
//            new DragConstraintsTests(),
//            new FontTests(),
//            new ImagesTests(),
//            new MultiPathShapesTests(),
//            new WiresRingTests(),
//            new BasicWiresShapesTests(),
//            new GlyphPositionsAndScaleTests(),
//            new TransformTests(),
//            new MagnetsAndCPsTests(),
//            new WiresDragAndMoveTests(),
//            new ShapeResizeTests(),
//            new DragBoundsTests(),
//            new LayoutContainerChildrenTests(),
//            new LayoutContainerChildrenTests2(),
//            new ChildRectangleResizeTests(),
//            new ChildCircleResizeTests(),
//            new StandaloneConnectorsTests(),
//            new ConnectionAndMagnetsTests(),
//            new ConnectionAcceptorsTests(),
//            new ConnectorsSelectionTests(),
//            new ConnectorsAndParentsTests(),
//            new ConnectorsAndParentsTests2(),
//            new DeleteChildTests(),
//            new DockingTests(),
//            new MarkConnectorTests(),
//            new MediatorsTests(),
//            new MediatorsTests2(),
//            new WiresTests(),
//            new CaseModellerContainmentTests(),
//            // From Lienzo KS
//            new WiresAlignDistroTests(),
//            new CardinalIntersectKSTests(),
//            new MultiPathResizeTests(),
//            new WiresArrowsTests(),
//            new WiresSquaresTests(),
//            new WiresResizesTests(),
//            new WiresDockingTests(),
//    };

//    private static final int MAX_BUTTONS_ROW = 7;
//    private VerticalPanel mainPanel = new VerticalPanel();
//    private VerticalPanel buttonsPanel = new VerticalPanel();
//    private HorizontalPanel screenButtonsPanel = new HorizontalPanel();
//    private HorizontalPanel buttonsRowPanel;
//    private int buttonsPanelSize = 0;
//    private FlowPanel testsPanel = new FlowPanel();

    public void onModuleLoad() {
//    <!--div id="demo" style="display:inline-block; width:2815px; height:1415px">
//                            asdf
//                            </div-->

        //div.style = CSSStyleDeclaration.of("display:inline-block; width:2815px; height:1415p");

        HTMLDivElement div = (HTMLDivElement) document.createElement("div");
        div.style.display = "inline-block";
        div.style.width = WidthUnionType.of("2815px");
        div.style.height = HeightUnionType.of("1415px");
        document.body.appendChild(div);


//        HTMLButtonElement btn = (HTMLButtonElement) document.createElement("button");
//        btn.textContent = "I am a button";
//        btn.addEventListener("click", evt -> {
//            if(evt instanceof MouseEvent) {
//                MouseEvent mouseEvent = (MouseEvent) evt;
//
////                HTMLDivElement elm    = (HTMLDivElement) document.getElementById("demo");
////                DomGlobal.alert("Clicked " + mouseEvent.clientX + " " + mouseEvent.clientY + " " + elm);
//
//                DomGlobal.alert("Clicked " + mouseEvent.clientX + " " + mouseEvent.clientY);
//
////                LienzoPanel2   lienzo = new LienzoPanel2(div);
////
////                //lienzo.add()
////                Layer l1 = new Layer();
////                lienzo.add(l1);
////                l1.add(new MultiPath().rect(100, 100, 100, 100)
////                                      .setStrokeColor( "#FFFFFF" ).setFillColor( "#CC0000" ).setDraggable(true) );
////                l1.draw();
//
//            }
//        });

                LienzoPanel2   lienzo = new LienzoPanel2(div);

                //lienzo.add()
                Layer l1 = new Layer();
                lienzo.add(l1);
                l1.add(new MultiPath().rect(100, 100, 100, 100)
                                      .setStrokeColor( "#FFFFFF" ).setFillColor( "#CC0000" ).setDraggable(true) );
                l1.draw();

//        document.body.appendChild(btn);


        //HTMLBodyElement body   = DomGlobal.document.body;
        //HTMLDivElement  div    = (HTMLDivElement) DomGlobal.document.getElementById("demo");

        //lienzo.

//        buttonsPanel.getElement().getStyle().setMargin( 10, Style.Unit.PX );
//
//        RootPanel.get().add( mainPanel );
//
//        for ( final MyLienzoTest test : TESTS ) {
//
//            final Button button = new Button( test.getClass().getSimpleName() );
//            button.addClickHandler( new ClickHandler() {
//                @Override
//                public void onClick( ClickEvent clickEvent ) {
//                    createPanelForTest( test );
//                }
//            } );
//
//            addButton( button );
//
//        }
//
//        mainPanel.add( buttonsPanel );
//        mainPanel.add( screenButtonsPanel );
//        mainPanel.add( testsPanel );

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

//    private void applyGrid( final LienzoPanel panel ) {
//        // Grid.
//        Line line1 = new Line( 0, 0, 0, 0 )
//                .setStrokeColor( "#0000FF" )
//                .setAlpha( 0.2 );
//        Line line2 = new Line( 0, 0, 0, 0 )
//                .setStrokeColor( "#00FF00"  )
//                .setAlpha( 0.2 );
//
//        line2.setDashArray( 2,
//                2 );
//
//        GridLayer gridLayer = new GridLayer( 100, line1, 25, line2 );
//
//        panel.setBackgroundLayer( gridLayer );
//    }
}
