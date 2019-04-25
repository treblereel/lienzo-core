package com.ait.lienzo.client.core.animation;

import org.gwtproject.core.client.Duration;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;

public class AnimationScheduler
{
    private static AnimationScheduler INSTANCE;

    public static AnimationScheduler get()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new AnimationScheduler();
        }
        return INSTANCE;
    }

    public AnimationHandle requestAnimationFrame(AnimationCallback callback) {
        return requestAnimationFrame(callback,
                                     null);
    }


    public AnimationHandle requestAnimationFrame(AnimationCallback callback,
                                                 Element element) {
        final int id = requestImplNew(callback,
                                      element);
        return new AnimationHandle() {
            @Override
            public void cancel() {
                cancelImpl(id);
            }
        };
    }

    private static int requestImplNew(AnimationCallback cb,
                                      Element element) {
        return DomGlobal.requestAnimationFrame(p0 -> cb.execute(Duration.currentTimeMillis()),
                                               element);
    }

    private static void cancelImpl(int id) {
        DomGlobal.cancelAnimationFrame(id);
    }

//    public void requestAnimationFrame(final com.ait.lienzo.client.core.animation.AnimationCallback animationCallback)
//    {
//    }

    /**
     * The callback used when an animation frame becomes available.
     */
    public interface AnimationCallback {
        /**
         * Invokes the command.
         *
         * @param timestamp the current timestamp
         */
        void execute(double timestamp);
    }

    /**
     * A handle to the requested animation frame created by
     * {@link #requestAnimationFrame(AnimationCallback, Element)}.
     */
    public abstract static class AnimationHandle {
        /**
         * Cancel the requested animation frame. If the animation frame is already
         * canceled, do nothing.
         */
        public abstract void cancel();
    }


}
