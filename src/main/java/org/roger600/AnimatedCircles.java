package org.roger600;

import java.util.ArrayList;
import java.util.List;

import org.roger600.lienzo.client.BaseExampleTest;
import org.roger600.lienzo.client.ExampleTest;

import com.ait.lienzo.client.core.animation.AnimationCallback;
import com.ait.lienzo.client.core.animation.AnimationScheduler;
import com.ait.lienzo.client.core.animation.AnimationScheduler.AnimationHandle;
import com.ait.lienzo.client.core.animation.IAnimation;
import com.ait.lienzo.client.core.animation.IAnimationCallback;
import com.ait.lienzo.client.core.animation.IAnimationHandle;
import com.ait.lienzo.client.core.animation.IndefiniteAnimation;
import com.ait.lienzo.client.core.animation.TimedAnimation;
import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.widget.LienzoPanel2;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.client.core.shape.Viewport;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsType;

import static jsinterop.annotations.JsPackage.GLOBAL;

@JsType(isNative = false, namespace = GLOBAL)
public class AnimatedCircles extends BaseExampleTest implements ExampleTest
{
    private IAnimationHandle    animationHandle;

    private       List<MotionCircle> circles       = new ArrayList<MotionCircle>();
    private       boolean            animate       = true;
    private final int                yBottomOffSet = 0;

    private int width;
    private int height;

    public AnimatedCircles(final String title)
    {
        super(title);
    }

    public void destroy() {
        super.destroy();
        animationHandle.stop();
    }

    public void init(LienzoPanel2 panel) {
        super.init(panel);
        layer.setListening(false);
        int total = 100; //GWT.isProdMode() ? 100 : 3;

        width = panel.getWidth();
        height = panel.getHeight();

        for (int i = 0; i < total; i++) {
            MotionCircle circle = new MotionCircle(Math.max(40, Math.random() * 60));
            circle.setAlpha(0.75).setX(Util.generateValueWithinBoundary(width, 125)).setY(Util.generateValueWithinBoundary(height, 125))
                  .setStrokeColor(Color.getRandomHexColor()).setStrokeWidth(2).setFillColor(Color.getRandomHexColor());
            circles.add(circle);
            layer.add(circle);

        }
    }

    @Override
    public void onResize()
    {
        width = panel.getWidth();
        height = panel.getHeight();
    }

    public void run() {
        IAnimationCallback callback = new IAnimationCallback() {
            @Override public void onStart(final IAnimation animation, final IAnimationHandle handle)
            {

            }

            @Override public void onFrame(final IAnimation animation, final IAnimationHandle handle)
            {
                if (animate) {
                    //animateCircle(((MotionCircle)animation.getNode()));
                    if (animate) {
                        for (MotionCircle circle : circles) {
                            animateCircle(circle);
                        }
                        layer.batch();
                        //AnimationScheduler.get().requestAnimationFrame(this);
                    }
                }
            }

            @Override public void onClose(final IAnimation animation, final IAnimationHandle handle)
            {

            }
        };

        IndefiniteAnimation animation = new IndefiniteAnimation(callback);
        animationHandle = animation.run();
    }

    private void animateCircle(MotionCircle circle) {

        double x = circle.getX();
        double y = circle.getY();
        double r = circle.getRadius();

        if ((circle.getxVelocity() > 0 && x + circle.getxVelocity() + r > width) ||
            (circle.getxVelocity() < 0 && x + circle.getxVelocity() - r < 0)) {
            circle.setxVelocity(-circle.getxVelocity());
        }

        if ((circle.getyVelocity() > 0 && y + circle.getyVelocity() + r > height - yBottomOffSet) ||
            (circle.getyVelocity() < 0 && y + circle.getyVelocity() - r < 0)) {
            circle.setyVelocity(-circle.getyVelocity());
        }

        circle.setX(x + circle.getxVelocity());
        circle.setY(y + circle.getyVelocity());
    }

    private static final class MotionCircle extends Circle
    {

        private double xVelocity = Math.random() * 3;
        private double yVelocity = Math.random() * 3;

        public MotionCircle(double radius) {
            super(radius);
        }

        public double getxVelocity() {
            return xVelocity;
        }

        public void setxVelocity(double xVelocity) {
            this.xVelocity = xVelocity;
        }

        public double getyVelocity() {
            return yVelocity;
        }

        public void setyVelocity(double yVelocity) {
            this.yVelocity = yVelocity;
        }

    }
}
