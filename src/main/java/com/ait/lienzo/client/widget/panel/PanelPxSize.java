package com.ait.lienzo.client.widget.panel;

import elemental2.core.JsNumber;
import elemental2.dom.CSSStyleDeclaration;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.ViewCSS;
import jsinterop.base.Js;

public class PanelPxSize {

    public int width;
    public int height;

    public static PanelPxSize getPanelSize(final HTMLDivElement element) {
        final CSSStyleDeclaration cs = Js.<ViewCSS>uncheckedCast(DomGlobal.window).getComputedStyle(element);
        final double paddingX = JsNumber.parseFloat(cs.paddingLeft.asString()) + JsNumber.parseFloat(cs.paddingRight.asString());
        final double paddingY = JsNumber.parseFloat(cs.paddingTop.asString()) + JsNumber.parseFloat(cs.paddingBottom.asString());
        final double borderX = JsNumber.parseFloat(cs.borderLeftWidth.asString()) + JsNumber.parseFloat(cs.borderRightWidth.asString());
        final double borderY = JsNumber.parseFloat(cs.borderTopWidth.asString()) + JsNumber.parseFloat(cs.borderBottomWidth.asString());
        final int width  = (int)(element.offsetWidth - paddingX - borderX);
        final int height = (int)(element.offsetHeight - paddingY - borderY);
        return new PanelPxSize(width, height);
    }

    public PanelPxSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidthPx() {
        return width;
    }

    public void setWidthPx(int width) {
        this.width = width;
    }

    public int getHeightPx() {
        return height;
    }

    public void setHeightPx(int height) {
        this.height = height;
    }
}
