package org.roger600.lienzo.client;

import com.ait.lienzo.client.core.event.NodeMouseDownEvent;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.widget.LienzoHandlerManager;
import com.ait.lienzo.client.widget.LienzoPanel2;
import com.ait.lienzo.tools.client.Console;
import com.ait.lienzo.tools.client.event.EventType;

import elemental2.dom.DomGlobal;
import elemental2.dom.Event;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.MouseEvent;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(namespace= JsPackage.GLOBAL)
public class Test1
{
    public void exec(HTMLDivElement elm)
    {
//        elm.addEventListener(EventType.CLICKED.name(), (Event event) ->
//        {
//            Console.get().info("CLICKED");
//        });
//        Console.get().info("added CLICKED");




//        Console.get().info("event :" + EventType.MOUSE_DOWN.name());
//        elm.addEventListener(EventType.MOUSE_DOWN.name(), (Event event) ->
//        {
//            DomGlobal.window.alert("Hi3");
//        });

        // div.onclick = function() {
        //     alert('Hi1!');
        // };

        // div.addEventListener('click', function (event) {
        //     alert('Hi!');
        // })

        //elm.innerHTML = "hello1";
        LienzoPanel2 lienzo = new LienzoPanel2(elm);


        //lienzo.add()
        Layer l1 = new Layer();
        lienzo.add(l1);
        l1.add(new MultiPath().rect(100, 100, 100, 100)
                              .setStrokeColor( "#FFFFFF" ).setFillColor( "#CC0000" ).setDraggable(true) );
        l1.draw();
    }

}
