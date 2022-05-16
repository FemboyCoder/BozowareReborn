package bozoware.client.module.impl.movement;

import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import bozoware.client.event.player.EventUpdate;
import bozoware.client.module.Module;
import bozoware.client.module.ModuleAnnotation;
import bozoware.client.module.ModuleCategory;

@ModuleAnnotation(name = "Sprint", category = ModuleCategory.MOVEMENT, description = "Makes you sprint")
public class Sprint extends Module {

    @EventHandler
    public final Listener<EventUpdate> eventUpdateListener = event -> {
        if(mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0)
            mc.thePlayer.setSprinting(true);
    };

}
