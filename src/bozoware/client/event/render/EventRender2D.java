package bozoware.client.event.render;

import best.azura.eventbus.events.EventNonCancellable;

public class EventRender2D extends EventNonCancellable {

    private final float partialTicks;

    public EventRender2D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
