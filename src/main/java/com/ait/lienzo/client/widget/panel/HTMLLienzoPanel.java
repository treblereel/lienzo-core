/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ait.lienzo.client.widget.panel;


import elemental2.dom.CSSProperties;
import elemental2.dom.DomGlobal;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLDivElement;

import static com.ait.lienzo.client.widget.panel.PanelPxSize.getPanelSize;
import static elemental2.dom.DomGlobal.window;

public abstract class HTMLLienzoPanel<P extends HTMLLienzoPanel>
    extends LienzoPanel<P>
{
    private final PanelPxSize m_panelSize;
    private final HTMLDivElement m_elm;
    private final EventListener m_resizeListener;

    protected HTMLLienzoPanel(HTMLDivElement m_elm) {
        this.m_elm = m_elm;
        this.m_panelSize = getPanelSize(getParent());
        m_elm.tabIndex = 0;
        m_resizeListener = (e) ->
        {
            onResize();
        };
        window.addEventListener("resize", m_resizeListener);
    }

    public void onResize() {
        PanelPxSize parentSize = getPanelSize(getParent());
        setWidthPx(parentSize.getWidthPx());
        setHeightPx(parentSize.getHeightPx());
    }

    public int getWidthPx() {
        return m_panelSize.getWidthPx();
    }

    public int getHeightPx() {
        return m_panelSize.getHeightPx();
    }

    public void destroy() {
        window.removeEventListener("resize", m_resizeListener);
        m_elm.remove();
    }

    public void setWidthPx(int width)
    {
        if (m_panelSize.getWidthPx() != width) {
            m_panelSize.setWidthPx(width);
            m_elm.style.width = CSSProperties.WidthUnionType.of(width + "px");
        }
    }

    public void setHeightPx(int height)
    {
        if (m_panelSize.getHeightPx() != height) {
            m_panelSize.setHeightPx(height);
            m_elm.style.height = CSSProperties.HeightUnionType.of(height + "px");
        }
    }

    public HTMLDivElement getElement() {
        return m_elm;
    }

    protected HTMLDivElement getParent() {
        return (HTMLDivElement) m_elm.parentNode;
    }
}
