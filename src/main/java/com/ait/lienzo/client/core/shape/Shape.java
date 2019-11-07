/*
   Copyright (c) 2017 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.ait.lienzo.client.core.shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.ait.lienzo.client.core.Attribute;
import com.ait.lienzo.client.core.Context2D;
import com.ait.lienzo.client.core.config.LienzoCore;
import com.ait.lienzo.client.core.image.ImageLoader;
import com.ait.lienzo.client.core.shape.json.IFactory;
import com.ait.lienzo.client.core.shape.json.validators.ValidationContext;
import com.ait.lienzo.client.core.shape.json.validators.ValidationException;
import com.ait.lienzo.client.core.shape.wires.IControlHandle.ControlHandleType;
import com.ait.lienzo.client.core.shape.wires.IControlHandleFactory;
import com.ait.lienzo.client.core.shape.wires.IControlHandleList;
import com.ait.lienzo.client.core.types.BoundingBox;
import com.ait.lienzo.client.core.types.DashArray;
import com.ait.lienzo.client.core.types.FillGradient;
import com.ait.lienzo.client.core.types.LinearGradient;
import com.ait.lienzo.client.core.types.PathPartList;
import com.ait.lienzo.client.core.types.PatternGradient;
import com.ait.lienzo.client.core.types.RadialGradient;
import com.ait.lienzo.client.core.types.Shadow;
import com.ait.lienzo.client.widget.DefaultDragConstraintEnforcer;
import com.ait.lienzo.client.widget.DragConstraintEnforcer;
import com.ait.lienzo.shared.core.types.IColor;
import com.ait.lienzo.shared.core.types.LineCap;
import com.ait.lienzo.shared.core.types.LineJoin;
import com.ait.lienzo.shared.core.types.NodeType;
import com.ait.lienzo.shared.core.types.ShapeType;

import elemental2.dom.HTMLImageElement;
import elemental2.dom.Path2D;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Shapes are objects that can be drawn on a canvas.
 * A Shape can be added to a {@link Group} or to a {@link Layer}.
 * @param <T>
 */

public abstract class Shape<T extends Shape<T>> extends Node<T> implements IPrimitive<T>
{
    private ShapeType                 m_type;

    private String                    m_ckey;

    private final OptionalShapeFields m_opts = OptionalShapeFields.make();

    @JsProperty
    private       FillGradient              gradient;

    @JsProperty
    private       String                    fillColor;

    @JsProperty
    private       String                    strokeColor;

    @JsProperty
    private boolean                   fillBoundsForSelection = false;

    @JsProperty
    private double                    selectionBoundsOffset  = 0;

    @JsProperty
    private boolean                   fillShapeForSelection = LienzoCore.get().getDefaultFillShapeForSelection();

    @JsProperty
    private double                    selectionStrokeOffset = 0;

    @JsProperty
    private double                    strokeWidth = LienzoCore.get().getDefaultStrokeWidth();

    @JsProperty
    private LineCap                   lineCap;

    @JsProperty
    private LineJoin                  lineJoin;

    @JsProperty
    private DashArray                 dashArray;

    @JsProperty
    private double                    dashOffset = 0;

    @JsProperty
    private Shadow                    shadow;

    /**
     * By leaving this as 0, it is considered unset.
     * The spe default is 10.
     */
    @JsProperty
    private double                    miterLimit = 0;

    protected Shape(final ShapeType type)
    {
        super(NodeType.SHAPE);

        m_type = type;
    }

    public Shape(final ShapeType type, final Object node, final ValidationContext ctx) throws ValidationException
    {
        super(NodeType.SHAPE, node, ctx);

        m_type = type;

        if (hasFill())
        {
            FillGradient grad = getFillGradient();

            if (null != grad)
            {
                final PatternGradient patg = grad.asPatternGradient();

                if (null != patg)
                {
                    new ImageLoader(patg.getSrc())
                    {
                        @Override
                        public void onImageElementLoad(final HTMLImageElement elem)
                        {
                            setFillGradient(new PatternGradient(elem, patg.getRepeat()));

                            batch();
                        }

                        @Override
                        public void onImageElementError(String message)
                        {
                            LienzoCore.get().error(message);
                        }
                    };
                }
            }
        }
    }

    @Override
    public T draw()
    {
        final Layer layer = getLayer();

        if (null != layer)
        {
            layer.draw();
        }
        return cast();
    }

    @Override
    public T batch()
    {
        final Layer layer = getLayer();

        if (null != layer)
        {
            layer.batch();
        }
        return cast();
    }

    /**
     * Only sub-classes that wish to extend a Shape should use this.
     * 
     * @param type
     */
    protected void setShapeType(final ShapeType type)
    {
        m_type = type;
    }

    @Override
    public IFactory<?> getFactory()
    {
        return LienzoCore.get().getFactory(m_type);
    }

    @Override
    public Map<ControlHandleType, IControlHandleList> getControlHandles(ControlHandleType... types)
    {
        return getControlHandles(Arrays.asList(types));
    }

    @Override
    public Map<ControlHandleType, IControlHandleList> getControlHandles(List<ControlHandleType> types)
    {
        if ((null == types) || (types.isEmpty()))
        {
            return null;
        }
        if (types.size() > 1)
        {
            types = new ArrayList<>(new HashSet<>(types));
        }
        IControlHandleFactory factory = getControlHandleFactory();

        if (null == factory)
        {
            return null;
        }
        return factory.getControlHandles(types);
    }

    @Override
    public IControlHandleFactory getControlHandleFactory()
    {
        return m_opts.getControlHandleFactory();
    }

    @Override
    public T setControlHandleFactory(IControlHandleFactory factory)
    {
        m_opts.setControlHandleFactory(factory);

        return cast();
    }

    @Override
    public T copy()
    {
        final Node<?> node = copyUnchecked();

        if (null == node)
        {
            return null;
        }
        if (NodeType.SHAPE != node.getNodeType())
        {
            return null;
        }
        final Shape<?> shape = ((Shape<?>) node);

        if (getShapeType() != shape.getShapeType())
        {
            return null;
        }
        return shape.cast();
    }

    /**
     * Used internally. Draws the node in the current Context2D
     * without applying the transformation-related attributes 
     * (e.g. X, Y, ROTATION, SCALE, SHEAR, OFFSET and TRANSFORM.)
     * <p>
     * Shapes should apply the non-Transform related attributes (such a colors, strokeWidth etc.)
     * and draw the Shape's details (such as the the actual lines and fills.)
     */
    @Override
    protected void drawWithoutTransforms(final Context2D context, double alpha, BoundingBox bounds)
    {
        alpha = alpha * getAlpha();

        if (alpha <= 0)
        {
            return;
        }
        if (context.isSelection())
        {
            if (dofillBoundsForSelection(context, alpha))
            {
                return;
            }
        }
        else
        {
            setAppliedShadow(false);
        }
        if (prepare(context, alpha))
        {
            final boolean fill = fill(context, alpha);

            stroke(context, alpha, fill);
        }
    }

    public PathPartList getPathPartList()
    {
        return null;
    }

    protected final void setAppliedShadow(final boolean apsh)
    {
        m_opts.setAppliedShadow(apsh);
    }

    protected final boolean isAppliedShadow()
    {
        return m_opts.isAppliedShadow();
    }

    protected abstract boolean prepare(Context2D context, double alpha);

    /**
     * Fills the Shape using the passed attributes.
     * This method will silently also fill the Shape to its unique rgb color if the context is a buffer.
     * 
     * @param context
     * @param attr
     */
    protected boolean fill(final Context2D context, double alpha)
    {
        final boolean filled = hasFill();

        if ((filled) || (isFillShapeForSelection()))
        {
            alpha = alpha * getFillAlpha();

            if (alpha <= 0)
            {
                return false;
            }
            if (context.isSelection())
            {
                final String color = getColorKey();

                if (null == color)
                {
                    return false;
                }
                context.save();

                context.setFillColor(color);

                context.fill();

                context.restore();

                return true;
            }
            if (!filled)
            {
                return false;
            }
            context.save(getID());

            if (getShadow() != null)
            {
                doApplyShadow(context);
            }
            context.setGlobalAlpha(alpha);

            final String fill = getFillColor();

            if (null != fill)
            {
                context.setFillColor(fill);

                context.fill();

                context.restore();

                return true;
            }
            final FillGradient grad = getFillGradient();

            if (null != grad)
            {
                final String type = grad.getType();

                if (LinearGradient.TYPE.equals(type))
                {
                    context.setFillGradient(grad.asLinearGradient());

                    context.fill();

                    context.restore();

                    return true;
                }
                else if (RadialGradient.TYPE.equals(type))
                {
                    context.setFillGradient(grad.asRadialGradient());

                    context.fill();

                    context.restore();

                    return true;
                }
                else if (PatternGradient.TYPE.equals(type))
                {
                    context.setFillGradient(grad.asPatternGradient());

                    context.fill();

                    context.restore();

                    return true;
                }
            }
            context.restore();
        }
        return false;
    }

    protected boolean dofillBoundsForSelection(final Context2D context, final double alpha)
    {
        if (isFillBoundsForSelection())
        {
            if ((alpha * getFillAlpha()) > 0)
            {
                final String color = getColorKey();

                if (null != color)
                {
                    final BoundingBox bbox = getBoundingBox();

                    if (null != bbox)
                    {
                        final double wide = bbox.getWidth();

                        if (wide > 0)
                        {
                            final double high = bbox.getHeight();

                            if (high > 0)
                            {
                                context.setFillColor(color);

                                final double offset = getSelectionBoundsOffset();

                                context.fillRect(bbox.getX() - offset, bbox.getY() - offset, wide + offset, high + offset);
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    protected boolean fill(final Context2D context, double alpha, final Path2D path)
    {
        final boolean filled = hasFill();

        if ((filled) || (isFillShapeForSelection()))
        {
            alpha = alpha * getFillAlpha();

            if (alpha <= 0)
            {
                return false;
            }
            if (context.isSelection())
            {
                final String color = getColorKey();

                if (null == color)
                {
                    return false;
                }
                context.save();

                context.setFillColor(color);

                context.fill(path);

                context.restore();

                return true;
            }
            if (!filled)
            {
                return false;
            }
            context.save(getID());

            if (getShadow() != null)
            {
                doApplyShadow(context);
            }
            context.setGlobalAlpha(alpha);

            final String fill = getFillColor();

            if (null != fill)
            {
                context.setFillColor(fill);

                context.fill(path);

                context.restore();

                return true;
            }
            else
            {
                final FillGradient grad = getFillGradient();

                if (null != grad)
                {
                    final String type = grad.getType();

                    if (LinearGradient.TYPE.equals(type))
                    {
                        context.setFillGradient(grad.asLinearGradient());

                        context.fill(path);

                        context.restore();

                        return true;
                    }
                    else if (RadialGradient.TYPE.equals(type))
                    {
                        context.setFillGradient(grad.asRadialGradient());

                        context.fill(path);

                        context.restore();

                        return true;
                    }
                    else if (PatternGradient.TYPE.equals(type))
                    {
                        context.setFillGradient(grad.asPatternGradient());

                        context.fill(path);

                        context.restore();

                        return true;
                    }
                }
            }
            context.restore();
        }
        return false;
    }

    /**
     * Sets the Shape Stroke parameters.
     * 
     * @param context
     * @param attr
     * @return boolean
     */
    protected boolean setStrokeParams(final Context2D context, double alpha, final boolean filled)
    {
        double width = getStrokeWidth();

        String color = getStrokeColor();

        if (null == color)
        {
            if (width > 0)
            {
                color = LienzoCore.get().getDefaultStrokeColor();
            }
        }
        else if (width <= 0)
        {
            width = LienzoCore.get().getDefaultStrokeWidth();
        }
        if ((null == color) && (width <= 0))
        {
            if (filled)
            {
                return false;
            }
            color = LienzoCore.get().getDefaultStrokeColor();

            width = LienzoCore.get().getDefaultStrokeWidth();
        }
        alpha = alpha * getStrokeAlpha();

        if (alpha <= 0)
        {
            return false;
        }
        double offset = 0;
        
        if (context.isSelection())
        {
            color = getColorKey();

            if (null == color)
            {
                return false;
            }
            context.save();

            offset = getSelectionStrokeOffset();
        }
        else
        {
            context.save(getID());

            context.setGlobalAlpha(alpha);
        }
        context.setStrokeColor(color);

        context.setStrokeWidth(width + offset);

        if (!hasExtraStrokeAttributes())
        {
            return true;
        }
        boolean isdashed = false;

        if (getDashArray() != null)
        {
            if (LienzoCore.get().isLineDashSupported())
            {
                DashArray dash = getDashArray();

                if ((null != dash) && (dash.size() > 0))
                {
                    context.setLineDash(dash);

                    if (dashOffset > 0)
                    {
                        context.setLineDashOffset(getDashOffset());
                    }
                    isdashed = true;
                }
            }
        }
        if ((isdashed) || (doStrokeExtraProperties()))
        {
            if (lineJoin != null)
            {
                context.setLineJoin(getLineJoin());
            }
            if (lineCap != null )
            {
                context.setLineCap(getLineCap());
            }
            if (miterLimit > 0)
            {
                context.setMiterLimit(getMiterLimit());
            }
        }
        return true;
    }

    private final boolean hasExtraStrokeAttributes()
    {
        boolean hasAttribute = dashArray != null || lineJoin != null || lineCap != null || miterLimit > 0;
        return hasAttribute;
    }

    protected boolean doStrokeExtraProperties()
    {
        return true;
    }

    /**
     * Sets the Shape stroke.
     * 
     * @param context
     * @param attr
     */
    protected void stroke(final Context2D context,final double alpha, final boolean filled)
    {
        if (setStrokeParams(context, alpha, filled))
        {
            if (getShadow() != null && !context.isSelection())
            {
                doApplyShadow(context);
            }
            context.stroke();

            context.restore();
        }
    }

    protected void stroke(final Context2D context, final double alpha, final Path2D path, final boolean filled)
    {
        if (setStrokeParams(context, alpha, filled))
        {
            if (getShadow() != null && !context.isSelection())
            {
                doApplyShadow(context);
            }
            context.stroke(path);

            context.restore();
        }
    }

    /**
     * Applies this shape's Shadow.
     * 
     * @param context
     * @param attr
     * @return boolean
     */
    protected final void doApplyShadow(final Context2D context)
    {
        if (!isAppliedShadow() && getShadow() != null)
        {
            setAppliedShadow(true);

            final Shadow shadow = getShadow();

            if (null != shadow)
            {
                context.setShadow(shadow);
            }
        }
    }

    @Override
    public boolean isDragging()
    {
        return m_opts.isDragging();
    }

    @Override
    public T setDragging(final boolean drag)
    {
        m_opts.setDragging(drag);

        return cast();
    }

    /**
     * Gets the {@link DashArray}. If this is a solid line, the dash array is empty.
     *
     * @return {@link DashArray} if this line is not dashed, there will be no elements in the {@link DashArray}
     */
    public DashArray getDashArray()
    {
        return this.dashArray;
    }

    /**
     * Sets the dash array.
     *
     * @param array containsBoundingBox dash lengths
     * @return this Line
     */
    public T setDashArray(final DashArray array)
    {
        this.dashArray = array;

        return cast();
    }

    public double getDashOffset()
    {
        return this.dashOffset;
    }

    public T setDashOffset(final double offset)
    {
        this.dashOffset = offset;

        return cast();
    }

    /**
     * Sets the dash array with individual dash lengths.
     *
     * @param dash length of dash
     * @param dashes if specified, length of remaining dashes
     * @return this Line
     */
    public T setDashArray(final double... dashes)
    {
        setDashArray(new DashArray(dashes));

        return cast();
    }

    /**
     * Returns this shape cast as an {@link IPrimitive}
     * 
     * @return IPrimitive
     */
    @Override
    public IPrimitive<?> asPrimitive()
    {
        return this;
    }

    @Override
    public Shape<?> asShape()
    {
        return this;
    }

    /**
     * Returns the Shape type.
     * 
     * @return {@link ShapeType}
     */
    public ShapeType getShapeType()
    {
        return m_type;
    }

    /**
     * Returns unique RGB color assigned to the off-set Shape.
     * 
     * @return String
     */
    public String getColorKey()
    {
        return m_ckey;
    }

    protected void setColorKey(final String ckey)
    {
        m_ckey = ckey;
    }

    @Override
    public boolean removeFromParent()
    {
        final Node<?> parent = getParent();

        if (null != parent)
        {
            final Layer layer = parent.asLayer();

            if (null != layer)
            {
                layer.remove(this);

                return true;
            }
            final GroupOf<IPrimitive<?>, ?> group = parent.asGroupOf();

            if (null != group)
            {
                group.remove(this);

                return true;
            }
        }
        return false;
    }

    /**
     * Moves this shape one layer up.
     * 
     * @return T
     */
    @SuppressWarnings("unchecked")
    @Override
    public T moveUp()
    {
        final Node<?> parent = getParent();

        if (null != parent)
        {
            final IContainer<?, IPrimitive<?>> container = (IContainer<?, IPrimitive<?>>) parent.asContainer();

            if (null != container)
            {
                container.moveUp(this);
            }
        }
        return cast();
    }

    /**
     * Moves this shape one layer down.
     * 
     * @return T
     */
    @SuppressWarnings("unchecked")
    @Override
    public T moveDown()
    {
        final Node<?> parent = getParent();

        if (null != parent)
        {
            final IContainer<?, IPrimitive<?>> container = (IContainer<?, IPrimitive<?>>) parent.asContainer();

            if (null != container)
            {
                container.moveDown(this);
            }
        }
        return cast();
    }

    /**
     * Moves this shape to the top of the layers stack.
     * 
     * @return T
     */
    @SuppressWarnings("unchecked")
    @Override
    public T moveToTop()
    {
        final Node<?> parent = getParent();

        if (null != parent)
        {
            final IContainer<?, IPrimitive<?>> container = (IContainer<?, IPrimitive<?>>) parent.asContainer();

            if (null != container)
            {
                container.moveToTop(this);
            }
        }
        return cast();
    }

    /**
     * Moves this shape to the bottomw of the layers stack.
     * 
     * @return T
     */
    @SuppressWarnings("unchecked")
    @Override
    public T moveToBottom()
    {
        final Node<?> parent = getParent();

        if (null != parent)
        {
            final IContainer<?, IPrimitive<?>> container = (IContainer<?, IPrimitive<?>>) parent.asContainer();

            if (null != container)
            {
                container.moveToBottom(this);
            }
        }
        return cast();
    }


    public final boolean hasFill()
    {
        return gradient != null || fillColor != null;
    }

    public final T setFillGradient(final LinearGradient gradient)
    {
        this.gradient = gradient;
        return cast();
    }

    public final T setFillGradient(final RadialGradient gradient)
    {
        this.gradient = gradient;
        return cast();
    }

    public final T setFillGradient(final PatternGradient gradient)
    {
        this.gradient = gradient;
        return cast();
    }

    public final FillGradient getFillGradient()
    {
        return this.gradient;
    }

    public T setFillColor(String fill)
    {
        this.fillColor = fill;
        return cast();
    }

    public final String getFillColor()
    {
        return this.fillColor;
    }


    public final T setStrokeColor(String stroke)
    {
        this.strokeColor = stroke;
        return cast();
    }

    public final String getStrokeColor()
    {
        return this.strokeColor;
    }

    public boolean isFillShapeForSelection()
    {
        return this.fillShapeForSelection;
    }

    public T setFillShapeForSelection(final boolean selection)
    {
        this.fillShapeForSelection = selection;

        return cast();
    }

    public T setFillBoundsForSelection(final boolean selection)
    {
        this.fillBoundsForSelection = selection;

        return cast();
    }

    public final boolean isFillBoundsForSelection()
    {
        return this.fillBoundsForSelection;
    }

    public final double getSelectionBoundsOffset()
    {
        return this.selectionBoundsOffset;
    }

    public T setSelectionBoundsOffset(final double selectionBoundsOffset)
    {
        this.selectionBoundsOffset = selectionBoundsOffset;
        return cast();
    }

    /**
     * Sets the number of pixels that are used to increase
     * stroke size on the selection layer.
     */
    public final T setSelectionStrokeOffset(final double offset)
    {
        this.selectionStrokeOffset = offset;
        return cast();
    }

    /**
     * Gets the number of pixels that are used to increase
     * stroke size on the selection layer.
     */
    public final double getSelectionStrokeOffset()
    {
        return this.selectionStrokeOffset;
    }


    /**
     * Sets the fill color.
     * 
     * @param color ColorName
     * @return T
     */
    public T setFillColor(final IColor color)
    {
        return setFillColor(null == color ? null : color.getColorString());
    }

    /**
     * Sets the stroke color.
     * 
     * @param color Color or ColorName
     * @return T
     */
    public T setStrokeColor(final IColor color)
    {
        return setStrokeColor(null == color ? null : color.getColorString());
    }

    /**
     * Gets the stroke width.
     * 
     * @return double
     */
    public double getStrokeWidth()
    {
        return this.strokeWidth;
    }

    /**
     * Sets the stroke width for this shape.
     * 
     * @param width
     * @return T
     */
    public T setStrokeWidth(final double width)
    {
        this.strokeWidth = width;

        return cast();
    }

    /**
     * Gets the type of {@link LineJoin} for this shape.
     * 
     * @return {@link LineJoin}
     */
    public LineJoin getLineJoin()
    {
        return this.lineJoin;
    }

    /**
     * Sets the type of {@link LineJoin} for this shape.
     * 
     * @param linejoin
     * @return T
     */
    public T setLineJoin(final LineJoin linejoin)
    {
        this.lineJoin = lineJoin;

        return cast();
    }

//    public final void setMiterLimit(final double limit)
//    {
//        put(Attribute.MITER_LIMIT.getProperty(), limit);
//    }
//
//    public final double getMiterLimit()
//    {
//        return getDouble(Attribute.MITER_LIMIT.getProperty());
//    }

    /**
     * Sets the value of Miter Limit for this shape.
     * 
     * @param limit
     * @return T
     */

    public T setMiterLimit(final double limit)
    {
        this.miterLimit = limit;

        return cast();
    }

    /**
     * Gets the type of Miter Limit for this shape.
     * 
     * @return double
     */

    public double getMiterLimit()
    {
        return this.miterLimit;
    }

    /**
     * Gets the type of {@link LineCap} for this shape.
     * 
     * @return {@link LineCap}
     */
    public LineCap getLineCap()
    {
        return lineCap;
    }

    /**
     * Sets the type of {@link LineCap} for this shape.
     * 
     * @param linecap
     * @return T
     */
    public T setLineCap(final LineCap linecap)
    {
        this.lineCap = linecap;

        return cast();
    }

    /**
     * Gets this shape's {@link Shadow}
     * 
     * @return Shadow
     */
    public Shadow getShadow()
    {
        return this.shadow;
    }

    /**
     * Sets this shape's {@link Shadow}
     * 
     * @param shadow
     * @return T
     */
    public T setShadow(final Shadow shadow)
    {
        this.shadow = shadow;

        return cast();
    }

    /**
     * Attaches this Shape to the Layers Color Map
     */
    @Override
    public void attachToLayerColorMap()
    {
        final Layer layer = getLayer();

        if (null != layer)
        {
            layer.attachShapeToColorMap(this);
        }
    }

    /**
     * Detaches this Shape from the Layers Color Map
     */
    @Override
    public void detachFromLayerColorMap()
    {
        final Layer layer = getLayer();

        if (null != layer)
        {
            layer.detachShapeFromColorMap(this);
        }
    }

    // @FIXME serialization (mdp)
//    /**
//     * Serializes this shape as a {@link JSONObject}
//     *
//     * @return JSONObject
//     */
//    @Override
//    public JSONObject toJSONObject()
//    {
//        final JSONObject object = new JSONObject();
//
//        object.put("type", new JSONString(getShapeType().getValue()));
//
//        if (hasMetaData())
//        {
//            final MetaData meta = getMetaData();
//
//            if (false == meta.isEmpty())
//            {
//                // @FIXME (mdp)
//                //object.putString("meta", new JSONObject(meta.getJSO()));
//            }
//        }
//        //object.put("attributes", new JSONObject(getAttributes().getJSO()));
//
//        return object;
//    }

    @Override
    public DragConstraintEnforcer getDragConstraints()
    {
        final DragConstraintEnforcer enforcer = m_opts.getDragConstraintEnforcer();

        if (enforcer == null)
        {
            return new DefaultDragConstraintEnforcer();
        }
        else
        {
            return enforcer;
        }
    }

    @Override
    public T setDragConstraints(final DragConstraintEnforcer enforcer)
    {
        m_opts.setDragConstraintEnforcer(enforcer);

        return cast();
    }

    @Override
    public List<Attribute> getTransformingAttributes()
    {
        return LienzoCore.STANDARD_TRANSFORMING_ATTRIBUTES;
    }

    protected static abstract class ShapeFactory<S extends Shape<S>>extends NodeFactory<S>
    {
        protected ShapeFactory(final ShapeType type)
        {
            super(type.getValue());

            addAttribute(Attribute.X);

            addAttribute(Attribute.Y);

            addAttribute(Attribute.ALPHA);

            addAttribute(Attribute.FILL);

            addAttribute(Attribute.FILL_ALPHA);

            addAttribute(Attribute.STROKE);

            addAttribute(Attribute.STROKE_WIDTH);

            addAttribute(Attribute.STROKE_ALPHA);

            addAttribute(Attribute.DRAGGABLE);

            addAttribute(Attribute.EDITABLE);

            addAttribute(Attribute.SCALE);

            addAttribute(Attribute.SHEAR);

            addAttribute(Attribute.ROTATION);

            addAttribute(Attribute.OFFSET);

            addAttribute(Attribute.SHADOW);

            addAttribute(Attribute.LINE_CAP);

            addAttribute(Attribute.LINE_JOIN);

            addAttribute(Attribute.MITER_LIMIT);

            addAttribute(Attribute.DRAG_CONSTRAINT);

            addAttribute(Attribute.DRAG_BOUNDS);

            addAttribute(Attribute.DRAG_MODE);

            addAttribute(Attribute.DASH_ARRAY);

            addAttribute(Attribute.DASH_OFFSET);

            addAttribute(Attribute.FILL_SHAPE_FOR_SELECTION);

            addAttribute(Attribute.FILL_BOUNDS_FOR_SELECTION);

            addAttribute(Attribute.SELECTION_BOUNDS_OFFSET);

            addAttribute(Attribute.SELECTION_STROKE_OFFSET);

            addAttribute(Attribute.EVENT_PROPAGATION_MODE);
        }

        /**
         * Only factories that wish to extend other factories should use this.
         * 
         * @param type {@link ShapeType}
         */
        protected void setShapeType(final ShapeType type)
        {
            setTypeName(type.getValue());
        }
    }

    @JsType
    private static class OptionalShapeFields
    {
        @JsProperty
        private boolean drag;

        @JsProperty
        private boolean apsh;

        @JsProperty
        private DragConstraintEnforcer denf;


        @JsProperty
        private IControlHandleFactory hand;

        public static final OptionalShapeFields make()
        {
            return new OptionalShapeFields();
        }


        protected OptionalShapeFields()
        {
        }

        protected final boolean isDragging()
        {
			return this.drag;
        }

        protected final void setDragging(boolean drag)
        {
            this.drag = drag;
        }

        protected final boolean isAppliedShadow()
        {
			return this.apsh;
        }

        protected final void setAppliedShadow(boolean apsh)
        {
            this.apsh = apsh;
        }

        protected final DragConstraintEnforcer getDragConstraintEnforcer()
        {
			return this.denf;
        }

        protected final void setDragConstraintEnforcer(DragConstraintEnforcer denf)
        {
            this.denf = denf;
        }

        protected final IControlHandleFactory getControlHandleFactory()
        {
			return this.hand;
        }

        protected final void setControlHandleFactory(IControlHandleFactory hand)
        {
            this.hand = hand;
        }
    }
}
