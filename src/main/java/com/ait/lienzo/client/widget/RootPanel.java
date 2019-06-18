package com.ait.lienzo.client.widget;

import elemental2.core.Global;
import elemental2.core.JsArray;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLBodyElement;

public class RootPanel
{

    static RootPanel INSTANCE = new RootPanel();

    private JsArray<Element> elements = new JsArray<Element>();

    public static RootPanel get()
    {
        return INSTANCE;
    }

    public void add(Element child)
    {
        elements.push(child);
        DomGlobal.document.body.appendChild(child);
    }

    public void remove(Element child)
    {
        child.remove();
        DomGlobal.document.body.removeChild(child);
    }
}
