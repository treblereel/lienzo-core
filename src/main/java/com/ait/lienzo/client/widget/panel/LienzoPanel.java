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


import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Viewport;
import org.gwtproject.dom.client.Style;

public abstract class LienzoPanel<P extends LienzoPanel>
{
    public abstract P add(Layer layer);

    public abstract P setBackgroundLayer(Layer layer);

    public abstract P setCursor(Style.Cursor cursor);

    public abstract void onResize();

    public abstract int getWidthPx();

    public abstract int getHeightPx();

    public abstract Viewport getViewport();

    public abstract void destroy();
}
