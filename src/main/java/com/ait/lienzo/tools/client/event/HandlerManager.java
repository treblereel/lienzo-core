package com.ait.lienzo.tools.client.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.ait.lienzo.tools.client.event.INodeEvent.Type;
import com.ait.lienzo.gwtlienzo.event.shared.EventHandler;

public class HandlerManager
{
    private Object source;

    public Map<Type<?>, LinkedList<EventHandler>> map; //TODO

    private int firingDepth = 0;


    public HandlerManager(final Object source)
    {
        this.source = source;
    }

    public <H extends EventHandler> boolean isEventHandled(final Type<H> type)
    {
        return map.containsKey(type);
    }

    public <H extends EventHandler> HandlerRegistration addHandler(final Type<H> type, final EventHandler handler)
    {
        if (map == null)
        {
            map  = new HashMap<>();
        }
        LinkedList<EventHandler> handlers = map.get(type);
        if (handlers == null)
        {
            handlers = new LinkedList<>();
            map.put(type, handlers);
        }
        handlers.push(handler);

        HandlerRegistration reg = new HandlerRegistrationImpl(type, handler, this);

        return reg;
    }

    protected <H> void removeHandler(Type<H> type, EventHandler handler) {
        LinkedList<EventHandler> handlers = map.get(type);
        if ( handlers != null )
        {
            int index = handlers.indexOf(handler);
            handlers.subList(index, 1);
        }

        // no more handlers, so prune map.
        if ( handlers.isEmpty())
        {
            map.remove(type);
        }

        if (map.size() == 0)
        {
            map = null;
        }
//        if (this.firingDepth > 0) {
//            this.enqueueRemove(type, source, handler);
//        } else {
//            this.doRemoveNow(type, source, handler);
//        }

    }

    public void fireEvent(final INodeEvent event)
    {
        if (map == null)
        {
            // map is only created, once handlers are added
            return;
        }

        LinkedList<EventHandler> handlers = map.get(event.getAssociatedType());
        if (handlers==null)
        {
            // no handlers for this type have been added yet
            return;
        }
        Set<Throwable> causes   = null;
        try
        {
            firingDepth++;
            for(int i=0, length=handlers.size(); i < length; i++)
            {
                try
                {
                    event.dispatch(handlers.get(i));
                }
                catch (Throwable var11)
                {
                    if (causes == null) {
                        causes = new HashSet<>();
                    }
                    causes.add(var11);
                }
            }
            if (causes != null) {
                // @FIXME make Umbrella Exception work with JsSet (mdp)
                //throw new Error(causes.size() + "");
            }
        }
        finally
        {
            firingDepth--;
            if ( firingDepth == 0 )
            {
                // @FIXME fire defered (mdp)
            }
        }
    }

    public static class HandlerRegistrationImpl<H extends EventHandler> implements HandlerRegistration
    {
        private Type<H> type;
        private H handler;
        private HandlerManager manager;

        public HandlerRegistrationImpl(final Type<H> type, final H handler, final HandlerManager manager)
        {
            this.type = type;
            this.handler = handler;
            this.manager = manager;
        }

        @Override
        public void removeHandler()
        {
            manager.removeHandler(type, handler);
        }
    }

}
