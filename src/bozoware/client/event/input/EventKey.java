package bozoware.client.event.input;

import best.azura.eventbus.core.Event;
import best.azura.eventbus.events.EventNonCancellable;
import org.lwjgl.input.Keyboard;

public class EventKey extends EventNonCancellable {

    private final int key;

    public EventKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public String getKeyName() {
        return Keyboard.getKeyName(key);
    }
}
